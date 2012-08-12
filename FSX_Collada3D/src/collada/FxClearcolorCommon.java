
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_clearcolor_common", propOrder={"value"})
public class FxClearcolorCommon
{
  @XmlValue
  protected List<Double> value;
  @XmlAttribute
  protected BigInteger index;
  public List<Double> getValue()
  {
    if (this.value == null)
      this.value = new ArrayList();
    return this.value;
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

