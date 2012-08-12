package sli.kim.classfile;

import java.io.*;

/**
* Thrown if ClassFileReader encounters a corrupt class file.
* 
* @see ClassFileReader
*/
public class ClassFileParseException extends IOException {
	public ClassFileParseException(String msg) {
		super(msg);
	}
}