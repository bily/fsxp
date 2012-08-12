
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"radius", "extra"})
@XmlRootElement(name="sphere")
public class Sphere
{
  protected double radius;
  protected List<Extra> extra;
  public double getRadius()
  {
    return this.radius;
  }
  public void setRadius(double value)
  {
    this.radius = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

