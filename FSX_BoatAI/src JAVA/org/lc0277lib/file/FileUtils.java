// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileUtils.java

package org.lc0277lib.file;

import java.io.*;

public class FileUtils
{

    public FileUtils()
    {
    }

    public static boolean copyFile(File src, File dst)
    {
        boolean res;
        FileInputStream sourceFile;
        FileOutputStream destinationFile;
        res = false;
        sourceFile = null;
        destinationFile = null;
        try
        {
            dst.createNewFile();
            sourceFile = new FileInputStream(src);
            destinationFile = new FileOutputStream(dst);
            byte buffer[] = new byte[0x80000];
            int nbLecture;
            while((nbLecture = sourceFile.read(buffer)) != -1) 
                destinationFile.write(buffer, 0, nbLecture);
            res = true;
        }
        catch(FileNotFoundException filenotfoundexception)
        {}
        catch(IOException ioexception)
        {}
		finally {
			try
			{
				sourceFile.close();
				destinationFile.close();
			}
        catch(Exception exception1) { }
		}
        return res;
    }

    public static boolean copyDir(File srcDir, File toDir)
    {
        if(!toDir.exists() && !toDir.mkdirs())
            return false;
        boolean res = true;
        File afile[] = srcDir.listFiles();
        int i = 0;
        for(int j = afile.length; i < j; i++)
        {
            File f = afile[i];
            if(f.isDirectory())
                res &= copyDir(f, new File(toDir, f.getName()));
            else
                res &= copyFile(f, new File(toDir, f.getName()));
        }

        return res;
    }

    public static boolean delDir(File dir)
    {
        if(!dir.isDirectory())
            return dir.delete();
        boolean res = true;
        File afile[] = dir.listFiles();
        int i = 0;
        for(int j = afile.length; i < j; i++)
        {
            File f = afile[i];
            res &= delDir(f);
        }

        dir.delete();
        return res;
    }
}
