// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMesh.java

package collada2x;

import java.util.*;
import org.lc0277lib.geom.Point2D;
import org.lc0277lib.geom.Point3D;

// Referenced classes of package collada2x:
//            XMaterial, Face, XOutput

public class XMesh
{

    public XMesh(String name)
    {
        this.name = "";
        vertices = new ArrayList();
        faces = new ArrayList();
        materials = new ArrayList();
        facesMaterials = new ArrayList();
        normals = new ArrayList();
        facesNormals = new ArrayList();
        textureCoords = new ArrayList();
        this.name = name;
    }

    public XMesh()
    {
        this((new StringBuilder()).append("mesh-").append(count++).toString());
    }

    public void writeXOutput(XOutput xo)
    {
        xo.beginSection("Mesh", name);
        writeVertexList(xo, vertices);
        writeFaceList(xo, faces);
        xo.beginSection("MeshMaterialList");
        xo.printData(materials.size());
        xo.printData(facesMaterials.size());
        Integer f;
        for(Iterator itFM = facesMaterials.iterator(); itFM.hasNext(); xo.printLine((new StringBuilder()).append(f.intValue()).append(itFM.hasNext() ? "," : ";").toString()))
            f = (Integer)itFM.next();

        xo.println();
        XMaterial mat;
        for(Iterator i$ = materials.iterator(); i$.hasNext(); mat.writeXOutput(xo))
            mat = (XMaterial)i$.next();

        xo.endSection();
        xo.beginSection("MeshNormals");
        writeVertexList(xo, normals);
        writeFaceList(xo, facesNormals);
        xo.endSection();
        xo.beginSection("MeshTextureCoords");
        writeUVList(xo, textureCoords);
        xo.endSection();
        xo.endSection();
    }

    private void writeVertexList(XOutput xo, List vertexList)
    {
        xo.printData(vertexList.size());
        Point3D p;
        for(Iterator itP = vertexList.iterator(); itP.hasNext(); xo.printLine((new StringBuilder()).append(p.x).append("; ").append(p.y).append("; ").append(p.z).append(";").append(itP.hasNext() ? "," : ";").toString()))
            p = (Point3D)itP.next();

        xo.println();
    }

    private void writeFaceList(XOutput xo, List facesList)
    {
        xo.printData(facesList.size());
        Face f;
        for(Iterator itF = facesList.iterator(); itF.hasNext(); xo.printLine((new StringBuilder()).append("3; ").append(f.getP3()).append(", ").append(f.getP2()).append(", ").append(f.getP1()).append(";").append(itF.hasNext() ? "," : ";").toString()))
            f = (Face)itF.next();

        xo.println();
    }

    private void writeUVList(XOutput xo, List uvList)
    {
        xo.printData(uvList.size());
        Point2D p;
        for(Iterator itP = uvList.iterator(); itP.hasNext(); xo.printLine((new StringBuilder()).append(p.x).append("; ").append(p.y).append("; ").append(itP.hasNext() ? "," : ";").toString()))
            p = (Point2D)itP.next();

        xo.println();
    }

    public ArrayList getFaces()
    {
        return faces;
    }

    public void setFaces(ArrayList faces)
    {
        this.faces = faces;
    }

    public void addFace(Face f)
    {
        faces.add(f);
    }

    public List getFacesMaterials()
    {
        return facesMaterials;
    }

    public void setFacesMaterials(ArrayList facesMaterials)
    {
        this.facesMaterials = facesMaterials;
    }

    public void addFaceMaterial(int matId)
    {
        facesMaterials.add(Integer.valueOf(matId));
    }

    public ArrayList getFacesNormals()
    {
        return facesNormals;
    }

    public void setFacesNormals(ArrayList facesNormals)
    {
        this.facesNormals = facesNormals;
    }

    public void addFaceNormal(Face f)
    {
        facesNormals.add(f);
    }

    public List getMaterials()
    {
        return materials;
    }

    public void setMaterials(ArrayList materials)
    {
        this.materials = materials;
    }

    public int addMaterial(XMaterial m)
    {
        materials.add(m);
        return materials.size() - 1;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList getNormals()
    {
        return normals;
    }

    public void setNormals(ArrayList normals)
    {
        this.normals = normals;
    }

    public void addNormal(Point3D p)
    {
        normals.add(p);
    }

    public ArrayList getTextureCoords()
    {
        return textureCoords;
    }

    public void setTextureCoords(ArrayList textureCoords)
    {
        this.textureCoords = textureCoords;
    }

    public void addTextureCoords(Point2D p)
    {
        textureCoords.add(p);
    }

    public ArrayList getVertices()
    {
        return vertices;
    }

    public void setVertices(ArrayList vertices)
    {
        this.vertices = vertices;
    }

    public int addVertex(Point3D p)
    {
        vertices.add(p);
        return vertices.size() - 1;
    }

    private static int count = 0;
    private String name;
    private ArrayList vertices;
    private ArrayList faces;
    private ArrayList materials;
    private ArrayList facesMaterials;
    private ArrayList normals;
    private ArrayList facesNormals;
    private ArrayList textureCoords;

}
