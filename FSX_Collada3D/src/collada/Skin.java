
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"bindShapeMatrix", "ctSourceSkinElt", "joints", "vertexWeights", "extra"})
@XmlRootElement(name="skin")
public class Skin
{
  @XmlList
  @XmlElement(name="bind_shape_matrix", type=Double.class)
  protected List<Double> bindShapeMatrix;
  @XmlElement(name="source", required=true)
  protected List<Source> ctSourceSkinElt;
  @XmlElement(required=true)
  protected Joints joints;
  @XmlElement(name="vertex_weights", required=true)
  protected VertexWeights vertexWeights;
  protected List<Extra> extra;
  @XmlAttribute(name="source", required=true)
  protected String uriBaseMeshRef;
  public List<Double> getBindShapeMatrix()
  {
    if (this.bindShapeMatrix == null)
      this.bindShapeMatrix = new ArrayList();
    return this.bindShapeMatrix;
  }
  public List<Source> getCtSourceSkinElt()
  {
    if (this.ctSourceSkinElt == null)
      this.ctSourceSkinElt = new ArrayList();
    return this.ctSourceSkinElt;
  }
  public Joints getJoints()
  {
    return this.joints;
  }
  public void setJoints(Joints value)
  {
    this.joints = value;
  }
  public VertexWeights getVertexWeights()
  {
    return this.vertexWeights;
  }
  public void setVertexWeights(VertexWeights value)
  {
    this.vertexWeights = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public String getUriBaseMeshRef()
  {
    return this.uriBaseMeshRef;
  }
  public void setUriBaseMeshRef(String value)
  {
    this.uriBaseMeshRef = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"input", "vcount", "v", "extra"})
  public static class VertexWeights
  {
    @XmlElement(required=true)
    protected List<InputLocalOffset> input;
    @XmlList
    protected List<BigInteger> vcount;
    @XmlList
    @XmlElement(type=Long.class)
    protected List<Long> v;
    protected List<Extra> extra;
    @XmlAttribute(required=true)
    protected BigInteger count;
    public List<InputLocalOffset> getInput()
    {
      if (this.input == null)
        this.input = new ArrayList();
      return this.input;
    }
    public List<BigInteger> getVcount()
    {
      if (this.vcount == null)
        this.vcount = new ArrayList();
      return this.vcount;
    }
    public List<Long> getV()
    {
      if (this.v == null)
        this.v = new ArrayList();
      return this.v;
    }
    public List<Extra> getExtra()
    {
      if (this.extra == null)
        this.extra = new ArrayList();
      return this.extra;
    }
    public BigInteger getCount()
    {
      return this.count;
    }
    public void setCount(BigInteger value)
    {
      this.count = value;
    }
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"input", "extra"})
  public static class Joints
  {
    @XmlElement(required=true)
    protected List<InputLocal> input;
    protected List<Extra> extra;
    public List<InputLocal> getInput()
    {
      if (this.input == null)
        this.input = new ArrayList();
      return this.input;
    }
    public List<Extra> getExtra()
    {
      if (this.extra == null)
        this.extra = new ArrayList();
      return this.extra;
    }
  }
}

