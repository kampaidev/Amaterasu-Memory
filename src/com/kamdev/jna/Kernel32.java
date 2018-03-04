package com.kamdev.jna;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public abstract interface Kernel32
  extends StdCallLibrary
{
  public abstract boolean WriteProcessMemory(Pointer paramPointer1, long paramLong, Pointer paramPointer2, int paramInt, IntByReference paramIntByReference);
  
  public abstract boolean ReadProcessMemory(Pointer paramPointer1, long paramLong, Pointer paramPointer2, int paramInt, IntByReference paramIntByReference);
  
  public abstract Pointer OpenProcess(int paramInt1, boolean paramBoolean, int paramInt2);
  
  public abstract Pointer CreateToolhelp32Snapshot(int flags, int pid);

  public abstract boolean CloseHandle(Pointer pointer);

  public abstract boolean Process32Next(Pointer pointer, Tlhelp32.PROCESSENTRY32 entry);

  public abstract boolean Module32NextW(Pointer pointer, Tlhelp32.MODULEENTRY32W entry);
	
  public abstract int GetLastError();
}
