
package sli.kim.classfile;

import java.io.*;

/**
* This class parses java class files and stores the information in a ClassInfo object.
*
* @see ClassInfo
*/
public class ClassFileReader implements AccessFlags {
	/**
	* Read in and parse a java class file.
	* 
	* @param in a DataInput stream to read in the class file.
	* @param classInfo a ClassInfo to consume the class information.
	* @throws ClassFileParseException if the class file is corrupt.
	* @throws IOException if the InputStream throws an IOException.
	*/
	public void read(InputStream in, ClassInfo classInfo)
		throws ClassFileParseException, IOException 
	{
		readClass(new DataInputStream(new BufferedInputStream(in)), classInfo);
	}

	private void readClass(DataInput in, ClassInfo classInfo)
		throws ClassFileParseException, IOException
	{
		short count;

		// Magic And Version Numbers
		{
			int magic = in.readInt();
			if (magic != 0xCAFEBABE)
				throw new ClassFileParseException("Invalid classfile magic number" + 
					": expected 0xCAFEBABE, found 0x" + Integer.toHexString(magic));
			short major = in.readShort(), minor = in.readShort();
			// assume we can handle changes to the minor version number
			if (major != 0x003)
				throw new ClassFileParseException("Unrecognized classfile version " + 
					major + "." + minor);
		}

		// Constant Pool
		ConstPool cp = new ConstPool();
		cp.read(in);
		classInfo.setConstPool(cp);

		// General Class Info
		{
			short flags = in.readShort();
			short classIndex = in.readShort();
			short superClassIndex = in.readShort();
			short classNameIndex = cp.getEntryAtIndex(classIndex).getClassNameIndex();
			short superClassNameIndex = cp.getEntryAtIndex(superClassIndex).getClassNameIndex();
			String className = cp.getEntryAtIndex(classNameIndex).getString();
			String superClassName = cp.getEntryAtIndex(superClassNameIndex).getString();

			if (Debug.readClass != null) Debug.println(Debug.readClass,
				"flags=" + flags +
				"; class index=" + classIndex +
				"; super class index=" + superClassIndex);

			classInfo.setAccessFlags(flags);
			classInfo.setName(className);
			classInfo.setSuperClassName(superClassName);
		}

		// Interfaces
		count = in.readShort();
		if (Debug.readClass != null) Debug.println(Debug.readClass,
			"#interfaces=" + count);
		Debug.indent();
		for (int i = 0; i < count; i++) 
			classInfo.addInterface(readInterface(cp, in));
		Debug.outdent();

		// Fields
		count = in.readShort();
		if (Debug.readClass != null) Debug.println(Debug.readClass,
			"#fields=" + count);
		Debug.indent();
		for (int i = 0; i < count; i++)
			classInfo.addField(readField(cp, in));
		Debug.outdent();

		// Methods
		count = in.readShort();
		if (Debug.readClass != null) Debug.println(Debug.readClass,
			"#methods=" + count);
		Debug.indent();
		for (int i = 0; i < count; i++)
			classInfo.addMethod(readMethod(cp, in));
		Debug.outdent();

		// Attributes
		count = in.readShort();
		if (Debug.readClass != null) Debug.println(Debug.readClass,
			"#attributes=" + count);
		Debug.indent();
		for (int i = 0; i < count; i++) 
			readClassAttribute(classInfo, cp, in);
		Debug.outdent();
	}

	private String readInterface(ConstPool cp, DataInput in)
		throws IOException
	{
		short classIndex = in.readShort();
		if (Debug.readInterface != null) Debug.println(Debug.readInterface,
			"class index=" + classIndex);
		short nameIndex = cp.getEntryAtIndex(classIndex).getClassNameIndex();
		return cp.getEntryAtIndex(nameIndex).getString();
	}

	private FieldInfo readField(ConstPool cp, DataInput in) 
		throws ClassFileParseException, IOException 
	{
		FieldInfo result;

		// General Field Info
		{
			short flags = in.readShort();
			short nameIndex = in.readShort();
			short signatureIndex = in.readShort();

			if (Debug.readField != null) Debug.println(Debug.readField,
				"flags=" + flags +
				"; name index=" + nameIndex +
				"; signature index=" + signatureIndex);

			result = new FieldInfo(flags, cp.getEntryAtIndex(nameIndex).getString(), 
				cp.getEntryAtIndex(signatureIndex).getString());
		}
	
		// Field Attributes
		short numAttributes = in.readShort();
		if (Debug.readField != null) Debug.println(Debug.readField,
			"#attributes=" + numAttributes);
		Debug.indent();
		for (int i = 0; i < numAttributes; i++)
			readFieldAttribute(result, cp, in);
		Debug.outdent();

		return result;
	}

	private MethodInfo readMethod(ConstPool cp, DataInput in) 
		throws ClassFileParseException, IOException 
	{
		MethodInfo result;

		// General Method Info
		{
			short flags = in.readShort();
			short nameIndex = in.readShort();
			short signatureIndex = in.readShort();
			String methodName = cp.getEntryAtIndex(nameIndex).getString();
			String methodSignature = cp.getEntryAtIndex(signatureIndex).getString();

			if (Debug.readMethod != null) Debug.println(Debug.readMethod,
				"flags=" + flags +
				"; name index=" + nameIndex +
				"; signature index=" + signatureIndex);

			result = new MethodInfo(flags, methodName, methodSignature);
		}

		// Method Attributes
		short methodAttrCount = in.readShort();
		if (Debug.readMethod != null) Debug.println(Debug.readMethod,
			"#attributes=" + methodAttrCount);
		Debug.indent();

		for (int iMethodAttr = 0; iMethodAttr < methodAttrCount; iMethodAttr++)
			readMethodAttribute(result, cp, in);

		Debug.outdent();

		return result;
	}

	private void readClassAttribute(ClassInfo classInfo, ConstPool cp, 
		DataInput in) throws IOException
	{
		short nameIndex = in.readShort();
		int length = in.readInt();

		// make sure we read the entire attribute -- if it has bad data,
		// an exception might get thrown before we've read it all
		byte[] bytes = new byte[length];
		in.readFully(bytes);
		in = new DataInputStream(new ByteArrayInputStream(bytes));

		if (Debug.readClass != null) Debug.println(Debug.readClass,
			"attribute name index=" + nameIndex +
			"; length=" + length);
		Debug.indent();

		try {

			String name = cp.getEntryAtIndex(nameIndex).getString();

			// SourceFile Attribute
			if (name.equals("SourceFile")) {
				short filenameIndex = in.readShort();
				if (Debug.readClass != null) Debug.println(Debug.readClass,
					"filename index=" + filenameIndex);
				classInfo.setSourceFile(cp.getEntryAtIndex(filenameIndex).getString());
			}

			else if (name.equals("InnerClasses"))
				classInfo.setInnerClasses(readInnerClasses(cp, in));

			else 
				classInfo.addAttribute(readUnknownAttribute(name, length, in));

		} catch (ConstPoolEntryError e) {
			if (Debug.readBadData != null) Debug.println(Debug.readBadData,
				"class attribute name index=" + nameIndex);
		}

		Debug.outdent();
	}

	private void readFieldAttribute(FieldInfo fieldInfo, ConstPool cp, 
		DataInput in) throws IOException
	{
		short nameIndex = in.readShort();
		String name = cp.getEntryAtIndex(nameIndex).getString();
		int length = in.readInt();

		// make sure we read the entire attribute -- if it has bad data,
		// an exception might get thrown before we've read it all
		byte[] bytes = new byte[length];
		in.readFully(bytes);
		in = new DataInputStream(new ByteArrayInputStream(bytes));

		if (Debug.readField != null) Debug.println(Debug.readField,
			"attribute name index=" + nameIndex +
			"; length=" + length);
		Debug.indent();

		try {

			// ConstantValue Attribute
			if (name.equals("ConstantValue")) {
				short cvIndex = in.readShort();
				if (Debug.readField != null) Debug.println(Debug.readField, 
					"constant value index=" + cvIndex);
				fieldInfo.setConstantValue(cp.getEntryAtIndex(cvIndex).getPrimitiveTypeValue());
			}

			else if (name.equals("Synthetic")) {
				if (Debug.readField != null) Debug.println(Debug.readField,
					"synthetic");
				fieldInfo.setSynthetic(true);
			}

			else
				fieldInfo.addAttribute(readUnknownAttribute(name, length, in));

		} catch (ConstPoolEntryError e) {
			if (Debug.readBadData != null) Debug.println(Debug.readBadData,
				"field attribute name index=" + nameIndex);
		}

		Debug.outdent();
	}

	private void readMethodAttribute(MethodInfo methodInfo, ConstPool cp, 
		DataInput in) throws IOException
	{
		short nameIndex = in.readShort();
		int length = in.readInt();

		// make sure we read the entire attribute -- if it has bad data,
		// an exception might get thrown before we've read it all
		byte[] bytes = new byte[length];
		in.readFully(bytes);
		in = new DataInputStream(new ByteArrayInputStream(bytes));

		if (Debug.readMethod != null) Debug.println(Debug.readMethod,
			"attribute name index=" + nameIndex +
			"; length=" + length);
		Debug.indent();

		try {

			String name = cp.getEntryAtIndex(nameIndex).getString();
			if (name.equals("Exceptions")) {
				int count = in.readShort();
				for (int i = 0; i < count; i++) {
					short exceptionClassIndex = in.readShort();
					short exceptionClassNameIndex = cp.getEntryAtIndex(exceptionClassIndex).getClassNameIndex();
					String exceptionName = cp.getEntryAtIndex(exceptionClassNameIndex). getString();
					methodInfo.addException(exceptionName);
				}
			}

			else if (name.equals("Code")) 
				methodInfo.setCodeInfo(readCode(cp, in));

			else if (name.equals("Deprecated")) {
				if (Debug.readMethod != null) Debug.println(Debug.readMethod,
					"deprecated");
				methodInfo.setDeprecated(true);
			}

			else 
				methodInfo.addAttribute(readUnknownAttribute(name, length, in));

		} catch (ConstPoolEntryError e) {
			if (Debug.readBadData != null) Debug.println(Debug.readBadData,
				"method attribute name index=" + nameIndex);
		}
		
		Debug.outdent();
	}

	private CodeInfo readCode(ConstPool cp, DataInput in) 
		throws IOException 
	{
		// General Code Info
		short maxStack = in.readShort();
		short maxLocals = in.readShort();
		byte[] bytecode = new byte[in.readInt()];

		if (Debug.readCode != null) Debug.println(Debug.readCode,
			"maxStack=" + maxStack +
			"; maxLocals=" + maxLocals +
			"; bytecode length=" + bytecode.length);
		
		in.readFully(bytecode);

		// Exception Table
		ExceptionInfo[] exceptionTable = new ExceptionInfo[in.readShort()];
		if (Debug.readCode != null) Debug.println(Debug.readCode,
			"exception table length=" + exceptionTable.length);
		Debug.indent();
		for (int i = 0; i < exceptionTable.length; i++) {
			short startPC = in.readShort();
			short endPC = in.readShort();
			short handlerPC = in.readShort();
			short catchTypeIndex = in.readShort();

			if (Debug.readCode != null) Debug.println(Debug.readCode,
				"startPC=" + startPC +
				"; endPC=" + endPC +
				"; handlerPC=" + handlerPC +
				"; catchTypeIndex=" + catchTypeIndex);

			String catchType = null;
			if (catchTypeIndex != 0) {		// index is null for finally blocks
				short catchTypeNameIndex = 
					cp.getEntryAtIndex(catchTypeIndex).getClassNameIndex();
				catchType = cp.getEntryAtIndex(catchTypeNameIndex).getString();
			}
			exceptionTable[i] = 
				new ExceptionInfo(startPC, endPC, handlerPC, catchType);
		}
		Debug.outdent();

		CodeInfo codeInfo = 
			new CodeInfo(maxStack, maxLocals, bytecode, exceptionTable);

		// Code Attributes
		short codeAttrCount = in.readShort();
		if (Debug.readCode != null) Debug.println(Debug.readCode,
			"#attributes=" + codeAttrCount);
		Debug.indent();
		for (int iCodeAttr = 0; iCodeAttr < codeAttrCount; iCodeAttr++)
			readCodeAttribute(codeInfo, cp, in);
		Debug.outdent();

		return codeInfo;
	}		

	private void readCodeAttribute(CodeInfo codeInfo, ConstPool cp, 
		DataInput in) throws IOException
	{
		short nameIndex = in.readShort();
		int length = in.readInt();

		// make sure we read the entire attribute -- if it has bad data,
		// an exception might get thrown before we've read it all
		byte[] bytes = new byte[length];
		in.readFully(bytes);
		in = new DataInputStream(new ByteArrayInputStream(bytes));

		if (Debug.readCode != null) Debug.println(Debug.readCode,
			"code attribute name index=" + nameIndex +
			"; length=" + length);
		Debug.indent();

		try {

			String name = cp.getEntryAtIndex(nameIndex).getString();
			if (name.equals("LineNumberTable")) 
				codeInfo.setLineNumberTable(readLineNumberTable(in));
			else if (name.equals("LocalVariableTable")) 
				codeInfo.setLocalVariableTable(readLocalVariableTable(cp, in));
			else
				codeInfo.addAttribute(readUnknownAttribute(name, length, in));

		} catch (ConstPoolEntryError e) {
			if (Debug.readBadData != null) Debug.println(Debug.readBadData,
				"code attribute name index=" + nameIndex);
		}

		Debug.outdent();
	}

	private InnerClassInfo[] readInnerClasses(ConstPool cp, DataInput in) 
		throws IOException
	{
		short rows = in.readShort();
		if (Debug.readInnerClasses != null) Debug.println(Debug.readInnerClasses,
			"#inner classes=" + rows);
		InnerClassInfo[] classes = new InnerClassInfo[rows];
		for (int i = 0; i < rows; i++) {
			short innerClassIndex = in.readShort();
			short outerClassIndex = in.readShort();
			short simpleNameIndex = in.readShort();
			short flags = in.readShort();

			if (Debug.readInnerClasses != null) Debug.println(Debug.readInnerClasses,
				"inner class index=" + innerClassIndex +
				"; outer class index=" + outerClassIndex +
				"; simple name index=" + simpleNameIndex +
				"; flags=" + flags);

			short innerClassNameIndex = cp.getEntryAtIndex(innerClassIndex).getClassNameIndex();
			String innerClassName = cp.getEntryAtIndex(innerClassNameIndex).getString();
			String outerClassName = null;
			if (outerClassIndex != 0) {
				short outerClassNameIndex = cp.getEntryAtIndex(outerClassIndex).getClassNameIndex();
				outerClassName = cp.getEntryAtIndex(outerClassNameIndex).getString();
			}
			String simpleName = null;
			if (simpleNameIndex != 0)
				simpleName = cp.getEntryAtIndex(simpleNameIndex).getString();

			classes[i] = new InnerClassInfo(innerClassName, outerClassName, simpleName, flags);
		}
		return classes;
	}

	private LocalVariableInfo[] readLocalVariableTable(ConstPool cp, DataInput in) 
		throws IOException
	{
		LocalVariableInfo[] table = new LocalVariableInfo[in.readShort()];
		if (Debug.readLocalVariables != null) Debug.println(Debug.readLocalVariables,
			"#local variables=" + table.length);
		for (int i = 0; i < table.length; i++) {
			short startPC = in.readShort();
			short length = in.readShort();
			short nameIndex = in.readShort();
			short signatureIndex = in.readShort();
			short slot= in.readShort();
			if (Debug.readLocalVariables != null) Debug.println(Debug.readLocalVariables,
				"start PC=" + startPC +
				"; length=" + length +
				"; name index=" + nameIndex +
				"; signature index=" + signatureIndex +
				"; slot=" + slot);

			String name = cp.getEntryAtIndex(nameIndex).getString();
			String signature = cp.getEntryAtIndex(signatureIndex).getString();
			table[i] = new LocalVariableInfo(startPC, length, name, signature, slot);
		}
		return table;
	}

	private LineNumberInfo[] readLineNumberTable(DataInput in)
		throws IOException
	{
		LineNumberInfo[] table = new LineNumberInfo[in.readShort()];
		if (Debug.readLineNumbers != null) Debug.println(Debug.readLineNumbers,
			"#line numbers=" + table.length);
		for (int i = 0; i < table.length; i++) {
			short startPC = in.readShort();
			short lineNumber = in.readShort();

			if (Debug.readLineNumbers != null) Debug.println(Debug.readLineNumbers,
				"start PC=" + startPC +
				"; line number=" + lineNumber);

			table[i] = new LineNumberInfo(startPC, lineNumber);
		}
		return table;
	}

	private AttributeInfo readUnknownAttribute(String name, int length, DataInput in) 
		throws IOException
	{
		if (Debug.readUnknownAttribute != null) Debug.println(Debug.readUnknownAttribute,
			"attribute name=\"" + name + "\"");
		byte[] data = new byte[length];
		in.readFully(data);
		return new AttributeInfo(name, data);
	}
}
