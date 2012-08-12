
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum GlBlendType
{
  ZERO, ONE, SRC_COLOR, ONE_MINUS_SRC_COLOR, DEST_COLOR, ONE_MINUS_DEST_COLOR, SRC_ALPHA, ONE_MINUS_SRC_ALPHA, DST_ALPHA, ONE_MINUS_DST_ALPHA, CONSTANT_COLOR, ONE_MINUS_CONSTANT_COLOR, CONSTANT_ALPHA, ONE_MINUS_CONSTANT_ALPHA, SRC_ALPHA_SATURATE;
  /*public static final GlBlendType[] values()
  {
    return ((GlBlendType[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static GlBlendType fromValue(String v)
  {
    return valueOf(v);
  }
*/}

