// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Point3D.java

package org.lc0277lib.geom;


// Referenced classes of package org.lc0277lib.geom:
//            Point2D

public class Point3D
    implements Cloneable
{

    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D()
    {
        this(0.0D, 0.0D, 0.0D);
    }

    public Point3D(Point3D op)
    {
        this(op.x, op.y, op.z);
    }

    public void setFromSpherical(double lat, double lon, double alt)
    {
        x = alt * Math.sin(lat) * Math.cos(lon);
        y = alt * Math.sin(lat) * Math.sin(lon);
        z = alt * Math.cos(lat);
    }

    public void set(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Point2D p2d)
    {
        x = p2d.x;
        y = p2d.y;
    }

    public void set(Point3D p3d)
    {
        set(p3d.x, p3d.y, p3d.z);
    }

    public double dist()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double distFrom(double dx, double dy, double dz)
    {
        return Math.sqrt((x - dx) * (x - dx) + (y - dy) * (y - dy) + (z - dz) * (z - dz));
    }

    public double distFrom(Point3D p)
    {
        return distFrom(p.x, p.y, p.z);
    }

    public double norm1()
    {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    public double norm2()
    {
        return dist();
    }

    public double normInf()
    {
        return Math.max(x, Math.max(y, z));
    }

    public void translate(double xx, double yy, double zz)
    {
        x += xx;
        y += yy;
        z += zz;
    }

    public void translate(Point3D op)
    {
        translate(op.x, op.y, op.z);
    }

    public void rotateX(double xCenter, double yCenter, double zCenter, double a)
    {
        double newx = x - xCenter;
        double newy = Math.cos(a) * (y - yCenter) - Math.sin(a) * (z - zCenter);
        double newz = Math.sin(a) * (y - yCenter) + Math.cos(a) * (z - zCenter);
        x = newx;
        y = newy;
        z = newz;
    }

    public void rotateY(double xCenter, double yCenter, double zCenter, double a)
    {
        double newx = Math.cos(a) * (x - xCenter) + Math.sin(a) * (z - zCenter);
        double newy = y - yCenter;
        double newz = -Math.sin(a) * (x - xCenter) + Math.cos(a) * (z - zCenter);
        x = newx;
        y = newy;
        z = newz;
    }

    public void rotateZ(double xCenter, double yCenter, double zCenter, double a)
    {
        double newx = Math.cos(a) * (x - xCenter) - Math.sin(a) * (y - yCenter);
        double newy = Math.sin(a) * (x - xCenter) + Math.cos(a) * (y - yCenter);
        double newz = z - zCenter;
        x = newx;
        y = newy;
        z = newz;
    }

    public void rotateX(Point3D op, double a)
    {
        rotateX(op.x, op.y, op.z, a);
    }

    public void rotateX(double a)
    {
        rotateX(0.0D, 0.0D, 0.0D, a);
    }

    public void rotateY(Point3D op, double a)
    {
        rotateY(op.x, op.y, op.z, a);
    }

    public void rotateY(double a)
    {
        rotateY(0.0D, 0.0D, 0.0D, a);
    }

    public void rotateZ(Point3D op, double a)
    {
        rotateZ(op.x, op.y, op.z, a);
    }

    public void rotateZ(double a)
    {
        rotateZ(0.0D, 0.0D, 0.0D, a);
    }

    public void scale(double sx, double sy, double sz)
    {
        x *= sx;
        y *= sy;
        z *= sz;
    }

    public void scale(Point3D op)
    {
        scale(op.x, op.y, op.z);
    }

    public void scale(double s)
    {
        scale(s, s, s);
    }

    public int hashCode()
    {
        long b1 = Double.doubleToLongBits(x);
        long b2 = Double.doubleToLongBits(y);
        long b3 = Double.doubleToLongBits(y);
        return (int)(b1 ^ b1 >>> 32 ^ b2 ^ b2 >>> 32 ^ b3 ^ b3 >>> 32);
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Point3D)
        {
            Point3D pp = (Point3D)obj;
            return pp.x == x && pp.y == y && pp.z == z;
        } else
        {
            return false;
        }
    }

    public Point3D clone() throws CloneNotSupportedException
    {
        return new Point3D(this);
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(x))).append(", ").append(y).append(", ").append(z).toString();
    }
/*
    public Object clone()
    {
        return clone();
    }
*/
    public double x;
    public double y;
    public double z;
}
