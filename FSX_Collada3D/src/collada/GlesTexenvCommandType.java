
package collada;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="gles_texenv_command_type", propOrder={"constant"})
public class GlesTexenvCommandType
{
  protected GlesTextureConstantType constant;
  @XmlAttribute
  protected GlesTexenvModeEnums operator;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String unit;
  public GlesTextureConstantType getConstant()
  {
    return this.constant;
  }
  public void setConstant(GlesTextureConstantType value)
  {
    this.constant = value;
  }
  public GlesTexenvModeEnums getOperator()
  {
    return this.operator;
  }
  public void setOperator(GlesTexenvModeEnums value)
  {
    this.operator = value;
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

