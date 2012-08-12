
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlLogicOpType
{
  CLEAR, AND, AND_REVERSE, COPY, AND_INVERTED, NOOP, XOR, OR, NOR, EQUIV, INVERT, OR_REVERSE, COPY_INVERTED, NAND, SET;
  /*public static final GlLogicOpType[] values()
  {
    return ((GlLogicOpType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlLogicOpType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

