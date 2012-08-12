
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "annotate", "image", "newparam", "fxProfileAbstract", "extra"})
@XmlRootElement(name="effect")
public class Effect
{
  protected Asset asset;
  protected List<FxAnnotateCommon> annotate;
  protected List<Image> image;
  protected List<FxNewparamCommon> newparam;
  @XmlElementRef(name="fx_profile_abstract", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class)
  protected List<JAXBElement<?>> fxProfileAbstract;
  protected List<Extra> extra;
  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public List<FxAnnotateCommon> getAnnotate()
  {
    if (this.annotate == null)
      this.annotate = new ArrayList();
    return this.annotate;
  }
  public List<Image> getImage()
  {
    if (this.image == null)
      this.image = new ArrayList();
    return this.image;
  }
  public List<FxNewparamCommon> getNewparam()
  {
    if (this.newparam == null)
      this.newparam = new ArrayList();
    return this.newparam;
  }
  public List<JAXBElement<?>> getFxProfileAbstract()
  {
    if (this.fxProfileAbstract == null)
      this.fxProfileAbstract = new ArrayList();
    return this.fxProfileAbstract;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public String getId()
  {
    return this.id;
  }
  public void setId(String value)
  {
    this.id = value;
  }
  public String getName()
  {
    return this.name;
  }
  public void setName(String value)
  {
    this.name = value;
  }
}

