
package collada;
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
import javax.xml.datatype.XMLGregorianCalendar;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"contributor", "created", "keywords", "modified", "revision", "subject", "title", "unit", "upAxis"})
@XmlRootElement(name="asset")
public class Asset
{
  protected List<Contributor> contributor;
  @XmlElement(required=true)
  protected XMLGregorianCalendar created;
  protected String keywords;
  @XmlElement(required=true)
  protected XMLGregorianCalendar modified;
  protected String revision;
  protected String subject;
  protected String title;
  protected Unit unit;
  @XmlElement(name="up_axis", defaultValue="Y_UP")
  protected UpAxisType upAxis;
  public List<Contributor> getContributor()
  {
    if (this.contributor == null)
      this.contributor = new ArrayList();
    return this.contributor;
  }
  public XMLGregorianCalendar getCreated()
  {
    return this.created;
  }
  public void setCreated(XMLGregorianCalendar value)
  {
    this.created = value;
  }
  public String getKeywords()
  {
    return this.keywords;
  }
  public void setKeywords(String value)
  {
    this.keywords = value;
  }
  public XMLGregorianCalendar getModified()
  {
    return this.modified;
  }
  public void setModified(XMLGregorianCalendar value)
  {
    this.modified = value;
  }
  public String getRevision()
  {
    return this.revision;
  }
  public void setRevision(String value)
  {
    this.revision = value;
  }
  public String getSubject()
  {
    return this.subject;
  }
  public void setSubject(String value)
  {
    this.subject = value;
  }
  public String getTitle()
  {
    return this.title;
  }
  public void setTitle(String value)
  {
    this.title = value;
  }
  public Unit getUnit()
  {
    return this.unit;
  }
  public void setUnit(Unit value)
  {
    this.unit = value;
  }
  public UpAxisType getUpAxis()
  {
    return this.upAxis;
  }
  public void setUpAxis(UpAxisType value)
  {
    this.upAxis = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="")
  public static class Unit
  {
    @XmlAttribute
    protected Double meter;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String name;
    public double getMeter()
    {
      if (this.meter == null)
        return 1D;
      return this.meter.doubleValue();
    }
    public void setMeter(Double value)
    {
      this.meter = value;
    }
    public String getName()
    {
      if (this.name == null)
        return "meter";
      return this.name;
    }
    public void setName(String value)
    {
      this.name = value;
    }
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"author", "authoringTool", "comments", "copyright", "sourceData"})
  public static class Contributor
  {
    protected String author;
    @XmlElement(name="authoring_tool")
    protected String authoringTool;
    protected String comments;
    protected String copyright;
    @XmlElement(name="source_data")
    protected String sourceData;
    public String getAuthor()
    {
      return this.author;
    }
    public void setAuthor(String value)
    {
      this.author = value;
    }
    public String getAuthoringTool()
    {
      return this.authoringTool;
    }
    public void setAuthoringTool(String value)
    {
      this.authoringTool = value;
    }
    public String getComments()
    {
      return this.comments;
    }
    public void setComments(String value)
    {
      this.comments = value;
    }
    public String getCopyright()
    {
      return this.copyright;
    }
    public void setCopyright(String value)
    {
      this.copyright = value;
    }
    public String getSourceData()
    {
      return this.sourceData;
    }
    public void setSourceData(String value)
    {
      this.sourceData = value;
    }
  }
}

