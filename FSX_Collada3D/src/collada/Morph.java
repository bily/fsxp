
package collada;
import java.util.ArrayList;
import java.util.List;
public class Morph
{
  protected List ctSourceMorphElt;
  protected Targets targets;
  protected List extra;
  protected MorphMethodType method;
  protected String source;
  public List getCtSourceMorphElt()
  {
    if (this.ctSourceMorphElt == null)
      this.ctSourceMorphElt = new ArrayList();
    return this.ctSourceMorphElt;
  }
  public Targets getTargets()
  {
    return this.targets;
  }
  public void setTargets(Targets paramTargets)
  {
    this.targets = paramTargets;
  }
  public List getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public MorphMethodType getMethod()
  {
    if (this.method == null)
      return MorphMethodType.NORMALIZED;
    return this.method;
  }
  public void setMethod(MorphMethodType paramMorphMethodType)
  {
    this.method = paramMorphMethodType;
  }
  public String getSource()
  {
    return this.source;
  }
  public void setSource(String paramString)
  {
    this.source = paramString;
  }
  public static class Targets
  {
    protected List input;
    protected List extra;
    public List getInput()
    {
      if (this.input == null)
        this.input = new ArrayList();
      return this.input;
    }
    public List getExtra()
    {
      if (this.extra == null)
        this.extra = new ArrayList();
      return this.extra;
    }
  }
}

