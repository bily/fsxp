@echo on



set CP=.;.\jdec121.jar

rem %CP%
 
java -classpath %CP% net.sf.jdec.ui.main.UILauncher

goto end



:end
set CP=
