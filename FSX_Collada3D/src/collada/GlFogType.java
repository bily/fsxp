
package collada;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
@XmlEnum
public enum GlFogType
{
  LINEAR, EXP, EXP_2;
 // private final String value;
  /*public static final GlFogType[] values()
  {
    return ((GlFogType[])$VALUES.clone());
  }
  public String value()
  {
    return this.value;
  }
  public static GlFogType fromValue(String v)
  {
    GlFogType[] arr$ = values();
    int len$ = arr$.length;
    int i$ = 0;
    while (true)
    {
      if (i$ < len$)
        break;
      GlFogType c = arr$[i$];
      if (c.value.equals(v))
        return c;
      ++i$;
    }
    throw new IllegalArgumentException(v.toString());
  }
*/}

