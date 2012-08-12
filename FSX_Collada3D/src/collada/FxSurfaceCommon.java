
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_surface_common", propOrder={"initAsNull", "initAsTarget", "initCube", "initVolume", "initPlanar", "initFrom", "format", "formatHint", "size", "viewportRatio", "mipLevels", "mipmapGenerate", "extra"})
public class FxSurfaceCommon
{
  @XmlElement(name="init_as_null")
  protected Object initAsNull;
  @XmlElement(name="init_as_target")
  protected Object initAsTarget;
  @XmlElement(name="init_cube")
  protected FxSurfaceInitCubeCommon initCube;
  @XmlElement(name="init_volume")
  protected FxSurfaceInitVolumeCommon initVolume;
  @XmlElement(name="init_planar")
  protected FxSurfaceInitPlanarCommon initPlanar;
  @XmlElement(name="init_from")
  protected List<FxSurfaceInitFromCommon> initFrom;
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String format;
  @XmlElement(name="format_hint")
  protected FxSurfaceFormatHintCommon formatHint;
  @XmlList
  @XmlElement(type=Long.class, defaultValue="0 0 0")
  protected List<Long> size;
  @XmlList
  @XmlElement(name="viewport_ratio", type=Double.class, defaultValue="1 1")
  protected List<Double> viewportRatio;
  @XmlElement(name="mip_levels", defaultValue="0")
  protected Long mipLevels;
  @XmlElement(name="mipmap_generate")
  protected Boolean mipmapGenerate;
  protected List<Extra> extra;
  @XmlAttribute(required=true)
  protected String type;
  public Object getInitAsNull()
  {
    return this.initAsNull;
  }
  public void setInitAsNull(Object value)
  {
    this.initAsNull = value;
  }
  public Object getInitAsTarget()
  {
    return this.initAsTarget;
  }
  public void setInitAsTarget(Object value)
  {
    this.initAsTarget = value;
  }
  public FxSurfaceInitCubeCommon getInitCube()
  {
    return this.initCube;
  }
  public void setInitCube(FxSurfaceInitCubeCommon value)
  {
    this.initCube = value;
  }
  public FxSurfaceInitVolumeCommon getInitVolume()
  {
    return this.initVolume;
  }
  public void setInitVolume(FxSurfaceInitVolumeCommon value)
  {
    this.initVolume = value;
  }
  public FxSurfaceInitPlanarCommon getInitPlanar()
  {
    return this.initPlanar;
  }
  public void setInitPlanar(FxSurfaceInitPlanarCommon value)
  {
    this.initPlanar = value;
  }
  public List<FxSurfaceInitFromCommon> getInitFrom()
  {
    if (this.initFrom == null)
      this.initFrom = new ArrayList();
    return this.initFrom;
  }
  public String getFormat()
  {
    return this.format;
  }
  public void setFormat(String value)
  {
    this.format = value;
  }
  public FxSurfaceFormatHintCommon getFormatHint()
  {
    return this.formatHint;
  }
  public void setFormatHint(FxSurfaceFormatHintCommon value)
  {
    this.formatHint = value;
  }
  public List<Long> getSize()
  {
    if (this.size == null)
      this.size = new ArrayList();
    return this.size;
  }
  public List<Double> getViewportRatio()
  {
    if (this.viewportRatio == null)
      this.viewportRatio = new ArrayList();
    return this.viewportRatio;
  }
  public Long getMipLevels()
  {
    return this.mipLevels;
  }
  public void setMipLevels(Long value)
  {
    this.mipLevels = value;
  }
  public Boolean isMipmapGenerate()
  {
    return this.mipmapGenerate;
  }
  public void setMipmapGenerate(Boolean value)
  {
    this.mipmapGenerate = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public String getType()
  {
    return this.type;
  }
  public void setType(String value)
  {
    this.type = value;
  }
}

