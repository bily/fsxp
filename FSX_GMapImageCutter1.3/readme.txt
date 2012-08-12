Google Map Image Cutter Version 1.3
===================================

The Google Map Image Cutter is an application designed to take any image or a digital photo and cut it into tiles
which are displayed on a Google Map. Using this tool, large images can be published on the web in a format that allows
the user to pan and zoom using the standard Google Maps interface. Although publishing large digital photos is the
most obvious application, this technique can be used for annotated maps of an area that are not to scale e.g. directions
for how to get to the office.

Instructions
============

The software is very simple to use. Use File/Open to load a jpeg, gif or tiff. The image will display in the centre of
the window and the zoom level slider on the right will show the maximum zoom depth before the displayed image on the map
is bigger than the original image. It is possible to move the slider up and down to change the zoom level created, but
going past this limit requires the image to be blown up, so the result will be a pixelated image. The number of tiles
shown below the slider shows how many files will be displayed when create is pressed. As the zoom depth is increased,
this number will increase exponentially. As a general guide, an image of width 8000 pixels only requires zoom level 6
and 1365 tiles.

To create a working Google Maps website containing your image, simply click the Create button and press Start. The
progress bar shows how far the tile creation still has to go. Once the tile creation has finished, there will be an
html file and a directory created in the same directory as the one the original image was loaded from. Double clicking
on the html file will open up the image in a web browser. The html file and the directory called 'myimagename-tiles'
can be moved anywhere on the disk, or onto a web server, as long as they both remain in the same position relative to
each other e.g. both in the same directory.


Image Quality
=============

Under the 'Processing' menu are two options to control the quality of the images produced. The 'Low Quality Images'
option simply resizes the images to fit the Google Map Tiles, while the 'High Quality Images' option uses bicubic
interpolation. For this reason, the high quality option takes longer to produce the final result than the low quality
option due to each rendered image taking longer to produce. By default the high quality setting is used.

NOTE: Java versions below 1.5 do not support bicubic interpolation, so the algorithm used in this case is bilinear
interpolation, which will still produce higher quality images than the low quality setting.


Publishing on the Web
=====================

In order to publish the image on the web it is necessary to obtain a Google Maps API key from Google and insert
it manually into the html page. The html page can be opened in a text editor and the line:

<script src="http://maps.google.com/maps?file=api&v=2&key=PUTAPIKEYHERE"

found near the top. Simply substitute PUTAPIKEYHERE for your API key. This is not necessary for pages loaded off of a
local disk.

If markers are required on the resulting Google Maps image, this can be accomplished by editing the resulting html file.
When an image is loaded into the application, the status bar at the bottom of the window contains the name of the file
loaded, its dimensions and the current position of the mouse shown as a GLatLng coordinate. By clicking on the desired
position on the map, the position is copied to the computer's clipboard for inclusion in the html file. The template
html file contains a comment of the form:

/////////////////////////////////////////////////////////////////////////////////////
//Add any markers here e.g.
//      map.addOverlay(new GMarker(new GLatLng(x,y)));
/////////////////////////////////////////////////////////////////////////////////////

to show where to how and where to add markers. Simply uncomment the line (remove the leading '//') and substitute the
coordinates shown at the bottom of the application for x,y in the line above e.g.

/////////////////////////////////////////////////////////////////////////////////////
//Add any markers here e.g.
      map.addOverlay(new GMarker(new GLatLng(19.98216,-13.338884)));
/////////////////////////////////////////////////////////////////////////////////////

Any additional markers can be added after this one.

Currently, there is no way to zoom in on the image using this application, so, for large images, there can be some error in the
position caused by pixel level inaccuracies. The lat/long calculated is correct for the pixel in the image the mouse is currently
over, but the image may be larger than can be displayed on the screen, introducing a small error.


Supported Image File Formats
============================

By default, the supported image formats are: jpeg, png, gif, bmp, tiff, pnm and raw. Depending on what is installed in the Java
virtual machine, some of these might be missing due to a lack of support for them. The application uses ImageIO and, with Java
Advanced Imaging (JAI) installed, the full set of formats should be present. On a system without JAI, the formats are more likely
to be: jpeg, png, gif and bmp. This is checked when the application loads and is displayed in the 'Files of Type:' section of the
open file dialog. If you go to File/Open and look at the 'Files of Type:' box at the bottom, this will contain only the image file
types currently suported on this system. Download and install the latest version of JAI from Sun to obtain the full set.


Installation
============

Pre-requisites
==============

The Google Map Image Cutter requires a Java Virtual Machine version 1.4.2 or upwards. For information on downloading Java, see
http://java.sun.com. You will need to download the latest Java Runtime Environment (JRE), which is version 1.6 at the current
time. Additional downloads for Java Advanced Imaging (JAI) and ImageIO may be necessary for some platforms to handle other
image formats. The following links show where these projects can be found. Go to 'binary downloads' and download the appropriate
'jre' version of the file for your platform.

https://jai.dev.java.net/
https://imageio.dev.java.net/


Installation Instructions - All Platforms
=========================================

The application is simple enough to run without any complex installation and so will work on any platform supporting
Java (see pre-requisites section). Unpack the files in the zip or gzip archive into a directory. The following files
are present:

	GMapImageCutter.jar	(java archive containing the application for all platforms)
	GMapImageCutter.bat	(script for Windows to run application with more memory)
	GMapImageCutter.sh	(script for Linux to run application with more memory)
	readme.txt		(this readme file)

Windows users should be able to double click on either the '.bat' or '.jar' files to run the application, but it is
recommended that the '.bat' file is used as it starts up the java virtual machine with additional memory to cope with large
images. Linux users can use the '.sh' file instead of the '.bat', but you will need to change the permissions of the file to
make it executable. These script files only contain one command to start the program using a Java Virtual Machine of sufficient
size, as follows:

start javaw -Xms512M -Xmx1024M -jar GMapImageCutter.jar

(Note: 'java' is used on Linux instead of 'javaw').

Mac users can alter this to do the same thing, or just run the '.jar' file.


Uninstalling - All Platforms
============================

Just delete the files.


Versions and Changes
====================

Version 1.3 - Released 31 July 2007
BUG: Fixed problem with image tiles not being created or being created with uninitialised (random) images in them. This problem affects JDK versions
below 1.6 with any type of images and PNG handling in all versions including 1.6. Added custom area clipping code to avoid using the JDK code which is
causing the problem.

Version 1.2 - Released 16 May 2007
FEATURE: Added high quality rendering of images using bicubic interpolation (Java 1.5) falling back to bilinear interpolation (<1.5).
FEATURE: Clicking on the map inserts the click position into the clipboard as a GLatLng position string.
FEATURE: Added continuous zoom, zoom by double-clicking on the image and zoom with the mouse wheel to the html template.
FEATURE: Added small insert picture into the bottom right corner of the html template page. 
BUG: Fixed inability to create more than one image without restarting the program (also affected abort and restart create).
BUG: Fixed vertical error when positioning markers on the image.

Version 1.1 - Released 1 March 2007
BUG: Fixed bug where the image isn't displayed in the window if the image height is greater than the width.


Version 1.0 - Released 22 February 2007
Initial release.


Support
=======

email: support@casa.ucl.ac.uk


Acknowledgements
================

The Google Maps Image Cutter was developed at the Centre for Advanced Spatial Analysis (CASA), which is part of
University College London (UCL).

http://www.casa.ucl.ac.uk
 