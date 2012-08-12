
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"input", "pOrPh", "extra"})
@XmlRootElement(name="polygons")
public class Polygons
{
  protected List<InputLocalOffset> input;
  @XmlElementRefs({@XmlElementRef(name="p", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @XmlElementRef(name="ph", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class)})
  protected List<JAXBElement<?>> pOrPh;
  protected List<Extra> extra;
  @XmlAttribute(required=true)
  protected BigInteger count;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String material;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  public List<InputLocalOffset> getInput()
  {
    if (this.input == null)
      this.input = new ArrayList();
    return this.input;
  }
  public List<JAXBElement<?>> getPOrPh()
  {
    if (this.pOrPh == null)
      this.pOrPh = new ArrayList();
    return this.pOrPh;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public BigInteger getCount()
  {
    return this.count;
  }
  public void setCount(BigInteger value)
  {
    this.count = value;
  }
  public String getMaterial()
  {
    return this.material;
  }
  public void setMaterial(String value)
  {
    this.material = value;
  }
  public String getName()
  {
    return this.name;
  }
  public void setName(String value)
  {
    this.name = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"p", "h"})
  public static class Ph
  {
    @XmlList
    @XmlElement(required=true)
    protected List<BigInteger> p;
    @XmlElementRef(name="h", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class)
    protected List<JAXBElement<List<BigInteger>>> h;
    public List<BigInteger> getP()
    {
      if (this.p == null)
        this.p = new ArrayList();
      return this.p;
    }
    public List<JAXBElement<List<BigInteger>>> getH()
    {
      if (this.h == null)
        this.h = new ArrayList();
      return this.h;
    }
  }
}

