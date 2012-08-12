
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="common_color_or_texture_type", propOrder={"color", "param", "texture"})
public class CommonColorOrTextureType
{
  protected Color color;
  protected Param param;
  protected Texture texture;
  public Color getColor()
  {
    return this.color;
  }
  public void setColor(Color value)
  {
    this.color = value;
  }
  public Param getParam()
  {
    return this.param;
  }
  public void setParam(Param value)
  {
    this.param = value;
  }
  public Texture getTexture()
  {
    return this.texture;
  }
  public void setTexture(Texture value)
  {
    this.texture = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"extra"})
  public static class Texture
  {
    protected Extra extra;
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String texcoord;
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String texture;
    public Extra getExtra()
    {
      return this.extra;
    }
    public void setExtra(Extra value)
    {
      this.extra = value;
    }
    public String getTexcoord()
    {
      return this.texcoord;
    }
    public void setTexcoord(String value)
    {
      this.texcoord = value;
    }
    public String getTexture()
    {
      return this.texture;
    }
    public void setTexture(String value)
    {
      this.texture = value;
    }
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
  public static class Color
  {
    @XmlValue
    protected List<Double> value;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    public List<Double> getValue()
    {
      if (this.value == null)
        this.value = new ArrayList();
      return this.value;
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

