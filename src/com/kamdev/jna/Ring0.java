package com.kamdev.jna;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;

public final class Ring0
{
  static
  {
    Native.register(NativeLibrary.getInstance("Kernel32", W32APIOptions.UNICODE_OPTIONS));
  }
  
  public static native Pointer CreateToolhelp32Snapshot(int paramInt1, int paramInt2);
  
  public static native boolean CloseHandle(Pointer paramPointer);
  
  public static native boolean Process32Next(Pointer paramPointer, Tlhelp32.PROCESSENTRY32 paramPROCESSENTRY32);
  
  public static native boolean Module32NextW(Pointer paramPointer, Tlhelp32.MODULEENTRY32W paramMODULEENTRY32W);
  
  public static native boolean ReadProcessMemory(Pointer paramPointer1, long paramLong, Pointer paramPointer2, int paramInt, IntByReference paramIntByReference);
  
  public static native boolean WriteProcessMemory(Pointer paramPointer1, long paramLong, Pointer paramPointer2, int paramInt, IntByReference paramIntByReference);
}
