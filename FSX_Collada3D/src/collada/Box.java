
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
@XmlType(name="", propOrder={"halfExtents", "extra"})
@XmlRootElement(name="box")
public class Box
{
  @XmlList
  @XmlElement(name="half_extents", type=Double.class)
  protected List<Double> halfExtents;
  protected List<Extra> extra;
  public List<Double> getHalfExtents()
  {
    if (this.halfExtents == null)
      this.halfExtents = new ArrayList();
    return this.halfExtents;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
}

