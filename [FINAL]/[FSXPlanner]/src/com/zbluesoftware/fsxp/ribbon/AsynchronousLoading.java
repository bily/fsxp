// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   AsynchronousLoading.java

package com.zbluesoftware.fsxp.ribbon;


// Referenced classes of package com.zbluesoftware.fsxp.ribbon:
//            AsynchronousLoadListener

public interface AsynchronousLoading
{

    public abstract void addAsynchronousLoadListener(AsynchronousLoadListener asynchronousloadlistener);

    public abstract void removeAsynchronousLoadListener(AsynchronousLoadListener asynchronousloadlistener);
}