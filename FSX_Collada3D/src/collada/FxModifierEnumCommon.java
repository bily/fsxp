
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum FxModifierEnumCommon
{
  CONST, UNIFORM, VARYING, STATIC, VOLATILE, EXTERN, SHARED;
  /*public static final FxModifierEnumCommon[] values()
  {
    return ((FxModifierEnumCommon[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static FxModifierEnumCommon fromValue(String v)
  {
    return valueOf(v);
  }
*/}

