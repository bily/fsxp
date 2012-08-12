// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ResizableIcon.java

package com.zbluesoftware.fsxp.ribbon;

import java.awt.Dimension;
import javax.swing.Icon;

public interface ResizableIcon
    extends Icon
{

    public abstract void setDimension(Dimension dimension);

    public abstract void setHeight(int i);

    public abstract void setWidth(int i);

    public abstract void revertToOriginalDimension();

    public abstract void setDrawX(boolean flag);

    public abstract boolean getDrawX();
}