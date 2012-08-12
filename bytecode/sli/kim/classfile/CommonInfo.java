package sli.kim.classfile;

import java.util.Vector;

/**
* A common base class for ClassInfo, MethodInfo, and FieldInfo.
*
* @see ClassInfo
* @see MethodInfo
* @see FieldInfo
*/
public class CommonInfo {
	short accessFlags = 0;
	Vector attributes = new Vector();
	String name;

	/**
	* Set the name of this class/method/field.
	*/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Get the name of this class/method/field.
	*/
	public String getName() {
		return name;
	}

	/**
	* Set the access flags of the class.
	* 
	* @see AccessFlags
	*/
	public void setAccessFlags(short accessFlags) {
		this.accessFlags = accessFlags;
	}

	/**
	* Get the access flags of the class.
	* 
	* @see AccessFlags
	*/
	public short getAccessFlags() {
		return accessFlags;
	}

	/**
	* Add a <i>non-standard</i> attribute.  This does not include the Code 
	* attribute for methods, the SourceFile attribute for classes, or the 
	* ConstantValue attribute for fields.
	*/
	public void addAttribute(AttributeInfo attributeInfo) {
		attributes.addElement(attributeInfo);
	}

	/**
	* Get all <i>non-standard</i> attributes.  This does not include the Code 
	* attribute for methods, the SourceFile attribute for classes, or the 
	* ConstantValue attribute for fields.
	*/
	public AttributeInfo[] getAttributes() {
		AttributeInfo[] list = new AttributeInfo[attributes.size()];
		attributes.copyInto(list);
		return list;
	}
}