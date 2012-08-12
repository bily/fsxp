package sli.kim.classfile;

import java.util.Vector;

/**
* A class for storing information about the code of a method.
*
* @see sli.kim.classfile.MethodInfo#setCodeInfo()
*/
public class CodeInfo {
	private short maxStack, maxLocals;
	private byte[] bytecode;
	private ExceptionInfo[] exceptionTable;
	private LineNumberInfo[] lineNumberTable;
	private LocalVariableInfo[] localVariableTable;
	private Vector attributes = new Vector();

	public CodeInfo(short maxStack, short maxLocals, byte[] bytecode, 
		ExceptionInfo[] exceptionTable) 
	{
		setMaxStack(maxStack);
		setMaxLocals(maxLocals);
		setBytecode(bytecode);
		setExceptionTable(exceptionTable);
	}

	public void setMaxStack(short maxStack) {
		this.maxStack = maxStack;
	}

	public short getMaxStack() {
		return maxStack;
	}

	public void setMaxLocals(short maxLocals) {
		this.maxLocals = maxLocals;
	}

	public short getMaxLocals() {
		return maxLocals;
	}

	public void setBytecode(byte[] bytecode) {
		this.bytecode = bytecode;
	}

	public byte[] getBytecode() {
		return bytecode;
	}

	public void setExceptionTable(ExceptionInfo[] exceptionTable) {
		this.exceptionTable = exceptionTable;
	}

	public ExceptionInfo[] getExceptionTable() {
		return exceptionTable;
	}

	public void setLineNumberTable(LineNumberInfo[] lineNumberTable) {
		this.lineNumberTable = lineNumberTable;
	}

	public LineNumberInfo[] getLineNumberTable() {
		return lineNumberTable;
	}

	public void setLocalVariableTable(LocalVariableInfo[] localVariableTable) {
		this.localVariableTable = localVariableTable;
	}

	public LocalVariableInfo[] getLocalVariableTable() {
		return localVariableTable;
	}

	public void addAttribute(AttributeInfo attributeInfo) {
		attributes.addElement(attributeInfo);
	}

	public AttributeInfo[] getAttributes() {
		AttributeInfo[] list = new AttributeInfo[attributes.size()];
		attributes.copyInto(list);
		return list;
	}
}