
package collada;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="common_float_or_param_type", propOrder={"_float", "param"})
public class CommonFloatOrParamType
{
  @XmlElement(name="float")
  protected Float _float;
  protected Param param;
  public Float getFloat()
  {
    return this._float;
  }
  public void setFloat(Float value)
  {
    this._float = value;
  }
  public Param getParam()
  {
    return this.param;
  }
  public void setParam(Param value)
  {
    this.param = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="")
  public static class Param
  {
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String ref;
    public String getRef()
    {
      return this.ref;
    }
    public void setRef(String value)
    {
      this.ref = value;
    }
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"value"})
  public static class Float
  {
    @XmlValue
    protected double value;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    public double getValue()
    {
      return this.value;
    }
    public void setValue(double value)
    {
      this.value = value;
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
}

