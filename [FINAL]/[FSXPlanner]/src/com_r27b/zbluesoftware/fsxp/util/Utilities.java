// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utilities.java

package com.zbluesoftware.fsxp.util;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

// Referenced classes of package com.zbluesoftware.fsxp.util:
//            LatLonPoint

public class Utilities
{

    private Utilities()
    {
    }

    public static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight, int fill, int anchor, 
            Insets insets, int ipadx, int ipady, double weightx, double weighty)
    {
        java.awt.LayoutManager lm = container.getLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.fill = fill;
        gbc.anchor = anchor;
        gbc.insets = insets;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        ((GridBagLayout)lm).setConstraints(component, gbc);
        container.add(component);
    }

    public static Rectangle fullScreen()
    {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Rectangle rectangle = graphicsConfiguration.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(graphicsConfiguration);
        rectangle.x = insets.left;
        rectangle.y = insets.top;
        rectangle.height -= insets.top + insets.bottom;
        rectangle.width -= insets.left + insets.right;
        return rectangle;
    }

    public static java.awt.geom.Point2D.Float getPixelsBetweenPoints(double startLat, double startLon, double endLat, double endLon, 
            float scale)
    {
        double startY = 20925.5249D * Math.toRadians(startLat);
        double endY = 20925.5249D * Math.toRadians(endLat);
        double differenceY = (startY - endY) * 1000D;
        double startX = 20925.5249D * Math.cos(Math.toRadians(startLat)) * Math.toRadians(startLon);
        double endX = 20925.5249D * Math.cos(Math.toRadians(startLat)) * Math.toRadians(endLon);
        double differenceX = (endX - startX) * 1000D;
        return new java.awt.geom.Point2D.Float((float)differenceX * scale, (float)(differenceY * (double)scale));
    }

    public static LatLonPoint getLatLonForPixel(double startLat, double startLon, double differenceX, double differenceY, 
            float scale)
    {
        double startY = 20925.5249D * Math.toRadians(startLat);
        double startX = 20925.5249D * Math.cos(Math.toRadians(startLat)) * Math.toRadians(startLon);
        differenceX /= scale;
        differenceY /= scale;
        double endX = differenceX / 1000D + startX;
        double endY = (differenceY / 1000D - startY) * -1D;
        double endLat = Math.toDegrees(endY / 20925.5249D);
        double endLon = Math.toDegrees(endX / (20925.5249D * Math.cos(Math.toRadians(startLat))));
        return new LatLonPoint(endLat, endLon);
    }

    public static double getDistanceBetweenLatLons(double lat1, double lon1, double lat2, double lon2)
    {
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        double a = Math.sin(Math.toRadians(dLat) / 2D) * Math.sin(Math.toRadians(dLat) / 2D) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(Math.toRadians(dLon) / 2D) * Math.sin(Math.toRadians(dLon) / 2D);
        return 12742D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a));
    }

    public static java.awt.geom.Point2D.Float rotatePoint(java.awt.geom.Point2D.Float centerPoint, java.awt.geom.Point2D.Float point, float angle)
    {
        double radius = Math.sqrt(Math.pow(point.x - centerPoint.x, 2D) + Math.pow(point.y - centerPoint.y, 2D));
        double angleB1 = Math.toDegrees(Math.acos((double)(point.x - centerPoint.x) / radius));
        double x;
        double y;
        if(point.x <= centerPoint.x && point.y <= centerPoint.y)
        {
            x = (double)centerPoint.x + radius * Math.cos(Math.toRadians(angleB1 - (double)angle));
            y = (double)centerPoint.y - radius * Math.sin(Math.toRadians(angleB1 - (double)angle));
        } else
        if(point.x > centerPoint.x && point.y <= centerPoint.y)
        {
            x = (double)centerPoint.x + radius * Math.cos(Math.toRadians(angleB1 - (double)angle));
            y = (double)centerPoint.y - radius * Math.sin(Math.toRadians(angleB1 - (double)angle));
        } else
        if(point.x > centerPoint.x && point.y > centerPoint.y)
        {
            x = (double)centerPoint.x + radius * Math.cos(Math.toRadians(angleB1 + (double)angle));
            y = (double)centerPoint.y + radius * Math.sin(Math.toRadians(angleB1 + (double)angle));
        } else
        {
            x = (double)centerPoint.x + radius * Math.cos(Math.toRadians(angleB1 + (double)angle));
            y = (double)centerPoint.y + radius * Math.sin(Math.toRadians(angleB1 + (double)angle));
        }
        return new java.awt.geom.Point2D.Float((float)x, (float)y);
    }

    public static void setCodeDescComboBox(JComboBox comboBox, String code, String blankCode)
    {
        if(code.length() == 0 || code.equals(blankCode))
        {
            comboBox.setSelectedIndex(0);
            return;
        }
        DefaultComboBoxModel model = (DefaultComboBoxModel)comboBox.getModel();
        for(int i = model.getSize() - 1; i >= 0; i--)
            if(((String)((HashMap)model.getElementAt(i)).get("index")).equals(code))
            {
                comboBox.setSelectedIndex(i);
                return;
            }

        if(comboBox.getItemCount() > 0)
            comboBox.setSelectedIndex(0);
    }

    public static void openURL(String urlToOpen)
    {
        try
        {
            String cmd = (new StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(urlToOpen).toString();
            Process p = Runtime.getRuntime().exec(cmd);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public static final Font TITLED_BORDER_FONT = new Font("Arial", 0, 10);
    public static final Font COMBO_BOX_FONT = new Font("Arial", 0, 10);
    public static final Font BUTTON_FONT = new Font("Arial", 0, 10);
    public static final Font DIALOG_BUTTON_FONT = new Font("dialog", 1, 12);
    public static final Font BOLD_LABEL_FONT = new Font("Arial", 1, 11);
    public static final Font LABEL_FONT = new Font("Arial", 0, 10);
    public static final Font DIALOG_LABEL_FONT = new Font("Arial", 1, 12);
    public static final Font TEXT_FIELD_FONT = new Font("Courier New", 0, 12);
    public static final Font CHECK_BOX_FONT = new Font("Arial", 0, 10);
    public static final Font LARGE_LABEL_FONT = new Font("Arial", 1, 16);
    public static Font MENU_FONT = new Font("Tahoma", 0, 11);
    public static Font LAT_LON_FONT = new Font("Tahoma", 0, 10);
    public static Font PARKING_FONT = new Font("Arial", 0, 10);
    public static Font JETWAY_FONT = new Font("Arial", 0, 10);
    public static Font ILS_FONT = new Font("Arial", 0, 20);
    public static Font SCENERY_FONT = new Font("Arial", 0, 10);
    public static Font VOR_FONT = new Font("Arial", 0, 15);
    public static Font NDB_FONT = new Font("Arial", 0, 15);
    public static final Color LIGHT_BG_COLOR = new Color(238, 238, 238);
    public static final Color DESIGN_EDIT_BG_COLOR = new Color(255, 255, 153);
    public static final Color DATA_HIGHLIGHT_COLOR = new Color(51, 0, 204);
    public static final Color HALO_COLOR = new Color(255, 255, 255, 96);
    public static final int FILE_NEW = 1;
    public static final int FILE_FIND_AIRPORT = 2;
    public static final int FILE_OPEN = 3;
    public static final int FILE_OPEN_BGL = 4;
    public static final int FILE_OPEN_RECENT = 5;
    public static final int FILE_SAVE = 6;
    public static final int FILE_SAVE_AS = 7;
    public static final int FILE_CLOSE = 8;
    public static final int FILE_JPG = 9;
    public static final int FILE_PRINT = 10;
    public static final int FILE_NEW_SCENERY = 11;
    public static final int FILE_OPEN_SCENERY = 12;
    public static final int FILE_SAVE_SCENERY = 13;
    public static final int FILE_SAVE_SCENERY_AS = 14;
    public static final int FILE_PREFS = 15;
    public static final int FILE_UPDATES = 16;
    public static final int FILE_EXIT = 17;
    public static final int EDIT_UNDO = 18;
    public static final int EDIT_REDO = 19;
    public static final int EDIT_HISTORY = 20;
    public static final int EDIT_CUT = 21;
    public static final int EDIT_COPY = 22;
    public static final int EDIT_PASTE = 23;
    public static final int EDIT_CENTER = 24;
    public static final int EDIT_FIND_POINT = 25;
    public static final int EDIT_MANAGE_BG_IMAGE = 26;
    public static final int EDIT_OBJECT_LOCKING = 27;
    public static final int EDIT_DISPLAY_COUNTS = 28;
    public static final int EDIT_TAXI_NAMES = 29;
    public static final int EDIT_SERVICES = 30;
    public static final int EDIT_MOVE_AIRPORT = 31;
    public static final int DISPLAY_RUNWAY = 32;
    public static final int DISPLAY_ILS = 33;
    public static final int DISPLAY_APRON = 34;
    public static final int DISPLAY_APRON_EL = 35;
    public static final int DISPLAY_TW_POINT = 36;
    public static final int DISPLAY_TW_SIGN = 37;
    public static final int DISPLAY_PARKING = 38;
    public static final int DISPLAY_JETWAYS = 39;
    public static final int DISPLAY_TAXI_RUNWAY = 40;
    public static final int DISPLAY_TAXI_PARKING = 41;
    public static final int DISPLAY_TAXI_TAXI = 42;
    public static final int DISPLAY_TAXI_PATH = 43;
    public static final int DISPLAY_TAXI_CLOSED = 44;
    public static final int DISPLAY_TAXI_VEHICLE = 45;
    public static final int DISPLAY_TOWER = 46;
    public static final int DISPLAY_BOUND_FENCE = 47;
    public static final int DISPLAY_BLAST_FENCE = 48;
    public static final int DISPLAY_HELIPAD = 49;
    public static final int DISPLAY_START = 50;
    public static final int DISPLAY_LAT_LON = 51;
    public static final int DISPLAY_EXCLUDES = 52;
    public static final int DISPLAY_AIRPORT_CENTER = 53;
    public static final int DISPLAY_TEST_RADIUS = 54;
    public static final int DISPLAY_BG_IMAGE = 55;
    public static final int DISPLAY_MARKERS = 56;
    public static final int DISPLAY_NDBS = 57;
    public static final int DISPLAY_VORS = 58;
    public static final int DISPLAY_WINDSOCKS = 59;
    public static final int DISPLAY_TRIGGERS = 60;
    public static final int DISPLAY_SCENERY = 61;
    public static final int COMPILE_PREPARE = 62;
    public static final int COMPILE_ERROR_CHECKING = 63;
    public static final int COMPILE_CLEAN_APRONS = 64;
    public static final int COMPILE_CLEAN_FENCES = 65;
    public static final int COMPILE_CLEAN_TAXIWAYS = 66;
    public static final int COMPILE_CLEAN_TAXIPOINTS = 67;
    public static final int COMPILE_CLEAN_ALL = 68;
    public static final int COMPILE_COMPILE = 69;
    public static final int NAVIGATION_COMS = 70;
    public static final int NAVIGATION_APPROACHES = 71;
    public static final int NAVIGATION_LIGHTS = 72;
    public static final int SC_CONNECT = 73;
    public static final int SC_CURSOR_MOVE = 74;
    public static final int SC_FSX_PLANE_MOVE = 75;
    public static final int SC_CURSOR_PLANE = 76;
    public static final int SC_CURSOR_SHOW = 77;
    public static final int VIEW_AIRPORT_DATA = 78;
    public static final int VIEW_OBJECT_DATA = 79;
    public static final int VIEW_NAV_TOOLBAR = 80;
    public static final int HELP_KARMA = 81;
    public static final int HELP_BASICS = 82;
    public static final int HELP_BGL = 83;
    public static final int HELP_COMPILE = 84;
    public static final int HELP_SIM_CONNECT = 85;
    public static final int HELP_APRONS = 86;
    public static final int HELP_TAXIWAYS = 87;
    public static final int HELP_SIGNS = 88;
    public static final int HELP_REFUEL = 89;
    public static final int HELP_IMAGES = 90;
    public static final int HELP_INDEX = 91;
    public static final int INSERT_SELECT = 92;
    public static final int INSERT_SCROLL = 93;
    public static final int INSERT_ZOOM_IN = 94;
    public static final int INSERT_ZOOM_OUT = 95;
    public static final int INSERT_PARKING = 96;
    public static final int INSERT_START_POINT = 97;
    public static final int INSERT_TOWER = 98;
    public static final int INSERT_TAXI_POINT = 99;
    public static final int INSERT_ILS_TAXI_POINT = 100;
    public static final int INSERT_HS_TAXI_POINT = 101;
    public static final int INSERT_TAXIWAY_SIGN = 102;
    public static final int INSERT_APRON_EDGE_LIGHT = 103;
    public static final int INSERT_HELIPAD = 104;
    public static final int INSERT_TAXIWAY = 105;
    public static final int INSERT_APRON = 106;
    public static final int INSERT_RUNWAY = 107;
    public static final int INSERT_FENCE = 108;
    public static final int INSERT_BLAST_FENCE = 109;
    public static final int INSERT_JETWAY = 110;
    public static final int INSERT_ILS = 111;
    public static final int INSERT_MARKER = 112;
    public static final int INSERT_VOR = 113;
    public static final int INSERT_NDB = 114;
    public static final int INSERT_WINDSOCK = 115;
    public static final int INSERT_EXCLUDE = 116;
    public static final int INSERT_TRIGGER = 117;
    public static final int INSERT_SCENERY = 118;
    public static final int MISC_HISTORY_DETAILS = 119;
    public static final int MISC_HISTORY_UNDO = 120;
    public static final int MISC_BREAK_FENCE = 121;
    public static final int MISC_ADD_PRIMARY_ILS = 122;
    public static final int MISC_ADD_SECONDARY_ILS = 123;
    public static final int MISC_ADD_TRIGGER = 124;
    public static final int MISC_ADD_STATION = 125;
    public static final int MISC_ADD_LOCALIZER = 126;
    public static final int MISC_ADD_VOR_STATION = 127;
    public static final int MISC_ADD_NDB_ANTENNA = 128;
    public static final double earthRadiusKM = 6371D;
    public static final double earthRadius = 20925.5249D;
    public static final String APP_NAME = "FSX Planner [R 27b]";
    public static Frame MAIN_FRAME = null;
    public static final float FEET_IN_METERS = 3.28F;
    public static final float METERS_IN_NAUTICAL_MILE = 1852F;
    public static final BasicStroke DEFAULT_STROKE = new BasicStroke();

}
