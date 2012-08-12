
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
@XmlType(name="", propOrder={"asset", "techniqueCommon", "technique", "extra"})
@XmlRootElement(name="physics_material")
public class PhysicsMaterial
{
  protected Asset asset;
  @XmlElement(name="technique_common", required=true)
  protected TechniqueCommon techniqueCommon;
  protected List<Technique> technique;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public TechniqueCommon getTechniqueCommon()
  {
    return this.techniqueCommon;
  }
  public void setTechniqueCommon(TechniqueCommon value)
  {
    this.techniqueCommon = value;
  }
  public List<Technique> getTechnique()
  {
    if (this.technique == null)
      this.technique = new ArrayList();
    return this.technique;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
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
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"dynamicFriction", "restitution", "staticFriction"})
  public static class TechniqueCommon
  {
    @XmlElement(name="dynamic_friction")
    protected TargetableFloat dynamicFriction;
    protected TargetableFloat restitution;
    @XmlElement(name="static_friction")
    protected TargetableFloat staticFriction;
    public TargetableFloat getDynamicFriction()
    {
      return this.dynamicFriction;
    }
    public void setDynamicFriction(TargetableFloat value)
    {
      this.dynamicFriction = value;
    }
    public TargetableFloat getRestitution()
    {
      return this.restitution;
    }
    public void setRestitution(TargetableFloat value)
    {
      this.restitution = value;
    }
    public TargetableFloat getStaticFriction()
    {
      return this.staticFriction;
    }
    public void setStaticFriction(TargetableFloat value)
    {
      this.staticFriction = value;
    }
  }
}

