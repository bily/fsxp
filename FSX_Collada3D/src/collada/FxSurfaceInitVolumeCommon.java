
package collada;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_surface_init_volume_common", propOrder={"all", "primary"})
public class FxSurfaceInitVolumeCommon
{
  protected All all;
  protected Primary primary;
  public All getAll()
  {
    return this.all;
  }
  public void setAll(All value)
  {
    this.all = value;
  }
  public Primary getPrimary()
  {
    return this.primary;
  }
  public void setPrimary(Primary value)
  {
    this.primary = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="")
  public static class Primary
  {
    @XmlAttribute(required=true)
    @XmlIDREF
    protected Object ref;
    public Object getRef()
    {
      return this.ref;
    }
    public void setRef(Object value)
    {
      this.ref = value;
    }
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="")
  public static class All
  {
    @XmlAttribute(required=true)
    @XmlIDREF
    protected Object ref;
    public Object getRef()
    {
      return this.ref;
    }
    public void setRef(Object value)
    {
      this.ref = value;
    }
  }
}

