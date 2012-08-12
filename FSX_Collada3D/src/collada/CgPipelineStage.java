
package collada;
import javax.xml.bind.annotation.XmlEnum;
@XmlEnum
public enum CgPipelineStage
{
  VERTEX, FRAGMENT;
  /*public static final CgPipelineStage[] values()
  {
    return ((CgPipelineStage[])$VALUES.clone());
  }
  public String value()
  {
    return name();
  }
  public static CgPipelineStage fromValue(String v)
  {
    return valueOf(v);
  }
*/}

