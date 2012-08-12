
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"content"})
@XmlRootElement(name="animation")
public class Animation
{
  @XmlElementRefs({@javax.xml.bind.annotation.XmlElementRef(name="extra", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Extra.class), @javax.xml.bind.annotation.XmlElementRef(name="sampler", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Sampler.class), @javax.xml.bind.annotation.XmlElementRef(name="source", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Source.class), @javax.xml.bind.annotation.XmlElementRef(name="channel", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Channel.class), @javax.xml.bind.annotation.XmlElementRef(name="asset", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Asset.class), @javax.xml.bind.annotation.XmlElementRef(name="animation", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Animation.class)})
  protected List<Object> content;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  public List<Object> getContent()
  {
    if (this.content == null)
      this.content = new ArrayList();
    return this.content;
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
}

