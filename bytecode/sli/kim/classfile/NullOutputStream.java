
package sli.kim.classfile;

import java.io.*;

/**
* This output stream is a black hole.  It's used by ClassFileWriter
* when it needs to do a dry run.
*/
public class NullOutputStream extends OutputStream implements DataOutput {
	public void close() {}
	public void flush() {}
	public void write(byte b[]) {}
	public void write(byte b[], int off, int len) {}
	public void write(int b) {}
    public void writeBoolean(boolean  v) {}
    public void writeByte(int  v) {}
    public void writeBytes(String  s) {}
    public void writeChar(int  v) {}
    public void writeChars(String  s) {}
    public void writeDouble(double  v) {}
    public void writeFloat(float  v) {}
    public void writeInt(int  v) {}
    public void writeLong(long  v) {}
    public void writeShort(int  v) {}
	public void writeUTF(String str) {}
}
