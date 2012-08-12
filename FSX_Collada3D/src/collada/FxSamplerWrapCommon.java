
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum FxSamplerWrapCommon
{
  NONE, WRAP, MIRROR, CLAMP, BORDER;
  /*public static final FxSamplerWrapCommon[] values()
  {
    return ((FxSamplerWrapCommon[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static FxSamplerWrapCommon fromValue(String v)
  {
    return valueOf(v);
  }*/
}

