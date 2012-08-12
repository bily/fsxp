
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="glsl_surface_type", propOrder={"generator"})
public class GlslSurfaceType extends FxSurfaceCommon
{
  protected Generator generator;
  public Generator getGenerator()
  {
    return this.generator;
  }
  public void setGenerator(Generator value)
  {
    this.generator = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"annotate", "codeOrInclude", "name", "setparam"})
  public static class Generator
  {
    protected List<FxAnnotateCommon> annotate;
    @XmlElements({@XmlElement(name="code", type=FxCodeProfile.class), @XmlElement(name="include", type=FxIncludeCommon.class)})
    protected List<Object> codeOrInclude;
    @XmlElement(required=true)
    protected Name name;
    protected List<GlslSetparamSimple> setparam;
    public List<FxAnnotateCommon> getAnnotate()
    {
      if (this.annotate == null)
        this.annotate = new ArrayList();
      return this.annotate;
    }
    public List<Object> getCodeOrInclude()
    {
      if (this.codeOrInclude == null)
        this.codeOrInclude = new ArrayList();
      return this.codeOrInclude;
    }
    public Name getName()
    {
      return this.name;
    }
    public void setName(Name value)
    {
      this.name = value;
    }
    public List<GlslSetparamSimple> getSetparam()
    {
      if (this.setparam == null)
        this.setparam = new ArrayList();
      return this.setparam;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"value"})
    public static class Name
    {
      @XmlValue
      @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
      protected String value;
      @XmlAttribute
      @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
      protected String source;
      public String getValue()
      {
        return this.value;
      }
      public void setValue(String value)
      {
        this.value = value;
      }
      public String getSource()
      {
        return this.source;
      }
      public void setSource(String value)
      {
        this.source = value;
      }
    }
  }
}

