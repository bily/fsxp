// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirListModel.java

package flightsim.shiptraffic.btcgui;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;

public class DirListModel extends AbstractListModel
{

    public DirListModel(String dir, String exts[])
    {
        files = new LinkedList();
        this.exts = exts;
        setCurrentDir(dir);
    }

    public DirListModel(String dir, String ext)
    {
        files = new LinkedList();
        exts = (new String[] {
            ext
        });
        setCurrentDir(dir);
    }

    public Object getElementAt(int index)
    {
        if(files == null)
            return null;
        else
            return files.get(index);
    }

    public int getSize()
    {
        if(files == null)
            return 0;
        else
            return files.size();
    }

    public File getCurrentDir()
    {
        return current;
    }

    public void setCurrentDir(String s)
    {
        setCurrentDir(new File(s));
    }

    public void setCurrentDir(File f)
    {
        int oldsize = getSize();
        files.clear();
        fireIntervalRemoved(this, 0, oldsize);
        current = f;
        File flist[] = f.listFiles();
        if(flist == null)
            return;
        File afile[] = f.listFiles();
        int i = 0;
        for(int j = afile.length; i < j; i++)
        {
            File fs = afile[i];
            String fsName = fs.getName().toLowerCase();
            String as[] = exts;
            int k = 0;
            for(int l = as.length; k < l; k++)
            {
                String ext = as[k];
                if(fsName.endsWith((new StringBuilder(".")).append(ext).toString()))
                    files.add(fs);
            }

        }

        fireContentsChanged(this, 0, getSize());
    }

    public void resetCurrentDir()
    {
        setCurrentDir(getCurrentDir());
    }

    public File getFileAt(int lastIndex)
    {
        return (File)files.get(lastIndex);
    }

    public int getIndex(String fileName)
    {
        for(int i = 0; i < files.size(); i++)
        {
            File f = (File)files.get(i);
            if(f.getName().equalsIgnoreCase(fileName))
                return i;
        }

        return -1;
    }

    private File current;
    private List files;
    private String exts[];
}
