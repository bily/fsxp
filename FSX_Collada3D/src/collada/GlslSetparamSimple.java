
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
@XmlType(name="glsl_setparam_simple", propOrder={"annotate", "bool", "bool2", "bool3", "bool4", "_float", "float2", "float3", "float4", "float2X2", "float3X3", "float4X4", "_int", "int2", "int3", "int4", "surface", "sampler1D", "sampler2D", "sampler3D", "samplerCUBE", "samplerRECT", "samplerDEPTH", "_enum"})
public class GlslSetparamSimple
{
  protected List<FxAnnotateCommon> annotate;
  protected Boolean bool;
  @XmlList
  @XmlElement(type=Boolean.class)
  protected List<Boolean> bool2;
  @XmlList
  @XmlElement(type=Boolean.class)
  protected List<Boolean> bool3;
  @XmlList
  @XmlElement(type=Boolean.class)
  protected List<Boolean> bool4;
  @XmlElement(name="float")
  protected Float _float;
  @XmlList
  @XmlElement(type=Float.class)
  protected List<Float> float2;
  @XmlList
  @XmlElement(type=Float.class)
  protected List<Float> float3;
  @XmlList
  @XmlElement(type=Float.class)
  protected List<Float> float4;
  @XmlList
  @XmlElement(name="float2x2", type=Float.class)
  protected List<Float> float2X2;
  @XmlList
  @XmlElement(name="float3x3", type=Float.class)
  protected List<Float> float3X3;
  @XmlList
  @XmlElement(name="float4x4", type=Float.class)
  protected List<Float> float4X4;
  @XmlElement(name="int")
  protected Integer _int;
  @XmlList
  @XmlElement(type=Integer.class)
  protected List<Integer> int2;
  @XmlList
  @XmlElement(type=Integer.class)
  protected List<Integer> int3;
  @XmlList
  @XmlElement(type=Integer.class)
  protected List<Integer> int4;
  protected GlslSurfaceType surface;
  protected GlSampler1D sampler1D;
  protected GlSampler2D sampler2D;
  protected GlSampler3D sampler3D;
  protected GlSamplerCUBE samplerCUBE;
  protected GlSamplerRECT samplerRECT;
  protected GlSamplerDEPTH samplerDEPTH;
  @XmlElement(name="enum")
  protected String _enum;
  @XmlAttribute(required=true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String ref;
  public List<FxAnnotateCommon> getAnnotate()
  {
    if (this.annotate == null)
      this.annotate = new ArrayList();
    return this.annotate;
  }
  public Boolean isBool()
  {
    return this.bool;
  }
  public void setBool(Boolean value)
  {
    this.bool = value;
  }
  public List<Boolean> getBool2()
  {
    if (this.bool2 == null)
      this.bool2 = new ArrayList();
    return this.bool2;
  }
  public List<Boolean> getBool3()
  {
    if (this.bool3 == null)
      this.bool3 = new ArrayList();
    return this.bool3;
  }
  public List<Boolean> getBool4()
  {
    if (this.bool4 == null)
      this.bool4 = new ArrayList();
    return this.bool4;
  }
  public Float getFloat()
  {
    return this._float;
  }
  public void setFloat(Float value)
  {
    this._float = value;
  }
  public List<Float> getFloat2()
  {
    if (this.float2 == null)
      this.float2 = new ArrayList();
    return this.float2;
  }
  public List<Float> getFloat3()
  {
    if (this.float3 == null)
      this.float3 = new ArrayList();
    return this.float3;
  }
  public List<Float> getFloat4()
  {
    if (this.float4 == null)
      this.float4 = new ArrayList();
    return this.float4;
  }
  public List<Float> getFloat2X2()
  {
    if (this.float2X2 == null)
      this.float2X2 = new ArrayList();
    return this.float2X2;
  }
  public List<Float> getFloat3X3()
  {
    if (this.float3X3 == null)
      this.float3X3 = new ArrayList();
    return this.float3X3;
  }
  public List<Float> getFloat4X4()
  {
    if (this.float4X4 == null)
      this.float4X4 = new ArrayList();
    return this.float4X4;
  }
  public Integer getInt()
  {
    return this._int;
  }
  public void setInt(Integer value)
  {
    this._int = value;
  }
  public List<Integer> getInt2()
  {
    if (this.int2 == null)
      this.int2 = new ArrayList();
    return this.int2;
  }
  public List<Integer> getInt3()
  {
    if (this.int3 == null)
      this.int3 = new ArrayList();
    return this.int3;
  }
  public List<Integer> getInt4()
  {
    if (this.int4 == null)
      this.int4 = new ArrayList();
    return this.int4;
  }
  public GlslSurfaceType getSurface()
  {
    return this.surface;
  }
  public void setSurface(GlslSurfaceType value)
  {
    this.surface = value;
  }
  public GlSampler1D getSampler1D()
  {
    return this.sampler1D;
  }
  public void setSampler1D(GlSampler1D value)
  {
    this.sampler1D = value;
  }
  public GlSampler2D getSampler2D()
  {
    return this.sampler2D;
  }
  public void setSampler2D(GlSampler2D value)
  {
    this.sampler2D = value;
  }
  public GlSampler3D getSampler3D()
  {
    return this.sampler3D;
  }
  public void setSampler3D(GlSampler3D value)
  {
    this.sampler3D = value;
  }
  public GlSamplerCUBE getSamplerCUBE()
  {
    return this.samplerCUBE;
  }
  public void setSamplerCUBE(GlSamplerCUBE value)
  {
    this.samplerCUBE = value;
  }
  public GlSamplerRECT getSamplerRECT()
  {
    return this.samplerRECT;
  }
  public void setSamplerRECT(GlSamplerRECT value)
  {
    this.samplerRECT = value;
  }
  public GlSamplerDEPTH getSamplerDEPTH()
  {
    return this.samplerDEPTH;
  }
  public void setSamplerDEPTH(GlSamplerDEPTH value)
  {
    this.samplerDEPTH = value;
  }
  public String getEnum()
  {
    return this._enum;
  }
  public void setEnum(String value)
  {
    this._enum = value;
  }
  public String getRef()
  {
    return this.ref;
  }
  public void setRef(String value)
  {
    this.ref = value;
  }
}

