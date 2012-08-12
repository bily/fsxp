
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"source", "controlVertices", "extra"})
@XmlRootElement(name="spline")
public class Spline
{
  @XmlElement(required=true)
  protected List<Source> source;
  @XmlElement(name="control_vertices", required=true)
  protected ControlVertices controlVertices;
  protected List<Extra> extra;
  @XmlAttribute
  protected Boolean closed;
  public List<Source> getSource()
  {
    if (this.source == null)
      this.source = new ArrayList();
    return this.source;
  }
  public ControlVertices getControlVertices()
  {
    return this.controlVertices;
  }
  public void setControlVertices(ControlVertices value)
  {
    this.controlVertices = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public boolean isClosed()
  {
    if (this.closed == null)
      return false;
    return this.closed.booleanValue();
  }
  public void setClosed(Boolean value)
  {
    this.closed = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"input", "extra"})
  public static class ControlVertices
  {
    @XmlElement(required=true)
    protected List<InputLocal> input;
    protected List<Extra> extra;
    public List<InputLocal> getInput()
    {
      if (this.input == null)
        this.input = new ArrayList();
      return this.input;
    }
    public List<Extra> getExtra()
    {
      if (this.extra == null)
        this.extra = new ArrayList();
      return this.extra;
    }
  }
}

