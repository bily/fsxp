
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum FxPipelineStageCommon
{
  VERTEXPROGRAM, FRAGMENTPROGRAM, VERTEXSHADER, PIXELSHADER;
  /*public static final FxPipelineStageCommon[] values()
  {
    return ((FxPipelineStageCommon[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static FxPipelineStageCommon fromValue(String v)
  {
    return valueOf(v);
  }
*/}

