package sli.kim.classfile;

import java.io.PrintStream;

/**
* All classfile debugging information gets piped through this class.  
* You can turn off categories of debug information by setting the 
* appropriate String to null.
*/
public class Debug {
	public static String readClass = "read class: ";
	public static String readConstPool = "read CP: ";
	public static String readField = "read field: ";
	public static String readMethod = "read method: ";
	public static String readCode = "read code: ";
	public static String readInterface = "read interface: ";
	public static String readLineNumbers = "read line numbers: ";
	public static String readLocalVariables = "read local variables: ";
	public static String readInnerClasses = "read inner classes: ";
	public static String readUnknownAttribute = "read unknown attribute: ";
	public static String readBadData = "read bad data: ";

	public static String writeClass = "write class: ";
	public static String writeConstPool = "write CP: ";
	public static String writeField = "write field: ";
	public static String writeMethod = "write method: ";
	public static String writeCode = "write code: ";
	public static String writeInterface = "write interface: ";
	public static String writeLineNumbers = "write line numbers: ";
	public static String writeLocalVariables = "write local variables: ";
	public static String writeInnerClasses = "write inner classes: ";
	public static String writeUnknownAttribute = "write unknown attribute: ";

	/**
	* Indent subsequent messages.  Subsequent messages will be indented by 
	* two spaces until outdent() is called.  All calls to indent() should eventually
	* be followed by a call to outdent().
	*/
	public static void indent() {
		indentLevel++;
	}
	/**
	* Outdent subsequent messages.  This should only be called after a previous
	* call to indent().
	*/
	public static void outdent() {
		indentLevel--;
	}

	/**
	* Enable or disable printing of any debug message.
	*/
	public static void setEnabled(boolean e) {
		enabled = e;
	}
	/**
	* Get whether printing of debug messages is enabled.
	*/
	public static boolean isEnabled() {
		return enabled;
	}

	/**
	* Redirect where debug messages are sent.
	*/
	public static void setOutput(PrintStream out) {
		msgs = out;
	}
	/**
	* Get the destination of debug messages.
	*/
	public static PrintStream getOutput() {
		return msgs;
	}

	/**
	* Print a debug message.  If the prefix is null, the message is not printed.
	* The message will be prefixed by blank space, the length of which is
	* determined by the current level of indentation.
	*/
	public static void println(String prefix, String msg) {
		if (enabled && prefix != null) {
			for (int i = 0; i < indentLevel; i++)
				msgs.print("  ");
			msgs.println(prefix + msg);
		}
	}

	private static PrintStream msgs = System.out;
	private static boolean enabled = true;
	private static int indentLevel = 0;
}