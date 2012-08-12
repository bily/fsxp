
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"param"})
@XmlRootElement(name="accessor")
public class Accessor
{
  protected List<Param> param;
  @XmlAttribute(required=true)
  protected BigInteger count;
  @XmlAttribute
  protected BigInteger offset;
  @XmlAttribute
  protected String source;
  @XmlAttribute
  protected BigInteger stride;
  public List<Param> getParam()
  {
    if (this.param == null)
      this.param = new ArrayList();
    return this.param;
  }
  public BigInteger getCount()
  {
    return this.count;
  }
  public void setCount(BigInteger value)
  {
    this.count = value;
  }
  public BigInteger getOffset()
  {
    if (this.offset == null)
      return new BigInteger("0");
    return this.offset;
  }
  public void setOffset(BigInteger value)
  {
    this.offset = value;
  }
  public String getSource()
  {
    return this.source;
  }
  public void setSource(String value)
  {
    this.source = value;
  }
  public BigInteger getStride()
  {
    if (this.stride == null)
      return new BigInteger("1");
    return this.stride;
  }
  public void setStride(BigInteger value)
  {
    this.stride = value;
  }
}

