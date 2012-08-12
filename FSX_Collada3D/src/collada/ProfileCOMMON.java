
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "imageOrNewparam", "technique", "extra"})
public class ProfileCOMMON
{
  protected Asset asset;
  @XmlElements({@XmlElement(name="newparam", type=CommonNewparamType.class), @XmlElement(name="image", type=Image.class)})
  protected List<Object> imageOrNewparam;
  @XmlElement(required=true)
  protected Technique technique;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public List<Object> getImageOrNewparam()
  {
    if (this.imageOrNewparam == null)
      this.imageOrNewparam = new ArrayList();
    return this.imageOrNewparam;
  }
  public Technique getTechnique()
  {
    return this.technique;
  }
  public void setTechnique(Technique value)
  {
    this.technique = value;
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
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"asset", "imageOrNewparam", "constant", "lambert", "phong", "blinn", "extra"})
  public static class Technique
  {
    protected Asset asset;
    @XmlElements({@XmlElement(name="newparam", type=CommonNewparamType.class), @XmlElement(name="image", type=Image.class)})
    protected List<Object> imageOrNewparam;
    protected Constant constant;
    protected Lambert lambert;
    protected Phong phong;
    protected Blinn blinn;
    protected List<Extra> extra;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    public Asset getAsset()
    {
      return this.asset;
    }
    public void setAsset(Asset value)
    {
      this.asset = value;
    }
    public List<Object> getImageOrNewparam()
    {
      if (this.imageOrNewparam == null)
        this.imageOrNewparam = new ArrayList();
      return this.imageOrNewparam;
    }
    public Constant getConstant()
    {
      return this.constant;
    }
    public void setConstant(Constant value)
    {
      this.constant = value;
    }
    public Lambert getLambert()
    {
      return this.lambert;
    }
    public void setLambert(Lambert value)
    {
      this.lambert = value;
    }
    public Phong getPhong()
    {
      return this.phong;
    }
    public void setPhong(Phong value)
    {
      this.phong = value;
    }
    public Blinn getBlinn()
    {
      return this.blinn;
    }
    public void setBlinn(Blinn value)
    {
      this.blinn = value;
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
    public String getSid()
    {
      return this.sid;
    }
    public void setSid(String value)
    {
      this.sid = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"emission", "ambient", "diffuse", "specular", "shininess", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction"})
    public static class Phong
    {
      protected CommonColorOrTextureType emission;
      protected CommonColorOrTextureType ambient;
      protected CommonColorOrTextureType diffuse;
      protected CommonColorOrTextureType specular;
      protected CommonFloatOrParamType shininess;
      protected CommonColorOrTextureType reflective;
      protected CommonFloatOrParamType reflectivity;
      protected CommonTransparentType transparent;
      protected CommonFloatOrParamType transparency;
      @XmlElement(name="index_of_refraction")
      protected CommonFloatOrParamType indexOfRefraction;
      public CommonColorOrTextureType getEmission()
      {
        return this.emission;
      }
      public void setEmission(CommonColorOrTextureType value)
      {
        this.emission = value;
      }
      public CommonColorOrTextureType getAmbient()
      {
        return this.ambient;
      }
      public void setAmbient(CommonColorOrTextureType value)
      {
        this.ambient = value;
      }
      public CommonColorOrTextureType getDiffuse()
      {
        return this.diffuse;
      }
      public void setDiffuse(CommonColorOrTextureType value)
      {
        this.diffuse = value;
      }
      public CommonColorOrTextureType getSpecular()
      {
        return this.specular;
      }
      public void setSpecular(CommonColorOrTextureType value)
      {
        this.specular = value;
      }
      public CommonFloatOrParamType getShininess()
      {
        return this.shininess;
      }
      public void setShininess(CommonFloatOrParamType value)
      {
        this.shininess = value;
      }
      public CommonColorOrTextureType getReflective()
      {
        return this.reflective;
      }
      public void setReflective(CommonColorOrTextureType value)
      {
        this.reflective = value;
      }
      public CommonFloatOrParamType getReflectivity()
      {
        return this.reflectivity;
      }
      public void setReflectivity(CommonFloatOrParamType value)
      {
        this.reflectivity = value;
      }
      public CommonTransparentType getTransparent()
      {
        return this.transparent;
      }
      public void setTransparent(CommonTransparentType value)
      {
        this.transparent = value;
      }
      public CommonFloatOrParamType getTransparency()
      {
        return this.transparency;
      }
      public void setTransparency(CommonFloatOrParamType value)
      {
        this.transparency = value;
      }
      public CommonFloatOrParamType getIndexOfRefraction()
      {
        return this.indexOfRefraction;
      }
      public void setIndexOfRefraction(CommonFloatOrParamType value)
      {
        this.indexOfRefraction = value;
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"emission", "ambient", "diffuse", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction"})
    public static class Lambert
    {
      protected CommonColorOrTextureType emission;
      protected CommonColorOrTextureType ambient;
      protected CommonColorOrTextureType diffuse;
      protected CommonColorOrTextureType reflective;
      protected CommonFloatOrParamType reflectivity;
      protected CommonTransparentType transparent;
      protected CommonFloatOrParamType transparency;
      @XmlElement(name="index_of_refraction")
      protected CommonFloatOrParamType indexOfRefraction;
      public CommonColorOrTextureType getEmission()
      {
        return this.emission;
      }
      public void setEmission(CommonColorOrTextureType value)
      {
        this.emission = value;
      }
      public CommonColorOrTextureType getAmbient()
      {
        return this.ambient;
      }
      public void setAmbient(CommonColorOrTextureType value)
      {
        this.ambient = value;
      }
      public CommonColorOrTextureType getDiffuse()
      {
        return this.diffuse;
      }
      public void setDiffuse(CommonColorOrTextureType value)
      {
        this.diffuse = value;
      }
      public CommonColorOrTextureType getReflective()
      {
        return this.reflective;
      }
      public void setReflective(CommonColorOrTextureType value)
      {
        this.reflective = value;
      }
      public CommonFloatOrParamType getReflectivity()
      {
        return this.reflectivity;
      }
      public void setReflectivity(CommonFloatOrParamType value)
      {
        this.reflectivity = value;
      }
      public CommonTransparentType getTransparent()
      {
        return this.transparent;
      }
      public void setTransparent(CommonTransparentType value)
      {
        this.transparent = value;
      }
      public CommonFloatOrParamType getTransparency()
      {
        return this.transparency;
      }
      public void setTransparency(CommonFloatOrParamType value)
      {
        this.transparency = value;
      }
      public CommonFloatOrParamType getIndexOfRefraction()
      {
        return this.indexOfRefraction;
      }
      public void setIndexOfRefraction(CommonFloatOrParamType value)
      {
        this.indexOfRefraction = value;
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"emission", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction"})
    public static class Constant
    {
      protected CommonColorOrTextureType emission;
      protected CommonColorOrTextureType reflective;
      protected CommonFloatOrParamType reflectivity;
      protected CommonTransparentType transparent;
      protected CommonFloatOrParamType transparency;
      @XmlElement(name="index_of_refraction")
      protected CommonFloatOrParamType indexOfRefraction;
      public CommonColorOrTextureType getEmission()
      {
        return this.emission;
      }
      public void setEmission(CommonColorOrTextureType value)
      {
        this.emission = value;
      }
      public CommonColorOrTextureType getReflective()
      {
        return this.reflective;
      }
      public void setReflective(CommonColorOrTextureType value)
      {
        this.reflective = value;
      }
      public CommonFloatOrParamType getReflectivity()
      {
        return this.reflectivity;
      }
      public void setReflectivity(CommonFloatOrParamType value)
      {
        this.reflectivity = value;
      }
      public CommonTransparentType getTransparent()
      {
        return this.transparent;
      }
      public void setTransparent(CommonTransparentType value)
      {
        this.transparent = value;
      }
      public CommonFloatOrParamType getTransparency()
      {
        return this.transparency;
      }
      public void setTransparency(CommonFloatOrParamType value)
      {
        this.transparency = value;
      }
      public CommonFloatOrParamType getIndexOfRefraction()
      {
        return this.indexOfRefraction;
      }
      public void setIndexOfRefraction(CommonFloatOrParamType value)
      {
        this.indexOfRefraction = value;
      }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"emission", "ambient", "diffuse", "specular", "shininess", "reflective", "reflectivity", "transparent", "transparency", "indexOfRefraction"})
    public static class Blinn
    {
      protected CommonColorOrTextureType emission;
      protected CommonColorOrTextureType ambient;
      protected CommonColorOrTextureType diffuse;
      protected CommonColorOrTextureType specular;
      protected CommonFloatOrParamType shininess;
      protected CommonColorOrTextureType reflective;
      protected CommonFloatOrParamType reflectivity;
      protected CommonTransparentType transparent;
      protected CommonFloatOrParamType transparency;
      @XmlElement(name="index_of_refraction")
      protected CommonFloatOrParamType indexOfRefraction;
      public CommonColorOrTextureType getEmission()
      {
        return this.emission;
      }
      public void setEmission(CommonColorOrTextureType value)
      {
        this.emission = value;
      }
      public CommonColorOrTextureType getAmbient()
      {
        return this.ambient;
      }
      public void setAmbient(CommonColorOrTextureType value)
      {
        this.ambient = value;
      }
      public CommonColorOrTextureType getDiffuse()
      {
        return this.diffuse;
      }
      public void setDiffuse(CommonColorOrTextureType value)
      {
        this.diffuse = value;
      }
      public CommonColorOrTextureType getSpecular()
      {
        return this.specular;
      }
      public void setSpecular(CommonColorOrTextureType value)
      {
        this.specular = value;
      }
      public CommonFloatOrParamType getShininess()
      {
        return this.shininess;
      }
      public void setShininess(CommonFloatOrParamType value)
      {
        this.shininess = value;
      }
      public CommonColorOrTextureType getReflective()
      {
        return this.reflective;
      }
      public void setReflective(CommonColorOrTextureType value)
      {
        this.reflective = value;
      }
      public CommonFloatOrParamType getReflectivity()
      {
        return this.reflectivity;
      }
      public void setReflectivity(CommonFloatOrParamType value)
      {
        this.reflectivity = value;
      }
      public CommonTransparentType getTransparent()
      {
        return this.transparent;
      }
      public void setTransparent(CommonTransparentType value)
      {
        this.transparent = value;
      }
      public CommonFloatOrParamType getTransparency()
      {
        return this.transparency;
      }
      public void setTransparency(CommonFloatOrParamType value)
      {
        this.transparency = value;
      }
      public CommonFloatOrParamType getIndexOfRefraction()
      {
        return this.indexOfRefraction;
      }
      public void setIndexOfRefraction(CommonFloatOrParamType value)
      {
        this.indexOfRefraction = value;
      }
    }
  }
}

