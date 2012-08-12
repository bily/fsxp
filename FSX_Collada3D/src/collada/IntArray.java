
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
@XmlRootElement(name="int_array")
public class IntArray
{
  @XmlValue
  protected List<Long> value;
  @XmlAttribute(required=true)
  protected BigInteger count;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  protected BigInteger maxInclusive;
  @XmlAttribute
  protected BigInteger minInclusive;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  public List<Long> getValue()
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
  public String getId()
  {
    return this.id;
  }
  public void setId(String value)
  {
    this.id = value;
  }
  public BigInteger getMaxInclusive()
  {
    if (this.maxInclusive == null)
      return new BigInteger("2147483647");
    return this.maxInclusive;
  }
  public void setMaxInclusive(BigInteger value)
  {
    this.maxInclusive = value;
  }
  public BigInteger getMinInclusive()
  {
    if (this.minInclusive == null)
      return new BigInteger("-2147483648");
    return this.minInclusive;
  }
  public void setMinInclusive(BigInteger value)
  {
    this.minInclusive = value;
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

