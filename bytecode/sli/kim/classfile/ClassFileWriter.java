package	sli.kim.classfile;

import java.util.*;
import java.io.*;

/**
* This class gets information from a ClassInfo and writes it out in the 
* java classfile format.
*
* @see ClassInfo
*/
public class ClassFileWriter {
	/**
	* Write a ClassInfo out to a stream in the java classfile format.
	* @throws ClassFileWriteException if there is incorrect or missing 
	*     information in the ClassInfo.
	* @throws IOException if the OutputStream throws an IOException.
	*/
	public void write(ClassInfo classInfo, OutputStream out) 
		throws IOException, ClassFileWriteException 
	{
		ConstPool cp = classInfo.getConstPool();
		if (cp == null)
			cp = new ConstPool();
		// do a dry run to make sure ConstPool has all the entries it needs.
		boolean debugEnabled = Debug.isEnabled();
		Debug.setEnabled(false);
		writeClass(cp, classInfo, new NullOutputStream());
		Debug.setEnabled(debugEnabled);
		// now do the real write
		BufferedOutputStream bos = new BufferedOutputStream(out);
		DataOutputStream dos = new DataOutputStream(bos);
		writeClass(cp, classInfo, dos);
		dos.flush(); dos.close();
		bos.flush(); bos.close();
	}

	private void writeClass(ConstPool cp, ClassInfo classInfo, DataOutput out)
		throws IOException, ClassFileWriteException
	{
		out.writeInt(0xCAFEBABE);
		out.writeInt(0x0003002D);

		cp.write(out);

		// General Class Info
		{
			short flags = classInfo.getAccessFlags();
			short classIndex = cp.getIndexOfClassAdd(classInfo.getName());
			short superClassIndex = cp.getIndexOfClassAdd(classInfo.getSuperClassName());

			if (Debug.writeClass != null) Debug.println(Debug.writeClass,
				"flags=" + flags +
				"; class index=" + classIndex +
				"; super class index=" + superClassIndex);

			out.writeShort(flags);
			out.writeShort(classIndex);
			out.writeShort(superClassIndex);
		}

		// Interfaces
		String[] interfaces = classInfo.getInterfaces();
		if (Debug.writeClass != null) Debug.println(Debug.writeClass,
			"#interfaces=" + interfaces.length);
		Debug.indent();
			out.writeShort(interfaces.length);
			for (int i = 0; i < interfaces.length; i++) 
				writeInterface(interfaces[i], cp, out);
		Debug.outdent();

		// Fields
		FieldInfo[] fields = classInfo.getFields();
		if (Debug.writeClass != null) Debug.println(Debug.writeClass,
			"#fields=" + fields.length);
		Debug.indent();
			out.writeShort(fields.length);
			for (int i = 0; i < fields.length; i++)
				writeField(fields[i], cp, out);
		Debug.outdent();

		// Methods
		MethodInfo[] methods = classInfo.getMethods();
		if (Debug.writeClass != null) Debug.println(Debug.writeClass,
			"#methods=" + methods.length);
		Debug.indent();
			out.writeShort(methods.length);
			for (int i = 0; i < methods.length; i++)
				writeMethod(methods[i], cp, out);
		Debug.outdent();

		// Attributes
		AttributeInfo[] attributes = classInfo.getAttributes();
		String sourceFile = classInfo.getSourceFile();
		InnerClassInfo[] innerClasses = classInfo.getInnerClasses();
		int numAttributes = attributes.length + (sourceFile != null ? 1 : 0) +
			(innerClasses != null ? 1 : 0);

		if (Debug.writeClass != null) Debug.println(Debug.writeClass,
			"#attributes=" + numAttributes);
		Debug.indent();

		out.writeShort(numAttributes);

		// SourceFile Attribute
		if (sourceFile != null)
			writeSourceFile(sourceFile, cp, out);

		// InnerClasses Attribute
		if (innerClasses != null)
			writeInnerClasses(innerClasses, cp, out);

		// Other Attributes
		writeUnknownAttributes(attributes, cp, out);

		Debug.outdent();
	}

	private void writeInterface(String className, ConstPool cp, DataOutput out) 
		throws IOException
	{
		short classIndex = cp.getIndexOfClassAdd(className);
		if (Debug.writeInterface != null) Debug.println(Debug.writeInterface,
			"class index=" + classIndex);
		out.writeShort(classIndex);
	}

	private void writeField(FieldInfo fieldInfo, ConstPool cp, DataOutput out) 
		throws IOException, ClassFileWriteException
	{
		// General Field Info
		{
			short flags = fieldInfo.getAccessFlags();
			short nameIndex = cp.getIndexOfUTFAdd(fieldInfo.getName());
			short signatureIndex = cp.getIndexOfUTFAdd(fieldInfo.getSignature());

			if (Debug.writeField != null) Debug.println(Debug.writeField, 
				"flags=" + flags + 
				"; name index=" + nameIndex + 
				"; signature index=" + signatureIndex);

			out.writeShort(flags);
			out.writeShort(nameIndex);
			out.writeShort(signatureIndex);
		}
		
		// Field Attributes
		AttributeInfo[] attributes = fieldInfo.getAttributes();
		Object constValue = fieldInfo.getConstantValue();
		int numAttributes = (constValue != null ? 1 : 0) + attributes.length;

		if (Debug.writeField != null) Debug.println(Debug.writeField,
			"#attributes=" + numAttributes);
		Debug.indent();

		out.writeShort((short)numAttributes);

		// ConstantValue Attribute
		if (constValue != null) 
			writeConstantValue(constValue, cp, out);

		// Synthetic Attribute
		if (fieldInfo.isSynthetic()) {
			writeAttributeNameIndex("Synthetic", Debug.writeField, cp, out);
			out.writeInt(0);	// length == 0
		}

		// Other Attributes
		writeUnknownAttributes(fieldInfo.getAttributes(), cp, out);

		Debug.outdent();
	}

	private void writeMethod(MethodInfo methodInfo, ConstPool cp, 
		DataOutput out) throws IOException
	{
		// General Method Info
		{
			short flags = methodInfo.getAccessFlags();
			short nameIndex = cp.getIndexOfUTFAdd(methodInfo.getName());
			short signatureIndex = cp.getIndexOfUTFAdd(methodInfo.getSignature());

			if (Debug.writeMethod != null) Debug.println(Debug.writeMethod,
				"flags=" + flags + 
				"; name index=" + nameIndex + 
				"; signature index=" + signatureIndex);

			out.writeShort(flags);
			out.writeShort(nameIndex);
			out.writeShort(signatureIndex);
		}

		// Method Attributes
		AttributeInfo[] methodAttributes = methodInfo.getAttributes();
		String[] exceptions = methodInfo.getExceptions();
		boolean deprecated = methodInfo.isDeprecated();
		if (exceptions.length == 0)
			exceptions = null;
		CodeInfo codeInfo = methodInfo.getCodeInfo();
		int numAttributes = (exceptions != null ? 1 : 0) + (codeInfo != null ? 1 : 0) + 
			(deprecated ? 1 : 0) + methodAttributes.length;

		if (Debug.writeMethod != null) Debug.println(Debug.writeMethod, 
			"#attributes=" + numAttributes);
		Debug.indent();

		out.writeShort((short)numAttributes);

		// Exceptions Attribute
		if (exceptions != null) 
			writeExceptions(exceptions, cp, out);

		// Code Attribute
		if (codeInfo != null) 
			writeCode(codeInfo, cp, out);

		// Deprecated Attribute
		if (deprecated) {
			writeAttributeNameIndex("Deprecated", Debug.writeMethod, cp, out);
			out.writeInt(0);	// length == 0
		}

		// Other Method Attributes
		writeUnknownAttributes(methodAttributes, cp, out);

		Debug.outdent();
	}

	private void writeCode(CodeInfo codeInfo, ConstPool cp, DataOutput out)
		throws IOException
	{
		writeAttributeNameIndex("Code", Debug.writeMethod, cp, out);
		out = new LengthFirstOS(out);

		// General Code Info
		{
			short maxStack = codeInfo.getMaxStack();
			short maxLocals = codeInfo.getMaxLocals();
			byte[] bytecode = codeInfo.getBytecode();

			if (Debug.writeCode != null) Debug.println(Debug.writeCode, 
				"maxStack=" + maxStack +
				"; maxLocals=" + maxLocals +
				"; bytecode length=" + bytecode.length);

			out.writeShort(maxStack);
			out.writeShort(maxLocals);
			out.writeInt(bytecode.length);
			out.write(bytecode);
		}

		// Exception Table
		ExceptionInfo[] exceptionTable = codeInfo.getExceptionTable();

		if (Debug.writeCode != null) Debug.println(Debug.writeCode, 
			"exception table length=" + exceptionTable.length);
		Debug.indent();

		out.writeShort(exceptionTable.length);
		for (int i = 0; i < exceptionTable.length; i++) {
			short startPC = exceptionTable[i].startPC;
			short endPC = exceptionTable[i].endPC;
			short handlerPC = exceptionTable[i].handlerPC;
			String catchType = exceptionTable[i].catchType;
			short catchTypeIndex = 0;
			if (catchType != null)	// catch type is null for finally blocks
				cp.getIndexOfClassAdd(exceptionTable[i].catchType);

			if (Debug.writeCode != null) Debug.println(Debug.writeCode,
				"startPC = " + startPC +
				"; endPC = " + endPC +
				"; handlerPC = " + handlerPC +
				"; catch type index=" + catchTypeIndex);

			out.writeShort(startPC);
			out.writeShort(endPC);
			out.writeShort(handlerPC);
			out.writeShort(catchTypeIndex);
		}

		Debug.outdent();

		// Code Attributes
		AttributeInfo[] codeAttributes = codeInfo.getAttributes();
		LineNumberInfo[] lineNumberTable = codeInfo.getLineNumberTable();
		LocalVariableInfo[] localVariableTable = codeInfo.getLocalVariableTable();
		int numCodeAttributes = (lineNumberTable != null ? 1 : 0) +
			(localVariableTable != null ? 1 : 0) + codeAttributes.length;

		if (Debug.writeCode != null) Debug.println(Debug.writeCode,
			"#attributes=" + numCodeAttributes);
		Debug.indent();

		out.writeShort(numCodeAttributes);

		// LineNumberTable Attribute
		if (lineNumberTable != null) 
			writeLineNumberTable(lineNumberTable, cp, out);

		// LocalVariableTable Attribute
		if (localVariableTable != null) 
			writeLocalVariableTable(localVariableTable, cp, out);

		// Other Code Attributes
		writeUnknownAttributes(codeAttributes, cp, out);
		((LengthFirstOS)out).close();

		Debug.outdent();
	}

	private void writeSourceFile(String filename, ConstPool cp, DataOutput out)
		throws IOException
	{
		writeAttributeNameIndex("SourceFile", Debug.writeClass, cp, out);

		out.writeInt(2);		// length of attribute
		short filenameIndex = cp.getIndexOfUTFAdd(filename);

		Debug.indent();
		if (Debug.writeClass != null) Debug.println(Debug.writeClass,
			"source file index=" + filenameIndex);
		Debug.outdent();

		out.writeShort(filenameIndex);
	}

	private void writeConstantValue(Object constValue, ConstPool cp, DataOutput out)
		throws IOException, ClassFileWriteException
	{
		writeAttributeNameIndex("ConstantValue", Debug.writeField, cp, out);
		out = new LengthFirstOS(out);
		ConstPoolEntry searchEntry = new ConstPoolEntry();
		if (constValue instanceof String)
			searchEntry.setString(cp.getIndexOfUTFAdd((String)constValue));
		else if (constValue instanceof Integer)
			searchEntry.setInt(((Integer)constValue).intValue());
		else if (constValue instanceof Long)
			searchEntry.setLong(((Long)constValue).longValue());
		else if (constValue instanceof Float)
			searchEntry.setFloat(((Float)constValue).floatValue());
		else if (constValue instanceof Double)
			searchEntry.setDouble(((Double)constValue).doubleValue());
		else
			throw new ClassFileWriteException("Unrecognized constant value " + constValue);
		short cvIndex = cp.getIndexOfEntryAdd(searchEntry);

		Debug.indent();
		if (Debug.writeField != null) Debug.println(Debug.writeField, 
			"constant value index=" + cvIndex);
		Debug.outdent();

		out.writeShort(cvIndex);
		((LengthFirstOS)out).close();
	}

	private void writeExceptions(String[] exceptions, ConstPool cp, 
		DataOutput out) throws IOException
	{
		writeAttributeNameIndex("Exceptions", Debug.writeMethod, cp, out);
		out = new LengthFirstOS(out);

		if (Debug.writeMethod != null) Debug.println(Debug.writeMethod, 
			"#exceptions=" + exceptions.length);
		Debug.indent();

		out.writeShort(exceptions.length);
		for (int i = 0; i < exceptions.length; i++) {
			short classIndex = cp.getIndexOfClassAdd(exceptions[i]);

			if (Debug.writeMethod != null) Debug.println(Debug.writeMethod, 
				"exception class index = " + classIndex);

			out.writeShort(classIndex);
		}

		Debug.outdent();

		((LengthFirstOS)out).close();
	}

	private void writeLocalVariableTable(LocalVariableInfo[] localVariableTable,
		ConstPool cp, DataOutput out) throws IOException
	{
		writeAttributeNameIndex("LocalVariableTable", Debug.writeCode, cp, out);
		out = new LengthFirstOS(out);

		Debug.indent();
		if (Debug.writeLocalVariables != null) Debug.println(Debug.writeLocalVariables,
			"#local variables=" + localVariableTable.length);

		out.writeShort(localVariableTable.length);
		for (int i = 0; i < localVariableTable.length; i++) {
			short startPC = localVariableTable[i].startPC;
			short length = localVariableTable[i].length;
			short nameIndex = cp.getIndexOfUTFAdd(localVariableTable[i].name);
			short signatureIndex = cp.getIndexOfUTFAdd(localVariableTable[i].signature);
			short slot = localVariableTable[i].slot;

			if (Debug.writeLocalVariables != null) Debug.println(Debug.writeLocalVariables,
				"startPC=" + startPC +
				"; length=" + length +
				"; nameIndex=" + nameIndex +
				"; signatureIndex=" + signatureIndex +
				"; slot=" + slot);

			out.writeShort(startPC);
			out.writeShort(length);
			out.writeShort(nameIndex);
			out.writeShort(signatureIndex);
			out.writeShort(slot);
		}
		((LengthFirstOS)out).close();

		Debug.outdent();
	}

	private void writeLineNumberTable(LineNumberInfo[] lineNumberTable,
		ConstPool cp, DataOutput out) throws IOException
	{
		writeAttributeNameIndex("LineNumberTable", Debug.writeCode, cp, out);
		out = new LengthFirstOS(out);

		Debug.indent();
		if (Debug.writeLineNumbers != null) Debug.println(Debug.writeLineNumbers, 
			"#line numbers=" + lineNumberTable.length);

		out.writeShort(lineNumberTable.length);
		for (int i = 0; i < lineNumberTable.length; i++) {
			short startPC = lineNumberTable[i].startPC;
			short lineNumber = lineNumberTable[i].lineNumber;

			if (Debug.writeLineNumbers != null) Debug.println(Debug.writeLineNumbers,
				"startPC=" + startPC +
				"; lineNumber=" + lineNumber);

			out.writeShort(startPC);
			out.writeShort(lineNumber);
		}
		((LengthFirstOS)out).close();

		Debug.outdent();
	}

	private void writeInnerClasses(InnerClassInfo[] innerClasses, ConstPool cp, 
		DataOutput out) throws IOException
	{
		writeAttributeNameIndex("InnerClasses", Debug.writeClass, cp, out);
		out = new LengthFirstOS(out);

		Debug.indent();
		if (Debug.writeInnerClasses != null) Debug.println(Debug.writeInnerClasses,
			"#inner classes=" + innerClasses.length);

		out.writeShort(innerClasses.length);
		for (int i = 0; i < innerClasses.length; i++) {
			short innerClassIndex = cp.getIndexOfClassAdd(innerClasses[i].innerClass);
			short outerClassIndex = 0;
			if (innerClasses[i].outerClass != null)		// can be null if non-member class
				outerClassIndex = cp.getIndexOfClassAdd(innerClasses[i].outerClass);
			short simpleNameIndex = 0;
			if (innerClasses[i].simpleName != null)		// can be null if anonymous class
				simpleNameIndex = cp.getIndexOfUTFAdd(innerClasses[i].simpleName);
			short flags = innerClasses[i].flags;

			if (Debug.writeInnerClasses != null) Debug.println(Debug.writeInnerClasses,
				"inner class index=" + innerClassIndex +
				"; outer class index=" + outerClassIndex +
				"; simple name index=" + simpleNameIndex +
				"; flags=" + flags);

			out.writeShort(innerClassIndex);
			out.writeShort(outerClassIndex);
			out.writeShort(simpleNameIndex);
			out.writeShort(flags);
		}
		((LengthFirstOS)out).close();

		Debug.outdent();
	}

	private void writeAttributeNameIndex(String attrName, String debugMsg,
		ConstPool cp, DataOutput out) throws IOException
	{
		short index = cp.getIndexOfUTFAdd(attrName);

		if (debugMsg != null) Debug.println(debugMsg,
			"attribute name index=" + index);

		out.writeShort(index);
	}

	private void writeUnknownAttributes(AttributeInfo[] attributes, 
		ConstPool cp, DataOutput out) throws IOException 
	{
		for (int i = 0; i < attributes.length; i++) {
			short attrNameIndex = cp.getIndexOfUTFAdd(attributes[i].getName());
			byte[] data = attributes[i].getData();

			if (Debug.writeUnknownAttribute != null) Debug.println(Debug.writeUnknownAttribute,
				"attribute name index=" + attrNameIndex +
				"; attribute length=" + data.length);

			out.writeShort(attrNameIndex);
			out.writeInt(data.length);
			out.write(data);
		}
	}
}

// Special output stream to help with writing attributes.
// When we write attributes, we need to know the size of the entire
// attribute before we write its data, and figuring that out can be
// awkward.  This class acts like an output stream, but it stores all
// output into a byte array, and when closed, it writes out the size of
// the stored data followed by the data itself.
class LengthFirstOS implements DataOutput {
	DataOutput realOut;
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	DataOutputStream dos = new DataOutputStream(baos);

	public LengthFirstOS(DataOutput realOut) {
		this.realOut = realOut;
	}
	public void write(byte  b[]) throws IOException {
		dos.write(b);
	}
	public void write(byte  b[], int  off, int  len) throws IOException {
		dos.write(b, off, len);
	}
	public void write(int  b) throws IOException {
		dos.write(b);
	}
	public void writeBoolean(boolean  v) throws IOException {
		dos.writeBoolean(v);
	}
	public void writeByte(int  v) throws IOException {
		dos.writeByte(v);
	}
	public void writeBytes(String  s) throws IOException {
		dos.writeBytes(s);
	}
	public void writeChar(int  v) throws IOException {
		dos.writeChar(v);
	}
	public void writeChars(String  s) throws IOException {
		dos.writeChars(s);
	}
	public void writeDouble(double  v) throws IOException {
		dos.writeDouble(v);
	}
	public void writeFloat(float  v) throws IOException {
		dos.writeFloat(v);
	}
	public void writeInt(int  v) throws IOException {
		dos.writeInt(v);
	}
	public void writeLong(long  v) throws IOException {
		dos.writeLong(v);
	}
	public void writeShort(int  v) throws IOException {
		dos.writeShort(v);
	}
	public void writeUTF(String  str) throws IOException {
		dos.writeUTF(str);
	}
	public void close() throws IOException {
		dos.flush(); dos.close();
		baos.flush(); baos.close();
		byte[] data = baos.toByteArray();
		realOut.writeInt((short)data.length);
		realOut.write(data);
	}
}
