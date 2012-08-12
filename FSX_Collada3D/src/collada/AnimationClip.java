
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "instanceAnimation", "extra"})
@XmlRootElement(name="animation_clip")
public class AnimationClip
{
  protected Asset asset;
  @XmlElement(name="instance_animation", required=true)
  protected List<InstanceWithExtra> instanceAnimation;
  protected List<Extra> extra;
  @XmlAttribute
  protected Double end;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  @XmlAttribute
  protected Double start;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public List<InstanceWithExtra> getInstanceAnimation()
  {
    if (this.instanceAnimation == null)
      this.instanceAnimation = new ArrayList();
    return this.instanceAnimation;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public Double getEnd()
  {
    return this.end;
  }
  public void setEnd(Double value)
  {
    this.end = value;
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
  public double getStart()
  {
    if (this.start == null)
      return 0D;
    return this.start.doubleValue();
  }
  public void setStart(Double value)
  {
    this.start = value;
  }
}

