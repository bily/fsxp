@echo off
for /R ./ %%a in (*.java) do if exist  "%%~pa%%~na.class" (echo ok) else javac "%%a"
