
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="gles_texture_unit", propOrder={"surface", "samplerState", "texcoord", "extra"})
public class GlesTextureUnit
{
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String surface;
  @XmlElement(name="sampler_state")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String samplerState;
  protected Texcoord texcoord;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  public String getSurface()
  {
    return this.surface;
  }
  public void setSurface(String value)
  {
    this.surface = value;
  }
  public String getSamplerState()
  {
    return this.samplerState;
  }
  public void setSamplerState(String value)
  {
    this.samplerState = value;
  }
  public Texcoord getTexcoord()
  {
    return this.texcoord;
  }
  public void setTexcoord(Texcoord value)
  {
    this.texcoord = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public String getSid()
  {
    return this.sid;
  }
  public void setSid(String value)
  {
    this.sid = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="")
  public static class Texcoord
  {
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String semantic;
    public String getSemantic()
    {
      return this.semantic;
    }
    public void setSemantic(String value)
    {
      this.semantic = value;
    }
  }
}

