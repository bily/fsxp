
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"any"})
@XmlRootElement(name="technique")
public class Technique
{
  @XmlAnyElement(lax=true)
  protected List<Object> any;
  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String profile;
  public List<Object> getAny()
  {
    if (this.any == null)
      this.any = new ArrayList();
    return this.any;
  }
  public String getProfile()
  {
    return this.profile;
  }
  public void setProfile(String value)
  {
    this.profile = value;
  }
}

