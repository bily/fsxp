// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMaterial.java

package collada2x;


// Referenced classes of package collada2x:
//            XOutput, FS10Material

public class XMaterial
{

    public XMaterial(String name)
    {
        this.name = "";
        faceColor = new double[4];
        power = 0.0D;
        specularColor = new double[3];
        emissiveColor = new double[3];
        textureFileName = "";
        diffuseTextureName = "";
        this.name = name;
    }

    public XMaterial()
    {
        this((new StringBuilder()).append("material-").append(count++).toString());
    }

    public void writeXOutput(XOutput xo)
    {
        xo.beginSection("Material", name);
        xo.printData(faceColor, 4);
        xo.printData(power);
        xo.printData(specularColor, 3);
        xo.printData(emissiveColor, 3);
        if(textureFileName != null && !"".equals(textureFileName))
            xo.printSection("TextureFileName", textureFileName);
        if(diffuseTextureName != null && !"".equals(diffuseTextureName))
            xo.printSection("DiffuseTextureFileName", diffuseTextureName);
        if(fs10Material != null)
            fs10Material.writeXOutput(xo);
        xo.endSection();
    }

    public String getDiffuseTextureName()
    {
        return diffuseTextureName;
    }

    public void setDiffuseTextureName(String diffuseTextureName)
    {
        this.diffuseTextureName = diffuseTextureName;
    }

    public double[] getEmissiveColor()
    {
        return emissiveColor;
    }

    public void setEmissiveColor(double emissiveColor[])
    {
        this.emissiveColor = emissiveColor;
    }

    public void setEmissiveColor(double emissiveColor_r, double emissiveColor_g, double emissiveColor_b)
    {
        emissiveColor[0] = emissiveColor_r;
        emissiveColor[1] = emissiveColor_g;
        emissiveColor[2] = emissiveColor_b;
    }

    public double[] getFaceColor()
    {
        return faceColor;
    }

    public void setFaceColor(double faceColor[])
    {
        this.faceColor = faceColor;
    }

    public void setFaceColor(double faceColor_r, double faceColor_g, double faceColor_b, double faceColor_a)
    {
        faceColor[0] = faceColor_r;
        faceColor[1] = faceColor_g;
        faceColor[2] = faceColor_b;
        faceColor[3] = faceColor_a;
    }

    public FS10Material getFs10Material()
    {
        return fs10Material;
    }

    public void setFs10Material(FS10Material fs10Material)
    {
        this.fs10Material = fs10Material;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPower()
    {
        return power;
    }

    public void setPower(double power)
    {
        this.power = power;
    }

    public double[] getSpecularColor()
    {
        return specularColor;
    }

    public void setSpecularColor(double specularColor[])
    {
        this.specularColor = specularColor;
    }

    public void setSpecularColor(double specularColor_r, double specularColor_g, double specularColor_b)
    {
        specularColor[0] = specularColor_r;
        specularColor[1] = specularColor_g;
        specularColor[2] = specularColor_b;
    }

    public String getTextureFileName()
    {
        return textureFileName;
    }

    public void setTextureFileName(String textureFileName)
    {
        this.textureFileName = textureFileName;
    }

    private static int count = 0;
    private String name;
    private double faceColor[];
    private double power;
    private double specularColor[];
    private double emissiveColor[];
    private String textureFileName;
    private String diffuseTextureName;
    private FS10Material fs10Material;

}
