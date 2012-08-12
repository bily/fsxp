package sli.kim.classfile;

/**
* A class for storing information about inner classes.
* This class represents one row in the InnerClasses table.
*
* @see sli.kim.classfile.ClassInfo#setInnerClasses()
*/
public class InnerClassInfo {
	public String innerClass, outerClass, simpleName;
	public short flags;

	/**
	* Construct an inner class record.
	*
	* @param innerClass the full name of the inner class (eg "mypackage.Foo$Bar")
	* @param outerClass the full name of the outer class.  This is null for non-member
	*    classes (that is, classes that are declared static)
	* @param simpleName the simple name of the class (eg "String").  This is null for
	*    anonymous classes.
	* @param the access flags of the class.  When an inner class is compiled to a .class
	*    file, the compiler changes its access flags to ensure compatibility with pre-1.1
	*    virtual machines.  Specifically, protected becomes public, and private becomes 
	*    package.
	*/
	public InnerClassInfo(String innerClass, String outerClass, String simpleName, short flags) {
		this.innerClass = innerClass;
		this.outerClass = outerClass;
		this.simpleName = simpleName;
		this.flags = flags;
	}
}
