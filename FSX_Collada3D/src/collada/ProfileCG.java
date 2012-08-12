
package collada;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"asset", "codeOrInclude", "imageOrNewparam", "technique", "extra"})
public class ProfileCG
{
  protected Asset asset;
  @XmlElements({@XmlElement(name="include", type=FxIncludeCommon.class), @XmlElement(name="code", type=FxCodeProfile.class)})
  protected List<Object> codeOrInclude;
  @XmlElements({@XmlElement(name="newparam", type=CgNewparam.class), @XmlElement(name="image", type=Image.class)})
  protected List<Object> imageOrNewparam;
  @XmlElement(required=true)
  protected List<Technique> technique;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  protected String platform;
  public Asset getAsset()
  {
    return this.asset;
  }
  public void setAsset(Asset value)
  {
    this.asset = value;
  }
  public List<Object> getCodeOrInclude()
  {
    if (this.codeOrInclude == null)
      this.codeOrInclude = new ArrayList();
    return this.codeOrInclude;
  }
  public List<Object> getImageOrNewparam()
  {
    if (this.imageOrNewparam == null)
      this.imageOrNewparam = new ArrayList();
    return this.imageOrNewparam;
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
  public String getId()
  {
    return this.id;
  }
  public void setId(String value)
  {
    this.id = value;
  }
  public String getPlatform()
  {
    if (this.platform == null)
      return "PC";
    return this.platform;
  }
  public void setPlatform(String value)
  {
    this.platform = value;
  }
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"asset", "annotate", "codeOrInclude", "imageOrNewparamOrSetparam", "pass", "extra"})
  public static class Technique
  {
    protected Asset asset;
    protected List<FxAnnotateCommon> annotate;
    @XmlElements({@XmlElement(name="include", type=FxIncludeCommon.class), @XmlElement(name="code", type=FxCodeProfile.class)})
    protected List<Object> codeOrInclude;
    @XmlElements({@XmlElement(name="image", type=Image.class), @XmlElement(name="setparam", type=CgSetparam.class), @XmlElement(name="newparam", type=CgNewparam.class)})
    protected List<Object> imageOrNewparamOrSetparam;
    @XmlElement(required=true)
    protected List<Pass> pass;
    protected List<Extra> extra;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(required=true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String sid;
    public Asset getAsset()
    {
      return this.asset;
    }
    public void setAsset(Asset value)
    {
      this.asset = value;
    }
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
    public List<Object> getImageOrNewparamOrSetparam()
    {
      if (this.imageOrNewparamOrSetparam == null)
        this.imageOrNewparamOrSetparam = new ArrayList();
      return this.imageOrNewparamOrSetparam;
    }
    public List<Pass> getPass()
    {
      if (this.pass == null)
        this.pass = new ArrayList();
      return this.pass;
    }
    public List<Extra> getExtra()
    {
      if (this.extra == null)
        this.extra = new ArrayList();
      return this.extra;
    }
    public String getId()
    {
      return this.id;
    }
    public void setId(String value)
    {
      this.id = value;
    }
    public String getSid()
    {
      return this.sid;
    }
    public void setSid(String value)
    {
      this.sid = value;
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name="", propOrder={"annotate", "colorTarget", "depthTarget", "stencilTarget", "colorClear", "depthClear", "stencilClear", "draw", "alphaFuncOrBlendFuncOrBlendFuncSeparate", "extra"})
    public static class Pass
    {
      protected List<FxAnnotateCommon> annotate;
      @XmlElement(name="color_target")
      protected List<FxColortargetCommon> colorTarget;
      @XmlElement(name="depth_target")
      protected List<FxDepthtargetCommon> depthTarget;
      @XmlElement(name="stencil_target")
      protected List<FxStenciltargetCommon> stencilTarget;
      @XmlElement(name="color_clear")
      protected List<FxClearcolorCommon> colorClear;
      @XmlElement(name="depth_clear")
      protected List<FxCleardepthCommon> depthClear;
      @XmlElement(name="stencil_clear")
      protected List<FxClearstencilCommon> stencilClear;
      protected String draw;
      @XmlElements({@XmlElement(name="polygon_smooth_enable", type=ProfileGLSL.Technique.Pass.PolygonSmoothEnable.class), @XmlElement(name="fog_mode", type=ProfileGLSL.Technique.Pass.FogMode.class), @XmlElement(name="alpha_func", type=ProfileGLSL.Technique.Pass.AlphaFunc.class), @XmlElement(name="light_model_ambient", type=ProfileGLSL.Technique.Pass.LightModelAmbient.class), @XmlElement(name="textureDEPTH", type=ProfileGLSL.Technique.Pass.TextureDEPTH.class), @XmlElement(name="material_shininess", type=ProfileGLSL.Technique.Pass.MaterialShininess.class), @XmlElement(name="light_linear_attenuation", type=ProfileGLSL.Technique.Pass.LightLinearAttenuation.class), @XmlElement(name="shader", type=Shader.class), @XmlElement(name="textureRECT", type=ProfileGLSL.Technique.Pass.TextureRECT.class), @XmlElement(name="depth_range", type=ProfileGLSL.Technique.Pass.DepthRange.class), @XmlElement(name="stencil_func_separate", type=ProfileGLSL.Technique.Pass.StencilFuncSeparate.class), @XmlElement(name="sample_coverage_enable", type=ProfileGLSL.Technique.Pass.SampleCoverageEnable.class), @XmlElement(name="polygon_offset_line_enable", type=ProfileGLSL.Technique.Pass.PolygonOffsetLineEnable.class), @XmlElement(name="material_emission", type=ProfileGLSL.Technique.Pass.MaterialEmission.class), @XmlElement(name="point_fade_threshold_size", type=ProfileGLSL.Technique.Pass.PointFadeThresholdSize.class), @XmlElement(name="fog_density", type=ProfileGLSL.Technique.Pass.FogDensity.class), @XmlElement(name="fog_start", type=ProfileGLSL.Technique.Pass.FogStart.class), @XmlElement(name="light_model_local_viewer_enable", type=ProfileGLSL.Technique.Pass.LightModelLocalViewerEnable.class), @XmlElement(name="textureCUBE_enable", type=ProfileGLSL.Technique.Pass.TextureCUBEEnable.class), @XmlElement(name="normalize_enable", type=ProfileGLSL.Technique.Pass.NormalizeEnable.class), @XmlElement(name="stencil_func", type=ProfileGLSL.Technique.Pass.StencilFunc.class), @XmlElement(name="light_diffuse", type=ProfileGLSL.Technique.Pass.LightDiffuse.class), @XmlElement(name="light_specular", type=ProfileGLSL.Technique.Pass.LightSpecular.class), @XmlElement(name="scissor", type=ProfileGLSL.Technique.Pass.Scissor.class), @XmlElement(name="blend_equation_separate", type=ProfileGLSL.Technique.Pass.BlendEquationSeparate.class), @XmlElement(name="material_diffuse", type=ProfileGLSL.Technique.Pass.MaterialDiffuse.class), @XmlElement(name="cull_face_enable", type=ProfileGLSL.Technique.Pass.CullFaceEnable.class), @XmlElement(name="clear_color", type=ProfileGLSL.Technique.Pass.ClearColor.class), @XmlElement(name="textureDEPTH_enable", type=ProfileGLSL.Technique.Pass.TextureDEPTHEnable.class), @XmlElement(name="stencil_op_separate", type=ProfileGLSL.Technique.Pass.StencilOpSeparate.class), @XmlElement(name="textureRECT_enable", type=ProfileGLSL.Technique.Pass.TextureRECTEnable.class), @XmlElement(name="front_face", type=ProfileGLSL.Technique.Pass.FrontFace.class), @XmlElement(name="textureCUBE", type=ProfileGLSL.Technique.Pass.TextureCUBE.class), @XmlElement(name="depth_bounds", type=ProfileGLSL.Technique.Pass.DepthBounds.class), @XmlElement(name="texture3D_enable", type=ProfileGLSL.Technique.Pass.Texture3DEnable.class), @XmlElement(name="texture2D_enable", type=ProfileGLSL.Technique.Pass.Texture2DEnable.class), @XmlElement(name="stencil_mask_separate", type=ProfileGLSL.Technique.Pass.StencilMaskSeparate.class), @XmlElement(name="stencil_mask", type=ProfileGLSL.Technique.Pass.StencilMask.class), @XmlElement(name="projection_matrix", type=ProfileGLSL.Technique.Pass.ProjectionMatrix.class), @XmlElement(name="color_logic_op_enable", type=ProfileGLSL.Technique.Pass.ColorLogicOpEnable.class), @XmlElement(name="texture3D", type=ProfileGLSL.Technique.Pass.Texture3D.class), @XmlElement(name="blend_equation", type=ProfileGLSL.Technique.Pass.BlendEquation.class), @XmlElement(name="line_stipple", type=ProfileGLSL.Technique.Pass.LineStipple.class), @XmlElement(name="fog_enable", type=ProfileGLSL.Technique.Pass.FogEnable.class), @XmlElement(name="alpha_test_enable", type=ProfileGLSL.Technique.Pass.AlphaTestEnable.class), @XmlElement(name="dither_enable", type=ProfileGLSL.Technique.Pass.DitherEnable.class), @XmlElement(name="point_size_max", type=ProfileGLSL.Technique.Pass.PointSizeMax.class), @XmlElement(name="sample_alpha_to_coverage_enable", type=ProfileGLSL.Technique.Pass.SampleAlphaToCoverageEnable.class), @XmlElement(name="line_smooth_enable", type=ProfileGLSL.Technique.Pass.LineSmoothEnable.class), @XmlElement(name="point_size_min", type=ProfileGLSL.Technique.Pass.PointSizeMin.class), @XmlElement(name="line_width", type=ProfileGLSL.Technique.Pass.LineWidth.class), @XmlElement(name="gl_hook_abstract"), @XmlElement(name="cull_face", type=ProfileGLSL.Technique.Pass.CullFace.class), @XmlElement(name="blend_func", type=ProfileGLSL.Technique.Pass.BlendFunc.class), @XmlElement(name="depth_clamp_enable", type=ProfileGLSL.Technique.Pass.DepthClampEnable.class), @XmlElement(name="shade_model", type=ProfileGLSL.Technique.Pass.ShadeModel.class), @XmlElement(name="clear_depth", type=ProfileGLSL.Technique.Pass.ClearDepth.class), @XmlElement(name="blend_enable", type=ProfileGLSL.Technique.Pass.BlendEnable.class), @XmlElement(name="point_smooth_enable", type=ProfileGLSL.Technique.Pass.PointSmoothEnable.class), @XmlElement(name="sample_alpha_to_one_enable", type=ProfileGLSL.Technique.Pass.SampleAlphaToOneEnable.class), @XmlElement(name="polygon_offset_fill_enable", type=ProfileGLSL.Technique.Pass.PolygonOffsetFillEnable.class), @XmlElement(name="texture2D", type=ProfileGLSL.Technique.Pass.Texture2D.class), @XmlElement(name="fog_end", type=ProfileGLSL.Technique.Pass.FogEnd.class), @XmlElement(name="depth_test_enable", type=ProfileGLSL.Technique.Pass.DepthTestEnable.class), @XmlElement(name="texture_env_mode", type=ProfileGLSL.Technique.Pass.TextureEnvMode.class), @XmlElement(name="light_spot_direction", type=ProfileGLSL.Technique.Pass.LightSpotDirection.class), @XmlElement(name="light_quadratic_attenuation", type=ProfileGLSL.Technique.Pass.LightQuadraticAttenuation.class), @XmlElement(name="color_mask", type=ProfileGLSL.Technique.Pass.ColorMask.class), @XmlElement(name="scissor_test_enable", type=ProfileGLSL.Technique.Pass.ScissorTestEnable.class), @XmlElement(name="texture_env_color", type=ProfileGLSL.Technique.Pass.TextureEnvColor.class), @XmlElement(name="line_stipple_enable", type=ProfileGLSL.Technique.Pass.LineStippleEnable.class), @XmlElement(name="point_distance_attenuation", type=ProfileGLSL.Technique.Pass.PointDistanceAttenuation.class), @XmlElement(name="logic_op", type=ProfileGLSL.Technique.Pass.LogicOp.class), @XmlElement(name="point_size", type=ProfileGLSL.Technique.Pass.PointSize.class), @XmlElement(name="depth_mask", type=ProfileGLSL.Technique.Pass.DepthMask.class), @XmlElement(name="depth_func", type=ProfileGLSL.Technique.Pass.DepthFunc.class), @XmlElement(name="blend_func_separate", type=ProfileGLSL.Technique.Pass.BlendFuncSeparate.class), @XmlElement(name="clear_stencil", type=ProfileGLSL.Technique.Pass.ClearStencil.class), @XmlElement(name="model_view_matrix", type=ProfileGLSL.Technique.Pass.ModelViewMatrix.class), @XmlElement(name="light_model_color_control", type=ProfileGLSL.Technique.Pass.LightModelColorControl.class), @XmlElement(name="polygon_mode", type=ProfileGLSL.Technique.Pass.PolygonMode.class), @XmlElement(name="stencil_test_enable", type=ProfileGLSL.Technique.Pass.StencilTestEnable.class), @XmlElement(name="material_ambient", type=ProfileGLSL.Technique.Pass.MaterialAmbient.class), @XmlElement(name="lighting_enable", type=ProfileGLSL.Technique.Pass.LightingEnable.class), @XmlElement(name="fog_coord_src", type=ProfileGLSL.Technique.Pass.FogCoordSrc.class), @XmlElement(name="color_material_enable", type=ProfileGLSL.Technique.Pass.ColorMaterialEnable.class), @XmlElement(name="material_specular", type=ProfileGLSL.Technique.Pass.MaterialSpecular.class), @XmlElement(name="light_spot_cutoff", type=ProfileGLSL.Technique.Pass.LightSpotCutoff.class), @XmlElement(name="blend_color", type=ProfileGLSL.Technique.Pass.BlendColor.class), @XmlElement(name="light_constant_attenuation", type=ProfileGLSL.Technique.Pass.LightConstantAttenuation.class), @XmlElement(name="color_material", type=ProfileGLSL.Technique.Pass.ColorMaterial.class), @XmlElement(name="light_model_two_side_enable", type=ProfileGLSL.Technique.Pass.LightModelTwoSideEnable.class), @XmlElement(name="light_spot_exponent", type=ProfileGLSL.Technique.Pass.LightSpotExponent.class), @XmlElement(name="depth_bounds_enable", type=ProfileGLSL.Technique.Pass.DepthBoundsEnable.class), @XmlElement(name="polygon_offset_point_enable", type=ProfileGLSL.Technique.Pass.PolygonOffsetPointEnable.class), @XmlElement(name="light_ambient", type=ProfileGLSL.Technique.Pass.LightAmbient.class), @XmlElement(name="auto_normal_enable", type=ProfileGLSL.Technique.Pass.AutoNormalEnable.class), @XmlElement(name="stencil_op", type=ProfileGLSL.Technique.Pass.StencilOp.class), @XmlElement(name="polygon_offset", type=ProfileGLSL.Technique.Pass.PolygonOffset.class), @XmlElement(name="logic_op_enable", type=ProfileGLSL.Technique.Pass.LogicOpEnable.class), @XmlElement(name="clip_plane", type=ProfileGLSL.Technique.Pass.ClipPlane.class), @XmlElement(name="fog_color", type=ProfileGLSL.Technique.Pass.FogColor.class), @XmlElement(name="texture1D", type=ProfileGLSL.Technique.Pass.Texture1D.class), @XmlElement(name="polygon_stipple_enable", type=ProfileGLSL.Technique.Pass.PolygonStippleEnable.class), @XmlElement(name="texture1D_enable", type=ProfileGLSL.Technique.Pass.Texture1DEnable.class), @XmlElement(name="light_position", type=ProfileGLSL.Technique.Pass.LightPosition.class), @XmlElement(name="light_enable", type=ProfileGLSL.Technique.Pass.LightEnable.class), @XmlElement(name="multisample_enable", type=ProfileGLSL.Technique.Pass.MultisampleEnable.class), @XmlElement(name="rescale_normal_enable", type=ProfileGLSL.Technique.Pass.RescaleNormalEnable.class), @XmlElement(name="clip_plane_enable", type=ProfileGLSL.Technique.Pass.ClipPlaneEnable.class)})
      protected List<Object> alphaFuncOrBlendFuncOrBlendFuncSeparate;
      protected List<Extra> extra;
      @XmlAttribute
      @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
      protected String sid;
      public List<FxAnnotateCommon> getAnnotate()
      {
        if (this.annotate == null)
          this.annotate = new ArrayList();
        return this.annotate;
      }
      public List<FxColortargetCommon> getColorTarget()
      {
        if (this.colorTarget == null)
          this.colorTarget = new ArrayList();
        return this.colorTarget;
      }
      public List<FxDepthtargetCommon> getDepthTarget()
      {
        if (this.depthTarget == null)
          this.depthTarget = new ArrayList();
        return this.depthTarget;
      }
      public List<FxStenciltargetCommon> getStencilTarget()
      {
        if (this.stencilTarget == null)
          this.stencilTarget = new ArrayList();
        return this.stencilTarget;
      }
      public List<FxClearcolorCommon> getColorClear()
      {
        if (this.colorClear == null)
          this.colorClear = new ArrayList();
        return this.colorClear;
      }
      public List<FxCleardepthCommon> getDepthClear()
      {
        if (this.depthClear == null)
          this.depthClear = new ArrayList();
        return this.depthClear;
      }
      public List<FxClearstencilCommon> getStencilClear()
      {
        if (this.stencilClear == null)
          this.stencilClear = new ArrayList();
        return this.stencilClear;
      }
      public String getDraw()
      {
        return this.draw;
      }
      public void setDraw(String value)
      {
        this.draw = value;
      }
      public List<Object> getAlphaFuncOrBlendFuncOrBlendFuncSeparate()
      {
        if (this.alphaFuncOrBlendFuncOrBlendFuncSeparate == null)
          this.alphaFuncOrBlendFuncOrBlendFuncSeparate = new ArrayList();
        return this.alphaFuncOrBlendFuncOrBlendFuncSeparate;
      }
      public List<Extra> getExtra()
      {
        if (this.extra == null)
          this.extra = new ArrayList();
        return this.extra;
      }
      public String getSid()
      {
        return this.sid;
      }
      public void setSid(String value)
      {
        this.sid = value;
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"annotate", "compilerTarget", "compilerOptions", "name", "bind"})
      public static class Shader
      {
        protected List<FxAnnotateCommon> annotate;
        @XmlElement(name="compiler_target")
        protected CompilerTarget compilerTarget;
        @XmlElement(name="compiler_options")
        protected String compilerOptions;
        @XmlElement(required=true)
        protected Name name;
        protected List<Bind> bind;
        @XmlAttribute
        protected CgPipelineStage stage;
        public List<FxAnnotateCommon> getAnnotate()
        {
          if (this.annotate == null)
            this.annotate = new ArrayList();
          return this.annotate;
        }
        public CompilerTarget getCompilerTarget()
        {
          return this.compilerTarget;
        }
        public void setCompilerTarget(CompilerTarget value)
        {
          this.compilerTarget = value;
        }
        public String getCompilerOptions()
        {
          return this.compilerOptions;
        }
        public void setCompilerOptions(String value)
        {
          this.compilerOptions = value;
        }
        public Name getName()
        {
          return this.name;
        }
        public void setName(Name value)
        {
          this.name = value;
        }
        public List<Bind> getBind()
        {
          if (this.bind == null)
            this.bind = new ArrayList();
          return this.bind;
        }
        public CgPipelineStage getStage()
        {
          return this.stage;
        }
        public void setStage(CgPipelineStage value)
        {
          this.stage = value;
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
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="", propOrder={"value"})
        public static class CompilerTarget
        {
          @XmlValue
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String value;
          public String getValue()
          {
            return this.value;
          }
          public void setValue(String value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="", propOrder={"bool", "bool1", "bool2", "bool3", "bool4", "bool1X1", "bool1X2", "bool1X3", "bool1X4", "bool2X1", "bool2X2", "bool2X3", "bool2X4", "bool3X1", "bool3X2", "bool3X3", "bool3X4", "bool4X1", "bool4X2", "bool4X3", "bool4X4", "_float", "float1", "float2", "float3", "float4", "float1X1", "float1X2", "float1X3", "float1X4", "float2X1", "float2X2", "float2X3", "float2X4", "float3X1", "float3X2", "float3X3", "float3X4", "float4X1", "float4X2", "float4X3", "float4X4", "_int", "int1", "int2", "int3", "int4", "int1X1", "int1X2", "int1X3", "int1X4", "int2X1", "int2X2", "int2X3", "int2X4", "int3X1", "int3X2", "int3X3", "int3X4", "int4X1", "int4X2", "int4X3", "int4X4", "half", "half1", "half2", "half3", "half4", "half1X1", "half1X2", "half1X3", "half1X4", "half2X1", "half2X2", "half2X3", "half2X4", "half3X1", "half3X2", "half3X3", "half3X4", "half4X1", "half4X2", "half4X3", "half4X4", "fixed", "fixed1", "fixed2", "fixed3", "fixed4", "fixed1X1", "fixed1X2", "fixed1X3", "fixed1X4", "fixed2X1", "fixed2X2", "fixed2X3", "fixed2X4", "fixed3X1", "fixed3X2", "fixed3X3", "fixed3X4", "fixed4X1", "fixed4X2", "fixed4X3", "fixed4X4", "surface", "sampler1D", "sampler2D", "sampler3D", "samplerRECT", "samplerCUBE", "samplerDEPTH", "string", "_enum", "param"})
        public static class Bind
        {
          protected Boolean bool;
          protected Boolean bool1;
          @XmlList
          @XmlElement(type=Boolean.class)
          protected List<Boolean> bool2;
          @XmlList
          @XmlElement(type=Boolean.class)
          protected List<Boolean> bool3;
          @XmlList
          @XmlElement(type=Boolean.class)
          protected List<Boolean> bool4;
          @XmlList
          @XmlElement(name="bool1x1", type=Boolean.class)
          protected List<Boolean> bool1X1;
          @XmlList
          @XmlElement(name="bool1x2", type=Boolean.class)
          protected List<Boolean> bool1X2;
          @XmlList
          @XmlElement(name="bool1x3", type=Boolean.class)
          protected List<Boolean> bool1X3;
          @XmlList
          @XmlElement(name="bool1x4", type=Boolean.class)
          protected List<Boolean> bool1X4;
          @XmlList
          @XmlElement(name="bool2x1", type=Boolean.class)
          protected List<Boolean> bool2X1;
          @XmlList
          @XmlElement(name="bool2x2", type=Boolean.class)
          protected List<Boolean> bool2X2;
          @XmlList
          @XmlElement(name="bool2x3", type=Boolean.class)
          protected List<Boolean> bool2X3;
          @XmlList
          @XmlElement(name="bool2x4", type=Boolean.class)
          protected List<Boolean> bool2X4;
          @XmlList
          @XmlElement(name="bool3x1", type=Boolean.class)
          protected List<Boolean> bool3X1;
          @XmlList
          @XmlElement(name="bool3x2", type=Boolean.class)
          protected List<Boolean> bool3X2;
          @XmlList
          @XmlElement(name="bool3x3", type=Boolean.class)
          protected List<Boolean> bool3X3;
          @XmlList
          @XmlElement(name="bool3x4", type=Boolean.class)
          protected List<Boolean> bool3X4;
          @XmlList
          @XmlElement(name="bool4x1", type=Boolean.class)
          protected List<Boolean> bool4X1;
          @XmlList
          @XmlElement(name="bool4x2", type=Boolean.class)
          protected List<Boolean> bool4X2;
          @XmlList
          @XmlElement(name="bool4x3", type=Boolean.class)
          protected List<Boolean> bool4X3;
          @XmlList
          @XmlElement(name="bool4x4", type=Boolean.class)
          protected List<Boolean> bool4X4;
          @XmlElement(name="float")
          protected Float _float;
          protected Float float1;
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
          @XmlElement(name="float1x1", type=Float.class)
          protected List<Float> float1X1;
          @XmlList
          @XmlElement(name="float1x2", type=Float.class)
          protected List<Float> float1X2;
          @XmlList
          @XmlElement(name="float1x3", type=Float.class)
          protected List<Float> float1X3;
          @XmlList
          @XmlElement(name="float1x4", type=Float.class)
          protected List<Float> float1X4;
          @XmlList
          @XmlElement(name="float2x1", type=Float.class)
          protected List<Float> float2X1;
          @XmlList
          @XmlElement(name="float2x2", type=Float.class)
          protected List<Float> float2X2;
          @XmlList
          @XmlElement(name="float2x3", type=Float.class)
          protected List<Float> float2X3;
          @XmlList
          @XmlElement(name="float2x4", type=Float.class)
          protected List<Float> float2X4;
          @XmlList
          @XmlElement(name="float3x1", type=Float.class)
          protected List<Float> float3X1;
          @XmlList
          @XmlElement(name="float3x2", type=Float.class)
          protected List<Float> float3X2;
          @XmlList
          @XmlElement(name="float3x3", type=Float.class)
          protected List<Float> float3X3;
          @XmlList
          @XmlElement(name="float3x4", type=Float.class)
          protected List<Float> float3X4;
          @XmlList
          @XmlElement(name="float4x1", type=Float.class)
          protected List<Float> float4X1;
          @XmlList
          @XmlElement(name="float4x2", type=Float.class)
          protected List<Float> float4X2;
          @XmlList
          @XmlElement(name="float4x3", type=Float.class)
          protected List<Float> float4X3;
          @XmlList
          @XmlElement(name="float4x4", type=Float.class)
          protected List<Float> float4X4;
          @XmlElement(name="int")
          protected Integer _int;
          protected Integer int1;
          @XmlList
          @XmlElement(type=Integer.class)
          protected List<Integer> int2;
          @XmlList
          @XmlElement(type=Integer.class)
          protected List<Integer> int3;
          @XmlList
          @XmlElement(type=Integer.class)
          protected List<Integer> int4;
          @XmlList
          @XmlElement(name="int1x1", type=Integer.class)
          protected List<Integer> int1X1;
          @XmlList
          @XmlElement(name="int1x2", type=Integer.class)
          protected List<Integer> int1X2;
          @XmlList
          @XmlElement(name="int1x3", type=Integer.class)
          protected List<Integer> int1X3;
          @XmlList
          @XmlElement(name="int1x4", type=Integer.class)
          protected List<Integer> int1X4;
          @XmlList
          @XmlElement(name="int2x1", type=Integer.class)
          protected List<Integer> int2X1;
          @XmlList
          @XmlElement(name="int2x2", type=Integer.class)
          protected List<Integer> int2X2;
          @XmlList
          @XmlElement(name="int2x3", type=Integer.class)
          protected List<Integer> int2X3;
          @XmlList
          @XmlElement(name="int2x4", type=Integer.class)
          protected List<Integer> int2X4;
          @XmlList
          @XmlElement(name="int3x1", type=Integer.class)
          protected List<Integer> int3X1;
          @XmlList
          @XmlElement(name="int3x2", type=Integer.class)
          protected List<Integer> int3X2;
          @XmlList
          @XmlElement(name="int3x3", type=Integer.class)
          protected List<Integer> int3X3;
          @XmlList
          @XmlElement(name="int3x4", type=Integer.class)
          protected List<Integer> int3X4;
          @XmlList
          @XmlElement(name="int4x1", type=Integer.class)
          protected List<Integer> int4X1;
          @XmlList
          @XmlElement(name="int4x2", type=Integer.class)
          protected List<Integer> int4X2;
          @XmlList
          @XmlElement(name="int4x3", type=Integer.class)
          protected List<Integer> int4X3;
          @XmlList
          @XmlElement(name="int4x4", type=Integer.class)
          protected List<Integer> int4X4;
          protected Float half;
          protected Float half1;
          @XmlList
          @XmlElement(type=Float.class)
          protected List<Float> half2;
          @XmlList
          @XmlElement(type=Float.class)
          protected List<Float> half3;
          @XmlList
          @XmlElement(type=Float.class)
          protected List<Float> half4;
          @XmlList
          @XmlElement(name="half1x1", type=Float.class)
          protected List<Float> half1X1;
          @XmlList
          @XmlElement(name="half1x2", type=Float.class)
          protected List<Float> half1X2;
          @XmlList
          @XmlElement(name="half1x3", type=Float.class)
          protected List<Float> half1X3;
          @XmlList
          @XmlElement(name="half1x4", type=Float.class)
          protected List<Float> half1X4;
          @XmlList
          @XmlElement(name="half2x1", type=Float.class)
          protected List<Float> half2X1;
          @XmlList
          @XmlElement(name="half2x2", type=Float.class)
          protected List<Float> half2X2;
          @XmlList
          @XmlElement(name="half2x3", type=Float.class)
          protected List<Float> half2X3;
          @XmlList
          @XmlElement(name="half2x4", type=Float.class)
          protected List<Float> half2X4;
          @XmlList
          @XmlElement(name="half3x1", type=Float.class)
          protected List<Float> half3X1;
          @XmlList
          @XmlElement(name="half3x2", type=Float.class)
          protected List<Float> half3X2;
          @XmlList
          @XmlElement(name="half3x3", type=Float.class)
          protected List<Float> half3X3;
          @XmlList
          @XmlElement(name="half3x4", type=Float.class)
          protected List<Float> half3X4;
          @XmlList
          @XmlElement(name="half4x1", type=Float.class)
          protected List<Float> half4X1;
          @XmlList
          @XmlElement(name="half4x2", type=Float.class)
          protected List<Float> half4X2;
          @XmlList
          @XmlElement(name="half4x3", type=Float.class)
          protected List<Float> half4X3;
          @XmlList
          @XmlElement(name="half4x4", type=Float.class)
          protected List<Float> half4X4;
          protected Float fixed;
          protected Float fixed1;
          @XmlList
          @XmlElement(type=Float.class)
          protected List<Float> fixed2;
          @XmlList
          @XmlElement(type=Float.class)
          protected List<Float> fixed3;
          @XmlList
          @XmlElement(type=Float.class)
          protected List<Float> fixed4;
          @XmlList
          @XmlElement(name="fixed1x1", type=Float.class)
          protected List<Float> fixed1X1;
          @XmlList
          @XmlElement(name="fixed1x2", type=Float.class)
          protected List<Float> fixed1X2;
          @XmlList
          @XmlElement(name="fixed1x3", type=Float.class)
          protected List<Float> fixed1X3;
          @XmlList
          @XmlElement(name="fixed1x4", type=Float.class)
          protected List<Float> fixed1X4;
          @XmlList
          @XmlElement(name="fixed2x1", type=Float.class)
          protected List<Float> fixed2X1;
          @XmlList
          @XmlElement(name="fixed2x2", type=Float.class)
          protected List<Float> fixed2X2;
          @XmlList
          @XmlElement(name="fixed2x3", type=Float.class)
          protected List<Float> fixed2X3;
          @XmlList
          @XmlElement(name="fixed2x4", type=Float.class)
          protected List<Float> fixed2X4;
          @XmlList
          @XmlElement(name="fixed3x1", type=Float.class)
          protected List<Float> fixed3X1;
          @XmlList
          @XmlElement(name="fixed3x2", type=Float.class)
          protected List<Float> fixed3X2;
          @XmlList
          @XmlElement(name="fixed3x3", type=Float.class)
          protected List<Float> fixed3X3;
          @XmlList
          @XmlElement(name="fixed3x4", type=Float.class)
          protected List<Float> fixed3X4;
          @XmlList
          @XmlElement(name="fixed4x1", type=Float.class)
          protected List<Float> fixed4X1;
          @XmlList
          @XmlElement(name="fixed4x2", type=Float.class)
          protected List<Float> fixed4X2;
          @XmlList
          @XmlElement(name="fixed4x3", type=Float.class)
          protected List<Float> fixed4X3;
          @XmlList
          @XmlElement(name="fixed4x4", type=Float.class)
          protected List<Float> fixed4X4;
          protected CgSurfaceType surface;
          protected CgSampler1D sampler1D;
          protected CgSampler2D sampler2D;
          protected CgSampler3D sampler3D;
          protected CgSamplerRECT samplerRECT;
          protected CgSamplerCUBE samplerCUBE;
          protected CgSamplerDEPTH samplerDEPTH;
          protected String string;
          @XmlElement(name="enum")
          protected String _enum;
          protected Param param;
          @XmlAttribute(required=true)
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String symbol;
          public Boolean isBool()
          {
            return this.bool;
          }
          public void setBool(Boolean value)
          {
            this.bool = value;
          }
          public Boolean isBool1()
          {
            return this.bool1;
          }
          public void setBool1(Boolean value)
          {
            this.bool1 = value;
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
          public List<Boolean> getBool1X1()
          {
            if (this.bool1X1 == null)
              this.bool1X1 = new ArrayList();
            return this.bool1X1;
          }
          public List<Boolean> getBool1X2()
          {
            if (this.bool1X2 == null)
              this.bool1X2 = new ArrayList();
            return this.bool1X2;
          }
          public List<Boolean> getBool1X3()
          {
            if (this.bool1X3 == null)
              this.bool1X3 = new ArrayList();
            return this.bool1X3;
          }
          public List<Boolean> getBool1X4()
          {
            if (this.bool1X4 == null)
              this.bool1X4 = new ArrayList();
            return this.bool1X4;
          }
          public List<Boolean> getBool2X1()
          {
            if (this.bool2X1 == null)
              this.bool2X1 = new ArrayList();
            return this.bool2X1;
          }
          public List<Boolean> getBool2X2()
          {
            if (this.bool2X2 == null)
              this.bool2X2 = new ArrayList();
            return this.bool2X2;
          }
          public List<Boolean> getBool2X3()
          {
            if (this.bool2X3 == null)
              this.bool2X3 = new ArrayList();
            return this.bool2X3;
          }
          public List<Boolean> getBool2X4()
          {
            if (this.bool2X4 == null)
              this.bool2X4 = new ArrayList();
            return this.bool2X4;
          }
          public List<Boolean> getBool3X1()
          {
            if (this.bool3X1 == null)
              this.bool3X1 = new ArrayList();
            return this.bool3X1;
          }
          public List<Boolean> getBool3X2()
          {
            if (this.bool3X2 == null)
              this.bool3X2 = new ArrayList();
            return this.bool3X2;
          }
          public List<Boolean> getBool3X3()
          {
            if (this.bool3X3 == null)
              this.bool3X3 = new ArrayList();
            return this.bool3X3;
          }
          public List<Boolean> getBool3X4()
          {
            if (this.bool3X4 == null)
              this.bool3X4 = new ArrayList();
            return this.bool3X4;
          }
          public List<Boolean> getBool4X1()
          {
            if (this.bool4X1 == null)
              this.bool4X1 = new ArrayList();
            return this.bool4X1;
          }
          public List<Boolean> getBool4X2()
          {
            if (this.bool4X2 == null)
              this.bool4X2 = new ArrayList();
            return this.bool4X2;
          }
          public List<Boolean> getBool4X3()
          {
            if (this.bool4X3 == null)
              this.bool4X3 = new ArrayList();
            return this.bool4X3;
          }
          public List<Boolean> getBool4X4()
          {
            if (this.bool4X4 == null)
              this.bool4X4 = new ArrayList();
            return this.bool4X4;
          }
          public Float getFloat()
          {
            return this._float;
          }
          public void setFloat(Float value)
          {
            this._float = value;
          }
          public Float getFloat1()
          {
            return this.float1;
          }
          public void setFloat1(Float value)
          {
            this.float1 = value;
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
          public List<Float> getFloat1X1()
          {
            if (this.float1X1 == null)
              this.float1X1 = new ArrayList();
            return this.float1X1;
          }
          public List<Float> getFloat1X2()
          {
            if (this.float1X2 == null)
              this.float1X2 = new ArrayList();
            return this.float1X2;
          }
          public List<Float> getFloat1X3()
          {
            if (this.float1X3 == null)
              this.float1X3 = new ArrayList();
            return this.float1X3;
          }
          public List<Float> getFloat1X4()
          {
            if (this.float1X4 == null)
              this.float1X4 = new ArrayList();
            return this.float1X4;
          }
          public List<Float> getFloat2X1()
          {
            if (this.float2X1 == null)
              this.float2X1 = new ArrayList();
            return this.float2X1;
          }
          public List<Float> getFloat2X2()
          {
            if (this.float2X2 == null)
              this.float2X2 = new ArrayList();
            return this.float2X2;
          }
          public List<Float> getFloat2X3()
          {
            if (this.float2X3 == null)
              this.float2X3 = new ArrayList();
            return this.float2X3;
          }
          public List<Float> getFloat2X4()
          {
            if (this.float2X4 == null)
              this.float2X4 = new ArrayList();
            return this.float2X4;
          }
          public List<Float> getFloat3X1()
          {
            if (this.float3X1 == null)
              this.float3X1 = new ArrayList();
            return this.float3X1;
          }
          public List<Float> getFloat3X2()
          {
            if (this.float3X2 == null)
              this.float3X2 = new ArrayList();
            return this.float3X2;
          }
          public List<Float> getFloat3X3()
          {
            if (this.float3X3 == null)
              this.float3X3 = new ArrayList();
            return this.float3X3;
          }
          public List<Float> getFloat3X4()
          {
            if (this.float3X4 == null)
              this.float3X4 = new ArrayList();
            return this.float3X4;
          }
          public List<Float> getFloat4X1()
          {
            if (this.float4X1 == null)
              this.float4X1 = new ArrayList();
            return this.float4X1;
          }
          public List<Float> getFloat4X2()
          {
            if (this.float4X2 == null)
              this.float4X2 = new ArrayList();
            return this.float4X2;
          }
          public List<Float> getFloat4X3()
          {
            if (this.float4X3 == null)
              this.float4X3 = new ArrayList();
            return this.float4X3;
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
          public Integer getInt1()
          {
            return this.int1;
          }
          public void setInt1(Integer value)
          {
            this.int1 = value;
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
          public List<Integer> getInt1X1()
          {
            if (this.int1X1 == null)
              this.int1X1 = new ArrayList();
            return this.int1X1;
          }
          public List<Integer> getInt1X2()
          {
            if (this.int1X2 == null)
              this.int1X2 = new ArrayList();
            return this.int1X2;
          }
          public List<Integer> getInt1X3()
          {
            if (this.int1X3 == null)
              this.int1X3 = new ArrayList();
            return this.int1X3;
          }
          public List<Integer> getInt1X4()
          {
            if (this.int1X4 == null)
              this.int1X4 = new ArrayList();
            return this.int1X4;
          }
          public List<Integer> getInt2X1()
          {
            if (this.int2X1 == null)
              this.int2X1 = new ArrayList();
            return this.int2X1;
          }
          public List<Integer> getInt2X2()
          {
            if (this.int2X2 == null)
              this.int2X2 = new ArrayList();
            return this.int2X2;
          }
          public List<Integer> getInt2X3()
          {
            if (this.int2X3 == null)
              this.int2X3 = new ArrayList();
            return this.int2X3;
          }
          public List<Integer> getInt2X4()
          {
            if (this.int2X4 == null)
              this.int2X4 = new ArrayList();
            return this.int2X4;
          }
          public List<Integer> getInt3X1()
          {
            if (this.int3X1 == null)
              this.int3X1 = new ArrayList();
            return this.int3X1;
          }
          public List<Integer> getInt3X2()
          {
            if (this.int3X2 == null)
              this.int3X2 = new ArrayList();
            return this.int3X2;
          }
          public List<Integer> getInt3X3()
          {
            if (this.int3X3 == null)
              this.int3X3 = new ArrayList();
            return this.int3X3;
          }
          public List<Integer> getInt3X4()
          {
            if (this.int3X4 == null)
              this.int3X4 = new ArrayList();
            return this.int3X4;
          }
          public List<Integer> getInt4X1()
          {
            if (this.int4X1 == null)
              this.int4X1 = new ArrayList();
            return this.int4X1;
          }
          public List<Integer> getInt4X2()
          {
            if (this.int4X2 == null)
              this.int4X2 = new ArrayList();
            return this.int4X2;
          }
          public List<Integer> getInt4X3()
          {
            if (this.int4X3 == null)
              this.int4X3 = new ArrayList();
            return this.int4X3;
          }
          public List<Integer> getInt4X4()
          {
            if (this.int4X4 == null)
              this.int4X4 = new ArrayList();
            return this.int4X4;
          }
          public Float getHalf()
          {
            return this.half;
          }
          public void setHalf(Float value)
          {
            this.half = value;
          }
          public Float getHalf1()
          {
            return this.half1;
          }
          public void setHalf1(Float value)
          {
            this.half1 = value;
          }
          public List<Float> getHalf2()
          {
            if (this.half2 == null)
              this.half2 = new ArrayList();
            return this.half2;
          }
          public List<Float> getHalf3()
          {
            if (this.half3 == null)
              this.half3 = new ArrayList();
            return this.half3;
          }
          public List<Float> getHalf4()
          {
            if (this.half4 == null)
              this.half4 = new ArrayList();
            return this.half4;
          }
          public List<Float> getHalf1X1()
          {
            if (this.half1X1 == null)
              this.half1X1 = new ArrayList();
            return this.half1X1;
          }
          public List<Float> getHalf1X2()
          {
            if (this.half1X2 == null)
              this.half1X2 = new ArrayList();
            return this.half1X2;
          }
          public List<Float> getHalf1X3()
          {
            if (this.half1X3 == null)
              this.half1X3 = new ArrayList();
            return this.half1X3;
          }
          public List<Float> getHalf1X4()
          {
            if (this.half1X4 == null)
              this.half1X4 = new ArrayList();
            return this.half1X4;
          }
          public List<Float> getHalf2X1()
          {
            if (this.half2X1 == null)
              this.half2X1 = new ArrayList();
            return this.half2X1;
          }
          public List<Float> getHalf2X2()
          {
            if (this.half2X2 == null)
              this.half2X2 = new ArrayList();
            return this.half2X2;
          }
          public List<Float> getHalf2X3()
          {
            if (this.half2X3 == null)
              this.half2X3 = new ArrayList();
            return this.half2X3;
          }
          public List<Float> getHalf2X4()
          {
            if (this.half2X4 == null)
              this.half2X4 = new ArrayList();
            return this.half2X4;
          }
          public List<Float> getHalf3X1()
          {
            if (this.half3X1 == null)
              this.half3X1 = new ArrayList();
            return this.half3X1;
          }
          public List<Float> getHalf3X2()
          {
            if (this.half3X2 == null)
              this.half3X2 = new ArrayList();
            return this.half3X2;
          }
          public List<Float> getHalf3X3()
          {
            if (this.half3X3 == null)
              this.half3X3 = new ArrayList();
            return this.half3X3;
          }
          public List<Float> getHalf3X4()
          {
            if (this.half3X4 == null)
              this.half3X4 = new ArrayList();
            return this.half3X4;
          }
          public List<Float> getHalf4X1()
          {
            if (this.half4X1 == null)
              this.half4X1 = new ArrayList();
            return this.half4X1;
          }
          public List<Float> getHalf4X2()
          {
            if (this.half4X2 == null)
              this.half4X2 = new ArrayList();
            return this.half4X2;
          }
          public List<Float> getHalf4X3()
          {
            if (this.half4X3 == null)
              this.half4X3 = new ArrayList();
            return this.half4X3;
          }
          public List<Float> getHalf4X4()
          {
            if (this.half4X4 == null)
              this.half4X4 = new ArrayList();
            return this.half4X4;
          }
          public Float getFixed()
          {
            return this.fixed;
          }
          public void setFixed(Float value)
          {
            this.fixed = value;
          }
          public Float getFixed1()
          {
            return this.fixed1;
          }
          public void setFixed1(Float value)
          {
            this.fixed1 = value;
          }
          public List<Float> getFixed2()
          {
            if (this.fixed2 == null)
              this.fixed2 = new ArrayList();
            return this.fixed2;
          }
          public List<Float> getFixed3()
          {
            if (this.fixed3 == null)
              this.fixed3 = new ArrayList();
            return this.fixed3;
          }
          public List<Float> getFixed4()
          {
            if (this.fixed4 == null)
              this.fixed4 = new ArrayList();
            return this.fixed4;
          }
          public List<Float> getFixed1X1()
          {
            if (this.fixed1X1 == null)
              this.fixed1X1 = new ArrayList();
            return this.fixed1X1;
          }
          public List<Float> getFixed1X2()
          {
            if (this.fixed1X2 == null)
              this.fixed1X2 = new ArrayList();
            return this.fixed1X2;
          }
          public List<Float> getFixed1X3()
          {
            if (this.fixed1X3 == null)
              this.fixed1X3 = new ArrayList();
            return this.fixed1X3;
          }
          public List<Float> getFixed1X4()
          {
            if (this.fixed1X4 == null)
              this.fixed1X4 = new ArrayList();
            return this.fixed1X4;
          }
          public List<Float> getFixed2X1()
          {
            if (this.fixed2X1 == null)
              this.fixed2X1 = new ArrayList();
            return this.fixed2X1;
          }
          public List<Float> getFixed2X2()
          {
            if (this.fixed2X2 == null)
              this.fixed2X2 = new ArrayList();
            return this.fixed2X2;
          }
          public List<Float> getFixed2X3()
          {
            if (this.fixed2X3 == null)
              this.fixed2X3 = new ArrayList();
            return this.fixed2X3;
          }
          public List<Float> getFixed2X4()
          {
            if (this.fixed2X4 == null)
              this.fixed2X4 = new ArrayList();
            return this.fixed2X4;
          }
          public List<Float> getFixed3X1()
          {
            if (this.fixed3X1 == null)
              this.fixed3X1 = new ArrayList();
            return this.fixed3X1;
          }
          public List<Float> getFixed3X2()
          {
            if (this.fixed3X2 == null)
              this.fixed3X2 = new ArrayList();
            return this.fixed3X2;
          }
          public List<Float> getFixed3X3()
          {
            if (this.fixed3X3 == null)
              this.fixed3X3 = new ArrayList();
            return this.fixed3X3;
          }
          public List<Float> getFixed3X4()
          {
            if (this.fixed3X4 == null)
              this.fixed3X4 = new ArrayList();
            return this.fixed3X4;
          }
          public List<Float> getFixed4X1()
          {
            if (this.fixed4X1 == null)
              this.fixed4X1 = new ArrayList();
            return this.fixed4X1;
          }
          public List<Float> getFixed4X2()
          {
            if (this.fixed4X2 == null)
              this.fixed4X2 = new ArrayList();
            return this.fixed4X2;
          }
          public List<Float> getFixed4X3()
          {
            if (this.fixed4X3 == null)
              this.fixed4X3 = new ArrayList();
            return this.fixed4X3;
          }
          public List<Float> getFixed4X4()
          {
            if (this.fixed4X4 == null)
              this.fixed4X4 = new ArrayList();
            return this.fixed4X4;
          }
          public CgSurfaceType getSurface()
          {
            return this.surface;
          }
          public void setSurface(CgSurfaceType value)
          {
            this.surface = value;
          }
          public CgSampler1D getSampler1D()
          {
            return this.sampler1D;
          }
          public void setSampler1D(CgSampler1D value)
          {
            this.sampler1D = value;
          }
          public CgSampler2D getSampler2D()
          {
            return this.sampler2D;
          }
          public void setSampler2D(CgSampler2D value)
          {
            this.sampler2D = value;
          }
          public CgSampler3D getSampler3D()
          {
            return this.sampler3D;
          }
          public void setSampler3D(CgSampler3D value)
          {
            this.sampler3D = value;
          }
          public CgSamplerRECT getSamplerRECT()
          {
            return this.samplerRECT;
          }
          public void setSamplerRECT(CgSamplerRECT value)
          {
            this.samplerRECT = value;
          }
          public CgSamplerCUBE getSamplerCUBE()
          {
            return this.samplerCUBE;
          }
          public void setSamplerCUBE(CgSamplerCUBE value)
          {
            this.samplerCUBE = value;
          }
          public CgSamplerDEPTH getSamplerDEPTH()
          {
            return this.samplerDEPTH;
          }
          public void setSamplerDEPTH(CgSamplerDEPTH value)
          {
            this.samplerDEPTH = value;
          }
          public String getString()
          {
            return this.string;
          }
          public void setString(String value)
          {
            this.string = value;
          }
          public String getEnum()
          {
            return this._enum;
          }
          public void setEnum(String value)
          {
            this._enum = value;
          }
          public Param getParam()
          {
            return this.param;
          }
          public void setParam(Param value)
          {
            this.param = value;
          }
          public String getSymbol()
          {
            return this.symbol;
          }
          public void setSymbol(String value)
          {
            this.symbol = value;
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
        }
      }
    }
  }
}

