// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FS10Material.java

package collada2x;


// Referenced classes of package collada2x:
//            XOutput

public class FS10Material
{

    public FS10Material()
    {
        diffuseComponent = new double[4];
        specularComponent = new double[3];
        specularPower = 0.0D;
        scaleEnvironment = 0.20000000000000001D;
        globalEnvironment = false;
        blendEnvironmentDiffuseAlpha = false;
        blendEnvironmentSpecularAlpha = true;
        fresnelDiffuse = false;
        fresnelSpecular = false;
        fresnelEnvironment = false;
        precipitationUse = false;
        precipitationOffset = false;
        precipitationOffsetFactor = 0.0D;
        specularMapPowerScale = 64D;
        srcBlend = "One";
        dstBlend = "Zero";
        blendDiffuseByBaseAlpha = false;
        blendDiffuseByInverseSpecularMapAlpha = false;
        allowBloom = true;
        specularBloomFactor = 0.90000000000000002D;
        emissiveDataMode = "Blended";
        zTestAlpha = false;
        AlphaTestValue = 0.0D;
        alphaTestFunction = "Never";
        finalAlphaWrite = false;
        finalAlphaWriteValue = 255D;
        assumeVerticalNormal = false;
        zWriteAlpha = false;
        noZWrite = false;
        volumeShadow = false;
        noShadow = false;
        prelitVertices = false;
        allowSkinning = false;
        doubleSided = true;
        blendConstant = false;
        forceTextureAddressWrap = false;
        forceTextureAddressClamp = false;
        allowBaseMaterialSpecular = true;
        allowSpecularBloom = true;
        allowEmissiveBloom = false;
        ambientLightScale = 1.0D;
        diffuseTextureFileName = "";
    }

    public void writeXOutput(XOutput xo)
    {
        xo.beginSection("FS10Material");
        xo.printData(diffuseComponent, 4);
        xo.printData(specularComponent, 3);
        xo.printData(specularPower);
        xo.printData(detailBumpScales);
        xo.printData(scaleEnvironment);
        xo.printData(globalEnvironment);
        xo.printData(blendEnvironmentDiffuseAlpha);
        xo.printData(blendEnvironmentSpecularAlpha);
        xo.printData(fresnelDiffuse);
        xo.printData(fresnelSpecular);
        xo.printData(fresnelEnvironment);
        xo.printData(precipitationUse);
        xo.printData(precipitationOffset);
        xo.printData(precipitationOffsetFactor);
        xo.printData(specularMapPowerScale);
        xo.printData(srcBlend);
        xo.printData(dstBlend);
        xo.printSection("BlendDiffuseByBaseAlpha", blendDiffuseByBaseAlpha);
        xo.printSection("BlendDiffuseByInverseSpecularMapAlpha", blendDiffuseByInverseSpecularMapAlpha);
        xo.printSection("AllowBloom", allowBloom);
        xo.printSection("SpecularBloomFloor", specularBloomFactor);
        xo.printSection("EmissiveData", emissiveDataMode);
        xo.beginSection("AlphaData");
        xo.printData(zTestAlpha);
        xo.printData(AlphaTestValue);
        xo.printData(alphaTestFunction);
        xo.printData(finalAlphaWrite);
        xo.printData(finalAlphaWriteValue);
        xo.endSection();
        xo.beginSection("EnhandedParameters");
        xo.printData(assumeVerticalNormal);
        xo.printData(zWriteAlpha);
        xo.printData(noZWrite);
        xo.printData(volumeShadow);
        xo.printData(noShadow);
        xo.printData(prelitVertices);
        xo.endSection();
        xo.printSection("BaseMaterialSkin", allowSkinning);
        xo.printSection("DoubleSidedMaterial", doubleSided);
        xo.printSection("BlendConstantSetting", blendConstant);
        xo.printSection("ForceTextureAddressWrapSetting", forceTextureAddressWrap);
        xo.printSection("ForceTextureAddressClampSetting", forceTextureAddressClamp);
        xo.printSection("BaseMaterialSpecular", allowBaseMaterialSpecular);
        xo.printSection("NoSpecularBloom", allowSpecularBloom);
        xo.printSection("EmissiveBloom", allowEmissiveBloom);
        xo.printSection("AmbientLightScale", ambientLightScale);
        if(diffuseTextureFileName != null && !"".equals(diffuseTextureFileName))
            xo.printSection("DiffuseTextureFileName", diffuseTextureFileName);
        xo.endSection();
    }

    public boolean isAllowBaseMaterialSpecular()
    {
        return allowBaseMaterialSpecular;
    }

    public void setAllowBaseMaterialSpecular(boolean allowBaseMaterialSpecular)
    {
        this.allowBaseMaterialSpecular = allowBaseMaterialSpecular;
    }

    public boolean isAllowBloom()
    {
        return allowBloom;
    }

    public void setAllowBloom(boolean allowBloom)
    {
        this.allowBloom = allowBloom;
    }

    public boolean isAllowEmissiveBloom()
    {
        return allowEmissiveBloom;
    }

    public void setAllowEmissiveBloom(boolean allowEmissiveBloom)
    {
        this.allowEmissiveBloom = allowEmissiveBloom;
    }

    public boolean isAllowSkinning()
    {
        return allowSkinning;
    }

    public void setAllowSkinning(boolean allowSkinning)
    {
        this.allowSkinning = allowSkinning;
    }

    public boolean isAllowSpecularBloom()
    {
        return allowSpecularBloom;
    }

    public void setAllowSpecularBloom(boolean allowSpecularBloom)
    {
        this.allowSpecularBloom = allowSpecularBloom;
    }

    public String getAlphaTestFunction()
    {
        return alphaTestFunction;
    }

    public void setAlphaTestFunction(String alphaTestFunction)
    {
        this.alphaTestFunction = alphaTestFunction;
    }

    public double getAlphaTestValue()
    {
        return AlphaTestValue;
    }

    public void setAlphaTestValue(double alphaTestValue)
    {
        AlphaTestValue = alphaTestValue;
    }

    public double getAmbientLightScale()
    {
        return ambientLightScale;
    }

    public void setAmbientLightScale(double ambientLightScale)
    {
        this.ambientLightScale = ambientLightScale;
    }

    public boolean isAssumeVerticalNormal()
    {
        return assumeVerticalNormal;
    }

    public void setAssumeVerticalNormal(boolean assumeVerticalNormal)
    {
        this.assumeVerticalNormal = assumeVerticalNormal;
    }

    public boolean isBlendConstant()
    {
        return blendConstant;
    }

    public void setBlendConstant(boolean blendConstant)
    {
        this.blendConstant = blendConstant;
    }

    public boolean isBlendDiffuseByBaseAlpha()
    {
        return blendDiffuseByBaseAlpha;
    }

    public void setBlendDiffuseByBaseAlpha(boolean blendDiffuseByBaseAlpha)
    {
        this.blendDiffuseByBaseAlpha = blendDiffuseByBaseAlpha;
    }

    public boolean isBlendDiffuseByInverseSpecularMapAlpha()
    {
        return blendDiffuseByInverseSpecularMapAlpha;
    }

    public void setBlendDiffuseByInverseSpecularMapAlpha(boolean blendDiffuseByInverseSpecularMapAlpha)
    {
        this.blendDiffuseByInverseSpecularMapAlpha = blendDiffuseByInverseSpecularMapAlpha;
    }

    public boolean isBlendEnvironmentDiffuseAlpha()
    {
        return blendEnvironmentDiffuseAlpha;
    }

    public void setBlendEnvironmentDiffuseAlpha(boolean blendEnvironmentDiffuseAlpha)
    {
        this.blendEnvironmentDiffuseAlpha = blendEnvironmentDiffuseAlpha;
    }

    public boolean isBlendEnvironmentSpecularAlpha()
    {
        return blendEnvironmentSpecularAlpha;
    }

    public void setBlendEnvironmentSpecularAlpha(boolean blendEnvironmentSpecularAlpha)
    {
        this.blendEnvironmentSpecularAlpha = blendEnvironmentSpecularAlpha;
    }

    public double[] getDetailBumpScales()
    {
        return detailBumpScales;
    }

    public void setDetailBumpScales(double detailBumpScales[])
    {
        this.detailBumpScales = detailBumpScales;
    }

    public double[] getDiffuseComponent()
    {
        return diffuseComponent;
    }

    public void setDiffuseComponent(double diffuseComponent[])
    {
        this.diffuseComponent = diffuseComponent;
    }

    public void setDiffuseComponent(double diffuseComponent_r, double diffuseComponent_g, double diffuseComponent_b, double diffuseComponent_a)
    {
        diffuseComponent[0] = diffuseComponent_r;
        diffuseComponent[1] = diffuseComponent_g;
        diffuseComponent[2] = diffuseComponent_b;
        diffuseComponent[3] = diffuseComponent_a;
    }

    public String getDiffuseTextureFileName()
    {
        return diffuseTextureFileName;
    }

    public void setDiffuseTextureFileName(String diffuseTextureFileName)
    {
        this.diffuseTextureFileName = diffuseTextureFileName;
    }

    public boolean isDoubleSided()
    {
        return doubleSided;
    }

    public void setDoubleSided(boolean doubleSided)
    {
        this.doubleSided = doubleSided;
    }

    public String getDstBlend()
    {
        return dstBlend;
    }

    public void setDstBlend(String dstBlend)
    {
        this.dstBlend = dstBlend;
    }

    public String getEmissiveDataMode()
    {
        return emissiveDataMode;
    }

    public void setEmissiveDataMode(String emissiveDataMode)
    {
        this.emissiveDataMode = emissiveDataMode;
    }

    public boolean isFinalAlphaWrite()
    {
        return finalAlphaWrite;
    }

    public void setFinalAlphaWrite(boolean finalAlphaWrite)
    {
        this.finalAlphaWrite = finalAlphaWrite;
    }

    public double getFinalAlphaWriteValue()
    {
        return finalAlphaWriteValue;
    }

    public void setFinalAlphaWriteValue(double finalAlphaWriteValue)
    {
        this.finalAlphaWriteValue = finalAlphaWriteValue;
    }

    public boolean isForceTextureAddressClamp()
    {
        return forceTextureAddressClamp;
    }

    public void setForceTextureAddressClamp(boolean forceTextureAddressClamp)
    {
        this.forceTextureAddressClamp = forceTextureAddressClamp;
    }

    public boolean isForceTextureAddressWrap()
    {
        return forceTextureAddressWrap;
    }

    public void setForceTextureAddressWrap(boolean forceTextureAddressWrap)
    {
        this.forceTextureAddressWrap = forceTextureAddressWrap;
    }

    public boolean isFresnelDiffuse()
    {
        return fresnelDiffuse;
    }

    public void setFresnelDiffuse(boolean fresnelDiffuse)
    {
        this.fresnelDiffuse = fresnelDiffuse;
    }

    public boolean isFresnelEnvironment()
    {
        return fresnelEnvironment;
    }

    public void setFresnelEnvironment(boolean fresnelEnvironment)
    {
        this.fresnelEnvironment = fresnelEnvironment;
    }

    public boolean isFresnelSpecular()
    {
        return fresnelSpecular;
    }

    public void setFresnelSpecular(boolean fresnelSpecular)
    {
        this.fresnelSpecular = fresnelSpecular;
    }

    public boolean isGlobalEnvironment()
    {
        return globalEnvironment;
    }

    public void setGlobalEnvironment(boolean globalEnvironment)
    {
        this.globalEnvironment = globalEnvironment;
    }

    public boolean isNoShadow()
    {
        return noShadow;
    }

    public void setNoShadow(boolean noShadow)
    {
        this.noShadow = noShadow;
    }

    public boolean isNoZWrite()
    {
        return noZWrite;
    }

    public void setNoZWrite(boolean noZWrite)
    {
        this.noZWrite = noZWrite;
    }

    public boolean isPrecipitationOffset()
    {
        return precipitationOffset;
    }

    public void setPrecipitationOffset(boolean precipitationOffset)
    {
        this.precipitationOffset = precipitationOffset;
    }

    public double getPrecipitationOffsetFactor()
    {
        return precipitationOffsetFactor;
    }

    public void setPrecipitationOffsetFactor(double precipitationOffsetFactor)
    {
        this.precipitationOffsetFactor = precipitationOffsetFactor;
    }

    public boolean isPrecipitationUse()
    {
        return precipitationUse;
    }

    public void setPrecipitationUse(boolean precipitationUse)
    {
        this.precipitationUse = precipitationUse;
    }

    public boolean isPrelitVertices()
    {
        return prelitVertices;
    }

    public void setPrelitVertices(boolean prelitVertices)
    {
        this.prelitVertices = prelitVertices;
    }

    public double getScaleEnvironment()
    {
        return scaleEnvironment;
    }

    public void setScaleEnvironment(double scaleEnvironment)
    {
        this.scaleEnvironment = scaleEnvironment;
    }

    public double getSpecularBloomFactor()
    {
        return specularBloomFactor;
    }

    public void setSpecularBloomFactor(double specularBloomFactor)
    {
        this.specularBloomFactor = specularBloomFactor;
    }

    public double[] getSpecularComponent()
    {
        return specularComponent;
    }

    public void setSpecularComponent(double specularComponent[])
    {
        this.specularComponent = specularComponent;
    }

    public void setSpecularComponent(double specularComponent_r, double specularComponent_g, double specularComponent_b)
    {
        specularComponent[0] = specularComponent_r;
        specularComponent[1] = specularComponent_g;
        specularComponent[2] = specularComponent_b;
    }

    public double getSpecularMapPowerScale()
    {
        return specularMapPowerScale;
    }

    public void setSpecularMapPowerScale(double specularMapPowerScale)
    {
        this.specularMapPowerScale = specularMapPowerScale;
    }

    public double getSpecularPower()
    {
        return specularPower;
    }

    public void setSpecularPower(double specularPower)
    {
        this.specularPower = specularPower;
    }

    public String getSrcBlend()
    {
        return srcBlend;
    }

    public void setSrcBlend(String srcBlend)
    {
        this.srcBlend = srcBlend;
    }

    public boolean isVolumeShadow()
    {
        return volumeShadow;
    }

    public void setVolumeShadow(boolean volumeShadow)
    {
        this.volumeShadow = volumeShadow;
    }

    public boolean isZTestAlpha()
    {
        return zTestAlpha;
    }

    public void setZTestAlpha(boolean testAlpha)
    {
        zTestAlpha = testAlpha;
    }

    public boolean isZWriteAlpha()
    {
        return zWriteAlpha;
    }

    public void setZWriteAlpha(boolean writeAlpha)
    {
        zWriteAlpha = writeAlpha;
    }

    private double diffuseComponent[];
    private double specularComponent[];
    private double specularPower;
    private double detailBumpScales[] = {
        1.0D, 1.0D
    };
    private double scaleEnvironment;
    private boolean globalEnvironment;
    private boolean blendEnvironmentDiffuseAlpha;
    private boolean blendEnvironmentSpecularAlpha;
    private boolean fresnelDiffuse;
    private boolean fresnelSpecular;
    private boolean fresnelEnvironment;
    private boolean precipitationUse;
    private boolean precipitationOffset;
    private double precipitationOffsetFactor;
    private double specularMapPowerScale;
    private String srcBlend;
    private String dstBlend;
    private boolean blendDiffuseByBaseAlpha;
    private boolean blendDiffuseByInverseSpecularMapAlpha;
    private boolean allowBloom;
    private double specularBloomFactor;
    private String emissiveDataMode;
    private boolean zTestAlpha;
    private double AlphaTestValue;
    private String alphaTestFunction;
    private boolean finalAlphaWrite;
    private double finalAlphaWriteValue;
    private boolean assumeVerticalNormal;
    private boolean zWriteAlpha;
    private boolean noZWrite;
    private boolean volumeShadow;
    private boolean noShadow;
    private boolean prelitVertices;
    private boolean allowSkinning;
    private boolean doubleSided;
    private boolean blendConstant;
    private boolean forceTextureAddressWrap;
    private boolean forceTextureAddressClamp;
    private boolean allowBaseMaterialSpecular;
    private boolean allowSpecularBloom;
    private boolean allowEmissiveBloom;
    private double ambientLightScale;
    private String diffuseTextureFileName;
}
