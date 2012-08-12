
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlFuncType
{
  NEVER, LESS, LEQUAL, EQUAL, GREATER, NOTEQUAL, GEQUAL, ALWAYS;
  /*public static final GlFuncType[] values()
  {
    return ((GlFuncType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlFuncType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

