
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"bindMaterial", "extra"})
@XmlRootElement(name="instance_geometry")
public class InstanceGeometry
{
  @XmlElement(name="bind_material")
  protected BindMaterial bindMaterial;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  @XmlAttribute(required=true)
  protected String url;
  public BindMaterial getBindMaterial()
  {
    return this.bindMaterial;
  }
  public void setBindMaterial(BindMaterial value)
  {
    this.bindMaterial = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public String getName()
  {
    return this.name;
  }
  public void setName(String value)
  {
    this.name = value;
  }
  public String getSid()
  {
    return this.sid;
  }
  public void setSid(String value)
  {
    this.sid = value;
  }
  public String getUrl()
  {
    return this.url;
  }
  public void setUrl(String value)
  {
    this.url = value;
  }
}

