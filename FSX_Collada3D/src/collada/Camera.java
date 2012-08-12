
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "optics", "imager", "extra"})
@XmlRootElement(name="camera")
public class Camera
{
  protected Asset asset;
  @XmlElement(required=true)
  protected Optics optics;
  protected Imager imager;
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
  public Optics getOptics()
  {
    return this.optics;
  }
  public void setOptics(Optics value)
  {
    this.optics = value;
  }
  public Imager getImager()
  {
    return this.imager;
  }
  public void setImager(Imager value)
  {
    this.imager = value;
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
  @XmlType(name="", propOrder={"techniqueCommon", "technique", "extra"})
  public static class Optics
  {
    @XmlElement(name="technique_common", required=true)
    protected TechniqueCommon techniqueCommon;
    protected List<Technique> technique;
    protected List<Extra> extra;
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
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"orthographic", "perspective"})
    public static class TechniqueCommon
    {
      protected Orthographic orthographic;
      protected Perspective perspective;
      public Orthographic getOrthographic()
      {
        return this.orthographic;
      }
      public void setOrthographic(Orthographic value)
      {
        this.orthographic = value;
      }
      public Perspective getPerspective()
      {
        return this.perspective;
      }
      public void setPerspective(Perspective value)
      {
        this.perspective = value;
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"content"})
      public static class Perspective
      {
        @XmlElementRefs({@javax.xml.bind.annotation.XmlElementRef(name="zfar", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="znear", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="aspect_ratio", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="yfov", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="xfov", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class)})
        protected List<JAXBElement<TargetableFloat>> content;
        public List<JAXBElement<TargetableFloat>> getContent()
        {
          if (this.content == null)
            this.content = new ArrayList();
          return this.content;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"content"})
      public static class Orthographic
      {
        @XmlElementRefs({@javax.xml.bind.annotation.XmlElementRef(name="zfar", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="xmag", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="aspect_ratio", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="znear", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="ymag", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class)})
        protected List<JAXBElement<TargetableFloat>> content;
        public List<JAXBElement<TargetableFloat>> getContent()
        {
          if (this.content == null)
            this.content = new ArrayList();
          return this.content;
        }
      }
    }
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"technique", "extra"})
  public static class Imager
  {
    @XmlElement(required=true)
    protected List<Technique> technique;
    protected List<Extra> extra;
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
  }
}

