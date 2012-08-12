
package sli.kim.classfile;

/**
* Bitfield constants for class/field/method access flags.
*/
public interface AccessFlags {
	public static final short PUBLIC = 0x0001;
	public static final short PRIVATE = 0x0002;
	public static final short PROTECTED = 0x0004;
	public static final short STATIC = 0x0008;
	public static final short FINAL = 0x0010;
	public static final short SYNCHRONIZED = 0x0020;
	public static final short VOLATILE = 0x0040;
	public static final short TRANSIENT = 0x0080;
	public static final short NATIVE = 0x0100;
	public static final short INTERFACE = 0x0200;
	public static final short ABSTRACT = 0x0400;
}