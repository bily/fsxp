// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MaxLengthDocument.java

package com.zbluesoftware.fsxp.util;

import java.awt.Toolkit;
import javax.swing.text.*;

public class MaxLengthDocument extends PlainDocument
{

    public MaxLengthDocument(int maxLength)
    {
        this.maxLength = maxLength;
    }

    public void insertString(int offset, String str, AttributeSet a)
        throws BadLocationException
    {
        if(getLength() + str.length() > maxLength)
            Toolkit.getDefaultToolkit().beep();
        else
            super.insertString(offset, str, a);
    }

    private int maxLength;
}
