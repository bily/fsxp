
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"bind", "bindVertexInput", "extra"})
@XmlRootElement(name="instance_material")
public class InstanceMaterial
{
  protected List<Bind> bind;
  @XmlElement(name="bind_vertex_input")
  protected List<BindVertexInput> bindVertexInput;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String name;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String symbol;
  @XmlAttribute(required=true)
  protected String target;
  public List<Bind> getBind()
  {
    if (this.bind == null)
      this.bind = new ArrayList();
    return this.bind;
  }
  public List<BindVertexInput> getBindVertexInput()
  {
    if (this.bindVertexInput == null)
      this.bindVertexInput = new ArrayList();
    return this.bindVertexInput;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
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
  public String getSymbol()
  {
    return this.symbol;
  }
  public void setSymbol(String value)
  {
    this.symbol = value;
  }
  public String getTarget()
  {
    return this.target;
  }
  public void setTarget(String value)
  {
    this.target = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="")
  public static class BindVertexInput
  {
    @XmlAttribute(name="input_semantic", required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String inputSemantic;
    @XmlAttribute(name="input_set")
    protected BigInteger inputSet;
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String semantic;
    public String getInputSemantic()
    {
      return this.inputSemantic;
    }
    public void setInputSemantic(String value)
    {
      this.inputSemantic = value;
    }
    public BigInteger getInputSet()
    {
      return this.inputSet;
    }
    public void setInputSet(BigInteger value)
    {
      this.inputSet = value;
    }
    public String getSemantic()
    {
      return this.semantic;
    }
    public void setSemantic(String value)
    {
      this.semantic = value;
    }
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="")
  public static class Bind
  {
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String semantic;
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String target;
    public String getSemantic()
    {
      return this.semantic;
    }
    public void setSemantic(String value)
    {
      this.semantic = value;
    }
    public String getTarget()
    {
      return this.target;
    }
    public void setTarget(String value)
    {
      this.target = value;
    }
  }
}

