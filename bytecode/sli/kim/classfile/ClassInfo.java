package sli.kim.classfile;

import java.util.Vector;

/**
* A class for storing information about a class.  
* This information can be read in by a ClassFileReader, or written out by
* a ClassFileWriter.
*
* @see FieldInfo
* @see MethodInfo
* @see AttributeInfo
* @see ClassFileReader
* @see ClassFileWriter
*/
public class ClassInfo extends CommonInfo {
	private String superName = "java.lang.Object", sourceFile;
	private ConstPool cp;
	// Vector of Strings
	private Vector interfaces = new Vector();
	// Vector of FieldInfo
	private Vector fields = new Vector();
	// Vector of MethodInfo
	private Vector methods = new Vector();
	private InnerClassInfo[] innerClasses;

	/**
	* Set the constant pool.  The ClassInfo class itself does not actually use 
	* the ConstPool for anything. This method is useful if the ClassInfo is 
	* being read in by a ClassFileReader and there are attributes that the 
	* Reader does not understand, which reference entries in the constant 
	* pool.  The best example of such an attribute is method bytecodes.
	* In a future release, ClassFileReaders will understand bytecodes, 
	* and so this method will become less important.  
	*
	* <p>The new ConstPool is not immutable.  It may be passed to a 
	* ClassFileWriter, which will add entries as needed.
	*
	* @see ConstPool
	*/
	public void setConstPool(ConstPool cp) {
		this.cp = cp;
	}

	/**
	* Get the constant pool.  The ClassInfo class itself does not use the
	* constant pool for anthing. The constant pool is useful if the ClassInfo is 
	* being written out by a ClassFileWriter and there are attributes that the 
	* Writer does not understand, which reference entries in the constant 
	* pool.  The best example of such an attribute is method bytecodes.
	* The Writer will use the ConstPool returned by this method in preference
	* to creating its own ConstPool.  Letting the Writer create its own 
	* ConstPool is probably not a good idea, for the reasons describe above.
	* In a future release, ClassFileWriters will understand bytecodes, and so
	* this method will become less important.
	*
	* @see ConstPool
	*/
	public ConstPool getConstPool() {
		return cp;
	}

	/**
	* Set the name of the super class.  The name should be of the form "java.lang.String".
	*/
	public void setSuperClassName(String name) {
		superName = name;
	}

	/**
	* Get the name of the super class.  The name is of the form "java.lang.String".
	*/
	public String getSuperClassName() {
		return superName;
	}

	/**
	* Add an interface that the class implements.
	*/
	public void addInterface(String interfaceName) {
		interfaces.addElement(interfaceName);
	}

	/**
	* Get the interfaces that this class implements.
	*/
	public String[] getInterfaces() {
		String[] list = new String[interfaces.size()];
		interfaces.copyInto(list);
		return list;
	}

	/**
	* Add a field to this class.  The FieldInfo should be need not be
	* entirely filled out before being added.
	* 
	* @see FieldInfo
	*/
	public void addField(FieldInfo fieldInfo) {
		fields.addElement(fieldInfo);
	}

	/**
	* Get the fields of this class.
	*
	* @see FieldInfo
	*/
	public FieldInfo[] getFields() {
		FieldInfo[] list = new FieldInfo[fields.size()];
		fields.copyInto(list);
		return list;
	}

	/**
	* Add a method to this class.  The MethodInfo should be need not be
	* entirely filled out before being added.
	* 
	* @see MethodInfo
	*/
	public void addMethod(MethodInfo methodInfo) {
		methods.addElement(methodInfo);
	}

	/**
	* Get the methods of this class.
	*
	* @see MethodInfo
	*/
	public MethodInfo[] getMethods() {
		MethodInfo[] list = new MethodInfo[methods.size()];
		methods.copyInto(list);
		return list;
	}
	
	/**
	* Specify the source file for this class, if it is known.
	*/
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	/**
	* Get the source file for this class.  If the file is not known, this
	* may return null.
	*/
	public String getSourceFile() {
		return sourceFile;
	}

	/**
	* Set the inner class information of this class.  Using inner classes is 
	* a bit tricky. The array should include elements for every inner class 
	* of this class, and every class that contains this class. For example:
	*
	*<pre>class Foo {
	*    class Bar {
	*        class Quux {
	*            class Squish {
	*            }
	*        }
	*    }
	*    class Baz {
	*    }
	*}</pre>
	*
	* If this ClassInfo is for Foo.Bar, then the inner classes array should be 
	* {FooInfo, BarInfo, QuuxInfo}. The order is important: outer classes 
	* <strong>must</strong> come before their inner classes. Note that the 
	* current class must have an entry in the array. Squish and Baz may be
	* included in the array, but they're not necessary.
	*
	* @see InnerClassInfo
	*/
	public void setInnerClasses(InnerClassInfo[] innerClasses) {
		this.innerClasses = innerClasses;
	}

	/**
	* Get the inner class information of this class.
	*
	* @see #setInnerClasses()
	*/
	public InnerClassInfo[] getInnerClasses() {
		return innerClasses;
	}
}
