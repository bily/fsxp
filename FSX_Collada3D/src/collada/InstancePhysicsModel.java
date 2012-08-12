
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
@XmlType(name="", propOrder={"instanceForceField", "instanceRigidBody", "instanceRigidConstraint", "extra"})
@XmlRootElement(name="instance_physics_model")
public class InstancePhysicsModel
{
  @XmlElement(name="instance_force_field")
  protected List<InstanceWithExtra> instanceForceField;
  @XmlElement(name="instance_rigid_body")
  protected List<InstanceRigidBody> instanceRigidBody;
  @XmlElement(name="instance_rigid_constraint")
  protected List<InstanceRigidConstraint> instanceRigidConstraint;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  @XmlAttribute
  protected String parent;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  @XmlAttribute(required=true)
  protected String url;
  public List<InstanceWithExtra> getInstanceForceField()
  {
    if (this.instanceForceField == null)
      this.instanceForceField = new ArrayList();
    return this.instanceForceField;
  }
  public List<InstanceRigidBody> getInstanceRigidBody()
  {
    if (this.instanceRigidBody == null)
      this.instanceRigidBody = new ArrayList();
    return this.instanceRigidBody;
  }
  public List<InstanceRigidConstraint> getInstanceRigidConstraint()
  {
    if (this.instanceRigidConstraint == null)
      this.instanceRigidConstraint = new ArrayList();
    return this.instanceRigidConstraint;
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
  public String getParent()
  {
    return this.parent;
  }
  public void setParent(String value)
  {
    this.parent = value;
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

