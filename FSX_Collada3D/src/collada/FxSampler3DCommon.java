
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_sampler3D_common", propOrder={"source", "wrapS", "wrapT", "wrapP", "minfilter", "magfilter", "mipfilter", "borderColor", "mipmapMaxlevel", "mipmapBias", "extra"})
public class FxSampler3DCommon
{
  @XmlElement(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String source;
  @XmlElement(name="wrap_s", defaultValue="WRAP")
  protected FxSamplerWrapCommon wrapS;
  @XmlElement(name="wrap_t", defaultValue="WRAP")
  protected FxSamplerWrapCommon wrapT;
  @XmlElement(name="wrap_p", defaultValue="WRAP")
  protected FxSamplerWrapCommon wrapP;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon minfilter;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon magfilter;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon mipfilter;
  @XmlList
  @XmlElement(name="border_color", type=Double.class)
  protected List<Double> borderColor;
  @XmlElement(name="mipmap_maxlevel", defaultValue="255")
  protected Short mipmapMaxlevel;
  @XmlElement(name="mipmap_bias", defaultValue="0.0")
  protected Float mipmapBias;
  protected List<Extra> extra;
  public String getSource()
  {
    return this.source;
  }
  public void setSource(String value)
  {
    this.source = value;
  }
  public FxSamplerWrapCommon getWrapS()
  {
    return this.wrapS;
  }
  public void setWrapS(FxSamplerWrapCommon value)
  {
    this.wrapS = value;
  }
  public FxSamplerWrapCommon getWrapT()
  {
    return this.wrapT;
  }
  public void setWrapT(FxSamplerWrapCommon value)
  {
    this.wrapT = value;
  }
  public FxSamplerWrapCommon getWrapP()
  {
    return this.wrapP;
  }
  public void setWrapP(FxSamplerWrapCommon value)
  {
    this.wrapP = value;
  }
  public FxSamplerFilterCommon getMinfilter()
  {
    return this.minfilter;
  }
  public void setMinfilter(FxSamplerFilterCommon value)
  {
    this.minfilter = value;
  }
  public FxSamplerFilterCommon getMagfilter()
  {
    return this.magfilter;
  }
  public void setMagfilter(FxSamplerFilterCommon value)
  {
    this.magfilter = value;
  }
  public FxSamplerFilterCommon getMipfilter()
  {
    return this.mipfilter;
  }
  public void setMipfilter(FxSamplerFilterCommon value)
  {
    this.mipfilter = value;
  }
  public List<Double> getBorderColor()
  {
    if (this.borderColor == null)
      this.borderColor = new ArrayList();
    return this.borderColor;
  }
  public Short getMipmapMaxlevel()
  {
    return this.mipmapMaxlevel;
  }
  public void setMipmapMaxlevel(Short value)
  {
    this.mipmapMaxlevel = value;
  }
  public Float getMipmapBias()
  {
    return this.mipmapBias;
  }
  public void setMipmapBias(Float value)
  {
    this.mipmapBias = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

