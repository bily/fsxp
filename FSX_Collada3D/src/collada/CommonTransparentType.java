
package collada;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="common_transparent_type")
public class CommonTransparentType extends CommonColorOrTextureType
{
  @XmlAttribute
  protected FxOpaqueEnum opaque;
  public FxOpaqueEnum getOpaque()
  {
    if (this.opaque == null)
      return FxOpaqueEnum.A_ONE;
    return this.opaque;
  }
  public void setOpaque(FxOpaqueEnum value)
  {
    this.opaque = value;
  }
}

