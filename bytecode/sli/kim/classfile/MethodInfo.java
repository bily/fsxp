package sli.kim.classfile;

import java.util.Vector;

/**
* A class for storing information about methods.
*
* @see sli.kim.classfile.ClassInfo#addMethod()
*/
public class MethodInfo extends CommonInfo {
	private String signature;
	private Vector exceptions = new Vector();
	private CodeInfo codeInfo;
	private boolean deprecated;

	public MethodInfo(short accessFlags, String name, String signature) {
		setAccessFlags(accessFlags);
		setName(name);
		setSignature(signature);
	}

	/**
	* Set the signature of the method.  For example "([I,S)Ljava/lang/String;"
	*
	* @see Signature
	*/
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	* Get the signature of the method. 
	*
	* @see Signature
	*/
	public String getSignature() {
		return signature;
	}

	/**
	* Add a checked exception that the method throws.
	*/
	public void addException(String exceptionName) {
		exceptions.addElement(exceptionName);
	}

	/**
	* Get all checked exceptions that the method throws.
	*/
	public String[] getExceptions() {
		String[] list = new String[exceptions.size()];
		exceptions.copyInto(list);
		return list;
	}

	/**
	* Set information about this method's code.  The CodeInfo need
	* not be entirely filled in before this method is called.
	*/
	public void setCodeInfo(CodeInfo codeInfo) {
		this.codeInfo = codeInfo;
	}

	/**
	* Get the information about this method's code.  If the method
	* has no code, this will return null.
	*/
	public CodeInfo getCodeInfo() {
		return codeInfo;
	}

	/**
	* Set whether this method is deprecated.  Methods are not
	* deprecated until setDeprecated(true) is called.
	*/
	public void setDeprecated(boolean d) {
		deprecated = d;
	}

	/**
	* Get whether this method is deprecated.  Methods are not
	* deprecated until setDeprecated(true) is called.
	*/
	public boolean isDeprecated() {
		return deprecated;
	}
}