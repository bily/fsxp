
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
@XmlType(name="", propOrder={"equation", "extra"})
@XmlRootElement(name="plane")
public class Plane
{
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> equation;
  protected List<Extra> extra;
  public List<Double> getEquation()
  {
    if (this.equation == null)
      this.equation = new ArrayList();
    return this.equation;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

