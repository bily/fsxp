
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="gles_sampler_state", propOrder={"wrapS", "wrapT", "minfilter", "magfilter", "mipfilter", "mipmapMaxlevel", "mipmapBias", "extra"})
public class GlesSamplerState
{
  @XmlElement(name="wrap_s", defaultValue="REPEAT")
  protected GlesSamplerWrap wrapS;
  @XmlElement(name="wrap_t", defaultValue="REPEAT")
  protected GlesSamplerWrap wrapT;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon minfilter;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon magfilter;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon mipfilter;
  @XmlElement(name="mipmap_maxlevel", defaultValue="255")
  protected Short mipmapMaxlevel;
  @XmlElement(name="mipmap_bias", defaultValue="0.0")
  protected Float mipmapBias;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  public GlesSamplerWrap getWrapS()
  {
    return this.wrapS;
  }
  public void setWrapS(GlesSamplerWrap value)
  {
    this.wrapS = value;
  }
  public GlesSamplerWrap getWrapT()
  {
    return this.wrapT;
  }
  public void setWrapT(GlesSamplerWrap value)
  {
    this.wrapT = value;
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
  public String getSid()
  {
    return this.sid;
  }
  public void setSid(String value)
  {
    this.sid = value;
  }
}

