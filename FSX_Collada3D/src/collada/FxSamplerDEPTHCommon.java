
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_samplerDEPTH_common", propOrder={"source", "wrapS", "wrapT", "minfilter", "magfilter", "extra"})
public class FxSamplerDEPTHCommon
{
  @XmlElement(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String source;
  @XmlElement(name="wrap_s", defaultValue="WRAP")
  protected FxSamplerWrapCommon wrapS;
  @XmlElement(name="wrap_t", defaultValue="WRAP")
  protected FxSamplerWrapCommon wrapT;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon minfilter;
  @XmlElement(defaultValue="NONE")
  protected FxSamplerFilterCommon magfilter;
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
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

