
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"source", "vertices", "linesOrLinestripsOrPolygons", "extra"})
@XmlRootElement(name="mesh")
public class Mesh
{
  @XmlElement(required=true)
  protected List<Source> source;
  @XmlElement(required=true)
  protected Vertices vertices;
  @XmlElements({@XmlElement(name="linestrips", type=Linestrips.class), @XmlElement(name="trifans", type=Trifans.class), @XmlElement(name="polylist", type=Polylist.class), @XmlElement(name="lines", type=Lines.class), @XmlElement(name="polygons", type=Polygons.class), @XmlElement(name="triangles", type=Triangles.class), @XmlElement(name="tristrips", type=Tristrips.class)})
  protected List<Object> linesOrLinestripsOrPolygons;
  protected List<Extra> extra;
  public List<Source> getSource()
  {
    if (this.source == null)
      this.source = new ArrayList();
    return this.source;
  }
  public Vertices getVertices()
  {
    return this.vertices;
  }
  public void setVertices(Vertices value)
  {
    this.vertices = value;
  }
  public List<Object> getLinesOrLinestripsOrPolygons()
  {
    if (this.linesOrLinestripsOrPolygons == null)
      this.linesOrLinestripsOrPolygons = new ArrayList();
    return this.linesOrLinestripsOrPolygons;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

