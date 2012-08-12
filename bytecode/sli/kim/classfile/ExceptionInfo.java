package sli.kim.classfile;

import java.io.*;

/**
* A class for storing information about the exception table of a method.
* This class represents one row in the ExceptionTable.
*
* @see sli.kim.classfile.CodeInfo#setExceptionTable()
*/
public class ExceptionInfo {
	public short startPC, endPC, handlerPC;
	public String catchType;

	/**
	* Construct an ExceptionInfo
	*
	* @param startPC the index of the first instruction in the try block (that is,
	*    startPC is inclusive).
	* @param endPC the index of the first instruction not in the try block (that is,
	*    endPC is exclusive).  If the try block extends to the end of the method, 
	*    endPC will be equal to the length of the bytecode for the method.
	* @param handlerPC the index of the first instruction of the catch handler.
	* @param catchType the name of the exception class caught by the catch 
	*    handler.  If this parameter is null, the handler catches all exceptions (this
	*    is used for finally blocks).
	*/
	public ExceptionInfo(short startPC, short endPC, short handlerPC, String catchType) 
	{
		this.startPC = startPC;
		this.endPC = endPC;
		this.handlerPC = handlerPC;
		this.catchType = catchType;
	}
}