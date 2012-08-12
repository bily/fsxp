
package collada;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_include_common")
public class FxIncludeCommon
{
  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  @XmlAttribute(required=true)
  protected String url;
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

