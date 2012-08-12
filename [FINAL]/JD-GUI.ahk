FileList =  ; Initialize to be blank.
path = C:\class\com\zbluesoftware\fsxp
SetWorkingDir %path%

Loop, %path%\*.class,0,1
{
    FileList = %FileList%%A_LoopFileFullPath%`n		;append to itself
}

Loop, parse, FileList, `n
{
    if A_LoopField =  ; Ignore the blank item at the end of the list.
		continue
IfInString, A_LoopField, $
	{ 
	;NOTHING
	}
Else
	{		;if not innerClass
StringReplace,output,A_LoopField,class,java,All	;replace .class with .java
StringReplace,output,output,java,src
If FileExist(output)
	{
	;NOTHING
	}
Else
{		;if file does not already exist
srcDir = %A_LoopField%
StringReplace,srcDir,srcDir,class,src		;switch to src DIR
;FullFileName = C:\My Documents\Address List.txt
; To fetch all info:
SplitPath, srcDir, name, dir, ext, name_no_ext, drive
; The above will set the variables as follows:
; name = Address List.txt
; dir = C:\My Documents
; ext = txt
; name_no_ext = Address List
; drive = C:
FileCreateDir %dir%
Run %comspec% /c C:\j.exe %A_LoopField%
IfWinExist Java Decompiler
    WinActivate ; use the window found above
WinWait, Java Decompiler, C:\class\|com/zblues
IfWinNotActive, Java Decompiler, C:\class\|com/zblues, WinActivate, Java Decompiler, C:\class\|com/zblues
WinWaitActive, Java Decompiler, C:\class\|com/zblues
WinGetActiveStats, Title, Width, Height, X, Y
X=%X%+0.5*%Width%
Y=%Y%+0.5*%Height%
MouseClick, left,  %X%, %Y%
Sleep, 100
Send, {CTRLDOWN}accccc{CTRLUP}
Run Notepad
WinWait Untitled - Notepad
IfWinNotActive, Untitled - Notepad, , WinActivate, Untitled - Notepad
WinWaitActive, Untitled - Notepad
Send, {CTRLDOWN}v{CTRLUP}
WinClose Untitled - Notepad
Send s
WinWait Save As
Send "%output%"{ENTER}
Sleep, 100
if WinExist("Confirm Save As")
{
	WinActivate
	Send y
}
WinClose Java Decompiler
;break
	}
}
}
return