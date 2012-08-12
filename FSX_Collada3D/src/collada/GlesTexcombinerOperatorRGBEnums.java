
package collada;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
@XmlEnum
public enum GlesTexcombinerOperatorRGBEnums
{
  REPLACE, MODULATE, ADD, ADD_SIGNED, INTERPOLATE, SUBTRACT, DOT_3_RGB, DOT_3_RGBA;
 // private final String value;
  /*public static final GlesTexcombinerOperatorRGBEnums[] values()
  {
    return ((GlesTexcombinerOperatorRGBEnums[])$VALUES.clone());
  }
  public String value()
  {
    return this.value;
  }
  public static GlesTexcombinerOperatorRGBEnums fromValue(String v)
  {
    GlesTexcombinerOperatorRGBEnums[] arr$ = values();
    int len$ = arr$.length;
    int i$ = 0;
    while (true)
    {
      if (i$ < len$)
        break;
      GlesTexcombinerOperatorRGBEnums c = arr$[i$];
      if (c.value.equals(v))
        return c;
      ++i$;
    }
    throw new IllegalArgumentException(v.toString());
  }
*/}

