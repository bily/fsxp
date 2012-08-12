
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum NodeType
{
  JOINT, NODE;
  /*public static final NodeType[] values()
  {
    return ((NodeType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static NodeType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

