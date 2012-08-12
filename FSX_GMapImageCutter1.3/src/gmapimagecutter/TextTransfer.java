// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextTransfer.java

package gmapimagecutter;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.io.PrintStream;

public final class TextTransfer
    implements ClipboardOwner
{

    public TextTransfer()
    {
    }

    public void lostOwnership(Clipboard clipboard, Transferable transferable)
    {
    }

    public void setClipboardContents(String text)
    {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, this);
    }

    public String getClipboardContents()
    {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if(hasTransferableText)
            try
            {
                result = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch(UnsupportedFlavorException ex)
            {
                System.out.println(ex);
                ex.printStackTrace();
            }
            catch(IOException ex)
            {
                System.out.println(ex);
                ex.printStackTrace();
            }
        return result;
    }
}
