// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SystemLogsToTextArea.java

package org.lc0277lib.gui;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class SystemLogsToTextArea extends OutputStream
{

    public SystemLogsToTextArea(JTextArea area, String prefix)
    {
        this.area = area;
        this.prefix = prefix;
    }

    public void write(int b)
        throws IOException
    {
        StringBuilder sgb = (StringBuilder)thrLocal.get();
        if(sgb == null)
        {
            sgb = new StringBuilder("");
            thrLocal.set(sgb);
        }
        sgb.append((char)b);
        if(b == 10)
        {
            String msg = sgb.toString();
            synchronized(area)
            {
                Document doc = area.getDocument();
                try
                {
                    doc.insertString(doc.getLength(), prefix == null ? msg : (new StringBuilder(String.valueOf(prefix))).append(msg).toString(), null);
                }
                catch(BadLocationException e)
                {
                    e.printStackTrace();
                }
            }
            sgb.setLength(0);
        }
    }

    private final JTextArea area;
    private final ThreadLocal thrLocal = new ThreadLocal();
    private final String prefix;
}
