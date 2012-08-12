
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "node", "evaluateScene", "extra"})
@XmlRootElement(name="visual_scene")
public class VisualScene
{
  protected Asset asset;
  @XmlElement(required=true)
  protected List<Node> node;
  @XmlElement(name="evaluate_scene")
  protected List<EvaluateScene> evaluateScene;
  protected List<Extra> extra;
  @XmlAttribute
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
  public List<Node> getNode()
  {
    if (this.node == null)
      this.node = new ArrayList();
    return this.node;
  }
  public List<EvaluateScene> getEvaluateScene()
  {
    if (this.evaluateScene == null)
      this.evaluateScene = new ArrayList();
    return this.evaluateScene;
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
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"render"})
  public static class EvaluateScene
  {
    @XmlElement(required=true)
    protected List<Render> render;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    public List<Render> getRender()
    {
      if (this.render == null)
        this.render = new ArrayList();
      return this.render;
    }
    public String getName()
    {
      return this.name;
    }
    public void setName(String value)
    {
      this.name = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"layer", "instanceEffect"})
    public static class Render
    {
      @XmlElementRef(name="layer", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class)
      protected List<JAXBElement<String>> layer;
      @XmlElement(name="instance_effect")
      protected InstanceEffect instanceEffect;
      @XmlAttribute(name="camera_node", required=true)
      protected String cameraNode;
      public List<JAXBElement<String>> getLayer()
      {
        if (this.layer == null)
          this.layer = new ArrayList();
        return this.layer;
      }
      public InstanceEffect getInstanceEffect()
      {
        return this.instanceEffect;
      }
      public void setInstanceEffect(InstanceEffect value)
      {
        this.instanceEffect = value;
      }
      public String getCameraNode()
      {
        return this.cameraNode;
      }
      public void setCameraNode(String value)
      {
        this.cameraNode = value;
      }
    }
  }
}

