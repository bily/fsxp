
package collada;
import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;
@XmlRegistry
public class ObjectFactory
{
  private static final QName _CameraOpticsTechniqueCommonPerspectiveZfar_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "zfar");
  private static final QName _CameraOpticsTechniqueCommonPerspectiveXfov_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "xfov");
  private static final QName _CameraOpticsTechniqueCommonPerspectiveYfov_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "yfov");
  private static final QName _CameraOpticsTechniqueCommonPerspectiveAspectRatio_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "aspect_ratio");
  private static final QName _CameraOpticsTechniqueCommonPerspectiveZnear_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "znear");
  private static final QName _Scale_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "scale");
  private static final QName _ProfileGLSL_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_GLSL");
  private static final QName _InstanceCamera_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_camera");
  private static final QName _ProfileGLES_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_GLES");
  private static final QName _Translate_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "translate");
  private static final QName _P_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "p");
  private static final QName _InstanceLight_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_light");
  private static final QName _ProfileCG_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_CG");
  private static final QName _InstancePhysicsMaterial_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_physics_material");
  private static final QName _InstanceNode_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_node");
  private static final QName _GlHookAbstract_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "gl_hook_abstract");
  private static final QName _InstanceForceField_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "instance_force_field");
  private static final QName _ProfileCOMMON_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "profile_COMMON");
  private static final QName _FxProfileAbstract_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fx_profile_abstract");
  private static final QName _CgNewarrayTypeEnum_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "enum");
  private static final QName _CgNewarrayTypeInt1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x2");
  private static final QName _CgNewarrayTypeSampler1D_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "sampler1D");
  private static final QName _CgNewarrayTypeBool2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2");
  private static final QName _CgNewarrayTypeHalf1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x3");
  private static final QName _CgNewarrayTypeFloat3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3");
  private static final QName _CgNewarrayTypeFloat1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x1");
  private static final QName _CgNewarrayTypeFloat2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x3");
  private static final QName _CgNewarrayTypeFixed1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1");
  private static final QName _CgNewarrayTypeHalf3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x3");
  private static final QName _CgNewarrayTypeBool3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3");
  private static final QName _CgNewarrayTypeBool1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x4");
  private static final QName _CgNewarrayTypeFloat2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x4");
  private static final QName _CgNewarrayTypeFixed3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3");
  private static final QName _CgNewarrayTypeInt_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int");
  private static final QName _CgNewarrayTypeHalf3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x2");
  private static final QName _CgNewarrayTypeFixed4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x2");
  private static final QName _CgNewarrayTypeBool4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x2");
  private static final QName _CgNewarrayTypeInt2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x4");
  private static final QName _CgNewarrayTypeInt3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x2");
  private static final QName _CgNewarrayTypeHalf_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half");
  private static final QName _CgNewarrayTypeHalf3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x4");
  private static final QName _CgNewarrayTypeInt2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x3");
  private static final QName _CgNewarrayTypeFixed3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x3");
  private static final QName _CgNewarrayTypeFloat3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x3");
  private static final QName _CgNewarrayTypeFloat2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2");
  private static final QName _CgNewarrayTypeFixed_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed");
  private static final QName _CgNewarrayTypeInt1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x4");
  private static final QName _CgNewarrayTypeHalf4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x4");
  private static final QName _CgNewarrayTypeSamplerDEPTH_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "samplerDEPTH");
  private static final QName _CgNewarrayTypeInt1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x1");
  private static final QName _CgNewarrayTypeInt2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x1");
  private static final QName _CgNewarrayTypeFloat1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1");
  private static final QName _CgNewarrayTypeArray_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "array");
  private static final QName _CgNewarrayTypeHalf4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4");
  private static final QName _CgNewarrayTypeFixed1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x4");
  private static final QName _CgNewarrayTypeFixed2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x2");
  private static final QName _CgNewarrayTypeHalf4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x2");
  private static final QName _CgNewarrayTypeInt2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2");
  private static final QName _CgNewarrayTypeHalf1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1");
  private static final QName _CgNewarrayTypeFixed4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x1");
  private static final QName _CgNewarrayTypeFixed3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x4");
  private static final QName _CgNewarrayTypeBool2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x2");
  private static final QName _CgNewarrayTypeFixed2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x1");
  private static final QName _CgNewarrayTypeFloat4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x4");
  private static final QName _CgNewarrayTypeInt3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x3");
  private static final QName _CgNewarrayTypeFloat1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x2");
  private static final QName _CgNewarrayTypeInt1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1");
  private static final QName _CgNewarrayTypeFloat3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x1");
  private static final QName _CgNewarrayTypeBool2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x4");
  private static final QName _CgNewarrayTypeFloat1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x4");
  private static final QName _CgNewarrayTypeBool1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x2");
  private static final QName _CgNewarrayTypeFixed1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x1");
  private static final QName _CgNewarrayTypeInt3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3");
  private static final QName _CgNewarrayTypeHalf2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2");
  private static final QName _CgNewarrayTypeHalf4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x3");
  private static final QName _CgNewarrayTypeBool4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x4");
  private static final QName _CgNewarrayTypeBool2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x3");
  private static final QName _CgNewarrayTypeFixed1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x3");
  private static final QName _CgNewarrayTypeBool1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1");
  private static final QName _CgNewarrayTypeFloat1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float1x3");
  private static final QName _CgNewarrayTypeSampler2D_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "sampler2D");
  private static final QName _CgNewarrayTypeBool1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x3");
  private static final QName _CgNewarrayTypeBool_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool");
  private static final QName _CgNewarrayTypeString_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "string");
  private static final QName _CgNewarrayTypeUsertype_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "usertype");
  private static final QName _CgNewarrayTypeFloat4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x1");
  private static final QName _CgNewarrayTypeBool3X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x3");
  private static final QName _CgNewarrayTypeHalf4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half4x1");
  private static final QName _CgNewarrayTypeBool3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x4");
  private static final QName _CgNewarrayTypeFixed1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed1x2");
  private static final QName _CgNewarrayTypeFloat_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float");
  private static final QName _CgNewarrayTypeFixed3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x1");
  private static final QName _CgNewarrayTypeFloat4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x3");
  private static final QName _CgNewarrayTypeSamplerRECT_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "samplerRECT");
  private static final QName _CgNewarrayTypeBool3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x2");
  private static final QName _CgNewarrayTypeHalf2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x4");
  private static final QName _CgNewarrayTypeSampler3D_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "sampler3D");
  private static final QName _CgNewarrayTypeFixed4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x4");
  private static final QName _CgNewarrayTypeHalf2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x1");
  private static final QName _CgNewarrayTypeFloat4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4x2");
  private static final QName _CgNewarrayTypeFixed2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x3");
  private static final QName _CgNewarrayTypeConnectParam_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "connect_param");
  private static final QName _CgNewarrayTypeFixed4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4x3");
  private static final QName _CgNewarrayTypeHalf3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3");
  private static final QName _CgNewarrayTypeBool4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x3");
  private static final QName _CgNewarrayTypeInt4X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x2");
  private static final QName _CgNewarrayTypeFloat2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x1");
  private static final QName _CgNewarrayTypeSamplerCUBE_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "samplerCUBE");
  private static final QName _CgNewarrayTypeInt4X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x3");
  private static final QName _CgNewarrayTypeBool3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool3x1");
  private static final QName _CgNewarrayTypeInt2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int2x2");
  private static final QName _CgNewarrayTypeInt1X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int1x3");
  private static final QName _CgNewarrayTypeSurface_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "surface");
  private static final QName _CgNewarrayTypeFixed2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2");
  private static final QName _CgNewarrayTypeFloat3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x4");
  private static final QName _CgNewarrayTypeHalf1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x1");
  private static final QName _CgNewarrayTypeInt4X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x4");
  private static final QName _CgNewarrayTypeBool2X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool2x1");
  private static final QName _CgNewarrayTypeBool4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4");
  private static final QName _CgNewarrayTypeFixed3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed3x2");
  private static final QName _CgNewarrayTypeInt3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x1");
  private static final QName _CgNewarrayTypeFixed2X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed2x4");
  private static final QName _CgNewarrayTypeFixed4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "fixed4");
  private static final QName _CgNewarrayTypeFloat4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float4");
  private static final QName _CgNewarrayTypeHalf3X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half3x1");
  private static final QName _CgNewarrayTypeBool4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool4x1");
  private static final QName _CgNewarrayTypeHalf1X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x4");
  private static final QName _CgNewarrayTypeHalf1X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half1x2");
  private static final QName _CgNewarrayTypeInt3X4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int3x4");
  private static final QName _CgNewarrayTypeFloat3X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float3x2");
  private static final QName _CgNewarrayTypeHalf2X3_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x3");
  private static final QName _CgNewarrayTypeBool1X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "bool1x1");
  private static final QName _CgNewarrayTypeInt4X1_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4x1");
  private static final QName _CgNewarrayTypeFloat2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "float2x2");
  private static final QName _CgNewarrayTypeHalf2X2_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "half2x2");
  private static final QName _CgNewarrayTypeInt4_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "int4");
  private static final QName _PolygonsPhH_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "h");
  private static final QName _CameraOpticsTechniqueCommonOrthographicYmag_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "ymag");
  private static final QName _CameraOpticsTechniqueCommonOrthographicXmag_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "xmag");
  private static final QName _PolygonsPh_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "ph");
  private static final QName _VisualSceneEvaluateSceneRenderLayer_QNAME = new QName("http://www.collada.org/2005/11/COLLADASchema", "layer");
  public ProfileGLSL.Technique.Pass.Shader.Bind createProfileGLSLTechniquePassShaderBind()
  {
    return new ProfileGLSL.Technique.Pass.Shader.Bind();
  }
  public ProfileGLES.Technique.Pass.LightModelAmbient createProfileGLESTechniquePassLightModelAmbient()
  {
    return new ProfileGLES.Technique.Pass.LightModelAmbient();
  }
  public ProfileGLES.Technique.Pass.StencilOp createProfileGLESTechniquePassStencilOp()
  {
    return new ProfileGLES.Technique.Pass.StencilOp();
  }
  public Ellipsoid createEllipsoid()
  {
    return new Ellipsoid();
  }
  public FxSurfaceFormatHintCommon createFxSurfaceFormatHintCommon()
  {
    return new FxSurfaceFormatHintCommon();
  }
  public ProfileGLES.Technique.Pass.PointSize createProfileGLESTechniquePassPointSize()
  {
    return new ProfileGLES.Technique.Pass.PointSize();
  }
  public Camera.Optics.TechniqueCommon.Perspective createCameraOpticsTechniqueCommonPerspective()
  {
    return new Camera.Optics.TechniqueCommon.Perspective();
  }
  public ProfileGLSL.Technique.Pass.StencilFuncSeparate.Back createProfileGLSLTechniquePassStencilFuncSeparateBack()
  {
    return new ProfileGLSL.Technique.Pass.StencilFuncSeparate.Back();
  }
  public BoolArray createBoolArray()
  {
    return new BoolArray();
  }
  public PhysicsScene createPhysicsScene()
  {
    return new PhysicsScene();
  }
  public ProfileGLSL.Technique.Pass.SampleCoverageEnable createProfileGLSLTechniquePassSampleCoverageEnable()
  {
    return new ProfileGLSL.Technique.Pass.SampleCoverageEnable();
  }
  public Vertices createVertices()
  {
    return new Vertices();
  }
  public GlesTextureUnit createGlesTextureUnit()
  {
    return new GlesTextureUnit();
  }
  public FxSurfaceInitCubeCommon.Primary createFxSurfaceInitCubeCommonPrimary()
  {
    return new FxSurfaceInitCubeCommon.Primary();
  }
  public LibraryAnimationClips createLibraryAnimationClips()
  {
    return new LibraryAnimationClips();
  }
  public ProfileGLSL.Technique.Pass.TextureEnvMode createProfileGLSLTechniquePassTextureEnvMode()
  {
    return new ProfileGLSL.Technique.Pass.TextureEnvMode();
  }
  public ProfileCG.Technique.Pass.Shader.Name createProfileCGTechniquePassShaderName()
  {
    return new ProfileCG.Technique.Pass.Shader.Name();
  }
  public ProfileGLES.Technique.Pass.CullFace createProfileGLESTechniquePassCullFace()
  {
    return new ProfileGLES.Technique.Pass.CullFace();
  }
  public ProfileGLSL.Technique.Pass.LightEnable createProfileGLSLTechniquePassLightEnable()
  {
    return new ProfileGLSL.Technique.Pass.LightEnable();
  }
  public ProfileGLES.Technique.Pass.AlphaFunc.Func createProfileGLESTechniquePassAlphaFuncFunc()
  {
    return new ProfileGLES.Technique.Pass.AlphaFunc.Func();
  }
  public ProfileGLSL.Technique.Pass.ShadeModel createProfileGLSLTechniquePassShadeModel()
  {
    return new ProfileGLSL.Technique.Pass.ShadeModel();
  }
  public ProfileGLSL.Technique.Pass.PolygonOffset createProfileGLSLTechniquePassPolygonOffset()
  {
    return new ProfileGLSL.Technique.Pass.PolygonOffset();
  }
  public Skin createSkin()
  {
    return new Skin();
  }
  public GlSamplerCUBE createGlSamplerCUBE()
  {
    return new GlSamplerCUBE();
  }
  public ProfileGLES.Technique.Pass.FogStart createProfileGLESTechniquePassFogStart()
  {
    return new ProfileGLES.Technique.Pass.FogStart();
  }
  public NameArray createNameArray()
  {
    return new NameArray();
  }
  public InstanceRigidBody.TechniqueCommon.MassFrame createInstanceRigidBodyTechniqueCommonMassFrame()
  {
    return new InstanceRigidBody.TechniqueCommon.MassFrame();
  }
  public CgSetarrayType createCgSetarrayType()
  {
    return new CgSetarrayType();
  }
  public Morph createMorph()
  {
    return new Morph();
  }
  public ProfileCG.Technique.Pass createProfileCGTechniquePass()
  {
    return new ProfileCG.Technique.Pass();
  }
  public ProfileGLSL.Technique.Pass.MaterialDiffuse createProfileGLSLTechniquePassMaterialDiffuse()
  {
    return new ProfileGLSL.Technique.Pass.MaterialDiffuse();
  }
  public Skin.VertexWeights createSkinVertexWeights()
  {
    return new Skin.VertexWeights();
  }
  public ProfileCOMMON createProfileCOMMON()
  {
    return new ProfileCOMMON();
  }
  public ProfileGLSL.Technique.Pass.LightAmbient createProfileGLSLTechniquePassLightAmbient()
  {
    return new ProfileGLSL.Technique.Pass.LightAmbient();
  }
  public ProfileCOMMON.Technique.Blinn createProfileCOMMONTechniqueBlinn()
  {
    return new ProfileCOMMON.Technique.Blinn();
  }
  public RigidConstraint.TechniqueCommon.Limits.Linear createRigidConstraintTechniqueCommonLimitsLinear()
  {
    return new RigidConstraint.TechniqueCommon.Limits.Linear();
  }
  public Material createMaterial()
  {
    return new Material();
  }
  public ProfileGLES createProfileGLES()
  {
    return new ProfileGLES();
  }
  public ProfileGLSL.Technique.Pass.DepthClampEnable createProfileGLSLTechniquePassDepthClampEnable()
  {
    return new ProfileGLSL.Technique.Pass.DepthClampEnable();
  }
  public CgSampler3D createCgSampler3D()
  {
    return new CgSampler3D();
  }
  public ProfileGLSL.Technique.Pass.LineStipple createProfileGLSLTechniquePassLineStipple()
  {
    return new ProfileGLSL.Technique.Pass.LineStipple();
  }
  public InstanceEffect createInstanceEffect()
  {
    return new InstanceEffect();
  }
  public PhysicsMaterial.TechniqueCommon createPhysicsMaterialTechniqueCommon()
  {
    return new PhysicsMaterial.TechniqueCommon();
  }
  public ProfileGLSL.Technique.Pass.BlendColor createProfileGLSLTechniquePassBlendColor()
  {
    return new ProfileGLSL.Technique.Pass.BlendColor();
  }
  public ProfileGLSL.Technique.Pass.FogEnable createProfileGLSLTechniquePassFogEnable()
  {
    return new ProfileGLSL.Technique.Pass.FogEnable();
  }
  public GlesTexenvCommandType createGlesTexenvCommandType()
  {
    return new GlesTexenvCommandType();
  }
  public ProfileGLSL.Technique.Pass.TextureCUBE createProfileGLSLTechniquePassTextureCUBE()
  {
    return new ProfileGLSL.Technique.Pass.TextureCUBE();
  }
  public ProfileGLES.Technique.Pass.TexturePipelineEnable createProfileGLESTechniquePassTexturePipelineEnable()
  {
    return new ProfileGLES.Technique.Pass.TexturePipelineEnable();
  }
  public CgSetuserType createCgSetuserType()
  {
    return new CgSetuserType();
  }
  public ProfileGLES.Technique.Pass.PointFadeThresholdSize createProfileGLESTechniquePassPointFadeThresholdSize()
  {
    return new ProfileGLES.Technique.Pass.PointFadeThresholdSize();
  }
  public Rotate createRotate()
  {
    return new Rotate();
  }
  public Param createParam()
  {
    return new Param();
  }
  public ProfileGLSL.Technique.Pass.BlendFuncSeparate.DestRgb createProfileGLSLTechniquePassBlendFuncSeparateDestRgb()
  {
    return new ProfileGLSL.Technique.Pass.BlendFuncSeparate.DestRgb();
  }
  public RigidBody.TechniqueCommon.Dynamic createRigidBodyTechniqueCommonDynamic()
  {
    return new RigidBody.TechniqueCommon.Dynamic();
  }
  public ProfileGLSL.Technique.Pass.Shader createProfileGLSLTechniquePassShader()
  {
    return new ProfileGLSL.Technique.Pass.Shader();
  }
  public ProfileGLSL.Technique.Pass.TextureDEPTHEnable createProfileGLSLTechniquePassTextureDEPTHEnable()
  {
    return new ProfileGLSL.Technique.Pass.TextureDEPTHEnable();
  }
  public ProfileGLES.Technique.Pass.FogDensity createProfileGLESTechniquePassFogDensity()
  {
    return new ProfileGLES.Technique.Pass.FogDensity();
  }
  public ProfileGLES.Technique.Pass.RescaleNormalEnable createProfileGLESTechniquePassRescaleNormalEnable()
  {
    return new ProfileGLES.Technique.Pass.RescaleNormalEnable();
  }
  public ProfileGLSL.Technique.Pass.StencilOpSeparate createProfileGLSLTechniquePassStencilOpSeparate()
  {
    return new ProfileGLSL.Technique.Pass.StencilOpSeparate();
  }
  public ProfileGLSL.Technique.Pass.StencilFunc.Mask createProfileGLSLTechniquePassStencilFuncMask()
  {
    return new ProfileGLSL.Technique.Pass.StencilFunc.Mask();
  }
  public ProfileGLES.Technique.Pass.DepthMask createProfileGLESTechniquePassDepthMask()
  {
    return new ProfileGLES.Technique.Pass.DepthMask();
  }
  public ProfileGLES.Technique.Pass.ColorMask createProfileGLESTechniquePassColorMask()
  {
    return new ProfileGLES.Technique.Pass.ColorMask();
  }
  public FxSurfaceInitVolumeCommon createFxSurfaceInitVolumeCommon()
  {
    return new FxSurfaceInitVolumeCommon();
  }
  public TaperedCapsule createTaperedCapsule()
  {
    return new TaperedCapsule();
  }
  public ProfileGLES.Technique.Pass.FogMode createProfileGLESTechniquePassFogMode()
  {
    return new ProfileGLES.Technique.Pass.FogMode();
  }
  public ProfileGLES.Technique.Pass.DepthFunc createProfileGLESTechniquePassDepthFunc()
  {
    return new ProfileGLES.Technique.Pass.DepthFunc();
  }
  public Plane createPlane()
  {
    return new Plane();
  }
  public LibraryGeometries createLibraryGeometries()
  {
    return new LibraryGeometries();
  }
  public LibraryControllers createLibraryControllers()
  {
    return new LibraryControllers();
  }
  public ProfileGLES.Technique createProfileGLESTechnique()
  {
    return new ProfileGLES.Technique();
  }
  public ProfileGLES.Technique.Pass.AlphaTestEnable createProfileGLESTechniquePassAlphaTestEnable()
  {
    return new ProfileGLES.Technique.Pass.AlphaTestEnable();
  }
  public VisualScene createVisualScene()
  {
    return new VisualScene();
  }
  public ProfileGLES.Technique.Pass.MaterialSpecular createProfileGLESTechniquePassMaterialSpecular()
  {
    return new ProfileGLES.Technique.Pass.MaterialSpecular();
  }
  public Box createBox()
  {
    return new Box();
  }
  public RigidConstraint.RefAttachment createRigidConstraintRefAttachment()
  {
    return new RigidConstraint.RefAttachment();
  }
  public GlesTexcombinerArgumentAlphaType createGlesTexcombinerArgumentAlphaType()
  {
    return new GlesTexcombinerArgumentAlphaType();
  }
  public Light.TechniqueCommon.Point createLightTechniqueCommonPoint()
  {
    return new Light.TechniqueCommon.Point();
  }
  public Tristrips createTristrips()
  {
    return new Tristrips();
  }
  public ProfileGLSL.Technique.Pass.LightSpotExponent createProfileGLSLTechniquePassLightSpotExponent()
  {
    return new ProfileGLSL.Technique.Pass.LightSpotExponent();
  }
  public ProfileGLES.Technique.Pass.MaterialDiffuse createProfileGLESTechniquePassMaterialDiffuse()
  {
    return new ProfileGLES.Technique.Pass.MaterialDiffuse();
  }
  public ProfileGLSL createProfileGLSL()
  {
    return new ProfileGLSL();
  }
  public InstanceRigidBody.TechniqueCommon.Shape createInstanceRigidBodyTechniqueCommonShape()
  {
    return new InstanceRigidBody.TechniqueCommon.Shape();
  }
  public InstanceController createInstanceController()
  {
    return new InstanceController();
  }
  public ProfileGLSL.Technique.Pass.BlendEquationSeparate.Alpha createProfileGLSLTechniquePassBlendEquationSeparateAlpha()
  {
    return new ProfileGLSL.Technique.Pass.BlendEquationSeparate.Alpha();
  }
  public FxSurfaceInitVolumeCommon.All createFxSurfaceInitVolumeCommonAll()
  {
    return new FxSurfaceInitVolumeCommon.All();
  }
  public ProfileGLES.Technique.Pass.ModelViewMatrix createProfileGLESTechniquePassModelViewMatrix()
  {
    return new ProfileGLES.Technique.Pass.ModelViewMatrix();
  }
  public ProfileGLES.Technique.Pass.BlendFunc.Dest createProfileGLESTechniquePassBlendFuncDest()
  {
    return new ProfileGLES.Technique.Pass.BlendFunc.Dest();
  }
  public ProfileGLES.Technique.Pass.ClearStencil createProfileGLESTechniquePassClearStencil()
  {
    return new ProfileGLES.Technique.Pass.ClearStencil();
  }
  public CgSurfaceType.Generator.Name createCgSurfaceTypeGeneratorName()
  {
    return new CgSurfaceType.Generator.Name();
  }
  public ProfileGLSL.Technique.Pass.SampleAlphaToOneEnable createProfileGLSLTechniquePassSampleAlphaToOneEnable()
  {
    return new ProfileGLSL.Technique.Pass.SampleAlphaToOneEnable();
  }
  public ProfileGLSL.Technique.Pass.Texture1DEnable createProfileGLSLTechniquePassTexture1DEnable()
  {
    return new ProfileGLSL.Technique.Pass.Texture1DEnable();
  }
  public FxNewparamCommon createFxNewparamCommon()
  {
    return new FxNewparamCommon();
  }
  public CommonColorOrTextureType.Texture createCommonColorOrTextureTypeTexture()
  {
    return new CommonColorOrTextureType.Texture();
  }
  public ProfileGLSL.Technique.Pass.AlphaFunc.Value createProfileGLSLTechniquePassAlphaFuncValue()
  {
    return new ProfileGLSL.Technique.Pass.AlphaFunc.Value();
  }
  public Source createSource()
  {
    return new Source();
  }
  public ProfileGLSL.Technique.Pass.LightPosition createProfileGLSLTechniquePassLightPosition()
  {
    return new ProfileGLSL.Technique.Pass.LightPosition();
  }
  public Skew createSkew()
  {
    return new Skew();
  }
  public FxStenciltargetCommon createFxStenciltargetCommon()
  {
    return new FxStenciltargetCommon();
  }
  public LibraryLights createLibraryLights()
  {
    return new LibraryLights();
  }
  public ProfileGLSL.Technique.Pass.FogStart createProfileGLSLTechniquePassFogStart()
  {
    return new ProfileGLSL.Technique.Pass.FogStart();
  }
  public CgSamplerRECT createCgSamplerRECT()
  {
    return new CgSamplerRECT();
  }
  public ProfileGLES.Technique.Pass.ClearColor createProfileGLESTechniquePassClearColor()
  {
    return new ProfileGLES.Technique.Pass.ClearColor();
  }
  public Sphere createSphere()
  {
    return new Sphere();
  }
  public InstanceRigidBody createInstanceRigidBody()
  {
    return new InstanceRigidBody();
  }
  public ProfileGLES.Technique.Pass.NormalizeEnable createProfileGLESTechniquePassNormalizeEnable()
  {
    return new ProfileGLES.Technique.Pass.NormalizeEnable();
  }
  public PhysicsScene.TechniqueCommon createPhysicsSceneTechniqueCommon()
  {
    return new PhysicsScene.TechniqueCommon();
  }
  public Polylist createPolylist()
  {
    return new Polylist();
  }
  public CgSetparam createCgSetparam()
  {
    return new CgSetparam();
  }
  public Light.TechniqueCommon.Spot createLightTechniqueCommonSpot()
  {
    return new Light.TechniqueCommon.Spot();
  }
  public ProfileGLES.Technique.Pass.LightLinearAttenutation createProfileGLESTechniquePassLightLinearAttenutation()
  {
    return new ProfileGLES.Technique.Pass.LightLinearAttenutation();
  }
  public ProfileGLSL.Technique.Pass.DepthTestEnable createProfileGLSLTechniquePassDepthTestEnable()
  {
    return new ProfileGLSL.Technique.Pass.DepthTestEnable();
  }
  public Source.TechniqueCommon createSourceTechniqueCommon()
  {
    return new Source.TechniqueCommon();
  }
  public FxColortargetCommon createFxColortargetCommon()
  {
    return new FxColortargetCommon();
  }
  public ProfileGLSL.Technique.Pass.FogCoordSrc createProfileGLSLTechniquePassFogCoordSrc()
  {
    return new ProfileGLSL.Technique.Pass.FogCoordSrc();
  }
  public Animation createAnimation()
  {
    return new Animation();
  }
  public GlSampler2D createGlSampler2D()
  {
    return new GlSampler2D();
  }
  public Sampler createSampler()
  {
    return new Sampler();
  }
  public ProfileGLSL.Technique.Pass.TextureRECT createProfileGLSLTechniquePassTextureRECT()
  {
    return new ProfileGLSL.Technique.Pass.TextureRECT();
  }
  public Camera.Optics createCameraOptics()
  {
    return new Camera.Optics();
  }
  public GlesTexcombinerCommandType createGlesTexcombinerCommandType()
  {
    return new GlesTexcombinerCommandType();
  }
  public ProfileGLES.Technique.Pass.LineWidth createProfileGLESTechniquePassLineWidth()
  {
    return new ProfileGLES.Technique.Pass.LineWidth();
  }
  public ProfileGLSL.Technique.Pass.PointSize createProfileGLSLTechniquePassPointSize()
  {
    return new ProfileGLSL.Technique.Pass.PointSize();
  }
  public ProfileGLES.Technique.Pass.CullFaceEnable createProfileGLESTechniquePassCullFaceEnable()
  {
    return new ProfileGLES.Technique.Pass.CullFaceEnable();
  }
  public ProfileGLSL.Technique.Pass.LightSpecular createProfileGLSLTechniquePassLightSpecular()
  {
    return new ProfileGLSL.Technique.Pass.LightSpecular();
  }
  public ProfileGLES.Technique.Setparam createProfileGLESTechniqueSetparam()
  {
    return new ProfileGLES.Technique.Setparam();
  }
  public ProfileGLES.Technique.Pass.SampleCoverageEnable createProfileGLESTechniquePassSampleCoverageEnable()
  {
    return new ProfileGLES.Technique.Pass.SampleCoverageEnable();
  }
  public ProfileGLSL.Technique.Pass.StencilFuncSeparate createProfileGLSLTechniquePassStencilFuncSeparate()
  {
    return new ProfileGLSL.Technique.Pass.StencilFuncSeparate();
  }
  public ProfileGLSL.Technique.Pass.FogColor createProfileGLSLTechniquePassFogColor()
  {
    return new ProfileGLSL.Technique.Pass.FogColor();
  }
  public ProfileGLES.Technique.Pass.StencilTestEnable createProfileGLESTechniquePassStencilTestEnable()
  {
    return new ProfileGLES.Technique.Pass.StencilTestEnable();
  }
  public ProfileGLSL.Technique createProfileGLSLTechnique()
  {
    return new ProfileGLSL.Technique();
  }
  public GlslSetarrayType createGlslSetarrayType()
  {
    return new GlslSetarrayType();
  }
  public ProfileGLSL.Technique.Pass.ScissorTestEnable createProfileGLSLTechniquePassScissorTestEnable()
  {
    return new ProfileGLSL.Technique.Pass.ScissorTestEnable();
  }
  public FxSamplerRECTCommon createFxSamplerRECTCommon()
  {
    return new FxSamplerRECTCommon();
  }
  public ProfileGLES.Technique.Pass.ClipPlaneEnable createProfileGLESTechniquePassClipPlaneEnable()
  {
    return new ProfileGLES.Technique.Pass.ClipPlaneEnable();
  }
  public ProfileGLSL.Technique.Pass.BlendFuncSeparate.SrcRgb createProfileGLSLTechniquePassBlendFuncSeparateSrcRgb()
  {
    return new ProfileGLSL.Technique.Pass.BlendFuncSeparate.SrcRgb();
  }
  public ProfileGLSL.Technique.Pass.FogEnd createProfileGLSLTechniquePassFogEnd()
  {
    return new ProfileGLSL.Technique.Pass.FogEnd();
  }
  public GlslNewarrayType createGlslNewarrayType()
  {
    return new GlslNewarrayType();
  }
  public FxSurfaceInitCubeCommon createFxSurfaceInitCubeCommon()
  {
    return new FxSurfaceInitCubeCommon();
  }
  public Matrix createMatrix()
  {
    return new Matrix();
  }
  public ProfileGLES.Technique.Pass.ScissorTestEnable createProfileGLESTechniquePassScissorTestEnable()
  {
    return new ProfileGLES.Technique.Pass.ScissorTestEnable();
  }
  public FxSamplerCUBECommon createFxSamplerCUBECommon()
  {
    return new FxSamplerCUBECommon();
  }
  public ProfileCG createProfileCG()
  {
    return new ProfileCG();
  }
  public FxAnnotateCommon createFxAnnotateCommon()
  {
    return new FxAnnotateCommon();
  }
  public InputLocal createInputLocal()
  {
    return new InputLocal();
  }
  public LibraryCameras createLibraryCameras()
  {
    return new LibraryCameras();
  }
  public InstanceWithExtra createInstanceWithExtra()
  {
    return new InstanceWithExtra();
  }
  public ProfileCG.Technique.Pass.Shader.Bind.Param createProfileCGTechniquePassShaderBindParam()
  {
    return new ProfileCG.Technique.Pass.Shader.Bind.Param();
  }
  public RigidConstraint createRigidConstraint()
  {
    return new RigidConstraint();
  }
  public ProfileGLSL.Technique.Pass.StencilFunc.Func createProfileGLSLTechniquePassStencilFuncFunc()
  {
    return new ProfileGLSL.Technique.Pass.StencilFunc.Func();
  }
  public ProfileGLSL.Technique.Pass.Texture3DEnable createProfileGLSLTechniquePassTexture3DEnable()
  {
    return new ProfileGLSL.Technique.Pass.Texture3DEnable();
  }
  public ProfileGLSL.Technique.Pass.MultisampleEnable createProfileGLSLTechniquePassMultisampleEnable()
  {
    return new ProfileGLSL.Technique.Pass.MultisampleEnable();
  }
  public Triangles createTriangles()
  {
    return new Triangles();
  }
  public ProfileGLSL.Technique.Pass.DepthBounds createProfileGLSLTechniquePassDepthBounds()
  {
    return new ProfileGLSL.Technique.Pass.DepthBounds();
  }
  public ProfileGLSL.Technique.Pass.MaterialShininess createProfileGLSLTechniquePassMaterialShininess()
  {
    return new ProfileGLSL.Technique.Pass.MaterialShininess();
  }
  public ProfileGLSL.Technique.Pass.TextureRECTEnable createProfileGLSLTechniquePassTextureRECTEnable()
  {
    return new ProfileGLSL.Technique.Pass.TextureRECTEnable();
  }
  public ProfileGLSL.Technique.Pass.PolygonMode createProfileGLSLTechniquePassPolygonMode()
  {
    return new ProfileGLSL.Technique.Pass.PolygonMode();
  }
  public ProfileGLSL.Technique.Pass.DitherEnable createProfileGLSLTechniquePassDitherEnable()
  {
    return new ProfileGLSL.Technique.Pass.DitherEnable();
  }
  public Cylinder createCylinder()
  {
    return new Cylinder();
  }
  public ProfileGLES.Technique.Pass.MaterialAmbient createProfileGLESTechniquePassMaterialAmbient()
  {
    return new ProfileGLES.Technique.Pass.MaterialAmbient();
  }
  public ProfileGLSL.Technique.Pass.StencilTestEnable createProfileGLSLTechniquePassStencilTestEnable()
  {
    return new ProfileGLSL.Technique.Pass.StencilTestEnable();
  }
  public InstanceMaterial createInstanceMaterial()
  {
    return new InstanceMaterial();
  }
  public IntArray createIntArray()
  {
    return new IntArray();
  }
  public GlesTexcombinerArgumentRGBType createGlesTexcombinerArgumentRGBType()
  {
    return new GlesTexcombinerArgumentRGBType();
  }
  public ProfileGLSL.Technique.Pass.StencilOpSeparate.Zpass createProfileGLSLTechniquePassStencilOpSeparateZpass()
  {
    return new ProfileGLSL.Technique.Pass.StencilOpSeparate.Zpass();
  }
  public ProfileGLES.Technique.Pass.StencilOp.Fail createProfileGLESTechniquePassStencilOpFail()
  {
    return new ProfileGLES.Technique.Pass.StencilOp.Fail();
  }
  public RigidBody.TechniqueCommon.MassFrame createRigidBodyTechniqueCommonMassFrame()
  {
    return new RigidBody.TechniqueCommon.MassFrame();
  }
  public ProfileGLSL.Technique.Pass.Scissor createProfileGLSLTechniquePassScissor()
  {
    return new ProfileGLSL.Technique.Pass.Scissor();
  }
  public CgSurfaceType.Generator createCgSurfaceTypeGenerator()
  {
    return new CgSurfaceType.Generator();
  }
  public InstanceRigidBody.TechniqueCommon createInstanceRigidBodyTechniqueCommon()
  {
    return new InstanceRigidBody.TechniqueCommon();
  }
  public ProfileGLSL.Technique.Pass.LineWidth createProfileGLSLTechniquePassLineWidth()
  {
    return new ProfileGLSL.Technique.Pass.LineWidth();
  }
  public ProfileGLSL.Technique.Pass.PointSmoothEnable createProfileGLSLTechniquePassPointSmoothEnable()
  {
    return new ProfileGLSL.Technique.Pass.PointSmoothEnable();
  }
  public Light.TechniqueCommon createLightTechniqueCommon()
  {
    return new Light.TechniqueCommon();
  }
  public Linestrips createLinestrips()
  {
    return new Linestrips();
  }
  public Node createNode()
  {
    return new Node();
  }
  public ProfileGLSL.Technique.Pass.LightLinearAttenuation createProfileGLSLTechniquePassLightLinearAttenuation()
  {
    return new ProfileGLSL.Technique.Pass.LightLinearAttenuation();
  }
  public RigidConstraint.TechniqueCommon.Spring.Linear createRigidConstraintTechniqueCommonSpringLinear()
  {
    return new RigidConstraint.TechniqueCommon.Spring.Linear();
  }
  public ProfileGLES.Technique.Pass.StencilFunc.Mask createProfileGLESTechniquePassStencilFuncMask()
  {
    return new ProfileGLES.Technique.Pass.StencilFunc.Mask();
  }
  public ProfileGLSL.Technique.Pass.ColorMaterial createProfileGLSLTechniquePassColorMaterial()
  {
    return new ProfileGLSL.Technique.Pass.ColorMaterial();
  }
  public GlslSetparam createGlslSetparam()
  {
    return new GlslSetparam();
  }
  public Image createImage()
  {
    return new Image();
  }
  public GlslSurfaceType.Generator createGlslSurfaceTypeGenerator()
  {
    return new GlslSurfaceType.Generator();
  }
  public InstanceRigidBody.TechniqueCommon.Dynamic createInstanceRigidBodyTechniqueCommonDynamic()
  {
    return new InstanceRigidBody.TechniqueCommon.Dynamic();
  }
  public ProfileGLSL.Technique.Pass.ClearDepth createProfileGLSLTechniquePassClearDepth()
  {
    return new ProfileGLSL.Technique.Pass.ClearDepth();
  }
  public GlSamplerDEPTH createGlSamplerDEPTH()
  {
    return new GlSamplerDEPTH();
  }
  public Skin.Joints createSkinJoints()
  {
    return new Skin.Joints();
  }
  public RigidConstraint.TechniqueCommon.Spring.Angular createRigidConstraintTechniqueCommonSpringAngular()
  {
    return new RigidConstraint.TechniqueCommon.Spring.Angular();
  }
  public Channel createChannel()
  {
    return new Channel();
  }
  public ProfileGLSL.Technique.Pass.PolygonOffsetLineEnable createProfileGLSLTechniquePassPolygonOffsetLineEnable()
  {
    return new ProfileGLSL.Technique.Pass.PolygonOffsetLineEnable();
  }
  public FxCodeProfile createFxCodeProfile()
  {
    return new FxCodeProfile();
  }
  public ProfileGLSL.Technique.Pass.BlendFunc createProfileGLSLTechniquePassBlendFunc()
  {
    return new ProfileGLSL.Technique.Pass.BlendFunc();
  }
  public CommonColorOrTextureType createCommonColorOrTextureType()
  {
    return new CommonColorOrTextureType();
  }
  public ProfileGLSL.Technique.Pass.ColorLogicOpEnable createProfileGLSLTechniquePassColorLogicOpEnable()
  {
    return new ProfileGLSL.Technique.Pass.ColorLogicOpEnable();
  }
  public ProfileGLSL.Technique.Pass.DepthBoundsEnable createProfileGLSLTechniquePassDepthBoundsEnable()
  {
    return new ProfileGLSL.Technique.Pass.DepthBoundsEnable();
  }
  public FxSurfaceInitVolumeCommon.Primary createFxSurfaceInitVolumeCommonPrimary()
  {
    return new FxSurfaceInitVolumeCommon.Primary();
  }
  public ProfileGLES.Technique.Pass.StencilFunc createProfileGLESTechniquePassStencilFunc()
  {
    return new ProfileGLES.Technique.Pass.StencilFunc();
  }
  public ProfileGLSL.Technique.Pass.BlendEquation createProfileGLSLTechniquePassBlendEquation()
  {
    return new ProfileGLSL.Technique.Pass.BlendEquation();
  }
  public ProfileGLSL.Technique.Pass.LightConstantAttenuation createProfileGLSLTechniquePassLightConstantAttenuation()
  {
    return new ProfileGLSL.Technique.Pass.LightConstantAttenuation();
  }
  public Camera.Optics.TechniqueCommon createCameraOpticsTechniqueCommon()
  {
    return new Camera.Optics.TechniqueCommon();
  }
  public RigidConstraint.TechniqueCommon.Limits createRigidConstraintTechniqueCommonLimits()
  {
    return new RigidConstraint.TechniqueCommon.Limits();
  }
  public ProfileGLSL.Technique.Pass.PolygonSmoothEnable createProfileGLSLTechniquePassPolygonSmoothEnable()
  {
    return new ProfileGLSL.Technique.Pass.PolygonSmoothEnable();
  }
  public ProfileGLES.Technique.Pass.StencilOp.Zfail createProfileGLESTechniquePassStencilOpZfail()
  {
    return new ProfileGLES.Technique.Pass.StencilOp.Zfail();
  }
  public ProfileGLSL.Technique.Pass.Shader.Bind.Param createProfileGLSLTechniquePassShaderBindParam()
  {
    return new ProfileGLSL.Technique.Pass.Shader.Bind.Param();
  }
  public Accessor createAccessor()
  {
    return new Accessor();
  }
  public ProfileGLSL.Technique.Pass.ColorMask createProfileGLSLTechniquePassColorMask()
  {
    return new ProfileGLSL.Technique.Pass.ColorMask();
  }
  public ProfileGLSL.Technique.Pass.ClipPlaneEnable createProfileGLSLTechniquePassClipPlaneEnable()
  {
    return new ProfileGLSL.Technique.Pass.ClipPlaneEnable();
  }
  public Controller createController()
  {
    return new Controller();
  }
  public ProfileGLSL.Technique.Pass.ColorMaterial.Face createProfileGLSLTechniquePassColorMaterialFace()
  {
    return new ProfileGLSL.Technique.Pass.ColorMaterial.Face();
  }
  public ProfileGLSL.Technique.Pass.BlendFunc.Src createProfileGLSLTechniquePassBlendFuncSrc()
  {
    return new ProfileGLSL.Technique.Pass.BlendFunc.Src();
  }
  public GlslSurfaceType createGlslSurfaceType()
  {
    return new GlslSurfaceType();
  }
  public ProfileGLSL.Technique.Pass.SampleAlphaToCoverageEnable createProfileGLSLTechniquePassSampleAlphaToCoverageEnable()
  {
    return new ProfileGLSL.Technique.Pass.SampleAlphaToCoverageEnable();
  }
  public IDREFArray createIDREFArray()
  {
    return new IDREFArray();
  }
  public ProfileGLSL.Technique.Pass.PointSizeMin createProfileGLSLTechniquePassPointSizeMin()
  {
    return new ProfileGLSL.Technique.Pass.PointSizeMin();
  }
  public ProfileGLSL.Technique.Pass.StencilOp.Fail createProfileGLSLTechniquePassStencilOpFail()
  {
    return new ProfileGLSL.Technique.Pass.StencilOp.Fail();
  }
  public ForceField createForceField()
  {
    return new ForceField();
  }
  public ProfileGLSL.Technique.Pass.ModelViewMatrix createProfileGLSLTechniquePassModelViewMatrix()
  {
    return new ProfileGLSL.Technique.Pass.ModelViewMatrix();
  }
  public Morph.Targets createMorphTargets()
  {
    return new Morph.Targets();
  }
  public ProfileGLES.Technique.Pass.StencilOp.Zpass createProfileGLESTechniquePassStencilOpZpass()
  {
    return new ProfileGLES.Technique.Pass.StencilOp.Zpass();
  }
  public ProfileGLSL.Technique.Pass.StencilMask createProfileGLSLTechniquePassStencilMask()
  {
    return new ProfileGLSL.Technique.Pass.StencilMask();
  }
  public CgConnectParam createCgConnectParam()
  {
    return new CgConnectParam();
  }
  public ProfileGLSL.Technique.Pass.Texture2D createProfileGLSLTechniquePassTexture2D()
  {
    return new ProfileGLSL.Technique.Pass.Texture2D();
  }
  public ProfileGLES.Technique.Pass.LightAmbient createProfileGLESTechniquePassLightAmbient()
  {
    return new ProfileGLES.Technique.Pass.LightAmbient();
  }
  public RigidConstraint.TechniqueCommon createRigidConstraintTechniqueCommon()
  {
    return new RigidConstraint.TechniqueCommon();
  }
  public ProfileGLSL.Technique.Pass.LightModelColorControl createProfileGLSLTechniquePassLightModelColorControl()
  {
    return new ProfileGLSL.Technique.Pass.LightModelColorControl();
  }
  public ProfileGLSL.Technique.Pass.MaterialSpecular createProfileGLSLTechniquePassMaterialSpecular()
  {
    return new ProfileGLSL.Technique.Pass.MaterialSpecular();
  }
  public ProfileGLSL.Technique.Pass.StencilOpSeparate.Zfail createProfileGLSLTechniquePassStencilOpSeparateZfail()
  {
    return new ProfileGLSL.Technique.Pass.StencilOpSeparate.Zfail();
  }
  public Lines createLines()
  {
    return new Lines();
  }
  public ProfileGLSL.Technique.Pass.LineSmoothEnable createProfileGLSLTechniquePassLineSmoothEnable()
  {
    return new ProfileGLSL.Technique.Pass.LineSmoothEnable();
  }
  public GlesTexturePipeline createGlesTexturePipeline()
  {
    return new GlesTexturePipeline();
  }
  public ProfileGLSL.Technique.Pass.StencilOp createProfileGLSLTechniquePassStencilOp()
  {
    return new ProfileGLSL.Technique.Pass.StencilOp();
  }
  public ProfileGLSL.Technique.Pass.ClearStencil createProfileGLSLTechniquePassClearStencil()
  {
    return new ProfileGLSL.Technique.Pass.ClearStencil();
  }
  public FxSurfaceCommon createFxSurfaceCommon()
  {
    return new FxSurfaceCommon();
  }
  public CgSamplerCUBE createCgSamplerCUBE()
  {
    return new CgSamplerCUBE();
  }
  public ProfileGLES.Technique.Pass.LightSpotDirection createProfileGLESTechniquePassLightSpotDirection()
  {
    return new ProfileGLES.Technique.Pass.LightSpotDirection();
  }
  public ProfileGLSL.Technique.Pass.PointDistanceAttenuation createProfileGLSLTechniquePassPointDistanceAttenuation()
  {
    return new ProfileGLSL.Technique.Pass.PointDistanceAttenuation();
  }
  public LibraryPhysicsModels createLibraryPhysicsModels()
  {
    return new LibraryPhysicsModels();
  }
  public ProfileGLES.Technique.Pass.SampleAlphaToOneEnable createProfileGLESTechniquePassSampleAlphaToOneEnable()
  {
    return new ProfileGLES.Technique.Pass.SampleAlphaToOneEnable();
  }
  public ProfileGLES.Technique.Pass.PointDistanceAttenuation createProfileGLESTechniquePassPointDistanceAttenuation()
  {
    return new ProfileGLES.Technique.Pass.PointDistanceAttenuation();
  }
  public ProfileGLSL.Technique.Pass.StencilOpSeparate.Face createProfileGLSLTechniquePassStencilOpSeparateFace()
  {
    return new ProfileGLSL.Technique.Pass.StencilOpSeparate.Face();
  }
  public ProfileGLES.Technique.Pass.FogEnable createProfileGLESTechniquePassFogEnable()
  {
    return new ProfileGLES.Technique.Pass.FogEnable();
  }
  public ProfileGLSL.Technique.Pass.PolygonOffsetPointEnable createProfileGLSLTechniquePassPolygonOffsetPointEnable()
  {
    return new ProfileGLSL.Technique.Pass.PolygonOffsetPointEnable();
  }
  public RigidConstraint.Attachment createRigidConstraintAttachment()
  {
    return new RigidConstraint.Attachment();
  }
  public ProfileGLES.Technique.Pass.LightingEnable createProfileGLESTechniquePassLightingEnable()
  {
    return new ProfileGLES.Technique.Pass.LightingEnable();
  }
  public ProfileGLES.Technique.Pass.Scissor createProfileGLESTechniquePassScissor()
  {
    return new ProfileGLES.Technique.Pass.Scissor();
  }
  public InstanceEffect.Setparam createInstanceEffectSetparam()
  {
    return new InstanceEffect.Setparam();
  }
  public CommonColorOrTextureType.Param createCommonColorOrTextureTypeParam()
  {
    return new CommonColorOrTextureType.Param();
  }
  public ProfileGLES.Technique.Pass.BlendFunc createProfileGLESTechniquePassBlendFunc()
  {
    return new ProfileGLES.Technique.Pass.BlendFunc();
  }
  public ProfileGLES.Technique.Pass.PolygonOffset createProfileGLESTechniquePassPolygonOffset()
  {
    return new ProfileGLES.Technique.Pass.PolygonOffset();
  }
  public FxSampler3DCommon createFxSampler3DCommon()
  {
    return new FxSampler3DCommon();
  }
  public ProfileGLES.Technique.Pass.PointSizeMax createProfileGLESTechniquePassPointSizeMax()
  {
    return new ProfileGLES.Technique.Pass.PointSizeMax();
  }
  public RigidConstraint.TechniqueCommon.Enabled createRigidConstraintTechniqueCommonEnabled()
  {
    return new RigidConstraint.TechniqueCommon.Enabled();
  }
  public GlesTexcombinerCommandRGBType createGlesTexcombinerCommandRGBType()
  {
    return new GlesTexcombinerCommandRGBType();
  }
  public TaperedCylinder createTaperedCylinder()
  {
    return new TaperedCylinder();
  }
  public ProfileGLES.Technique.Pass.SampleAlphaToCoverageEnable createProfileGLESTechniquePassSampleAlphaToCoverageEnable()
  {
    return new ProfileGLES.Technique.Pass.SampleAlphaToCoverageEnable();
  }
  public ProfileCG.Technique.Pass.Shader.CompilerTarget createProfileCGTechniquePassShaderCompilerTarget()
  {
    return new ProfileCG.Technique.Pass.Shader.CompilerTarget();
  }
  public ProfileCOMMON.Technique createProfileCOMMONTechnique()
  {
    return new ProfileCOMMON.Technique();
  }
  public ProfileGLSL.Technique.Pass.BlendEquationSeparate createProfileGLSLTechniquePassBlendEquationSeparate()
  {
    return new ProfileGLSL.Technique.Pass.BlendEquationSeparate();
  }
  public ProfileGLSL.Technique.Pass createProfileGLSLTechniquePass()
  {
    return new ProfileGLSL.Technique.Pass();
  }
  public ProfileGLSL.Technique.Pass.AlphaFunc createProfileGLSLTechniquePassAlphaFunc()
  {
    return new ProfileGLSL.Technique.Pass.AlphaFunc();
  }
  public CgNewparam createCgNewparam()
  {
    return new CgNewparam();
  }
  public ProfileGLES.Technique.Pass.ClearDepth createProfileGLESTechniquePassClearDepth()
  {
    return new ProfileGLES.Technique.Pass.ClearDepth();
  }
  public ProfileGLES.Technique.Pass.LightSpecular createProfileGLESTechniquePassLightSpecular()
  {
    return new ProfileGLES.Technique.Pass.LightSpecular();
  }
  public ProfileGLSL.Technique.Pass.StencilOp.Zfail createProfileGLSLTechniquePassStencilOpZfail()
  {
    return new ProfileGLSL.Technique.Pass.StencilOp.Zfail();
  }
  public Polygons createPolygons()
  {
    return new Polygons();
  }
  public ProfileGLSL.Technique.Pass.LightModelAmbient createProfileGLSLTechniquePassLightModelAmbient()
  {
    return new ProfileGLSL.Technique.Pass.LightModelAmbient();
  }
  public RigidConstraint.TechniqueCommon.Limits.SwingConeAndTwist createRigidConstraintTechniqueCommonLimitsSwingConeAndTwist()
  {
    return new RigidConstraint.TechniqueCommon.Limits.SwingConeAndTwist();
  }
  public CommonFloatOrParamType.Float createCommonFloatOrParamTypeFloat()
  {
    return new CommonFloatOrParamType.Float();
  }
  public ProfileGLES.Technique.Pass.LightConstantAttenuation createProfileGLESTechniquePassLightConstantAttenuation()
  {
    return new ProfileGLES.Technique.Pass.LightConstantAttenuation();
  }
  public InstancePhysicsModel createInstancePhysicsModel()
  {
    return new InstancePhysicsModel();
  }
  public ProfileGLSL.Technique.Pass.AlphaFunc.Func createProfileGLSLTechniquePassAlphaFuncFunc()
  {
    return new ProfileGLSL.Technique.Pass.AlphaFunc.Func();
  }
  public ProfileGLSL.Technique.Pass.LightSpotCutoff createProfileGLSLTechniquePassLightSpotCutoff()
  {
    return new ProfileGLSL.Technique.Pass.LightSpotCutoff();
  }
  public InputLocalOffset createInputLocalOffset()
  {
    return new InputLocalOffset();
  }
  public LibraryPhysicsMaterials createLibraryPhysicsMaterials()
  {
    return new LibraryPhysicsMaterials();
  }
  public Effect createEffect()
  {
    return new Effect();
  }
  public ProfileGLSL.Technique.Pass.LogicOp createProfileGLSLTechniquePassLogicOp()
  {
    return new ProfileGLSL.Technique.Pass.LogicOp();
  }
  public ProfileCOMMON.Technique.Lambert createProfileCOMMONTechniqueLambert()
  {
    return new ProfileCOMMON.Technique.Lambert();
  }
  public ProfileGLSL.Technique.Pass.StencilOpSeparate.Fail createProfileGLSLTechniquePassStencilOpSeparateFail()
  {
    return new ProfileGLSL.Technique.Pass.StencilOpSeparate.Fail();
  }
  public ProfileGLSL.Technique.Pass.ColorMaterial.Mode createProfileGLSLTechniquePassColorMaterialMode()
  {
    return new ProfileGLSL.Technique.Pass.ColorMaterial.Mode();
  }
  public ProfileGLSL.Technique.Pass.ColorMaterialEnable createProfileGLSLTechniquePassColorMaterialEnable()
  {
    return new ProfileGLSL.Technique.Pass.ColorMaterialEnable();
  }
  public ProfileGLSL.Technique.Pass.StencilMaskSeparate.Face createProfileGLSLTechniquePassStencilMaskSeparateFace()
  {
    return new ProfileGLSL.Technique.Pass.StencilMaskSeparate.Face();
  }
  public BindMaterial.TechniqueCommon createBindMaterialTechniqueCommon()
  {
    return new BindMaterial.TechniqueCommon();
  }
  public ProfileGLES.Technique.Pass.DepthRange createProfileGLESTechniquePassDepthRange()
  {
    return new ProfileGLES.Technique.Pass.DepthRange();
  }
  public FxCleardepthCommon createFxCleardepthCommon()
  {
    return new FxCleardepthCommon();
  }
  public RigidBody.TechniqueCommon.Shape.Hollow createRigidBodyTechniqueCommonShapeHollow()
  {
    return new RigidBody.TechniqueCommon.Shape.Hollow();
  }
  public CgSamplerDEPTH createCgSamplerDEPTH()
  {
    return new CgSamplerDEPTH();
  }
  public ProfileGLES.Technique.Pass.AlphaFunc createProfileGLESTechniquePassAlphaFunc()
  {
    return new ProfileGLES.Technique.Pass.AlphaFunc();
  }
  public GlSampler1D createGlSampler1D()
  {
    return new GlSampler1D();
  }
  public Light createLight()
  {
    return new Light();
  }
  public ProfileGLES.Technique.Pass.PointSmoothEnable createProfileGLESTechniquePassPointSmoothEnable()
  {
    return new ProfileGLES.Technique.Pass.PointSmoothEnable();
  }
  public LibraryVisualScenes createLibraryVisualScenes()
  {
    return new LibraryVisualScenes();
  }
  public GlslNewparam createGlslNewparam()
  {
    return new GlslNewparam();
  }
  public FxClearcolorCommon createFxClearcolorCommon()
  {
    return new FxClearcolorCommon();
  }
  public CommonColorOrTextureType.Color createCommonColorOrTextureTypeColor()
  {
    return new CommonColorOrTextureType.Color();
  }
  public GlesSamplerState createGlesSamplerState()
  {
    return new GlesSamplerState();
  }
  public ProfileGLES.Technique.Pass.FogEnd createProfileGLESTechniquePassFogEnd()
  {
    return new ProfileGLES.Technique.Pass.FogEnd();
  }
  public FxClearstencilCommon createFxClearstencilCommon()
  {
    return new FxClearstencilCommon();
  }
  public FxSurfaceInitFromCommon createFxSurfaceInitFromCommon()
  {
    return new FxSurfaceInitFromCommon();
  }
  public Spline createSpline()
  {
    return new Spline();
  }
  public ProfileGLSL.Technique.Pass.LightSpotDirection createProfileGLSLTechniquePassLightSpotDirection()
  {
    return new ProfileGLSL.Technique.Pass.LightSpotDirection();
  }
  public ProfileGLSL.Technique.Pass.ProjectionMatrix createProfileGLSLTechniquePassProjectionMatrix()
  {
    return new ProfileGLSL.Technique.Pass.ProjectionMatrix();
  }
  public ProfileGLSL.Technique.Pass.LightModelTwoSideEnable createProfileGLSLTechniquePassLightModelTwoSideEnable()
  {
    return new ProfileGLSL.Technique.Pass.LightModelTwoSideEnable();
  }
  public RigidConstraint.TechniqueCommon.Interpenetrate createRigidConstraintTechniqueCommonInterpenetrate()
  {
    return new RigidConstraint.TechniqueCommon.Interpenetrate();
  }
  public ProfileCOMMON.Technique.Constant createProfileCOMMONTechniqueConstant()
  {
    return new ProfileCOMMON.Technique.Constant();
  }
  public CommonTransparentType createCommonTransparentType()
  {
    return new CommonTransparentType();
  }
  public ProfileGLSL.Technique.Pass.StencilFuncSeparate.Front createProfileGLSLTechniquePassStencilFuncSeparateFront()
  {
    return new ProfileGLSL.Technique.Pass.StencilFuncSeparate.Front();
  }
  public InstanceEffect.TechniqueHint createInstanceEffectTechniqueHint()
  {
    return new InstanceEffect.TechniqueHint();
  }
  public ProfileCG.Technique createProfileCGTechnique()
  {
    return new ProfileCG.Technique();
  }
  public ProfileGLSL.Technique.Pass.StencilFuncSeparate.Ref createProfileGLSLTechniquePassStencilFuncSeparateRef()
  {
    return new ProfileGLSL.Technique.Pass.StencilFuncSeparate.Ref();
  }
  public ProfileGLES.Technique.Pass.StencilFunc.Ref createProfileGLESTechniquePassStencilFuncRef()
  {
    return new ProfileGLES.Technique.Pass.StencilFunc.Ref();
  }
  public Light.TechniqueCommon.Directional createLightTechniqueCommonDirectional()
  {
    return new Light.TechniqueCommon.Directional();
  }
  public ProfileGLES.Technique.Pass.ProjectionMatrix createProfileGLESTechniquePassProjectionMatrix()
  {
    return new ProfileGLES.Technique.Pass.ProjectionMatrix();
  }
  public PhysicsModel createPhysicsModel()
  {
    return new PhysicsModel();
  }
  public Lookat createLookat()
  {
    return new Lookat();
  }
  public BindMaterial createBindMaterial()
  {
    return new BindMaterial();
  }
  public Asset createAsset()
  {
    return new Asset();
  }
  public CgSampler2D createCgSampler2D()
  {
    return new CgSampler2D();
  }
  public LibraryMaterials createLibraryMaterials()
  {
    return new LibraryMaterials();
  }
  public ProfileGLSL.Technique.Pass.BlendFuncSeparate.DestAlpha createProfileGLSLTechniquePassBlendFuncSeparateDestAlpha()
  {
    return new ProfileGLSL.Technique.Pass.BlendFuncSeparate.DestAlpha();
  }
  public ProfileGLES.Technique.Pass.LightDiffuse createProfileGLESTechniquePassLightDiffuse()
  {
    return new ProfileGLES.Technique.Pass.LightDiffuse();
  }
  public GlSamplerRECT createGlSamplerRECT()
  {
    return new GlSamplerRECT();
  }
  public ProfileGLSL.Technique.Pass.FogMode createProfileGLSLTechniquePassFogMode()
  {
    return new ProfileGLSL.Technique.Pass.FogMode();
  }
  public ProfileGLSL.Technique.Pass.PolygonOffsetFillEnable createProfileGLSLTechniquePassPolygonOffsetFillEnable()
  {
    return new ProfileGLSL.Technique.Pass.PolygonOffsetFillEnable();
  }
  public Camera.Optics.TechniqueCommon.Orthographic createCameraOpticsTechniqueCommonOrthographic()
  {
    return new Camera.Optics.TechniqueCommon.Orthographic();
  }
  public ProfileGLSL.Technique.Pass.CullFaceEnable createProfileGLSLTechniquePassCullFaceEnable()
  {
    return new ProfileGLSL.Technique.Pass.CullFaceEnable();
  }
  public ConvexMesh createConvexMesh()
  {
    return new ConvexMesh();
  }
  public GlesTextureUnit.Texcoord createGlesTextureUnitTexcoord()
  {
    return new GlesTextureUnit.Texcoord();
  }
  public ProfileGLES.Technique.Pass.ColorLogicOpEnable createProfileGLESTechniquePassColorLogicOpEnable()
  {
    return new ProfileGLES.Technique.Pass.ColorLogicOpEnable();
  }
  public ProfileGLES.Technique.Pass.MaterialEmission createProfileGLESTechniquePassMaterialEmission()
  {
    return new ProfileGLES.Technique.Pass.MaterialEmission();
  }
  public ProfileGLSL.Technique.Pass.CullFace createProfileGLSLTechniquePassCullFace()
  {
    return new ProfileGLSL.Technique.Pass.CullFace();
  }
  public ProfileGLES.Technique.Pass.PointSizeMin createProfileGLESTechniquePassPointSizeMin()
  {
    return new ProfileGLES.Technique.Pass.PointSizeMin();
  }
  public FxSampler1DCommon createFxSampler1DCommon()
  {
    return new FxSampler1DCommon();
  }
  public TargetableFloat createTargetableFloat()
  {
    return new TargetableFloat();
  }
  public Geometry createGeometry()
  {
    return new Geometry();
  }
  public COLLADA.Scene createCOLLADAScene()
  {
    return new COLLADA.Scene();
  }
  public Spline.ControlVertices createSplineControlVertices()
  {
    return new Spline.ControlVertices();
  }
  public ProfileGLSL.Technique.Pass.ClearColor createProfileGLSLTechniquePassClearColor()
  {
    return new ProfileGLSL.Technique.Pass.ClearColor();
  }
  public ProfileGLSL.Technique.Pass.NormalizeEnable createProfileGLSLTechniquePassNormalizeEnable()
  {
    return new ProfileGLSL.Technique.Pass.NormalizeEnable();
  }
  public Light.TechniqueCommon.Ambient createLightTechniqueCommonAmbient()
  {
    return new Light.TechniqueCommon.Ambient();
  }
  public VisualScene.EvaluateScene.Render createVisualSceneEvaluateSceneRender()
  {
    return new VisualScene.EvaluateScene.Render();
  }
  public Asset.Unit createAssetUnit()
  {
    return new Asset.Unit();
  }
  public GlesTexcombinerCommandAlphaType createGlesTexcombinerCommandAlphaType()
  {
    return new GlesTexcombinerCommandAlphaType();
  }
  public ProfileCG.Technique.Pass.Shader.Bind createProfileCGTechniquePassShaderBind()
  {
    return new ProfileCG.Technique.Pass.Shader.Bind();
  }
  public ProfileGLES.Technique.Pass.BlendFunc.Src createProfileGLESTechniquePassBlendFuncSrc()
  {
    return new ProfileGLES.Technique.Pass.BlendFunc.Src();
  }
  public ProfileGLSL.Technique.Pass.Texture2DEnable createProfileGLSLTechniquePassTexture2DEnable()
  {
    return new ProfileGLSL.Technique.Pass.Texture2DEnable();
  }
  public ProfileGLES.Technique.Pass.LightEnable createProfileGLESTechniquePassLightEnable()
  {
    return new ProfileGLES.Technique.Pass.LightEnable();
  }
  public ProfileGLSL.Technique.Pass.BlendFunc.Dest createProfileGLSLTechniquePassBlendFuncDest()
  {
    return new ProfileGLSL.Technique.Pass.BlendFunc.Dest();
  }
  public InstanceGeometry createInstanceGeometry()
  {
    return new InstanceGeometry();
  }
  public TargetableFloat3 createTargetableFloat3()
  {
    return new TargetableFloat3();
  }
  public LibraryPhysicsScenes createLibraryPhysicsScenes()
  {
    return new LibraryPhysicsScenes();
  }
  public ProfileGLES.Technique.Pass.PolygonOffsetFillEnable createProfileGLESTechniquePassPolygonOffsetFillEnable()
  {
    return new ProfileGLES.Technique.Pass.PolygonOffsetFillEnable();
  }
  public ProfileGLSL.Technique.Pass.Shader.CompilerTarget createProfileGLSLTechniquePassShaderCompilerTarget()
  {
    return new ProfileGLSL.Technique.Pass.Shader.CompilerTarget();
  }
  public GlesTextureConstantType createGlesTextureConstantType()
  {
    return new GlesTextureConstantType();
  }
  public ProfileGLES.Technique.Pass.LightPosition createProfileGLESTechniquePassLightPosition()
  {
    return new ProfileGLES.Technique.Pass.LightPosition();
  }
  public ProfileGLES.Technique.Pass.LogicOp createProfileGLESTechniquePassLogicOp()
  {
    return new ProfileGLES.Technique.Pass.LogicOp();
  }
  public RigidBody.TechniqueCommon createRigidBodyTechniqueCommon()
  {
    return new RigidBody.TechniqueCommon();
  }
  public ProfileGLES.Technique.Pass.ColorMaterialEnable createProfileGLESTechniquePassColorMaterialEnable()
  {
    return new ProfileGLES.Technique.Pass.ColorMaterialEnable();
  }
  public Technique createTechnique()
  {
    return new Technique();
  }
  public Camera createCamera()
  {
    return new Camera();
  }
  public FxSampler2DCommon createFxSampler2DCommon()
  {
    return new FxSampler2DCommon();
  }
  public LibraryForceFields createLibraryForceFields()
  {
    return new LibraryForceFields();
  }
  public CgNewarrayType createCgNewarrayType()
  {
    return new CgNewarrayType();
  }
  public InstanceMaterial.Bind createInstanceMaterialBind()
  {
    return new InstanceMaterial.Bind();
  }
  public ProfileGLSL.Technique.Pass.LightModelLocalViewerEnable createProfileGLSLTechniquePassLightModelLocalViewerEnable()
  {
    return new ProfileGLSL.Technique.Pass.LightModelLocalViewerEnable();
  }
  public ProfileGLSL.Technique.Pass.Texture3D createProfileGLSLTechniquePassTexture3D()
  {
    return new ProfileGLSL.Technique.Pass.Texture3D();
  }
  public ProfileGLSL.Technique.Pass.BlendEnable createProfileGLSLTechniquePassBlendEnable()
  {
    return new ProfileGLSL.Technique.Pass.BlendEnable();
  }
  public ProfileGLES.Technique.Pass.ClipPlane createProfileGLESTechniquePassClipPlane()
  {
    return new ProfileGLES.Technique.Pass.ClipPlane();
  }
  public ProfileGLSL.Technique.Pass.PolygonMode.Mode createProfileGLSLTechniquePassPolygonModeMode()
  {
    return new ProfileGLSL.Technique.Pass.PolygonMode.Mode();
  }
  public ProfileCOMMON.Technique.Phong createProfileCOMMONTechniquePhong()
  {
    return new ProfileCOMMON.Technique.Phong();
  }
  public ProfileGLES.Technique.Pass.TexturePipeline createProfileGLESTechniquePassTexturePipeline()
  {
    return new ProfileGLES.Technique.Pass.TexturePipeline();
  }
  public InstanceRigidBody.TechniqueCommon.Shape.Hollow createInstanceRigidBodyTechniqueCommonShapeHollow()
  {
    return new InstanceRigidBody.TechniqueCommon.Shape.Hollow();
  }
  public ProfileGLSL.Technique.Pass.FogDensity createProfileGLSLTechniquePassFogDensity()
  {
    return new ProfileGLSL.Technique.Pass.FogDensity();
  }
  public ProfileGLSL.Technique.Pass.StencilFunc.Ref createProfileGLSLTechniquePassStencilFuncRef()
  {
    return new ProfileGLSL.Technique.Pass.StencilFunc.Ref();
  }
  public InputGlobal createInputGlobal()
  {
    return new InputGlobal();
  }
  public ProfileGLSL.Technique.Pass.StencilFuncSeparate.Mask createProfileGLSLTechniquePassStencilFuncSeparateMask()
  {
    return new ProfileGLSL.Technique.Pass.StencilFuncSeparate.Mask();
  }
  public FxIncludeCommon createFxIncludeCommon()
  {
    return new FxIncludeCommon();
  }
  public ProfileGLSL.Technique.Pass.PolygonStippleEnable createProfileGLSLTechniquePassPolygonStippleEnable()
  {
    return new ProfileGLSL.Technique.Pass.PolygonStippleEnable();
  }
  public GlSampler3D createGlSampler3D()
  {
    return new GlSampler3D();
  }
  public VisualScene.EvaluateScene createVisualSceneEvaluateScene()
  {
    return new VisualScene.EvaluateScene();
  }
  public ProfileGLSL.Technique.Pass.DepthFunc createProfileGLSLTechniquePassDepthFunc()
  {
    return new ProfileGLSL.Technique.Pass.DepthFunc();
  }
  public ProfileGLES.Technique.Pass.MaterialShininess createProfileGLESTechniquePassMaterialShininess()
  {
    return new ProfileGLES.Technique.Pass.MaterialShininess();
  }
  public ProfileGLES.Technique.Pass.DitherEnable createProfileGLESTechniquePassDitherEnable()
  {
    return new ProfileGLES.Technique.Pass.DitherEnable();
  }
  public CommonNewparamType createCommonNewparamType()
  {
    return new CommonNewparamType();
  }
  public FxDepthtargetCommon createFxDepthtargetCommon()
  {
    return new FxDepthtargetCommon();
  }
  public ProfileGLES.Technique.Pass.LightSpotExponent createProfileGLESTechniquePassLightSpotExponent()
  {
    return new ProfileGLES.Technique.Pass.LightSpotExponent();
  }
  public ProfileGLSL.Technique.Pass.DepthRange createProfileGLSLTechniquePassDepthRange()
  {
    return new ProfileGLSL.Technique.Pass.DepthRange();
  }
  public ProfileGLES.Technique.Pass.FrontFace createProfileGLESTechniquePassFrontFace()
  {
    return new ProfileGLES.Technique.Pass.FrontFace();
  }
  public ProfileGLSL.Technique.Pass.BlendEquationSeparate.Rgb createProfileGLSLTechniquePassBlendEquationSeparateRgb()
  {
    return new ProfileGLSL.Technique.Pass.BlendEquationSeparate.Rgb();
  }
  public ProfileGLES.Technique.Pass.AlphaFunc.Value createProfileGLESTechniquePassAlphaFuncValue()
  {
    return new ProfileGLES.Technique.Pass.AlphaFunc.Value();
  }
  public ProfileGLSL.Technique.Pass.ClipPlane createProfileGLSLTechniquePassClipPlane()
  {
    return new ProfileGLSL.Technique.Pass.ClipPlane();
  }
  public ProfileGLES.Technique.Pass.MultisampleEnable createProfileGLESTechniquePassMultisampleEnable()
  {
    return new ProfileGLES.Technique.Pass.MultisampleEnable();
  }
  public AnimationClip createAnimationClip()
  {
    return new AnimationClip();
  }
  public ProfileGLES.Technique.Pass.BlendEnable createProfileGLESTechniquePassBlendEnable()
  {
    return new ProfileGLES.Technique.Pass.BlendEnable();
  }
  public Polygons.Ph createPolygonsPh()
  {
    return new Polygons.Ph();
  }
  public LibraryAnimations createLibraryAnimations()
  {
    return new LibraryAnimations();
  }
  public PhysicsMaterial createPhysicsMaterial()
  {
    return new PhysicsMaterial();
  }
  public Trifans createTrifans()
  {
    return new Trifans();
  }
  public ProfileGLES.Technique.Pass.DepthTestEnable createProfileGLESTechniquePassDepthTestEnable()
  {
    return new ProfileGLES.Technique.Pass.DepthTestEnable();
  }
  public CgSurfaceType createCgSurfaceType()
  {
    return new CgSurfaceType();
  }
  public ProfileGLSL.Technique.Pass.StencilFunc createProfileGLSLTechniquePassStencilFunc()
  {
    return new ProfileGLSL.Technique.Pass.StencilFunc();
  }
  public ProfileGLSL.Technique.Pass.LogicOpEnable createProfileGLSLTechniquePassLogicOpEnable()
  {
    return new ProfileGLSL.Technique.Pass.LogicOpEnable();
  }
  public GlslSetparamSimple createGlslSetparamSimple()
  {
    return new GlslSetparamSimple();
  }
  public ProfileGLES.Technique.Pass.StencilMask createProfileGLESTechniquePassStencilMask()
  {
    return new ProfileGLES.Technique.Pass.StencilMask();
  }
  public FxSurfaceInitCubeCommon.All createFxSurfaceInitCubeCommonAll()
  {
    return new FxSurfaceInitCubeCommon.All();
  }
  public ProfileGLSL.Technique.Pass.RescaleNormalEnable createProfileGLSLTechniquePassRescaleNormalEnable()
  {
    return new ProfileGLSL.Technique.Pass.RescaleNormalEnable();
  }
  public GlslSurfaceType.Generator.Name createGlslSurfaceTypeGeneratorName()
  {
    return new GlslSurfaceType.Generator.Name();
  }
  public InstanceMaterial.BindVertexInput createInstanceMaterialBindVertexInput()
  {
    return new InstanceMaterial.BindVertexInput();
  }
  public RigidConstraint.TechniqueCommon.Spring createRigidConstraintTechniqueCommonSpring()
  {
    return new RigidConstraint.TechniqueCommon.Spring();
  }
  public ProfileGLSL.Technique.Pass.TextureDEPTH createProfileGLSLTechniquePassTextureDEPTH()
  {
    return new ProfileGLSL.Technique.Pass.TextureDEPTH();
  }
  public ProfileGLSL.Technique.Pass.LightingEnable createProfileGLSLTechniquePassLightingEnable()
  {
    return new ProfileGLSL.Technique.Pass.LightingEnable();
  }
  public FxSamplerDEPTHCommon createFxSamplerDEPTHCommon()
  {
    return new FxSamplerDEPTHCommon();
  }
  public ProfileGLSL.Technique.Pass.MaterialEmission createProfileGLSLTechniquePassMaterialEmission()
  {
    return new ProfileGLSL.Technique.Pass.MaterialEmission();
  }
  public FxSurfaceInitPlanarCommon createFxSurfaceInitPlanarCommon()
  {
    return new FxSurfaceInitPlanarCommon();
  }
  public ProfileGLSL.Technique.Pass.Texture1D createProfileGLSLTechniquePassTexture1D()
  {
    return new ProfileGLSL.Technique.Pass.Texture1D();
  }
  public ProfileGLSL.Technique.Pass.StencilMaskSeparate.Mask createProfileGLSLTechniquePassStencilMaskSeparateMask()
  {
    return new ProfileGLSL.Technique.Pass.StencilMaskSeparate.Mask();
  }
  public ProfileGLSL.Technique.Pass.PointFadeThresholdSize createProfileGLSLTechniquePassPointFadeThresholdSize()
  {
    return new ProfileGLSL.Technique.Pass.PointFadeThresholdSize();
  }
  public ProfileGLSL.Technique.Pass.StencilOp.Zpass createProfileGLSLTechniquePassStencilOpZpass()
  {
    return new ProfileGLSL.Technique.Pass.StencilOp.Zpass();
  }
  public ProfileGLSL.Technique.Pass.MaterialAmbient createProfileGLSLTechniquePassMaterialAmbient()
  {
    return new ProfileGLSL.Technique.Pass.MaterialAmbient();
  }
  public CgSetparamSimple createCgSetparamSimple()
  {
    return new CgSetparamSimple();
  }
  public RigidBody.TechniqueCommon.Shape createRigidBodyTechniqueCommonShape()
  {
    return new RigidBody.TechniqueCommon.Shape();
  }
  public ProfileGLSL.Technique.Pass.LightDiffuse createProfileGLSLTechniquePassLightDiffuse()
  {
    return new ProfileGLSL.Technique.Pass.LightDiffuse();
  }
  public ProfileGLSL.Technique.Pass.AlphaTestEnable createProfileGLSLTechniquePassAlphaTestEnable()
  {
    return new ProfileGLSL.Technique.Pass.AlphaTestEnable();
  }
  public ProfileGLSL.Technique.Pass.LightQuadraticAttenuation createProfileGLSLTechniquePassLightQuadraticAttenuation()
  {
    return new ProfileGLSL.Technique.Pass.LightQuadraticAttenuation();
  }
  public ProfileGLSL.Technique.Pass.TextureEnvColor createProfileGLSLTechniquePassTextureEnvColor()
  {
    return new ProfileGLSL.Technique.Pass.TextureEnvColor();
  }
  public ProfileGLSL.Technique.Pass.PointSizeMax createProfileGLSLTechniquePassPointSizeMax()
  {
    return new ProfileGLSL.Technique.Pass.PointSizeMax();
  }
  public ProfileGLSL.Technique.Pass.LineStippleEnable createProfileGLSLTechniquePassLineStippleEnable()
  {
    return new ProfileGLSL.Technique.Pass.LineStippleEnable();
  }
  public Capsule createCapsule()
  {
    return new Capsule();
  }
  public ProfileGLSL.Technique.Pass.PolygonMode.Face createProfileGLSLTechniquePassPolygonModeFace()
  {
    return new ProfileGLSL.Technique.Pass.PolygonMode.Face();
  }
  public CommonFloatOrParamType createCommonFloatOrParamType()
  {
    return new CommonFloatOrParamType();
  }
  public ProfileGLES.Technique.Pass.ShadeModel createProfileGLESTechniquePassShadeModel()
  {
    return new ProfileGLES.Technique.Pass.ShadeModel();
  }
  public LibraryImages createLibraryImages()
  {
    return new LibraryImages();
  }
  public ProfileGLSL.Technique.Pass.FrontFace createProfileGLSLTechniquePassFrontFace()
  {
    return new ProfileGLSL.Technique.Pass.FrontFace();
  }
  public ProfileGLES.Technique.Pass.LightQuadraticAttenuation createProfileGLESTechniquePassLightQuadraticAttenuation()
  {
    return new ProfileGLES.Technique.Pass.LightQuadraticAttenuation();
  }
  public CommonFloatOrParamType.Param createCommonFloatOrParamTypeParam()
  {
    return new CommonFloatOrParamType.Param();
  }
  public ProfileGLSL.Technique.Pass.TextureCUBEEnable createProfileGLSLTechniquePassTextureCUBEEnable()
  {
    return new ProfileGLSL.Technique.Pass.TextureCUBEEnable();
  }
  public ProfileGLSL.Technique.Pass.DepthMask createProfileGLSLTechniquePassDepthMask()
  {
    return new ProfileGLSL.Technique.Pass.DepthMask();
  }
  public Extra createExtra()
  {
    return new Extra();
  }
  public ProfileGLES.Technique.Pass.FogColor createProfileGLESTechniquePassFogColor()
  {
    return new ProfileGLES.Technique.Pass.FogColor();
  }
  public LibraryEffects createLibraryEffects()
  {
    return new LibraryEffects();
  }
  public ProfileGLES.Technique.Pass.LineSmoothEnable createProfileGLESTechniquePassLineSmoothEnable()
  {
    return new ProfileGLES.Technique.Pass.LineSmoothEnable();
  }
  public ProfileGLES.Technique.Pass.StencilFunc.Func createProfileGLESTechniquePassStencilFuncFunc()
  {
    return new ProfileGLES.Technique.Pass.StencilFunc.Func();
  }
  public ProfileGLSL.Technique.Pass.StencilMaskSeparate createProfileGLSLTechniquePassStencilMaskSeparate()
  {
    return new ProfileGLSL.Technique.Pass.StencilMaskSeparate();
  }
  public ProfileGLES.Technique.Pass createProfileGLESTechniquePass()
  {
    return new ProfileGLES.Technique.Pass();
  }
  public FxSurfaceInitCubeCommon.Face createFxSurfaceInitCubeCommonFace()
  {
    return new FxSurfaceInitCubeCommon.Face();
  }
  public FloatArray createFloatArray()
  {
    return new FloatArray();
  }
  public ProfileGLSL.Technique.Pass.Shader.Name createProfileGLSLTechniquePassShaderName()
  {
    return new ProfileGLSL.Technique.Pass.Shader.Name();
  }
  public FxSurfaceInitPlanarCommon.All createFxSurfaceInitPlanarCommonAll()
  {
    return new FxSurfaceInitPlanarCommon.All();
  }
  public ProfileCG.Technique.Pass.Shader createProfileCGTechniquePassShader()
  {
    return new ProfileCG.Technique.Pass.Shader();
  }
  public CgSampler1D createCgSampler1D()
  {
    return new CgSampler1D();
  }
  public Asset.Contributor createAssetContributor()
  {
    return new Asset.Contributor();
  }
  public COLLADA createCOLLADA()
  {
    return new COLLADA();
  }
  public ProfileGLSL.Technique.Pass.BlendFuncSeparate.SrcAlpha createProfileGLSLTechniquePassBlendFuncSeparateSrcAlpha()
  {
    return new ProfileGLSL.Technique.Pass.BlendFuncSeparate.SrcAlpha();
  }
  public ProfileGLES.Technique.Pass.LightSpotCutoff createProfileGLESTechniquePassLightSpotCutoff()
  {
    return new ProfileGLES.Technique.Pass.LightSpotCutoff();
  }
  public Camera.Imager createCameraImager()
  {
    return new Camera.Imager();
  }
  public ProfileGLES.Technique.Pass.LightModelTwoSideEnable createProfileGLESTechniquePassLightModelTwoSideEnable()
  {
    return new ProfileGLES.Technique.Pass.LightModelTwoSideEnable();
  }
  public Mesh createMesh()
  {
    return new Mesh();
  }
  public InstanceRigidConstraint createInstanceRigidConstraint()
  {
    return new InstanceRigidConstraint();
  }
  public LibraryNodes createLibraryNodes()
  {
    return new LibraryNodes();
  }
  public GlesNewparam createGlesNewparam()
  {
    return new GlesNewparam();
  }
  public ProfileGLSL.Technique.Pass.BlendFuncSeparate createProfileGLSLTechniquePassBlendFuncSeparate()
  {
    return new ProfileGLSL.Technique.Pass.BlendFuncSeparate();
  }
  public ProfileGLSL.Technique.Pass.AutoNormalEnable createProfileGLSLTechniquePassAutoNormalEnable()
  {
    return new ProfileGLSL.Technique.Pass.AutoNormalEnable();
  }
  public RigidBody createRigidBody()
  {
    return new RigidBody();
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="zfar", scope=Camera.Optics.TechniqueCommon.Perspective.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonPerspectiveZfar(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveZfar_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Perspective.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="xfov", scope=Camera.Optics.TechniqueCommon.Perspective.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonPerspectiveXfov(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveXfov_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Perspective.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="yfov", scope=Camera.Optics.TechniqueCommon.Perspective.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonPerspectiveYfov(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveYfov_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Perspective.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="aspect_ratio", scope=Camera.Optics.TechniqueCommon.Perspective.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonPerspectiveAspectRatio(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveAspectRatio_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Perspective.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="znear", scope=Camera.Optics.TechniqueCommon.Perspective.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonPerspectiveZnear(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveZnear_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Perspective.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="scale")
  public JAXBElement<TargetableFloat3> createScale(TargetableFloat3 value)
  {
    return new JAXBElement(_Scale_QNAME, TargetableFloat3.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="profile_GLSL", substitutionHeadNamespace="http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName="fx_profile_abstract")
  public JAXBElement<ProfileGLSL> createProfileGLSL(ProfileGLSL value)
  {
    return new JAXBElement(_ProfileGLSL_QNAME, ProfileGLSL.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="instance_camera")
  public JAXBElement<InstanceWithExtra> createInstanceCamera(InstanceWithExtra value)
  {
    return new JAXBElement(_InstanceCamera_QNAME, InstanceWithExtra.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="profile_GLES", substitutionHeadNamespace="http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName="fx_profile_abstract")
  public JAXBElement<ProfileGLES> createProfileGLES(ProfileGLES value)
  {
    return new JAXBElement(_ProfileGLES_QNAME, ProfileGLES.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="translate")
  public JAXBElement<TargetableFloat3> createTranslate(TargetableFloat3 value)
  {
    return new JAXBElement(_Translate_QNAME, TargetableFloat3.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="p")
  public JAXBElement<List<BigInteger>> createP(List<BigInteger> value)
  {
    return new JAXBElement(_P_QNAME, List.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="instance_light")
  public JAXBElement<InstanceWithExtra> createInstanceLight(InstanceWithExtra value)
  {
    return new JAXBElement(_InstanceLight_QNAME, InstanceWithExtra.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="profile_CG", substitutionHeadNamespace="http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName="fx_profile_abstract")
  public JAXBElement<ProfileCG> createProfileCG(ProfileCG value)
  {
    return new JAXBElement(_ProfileCG_QNAME, ProfileCG.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="instance_physics_material")
  public JAXBElement<InstanceWithExtra> createInstancePhysicsMaterial(InstanceWithExtra value)
  {
    return new JAXBElement(_InstancePhysicsMaterial_QNAME, InstanceWithExtra.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="instance_node")
  public JAXBElement<InstanceWithExtra> createInstanceNode(InstanceWithExtra value)
  {
    return new JAXBElement(_InstanceNode_QNAME, InstanceWithExtra.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="gl_hook_abstract")
  public JAXBElement<Object> createGlHookAbstract(Object value)
  {
    return new JAXBElement(_GlHookAbstract_QNAME, Object.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="instance_force_field")
  public JAXBElement<InstanceWithExtra> createInstanceForceField(InstanceWithExtra value)
  {
    return new JAXBElement(_InstanceForceField_QNAME, InstanceWithExtra.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="profile_COMMON", substitutionHeadNamespace="http://www.collada.org/2005/11/COLLADASchema", substitutionHeadName="fx_profile_abstract")
  public JAXBElement<ProfileCOMMON> createProfileCOMMON(ProfileCOMMON value)
  {
    return new JAXBElement(_ProfileCOMMON_QNAME, ProfileCOMMON.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fx_profile_abstract")
  public JAXBElement<Object> createFxProfileAbstract(Object value)
  {
    return new JAXBElement(_FxProfileAbstract_QNAME, Object.class, null, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="enum", scope=CgNewarrayType.class)
  public JAXBElement<String> createCgNewarrayTypeEnum(String value)
  {
    return new JAXBElement(_CgNewarrayTypeEnum_QNAME, String.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler1D", scope=CgNewarrayType.class)
  public JAXBElement<CgSampler1D> createCgNewarrayTypeSampler1D(CgSampler1D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler1D_QNAME, CgSampler1D.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1", scope=CgNewarrayType.class)
  public JAXBElement<Float> createCgNewarrayTypeFixed1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1_QNAME, Float.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int", scope=CgNewarrayType.class)
  public JAXBElement<Integer> createCgNewarrayTypeInt(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt_QNAME, Integer.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half", scope=CgNewarrayType.class)
  public JAXBElement<Float> createCgNewarrayTypeHalf(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf_QNAME, Float.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed", scope=CgNewarrayType.class)
  public JAXBElement<Float> createCgNewarrayTypeFixed(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed_QNAME, Float.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerDEPTH", scope=CgNewarrayType.class)
  public JAXBElement<CgSamplerDEPTH> createCgNewarrayTypeSamplerDEPTH(CgSamplerDEPTH value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerDEPTH_QNAME, CgSamplerDEPTH.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1", scope=CgNewarrayType.class)
  public JAXBElement<Float> createCgNewarrayTypeFloat1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1_QNAME, Float.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="array", scope=CgNewarrayType.class)
  public JAXBElement<CgNewarrayType> createCgNewarrayTypeArray(CgNewarrayType value)
  {
    return new JAXBElement(_CgNewarrayTypeArray_QNAME, CgNewarrayType.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1", scope=CgNewarrayType.class)
  public JAXBElement<Float> createCgNewarrayTypeHalf1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1_QNAME, Float.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1", scope=CgNewarrayType.class)
  public JAXBElement<Integer> createCgNewarrayTypeInt1(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1_QNAME, Integer.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1", scope=CgNewarrayType.class)
  public JAXBElement<Boolean> createCgNewarrayTypeBool1(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1_QNAME, Boolean.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler2D", scope=CgNewarrayType.class)
  public JAXBElement<CgSampler2D> createCgNewarrayTypeSampler2D(CgSampler2D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler2D_QNAME, CgSampler2D.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool", scope=CgNewarrayType.class)
  public JAXBElement<Boolean> createCgNewarrayTypeBool(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool_QNAME, Boolean.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="string", scope=CgNewarrayType.class)
  public JAXBElement<String> createCgNewarrayTypeString(String value)
  {
    return new JAXBElement(_CgNewarrayTypeString_QNAME, String.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="usertype", scope=CgNewarrayType.class)
  public JAXBElement<CgSetuserType> createCgNewarrayTypeUsertype(CgSetuserType value)
  {
    return new JAXBElement(_CgNewarrayTypeUsertype_QNAME, CgSetuserType.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float", scope=CgNewarrayType.class)
  public JAXBElement<Float> createCgNewarrayTypeFloat(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat_QNAME, Float.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerRECT", scope=CgNewarrayType.class)
  public JAXBElement<CgSamplerRECT> createCgNewarrayTypeSamplerRECT(CgSamplerRECT value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerRECT_QNAME, CgSamplerRECT.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler3D", scope=CgNewarrayType.class)
  public JAXBElement<CgSampler3D> createCgNewarrayTypeSampler3D(CgSampler3D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler3D_QNAME, CgSampler3D.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="connect_param", scope=CgNewarrayType.class)
  public JAXBElement<CgConnectParam> createCgNewarrayTypeConnectParam(CgConnectParam value)
  {
    return new JAXBElement(_CgNewarrayTypeConnectParam_QNAME, CgConnectParam.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerCUBE", scope=CgNewarrayType.class)
  public JAXBElement<CgSamplerCUBE> createCgNewarrayTypeSamplerCUBE(CgSamplerCUBE value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerCUBE_QNAME, CgSamplerCUBE.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool3X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt2X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt1X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="surface", scope=CgNewarrayType.class)
  public JAXBElement<CgSurfaceType> createCgNewarrayTypeSurface(CgSurfaceType value)
  {
    return new JAXBElement(_CgNewarrayTypeSurface_QNAME, CgSurfaceType.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool2X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFixed4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool4X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x4", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt3X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x3", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X3_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Boolean>> createCgNewarrayTypeBool1X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x1", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt4X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X1_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeFloat2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x2", scope=CgNewarrayType.class)
  public JAXBElement<List<Float>> createCgNewarrayTypeHalf2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X2_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4", scope=CgNewarrayType.class)
  public JAXBElement<List<Integer>> createCgNewarrayTypeInt4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4_QNAME, List.class, CgNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="enum", scope=GlslSetarrayType.class)
  public JAXBElement<String> createGlslSetarrayTypeEnum(String value)
  {
    return new JAXBElement(_CgNewarrayTypeEnum_QNAME, String.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="array", scope=GlslSetarrayType.class)
  public JAXBElement<GlslSetarrayType> createGlslSetarrayTypeArray(GlslSetarrayType value)
  {
    return new JAXBElement(_CgNewarrayTypeArray_QNAME, GlslSetarrayType.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4", scope=GlslSetarrayType.class)
  public JAXBElement<List<Float>> createGlslSetarrayTypeFloat4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerRECT", scope=GlslSetarrayType.class)
  public JAXBElement<GlSamplerRECT> createGlslSetarrayTypeSamplerRECT(GlSamplerRECT value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerRECT_QNAME, GlSamplerRECT.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3", scope=GlslSetarrayType.class)
  public JAXBElement<List<Integer>> createGlslSetarrayTypeInt3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerCUBE", scope=GlslSetarrayType.class)
  public JAXBElement<GlSamplerCUBE> createGlslSetarrayTypeSamplerCUBE(GlSamplerCUBE value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerCUBE_QNAME, GlSamplerCUBE.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler1D", scope=GlslSetarrayType.class)
  public JAXBElement<GlSampler1D> createGlslSetarrayTypeSampler1D(GlSampler1D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler1D_QNAME, GlSampler1D.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2", scope=GlslSetarrayType.class)
  public JAXBElement<List<Boolean>> createGlslSetarrayTypeBool2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2", scope=GlslSetarrayType.class)
  public JAXBElement<List<Integer>> createGlslSetarrayTypeInt2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3", scope=GlslSetarrayType.class)
  public JAXBElement<List<Float>> createGlslSetarrayTypeFloat3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x4", scope=GlslSetarrayType.class)
  public JAXBElement<List<Float>> createGlslSetarrayTypeFloat4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X4_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="surface", scope=GlslSetarrayType.class)
  public JAXBElement<GlslSurfaceType> createGlslSetarrayTypeSurface(GlslSurfaceType value)
  {
    return new JAXBElement(_CgNewarrayTypeSurface_QNAME, GlslSurfaceType.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x3", scope=GlslSetarrayType.class)
  public JAXBElement<List<Float>> createGlslSetarrayTypeFloat3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X3_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3", scope=GlslSetarrayType.class)
  public JAXBElement<List<Boolean>> createGlslSetarrayTypeBool3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2", scope=GlslSetarrayType.class)
  public JAXBElement<List<Float>> createGlslSetarrayTypeFloat2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler3D", scope=GlslSetarrayType.class)
  public JAXBElement<GlSampler3D> createGlslSetarrayTypeSampler3D(GlSampler3D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler3D_QNAME, GlSampler3D.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerDEPTH", scope=GlslSetarrayType.class)
  public JAXBElement<GlSamplerDEPTH> createGlslSetarrayTypeSamplerDEPTH(GlSamplerDEPTH value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerDEPTH_QNAME, GlSamplerDEPTH.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4", scope=GlslSetarrayType.class)
  public JAXBElement<List<Boolean>> createGlslSetarrayTypeBool4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler2D", scope=GlslSetarrayType.class)
  public JAXBElement<GlSampler2D> createGlslSetarrayTypeSampler2D(GlSampler2D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler2D_QNAME, GlSampler2D.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int", scope=GlslSetarrayType.class)
  public JAXBElement<Integer> createGlslSetarrayTypeInt(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt_QNAME, Integer.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x2", scope=GlslSetarrayType.class)
  public JAXBElement<List<Float>> createGlslSetarrayTypeFloat2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X2_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float", scope=GlslSetarrayType.class)
  public JAXBElement<Float> createGlslSetarrayTypeFloat(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat_QNAME, Float.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool", scope=GlslSetarrayType.class)
  public JAXBElement<Boolean> createGlslSetarrayTypeBool(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool_QNAME, Boolean.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4", scope=GlslSetarrayType.class)
  public JAXBElement<List<Integer>> createGlslSetarrayTypeInt4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4_QNAME, List.class, GlslSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="enum", scope=CgSetuserType.class)
  public JAXBElement<String> createCgSetuserTypeEnum(String value)
  {
    return new JAXBElement(_CgNewarrayTypeEnum_QNAME, String.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x2", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt1X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler1D", scope=CgSetuserType.class)
  public JAXBElement<CgSampler1D> createCgSetuserTypeSampler1D(CgSampler1D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler1D_QNAME, CgSampler1D.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1", scope=CgSetuserType.class)
  public JAXBElement<Float> createCgSetuserTypeFixed1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1_QNAME, Float.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x4", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int", scope=CgSetuserType.class)
  public JAXBElement<Integer> createCgSetuserTypeInt(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt_QNAME, Integer.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x2", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x4", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt2X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x2", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt3X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half", scope=CgSetuserType.class)
  public JAXBElement<Float> createCgSetuserTypeHalf(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf_QNAME, Float.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x3", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt2X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed", scope=CgSetuserType.class)
  public JAXBElement<Float> createCgSetuserTypeFixed(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed_QNAME, Float.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x4", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt1X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerDEPTH", scope=CgSetuserType.class)
  public JAXBElement<CgSamplerDEPTH> createCgSetuserTypeSamplerDEPTH(CgSamplerDEPTH value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerDEPTH_QNAME, CgSamplerDEPTH.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x1", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt1X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x1", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt2X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1", scope=CgSetuserType.class)
  public JAXBElement<Float> createCgSetuserTypeFloat1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1_QNAME, Float.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="array", scope=CgSetuserType.class)
  public JAXBElement<CgSetarrayType> createCgSetuserTypeArray(CgSetarrayType value)
  {
    return new JAXBElement(_CgNewarrayTypeArray_QNAME, CgSetarrayType.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1", scope=CgSetuserType.class)
  public JAXBElement<Float> createCgSetuserTypeHalf1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1_QNAME, Float.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x2", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x3", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt3X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1", scope=CgSetuserType.class)
  public JAXBElement<Integer> createCgSetuserTypeInt1(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1_QNAME, Integer.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x4", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x2", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x4", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x3", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1", scope=CgSetuserType.class)
  public JAXBElement<Boolean> createCgSetuserTypeBool1(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1_QNAME, Boolean.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler2D", scope=CgSetuserType.class)
  public JAXBElement<CgSampler2D> createCgSetuserTypeSampler2D(CgSampler2D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler2D_QNAME, CgSampler2D.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x3", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool", scope=CgSetuserType.class)
  public JAXBElement<Boolean> createCgSetuserTypeBool(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool_QNAME, Boolean.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="string", scope=CgSetuserType.class)
  public JAXBElement<String> createCgSetuserTypeString(String value)
  {
    return new JAXBElement(_CgNewarrayTypeString_QNAME, String.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="usertype", scope=CgSetuserType.class)
  public JAXBElement<CgSetuserType> createCgSetuserTypeUsertype(CgSetuserType value)
  {
    return new JAXBElement(_CgNewarrayTypeUsertype_QNAME, CgSetuserType.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x3", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x4", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float", scope=CgSetuserType.class)
  public JAXBElement<Float> createCgSetuserTypeFloat(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat_QNAME, Float.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerRECT", scope=CgSetuserType.class)
  public JAXBElement<CgSamplerRECT> createCgSetuserTypeSamplerRECT(CgSamplerRECT value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerRECT_QNAME, CgSamplerRECT.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x2", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler3D", scope=CgSetuserType.class)
  public JAXBElement<CgSampler3D> createCgSetuserTypeSampler3D(CgSampler3D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler3D_QNAME, CgSampler3D.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="connect_param", scope=CgSetuserType.class)
  public JAXBElement<CgConnectParam> createCgSetuserTypeConnectParam(CgConnectParam value)
  {
    return new JAXBElement(_CgNewarrayTypeConnectParam_QNAME, CgConnectParam.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x3", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x2", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt4X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerCUBE", scope=CgSetuserType.class)
  public JAXBElement<CgSamplerCUBE> createCgSetuserTypeSamplerCUBE(CgSamplerCUBE value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerCUBE_QNAME, CgSamplerCUBE.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x3", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt4X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x1", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool3X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x2", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt2X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x3", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt1X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="surface", scope=CgSetuserType.class)
  public JAXBElement<CgSurfaceType> createCgSetuserTypeSurface(CgSurfaceType value)
  {
    return new JAXBElement(_CgNewarrayTypeSurface_QNAME, CgSurfaceType.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x4", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt4X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x1", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool2X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x1", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt3X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFixed4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x1", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x1", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool4X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x4", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x4", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt3X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x3", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X3_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x1", scope=CgSetuserType.class)
  public JAXBElement<List<Boolean>> createCgSetuserTypeBool1X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x1", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt4X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X1_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeFloat2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4", scope=CgSetuserType.class)
  public JAXBElement<List<Integer>> createCgSetuserTypeInt4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x2", scope=CgSetuserType.class)
  public JAXBElement<List<Float>> createCgSetuserTypeHalf2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X2_QNAME, List.class, CgSetuserType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="h", scope=Polygons.Ph.class)
  public JAXBElement<List<BigInteger>> createPolygonsPhH(List<BigInteger> value)
  {
    return new JAXBElement(_PolygonsPhH_QNAME, List.class, Polygons.Ph.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="zfar", scope=Camera.Optics.TechniqueCommon.Orthographic.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonOrthographicZfar(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveZfar_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Orthographic.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="ymag", scope=Camera.Optics.TechniqueCommon.Orthographic.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonOrthographicYmag(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonOrthographicYmag_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Orthographic.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="xmag", scope=Camera.Optics.TechniqueCommon.Orthographic.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonOrthographicXmag(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonOrthographicXmag_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Orthographic.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="aspect_ratio", scope=Camera.Optics.TechniqueCommon.Orthographic.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonOrthographicAspectRatio(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveAspectRatio_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Orthographic.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="znear", scope=Camera.Optics.TechniqueCommon.Orthographic.class)
  public JAXBElement<TargetableFloat> createCameraOpticsTechniqueCommonOrthographicZnear(TargetableFloat value)
  {
    return new JAXBElement(_CameraOpticsTechniqueCommonPerspectiveZnear_QNAME, TargetableFloat.class, Camera.Optics.TechniqueCommon.Orthographic.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="ph", scope=Polygons.class)
  public JAXBElement<Polygons.Ph> createPolygonsPh(Polygons.Ph value)
  {
    return new JAXBElement(_PolygonsPh_QNAME, Polygons.Ph.class, Polygons.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="layer", scope=VisualScene.EvaluateScene.Render.class)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  public JAXBElement<String> createVisualSceneEvaluateSceneRenderLayer(String value)
  {
    return new JAXBElement(_VisualSceneEvaluateSceneRenderLayer_QNAME, String.class, VisualScene.EvaluateScene.Render.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="enum", scope=CgSetarrayType.class)
  public JAXBElement<String> createCgSetarrayTypeEnum(String value)
  {
    return new JAXBElement(_CgNewarrayTypeEnum_QNAME, String.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler1D", scope=CgSetarrayType.class)
  public JAXBElement<CgSampler1D> createCgSetarrayTypeSampler1D(CgSampler1D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler1D_QNAME, CgSampler1D.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1", scope=CgSetarrayType.class)
  public JAXBElement<Float> createCgSetarrayTypeFixed1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1_QNAME, Float.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int", scope=CgSetarrayType.class)
  public JAXBElement<Integer> createCgSetarrayTypeInt(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt_QNAME, Integer.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half", scope=CgSetarrayType.class)
  public JAXBElement<Float> createCgSetarrayTypeHalf(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf_QNAME, Float.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed", scope=CgSetarrayType.class)
  public JAXBElement<Float> createCgSetarrayTypeFixed(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed_QNAME, Float.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerDEPTH", scope=CgSetarrayType.class)
  public JAXBElement<CgSamplerDEPTH> createCgSetarrayTypeSamplerDEPTH(CgSamplerDEPTH value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerDEPTH_QNAME, CgSamplerDEPTH.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1", scope=CgSetarrayType.class)
  public JAXBElement<Float> createCgSetarrayTypeFloat1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1_QNAME, Float.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="array", scope=CgSetarrayType.class)
  public JAXBElement<CgSetarrayType> createCgSetarrayTypeArray(CgSetarrayType value)
  {
    return new JAXBElement(_CgNewarrayTypeArray_QNAME, CgSetarrayType.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1", scope=CgSetarrayType.class)
  public JAXBElement<Float> createCgSetarrayTypeHalf1(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1_QNAME, Float.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1", scope=CgSetarrayType.class)
  public JAXBElement<Integer> createCgSetarrayTypeInt1(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1_QNAME, Integer.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1", scope=CgSetarrayType.class)
  public JAXBElement<Boolean> createCgSetarrayTypeBool1(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1_QNAME, Boolean.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float1x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat1X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat1X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler2D", scope=CgSetarrayType.class)
  public JAXBElement<CgSampler2D> createCgSetarrayTypeSampler2D(CgSampler2D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler2D_QNAME, CgSampler2D.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool", scope=CgSetarrayType.class)
  public JAXBElement<Boolean> createCgSetarrayTypeBool(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool_QNAME, Boolean.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="string", scope=CgSetarrayType.class)
  public JAXBElement<String> createCgSetarrayTypeString(String value)
  {
    return new JAXBElement(_CgNewarrayTypeString_QNAME, String.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="usertype", scope=CgSetarrayType.class)
  public JAXBElement<CgSetuserType> createCgSetarrayTypeUsertype(CgSetuserType value)
  {
    return new JAXBElement(_CgNewarrayTypeUsertype_QNAME, CgSetuserType.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half4x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf4X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf4X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed1x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed1X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float", scope=CgSetarrayType.class)
  public JAXBElement<Float> createCgSetarrayTypeFloat(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat_QNAME, Float.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerRECT", scope=CgSetarrayType.class)
  public JAXBElement<CgSamplerRECT> createCgSetarrayTypeSamplerRECT(CgSamplerRECT value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerRECT_QNAME, CgSamplerRECT.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler3D", scope=CgSetarrayType.class)
  public JAXBElement<CgSampler3D> createCgSetarrayTypeSampler3D(CgSampler3D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler3D_QNAME, CgSampler3D.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat4X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed4X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerCUBE", scope=CgSetarrayType.class)
  public JAXBElement<CgSamplerCUBE> createCgSetarrayTypeSamplerCUBE(CgSamplerCUBE value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerCUBE_QNAME, CgSamplerCUBE.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool3X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt2X2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int1x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt1X3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt1X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="surface", scope=CgSetarrayType.class)
  public JAXBElement<CgSurfaceType> createCgSetarrayTypeSurface(CgSurfaceType value)
  {
    return new JAXBElement(_CgNewarrayTypeSurface_QNAME, CgSurfaceType.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool2X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed3x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed3X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed2x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed2X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed2X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="fixed4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFixed4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFixed4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half3x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf3X1(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf3X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool4X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half1x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf1X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf1X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3x4", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt3X4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3X4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat3X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool1x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Boolean>> createCgSetarrayTypeBool1X1(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool1X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x3", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X3_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4x1", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt4X1(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4X1_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeFloat2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="half2x2", scope=CgSetarrayType.class)
  public JAXBElement<List<Float>> createCgSetarrayTypeHalf2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeHalf2X2_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4", scope=CgSetarrayType.class)
  public JAXBElement<List<Integer>> createCgSetarrayTypeInt4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4_QNAME, List.class, CgSetarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="array", scope=GlslNewarrayType.class)
  public JAXBElement<GlslNewarrayType> createGlslNewarrayTypeArray(GlslNewarrayType value)
  {
    return new JAXBElement(_CgNewarrayTypeArray_QNAME, GlslNewarrayType.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="enum", scope=GlslNewarrayType.class)
  public JAXBElement<String> createGlslNewarrayTypeEnum(String value)
  {
    return new JAXBElement(_CgNewarrayTypeEnum_QNAME, String.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4", scope=GlslNewarrayType.class)
  public JAXBElement<List<Float>> createGlslNewarrayTypeFloat4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerRECT", scope=GlslNewarrayType.class)
  public JAXBElement<GlSamplerRECT> createGlslNewarrayTypeSamplerRECT(GlSamplerRECT value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerRECT_QNAME, GlSamplerRECT.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int3", scope=GlslNewarrayType.class)
  public JAXBElement<List<Integer>> createGlslNewarrayTypeInt3(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt3_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerCUBE", scope=GlslNewarrayType.class)
  public JAXBElement<GlSamplerCUBE> createGlslNewarrayTypeSamplerCUBE(GlSamplerCUBE value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerCUBE_QNAME, GlSamplerCUBE.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler1D", scope=GlslNewarrayType.class)
  public JAXBElement<GlSampler1D> createGlslNewarrayTypeSampler1D(GlSampler1D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler1D_QNAME, GlSampler1D.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool2", scope=GlslNewarrayType.class)
  public JAXBElement<List<Boolean>> createGlslNewarrayTypeBool2(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool2_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int2", scope=GlslNewarrayType.class)
  public JAXBElement<List<Integer>> createGlslNewarrayTypeInt2(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt2_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3", scope=GlslNewarrayType.class)
  public JAXBElement<List<Float>> createGlslNewarrayTypeFloat3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float4x4", scope=GlslNewarrayType.class)
  public JAXBElement<List<Float>> createGlslNewarrayTypeFloat4X4(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat4X4_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="surface", scope=GlslNewarrayType.class)
  public JAXBElement<GlslSurfaceType> createGlslNewarrayTypeSurface(GlslSurfaceType value)
  {
    return new JAXBElement(_CgNewarrayTypeSurface_QNAME, GlslSurfaceType.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float3x3", scope=GlslNewarrayType.class)
  public JAXBElement<List<Float>> createGlslNewarrayTypeFloat3X3(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat3X3_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool3", scope=GlslNewarrayType.class)
  public JAXBElement<List<Boolean>> createGlslNewarrayTypeBool3(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool3_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2", scope=GlslNewarrayType.class)
  public JAXBElement<List<Float>> createGlslNewarrayTypeFloat2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler3D", scope=GlslNewarrayType.class)
  public JAXBElement<GlSampler3D> createGlslNewarrayTypeSampler3D(GlSampler3D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler3D_QNAME, GlSampler3D.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="samplerDEPTH", scope=GlslNewarrayType.class)
  public JAXBElement<GlSamplerDEPTH> createGlslNewarrayTypeSamplerDEPTH(GlSamplerDEPTH value)
  {
    return new JAXBElement(_CgNewarrayTypeSamplerDEPTH_QNAME, GlSamplerDEPTH.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="sampler2D", scope=GlslNewarrayType.class)
  public JAXBElement<GlSampler2D> createGlslNewarrayTypeSampler2D(GlSampler2D value)
  {
    return new JAXBElement(_CgNewarrayTypeSampler2D_QNAME, GlSampler2D.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool4", scope=GlslNewarrayType.class)
  public JAXBElement<List<Boolean>> createGlslNewarrayTypeBool4(List<Boolean> value)
  {
    return new JAXBElement(_CgNewarrayTypeBool4_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int", scope=GlslNewarrayType.class)
  public JAXBElement<Integer> createGlslNewarrayTypeInt(Integer value)
  {
    return new JAXBElement(_CgNewarrayTypeInt_QNAME, Integer.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float", scope=GlslNewarrayType.class)
  public JAXBElement<Float> createGlslNewarrayTypeFloat(Float value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat_QNAME, Float.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="float2x2", scope=GlslNewarrayType.class)
  public JAXBElement<List<Float>> createGlslNewarrayTypeFloat2X2(List<Float> value)
  {
    return new JAXBElement(_CgNewarrayTypeFloat2X2_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="int4", scope=GlslNewarrayType.class)
  public JAXBElement<List<Integer>> createGlslNewarrayTypeInt4(List<Integer> value)
  {
    return new JAXBElement(_CgNewarrayTypeInt4_QNAME, List.class, GlslNewarrayType.class, value);
  }
  @XmlElementDecl(namespace="http://www.collada.org/2005/11/COLLADASchema", name="bool", scope=GlslNewarrayType.class)
  public JAXBElement<Boolean> createGlslNewarrayTypeBool(Boolean value)
  {
    return new JAXBElement(_CgNewarrayTypeBool_QNAME, Boolean.class, GlslNewarrayType.class, value);
  }
}

