package com.kamdev.jna;

import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

public abstract interface User32
  extends W32APIOptions
{
  public static final User32 INSTANCE = (User32)Native.loadLibrary("user32", User32.class, DEFAULT_OPTIONS);
  public static final int FLASHW_STOP = 0;
  public static final int FLASHW_CAPTION = 1;
  public static final int FLASHW_TRAY = 2;
  public static final int FLASHW_ALL = 3;
  public static final int FLASHW_TIMER = 4;
  public static final int FLASHW_TIMERNOFG = 12;
  public static final int IMAGE_BITMAP = 0;
  public static final int IMAGE_ICON = 1;
  public static final int IMAGE_CURSOR = 2;
  public static final int IMAGE_ENHMETAFILE = 3;
  public static final int LR_DEFAULTCOLOR = 0;
  public static final int LR_MONOCHROME = 1;
  public static final int LR_COLOR = 2;
  public static final int LR_COPYRETURNORG = 4;
  public static final int LR_COPYDELETEORG = 8;
  public static final int LR_LOADFROMFILE = 16;
  public static final int LR_LOADTRANSPARENT = 32;
  public static final int LR_DEFAULTSIZE = 64;
  public static final int LR_VGACOLOR = 128;
  public static final int LR_LOADMAP3DCOLORS = 4096;
  public static final int LR_CREATEDIBSECTION = 8192;
  public static final int LR_COPYFROMRESOURCE = 16384;
  public static final int LR_SHARED = 32768;
  public static final int GWL_EXSTYLE = -20;
  public static final int GWL_STYLE = -16;
  public static final int GWL_WNDPROC = -4;
  public static final int GWL_HINSTANCE = -6;
  public static final int GWL_ID = -12;
  public static final int GWL_USERDATA = -21;
  public static final int DWL_DLGPROC = 4;
  public static final int DWL_MSGRESULT = 0;
  public static final int DWL_USER = 8;
  public static final int WS_EX_COMPOSITED = 536870912;
  public static final int WS_EX_LAYERED = 524288;
  public static final int WS_EX_TRANSPARENT = 32;
  public static final int LWA_COLORKEY = 1;
  public static final int LWA_ALPHA = 2;
  public static final int ULW_COLORKEY = 1;
  public static final int ULW_ALPHA = 2;
  public static final int ULW_OPAQUE = 4;
  public static final int AC_SRC_OVER = 0;
  public static final int AC_SRC_ALPHA = 1;
  public static final int AC_SRC_NO_PREMULT_ALPHA = 1;
  public static final int AC_SRC_NO_ALPHA = 2;
  public static final int VK_SHIFT = 16;
  public static final int VK_LSHIFT = 160;
  public static final int VK_RSHIFT = 161;
  public static final int VK_CONTROL = 17;
  public static final int VK_LCONTROL = 162;
  public static final int VK_RCONTROL = 163;
  public static final int VK_MENU = 18;
  public static final int VK_LMENU = 164;
  public static final int VK_RMENU = 165;
  
  public abstract Pointer GetDC(Pointer paramPointer);
  
  public abstract int ReleaseDC(Pointer paramPointer1, Pointer paramPointer2);
  
  public abstract Pointer FindWindowA(String paramString1, String paramString2);
  
  public abstract int GetClassName(Pointer paramPointer, byte[] paramArrayOfByte, int paramInt);
  
  public abstract boolean GetGUIThreadInfo(int paramInt, GUITHREADINFO paramGUITHREADINFO);
  
  public abstract boolean GetWindowInfo(Pointer paramPointer, WINDOWINFO paramWINDOWINFO);
  
  public abstract boolean GetWindowRect(Pointer paramPointer, WinDef.RECT paramRECT);
  
  public abstract int GetWindowText(Pointer paramPointer, byte[] paramArrayOfByte, int paramInt);
  
  public abstract int GetWindowTextLength(Pointer paramPointer);
  
  public abstract int GetWindowModuleFileName(Pointer paramPointer, byte[] paramArrayOfByte, int paramInt);
  
  public abstract int GetWindowThreadProcessId(Pointer paramPointer, IntByReference paramIntByReference);
  
  public abstract boolean EnumWindows(WNDENUMPROC paramWNDENUMPROC, Pointer paramPointer);
  
  public abstract boolean EnumThreadWindows(int paramInt, WNDENUMPROC paramWNDENUMPROC, Pointer paramPointer);
  
  public abstract boolean FlashWindowEx(FLASHWINFO paramFLASHWINFO);
  
  public abstract Pointer LoadIcon(Pointer paramPointer, String paramString);
  
  public abstract Pointer LoadImage(Pointer paramPointer, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract boolean DestroyIcon(Pointer paramPointer);
  
  public abstract int GetWindowLong(Pointer paramPointer, int paramInt);
  
  public abstract int SetWindowLong(Pointer paramPointer, int paramInt1, int paramInt2);
  
  public abstract boolean SetLayeredWindowAttributes(Pointer paramPointer, int paramInt1, byte paramByte, int paramInt2);
  
  public abstract boolean GetLayeredWindowAttributes(Pointer paramPointer, IntByReference paramIntByReference1, ByteByReference paramByteByReference, IntByReference paramIntByReference2);
  
  public abstract boolean UpdateLayeredWindow(Pointer paramPointer1, Pointer paramPointer2, POINT paramPOINT1, SIZE paramSIZE, Pointer paramPointer3, POINT paramPOINT2, int paramInt1, BLENDFUNCTION paramBLENDFUNCTION, int paramInt2);
  
  public abstract int SetWindowRgn(Pointer paramPointer1, Pointer paramPointer2, boolean paramBoolean);
  
  public abstract boolean GetKeyboardState(byte[] paramArrayOfByte);
  
  public abstract short GetAsyncKeyState(int paramInt);
  
  public static class FLASHWINFO
    extends Structure
  {
    public int cbSize;
    public Pointer hWnd;
    public int dwFlags;
    public int uCount;
    public int dwTimeout;
    
    protected List<String> getFieldOrder()
    {
      return null;
    }
  }
  
  public static class GUITHREADINFO
    extends Structure
  {
    public int cbSize = size();
    public int flags;
    Pointer hwndActive;
    Pointer hwndFocus;
    Pointer hwndCapture;
    Pointer hwndMenuOwner;
    Pointer hwndMoveSize;
    Pointer hwndCaret;
    WinDef.RECT rcCaret;
    
    protected List<String> getFieldOrder()
    {
      return null;
    }
  }
  
  public static class WINDOWINFO
    extends Structure
  {
    public int cbSize = size();
    public WinDef.RECT rcWindow;
    public WinDef.RECT rcClient;
    public int dwStyle;
    public int dwExStyle;
    public int dwWindowStatus;
    public int cxWindowBorders;
    public int cyWindowBorders;
    public short atomWindowType;
    public short wCreatorVersion;
    
    protected List<String> getFieldOrder()
    {
      return null;
    }
  }
  
  public static class POINT
    extends Structure
  {
    public int x;
    public int y;
    
    protected List<String> getFieldOrder()
    {
      return null;
    }
  }
  
  public static class SIZE
    extends Structure
  {
    public int cx;
    public int cy;
    
    protected List<String> getFieldOrder()
    {
      return null;
    }
  }
  
  public static class BLENDFUNCTION
    extends Structure
  {
    public byte BlendOp = 0;
    public byte BlendFlags = 0;
    public byte SourceConstantAlpha;
    public byte AlphaFormat;
    
    protected List<String> getFieldOrder()
    {
      return null;
    }
  }
  
  public static abstract interface WNDENUMPROC
    extends StdCallLibrary.StdCallCallback
  {
    public abstract boolean callback(Pointer paramPointer1, Pointer paramPointer2);
  }
}