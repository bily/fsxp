
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlFogCoordSrcType
{
  FOG_COORDINATE, FRAGMENT_DEPTH;
  /*public static final GlFogCoordSrcType[] values()
  {
    return ((GlFogCoordSrcType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlFogCoordSrcType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

