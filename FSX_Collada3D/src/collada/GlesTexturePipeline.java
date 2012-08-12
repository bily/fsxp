
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="gles_texture_pipeline", propOrder={"texcombinerOrTexenvOrExtra"})
public class GlesTexturePipeline
{
  @XmlElements({@javax.xml.bind.annotation.XmlElement(name="texcombiner", type=GlesTexcombinerCommandType.class), @javax.xml.bind.annotation.XmlElement(name="extra", type=Extra.class), @javax.xml.bind.annotation.XmlElement(name="texenv", type=GlesTexenvCommandType.class)})
  protected List<Object> texcombinerOrTexenvOrExtra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  public List<Object> getTexcombinerOrTexenvOrExtra()
  {
    if (this.texcombinerOrTexenvOrExtra == null)
      this.texcombinerOrTexenvOrExtra = new ArrayList();
    return this.texcombinerOrTexenvOrExtra;
  }
  public String getSid()
  {
    return this.sid;
  }
  public void setSid(String value)
  {
    this.sid = value;
  }
}

