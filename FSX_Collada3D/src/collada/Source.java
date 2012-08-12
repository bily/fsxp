
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "idrefArray", "nameArray", "boolArray", "floatArray", "intArray", "techniqueCommon", "technique"})
@XmlRootElement(name="source")
public class Source
{
  protected Asset asset;
  @XmlElement(name="IDREF_array")
  protected IDREFArray idrefArray;
  @XmlElement(name="Name_array")
  protected NameArray nameArray;
  @XmlElement(name="bool_array")
  protected BoolArray boolArray;
  @XmlElement(name="float_array")
  protected FloatArray floatArray;
  @XmlElement(name="int_array")
  protected IntArray intArray;
  @XmlElement(name="technique_common")
  protected TechniqueCommon techniqueCommon;
  protected List<Technique> technique;
  @XmlAttribute(required=true)
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
  public IDREFArray getIDREFArray()
  {
    return this.idrefArray;
  }
  public void setIDREFArray(IDREFArray value)
  {
    this.idrefArray = value;
  }
  public NameArray getNameArray()
  {
    return this.nameArray;
  }
  public void setNameArray(NameArray value)
  {
    this.nameArray = value;
  }
  public BoolArray getBoolArray()
  {
    return this.boolArray;
  }
  public void setBoolArray(BoolArray value)
  {
    this.boolArray = value;
  }
  public FloatArray getFloatArray()
  {
    return this.floatArray;
  }
  public void setFloatArray(FloatArray value)
  {
    this.floatArray = value;
  }
  public IntArray getIntArray()
  {
    return this.intArray;
  }
  public void setIntArray(IntArray value)
  {
    this.intArray = value;
  }
  public TechniqueCommon getTechniqueCommon()
  {
    return this.techniqueCommon;
  }
  public void setTechniqueCommon(TechniqueCommon value)
  {
    this.techniqueCommon = value;
  }
  public List<Technique> getTechnique()
  {
    if (this.technique == null)
      this.technique = new ArrayList();
    return this.technique;
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
  @XmlType(name="", propOrder={"accessor"})
  public static class TechniqueCommon
  {
    @XmlElement(required=true)
    protected Accessor accessor;
    public Accessor getAccessor()
    {
      return this.accessor;
    }
    public void setAccessor(Accessor value)
    {
      this.accessor = value;
    }
  }
}

