
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
@XmlType(name="", propOrder={"size"})
@XmlRootElement(name="ellipsoid")
public class Ellipsoid
{
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> size;
  public List<Double> getSize()
  {
    if (this.size == null)
      this.size = new ArrayList();
    return this.size;
  }
}

