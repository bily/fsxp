
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"source", "vertices", "linesOrLinestripsOrPolygons", "extra"})
@XmlRootElement(name="convex_mesh")
public class ConvexMesh
{
  protected List<Source> source;
  protected Vertices vertices;
  @XmlElements({@javax.xml.bind.annotation.XmlElement(name="polylist", type=Polylist.class), @javax.xml.bind.annotation.XmlElement(name="trifans", type=Trifans.class), @javax.xml.bind.annotation.XmlElement(name="linestrips", type=Linestrips.class), @javax.xml.bind.annotation.XmlElement(name="polygons", type=Polygons.class), @javax.xml.bind.annotation.XmlElement(name="triangles", type=Triangles.class), @javax.xml.bind.annotation.XmlElement(name="tristrips", type=Tristrips.class), @javax.xml.bind.annotation.XmlElement(name="lines", type=Lines.class)})
  protected List<Object> linesOrLinestripsOrPolygons;
  protected List<Extra> extra;
  @XmlAttribute(name="convex_hull_of")
  protected String convexHullOf;
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
  public String getConvexHullOf()
  {
    return this.convexHullOf;
  }
  public void setConvexHullOf(String value)
  {
    this.convexHullOf = value;
  }
}

