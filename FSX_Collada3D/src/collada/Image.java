
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "data", "initFrom", "extra"})
@XmlRootElement(name="image")
public class Image
{
  protected Asset asset;
  @XmlList
  protected List<String> data;
  @XmlElement(name="init_from")
  protected String initFrom;
  protected List<Extra> extra;
  @XmlAttribute
  protected BigInteger depth;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String format;
  @XmlAttribute
  protected BigInteger height;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  @XmlAttribute
  protected BigInteger width;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public List<String> getData()
  {
    if (this.data == null)
      this.data = new ArrayList();
    return this.data;
  }
  public String getInitFrom()
  {
    return this.initFrom;
  }
  public void setInitFrom(String value)
  {
    this.initFrom = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public BigInteger getDepth()
  {
    if (this.depth == null)
      return new BigInteger("1");
    return this.depth;
  }
  public void setDepth(BigInteger value)
  {
    this.depth = value;
  }
  public String getFormat()
  {
    return this.format;
  }
  public void setFormat(String value)
  {
    this.format = value;
  }
  public BigInteger getHeight()
  {
    return this.height;
  }
  public void setHeight(BigInteger value)
  {
    this.height = value;
  }
  public String getId()
  {
    return this.id;
  }
  public void setId(String value)
  {
    this.id = value;
  }
  public String getName()
  {
    return this.name;
  }
  public void setName(String value)
  {
    this.name = value;
  }
  public BigInteger getWidth()
  {
    return this.width;
  }
  public void setWidth(BigInteger value)
  {
    this.width = value;
  }
}

