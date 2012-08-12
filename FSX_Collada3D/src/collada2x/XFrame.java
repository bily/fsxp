// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XFrame.java

package collada2x;

import java.util.*;
import org.lc0277lib.geom.Matrix4;

// Referenced classes of package collada2x:
//            XOutput, XMesh

public class XFrame
{

    public XFrame(String name)
    {
        matrix = new Matrix4();
        subFrames = new ArrayList();
        this.name = name;
    }

    public XFrame()
    {
        this((new StringBuilder()).append("untitled-").append(count++).toString());
    }

    public void writeXOutput(XOutput xo)
    {
        xo.beginSection("Frame", (new StringBuilder()).append("frm-").append(name).toString());
        xo.printMatrix(matrix);
        if(mesh != null)
            mesh.writeXOutput(xo);
        XFrame subFrame;
        for(Iterator i$ = subFrames.iterator(); i$.hasNext(); subFrame.writeXOutput(xo))
            subFrame = (XFrame)i$.next();

        xo.endSection((new StringBuilder()).append("End of frame ").append(name).toString());
    }

    public Matrix4 getMatrix()
    {
        return matrix;
    }

    public void setMatrix(Matrix4 matrix)
    {
        this.matrix = matrix;
    }

    public XMesh getMesh()
    {
        return mesh;
    }

    public void setMesh(XMesh mesh)
    {
        this.mesh = mesh;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getSubFrames()
    {
        return subFrames;
    }

    private static int count = 0;
    private String name;
    private Matrix4 matrix;
    private XMesh mesh;
    private List subFrames;

}
