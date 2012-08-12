package sli.kim.classfile;

import java.io.*;

/**
* A class for storing information about line numbers.
* This class represents one row in the LineNumberTable.
*
* @see sli.kim.classfile.CodeInfo#setLineNumberTable()
*/
public class LineNumberInfo {
	public short startPC, lineNumber;

	public LineNumberInfo(short startPC, short lineNumber) {
		this.startPC = startPC;
		this.lineNumber = lineNumber;
	}
}
