
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "lookatOrMatrixOrRotate", "instanceCamera", "instanceController", "instanceGeometry", "instanceLight", "instanceNode", "node", "extra"})
@XmlRootElement(name="node")
public class Node
{
  protected Asset asset;
  @XmlElementRefs({@javax.xml.bind.annotation.XmlElementRef(name="translate", namespace="http://www.collada.org/2005/11/COLLADASchema", type=javax.xml.bind.JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="matrix", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Matrix.class), @javax.xml.bind.annotation.XmlElementRef(name="rotate", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Rotate.class), @javax.xml.bind.annotation.XmlElementRef(name="skew", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Skew.class), @javax.xml.bind.annotation.XmlElementRef(name="lookat", namespace="http://www.collada.org/2005/11/COLLADASchema", type=Lookat.class), @javax.xml.bind.annotation.XmlElementRef(name="scale", namespace="http://www.collada.org/2005/11/COLLADASchema", type=javax.xml.bind.JAXBElement.class)})
  protected List<Object> lookatOrMatrixOrRotate;
  @XmlElement(name="instance_camera")
  protected List<InstanceWithExtra> instanceCamera;
  @XmlElement(name="instance_controller")
  protected List<InstanceController> instanceController;
  @XmlElement(name="instance_geometry")
  protected List<InstanceGeometry> instanceGeometry;
  @XmlElement(name="instance_light")
  protected List<InstanceWithExtra> instanceLight;
  @XmlElement(name="instance_node")
  protected List<InstanceWithExtra> instanceNode;
  protected List<Node> node;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  protected List<String> layer;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  @XmlAttribute
  protected NodeType type;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public List<Object> getLookatOrMatrixOrRotate()
  {
    if (this.lookatOrMatrixOrRotate == null)
      this.lookatOrMatrixOrRotate = new ArrayList();
    return this.lookatOrMatrixOrRotate;
  }
  public List<InstanceWithExtra> getInstanceCamera()
  {
    if (this.instanceCamera == null)
      this.instanceCamera = new ArrayList();
    return this.instanceCamera;
  }
  public List<InstanceController> getInstanceController()
  {
    if (this.instanceController == null)
      this.instanceController = new ArrayList();
    return this.instanceController;
  }
  public List<InstanceGeometry> getInstanceGeometry()
  {
    if (this.instanceGeometry == null)
      this.instanceGeometry = new ArrayList();
    return this.instanceGeometry;
  }
  public List<InstanceWithExtra> getInstanceLight()
  {
    if (this.instanceLight == null)
      this.instanceLight = new ArrayList();
    return this.instanceLight;
  }
  public List<InstanceWithExtra> getInstanceNode()
  {
    if (this.instanceNode == null)
      this.instanceNode = new ArrayList();
    return this.instanceNode;
  }
  public List<Node> getNode()
  {
    if (this.node == null)
      this.node = new ArrayList();
    return this.node;
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
  public List<String> getLayer()
  {
    if (this.layer == null)
      this.layer = new ArrayList();
    return this.layer;
  }
  public String getName()
  {
    return this.name;
  }
  public void setName(String value)
  {
    this.name = value;
  }
  public String getSid()
  {
    return this.sid;
  }
  public void setSid(String value)
  {
    this.sid = value;
  }
  public NodeType getType()
  {
    if (this.type == null)
      return NodeType.NODE;
    return this.type;
  }
  public void setType(NodeType value)
  {
    this.type = value;
  }
}

