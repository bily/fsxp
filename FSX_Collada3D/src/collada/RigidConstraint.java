
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
@XmlType(name="", propOrder={"refAttachment", "attachment", "techniqueCommon", "technique", "extra"})
@XmlRootElement(name="rigid_constraint")
public class RigidConstraint
{
  @XmlElement(name="ref_attachment", required=true)
  protected RefAttachment refAttachment;
  @XmlElement(required=true)
  protected Attachment attachment;
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
  public RefAttachment getRefAttachment()
  {
    return this.refAttachment;
  }
  public void setRefAttachment(RefAttachment value)
  {
    this.refAttachment = value;
  }
  public Attachment getAttachment()
  {
    return this.attachment;
  }
  public void setAttachment(Attachment value)
  {
    this.attachment = value;
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
  @XmlType(name="", propOrder={"enabled", "interpenetrate", "limits", "spring"})
  public static class TechniqueCommon
  {
    @XmlElement(defaultValue="true")
    protected Enabled enabled;
    @XmlElement(defaultValue="false")
    protected Interpenetrate interpenetrate;
    protected Limits limits;
    protected Spring spring;
    public Enabled getEnabled()
    {
      return this.enabled;
    }
    public void setEnabled(Enabled value)
    {
      this.enabled = value;
    }
    public Interpenetrate getInterpenetrate()
    {
      return this.interpenetrate;
    }
    public void setInterpenetrate(Interpenetrate value)
    {
      this.interpenetrate = value;
    }
    public Limits getLimits()
    {
      return this.limits;
    }
    public void setLimits(Limits value)
    {
      this.limits = value;
    }
    public Spring getSpring()
    {
      return this.spring;
    }
    public void setSpring(Spring value)
    {
      this.spring = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"angular", "linear"})
    public static class Spring
    {
      protected Angular angular;
      protected Linear linear;
      public Angular getAngular()
      {
        return this.angular;
      }
      public void setAngular(Angular value)
      {
        this.angular = value;
      }
      public Linear getLinear()
      {
        return this.linear;
      }
      public void setLinear(Linear value)
      {
        this.linear = value;
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"stiffness", "damping", "targetValue"})
      public static class Linear
      {
        @XmlElement(defaultValue="1.0")
        protected TargetableFloat stiffness;
        @XmlElement(defaultValue="0.0")
        protected TargetableFloat damping;
        @XmlElement(name="target_value", defaultValue="0.0")
        protected TargetableFloat targetValue;
        public TargetableFloat getStiffness()
        {
          return this.stiffness;
        }
        public void setStiffness(TargetableFloat value)
        {
          this.stiffness = value;
        }
        public TargetableFloat getDamping()
        {
          return this.damping;
        }
        public void setDamping(TargetableFloat value)
        {
          this.damping = value;
        }
        public TargetableFloat getTargetValue()
        {
          return this.targetValue;
        }
        public void setTargetValue(TargetableFloat value)
        {
          this.targetValue = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"stiffness", "damping", "targetValue"})
      public static class Angular
      {
        @XmlElement(defaultValue="1.0")
        protected TargetableFloat stiffness;
        @XmlElement(defaultValue="0.0")
        protected TargetableFloat damping;
        @XmlElement(name="target_value", defaultValue="0.0")
        protected TargetableFloat targetValue;
        public TargetableFloat getStiffness()
        {
          return this.stiffness;
        }
        public void setStiffness(TargetableFloat value)
        {
          this.stiffness = value;
        }
        public TargetableFloat getDamping()
        {
          return this.damping;
        }
        public void setDamping(TargetableFloat value)
        {
          this.damping = value;
        }
        public TargetableFloat getTargetValue()
        {
          return this.targetValue;
        }
        public void setTargetValue(TargetableFloat value)
        {
          this.targetValue = value;
        }
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"swingConeAndTwist", "linear"})
    public static class Limits
    {
      @XmlElement(name="swing_cone_and_twist")
      protected SwingConeAndTwist swingConeAndTwist;
      protected Linear linear;
      public SwingConeAndTwist getSwingConeAndTwist()
      {
        return this.swingConeAndTwist;
      }
      public void setSwingConeAndTwist(SwingConeAndTwist value)
      {
        this.swingConeAndTwist = value;
      }
      public Linear getLinear()
      {
        return this.linear;
      }
      public void setLinear(Linear value)
      {
        this.linear = value;
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"min", "max"})
      public static class SwingConeAndTwist
      {
        @XmlElement(defaultValue="0.0 0.0 0.0")
        protected TargetableFloat3 min;
        @XmlElement(defaultValue="0.0 0.0 0.0")
        protected TargetableFloat3 max;
        public TargetableFloat3 getMin()
        {
          return this.min;
        }
        public void setMin(TargetableFloat3 value)
        {
          this.min = value;
        }
        public TargetableFloat3 getMax()
        {
          return this.max;
        }
        public void setMax(TargetableFloat3 value)
        {
          this.max = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"min", "max"})
      public static class Linear
      {
        @XmlElement(defaultValue="0.0 0.0 0.0")
        protected TargetableFloat3 min;
        @XmlElement(defaultValue="0.0 0.0 0.0")
        protected TargetableFloat3 max;
        public TargetableFloat3 getMin()
        {
          return this.min;
        }
        public void setMin(TargetableFloat3 value)
        {
          this.min = value;
        }
        public TargetableFloat3 getMax()
        {
          return this.max;
        }
        public void setMax(TargetableFloat3 value)
        {
          this.max = value;
        }
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"value"})
    public static class Interpenetrate
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
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"value"})
    public static class Enabled
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
  @XmlType(name="", propOrder={"translateOrRotateOrExtra"})
  public static class RefAttachment
  {
    @XmlElements({@XmlElement(name="extra", type=Extra.class), @XmlElement(name="rotate", type=Rotate.class), @XmlElement(name="translate", type=TargetableFloat3.class)})
    protected List<Object> translateOrRotateOrExtra;
    @XmlAttribute(name="rigid_body")
    protected String rigidBody;
    public List<Object> getTranslateOrRotateOrExtra()
    {
      if (this.translateOrRotateOrExtra == null)
        this.translateOrRotateOrExtra = new ArrayList();
      return this.translateOrRotateOrExtra;
    }
    public String getRigidBody()
    {
      return this.rigidBody;
    }
    public void setRigidBody(String value)
    {
      this.rigidBody = value;
    }
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"translateOrRotateOrExtra"})
  public static class Attachment
  {
    @XmlElements({@XmlElement(name="extra", type=Extra.class), @XmlElement(name="translate", type=TargetableFloat3.class), @XmlElement(name="rotate", type=Rotate.class)})
    protected List<Object> translateOrRotateOrExtra;
    @XmlAttribute(name="rigid_body")
    protected String rigidBody;
    public List<Object> getTranslateOrRotateOrExtra()
    {
      if (this.translateOrRotateOrExtra == null)
        this.translateOrRotateOrExtra = new ArrayList();
      return this.translateOrRotateOrExtra;
    }
    public String getRigidBody()
    {
      return this.rigidBody;
    }
    public void setRigidBody(String value)
    {
      this.rigidBody = value;
    }
  }
}

