package sli.kim.classfile;

import java.io.*;

/**
* A simple test utility for the classfile package.
*
* <pre>Usage:
* 
* To read a classfile and write it back out:
*    sli.kim.classfile.Test read &lt;infile&gt; write &lt;outfile&gt;
*
* To create a simple classfile from scratch:
*    sli.kim.classfile.Test create &lt;outfile&gt;
* </pre>
*/
public class Test {
	public static void usage() {
		System.out.println("Usage:");
		System.out.println("    sli.kim.classfile.Test read <infile> write <outfile>");
	}

	public static void main(String[] args) 
		throws IOException, ClassFileParseException, ClassFileWriteException
	{
		if (args.length != 4 || !args[0].equals("read") || !args[2].equals("write")) {
			usage();
			return;
		}
		InputStream is = new FileInputStream(args[1]);
		ClassInfo classInfo = new ClassInfo();
		new ClassFileReader().read(is, classInfo);
		is.close();
		OutputStream os = new FileOutputStream(args[3]);
		new ClassFileWriter().write(classInfo, os);
		os.flush(); os.close();
	}
}