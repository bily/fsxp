package sli.kim.classfile;

/**
* A class for storing information about fields.
*
* @see ClassInfo#addField()
*/
public class FieldInfo extends CommonInfo {
	private String signature;
	private Object constValue;
	private boolean synthetic;

	public FieldInfo(short accessFlags, String name, String signature) {
		setAccessFlags(accessFlags);
		setName(name);
		setSignature(signature);
	}

	/**
	* Set the signature of the field.  For example "[Ljava/lang/String;"
	* 
	* @see Signature
	*/
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	* Get the signature of the field.
	* 
	* @see Signature
	*/
	public String getSignature() {
		return signature;
	}

	/**
	* Set the constant value of this field.  This only applies to final fields.
	* The value must be a String, Integer, Long, Float, or Double.
	*/
	public void setConstantValue(Object constValue) {
		this.constValue = constValue;
	}

	/**
	* Get the constant value of this field.  The result must be a String,
	* Integer, Long, Float, or Double.
	*/
	public Object getConstantValue() {
		return constValue;
	}

	public void setSynthetic(boolean synthetic) {
		this.synthetic = synthetic;
	}

	public boolean isSynthetic() {
		return synthetic;
	}
}
