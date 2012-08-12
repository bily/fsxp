
package collada;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="glsl_setarray_type", propOrder={"boolOrBool2OrBool3"})
public class GlslSetarrayType
{
  @XmlElementRefs({@javax.xml.bind.annotation.XmlElementRef(name="int2", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="sampler1D", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="bool2", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="surface", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="bool", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="float", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="float3", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="int", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="float2x2", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="sampler2D", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="sampler3D", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="bool3", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="samplerCUBE", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="int3", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="enum", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="samplerDEPTH", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="int4", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="float2", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="array", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="float4x4", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="float4", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="float3x3", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="samplerRECT", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class), @javax.xml.bind.annotation.XmlElementRef(name="bool4", namespace="http://www.collada.org/2005/11/COLLADASchema", type=JAXBElement.class)})
  protected List<JAXBElement<?>> boolOrBool2OrBool3;
  @XmlAttribute
  protected BigInteger length;
  public List<JAXBElement<?>> getBoolOrBool2OrBool3()
  {
    if (this.boolOrBool2OrBool3 == null)
      this.boolOrBool2OrBool3 = new ArrayList();
    return this.boolOrBool2OrBool3;
  }
  public BigInteger getLength()
  {
    return this.length;
  }
  public void setLength(BigInteger value)
  {
    this.length = value;
  }
}

