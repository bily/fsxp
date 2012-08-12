
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlesTexenvModeEnums
{
  REPLACE, MODULATE, DECAL, BLEND, ADD;
  /*public static final GlesTexenvModeEnums[] values()
  {
    return ((GlesTexenvModeEnums[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlesTexenvModeEnums fromValue(String v)
  {
    return valueOf(v);
  }
*/}

