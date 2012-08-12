
package collada;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="gles_texcombiner_argumentAlpha_type")
public class GlesTexcombinerArgumentAlphaType
{
  @XmlAttribute
  protected GlesTexcombinerOperandAlphaEnums operand;
  @XmlAttribute
  protected GlesTexcombinerSourceEnums source;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String unit;
  public GlesTexcombinerOperandAlphaEnums getOperand()
  {
    if (this.operand == null)
      return GlesTexcombinerOperandAlphaEnums.SRC_ALPHA;
    return this.operand;
  }
  public void setOperand(GlesTexcombinerOperandAlphaEnums value)
  {
    this.operand = value;
  }
  public GlesTexcombinerSourceEnums getSource()
  {
    return this.source;
  }
  public void setSource(GlesTexcombinerSourceEnums value)
  {
    this.source = value;
  }
  public String getUnit()
  {
    return this.unit;
  }
  public void setUnit(String value)
  {
    this.unit = value;
  }
}

