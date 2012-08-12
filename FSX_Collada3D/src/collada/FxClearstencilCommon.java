
package collada;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_clearstencil_common", propOrder={"value"})
public class FxClearstencilCommon
{
  @XmlValue
  protected byte value;
  @XmlAttribute
  protected BigInteger index;
  public byte getValue()
  {
    return this.value;
  }
  public void setValue(byte value)
  {
    this.value = value;
  }
  public BigInteger getIndex()
  {
    if (this.index == null)
      return new BigInteger("0");
    return this.index;
  }
  public void setIndex(BigInteger value)
  {
    this.index = value;
  }
}

