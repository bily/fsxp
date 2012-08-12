
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlLightModelColorControlType
{
  SINGLE_COLOR, SEPARATE_SPECULAR_COLOR;
  /*public static final GlLightModelColorControlType[] values()
  {
    return ((GlLightModelColorControlType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlLightModelColorControlType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

