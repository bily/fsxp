
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlesSamplerWrap
{
  REPEAT, CLAMP, CLAMP_TO_EDGE, MIRRORED_REPEAT;
  /*public static final GlesSamplerWrap[] values()
  {
    return ((GlesSamplerWrap[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlesSamplerWrap fromValue(String v)
  {
    return valueOf(v);
  }
*/}

