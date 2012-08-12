
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="fx_surface_init_cube_common", propOrder={"all", "primary", "face"})
public class FxSurfaceInitCubeCommon
{
  protected All all;
  protected Primary primary;
  protected List<Face> face;
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
  public List<Face> getFace()
  {
    if (this.face == null)
      this.face = new ArrayList();
    return this.face;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"order"})
  public static class Primary
  {
    protected List<FxSurfaceFaceEnum> order;
    @XmlAttribute(required=true)
    @XmlIDREF
    protected Object ref;
    public List<FxSurfaceFaceEnum> getOrder()
    {
      if (this.order == null)
        this.order = new ArrayList();
      return this.order;
    }
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
  public static class Face
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

