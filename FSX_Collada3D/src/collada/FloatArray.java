
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"value"})
@XmlRootElement(name="float_array")
public class FloatArray
{
  @XmlValue
  protected List<Double> value;
  @XmlAttribute(required=true)
  protected BigInteger count;
  @XmlAttribute
  protected Short digits;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  protected Short magnitude;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  public List<Double> getValue()
  {
    if (this.value == null)
      this.value = new ArrayList();
    return this.value;
  }
  public BigInteger getCount()
  {
    return this.count;
  }
  public void setCount(BigInteger value)
  {
    this.count = value;
  }
  public short getDigits()
  {
    if (this.digits == null)
      return 6;
    return this.digits.shortValue();
  }
  public void setDigits(Short value)
  {
    this.digits = value;
  }
  public String getId()
  {
    return this.id;
  }
  public void setId(String value)
  {
    this.id = value;
  }
  public short getMagnitude()
  {
    if (this.magnitude == null)
      return 38;
    return this.magnitude.shortValue();
  }
  public void setMagnitude(Short value)
  {
    this.magnitude = value;
  }
  public String getName()
  {
    return this.name;
  }
  public void setName(String value)
  {
    this.name = value;
  }
}

