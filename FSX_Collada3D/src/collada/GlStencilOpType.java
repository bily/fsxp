
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlStencilOpType
{
  KEEP, ZERO, REPLACE, INCR, DECR, INVERT, INCR_WRAP, DECR_WRAP;
  /*public static final GlStencilOpType[] values()
  {
    return ((GlStencilOpType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlStencilOpType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

