
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
@XmlType(name="", propOrder={"asset", "instanceForceField", "instancePhysicsModel", "techniqueCommon", "technique", "extra"})
@XmlRootElement(name="physics_scene")
public class PhysicsScene
{
  protected Asset asset;
  @XmlElement(name="instance_force_field")
  protected List<InstanceWithExtra> instanceForceField;
  @XmlElement(name="instance_physics_model")
  protected List<InstancePhysicsModel> instancePhysicsModel;
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
  public List<InstanceWithExtra> getInstanceForceField()
  {
    if (this.instanceForceField == null)
      this.instanceForceField = new ArrayList();
    return this.instanceForceField;
  }
  public List<InstancePhysicsModel> getInstancePhysicsModel()
  {
    if (this.instancePhysicsModel == null)
      this.instancePhysicsModel = new ArrayList();
    return this.instancePhysicsModel;
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
  @XmlType(name="", propOrder={"gravity", "timeStep"})
  public static class TechniqueCommon
  {
    protected TargetableFloat3 gravity;
    @XmlElement(name="time_step")
    protected TargetableFloat timeStep;
    public TargetableFloat3 getGravity()
    {
      return this.gravity;
    }
    public void setGravity(TargetableFloat3 value)
    {
      this.gravity = value;
    }
    public TargetableFloat getTimeStep()
    {
      return this.timeStep;
    }
    public void setTimeStep(TargetableFloat value)
    {
      this.timeStep = value;
    }
  }
}

