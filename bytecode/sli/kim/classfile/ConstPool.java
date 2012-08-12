package sli.kim.classfile;

import java.util.*;
import java.io.*;

/**
* This class encapsulates constant pool management.  Classfiles use
* constant pools to store information such as class names, method 
* names, type signatures, and literals like "Hello World" or 123. When 
* the information is needed (like for a method declaration), an index 
* into the constant pool is given instead of the actual information.  
* This can reduce the size of a classfile dramatically if the same 
* information is used frequently.
*
* <p>ClassFileReaders and ClassFileWriters usually try to convert
* constant pool indexes into meaningful data (eg ClassInfo.setName() 
* takes a String instead of a constant pool index).  So you probably 
* won't need to use this class unless you need to work with attributes 
* that the ClassFileReader/Writer doesn't understand, such as 
* bytecodes.
*
* <p>The constant pool is a 0-based array of entries.  The entry at
* index 0 is never used.  That is,
* 
* <pre>constPool.getEntryAtIndex(0).isUnused() == true;</pre>
* 
* Another quirk of the constant pool is that LONG and DOUBLE
* entries take up "two" indexes.  That is,
*
* <pre>short longIndex = constPool.addEntry(new ConstPoolEntry().setLong(0));
* constPool.getEntryAtIndex(longIndex+1).isUnused() == true;</pre>
*
* @see ConstPoolEntry
* @see Signature
*/
public class ConstPool {
	private Vector cp = new Vector();
	// for efficiency, addEntry() caches elements in a hashtable, which is
	// used by getEntryAtIndex().  Caching reduces the time required to 
	// read ClassFileWriter.class from ~10s to ~7.5s.
	private Hashtable cpHash = new Hashtable();

	/**
	* Get the size of the pool.
	*/
	public int size() {
		return cp.size();
	}

	/**
	* Add a constant pool entry, whether or not it's already in the pool.
	* If the entry is a LONG or DOUBLE, a second, unused entry is 
	* also added.
	* @returns the index of the added entry.
	*/
	public short addEntry(ConstPoolEntry entry) {
		cp.addElement(entry);
		short result = (short)(size() - 1);
		cpHash.put(entry, new Integer(result));
		// LONG and DOUBLE entries take up two entries
		if (entry.typecode() == ConstPoolEntry.LONG 
			|| entry.typecode() == ConstPoolEntry.DOUBLE) 
			cp.addElement(new ConstPoolEntry().setUnused());
		return result;
	}

	/**
	* Get the const pool entry at a given index.
	*/
	public ConstPoolEntry getEntryAtIndex(short index) {
		return (ConstPoolEntry)cp.elementAt(index);
	}

	/**
	* Return the index of a constant pool entry equal to the specified one.  
	* If there is no matching entry in the pool yet, -1 is returned.  Comparisons 
	* are by value, not by reference.
	*/
	public short getIndexOfEntryNoAdd(ConstPoolEntry entry) {
		Integer i = (Integer)cpHash.get(entry);
		if (i != null)
			return (short)i.intValue();
		return (short)-1;
	}

	/**
	* Return the index of a constant pool entry equal to the specified one.  
	* If there is no matching entry in the pool yet, a clone of the specified 
	* entry is added.  Comparisons are made by value, not by reference.
	*/
	public short getIndexOfEntryAdd(ConstPoolEntry entry) {
		short result = getIndexOfEntryNoAdd(entry);
		if (result < 0)
			result = addEntry((ConstPoolEntry)entry.clone());
		return result;
	}

	// used for doing searches -- reuse so we don't have to allocate a new
	// one every time.
	private ConstPoolEntry searchEntry = new ConstPoolEntry();

	/**
	* Convenience method to return the index of the UTF const pool entry 
	* equal to the given string.  If there is no matching entry in the const pool yet, 
	* a new one is added.  Without this method, something like the following code 
	* would be used:
	* <pre>constPool.getIndexOfEntryAdd(new ConstPoolEntry().setUTF(str));</pre>
	* This method avoids the overhead of allocating a ConstPoolEntry every time.
	*/
	public short getIndexOfUTFAdd(String str) {
		return getIndexOfEntryAdd(searchEntry.setUTF(str));
	}

	/**
	* Convenience method to return the index of the CLASS const pool entry 
	* whose classname is equal to the given string.  If there is no matching entry in 
	* the const pool yet, a new one is added.  Without this method, something like 
	* the following code would be used:
	* <pre>short nameIndex = constPool.getIndexOfUTFAdd(classname);
	* constPool.getIndexEntryAdd(new ConstPoolEntry().setClass(nameIndex));</pre>
	* This method avoids the overhead of allocating a ConstPoolEntry every time.
	*/
	public short getIndexOfClassAdd(String classname) {
		searchEntry.setClass(getIndexOfUTFAdd(classname));
		return getIndexOfEntryAdd(searchEntry);
	}

	/**
	* Read in the constant pool from a stream.
	* @throws ClassFileReadException if the class file is corrupt.
	* @throws IOException if the DataInput throws an IOException.
	*/
	public void read(DataInput in) throws IOException, ClassFileParseException {
		int count = in.readShort();

		if (Debug.readConstPool != null) Debug.println(Debug.readConstPool, 
			"# of entries = " + count);

		// entry 0 is unused
		addEntry(new ConstPoolEntry().setUnused());
		// note: start i at 1 because 0 is unused
		for (int i = 1; i < count; i++) {
			ConstPoolEntry entry = new ConstPoolEntry();
			entry.read(in, entry);

			if (Debug.readConstPool != null) Debug.println(Debug.readConstPool, 
				i + " " + entry.toString());

			addEntry(entry);
			// LONG and DOUBLE entries take up two slots
			if (ConstPoolEntry.LONG == entry.typecode() 
				|| ConstPoolEntry.DOUBLE == entry.typecode())
				i++;
		}
	}

	/**
	* Write out the constant pool to a stream.
	* @throws IOException if the DataInput stream throws an IOException.
	*/
	public void write(DataOutput out) throws IOException {
		int numEntries = size();

		if (Debug.writeConstPool != null) Debug.println(Debug.writeConstPool, 
			"# of entries = " + numEntries);

		out.writeShort(numEntries);
		for (short i = 1; i < numEntries; i++) {
			ConstPoolEntry entry = getEntryAtIndex(i);
			// blank entries after LONG and DOUBLE entries
			if (entry.isUnused()) {
				if (Debug.writeConstPool != null) Debug.println(Debug.writeConstPool,
					"skipping unused entry");
			}
			else {
				entry.write(out, entry);

				if (Debug.writeConstPool != null) Debug.println(Debug.writeConstPool,
					i + " " + entry.toString());
			}
		}
	}
}
