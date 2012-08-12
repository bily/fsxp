
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
@XmlType(name="", propOrder={"height", "radius1", "radius2", "extra"})
@XmlRootElement(name="tapered_cylinder")
public class TaperedCylinder
{
  protected double height;
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> radius1;
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> radius2;
  protected List<Extra> extra;
  public double getHeight()
  {
    return this.height;
  }
  public void setHeight(double value)
  {
    this.height = value;
  }
  public List<Double> getRadius1()
  {
    if (this.radius1 == null)
      this.radius1 = new ArrayList();
    return this.radius1;
  }
  public List<Double> getRadius2()
  {
    if (this.radius2 == null)
      this.radius2 = new ArrayList();
    return this.radius2;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

