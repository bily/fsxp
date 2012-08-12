**********************************************************
*  AI Carriers for Flight Simulator X SP1 & Acceleration
*
*  Lamont Clark - lc0277@nerim.net
*  second release, 3 november 2007
*
**********************************************************


This is a small software that allows you to place and control single ships or complete naval fleets in Flight Simulator X. 
Unlike previous software concerning AI ships and carriers, or missions, it doesn't need complex edit of traffic files or mission files. Just add when you are in free flight, anywhere you want.

Requirements:
- Java JRE 1.5 or upper (get free at http://java.sun.com)
- FSX SP1 (RTM version is not supported)

The addon should work with Acceleration (XPack addon). Inserted carriers are cables and catapult-enabled. The menu display keyboard shortcut was changed to shift+j to avoid conflicts with acceleration keys.

This software has not been tested on Windows Vista. It may requires manual tweaking of exe.xml. See the included sample_EXE.xml file


**** Features ****
- insert fleet at current user position or X nm ahead
- control fleet movement
- request distance and bearing of ships related to user aircraft (fixed magnetic variation)
- included hard decks cruiser and destroyer models (useless with Acceleration xpack since they are already included in it)
- use catapult & arrestor cables of carriers (FSX Acceleration only)
- Insert custom ship models (by editing config files)
- Multiple CVN textures



**** Package contents ****

- Setup.exe : installation program. Run it

- src.zip : if you are curious

- manual_install.zip : files for manual installation. Normally not to be used, the installer is doing everything for you.


**** Installation components ****

- Main program :
	used to interface with Flight Simulator through simconnect (No separate installation needed as this program does not use the Microsoft implementation of the simconnect library). 
	This component is also available to manual installation in the manual_install.zip archive
	
- Hard deck ship models :
	Hard pad versions of the standard cruiser and destroyer models included with FSX. Note that this option is useless and thus disabled if you have the Acceleration XPack since it already contains hardened models.

- CVN Texture variations : 
	Five texture variations included ID marking on the Island. Not so realistic.

- FSX Acceleration ships config :
	Sample config file that allows you to insert the USCG cutter included in the acceleration XPack. Note that the Expansion pack is NEEDED, only configuration for it is included.


**** Usage ****
After installation, a menu item should appear in under "Add-ons" in FS titlebar. If it doesn't appear, please
contact the author.

The main control panel for inserting an AI fleet or controlling it can be displayed by this menu item. Alternatively, 
a keyboard shortcut (shift + j by default) can be used too.

First select a fleet formation to use. Some of them contains only one ship, other may be large (take care if you are low on fps). Then select the location of insertion, either the current user plane location, or a specified FS waypoint. 
Note that waypoints are not available if you did not displayed the FS map first. 
After the fleet has been created, the menu allows multiple movements. Try to avoid sharp turns as FSX can be very rough with ships.


Manual exe.xml installation: if the installer cannot edit the exe.xml for you (this can be reported with Vista configurations), it's necessary
to add the AICarriers entry to this file manually. 


**** Customatisation ****
The config file aicarriers.cfg is located in the installation dir. Some items can be configured in it, just
follow the syntax. 
You can also add independant formation files in the conf.d directory. If you modeled a landable ship for FSX, you can
provide a config file to be put in this directory.


**** License ****

This software and its source code are in the public domain.
Permission to use, copy, modify and distribute this program for any purpose is hereby granted, without any conditions or restrictions.
This software is provided "as is" without express or implied warranty.


**** TODO ****
	
* REL 2
	textures carriers classes nimitz

* REL 3
	keep formation
	read maximum speed and apply diff throttle
	progressive throttle
	smoother turns
	small pitch angle when frigates are turning
	multiple formations
	save paths
	

	




