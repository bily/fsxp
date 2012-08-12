package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "libraryAnimationsOrLibraryAnimationClipsOrLibraryCameras", "scene", "extra"})
@XmlRootElement(name="COLLADA")
public class COLLADA
{
  @XmlElement(required=true)
  protected Asset asset;
  @XmlElements({@XmlElement(name="library_physics_materials", type=LibraryPhysicsMaterials.class), @XmlElement(name="library_effects", type=LibraryEffects.class), @XmlElement(name="library_nodes", type=LibraryNodes.class), @XmlElement(name="library_images", type=LibraryImages.class), @XmlElement(name="library_physics_scenes", type=LibraryPhysicsScenes.class), @XmlElement(name="library_visual_scenes", type=LibraryVisualScenes.class), @XmlElement(name="library_animations", type=LibraryAnimations.class), @XmlElement(name="library_animation_clips", type=LibraryAnimationClips.class), @XmlElement(name="library_geometries", type=LibraryGeometries.class), @XmlElement(name="library_lights", type=LibraryLights.class), @XmlElement(name="library_materials", type=LibraryMaterials.class), @XmlElement(name="library_physics_models", type=LibraryPhysicsModels.class), @XmlElement(name="library_controllers", type=LibraryControllers.class), @XmlElement(name="library_cameras", type=LibraryCameras.class), @XmlElement(name="library_force_fields", type=LibraryForceFields.class)})
  protected List<Object> libraryAnimationsOrLibraryAnimationClipsOrLibraryCameras;
  protected Scene scene;
  protected List<Extra> extra;
  @XmlAttribute(required=true)
  protected String version;
  @XmlAttribute(namespace="http://www.w3.org/XML/1998/namespace")
  protected String base;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public List<Object> getLibraryAnimationsOrLibraryAnimationClipsOrLibraryCameras()
  {
    if (this.libraryAnimationsOrLibraryAnimationClipsOrLibraryCameras == null)
      this.libraryAnimationsOrLibraryAnimationClipsOrLibraryCameras = new ArrayList();
    return this.libraryAnimationsOrLibraryAnimationClipsOrLibraryCameras;
  }
  public Scene getScene()
  {
    return this.scene;
  }
  public void setScene(Scene value)
  {
    this.scene = value;
  }
  public List<Extra> getExtra()
  {
    if (this.extra == null)
      this.extra = new ArrayList();
    return this.extra;
  }
  public String getVersion()
  {
    return this.version;
  }
  public void setVersion(String value)
  {
    this.version = value;
  }
  public String getBase()
  {
    return this.base;
  }
  public void setBase(String value)
  {
    this.base = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"instancePhysicsScene", "instanceVisualScene", "extra"})
  public static class Scene
  {
    @XmlElement(name="instance_physics_scene")
    protected List<InstanceWithExtra> instancePhysicsScene;
    @XmlElement(name="instance_visual_scene")
    protected InstanceWithExtra instanceVisualScene;
    protected List<Extra> extra;
    public List<InstanceWithExtra> getInstancePhysicsScene()
    {
      if (this.instancePhysicsScene == null)
        this.instancePhysicsScene = new ArrayList();
      return this.instancePhysicsScene;
    }
    public InstanceWithExtra getInstanceVisualScene()
    {
      return this.instanceVisualScene;
    }
    public void setInstanceVisualScene(InstanceWithExtra value)
    {
      this.instanceVisualScene = value;
    }
    public List<Extra> getExtra()
    {
      if (this.extra == null)
        this.extra = new ArrayList();
      return this.extra;
    }
  }
}

