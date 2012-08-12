
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"techniqueCommon", "technique", "extra"})
@XmlRootElement(name="rigid_body")
public class RigidBody
{
  @XmlElement(name="technique_common", required=true)
  protected TechniqueCommon techniqueCommon;
  protected List<Technique> technique;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
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
  public String getName()
  {
    return this.name;
  }
  public void setName(String value)
  {
    this.name = value;
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
  @XmlType(name="", propOrder={"dynamic", "mass", "massFrame", "inertia", "instancePhysicsMaterial", "physicsMaterial", "shape"})
  public static class TechniqueCommon
  {
    protected Dynamic dynamic;
    protected TargetableFloat mass;
    @XmlElement(name="mass_frame")
    protected MassFrame massFrame;
    protected TargetableFloat3 inertia;
    @XmlElement(name="instance_physics_material")
    protected InstanceWithExtra instancePhysicsMaterial;
    @XmlElement(name="physics_material")
    protected PhysicsMaterial physicsMaterial;
    @XmlElement(required=true)
    protected List<Shape> shape;
    public Dynamic getDynamic()
    {
      return this.dynamic;
    }
    public void setDynamic(Dynamic value)
    {
      this.dynamic = value;
    }
    public TargetableFloat getMass()
    {
      return this.mass;
    }
    public void setMass(TargetableFloat value)
    {
      this.mass = value;
    }
    public MassFrame getMassFrame()
    {
      return this.massFrame;
    }
    public void setMassFrame(MassFrame value)
    {
      this.massFrame = value;
    }
    public TargetableFloat3 getInertia()
    {
      return this.inertia;
    }
    public void setInertia(TargetableFloat3 value)
    {
      this.inertia = value;
    }
    public InstanceWithExtra getInstancePhysicsMaterial()
    {
      return this.instancePhysicsMaterial;
    }
    public void setInstancePhysicsMaterial(InstanceWithExtra value)
    {
      this.instancePhysicsMaterial = value;
    }
    public PhysicsMaterial getPhysicsMaterial()
    {
      return this.physicsMaterial;
    }
    public void setPhysicsMaterial(PhysicsMaterial value)
    {
      this.physicsMaterial = value;
    }
    public List<Shape> getShape()
    {
      if (this.shape == null)
        this.shape = new ArrayList();
      return this.shape;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"hollow", "mass", "density", "instancePhysicsMaterial", "physicsMaterial", "instanceGeometry", "plane", "box", "sphere", "cylinder", "taperedCylinder", "capsule", "taperedCapsule", "translateOrRotate", "extra"})
    public static class Shape
    {
      protected Hollow hollow;
      protected TargetableFloat mass;
      protected TargetableFloat density;
      @XmlElement(name="instance_physics_material")
      protected InstanceWithExtra instancePhysicsMaterial;
      @XmlElement(name="physics_material")
      protected PhysicsMaterial physicsMaterial;
      @XmlElement(name="instance_geometry")
      protected InstanceGeometry instanceGeometry;
      protected Plane plane;
      protected Box box;
      protected Sphere sphere;
      protected Cylinder cylinder;
      @XmlElement(name="tapered_cylinder")
      protected TaperedCylinder taperedCylinder;
      protected Capsule capsule;
      @XmlElement(name="tapered_capsule")
      protected TaperedCapsule taperedCapsule;
      @XmlElements({@XmlElement(name="rotate", type=Rotate.class), @XmlElement(name="translate", type=TargetableFloat3.class)})
      protected List<Object> translateOrRotate;
      protected List<Extra> extra;
      public Hollow getHollow()
      {
        return this.hollow;
      }
      public void setHollow(Hollow value)
      {
        this.hollow = value;
      }
      public TargetableFloat getMass()
      {
        return this.mass;
      }
      public void setMass(TargetableFloat value)
      {
        this.mass = value;
      }
      public TargetableFloat getDensity()
      {
        return this.density;
      }
      public void setDensity(TargetableFloat value)
      {
        this.density = value;
      }
      public InstanceWithExtra getInstancePhysicsMaterial()
      {
        return this.instancePhysicsMaterial;
      }
      public void setInstancePhysicsMaterial(InstanceWithExtra value)
      {
        this.instancePhysicsMaterial = value;
      }
      public PhysicsMaterial getPhysicsMaterial()
      {
        return this.physicsMaterial;
      }
      public void setPhysicsMaterial(PhysicsMaterial value)
      {
        this.physicsMaterial = value;
      }
      public InstanceGeometry getInstanceGeometry()
      {
        return this.instanceGeometry;
      }
      public void setInstanceGeometry(InstanceGeometry value)
      {
        this.instanceGeometry = value;
      }
      public Plane getPlane()
      {
        return this.plane;
      }
      public void setPlane(Plane value)
      {
        this.plane = value;
      }
      public Box getBox()
      {
        return this.box;
      }
      public void setBox(Box value)
      {
        this.box = value;
      }
      public Sphere getSphere()
      {
        return this.sphere;
      }
      public void setSphere(Sphere value)
      {
        this.sphere = value;
      }
      public Cylinder getCylinder()
      {
        return this.cylinder;
      }
      public void setCylinder(Cylinder value)
      {
        this.cylinder = value;
      }
      public TaperedCylinder getTaperedCylinder()
      {
        return this.taperedCylinder;
      }
      public void setTaperedCylinder(TaperedCylinder value)
      {
        this.taperedCylinder = value;
      }
      public Capsule getCapsule()
      {
        return this.capsule;
      }
      public void setCapsule(Capsule value)
      {
        this.capsule = value;
      }
      public TaperedCapsule getTaperedCapsule()
      {
        return this.taperedCapsule;
      }
      public void setTaperedCapsule(TaperedCapsule value)
      {
        this.taperedCapsule = value;
      }
      public List<Object> getTranslateOrRotate()
      {
        if (this.translateOrRotate == null)
          this.translateOrRotate = new ArrayList();
        return this.translateOrRotate;
      }
      public List<Extra> getExtra()
      {
        if (this.extra == null)
          this.extra = new ArrayList();
        return this.extra;
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"value"})
      public static class Hollow
      {
        @XmlValue
        protected boolean value;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String sid;
        public boolean isValue()
        {
          return this.value;
        }
        public void setValue(boolean value)
        {
          this.value = value;
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
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"translateOrRotate"})
    public static class MassFrame
    {
      @XmlElements({@XmlElement(name="rotate", type=Rotate.class), @XmlElement(name="translate", type=TargetableFloat3.class)})
      protected List<Object> translateOrRotate;
      public List<Object> getTranslateOrRotate()
      {
        if (this.translateOrRotate == null)
          this.translateOrRotate = new ArrayList();
        return this.translateOrRotate;
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"value"})
    public static class Dynamic
    {
      @XmlValue
      protected boolean value;
      @XmlAttribute
      @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
      protected String sid;
      public boolean isValue()
      {
        return this.value;
      }
      public void setValue(boolean value)
      {
        this.value = value;
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
  }
}

