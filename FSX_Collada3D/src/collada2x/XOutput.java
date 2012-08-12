// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XOutput.java

package collada2x;

import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.Locale;
import org.lc0277lib.GUID;
import org.lc0277lib.geom.Matrix4;

// Referenced classes of package collada2x:
//            XFrame

public class XOutput
{

    public XOutput(PrintStream ps, String name)
    {
        prefix = "";
        nf = new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.ENGLISH));
        this.ps = ps;
        modelGUID = GUID.randomGUID();
        modelName = name;
        if(modelName == null)
            modelName = "Noname";
    }

    public void setModelGUID(GUID modelGUID)
    {
        if(modelGUID != null)
            this.modelGUID = modelGUID;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public void writeXData(XFrame rootFrame)
        throws IOException
    {
        writeXDataHeader();
        writeGUID();
        rootFrame.writeXOutput(this);
    }

    private void writeXDataHeader()
        throws IOException
    {
        ps.println("xof 0302txt 0032");
        ps.println();
        ps.println((new StringBuilder()).append("// .x file generated at ").append((new Date()).toString()).toString());
        ps.println();
        InputStream is = getClass().getResourceAsStream("xtemplates");
        if(is != null)
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = br.readLine()) != null) 
                ps.println(line);
            is.close();
        }
        ps.println();
        writeHeader();
    }

    private void writeHeader()
    {
        beginSection("Header");
        printData(1);
        printData(0);
        printData(1);
        endSection();
    }

    private void writeGUID()
    {
        beginSection("GuidToName");
        printData(modelGUID.toRegistryString().toLowerCase());
        printData(modelName);
        endSection();
    }

    void beginSection(String section)
    {
        ps.print(prefix);
        ps.print(section);
        ps.println(" {");

	prefix += "\t";// this line is by deduction
	/*
this block is erroroneous output by jad
        new StringBuilder();
        this;
        JVM INSTR dup_x1 ;
        prefix;
        append();
        "\t";
        append();
        toString();
        prefix;*/
    }

    void beginSection(String section, String name)
    {
        ps.print(prefix);
        ps.print(section);
        ps.print(" ");
        ps.print(name);
        ps.println(" {");
prefix += "\t"; // this line is by deduction
/*
this block is erroroneous output by jad
        new StringBuilder();
        this;
        JVM INSTR dup_x1 ;
        prefix;
        append();
        "\t";
        append();
        toString();
        prefix;*/
    }

    void beginFrame(String frameName)
    {
        beginSection("XFrame", frameName);
    }

    void endSection()
    {
        prefix = prefix.substring(0, prefix.length() - 1);
        ps.print(prefix);
        ps.println("}");
        ps.println();
    }

    void endSection(String comment)
    {
        prefix = prefix.substring(0, prefix.length() - 1);
        ps.print(prefix);
        ps.print("}\t\t//");
        ps.println(comment);
        ps.println();
    }

    void printData(String s)
    {
        ps.print(prefix);
        ps.print('"');
        ps.print(s);
        ps.print('"');
        ps.println(";");
    }

    void printData(double db[])
    {
        ps.print(prefix);
        for(int i = 0; i < db.length; i++)
        {
            if(i > 0)
                ps.print(" ");
            ps.print(db[i]);
            ps.print(";");
        }

        ps.println(";");
    }

    void printData(double db[], int limit)
    {
        ps.print(prefix);
        for(int i = 0; i < limit; i++)
        {
            if(i > 0)
                ps.print(" ");
            ps.print(db[i]);
            ps.print(";");
        }

        ps.println(";");
    }

    void printData(boolean b)
    {
        printData(b ? 1 : 0);
    }

    void printMatrix(double d00, double d01, double d02, double d03, double d10, double d11, double d12, 
            double d13, double d20, double d21, double d22, double d23, double d30, double d31, 
            double d32, double d33)
    {
        beginSection("FrameTransformMatrix");
        ps.print(prefix);
        ps.print(d00);
        ps.print(", ");
        ps.print(d01);
        ps.print(", ");
        ps.print(d02);
        ps.print(", ");
        ps.print(d03);
        ps.println(", ");
        ps.print(prefix);
        ps.print(d10);
        ps.print(", ");
        ps.print(d11);
        ps.print(", ");
        ps.print(d12);
        ps.print(", ");
        ps.print(d13);
        ps.println(", ");
        ps.print(prefix);
        ps.print(d20);
        ps.print(", ");
        ps.print(d21);
        ps.print(", ");
        ps.print(d22);
        ps.print(", ");
        ps.print(d23);
        ps.println(", ");
        ps.print(prefix);
        ps.print(d30);
        ps.print(", ");
        ps.print(d31);
        ps.print(", ");
        ps.print(d32);
        ps.print(", ");
        ps.print(d33);
        ps.println(";;");
        endSection();
    }

    void printMatrix(Matrix4 mat)
    {
        beginSection("FrameTransformMatrix");
        int i = 0;
        ps.print(prefix);
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.println(", ");
        ps.print(prefix);
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.println(", ");
        ps.print(prefix);
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.println(", ");
        ps.print(prefix);
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.print(", ");
        ps.print(mat.data[i++]);
        ps.println(";;");
        endSection();
    }

    void printData(double d)
    {
        ps.print(prefix);
        ps.print(nf.format(d));
        ps.println(";");
    }

    void printLine(String s)
    {
        ps.print(prefix);
        ps.print(s);
        ps.println();
    }

    void printData(int i)
    {
        ps.print(prefix);
        ps.print(i);
        ps.println(";");
    }

    public void println()
    {
        ps.println();
    }

    void printSection(String sec, String text)
    {
        beginSection(sec);
        printData(text);
        endSection();
    }

    void printSection(String sec, boolean b)
    {
        beginSection(sec);
        printData(b);
        endSection();
    }

    void printSection(String sec, double d)
    {
        beginSection(sec);
        printData(d);
        endSection();
    }

    private PrintStream ps;
    private String modelName;
    private GUID modelGUID;
    private String prefix;
    private NumberFormat nf;
}
