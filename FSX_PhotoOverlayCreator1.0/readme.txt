PhotoOverlay Creator Version 1.0
================================

The PhotoOverlay Creator takes an image file (gif, jpeg, png or tiff) and builds a KML file for Google Earth containing
the image as a PhotoOverlay. An image pyramid is created from the original file, allowing large images to be displayed
on the web by breaking them up into smaller tiles which are only downloaded as required. The maximum zoom level of the
image is calculated so that it matches the resolution of the original image file. Each successive zoom level then has
half the pixel resolution of the last until the image fits within a single 256 pixel square tile. Depending on how the
image was captured, it can be mapped on to a rectangle, cylinder or sphere in Google Earth. This allows spherical and
cylindrical panoramas to be displayed, while regular photographs can use the rectangular mapping option. The field of
view angles for Google Earth are automatically calculated based on the image size and the projection type, so the
default create options will produce a working PhotoOverlay KML file.


Instructions
============

Use File/Open to load a jpeg, gif, png or tiff. The image will display in the centre of the window and the panel on the
right will show the required zoom level depth of the image pyramid based on the image dimensions. When the create button is
pressed, a KML file and image pyramid tiles will be created. Before the creation though, there is an additional dialogue
box that allows various photo overlay parameters to be altered. By default, the application calculates sensible values for
the initial settings so it should be possible to click on the 'OK' button and immediately create a working KML file.

On the 'Photo Overlay Data' dialogue box, the description and the position on the Earth where the photo is placed can be
entered. The description goes into the associated tag inside the photooverlay tag in the kml file and provides the description
that appears beneath the name of the layer in Google Earth. The latitude and longitude boxes allow a position to be entered for
the photo. This can be copied in from the status line of Google Earth where it shows the lat/long position of the mouse, but the
format must be set to decimal degrees (choose 'Options' from the 'Tools' menu and set 'Decimal Degrees' under 'Show Lat/Long' in
Google Earth). Alternatively, the 'Lookup' button displays another dialogue window containing a list of pre-defined
positions for various places on the Earth. Most capital cities are defined, so choose the closest and create the photo
overlay. It is then possible to move the image in Google Earth to the correct position and re-save the KML file.

The image shape, altitude and the field of view, accessed from the 'Set FOV...' button control how the image is displayed
in Google Earth. The image shape can be 'rectangle', 'cylinder' or 'panoramic sphere', which controls the shape of the
photo overlay. Most images will be mapped on to a flat rectangle, or panoramas mapped onto a cylinder. If the image has
been created so that it can be mapped onto the inside of a sphere which the viewer stands inside to view, then choose the
spherical option. Changing the image shape also changes the altitude and the field of view angles. The altitude is
calculated so that the bottom of the image should sit on top of the terrain. The altitude is in metres and is relative to
the height of the local terrain. The altitude is calculated from the top and bottom field of view angles, the near distance
or radius and the image aspect ratio. In the field of view dialogue, the four angles are the angles from the viewpoint and
the four edges of the image. The Google Earth documentation provides more information on the field of view information in
their photo overlay instructions. The 'near' value is the distance in metres from the viewer's eye to the image, which is
the radius in the case of a cylinder or sphere. The software defaults to a near value of 40m. These values come from the
Google Earth documentation and seem to work well. 

The labelling of the tiles for photo overlays follows the naming scheme in the Google Documentation e.g. filename_zoom_x_y.jpg,
where zoom is the zoom level, x is the number of tiles across and y is the number of tiles down. The images are recursively
divided in half at each zoom level exactly as in the Google Earth documentation.

NOTE: When photo overlays are created, the image is assumed to be square for the subdivision process, meaning that
not all the tiles will intersect the image. Due to how the photo overlays have been programmed in Google Earth, these
tiles are not necessary and will not be created by the application. When the creation process has finished, the number
of used tiles containing bits of the image, together with the count of the blank tiles that were skipped will be
displayed. These two numbers add up to the original tile count displayed before the creation process was started.


Publishing on the Web
=====================

For a photo overlay, the KML file and the associated tile directory are required. These should be placed on the web server in the
same position relative to each other. It is possible to zip the kml file and the tile directory and rename the zip file to a kmz
file. However, it is probably better to have the tile images as separate files as a kmz file has to be completely downloaded by
Google Earth before it will display. By having the image pyramid as separate links on the web, only those required for display
will be downloaded, rather than everything at once.


Supported Image File Formats
============================

By default, the supported image formats are: jpeg, png, gif, bmp, tiff, pnm and raw. Depending on what is installed in the Java
virtual machine, some of these might be missing due to a lack of support for them. The application uses ImageIO and, with Java
Advanced Imaging (JAI) installed, the full set of formats should be present. On a system without JAI, the formats are more likely
to be: jpeg, png, gif and bmp. This is checked when the application loads and is displayed in the 'Files of Type:' section of the
open file dialog. If you go to File/Open and look at the 'Files of Type:' box at the bottom, this will contain only the image file
types currently suported on this system. Download and install the latest version of JAI from Sun to obtain the full set.


Batch Processing
================

To process multiple files automatically, select the 'Batch Process' option from the 'File' menu. The KML data including the description,
position and field of view can be set from the option on the 'Processing' menu. This must be selected before the 'Batch Process' menu is
clicked on. The depth of zoom and field of view settings are calculated for each image file as it is processed, but the image shape (e.g.
rectangle, cylinder or sphere), position and description can be set for all the images beforehand. In the batch processing dialogue, files
are added to the list using the 'Add Files' button. In the file selection dialogue, more than one file can be selected by using the <SHIFT>
and <CTRL> keys. Once the creation is started using the 'Start' button, progress is shown in the panel at the bottom of the screen. The
file list empties as the files are processed and the current filename and number remaining are shown in the progress panel, along with a
blue progress bar showing the progress of the current input file. A log file is created in the directory containing the first image file,
with a filename containing the time of creation. This can be viewed with the 'View Log' button on the batch process dialog once processing
has finished. Each resulting kml file and tiles directory will be in the same directory as the image file it was created from. The kml files
can then be edited directly in a text editor, or by using Google Earth as required.

Installation
============

Pre-requisites
==============

The PhotoOverlay Creator requires a Java Virtual Machine version 1.4.2 or upwards. For information on downloading Java, see
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

	PhotoOverlayCreator.jar	(java archive containing the application for all platforms)
	PhotoOverlayCreator.bat	(script for Windows to run application with more memory)
	readme.txt		(this readme file)

Windows users should be able to double click on either the '.bat' or '.jar' files to run the application, but it is
recommended that the '.bat' file is used as it starts up the java virtual machine with additional memory to cope with large
images. This script file only contains one command to start the program using a Java Virtual Machine of sufficient size, as
follows:

start javaw -Xms512M -Xmx1024M -jar PhotoOverlayCreator.jar

(Note: 'java' is used on Linux instead of 'javaw').

Mac and Linux users can alter this to do the same thing, or just run the '.jar' file.


Uninstalling - All Platforms
============================

Just delete the files.


Versions and Changes
====================

Version 1.0 - Released 25 October 2007
Initial release.


Support
=======

email: support@casa.ucl.ac.uk


Acknowledgements
================

The PhotoOverlayCreator was developed at the Centre for Advanced Spatial Analysis (CASA), which is part of
University College London (UCL).

http://www.casa.ucl.ac.uk

