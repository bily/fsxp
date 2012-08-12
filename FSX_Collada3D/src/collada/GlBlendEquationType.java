
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlBlendEquationType
{
  FUNC_ADD, FUNC_SUBTRACT, FUNC_REVERSE_SUBTRACT, MIN, MAX;
  /*public static final GlBlendEquationType[] values()
  {
    return ((GlBlendEquationType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlBlendEquationType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

