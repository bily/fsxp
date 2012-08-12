package sli.kim.classfile;

import java.io.*;

/**
* Thrown if a ClassFileWriter encounters a problem.
*
* @see ClassFileWriter
*/
public class ClassFileWriteException extends IOException {
	public ClassFileWriteException(String msg) {
		super(msg);
	}
}