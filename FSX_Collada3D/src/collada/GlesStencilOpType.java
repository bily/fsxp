
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlesStencilOpType
{
  KEEP, ZERO, REPLACE, INCR, DECR, INVERT;
  /*public static final GlesStencilOpType[] values()
  {
    return ((GlesStencilOpType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlesStencilOpType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

