
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
@XmlType(name="", propOrder={"asset", "rigidBody", "rigidConstraint", "instancePhysicsModel", "extra"})
@XmlRootElement(name="physics_model")
public class PhysicsModel
{
  protected Asset asset;
  @XmlElement(name="rigid_body")
  protected List<RigidBody> rigidBody;
  @XmlElement(name="rigid_constraint")
  protected List<RigidConstraint> rigidConstraint;
  @XmlElement(name="instance_physics_model")
  protected List<InstancePhysicsModel> instancePhysicsModel;
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
  public List<RigidBody> getRigidBody()
  {
    if (this.rigidBody == null)
      this.rigidBody = new ArrayList();
    return this.rigidBody;
  }
  public List<RigidConstraint> getRigidConstraint()
  {
    if (this.rigidConstraint == null)
      this.rigidConstraint = new ArrayList();
    return this.rigidConstraint;
  }
  public List<InstancePhysicsModel> getInstancePhysicsModel()
  {
    if (this.instancePhysicsModel == null)
      this.instancePhysicsModel = new ArrayList();
    return this.instancePhysicsModel;
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
}

