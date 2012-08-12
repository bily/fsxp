
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"param", "techniqueCommon", "technique", "extra"})
@XmlRootElement(name="bind_material")
public class BindMaterial
{
  protected List<Param> param;
  @XmlElement(name="technique_common", required=true)
  protected TechniqueCommon techniqueCommon;
  protected List<Technique> technique;
  protected List<Extra> extra;
  public List<Param> getParam()
  {
    if (this.param == null)
      this.param = new ArrayList();
    return this.param;
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
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"instanceMaterial"})
  public static class TechniqueCommon
  {
    @XmlElement(name="instance_material", required=true)
    protected List<InstanceMaterial> instanceMaterial;
    public List<InstanceMaterial> getInstanceMaterial()
    {
      if (this.instanceMaterial == null)
        this.instanceMaterial = new ArrayList();
      return this.instanceMaterial;
    }
  }
}

