// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Collada2X.java

package collada2x;

import collada.*;
import com.sun.xml.bind.IDResolver;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;
import javax.xml.bind.*;
import org.lc0277lib.Pair;
import org.lc0277lib.geom.*;

// Referenced classes of package collada2x:
//            XFrame, XMesh, XMaterial, FS10Material, 
//            Face, XOutput

public class Collada2X
{
    class UVAccessor
    {

        Point2D makePointFromSource(int index)
        {
            Point2D pt = new Point2D();
            int offset = index * stride;
            if(type.equalsIgnoreCase("float"))
            {
                pt.x = ((Double)src.getFloatArray().getValue().get(offset + posS)).doubleValue();
                pt.y = ((Double)src.getFloatArray().getValue().get(offset + posT)).doubleValue();
            } else
            if(type.equalsIgnoreCase("int"))
            {
                pt.x = ((Long)src.getIntArray().getValue().get(offset + posS)).longValue();
                pt.y = ((Long)src.getIntArray().getValue().get(offset + posT)).longValue();
            } else
            {
                warn((new StringBuilder()).append("Bad source data type '").append(type).append("'").toString());
                return null;
            }
            return pt;
        }

        private Source src;
        private int stride;
        private int posS;
        private int posT;
        private String type;
        final Collada2X this$0;

        UVAccessor(Source src)
        { super();
            this$0 = Collada2X.this;
           
            stride = 2;
            posS = 0;
            posT = 1;
            type = "float";
            this.src = src;
            collada.Source.TechniqueCommon tc = src.getTechniqueCommon();
            if(tc == null)
                throw new NullPointerException("No technique for vertices");
            Accessor accessor = tc.getAccessor();
            if(accessor == null)
                throw new NullPointerException("No accessor for vertices");
            stride = accessor.getStride().intValue();
            for(int i = 0; i < accessor.getParam().size(); i++)
            {
                Param p = (Param)accessor.getParam().get(i);
                if(p.getName().equalsIgnoreCase("S"))
                {
                    type = p.getType();
                    posS = i;
                    continue;
                }
                if(p.getName().equalsIgnoreCase("T"))
                    posT = i;
            }

        }
    }

    class VertexAccessor
    {

        Point3D makePointFromSource(int index)
        {
            Point3D pt = new Point3D();
            int offset = index * stride;
            if(type.equalsIgnoreCase("float"))
            {
                pt.x = ((Double)src.getFloatArray().getValue().get(offset + posX)).doubleValue();
                pt.y = ((Double)src.getFloatArray().getValue().get(offset + posY)).doubleValue();
                pt.z = ((Double)src.getFloatArray().getValue().get(offset + posZ)).doubleValue();
            } else
            if(type.equalsIgnoreCase("int"))
            {
                pt.x = ((Long)src.getIntArray().getValue().get(offset + posX)).longValue();
                pt.y = ((Long)src.getIntArray().getValue().get(offset + posY)).longValue();
                pt.z = ((Long)src.getIntArray().getValue().get(offset + posZ)).longValue();
            } else
            {
                warn((new StringBuilder()).append("bad source data type '").append(type).append("'").toString());
                return null;
            }
            return pt;
        }

        private Source src;
        private int stride;
        private int posX;
        private int posY;
        private int posZ;
        private String type;
        final Collada2X this$0;

        VertexAccessor(Source src)
        {super();
            this$0 = Collada2X.this;
            
            stride = 3;
            posX = 0;
            posY = 1;
            posZ = 2;
            type = "float";
            this.src = src;
            collada.Source.TechniqueCommon tc = src.getTechniqueCommon();
            if(tc == null)
                throw new NullPointerException("No technique for vertices");
            Accessor accessor = tc.getAccessor();
            if(accessor == null)
                throw new NullPointerException("No accessor for vertices");
            stride = accessor.getStride().intValue();
            for(int i = 0; i < accessor.getParam().size(); i++)
            {
                Param p = (Param)accessor.getParam().get(i);
                if(p.getName().equalsIgnoreCase("X"))
                {
                    type = p.getType();
                    posX = i;
                    continue;
                }
                if(p.getName().equalsIgnoreCase("Y"))
                {
                    posY = i;
                    continue;
                }
                if(p.getName().equalsIgnoreCase("Z"))
                    posZ = i;
            }

        }
    }


    public Collada2X(COLLADA colDocument, IDResolver resolver)
    {
        this.colDocument = colDocument;
        idr = resolver;
    }

    public XFrame getRootFrame()
    {
        if(rootFrame == null)
            try
            {
                buildRootFrame();
            }
            catch(Exception e)
            {
                System.err.println((new StringBuilder()).append("*** Error: ").append(e.getMessage()).toString());
                e.printStackTrace();
            }
        return rootFrame;
    }

    public void setWarnings(PrintStream warnings)
    {
        this.warnings = warnings;
    }

    private void warn(String s)
    {
        if(warnings != null)
            warnings.println(s);
        else
            System.err.println((new StringBuilder()).append("*** Warning: ").append(s).toString());
    }

    private void warn(Node n, String s)
    {
        warn((new StringBuilder()).append("At node ").append(n.getName()).append(" : ").append(s).toString());
    }

    private void buildRootFrame()
    {
        rootFrame = new XFrame("masterScale");
        double scale = colDocument.getAsset().getUnit().getMeter();
        rootFrame.setMatrix(new Matrix4(scale, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, scale, 0.0D, 0.0D, scale, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 1.0D));
        collada.COLLADA.Scene scene = colDocument.getScene();
        InstanceWithExtra instanceScene = scene.getInstanceVisualScene();
        VisualScene vs = lookupScene(instanceScene.getUrl());
        if(vs != null)
        {
            XFrame sceneFrame = new XFrame(vs.getName());
            rootFrame.getSubFrames().add(sceneFrame);
            Iterator i$ = vs.getNode().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Node n = (Node)i$.next();
                XFrame nFrame = buildXFrameForNode(n);
                if(nFrame != null)
                    sceneFrame.getSubFrames().add(nFrame);
            } while(true);
        }
    }

    private XFrame buildXFrameForNode(Node n)
    {
        XFrame f = new XFrame();
        if(n.getName() != null)
            f.setName(n.getName());
        boolean convertible = false;
        InstanceGeometry iGeom = null;
        boolean matrixWrote = false;
        Iterator i$ = n.getLookatOrMatrixOrRotate().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object o = i$.next();
            if(o instanceof Matrix)
                if(matrixWrote)
                {
                    warn(n, "Multiple matrices, only the first one will be used");
                } else
                {
                    Matrix mat = (Matrix)o;
                    buildXFrameMatrix(f, mat);
                    matrixWrote = true;
                }
            if(o instanceof Skew)
                warn(n, "Skew matrix unsupported");
            if(o instanceof Rotate)
                warn(n, "Rotate matrix unsupported");
        } while(true);
        if(n.getInstanceGeometry().size() > 0)
            iGeom = (InstanceGeometry)n.getInstanceGeometry().get(0);
			    Geometry geom;
        if(iGeom != null)
        {
         geom = lookupGeometry(iGeom.getUrl());
            convertible |= isGeometryConvertible(geom);
        }
        if(n.getInstanceGeometry().size() > 1)
            warn(n, "Multiple geometries, only the first one will be used");
        Iterator geom1 = n.getNode().iterator();
        do
        {
            if(!geom1.hasNext())
                break;
            Node son = (Node)geom1.next();
            XFrame sonFrame = buildXFrameForNode(son);
            if(sonFrame != null)
                f.getSubFrames().add(sonFrame);
        } while(true);
        geom1 = n.getInstanceNode().iterator();
        do
        {
            if(!geom1.hasNext())
                break;
            InstanceWithExtra ieSonNode = (InstanceWithExtra)geom1.next();
            String url = ieSonNode.getUrl();
            Node son = lookupNode(url);
            if(son != null)
            {
                XFrame sonFrame = buildXFrameForNode(son);
                if(sonFrame != null)
                    f.getSubFrames().add(sonFrame);
            } else
            {
                warn(n, "Instance node not found");
            }
        } while(true);
        convertible |= f.getSubFrames().size() > 0;
        if(!convertible)
            return null;
        if(iGeom != null)
        {
            Map materialsInstance = new HashMap();
            parseInstanceMaterials(iGeom, materialsInstance);
            XMesh mesh = buildXMesh(lookupGeometry(iGeom.getUrl()), materialsInstance);
            if(mesh == null)
                return null;
            f.setMesh(mesh);
        }
        return f;
    }

    private void buildXFrameMatrix(XFrame f, Matrix mat)
    {
        Matrix4 frameMat = f.getMatrix();
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                double d = ((Double)mat.getValue().get(4 * i + j)).doubleValue();
                frameMat.set(j, i, d);
            }

        }

    }

    private void parseInstanceMaterials(InstanceGeometry geom, Map materialsInstance)
    {
        BindMaterial bm = geom.getBindMaterial();
        if(bm == null)
            return;
        collada.BindMaterial.TechniqueCommon tm = bm.getTechniqueCommon();
        if(tm == null)
            return;
        String symbol;
        String target;
        for(Iterator i$ = tm.getInstanceMaterial().iterator(); i$.hasNext(); materialsInstance.put(symbol, target))
        {
            InstanceMaterial im = (InstanceMaterial)i$.next();
            symbol = im.getSymbol();
            target = im.getTarget();
        }

    }

    private XMesh buildXMesh(Geometry geometry, Map materialsInstance)
    {
        XMesh xMesh = new XMesh();
        Mesh m = geometry.getMesh();
        if(m == null)
            return null;
        if(geometry.getName() != null)
            xMesh.setName(geometry.getName());
        if(!buildXMesh(xMesh, geometry))
            return null;
        if(!buildXMaterials(xMesh, geometry, materialsInstance))
            return null;
        if(!buildNormals(xMesh, geometry))
            return null;
        else
            return xMesh;
    }

    private boolean buildXMaterials(XMesh xMesh, Geometry geometry, Map materialsInstance)
    {
        int blankMatId = -1;
        Iterator i$ = geometry.getMesh().getLinesOrLinestripsOrPolygons().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object o = i$.next();
            if(o instanceof Triangles)
            {
                Triangles tr = (Triangles)o;
                String matRef = tr.getMaterial();
                if(matRef == null)
                {
                    blankMatId = setBlankMaterialForTriangle(xMesh, tr, blankMatId);
                } else
                {
                    String matRefTarget = (String)materialsInstance.get(matRef);
                    if(matRefTarget == null)
                    {
                        warn((new StringBuilder()).append("Unknown material symbol '").append(matRef).append("' in geometry '").append(geometry.getName()).append("'").toString());
                        blankMatId = setBlankMaterialForTriangle(xMesh, tr, blankMatId);
                    } else
                    {
                        Material mat = lookupMaterial(matRefTarget);
                        if(mat == null)
                        {
                            warn((new StringBuilder()).append("Unknown material reference '").append(matRefTarget).append("' in geometry '").append(geometry.getName()).append("'").toString());
                            blankMatId = setBlankMaterialForTriangle(xMesh, tr, blankMatId);
                        } else
                        {
                            int matId = buildMaterial(xMesh, mat);
                            setFaceMaterial(xMesh, tr, matId);
                        }
                    }
                }
            }
        } while(true);
        return true;
    }

    private int buildMaterial(XMesh xMesh, Material mat)
    {
        InstanceEffect ie = mat.getInstanceEffect();
        if(ie == null)
        {
            warn((new StringBuilder()).append("No instance effect in material ").append(mat.getName()).toString());
            return addUnknownMaterial(xMesh);
        }
        String effectUrl = ie.getUrl();
        if(effectUrl == null)
        {
            warn((new StringBuilder()).append("No effect in material ").append(mat.getName()).toString());
            return addUnknownMaterial(xMesh);
        }
        if(effectUrl == null)
        {
            warn((new StringBuilder()).append("No effect in material ").append(mat.getName()).toString());
            return addUnknownMaterial(xMesh);
        }
        Effect fx = lookupEffect(effectUrl);
        if(fx != null);
        collada.ProfileCOMMON.Technique.Phong phong = null;
        Iterator i$ = fx.getFxProfileAbstract().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            JAXBElement jxb = (JAXBElement)i$.next();
            Object o = jxb.getValue();
            if(!(o instanceof ProfileCOMMON))
                continue;
            ProfileCOMMON pc = (ProfileCOMMON)o;
            collada.ProfileCOMMON.Technique tc = pc.getTechnique();
            phong = tc.getPhong();
            break;
        } while(true);
        if(phong == null)
        {
            warn((new StringBuilder()).append("No instance effect in material ").append(mat.getName()).toString());
            return addUnknownMaterial(xMesh);
        }
        XMaterial xmat = new XMaterial();
        xmat.setName(mat.getName());
        FS10Material fs10xMat = new FS10Material();
        xmat.setFs10Material(fs10xMat);
        CommonColorOrTextureType col = phong.getEmission();
        if(col != null && col.getColor() != null)
            xmat.setEmissiveColor(getColor(col));
        col = phong.getDiffuse();
        if(col.getColor() != null)
        {
            xmat.setFaceColor(getColor(col));
            fs10xMat.setDiffuseComponent(getColor(col));
        } else
        if(col.getTexture() != null)
        {
            String txtFile = resolveTexture(fx, col.getTexture());
            xmat.setTextureFileName(txtFile);
            xmat.setDiffuseTextureName(txtFile);
            fs10xMat.setDiffuseTextureFileName(txtFile);
            double defDiffuse[] = {
                0.5D, 0.5D, 0.5D, 1.0D
            };
            xmat.setFaceColor(defDiffuse);
            fs10xMat.setDiffuseComponent(defDiffuse);
        }
        col = phong.getSpecular();
        if(col != null)
        {
            xmat.setSpecularColor(getColor(col));
            fs10xMat.setSpecularComponent(getColor(col));
        }
        xmat.setPower(phong.getReflectivity().getFloat().getValue());
        return xMesh.addMaterial(xmat);
    }

    private String resolveTexture(Effect fx, collada.CommonColorOrTextureType.Texture txt)
    {
        if(txt == null)
            return null;
        String txtName = txt.getTexture();
        ProfileCOMMON pc = null;
        Iterator i$ = fx.getFxProfileAbstract().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            JAXBElement jb = (JAXBElement)i$.next();
            Object o = jb.getValue();
            if(!(o instanceof ProfileCOMMON))
                continue;
            pc = (ProfileCOMMON)o;
            break;
        } while(true);
        if(pc == null)
        {
            warn((new StringBuilder()).append("No profile common in effect ").append(fx.getName()).toString());
            return null;
        }
        String samplerSource = null;
      i$ = pc.getImageOrNewparam().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object o = i$.next();
            if(o instanceof CommonNewparamType)
            {
                CommonNewparamType cnpt = (CommonNewparamType)o;
                if(cnpt.getSid().equals(txtName))
                    samplerSource = cnpt.getSampler2D().getSource();
            }
        } while(true);
        if(samplerSource == null)
        {
            warn((new StringBuilder()).append("Cannot found sampler2D '").append(txtName).append("'").toString());
            return null;
        }
        String txtValue = null;
        for(i$ = pc.getImageOrNewparam().iterator(); i$.hasNext();)
        {
            Object o = i$.next();
            if(o instanceof CommonNewparamType)
            {
                CommonNewparamType cnpt = (CommonNewparamType)o;
                if(cnpt.getSid().equals(samplerSource))
                {
                    i$ = cnpt.getSurface().getInitFrom().iterator();
                    while(i$.hasNext()) 
                    {
                        FxSurfaceInitFromCommon fxSurf = (FxSurfaceInitFromCommon)i$.next();
                        Object val = fxSurf.getValue();
                        if(val instanceof Image)
                        {
                            Image im = (Image)val;
                            txtValue = im.getInitFrom();
                        }
                    }
                }
            }
        }

        if(txtValue == null)
        {
            warn((new StringBuilder()).append("Cannot init surface '").append(samplerSource).append("' no valid texture image").toString());
            return null;
        }
        int ip = txtValue.lastIndexOf('/');
        if(ip != -1)
            txtValue = txtValue.substring(ip + 1);
        ip = txtValue.lastIndexOf('.');
        if(ip != -1)
            txtValue = txtValue.substring(0, ip);
        txtValue = (new StringBuilder()).append(txtValue).append(".DDS").toString();
        return txtValue;
    }

    private double[] getColor(CommonColorOrTextureType cct)
    {
        int sz = cct.getColor().getValue().size();
        double vals[] = new double[sz];
        for(int i = 0; i < sz; i++)
            vals[i] = ((Double)cct.getColor().getValue().get(i)).doubleValue();

        return vals;
    }

    private int setBlankMaterialForTriangle(XMesh xMesh, Triangles tr, int blankMatId)
    {
        if(blankMatId == -1)
            blankMatId = addUnknownMaterial(xMesh);
        setFaceMaterial(xMesh, tr, blankMatId);
        return blankMatId;
    }

    private int addUnknownMaterial(XMesh xMesh)
    {
        XMaterial xm = new XMaterial();
        xm.setFaceColor(0.58823499999999995D, 0.58823499999999995D, 0.58823499999999995D, 1.0D);
        xm.setPower(0.0D);
        xm.setSpecularColor(0.90000000000000002D, 0.90000000000000002D, 0.90000000000000002D);
        xm.setEmissiveColor(0.0D, 0.0D, 0.0D);
        xm.setTextureFileName(null);
        xm.setDiffuseTextureName(null);
        xm.setFs10Material(null);
        return xMesh.addMaterial(xm);
    }

    private void setFaceMaterial(XMesh xMesh, Triangles tr, int matId)
    {
        int count = tr.getCount().intValue();
        for(int i = 0; i < count; i++)
            xMesh.addFaceMaterial(matId);

    }

    private boolean buildXMesh(XMesh xMesh, Geometry geom)
    {
        Mesh m = geom.getMesh();
        Map verticesCoordMaps = new HashMap();
        Vertices v = m.getVertices();
        if(v == null)
        {
            warn((new StringBuilder()).append("No vertices in mesh '").append(geom.getName()).append("'").toString());
            return false;
        }
        String srcUrl = null;
        Iterator i$ = v.getInput().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            InputLocal il = (InputLocal)i$.next();
            if(!il.getSemantic().equals("POSITION"))
                continue;
            srcUrl = il.getSource();
            break;
        } while(true);
        if(srcUrl == null)
        {
            warn((new StringBuilder()).append("No source in vertices for mesh '").append(geom.getName()).append("'").toString());
            return false;
        }
        Source src = lookupSource(srcUrl);
        if(src == null)
        {
            warn((new StringBuilder()).append("Cannot find source '").append(srcUrl).append("' in mesh '").append(geom.getName()).append("'").toString());
            return false;
        }
        VertexAccessor va = new VertexAccessor(src);
        i$ = m.getLinesOrLinestripsOrPolygons().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object o = i$.next();
            if(o instanceof Triangles)
            {
                Triangles tr = (Triangles)o;
                buildTriangle(geom, tr, verticesCoordMaps, va, xMesh);
            }
        } while(true);
        return true;
    }

    private boolean buildTriangle(Geometry geom, Triangles tr, Map verticesCoordMaps, VertexAccessor va, XMesh xMesh)
    {
        String vertexUrl = null;
        String texCoordUrl = null;
        int offsetVertex = 0;
        int offsetTexCoord = 2;
        Iterator i$ = tr.getInput().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            InputLocalOffset ilo = (InputLocalOffset)i$.next();
            if(ilo.getSemantic().equals("VERTEX"))
            {
                vertexUrl = ilo.getSource();
                offsetVertex = ilo.getOffset().intValue();
            }
            if(ilo.getSemantic().equals("TEXCOORD"))
            {
                texCoordUrl = ilo.getSource();
                offsetTexCoord = ilo.getOffset().intValue();
            }
        } while(true);
        if(vertexUrl == null)
        {
            warn((new StringBuilder()).append("No vertices in triangle '").append(tr.getName()).append("'").toString());
            return false;
        }
        if(texCoordUrl == null)
        {
            warn((new StringBuilder()).append("No texture coordinates in triangle '").append(tr.getName()).append("'").toString());
            return false;
        }
        Source srcTexCoord = lookupSource(texCoordUrl);
        if(srcTexCoord == null)
        {
            warn((new StringBuilder()).append("Source not found: '").append(texCoordUrl).append("'").toString());
            return false;
        }
        List data = tr.getP();
        int count = tr.getCount().intValue();
        int sizeElement = tr.getP().size() / (3 * count);
        UVAccessor uva = new UVAccessor(srcTexCoord);
        int offset = 0;
        for(int i = 0; i < count; i++)
        {
            int v1 = ((BigInteger)data.get(offset + offsetVertex)).intValue();
            int v2 = ((BigInteger)data.get(offset + offsetVertex + sizeElement)).intValue();
            int v3 = ((BigInteger)data.get(offset + offsetVertex + 2 * sizeElement)).intValue();
            int tx1 = ((BigInteger)data.get(offset + offsetTexCoord)).intValue();
            int tx2 = ((BigInteger)data.get(offset + offsetTexCoord + sizeElement)).intValue();
            int tx3 = ((BigInteger)data.get(offset + offsetTexCoord + 2 * sizeElement)).intValue();
            v1 = checkVertex(v1, tx1, verticesCoordMaps, xMesh, va, uva);
            v2 = checkVertex(v2, tx2, verticesCoordMaps, xMesh, va, uva);
            v3 = checkVertex(v3, tx3, verticesCoordMaps, xMesh, va, uva);
            xMesh.addFace(new Face(v1, v2, v3));
            offset += 3 * sizeElement;
        }

        return true;
    }

    private int checkVertex(int vertexId, int texCoordId, Map verticesCoordMaps, XMesh xMesh, VertexAccessor va, UVAccessor uva)
    {
        List vertexInstances = (List)verticesCoordMaps.get(Integer.valueOf(vertexId));
        if(vertexInstances == null)
        {
            vertexInstances = new ArrayList();
            verticesCoordMaps.put(Integer.valueOf(vertexId), vertexInstances);
            Point3D vertex = va.makePointFromSource(vertexId);
            vertexId = xMesh.addVertex(vertex);
            Point2D vertexUV = uva.makePointFromSource(texCoordId);
            xMesh.addTextureCoords(vertexUV);
            vertexInstances.add(new Pair(Integer.valueOf(texCoordId), Integer.valueOf(vertexId)));
        } else
        {
            Pair good = null;
            Iterator i$ = vertexInstances.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Pair pi = (Pair)i$.next();
                if(((Integer)pi.get1()).intValue() != texCoordId)
                    continue;
                good = pi;
                break;
            } while(true);
            if(good != null)
            {
                vertexId = ((Integer)good.get2()).intValue();
            } else
            {
                Point3D vertex = va.makePointFromSource(vertexId);
                vertexId = xMesh.addVertex(vertex);
                Point2D vertexUV = uva.makePointFromSource(texCoordId);
                xMesh.addTextureCoords(vertexUV);
                vertexInstances.add(new Pair(Integer.valueOf(texCoordId), Integer.valueOf(vertexId)));
            }
        }
        return vertexId;
    }

    private boolean buildNormals(XMesh mesh, Geometry geometry)
    {
        for(Iterator i$ = geometry.getMesh().getLinesOrLinestripsOrPolygons().iterator(); i$.hasNext();)
        {
            Object o = i$.next();
            if(o instanceof Triangles)
            {
                Triangles tr = (Triangles)o;
                if(!buildXNormalsFrom(mesh, geometry, tr))
                    return false;
            }
        }

        for(Iterator i$ = geometry.getMesh().getLinesOrLinestripsOrPolygons().iterator(); i$.hasNext();)
        {
            Object o = i$.next();
            if(o instanceof Triangles)
            {
                Triangles tr = (Triangles)o;
                if(!buildXNormalsFacesFrom(mesh, geometry, tr))
                    return false;
            }
        }

        return true;
    }

    private boolean buildXNormalsFrom(XMesh xMesh, Geometry geometry, Triangles tr)
    {
        String srcUrl = null;
        Iterator i$ = tr.getInput().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            InputLocalOffset ilo = (InputLocalOffset)i$.next();
            if(ilo.getSemantic().equals("NORMAL"))
                srcUrl = ilo.getSource();
        } while(true);
        if(srcUrl == null)
        {
            warn((new StringBuilder()).append("No normals for triangle '").append(tr.getName()).append("'").toString());
            return false;
        }
        Source src = lookupSource(srcUrl);
        if(src == null)
        {
            warn((new StringBuilder()).append("Source not found '").append(srcUrl).append("'").toString());
            return false;
        }
        int posX = 0;
        int posY = 1;
        int posZ = 2;
        String type = "float";
        int count = 0;
        int stride = 3;
        collada.Source.TechniqueCommon tc = src.getTechniqueCommon();
        if(tc == null)
            return false;
        Accessor accessor = tc.getAccessor();
        if(accessor == null)
            return false;
        count = accessor.getCount().intValue();
        stride = accessor.getStride().intValue();
        for(int i = 0; i < accessor.getParam().size(); i++)
        {
            Param p = (Param)accessor.getParam().get(i);
            if(p.getName().equalsIgnoreCase("X"))
            {
                type = p.getType();
                posX = i;
                continue;
            }
            if(p.getName().equalsIgnoreCase("Y"))
            {
                posY = i;
                continue;
            }
            if(p.getName().equalsIgnoreCase("Z"))
                posZ = i;
        }

        int offset = 0;
        for(int i = 0; i < count; i++)
        {
            Point3D pt = new Point3D();
            if(type.equalsIgnoreCase("float"))
            {
                pt.x = ((Double)src.getFloatArray().getValue().get(offset + posX)).doubleValue();
                pt.y = ((Double)src.getFloatArray().getValue().get(offset + posY)).doubleValue();
                pt.z = ((Double)src.getFloatArray().getValue().get(offset + posZ)).doubleValue();
            } else
            if(type.equalsIgnoreCase("int"))
            {
                pt.x = ((Long)src.getIntArray().getValue().get(offset + posX)).longValue();
                pt.y = ((Long)src.getIntArray().getValue().get(offset + posY)).longValue();
                pt.z = ((Long)src.getIntArray().getValue().get(offset + posZ)).longValue();
            } else
            {
                warn((new StringBuilder()).append("Bad source data type '").append(type).append("'").toString());
                return false;
            }
            xMesh.getNormals().add(pt);
            offset += stride;
        }

        return true;
    }

    private boolean buildXNormalsFacesFrom(XMesh xMesh, Geometry geometry, Triangles tr)
    {
        int count = tr.getCount().intValue();
        int sizeElement = tr.getP().size() / (3 * count);
        int offsetNormal = -1;
        Iterator i$ = tr.getInput().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            InputLocalOffset ilo = (InputLocalOffset)i$.next();
            if(ilo.getSemantic().equals("NORMAL"))
                offsetNormal = ilo.getOffset().intValue();
        } while(true);
        if(offsetNormal == -1)
        {
            warn((new StringBuilder()).append("No normals input for triangle '").append(tr.getName()).append("'").toString());
            return false;
        }
        List list = tr.getP();
        int offset = 0;
        for(int i = 0; i < count; i++)
        {
            int px = ((BigInteger)list.get(offset + offsetNormal)).intValue();
            int py = ((BigInteger)list.get(offset + sizeElement + offsetNormal)).intValue();
            int pz = ((BigInteger)list.get(offset + 2 * sizeElement + offsetNormal)).intValue();
            Face f = new Face(px, py, pz);
            xMesh.addFaceNormal(f);
            offset += 3 * sizeElement;
        }

        return true;
    }

    private boolean isMeshConvertible(Mesh m)
    {
        if(m == null)
            return false;
        for(Iterator i$ = m.getLinesOrLinestripsOrPolygons().iterator(); i$.hasNext();)
        {
            Object o = i$.next();
            if(o instanceof Triangles)
                return true;
        }

        return false;
    }

    private boolean isGeometryConvertible(Geometry geom)
    {
        if(geom == null)
            return false;
        else
            return isMeshConvertible(geom.getMesh());
    }

    private Geometry lookupGeometry(String url)
    {
        if(url.charAt(0) == '#')
            url = url.substring(1);
        try
        {
            Callable c = idr.resolve(url, collada.Geometry.class);
            return (Geometry)c.call();
        }
        catch(Exception e)
        {
            warn((new StringBuilder()).append("Cannot resolve geometry '").append(url).append("' : ").append(e.getMessage()).toString());
        }
        return null;
    }

    private Source lookupSource(String url)
    {
        if(url.charAt(0) == '#')
            url = url.substring(1);
        try
        {
            Callable c = idr.resolve(url, collada.Source.class);
            return (Source)c.call();
        }
        catch(Exception e)
        {
            warn((new StringBuilder()).append("Cannot resolve source '").append(url).append("' : ").append(e.getMessage()).toString());
        }
        return null;
    }

    private VisualScene lookupScene(String url)
    {
        if(url.charAt(0) == '#')
            url = url.substring(1);
        try
        {
            Callable c = idr.resolve(url, collada.VisualScene.class);
            return (VisualScene)c.call();
        }
        catch(Exception e)
        {
            warn((new StringBuilder()).append("Cannot resolve scene '").append(url).append("' : ").append(e.getMessage()).toString());
        }
        return null;
    }

    private Material lookupMaterial(String url)
    {
        if(url.charAt(0) == '#')
            url = url.substring(1);
        try
        {
            Callable c = idr.resolve(url, collada.Material.class);
            return (Material)c.call();
        }
        catch(Exception e)
        {
            warn((new StringBuilder()).append("Cannot resolve material '").append(url).append("' : ").append(e.getMessage()).toString());
        }
        return null;
    }

    private Effect lookupEffect(String url)
    {
        if(url.charAt(0) == '#')
            url = url.substring(1);
        try
        {
            Callable c = idr.resolve(url, collada.Effect.class);
            return (Effect)c.call();
        }
        catch(Exception e)
        {
            warn((new StringBuilder()).append("Cannot resolve effect '").append(url).append("' : ").append(e.getMessage()).toString());
        }
        return null;
    }

    private Node lookupNode(String url)
    {
        if(url.charAt(0) == '#')
            url = url.substring(1);
        try
        {
            Callable c = idr.resolve(url, collada.Node.class);
            return (Node)c.call();
        }
        catch(Exception e)
        {
            warn((new StringBuilder()).append("Cannot resolve nodde '").append(url).append("' : ").append(e.getMessage()).toString());
        }
        return null;
    }

    public static void main(String args[])
        throws Exception
    {
        if(args.length < 1)
        {
            System.err.println("Usage: Collada2X <infile.dae> [toFile.x]");
            System.exit(0);
        }
        File inFile = new File(args[0]);
        PrintStream out;
        if(args.length > 1)
            out = new PrintStream(new FileOutputStream(new File(args[1])));
        else
            out = System.out;
        long t1 = System.currentTimeMillis();
        JAXBContext jcb = JAXBContext.newInstance("collada");
        Unmarshaller um = jcb.createUnmarshaller();
        COLLADA col = (COLLADA)um.unmarshal(new File("/home/lc/tmp/collada/models/bla.dae"));
        long t2 = System.currentTimeMillis();
        System.err.println((new StringBuilder()).append("Parsed collada file in ").append((int)(t2 - t1)).append(" ms").toString());
        IDResolver idr = (IDResolver)um.getProperty(com.sun.xml.bind.IDResolver.class.getName());
        t1 = System.currentTimeMillis();
        Collada2X converter = new Collada2X(col, idr);
        XFrame rootFrame = converter.getRootFrame();
        String fName = inFile.getName();
        int ip = fName.lastIndexOf('.');
        if(ip != -1)
            fName = fName.substring(0, ip);
        XOutput xo = new XOutput(out, fName);
        xo.writeXData(rootFrame);
        if(out != System.out)
        {
            out.flush();
            out.close();
        }
        t2 = System.currentTimeMillis();
        System.err.println((new StringBuilder()).append("Wrote data in ").append(t2 - t1).append(" ms").toString());
    }

    private COLLADA colDocument;
    private IDResolver idr;
    private XFrame rootFrame;
    private PrintStream warnings;

}
