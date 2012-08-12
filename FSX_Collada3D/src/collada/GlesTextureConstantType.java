
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="gles_texture_constant_type")
public class GlesTextureConstantType
{
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String param;
  @XmlAttribute
  protected List<Double> value;
  public String getParam()
  {
    return this.param;
  }
  public void setParam(String value)
  {
    this.param = value;
  }
  public List<Double> getValue()
  {
    if (this.value == null)
      this.value = new ArrayList();
    return this.value;
  }
}

