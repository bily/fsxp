
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"height", "radius", "extra"})
@XmlRootElement(name="cylinder")
public class Cylinder
{
  protected double height;
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> radius;
  protected List<Extra> extra;
  public double getHeight()
  {
    return this.height;
  }
  public void setHeight(double value)
  {
    this.height = value;
  }
  public List<Double> getRadius()
  {
    if (this.radius == null)
      this.radius = new ArrayList();
    return this.radius;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

