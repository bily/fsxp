
package collada;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
@XmlEnum
public enum FxSurfaceFormatHintOptionEnum
{
  SRGB_GAMMA, NORMALIZED_3, NORMALIZED_4, COMPRESSABLE;
 // private final String value;
  /*public static final FxSurfaceFormatHintOptionEnum[] values()
  {
    return ((FxSurfaceFormatHintOptionEnum[])$VALUES.clone());
  }
  public String value()
  {
    return this.value;
  }
  public static FxSurfaceFormatHintOptionEnum fromValue(String v)
  {
    FxSurfaceFormatHintOptionEnum[] arr$ = values();
    int len$ = arr$.length;
    int i$ = 0;
    while (true)
    {
      if (i$ < len$)
        break;
      FxSurfaceFormatHintOptionEnum c = arr$[i$];
      if (c.value.equals(v))
        return c;
      ++i$;
    }
    throw new IllegalArgumentException(v.toString());
  }
*/}

