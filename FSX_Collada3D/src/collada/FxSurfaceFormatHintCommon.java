
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_surface_format_hint_common", propOrder={"channels", "range", "precision", "option", "extra"})
public class FxSurfaceFormatHintCommon
{
  @XmlElement(required=true)
  protected FxSurfaceFormatHintChannelsEnum channels;
  @XmlElement(required=true)
  protected FxSurfaceFormatHintRangeEnum range;
  protected FxSurfaceFormatHintPrecisionEnum precision;
  protected List<FxSurfaceFormatHintOptionEnum> option;
  protected List<Extra> extra;
  public FxSurfaceFormatHintChannelsEnum getChannels()
  {
    return this.channels;
  }
  public void setChannels(FxSurfaceFormatHintChannelsEnum value)
  {
    this.channels = value;
  }
  public FxSurfaceFormatHintRangeEnum getRange()
  {
    return this.range;
  }
  public void setRange(FxSurfaceFormatHintRangeEnum value)
  {
    this.range = value;
  }
  public FxSurfaceFormatHintPrecisionEnum getPrecision()
  {
    return this.precision;
  }
  public void setPrecision(FxSurfaceFormatHintPrecisionEnum value)
  {
    this.precision = value;
  }
  public List<FxSurfaceFormatHintOptionEnum> getOption()
  {
    if (this.option == null)
      this.option = new ArrayList();
    return this.option;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

