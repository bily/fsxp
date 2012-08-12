// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImagePanel.java

package imagetoolscommon;

import java.awt.*;
import javax.swing.JComponent;

public class ImagePanel extends JComponent
{

    public ImagePanel()
    {
    }

    public Rectangle getImageRectangle()
    {
        return imageRect;
    }

    public Rectangle getSquareImageRectangle()
    {
        if(imageRect == null)
            return null;
        Rectangle rect = null;
        if(imageRect.width > imageRect.height)
        {
            int size = imageRect.width;
            rect = new Rectangle(imageRect.x, imageRect.y - (imageRect.width - imageRect.height) / 2, size, size);
        } else
        {
            int size = imageRect.height;
            rect = new Rectangle(imageRect.x - (imageRect.height - imageRect.width) / 2, imageRect.y, size, size);
        }
        return rect;
    }

    public ImagePanel(Image image)
    {
        setImage(image);
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
        if(image == null)
            return;
        width = image.getWidth(this);
        if(width <= 0)
        {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(image, 0);
            try
            {
                tracker.waitForAll();
            }
            catch(InterruptedException ie) { }
            width = image.getWidth(this);
        }
        height = image.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g)
    {
        if(image != null)
        {
            float imageAspect = (float)width / (float)height;
            float screenAspect = (float)getWidth() / (float)getHeight();
            if(imageAspect > screenAspect)
            {
                int h = (int)((float)getWidth() / imageAspect);
                int offY = (getHeight() - h) / 2;
                imageRect = new Rectangle(0, offY, getWidth(), h);
                g.drawImage(image, 0, offY, getWidth(), h, this);
            } else
            {
                int w = (int)((float)getHeight() * imageAspect);
                int offX = (getWidth() - w) / 2;
                imageRect = new Rectangle(offX, 0, w, getHeight());
                g.drawImage(image, offX, 0, w, getHeight(), this);
            }
        } else
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.white);
            String text = "No Image Loaded";
            g.drawString(text, (getWidth() - fm.stringWidth(text)) / 2, getHeight() / 2);
        }
    }

    private Image image;
    private int width;
    private int height;
    private Rectangle imageRect;
}
