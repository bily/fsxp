//----------------------------------------------------------------------------//
// AnimatedAd.java                                                            //
// ---------------                                                            //
// This example is from Developer's Daily (http://www.DevDaily.com).          //
// Copyright (c) 1998 DevDaily Interactive, Inc.                              //
// This example is provided WITHOUT ANY WARRANTY either expressed or implied. //
// You may study, use, modify, and distribute it for non-commercial purposes  //
// as long as this header is retained in the file.                            //
// For any commercial use, contact our editor (editor@DevDaily.com).          //
//----------------------------------------------------------------------------//
// Version 1.0(a)                                                             //
//----------------------------------------------------------------------------//

import java.awt.*;
import java.applet.*;
import java.util.*;
import java.lang.Math;
import java.net.URL;
import java.net.MalformedURLException;

public class AnimatedAd extends Applet implements Runnable {

    Thread runner;

    //  some constants, primarily for array sizing
    
    static final int MAX_NUM_STRINGS     = 10;      //  max number of text strings
    static final int MAX_NUM_COLORS      = 10;      //  max number of colors
    static final int MAX_NUM_FONTS       = 5;       //  "Helvetica", "Courier", "TimesRoman"
    static final int MAX_NUM_FONT_STYLES = 4;       //  0=plain, 1=bold, 2=italic, 3=bold+italic
    static final int MAX_NUM_FONT_SIZES  = 5;       //  12, 14, 16, ...

    //  arrays that are used when displaying the text strings 
    
    Color[] color     = new Color[MAX_NUM_COLORS];      //  possible string colors
    String[] fontName = new String[MAX_NUM_FONTS];      //  possible string fonts
    int[] fontStyle   = new int[MAX_NUM_FONT_STYLES];   //  possible string font styles
    int[] fontSize    = new int[MAX_NUM_FONT_SIZES];    //  possible string font sizes
	
	    
    //  info needed for text strings

    String[] adString = new String[10];
    String currentString;
    int    numStringsSupplied;           // the number of strings passed in from the HTML code


    //  info needed for the "final" text string, i.e., "@ Developer's Daily"

    String finalStringToDisplay;         //  the text string
    int    finalStringX;                 //  the x-loc
    int    finalStringY;                 //  the y-loc
    String finalStringFont;              //  the font
    int    finalStringFontSize;          //  the font size
    int    finalStringFontStyle;         //  the font style
    Color  finalStringFontColor;         //  the string color
    
    int    stringPauseTime;              //  time to pause after a string is displayed
    int    finalStringPauseTime;         //  time to pause after the final string is displayed
    int    stringLoopCount;              //  num times to display random strings before the final
	
    int    minX;                         //  the minimum x-coord where a string can be displayed
    int    maxX;                         //  the maximum x-coord where a string can be displayed
    int    minY;                         //  the minimum y-coord where a string can be displayed
    int    maxY;                         //  the maximum y-coord where a string can be displayed
    

    //  other parameters that are passed in from the HTML code
    
    String urlString;
    Color  bgColor;


    //  miscellaneous parameters used in the code
    
    int    currentX;
    int    currentY;
    Color  currentColor;
    Font   currentFont;
    
    //-------------------------------------------//
    //  info needed for random number generator  //
    //-------------------------------------------//
    
    int seed = 1;                         // random number seed
    static final int seedM = 233280;      // seedM, seedA, and seedC are constants
    static final int seedA = 9301;        //    (originally chosen from the book
    static final int seedC = 49297;       //    "Numerical Recipes in C")
    int lastRandomValue = 0;              // use this so I don't get identical numbers
    

    

    //-----------------------------------------( init )---------------//
	public void init() {
	    super.init();

	    //{{INIT_CONTROLS
	    setLayout(null);
	    addNotify();
	    resize(468,60);
	    //}}

	    urlString = GetAppletParameter.toString(this, "TARGET_URL",  "/");

            //  the background color for the applet

	    bgColor = GetAppletParameter.toColor(this,  "BG_COLOR",   Color.white);

            //  up to 10 different "ad" strings allowed; if less than 10 are given, the
            //  numStringsUsed() method will count only those that are set

	    adString[0]  = GetAppletParameter.toString(this, "STRING1",  "");
	    adString[1]  = GetAppletParameter.toString(this, "STRING2",  "");
	    adString[2]  = GetAppletParameter.toString(this, "STRING3",  "");
	    adString[3]  = GetAppletParameter.toString(this, "STRING4",  "");
	    adString[4]  = GetAppletParameter.toString(this, "STRING5",  "");
	    adString[5]  = GetAppletParameter.toString(this, "STRING6",  "");
	    adString[6]  = GetAppletParameter.toString(this, "STRING7",  "");
	    adString[7]  = GetAppletParameter.toString(this, "STRING8",  "");
	    adString[8]  = GetAppletParameter.toString(this, "STRING9",  "");
	    adString[9]  = GetAppletParameter.toString(this, "STRING10",  "");
	    numStringsSupplied = numStringsUsed(adString);
		
            //  min/max coordinates where strings can (potentially) appear
            //  (random number generator determines the locations within these bounds

	    minX         = GetAppletParameter.toInt(this, "MIN_X", 10);
	    maxX         = GetAppletParameter.toInt(this, "MAX_X", 400);
	    minY         = GetAppletParameter.toInt(this, "MIN_Y", 20);
	    maxY         = GetAppletParameter.toInt(this, "MAX_Y", 55);

            //  get the list of possible fonts for the "regular" text strings (not the "final" string)
        
            String defaultFont = GetAppletParameter.toString(this, "FONT_DEFAULT", "Helvetica");
            fontName[0] = GetAppletParameter.toString(this, "FONT1", defaultFont);
            fontName[1] = GetAppletParameter.toString(this, "FONT2", defaultFont);
            fontName[2] = GetAppletParameter.toString(this, "FONT3", defaultFont);
            fontName[3] = GetAppletParameter.toString(this, "FONT4", defaultFont);
            fontName[4] = GetAppletParameter.toString(this, "FONT5", defaultFont);

	    int defaultFontStyle = GetAppletParameter.toInt(this, "FONT_DEFAULT_STYLE", 0);
	    fontStyle[0] = GetAppletParameter.toInt(this, "FONT_STYLE1", defaultFontStyle);
	    fontStyle[1] = GetAppletParameter.toInt(this, "FONT_STYLE2", defaultFontStyle);
	    fontStyle[2] = GetAppletParameter.toInt(this, "FONT_STYLE3", defaultFontStyle);
	    fontStyle[3] = GetAppletParameter.toInt(this, "FONT_STYLE4", defaultFontStyle);

	    int defaultFontSize = GetAppletParameter.toInt(this, "FONT_DEFAULT_SIZE", 16);
	    fontSize[0] = GetAppletParameter.toInt(this, "FONT_SIZE1", defaultFontSize);
	    fontSize[1] = GetAppletParameter.toInt(this, "FONT_SIZE2", defaultFontSize);
	    fontSize[2] = GetAppletParameter.toInt(this, "FONT_SIZE3", defaultFontSize);
	    fontSize[3] = GetAppletParameter.toInt(this, "FONT_SIZE4", defaultFontSize);
	    fontSize[4] = GetAppletParameter.toInt(this, "FONT_SIZE5", defaultFontSize);

             //  get the list of possible string colors
        
            Color defaultColor = GetAppletParameter.toColor(this, "COLOR_DEFAULT", Color.blue);
	    color[0] = GetAppletParameter.toColor(this, "COLOR1",  defaultColor);
	    color[1] = GetAppletParameter.toColor(this, "COLOR2",  defaultColor);
	    color[2] = GetAppletParameter.toColor(this, "COLOR3",  defaultColor);
	    color[3] = GetAppletParameter.toColor(this, "COLOR4",  defaultColor);
	    color[4] = GetAppletParameter.toColor(this, "COLOR5",  defaultColor);
	    color[5] = GetAppletParameter.toColor(this, "COLOR6",  defaultColor);
	    color[6] = GetAppletParameter.toColor(this, "COLOR7",  defaultColor);
	    color[7] = GetAppletParameter.toColor(this, "COLOR8",  defaultColor);
	    color[8] = GetAppletParameter.toColor(this, "COLOR9",  defaultColor);
	    color[9] = GetAppletParameter.toColor(this, "COLOR10", defaultColor);
		
	    //  info needed to display the final string
		
            finalStringToDisplay = GetAppletParameter.toString(this, "FINAL_STRING",      "");
	    finalStringX         = GetAppletParameter.toInt(this,    "FINAL_STRING_X",    130);
	    finalStringY         = GetAppletParameter.toInt(this,    "FINAL_STRING_Y",    35);
            finalStringFont      = GetAppletParameter.toString(this, "FINAL_STRING_FONT", "Helvetica");
	    finalStringFontSize  = GetAppletParameter.toInt(this,    "FINAL_STRING_FONT_SIZE", 20);
	    finalStringFontStyle = GetAppletParameter.toInt(this,    "FINAL_STRING_FONT_STYLE", 1);
	    finalStringFontColor = GetAppletParameter.toColor(this,  "FINAL_STRING_FONT_COLOR", Color.blue);
        
            //  the time to pause after strings are displayed

	    stringPauseTime      = GetAppletParameter.toInt(this,    "STRING_PAUSE_TIME",         80);
	    finalStringPauseTime = GetAppletParameter.toInt(this,    "FINAL_STRING_PAUSE_TIME", 2500);
	    
            //  the number of strings that should be displayed between occurrences of
            //  the final string

	    stringLoopCount      = GetAppletParameter.toInt(this,    "STRING_LOOP_COUNT",         15);
        
	    setBackground(bgColor);

	}  // end of init()
	

    //-----------------------------------------( start )--------------//
	public void start () {
	    if (runner == null) {
	        runner = new Thread(this);
	        runner.start();
	    }
	}


    //------------------------------------------( stop )--------------//
	public void stop () {
	    if (runner != null) {
	        runner.stop();
	        runner = null;
	    }
	}
	
	
    //-------------------------------------------( run )--------------//
	public void run () {

        while (true) {
            for (int i=0; i<stringLoopCount; i++) {

                currentX = getRandomInt(minX,maxX);                 // random x-coord
                currentY = getRandomInt(minY,maxY);                 // random y-coord
                int rc   = getRandomInt(0,MAX_NUM_COLORS-1);        // random color
                int rf   = getRandomInt(0,MAX_NUM_FONTS-1);         // random font
                int rfst = getRandomInt(0,MAX_NUM_FONT_STYLES-1);   // random font style
                int rfsi = getRandomInt(0,MAX_NUM_FONT_SIZES-1);    // random font size

                int as   = getRandomInt(0,numStringsSupplied-1);
                currentString = adString[as];
                    
                currentColor  = color[rc];
                
                //  set the currentFont

                currentFont = new Font(fontName[rf],
                                       fontStyle[rfst],
                                       fontSize[rfsi]);
                                       
                // paint the text
                repaint();
	        pause(stringPauseTime);

                // paint the text the bgColor (make it disappear)
                currentColor = bgColor;
                repaint();
	        pause(stringPauseTime);

	    }  // end of 'i' loop

            // now display the last/final string (presumably a company name or
            // something similar)
            currentString = finalStringToDisplay;
            currentX      = finalStringX;
            currentY      = finalStringY;
            currentColor  = finalStringFontColor;
            currentFont   = new Font(finalStringFont,
                                     finalStringFontStyle,
                                     finalStringFontSize);
            repaint();
	    pause(finalStringPauseTime);

            currentColor = bgColor;
            repaint();
  	    
  	}  // end of 'while true'

    }
	

    //-----------------------------------------( handleEvent )--------//
	//public boolean handleEvent(Event event) {
	//	return super.handleEvent(event);
	//}
	

    //-----------------------------------------( mouseDown )----------//
    public boolean mouseDown (Event ev, int x, int y) {
        try {
            URL theURL = new URL(urlString);
            getAppletContext().showDocument(theURL);
        } catch (MalformedURLException mue) { }
        
        return true;
    }
    

    //-----------------------------------------( pause )--------------//
	public void pause (int sleepTime) {
	    try { Thread.sleep(sleepTime); }
	    catch (InterruptedException e) { }
	}
	
	
    //-----------------------------------------( drawString )--------------//
	public void drawMyString (String s, int x, int y, Graphics g, Font f, Color c) {
	    g.setFont(f);
	    g.setColor(c);
	    g.drawString(s,x,y);
	}


    //-----------------------------------------( paint )--------------//
	public void paint(Graphics g) {

	    drawMyString(currentString,
	                 currentX,
	                 currentY,
	                 g,
	                 currentFont,
	                 currentColor);

	}  // end of paint() method

	//{{DECLARE_CONTROLS
	//}}
	
	

    //------------------------------( numStringsUsed )-------------------//
    //  determine how many strings were passed in from the HTML code     //
    //-------------------------------------------------------------------//

    public int numStringsUsed (String[] stringArray) {

        // return once we find a blank string
        for (int i=0; i<stringArray.length; i++) {
            if (stringArray[i].equals("")) return i+1;
        }
        
        // if no blank strings were found, numStringsUsed is the length of
        // the array (i.e., there is some type of text in every element)
        return stringArray.length;
    }
    
    
    //------------------------------( getRandomInt )-------------------//
    //                                                                 //
    //  getRandomInt returns a random integer based on the supplied    //
    //  minValue and maxValue.                                         //
    //                                                                 //
    //-----------------------------------------------------------------//

    public int getRandomInt (int minValue, int maxValue) {

       int rv = minValue-1;

       while (rv < minValue  || rv == lastRandomValue) {
          int time = (int) System.currentTimeMillis();
          seed = (seed * seedA + seedC + time) % seedM;
          Random r = new java.util.Random(seed);
          rv = Math.round(maxValue * r.nextFloat());
       }

       lastRandomValue = rv;
       return rv;
    }

}
