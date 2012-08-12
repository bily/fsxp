
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum FxSamplerFilterCommon
{
  NONE, NEAREST, LINEAR, NEAREST_MIPMAP_NEAREST, LINEAR_MIPMAP_NEAREST, NEAREST_MIPMAP_LINEAR, LINEAR_MIPMAP_LINEAR;
  /*public static final FxSamplerFilterCommon[] values()
  {
    return ((FxSamplerFilterCommon[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static FxSamplerFilterCommon fromValue(String v)
  {
    return valueOf(v);
  }
*/}

