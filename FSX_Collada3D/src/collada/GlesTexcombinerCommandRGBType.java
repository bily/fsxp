
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="gles_texcombiner_commandRGB_type", propOrder={"argument"})
public class GlesTexcombinerCommandRGBType
{
  @XmlElement(required=true)
  protected List<GlesTexcombinerArgumentRGBType> argument;
  @XmlAttribute
  protected GlesTexcombinerOperatorRGBEnums operator;
  @XmlAttribute
  protected Float scale;
  public List<GlesTexcombinerArgumentRGBType> getArgument()
  {
    if (this.argument == null)
      this.argument = new ArrayList();
    return this.argument;
  }
  public GlesTexcombinerOperatorRGBEnums getOperator()
  {
    return this.operator;
  }
  public void setOperator(GlesTexcombinerOperatorRGBEnums value)
  {
    this.operator = value;
  }
  public Float getScale()
  {
    return this.scale;
  }
  public void setScale(Float value)
  {
    this.scale = value;
  }
}

