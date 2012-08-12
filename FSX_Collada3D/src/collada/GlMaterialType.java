
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlMaterialType
{
  EMISSION, AMBIENT, DIFFUSE, SPECULAR, AMBIENT_AND_DIFFUSE;
  /*public static final GlMaterialType[] values()
  {
    return ((GlMaterialType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlMaterialType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

