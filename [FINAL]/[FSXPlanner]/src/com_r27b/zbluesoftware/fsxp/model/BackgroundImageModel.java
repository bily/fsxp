// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BackgroundImageModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel

public class BackgroundImageModel extends BaseModel
    implements PropertyChangeListener
{

    public BackgroundImageModel(BufferedImage image, String path, LatLonPoint topLeft, LatLonPoint bottomRight)
    {
        modelName = "Background Image";
        this.image = image;
        this.path = path;
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        recreateBGImage = true;
        recreateBGAllImage = true;
        imageVisible = true;
        displaySize = new Dimension();
        bgImageOpacity = SettingsEngine.getInstance().getBGImageOpacity();
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(!imageVisible)
            return;
        if(recreateBGImage)
        {
            java.awt.geom.Point2D.Float topLeftPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), topLeft.getLat(), topLeft.getLon(), scale);
            java.awt.geom.Point2D.Float bottomRightPoint = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), bottomRight.getLat(), bottomRight.getLon(), scale);
            int imageWidth = (int)(bottomRightPoint.x - topLeftPoint.x);
            int imageHeight = (int)(bottomRightPoint.y - topLeftPoint.y);
            if(imageWidth > displaySize.width || imageHeight > displaySize.height)
            {
                float imageScaleX = (float)imageWidth / (float)image.getWidth();
                float imageScaleY = (float)imageHeight / (float)image.getHeight();
                Rectangle2D intersect = g2.getClip().getBounds().createIntersection(new java.awt.geom.Rectangle2D.Float(topLeftPoint.x, topLeftPoint.y, bottomRightPoint.x - topLeftPoint.x, bottomRightPoint.y - topLeftPoint.y));
                int clipX = (int)(intersect.getX() - (double)topLeftPoint.x);
                int clipY = (int)(intersect.getY() - (double)topLeftPoint.y);
                int clipWidth = (int)intersect.getWidth();
                int clipHeight = (int)intersect.getHeight();
                int imagePosX = (int)(topLeftPoint.x + (float)clipX);
                int imagePosY = (int)(topLeftPoint.y + (float)clipY);
                int scaleWidth = (int)intersect.getWidth();
                int scaleHeight = (int)intersect.getHeight();
                clipX = (int)((float)clipX / imageScaleX);
                clipY = (int)((float)clipY / imageScaleY);
                clipWidth = (int)((float)clipWidth / imageScaleX);
                clipHeight = (int)((float)clipHeight / imageScaleY);
                if(clipWidth <= 0 || clipHeight <= 0 || !g2.getClip().intersects(imagePosX, imagePosY, scaleWidth, scaleHeight))
                {
                    displayImage = null;
                    displayPoint = null;
                } else
                {
                    displayImage = image.getSubimage(clipX, clipY, clipWidth, clipHeight).getScaledInstance(scaleWidth, scaleHeight, 2);
                    displayPoint = new Point(imagePosX, imagePosY);
                }
            } else
            if(recreateBGAllImage)
            {
                displayImage = image.getScaledInstance(imageWidth, imageHeight, 2);
                displayPoint = new Point((int)topLeftPoint.x, (int)topLeftPoint.y);
            }
        }
        if(displayImage != null && g2.getClip().intersects(displayPoint.x, displayPoint.y, displayImage.getWidth(null), displayImage.getHeight(null)))
            if(bgImageOpacity == 100)
            {
                g2.drawImage(displayImage, displayPoint.x, displayPoint.y, null);
            } else
            {
                java.awt.Composite composite = g2.getComposite();
                g2.setComposite(AlphaComposite.getInstance(3, (float)bgImageOpacity / 100F));
                g2.drawImage(displayImage, displayPoint.x, displayPoint.y, null);
                g2.setComposite(composite);
            }
    }

    public boolean isImageVisible()
    {
        return imageVisible;
    }

    public void setImageVisible(boolean imageVisible)
    {
        this.imageVisible = imageVisible;
    }

    public void setRecreateBGImage(boolean recreateBGImage)
    {
        this.recreateBGImage = recreateBGImage;
    }

    public void setRecreateBGAllImage(boolean recreateBGAllImage)
    {
        this.recreateBGAllImage = recreateBGAllImage;
    }

    public void setDisplaySize(Dimension displaySize)
    {
        this.displaySize = displaySize;
    }

    public BufferedImage getImage()
    {
        return image;
    }

    public void setImage(BufferedImage image)
    {
        this.image = image;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public LatLonPoint getTopLeft()
    {
        return topLeft;
    }

    public void setTopLeft(LatLonPoint topLeft)
    {
        this.topLeft = topLeft;
    }

    public LatLonPoint getBottomRight()
    {
        return bottomRight;
    }

    public void setBottomRight(LatLonPoint bottomRight)
    {
        this.bottomRight = bottomRight;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if((event.getSource() instanceof SettingsEngine) && event.getPropertyName().equals("bgImageOpacity"))
            bgImageOpacity = ((Integer)event.getNewValue()).intValue();
    }

    private BufferedImage image;
    private String path;
    private LatLonPoint topLeft;
    private LatLonPoint bottomRight;
    private Dimension displaySize;
    private Image displayImage;
    private Point displayPoint;
    private int bgImageOpacity;
    private boolean recreateBGImage;
    private boolean recreateBGAllImage;
    private boolean imageVisible;
}
