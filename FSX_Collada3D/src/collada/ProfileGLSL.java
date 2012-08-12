
package collada;
import java.math.BigInteger;
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
public class ProfileGLSL
{
  protected Asset asset;
  @XmlElements({@XmlElement(name="code", type=FxCodeProfile.class), @XmlElement(name="include", type=FxIncludeCommon.class)})
  protected List<Object> codeOrInclude;
  @XmlElements({@XmlElement(name="image", type=Image.class), @XmlElement(name="newparam", type=GlslNewparam.class)})
  protected List<Object> imageOrNewparam;
  @XmlElement(required=true)
  protected List<Technique> technique;
  protected List<Extra> extra;
  @XmlAttribute
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlID
  protected String id;
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
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name="", propOrder={"annotate", "codeOrInclude", "imageOrNewparamOrSetparam", "pass", "extra"})
  public static class Technique
  {
    protected List<FxAnnotateCommon> annotate;
    @XmlElements({@XmlElement(name="include", type=FxIncludeCommon.class), @XmlElement(name="code", type=FxCodeProfile.class)})
    protected List<Object> codeOrInclude;
    @XmlElements({@XmlElement(name="setparam", type=GlslSetparam.class), @XmlElement(name="newparam", type=GlslNewparam.class), @XmlElement(name="image", type=Image.class)})
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
      @XmlElements({@XmlElement(name="textureCUBE", type=TextureCUBE.class), @XmlElement(name="fog_mode", type=FogMode.class), @XmlElement(name="normalize_enable", type=NormalizeEnable.class), @XmlElement(name="textureRECT", type=TextureRECT.class), @XmlElement(name="blend_func", type=BlendFunc.class), @XmlElement(name="light_model_color_control", type=LightModelColorControl.class), @XmlElement(name="texture3D", type=Texture3D.class), @XmlElement(name="alpha_test_enable", type=AlphaTestEnable.class), @XmlElement(name="logic_op_enable", type=LogicOpEnable.class), @XmlElement(name="depth_bounds_enable", type=DepthBoundsEnable.class), @XmlElement(name="light_model_ambient", type=LightModelAmbient.class), @XmlElement(name="point_smooth_enable", type=PointSmoothEnable.class), @XmlElement(name="fog_start", type=FogStart.class), @XmlElement(name="texture_env_color", type=TextureEnvColor.class), @XmlElement(name="polygon_offset_fill_enable", type=PolygonOffsetFillEnable.class), @XmlElement(name="scissor", type=Scissor.class), @XmlElement(name="cull_face_enable", type=CullFaceEnable.class), @XmlElement(name="light_spot_cutoff", type=LightSpotCutoff.class), @XmlElement(name="material_emission", type=MaterialEmission.class), @XmlElement(name="line_stipple", type=LineStipple.class), @XmlElement(name="fog_color", type=FogColor.class), @XmlElement(name="shade_model", type=ShadeModel.class), @XmlElement(name="color_logic_op_enable", type=ColorLogicOpEnable.class), @XmlElement(name="light_position", type=LightPosition.class), @XmlElement(name="light_linear_attenuation", type=LightLinearAttenuation.class), @XmlElement(name="material_shininess", type=MaterialShininess.class), @XmlElement(name="stencil_test_enable", type=StencilTestEnable.class), @XmlElement(name="textureDEPTH", type=TextureDEPTH.class), @XmlElement(name="point_fade_threshold_size", type=PointFadeThresholdSize.class), @XmlElement(name="stencil_op", type=StencilOp.class), @XmlElement(name="stencil_func_separate", type=StencilFuncSeparate.class), @XmlElement(name="depth_test_enable", type=DepthTestEnable.class), @XmlElement(name="light_model_local_viewer_enable", type=LightModelLocalViewerEnable.class), @XmlElement(name="blend_func_separate", type=BlendFuncSeparate.class), @XmlElement(name="polygon_offset_point_enable", type=PolygonOffsetPointEnable.class), @XmlElement(name="material_specular", type=MaterialSpecular.class), @XmlElement(name="auto_normal_enable", type=AutoNormalEnable.class), @XmlElement(name="blend_color", type=BlendColor.class), @XmlElement(name="light_constant_attenuation", type=LightConstantAttenuation.class), @XmlElement(name="color_material", type=ColorMaterial.class), @XmlElement(name="textureCUBE_enable", type=TextureCUBEEnable.class), @XmlElement(name="polygon_stipple_enable", type=PolygonStippleEnable.class), @XmlElement(name="front_face", type=FrontFace.class), @XmlElement(name="sample_coverage_enable", type=SampleCoverageEnable.class), @XmlElement(name="stencil_func", type=StencilFunc.class), @XmlElement(name="scissor_test_enable", type=ScissorTestEnable.class), @XmlElement(name="polygon_offset_line_enable", type=PolygonOffsetLineEnable.class), @XmlElement(name="color_mask", type=ColorMask.class), @XmlElement(name="depth_mask", type=DepthMask.class), @XmlElement(name="color_material_enable", type=ColorMaterialEnable.class), @XmlElement(name="light_model_two_side_enable", type=LightModelTwoSideEnable.class), @XmlElement(name="texture2D", type=Texture2D.class), @XmlElement(name="texture1D", type=Texture1D.class), @XmlElement(name="multisample_enable", type=MultisampleEnable.class), @XmlElement(name="polygon_smooth_enable", type=PolygonSmoothEnable.class), @XmlElement(name="logic_op", type=LogicOp.class), @XmlElement(name="shader", type=Shader.class), @XmlElement(name="depth_clamp_enable", type=DepthClampEnable.class), @XmlElement(name="light_ambient", type=LightAmbient.class), @XmlElement(name="fog_enable", type=FogEnable.class), @XmlElement(name="blend_equation_separate", type=BlendEquationSeparate.class), @XmlElement(name="fog_end", type=FogEnd.class), @XmlElement(name="texture_env_mode", type=TextureEnvMode.class), @XmlElement(name="fog_coord_src", type=FogCoordSrc.class), @XmlElement(name="depth_bounds", type=DepthBounds.class), @XmlElement(name="line_smooth_enable", type=LineSmoothEnable.class), @XmlElement(name="projection_matrix", type=ProjectionMatrix.class), @XmlElement(name="light_diffuse", type=LightDiffuse.class), @XmlElement(name="texture3D_enable", type=Texture3DEnable.class), @XmlElement(name="material_diffuse", type=MaterialDiffuse.class), @XmlElement(name="light_spot_direction", type=LightSpotDirection.class), @XmlElement(name="rescale_normal_enable", type=RescaleNormalEnable.class), @XmlElement(name="polygon_mode", type=PolygonMode.class), @XmlElement(name="blend_equation", type=BlendEquation.class), @XmlElement(name="gl_hook_abstract"), @XmlElement(name="sample_alpha_to_coverage_enable", type=SampleAlphaToCoverageEnable.class), @XmlElement(name="textureRECT_enable", type=TextureRECTEnable.class), @XmlElement(name="sample_alpha_to_one_enable", type=SampleAlphaToOneEnable.class), @XmlElement(name="clear_color", type=ClearColor.class), @XmlElement(name="fog_density", type=FogDensity.class), @XmlElement(name="point_size_max", type=PointSizeMax.class), @XmlElement(name="texture2D_enable", type=Texture2DEnable.class), @XmlElement(name="light_enable", type=LightEnable.class), @XmlElement(name="depth_range", type=DepthRange.class), @XmlElement(name="light_spot_exponent", type=LightSpotExponent.class), @XmlElement(name="clip_plane", type=ClipPlane.class), @XmlElement(name="model_view_matrix", type=ModelViewMatrix.class), @XmlElement(name="material_ambient", type=MaterialAmbient.class), @XmlElement(name="point_size_min", type=PointSizeMin.class), @XmlElement(name="stencil_mask_separate", type=StencilMaskSeparate.class), @XmlElement(name="textureDEPTH_enable", type=TextureDEPTHEnable.class), @XmlElement(name="alpha_func", type=AlphaFunc.class), @XmlElement(name="light_quadratic_attenuation", type=LightQuadraticAttenuation.class), @XmlElement(name="clear_depth", type=ClearDepth.class), @XmlElement(name="clip_plane_enable", type=ClipPlaneEnable.class), @XmlElement(name="polygon_offset", type=PolygonOffset.class), @XmlElement(name="line_stipple_enable", type=LineStippleEnable.class), @XmlElement(name="texture1D_enable", type=Texture1DEnable.class), @XmlElement(name="dither_enable", type=DitherEnable.class), @XmlElement(name="depth_func", type=DepthFunc.class), @XmlElement(name="line_width", type=LineWidth.class), @XmlElement(name="clear_stencil", type=ClearStencil.class), @XmlElement(name="stencil_mask", type=StencilMask.class), @XmlElement(name="point_size", type=PointSize.class), @XmlElement(name="blend_enable", type=BlendEnable.class), @XmlElement(name="light_specular", type=LightSpecular.class), @XmlElement(name="cull_face", type=CullFace.class), @XmlElement(name="stencil_op_separate", type=StencilOpSeparate.class), @XmlElement(name="lighting_enable", type=LightingEnable.class), @XmlElement(name="point_distance_attenuation", type=PointDistanceAttenuation.class)})
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
      @XmlType(name="")
      public static class TextureRECTEnable
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"value", "param"})
      public static class TextureRECT
      {
        protected GlSamplerRECT value;
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute(required=true)
        protected BigInteger index;
        public GlSamplerRECT getValue()
        {
          return this.value;
        }
        public void setValue(GlSamplerRECT value)
        {
          this.value = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class TextureEnvMode
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected String value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
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
      @XmlType(name="")
      public static class TextureEnvColor
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class TextureDEPTHEnable
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"value", "param"})
      public static class TextureDEPTH
      {
        protected GlSamplerDEPTH value;
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute(required=true)
        protected BigInteger index;
        public GlSamplerDEPTH getValue()
        {
          return this.value;
        }
        public void setValue(GlSamplerDEPTH value)
        {
          this.value = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class TextureCUBEEnable
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"value", "param"})
      public static class TextureCUBE
      {
        protected GlSamplerCUBE value;
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute(required=true)
        protected BigInteger index;
        public GlSamplerCUBE getValue()
        {
          return this.value;
        }
        public void setValue(GlSamplerCUBE value)
        {
          this.value = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class Texture3DEnable
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"value", "param"})
      public static class Texture3D
      {
        protected GlSampler3D value;
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute(required=true)
        protected BigInteger index;
        public GlSampler3D getValue()
        {
          return this.value;
        }
        public void setValue(GlSampler3D value)
        {
          this.value = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class Texture2DEnable
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"value", "param"})
      public static class Texture2D
      {
        protected GlSampler2D value;
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute(required=true)
        protected BigInteger index;
        public GlSampler2D getValue()
        {
          return this.value;
        }
        public void setValue(GlSampler2D value)
        {
          this.value = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class Texture1DEnable
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"value", "param"})
      public static class Texture1D
      {
        protected GlSampler1D value;
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute(required=true)
        protected BigInteger index;
        public GlSampler1D getValue()
        {
          return this.value;
        }
        public void setValue(GlSampler1D value)
        {
          this.value = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class StencilTestEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"face", "fail", "zfail", "zpass"})
      public static class StencilOpSeparate
      {
        @XmlElement(required=true)
        protected Face face;
        @XmlElement(required=true)
        protected Fail fail;
        @XmlElement(required=true)
        protected Zfail zfail;
        @XmlElement(required=true)
        protected Zpass zpass;
        public Face getFace()
        {
          return this.face;
        }
        public void setFace(Face value)
        {
          this.face = value;
        }
        public Fail getFail()
        {
          return this.fail;
        }
        public void setFail(Fail value)
        {
          this.fail = value;
        }
        public Zfail getZfail()
        {
          return this.zfail;
        }
        public void setZfail(Zfail value)
        {
          this.zfail = value;
        }
        public Zpass getZpass()
        {
          return this.zpass;
        }
        public void setZpass(Zpass value)
        {
          this.zpass = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Zpass
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlStencilOpType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlStencilOpType getValue()
          {
            if (this.value == null)
              return GlStencilOpType.KEEP;
            return this.value;
          }
          public void setValue(GlStencilOpType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Zfail
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlStencilOpType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlStencilOpType getValue()
          {
            if (this.value == null)
              return GlStencilOpType.KEEP;
            return this.value;
          }
          public void setValue(GlStencilOpType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Fail
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlStencilOpType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlStencilOpType getValue()
          {
            if (this.value == null)
              return GlStencilOpType.KEEP;
            return this.value;
          }
          public void setValue(GlStencilOpType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Face
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFaceType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFaceType getValue()
          {
            if (this.value == null)
              return GlFaceType.FRONT_AND_BACK;
            return this.value;
          }
          public void setValue(GlFaceType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"fail", "zfail", "zpass"})
      public static class StencilOp
      {
        @XmlElement(required=true)
        protected Fail fail;
        @XmlElement(required=true)
        protected Zfail zfail;
        @XmlElement(required=true)
        protected Zpass zpass;
        public Fail getFail()
        {
          return this.fail;
        }
        public void setFail(Fail value)
        {
          this.fail = value;
        }
        public Zfail getZfail()
        {
          return this.zfail;
        }
        public void setZfail(Zfail value)
        {
          this.zfail = value;
        }
        public Zpass getZpass()
        {
          return this.zpass;
        }
        public void setZpass(Zpass value)
        {
          this.zpass = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Zpass
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlStencilOpType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlStencilOpType getValue()
          {
            if (this.value == null)
              return GlStencilOpType.KEEP;
            return this.value;
          }
          public void setValue(GlStencilOpType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Zfail
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlStencilOpType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlStencilOpType getValue()
          {
            if (this.value == null)
              return GlStencilOpType.KEEP;
            return this.value;
          }
          public void setValue(GlStencilOpType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Fail
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlStencilOpType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlStencilOpType getValue()
          {
            if (this.value == null)
              return GlStencilOpType.KEEP;
            return this.value;
          }
          public void setValue(GlStencilOpType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"face", "mask"})
      public static class StencilMaskSeparate
      {
        @XmlElement(required=true)
        protected Face face;
        @XmlElement(required=true)
        protected Mask mask;
        public Face getFace()
        {
          return this.face;
        }
        public void setFace(Face value)
        {
          this.face = value;
        }
        public Mask getMask()
        {
          return this.mask;
        }
        public void setMask(Mask value)
        {
          this.mask = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Mask
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected Short value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public short getValue()
          {
            if (this.value == null)
              return 255;
            return this.value.shortValue();
          }
          public void setValue(Short value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Face
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFaceType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFaceType getValue()
          {
            if (this.value == null)
              return GlFaceType.FRONT_AND_BACK;
            return this.value;
          }
          public void setValue(GlFaceType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class StencilMask
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Long value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public long getValue()
        {
          if (this.value == null)
            return -1;
          return this.value.longValue();
        }
        public void setValue(Long value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"front", "back", "ref", "mask"})
      public static class StencilFuncSeparate
      {
        @XmlElement(required=true)
        protected Front front;
        @XmlElement(required=true)
        protected Back back;
        @XmlElement(required=true)
        protected Ref ref;
        @XmlElement(required=true)
        protected Mask mask;
        public Front getFront()
        {
          return this.front;
        }
        public void setFront(Front value)
        {
          this.front = value;
        }
        public Back getBack()
        {
          return this.back;
        }
        public void setBack(Back value)
        {
          this.back = value;
        }
        public Ref getRef()
        {
          return this.ref;
        }
        public void setRef(Ref value)
        {
          this.ref = value;
        }
        public Mask getMask()
        {
          return this.mask;
        }
        public void setMask(Mask value)
        {
          this.mask = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Ref
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected Short value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public short getValue()
          {
            if (this.value == null)
              return 0;
            return this.value.shortValue();
          }
          public void setValue(Short value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Mask
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected Short value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public short getValue()
          {
            if (this.value == null)
              return 255;
            return this.value.shortValue();
          }
          public void setValue(Short value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Front
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFuncType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFuncType getValue()
          {
            if (this.value == null)
              return GlFuncType.ALWAYS;
            return this.value;
          }
          public void setValue(GlFuncType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Back
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFuncType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFuncType getValue()
          {
            if (this.value == null)
              return GlFuncType.ALWAYS;
            return this.value;
          }
          public void setValue(GlFuncType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"func", "ref", "mask"})
      public static class StencilFunc
      {
        @XmlElement(required=true)
        protected Func func;
        @XmlElement(required=true)
        protected Ref ref;
        @XmlElement(required=true)
        protected Mask mask;
        public Func getFunc()
        {
          return this.func;
        }
        public void setFunc(Func value)
        {
          this.func = value;
        }
        public Ref getRef()
        {
          return this.ref;
        }
        public void setRef(Ref value)
        {
          this.ref = value;
        }
        public Mask getMask()
        {
          return this.mask;
        }
        public void setMask(Mask value)
        {
          this.mask = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Ref
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected Short value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public short getValue()
          {
            if (this.value == null)
              return 0;
            return this.value.shortValue();
          }
          public void setValue(Short value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Mask
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected Short value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public short getValue()
          {
            if (this.value == null)
              return 255;
            return this.value.shortValue();
          }
          public void setValue(Short value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Func
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFuncType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFuncType getValue()
          {
            if (this.value == null)
              return GlFuncType.ALWAYS;
            return this.value;
          }
          public void setValue(GlFuncType value)
          {
            this.value = value;
          }
        }
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
        protected GlslPipelineStage stage;
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
        public GlslPipelineStage getStage()
        {
          return this.stage;
        }
        public void setStage(GlslPipelineStage value)
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
        @XmlType(name="", propOrder={"bool", "bool2", "bool3", "bool4", "_float", "float2", "float3", "float4", "float2X2", "float3X3", "float4X4", "_int", "int2", "int3", "int4", "surface", "sampler1D", "sampler2D", "sampler3D", "samplerCUBE", "samplerRECT", "samplerDEPTH", "_enum", "param"})
        public static class Bind
        {
          protected Boolean bool;
          @XmlList
          @XmlElement(type=Boolean.class)
          protected List<Boolean> bool2;
          @XmlList
          @XmlElement(type=Boolean.class)
          protected List<Boolean> bool3;
          @XmlList
          @XmlElement(type=Boolean.class)
          protected List<Boolean> bool4;
          @XmlElement(name="float")
          protected Float _float;
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
          @XmlElement(name="float2x2", type=Float.class)
          protected List<Float> float2X2;
          @XmlList
          @XmlElement(name="float3x3", type=Float.class)
          protected List<Float> float3X3;
          @XmlList
          @XmlElement(name="float4x4", type=Float.class)
          protected List<Float> float4X4;
          @XmlElement(name="int")
          protected Integer _int;
          @XmlList
          @XmlElement(type=Integer.class)
          protected List<Integer> int2;
          @XmlList
          @XmlElement(type=Integer.class)
          protected List<Integer> int3;
          @XmlList
          @XmlElement(type=Integer.class)
          protected List<Integer> int4;
          protected GlslSurfaceType surface;
          protected GlSampler1D sampler1D;
          protected GlSampler2D sampler2D;
          protected GlSampler3D sampler3D;
          protected GlSamplerCUBE samplerCUBE;
          protected GlSamplerRECT samplerRECT;
          protected GlSamplerDEPTH samplerDEPTH;
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
          public Float getFloat()
          {
            return this._float;
          }
          public void setFloat(Float value)
          {
            this._float = value;
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
          public List<Float> getFloat2X2()
          {
            if (this.float2X2 == null)
              this.float2X2 = new ArrayList();
            return this.float2X2;
          }
          public List<Float> getFloat3X3()
          {
            if (this.float3X3 == null)
              this.float3X3 = new ArrayList();
            return this.float3X3;
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
          public GlslSurfaceType getSurface()
          {
            return this.surface;
          }
          public void setSurface(GlslSurfaceType value)
          {
            this.surface = value;
          }
          public GlSampler1D getSampler1D()
          {
            return this.sampler1D;
          }
          public void setSampler1D(GlSampler1D value)
          {
            this.sampler1D = value;
          }
          public GlSampler2D getSampler2D()
          {
            return this.sampler2D;
          }
          public void setSampler2D(GlSampler2D value)
          {
            this.sampler2D = value;
          }
          public GlSampler3D getSampler3D()
          {
            return this.sampler3D;
          }
          public void setSampler3D(GlSampler3D value)
          {
            this.sampler3D = value;
          }
          public GlSamplerCUBE getSamplerCUBE()
          {
            return this.samplerCUBE;
          }
          public void setSamplerCUBE(GlSamplerCUBE value)
          {
            this.samplerCUBE = value;
          }
          public GlSamplerRECT getSamplerRECT()
          {
            return this.samplerRECT;
          }
          public void setSamplerRECT(GlSamplerRECT value)
          {
            this.samplerRECT = value;
          }
          public GlSamplerDEPTH getSamplerDEPTH()
          {
            return this.samplerDEPTH;
          }
          public void setSamplerDEPTH(GlSamplerDEPTH value)
          {
            this.samplerDEPTH = value;
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
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ShadeModel
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlShadeModelType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlShadeModelType getValue()
        {
          if (this.value == null)
            return GlShadeModelType.SMOOTH;
          return this.value;
        }
        public void setValue(GlShadeModelType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ScissorTestEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class Scissor
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Long> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Long> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class SampleCoverageEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class SampleAlphaToOneEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class SampleAlphaToCoverageEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class RescaleNormalEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ProjectionMatrix
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PolygonStippleEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PolygonSmoothEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PolygonOffsetPointEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PolygonOffsetLineEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PolygonOffsetFillEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PolygonOffset
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"face", "mode"})
      public static class PolygonMode
      {
        @XmlElement(required=true)
        protected Face face;
        @XmlElement(required=true)
        protected Mode mode;
        public Face getFace()
        {
          return this.face;
        }
        public void setFace(Face value)
        {
          this.face = value;
        }
        public Mode getMode()
        {
          return this.mode;
        }
        public void setMode(Mode value)
        {
          this.mode = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Mode
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlPolygonModeType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlPolygonModeType getValue()
          {
            if (this.value == null)
              return GlPolygonModeType.FILL;
            return this.value;
          }
          public void setValue(GlPolygonModeType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Face
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFaceType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFaceType getValue()
          {
            if (this.value == null)
              return GlFaceType.FRONT_AND_BACK;
            return this.value;
          }
          public void setValue(GlFaceType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PointSmoothEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PointSizeMin
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 0D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PointSizeMax
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PointSize
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PointFadeThresholdSize
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class PointDistanceAttenuation
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class NormalizeEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class MultisampleEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ModelViewMatrix
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class MaterialSpecular
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class MaterialShininess
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 0D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class MaterialEmission
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class MaterialDiffuse
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class MaterialAmbient
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LogicOpEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LogicOp
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlLogicOpType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlLogicOpType getValue()
        {
          if (this.value == null)
            return GlLogicOpType.COPY;
          return this.value;
        }
        public void setValue(GlLogicOpType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LineWidth
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LineStippleEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LineStipple
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Long> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Long> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LineSmoothEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightSpotExponent
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 0D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightSpotDirection
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightSpotCutoff
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 180.0D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightSpecular
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightQuadraticAttenuation
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 0D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightPosition
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightModelTwoSideEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightModelLocalViewerEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightModelColorControl
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlLightModelColorControlType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlLightModelColorControlType getValue()
        {
          if (this.value == null)
            return GlLightModelColorControlType.SINGLE_COLOR;
          return this.value;
        }
        public void setValue(GlLightModelColorControlType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightModelAmbient
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightLinearAttenuation
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 0D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightingEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightEnable
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightDiffuse
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightConstantAttenuation
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class LightAmbient
      {
        @XmlAttribute(required=true)
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FrontFace
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlFrontFaceType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlFrontFaceType getValue()
        {
          if (this.value == null)
            return GlFrontFaceType.CCW;
          return this.value;
        }
        public void setValue(GlFrontFaceType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FogStart
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 0D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FogMode
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlFogType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlFogType getValue()
        {
          if (this.value == null)
            return GlFogType.EXP;
          return this.value;
        }
        public void setValue(GlFogType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FogEnd
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FogEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FogDensity
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FogCoordSrc
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlFogCoordSrcType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlFogCoordSrcType getValue()
        {
          if (this.value == null)
            return GlFogCoordSrcType.FOG_COORDINATE;
          return this.value;
        }
        public void setValue(GlFogCoordSrcType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class FogColor
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DitherEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return true;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DepthTestEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DepthRange
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DepthMask
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return true;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DepthFunc
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlFuncType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlFuncType getValue()
        {
          if (this.value == null)
            return GlFuncType.ALWAYS;
          return this.value;
        }
        public void setValue(GlFuncType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DepthClampEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DepthBoundsEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class DepthBounds
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class CullFaceEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class CullFace
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlFaceType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlFaceType getValue()
        {
          if (this.value == null)
            return GlFaceType.BACK;
          return this.value;
        }
        public void setValue(GlFaceType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ColorMaterialEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return true;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"face", "mode"})
      public static class ColorMaterial
      {
        @XmlElement(required=true)
        protected Face face;
        @XmlElement(required=true)
        protected Mode mode;
        public Face getFace()
        {
          return this.face;
        }
        public void setFace(Face value)
        {
          this.face = value;
        }
        public Mode getMode()
        {
          return this.mode;
        }
        public void setMode(Mode value)
        {
          this.mode = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Mode
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlMaterialType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlMaterialType getValue()
          {
            if (this.value == null)
              return GlMaterialType.AMBIENT_AND_DIFFUSE;
            return this.value;
          }
          public void setValue(GlMaterialType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Face
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFaceType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFaceType getValue()
          {
            if (this.value == null)
              return GlFaceType.FRONT_AND_BACK;
            return this.value;
          }
          public void setValue(GlFaceType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ColorMask
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Boolean> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Boolean> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ColorLogicOpEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ClipPlaneEnable
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ClipPlane
      {
        @XmlAttribute
        protected BigInteger index;
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public BigInteger getIndex()
        {
          return this.index;
        }
        public void setIndex(BigInteger value)
        {
          this.index = value;
        }
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ClearStencil
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Long value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public long getValue()
        {
          if (this.value == null)
            return 0L;
          return this.value.longValue();
        }
        public void setValue(Long value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ClearDepth
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Double value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public double getValue()
        {
          if (this.value == null)
            return 1D;
          return this.value.doubleValue();
        }
        public void setValue(Double value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class ClearColor
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"srcRgb", "destRgb", "srcAlpha", "destAlpha"})
      public static class BlendFuncSeparate
      {
        @XmlElement(name="src_rgb", required=true)
        protected SrcRgb srcRgb;
        @XmlElement(name="dest_rgb", required=true)
        protected DestRgb destRgb;
        @XmlElement(name="src_alpha", required=true)
        protected SrcAlpha srcAlpha;
        @XmlElement(name="dest_alpha", required=true)
        protected DestAlpha destAlpha;
        public SrcRgb getSrcRgb()
        {
          return this.srcRgb;
        }
        public void setSrcRgb(SrcRgb value)
        {
          this.srcRgb = value;
        }
        public DestRgb getDestRgb()
        {
          return this.destRgb;
        }
        public void setDestRgb(DestRgb value)
        {
          this.destRgb = value;
        }
        public SrcAlpha getSrcAlpha()
        {
          return this.srcAlpha;
        }
        public void setSrcAlpha(SrcAlpha value)
        {
          this.srcAlpha = value;
        }
        public DestAlpha getDestAlpha()
        {
          return this.destAlpha;
        }
        public void setDestAlpha(DestAlpha value)
        {
          this.destAlpha = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class SrcRgb
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendType getValue()
          {
            if (this.value == null)
              return GlBlendType.ONE;
            return this.value;
          }
          public void setValue(GlBlendType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class SrcAlpha
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendType getValue()
          {
            if (this.value == null)
              return GlBlendType.ONE;
            return this.value;
          }
          public void setValue(GlBlendType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class DestRgb
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendType getValue()
          {
            if (this.value == null)
              return GlBlendType.ZERO;
            return this.value;
          }
          public void setValue(GlBlendType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class DestAlpha
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendType getValue()
          {
            if (this.value == null)
              return GlBlendType.ZERO;
            return this.value;
          }
          public void setValue(GlBlendType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"src", "dest"})
      public static class BlendFunc
      {
        @XmlElement(required=true)
        protected Src src;
        @XmlElement(required=true)
        protected Dest dest;
        public Src getSrc()
        {
          return this.src;
        }
        public void setSrc(Src value)
        {
          this.src = value;
        }
        public Dest getDest()
        {
          return this.dest;
        }
        public void setDest(Dest value)
        {
          this.dest = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Src
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendType getValue()
          {
            if (this.value == null)
              return GlBlendType.ONE;
            return this.value;
          }
          public void setValue(GlBlendType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Dest
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendType getValue()
          {
            if (this.value == null)
              return GlBlendType.ZERO;
            return this.value;
          }
          public void setValue(GlBlendType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"rgb", "alpha"})
      public static class BlendEquationSeparate
      {
        @XmlElement(required=true)
        protected Rgb rgb;
        @XmlElement(required=true)
        protected Alpha alpha;
        public Rgb getRgb()
        {
          return this.rgb;
        }
        public void setRgb(Rgb value)
        {
          this.rgb = value;
        }
        public Alpha getAlpha()
        {
          return this.alpha;
        }
        public void setAlpha(Alpha value)
        {
          this.alpha = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Rgb
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendEquationType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendEquationType getValue()
          {
            if (this.value == null)
              return GlBlendEquationType.FUNC_ADD;
            return this.value;
          }
          public void setValue(GlBlendEquationType value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Alpha
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlBlendEquationType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlBlendEquationType getValue()
          {
            if (this.value == null)
              return GlBlendEquationType.FUNC_ADD;
            return this.value;
          }
          public void setValue(GlBlendEquationType value)
          {
            this.value = value;
          }
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class BlendEquation
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected GlBlendEquationType value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public GlBlendEquationType getValue()
        {
          if (this.value == null)
            return GlBlendEquationType.FUNC_ADD;
          return this.value;
        }
        public void setValue(GlBlendEquationType value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class BlendEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class BlendColor
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected List<Double> value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public List<Double> getValue()
        {
          if (this.value == null)
            this.value = new ArrayList();
          return this.value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class AutoNormalEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="")
      public static class AlphaTestEnable
      {
        @XmlAttribute
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String param;
        @XmlAttribute
        protected Boolean value;
        public String getParam()
        {
          return this.param;
        }
        public void setParam(String value)
        {
          this.param = value;
        }
        public boolean isValue()
        {
          if (this.value == null)
            return false;
          return this.value.booleanValue();
        }
        public void setValue(Boolean value)
        {
          this.value = value;
        }
      }
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name="", propOrder={"func", "value"})
      public static class AlphaFunc
      {
        @XmlElement(required=true)
        protected Func func;
        @XmlElement(required=true)
        protected Value value;
        public Func getFunc()
        {
          return this.func;
        }
        public void setFunc(Func value)
        {
          this.func = value;
        }
        public Value getValue()
        {
          return this.value;
        }
        public void setValue(Value value)
        {
          this.value = value;
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Value
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected Float value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public float getValue()
          {
            if (this.value == null)
              return 0F;
            return this.value.floatValue();
          }
          public void setValue(Float value)
          {
            this.value = value;
          }
        }
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name="")
        public static class Func
        {
          @XmlAttribute
          @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
          protected String param;
          @XmlAttribute
          protected GlFuncType value;
          public String getParam()
          {
            return this.param;
          }
          public void setParam(String value)
          {
            this.param = value;
          }
          public GlFuncType getValue()
          {
            if (this.value == null)
              return GlFuncType.ALWAYS;
            return this.value;
          }
          public void setValue(GlFuncType value)
          {
            this.value = value;
          }
        }
      }
    }
  }
}

