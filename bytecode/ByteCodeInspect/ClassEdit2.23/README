About Class Editor
------------------
Open a Java class file binary to view or edit strings, attributes, methods and 
generate readable reports similar to the javap utility. In built verifier 
checks changes before saving the file. Easy to use Java Swing GUI.

This is a tool to open Java class file binaries, view their internal structure,
modify portions of it if required and save the class file back. It also 
generates readable reports similar to the javap utility. Easy to use Java Swing 
GUI. The user interface tries to display as much detail as possible and tries 
to present a structure as close as the actual Java class file structure. At the
same time ease of use and class file consistency while doing modifications is 
also stressed. For example, when a method is deleted, the associated constant 
pool entry will also be deleted if it is no longer referenced. In built verifier
checks changes before saving the file. This tool has been used by people 
learning Java class file internals. This tool has also been used to do quick 
modifications in class files when the source code is not available.

 

Some broad level requirements that this tool is based on are:

    * Intuitive Graphical User Interface: The user interface should be clean 
      and intuitive. Screens should not be crowded. Components like tab panels 
      should be used wherever appropriate to reduce screen clutter.
    * Clear separation for the Novice and Power User: The features should be so
      arranged that the novice user can reach the required option easily, while
      all features are available for the power user deeper down the screen 
      hierarchy.
    * Cascading changes: The core engine should be able to detect cascading 
      changes if any and confirm all changes before proceeding.
    * Consistency check: Consistency check should be provided. Providing this 
      check at all stages might not be feasible. To handle such cases the user 
      should be given control to do this check optionally.
    * Facility to switch off consistency check: Why on earth would somebody 
      want this? Well, to test the Java Virtual Machine itself, to see how well
      it stands up to errors in the class file. The Java compiler would never 
      produce an invalid class file. This is probably the only way to do it.
    * Creating fresh classes: It should be possible to create classes out of 
      scratch using this tool. This will serve as a useful tool for learning 
      the Java Virtual Machine instructions.
    * Facility to examine compiler specific attributes: Some compilers put 
      attributes specific to their product to facilitate debugging and other 
      product specific features. For example, the compiler might embed an entry
      to the source file browser file or may be a line number attribute in the 
      class method descriptions. This utility should be able to detect, display
      and manipulate such features wherever possible.

Installation and Usage
----------------------
1. Uncompress and extract the downloaded file. It should create a directory 
   similar to ce2.23 with all the files inside.
2. To start classeditor go to the extracted directory and type 
   "java -jar ce.jar". 
3. When you are starting ClassEditor for the first time, it is important for 
   you to be located in the directory where it is installed. That is when few 
   configuration files are created automatically containing the install 
   location.
4. Classeditor creates its configuration files under the user's home directory 
   in a sub directory named ".ce".
5. The configuration file .ce_config contains path to a copy of the JavaClass
   XML schema. In case the schema location is not correct, either edit this 
   file or delete this file and go to step 2.
