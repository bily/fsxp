
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="common_newparam_type", propOrder={"semantic", "_float", "float2", "float3", "float4", "surface", "sampler2D"})
public class CommonNewparamType
{
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String semantic;
  @XmlElement(name="float")
  protected Double _float;
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> float2;
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> float3;
  @XmlList
  @XmlElement(type=Double.class)
  protected List<Double> float4;
  protected FxSurfaceCommon surface;
  protected FxSampler2DCommon sampler2D;
  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String sid;
  public String getSemantic()
  {
    return this.semantic;
  }
  public void setSemantic(String value)
  {
    this.semantic = value;
  }
  public Double getFloat()
  {
    return this._float;
  }
  public void setFloat(Double value)
  {
    this._float = value;
  }
  public List<Double> getFloat2()
  {
    if (this.float2 == null)
      this.float2 = new ArrayList();
    return this.float2;
  }
  public List<Double> getFloat3()
  {
    if (this.float3 == null)
      this.float3 = new ArrayList();
    return this.float3;
  }
  public List<Double> getFloat4()
  {
    if (this.float4 == null)
      this.float4 = new ArrayList();
    return this.float4;
  }
  public FxSurfaceCommon getSurface()
  {
    return this.surface;
  }
  public void setSurface(FxSurfaceCommon value)
  {
    this.surface = value;
  }
  public FxSampler2DCommon getSampler2D()
  {
    return this.sampler2D;
  }
  public void setSampler2D(FxSampler2DCommon value)
  {
    this.sampler2D = value;
  }
  public String getSid()
  {
    return this.sid;
  }
  public void setSid(String value)
  {
    this.sid = value;
  }
}

