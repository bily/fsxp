
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
@XmlRootElement(name="light")
public class Light
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
  @XmlType(name="", propOrder={"ambient", "directional", "point", "spot"})
  public static class TechniqueCommon
  {
    protected Ambient ambient;
    protected Directional directional;
    protected Point point;
    protected Spot spot;
    public Ambient getAmbient()
    {
      return this.ambient;
    }
    public void setAmbient(Ambient value)
    {
      this.ambient = value;
    }
    public Directional getDirectional()
    {
      return this.directional;
    }
    public void setDirectional(Directional value)
    {
      this.directional = value;
    }
    public Point getPoint()
    {
      return this.point;
    }
    public void setPoint(Point value)
    {
      this.point = value;
    }
    public Spot getSpot()
    {
      return this.spot;
    }
    public void setSpot(Spot value)
    {
      this.spot = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"color", "constantAttenuation", "linearAttenuation", "quadraticAttenuation", "falloffAngle", "falloffExponent"})
    public static class Spot
    {
      @XmlElement(required=true)
      protected TargetableFloat3 color;
      @XmlElement(name="constant_attenuation", defaultValue="1.0")
      protected TargetableFloat constantAttenuation;
      @XmlElement(name="linear_attenuation", defaultValue="0.0")
      protected TargetableFloat linearAttenuation;
      @XmlElement(name="quadratic_attenuation", defaultValue="0.0")
      protected TargetableFloat quadraticAttenuation;
      @XmlElement(name="falloff_angle", defaultValue="180.0")
      protected TargetableFloat falloffAngle;
      @XmlElement(name="falloff_exponent", defaultValue="0.0")
      protected TargetableFloat falloffExponent;
      public TargetableFloat3 getColor()
      {
        return this.color;
      }
      public void setColor(TargetableFloat3 value)
      {
        this.color = value;
      }
      public TargetableFloat getConstantAttenuation()
      {
        return this.constantAttenuation;
      }
      public void setConstantAttenuation(TargetableFloat value)
      {
        this.constantAttenuation = value;
      }
      public TargetableFloat getLinearAttenuation()
      {
        return this.linearAttenuation;
      }
      public void setLinearAttenuation(TargetableFloat value)
      {
        this.linearAttenuation = value;
      }
      public TargetableFloat getQuadraticAttenuation()
      {
        return this.quadraticAttenuation;
      }
      public void setQuadraticAttenuation(TargetableFloat value)
      {
        this.quadraticAttenuation = value;
      }
      public TargetableFloat getFalloffAngle()
      {
        return this.falloffAngle;
      }
      public void setFalloffAngle(TargetableFloat value)
      {
        this.falloffAngle = value;
      }
      public TargetableFloat getFalloffExponent()
      {
        return this.falloffExponent;
      }
      public void setFalloffExponent(TargetableFloat value)
      {
        this.falloffExponent = value;
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"color", "constantAttenuation", "linearAttenuation", "quadraticAttenuation"})
    public static class Point
    {
      @XmlElement(required=true)
      protected TargetableFloat3 color;
      @XmlElement(name="constant_attenuation", defaultValue="1.0")
      protected TargetableFloat constantAttenuation;
      @XmlElement(name="linear_attenuation", defaultValue="0.0")
      protected TargetableFloat linearAttenuation;
      @XmlElement(name="quadratic_attenuation", defaultValue="0.0")
      protected TargetableFloat quadraticAttenuation;
      public TargetableFloat3 getColor()
      {
        return this.color;
      }
      public void setColor(TargetableFloat3 value)
      {
        this.color = value;
      }
      public TargetableFloat getConstantAttenuation()
      {
        return this.constantAttenuation;
      }
      public void setConstantAttenuation(TargetableFloat value)
      {
        this.constantAttenuation = value;
      }
      public TargetableFloat getLinearAttenuation()
      {
        return this.linearAttenuation;
      }
      public void setLinearAttenuation(TargetableFloat value)
      {
        this.linearAttenuation = value;
      }
      public TargetableFloat getQuadraticAttenuation()
      {
        return this.quadraticAttenuation;
      }
      public void setQuadraticAttenuation(TargetableFloat value)
      {
        this.quadraticAttenuation = value;
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"color"})
    public static class Directional
    {
      @XmlElement(required=true)
      protected TargetableFloat3 color;
      public TargetableFloat3 getColor()
      {
        return this.color;
      }
      public void setColor(TargetableFloat3 value)
      {
        this.color = value;
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"color"})
    public static class Ambient
    {
      @XmlElement(required=true)
      protected TargetableFloat3 color;
      public TargetableFloat3 getColor()
      {
        return this.color;
      }
      public void setColor(TargetableFloat3 value)
      {
        this.color = value;
      }
    }
  }
}

