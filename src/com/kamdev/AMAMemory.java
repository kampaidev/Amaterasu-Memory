package com.kamdev;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import com.kamdev.dll.DLL;
import com.kamdev.jna.Kernel32;
import com.kamdev.jna.Ring0;
import com.kamdev.jna.User32;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.ptr.IntByReference;

public class AMAMemory
{
  protected int pid;
  protected Kernel32 kernel32;
  protected User32 user32;
  protected Pointer handle;
  protected static int PROCESS_VM_READ = 16;
  protected static int PROCESS_VM_WRITE = 32;
  protected static int PROCESS_VM_OPERATION = 8;
  protected List<DLL> dlls = new ArrayList<>();
  
  public AMAMemory(String process)
  {
    this.pid = byName(process);
    this.kernel32 = ((Kernel32)Native.loadLibrary("kernel32", Kernel32.class));
    this.user32 = ((User32)Native.loadLibrary("user32", User32.class));
    this.handle = openProcess(PROCESS_VM_READ | PROCESS_VM_WRITE | PROCESS_VM_OPERATION, this.pid);
    loadDLLs();
  }
  
  public AMAMemory(int pid)
  {
    this.pid = pid;
    this.kernel32 = ((Kernel32)Native.loadLibrary("kernel32", Kernel32.class));
    this.user32 = ((User32)Native.loadLibrary("user32", User32.class));
    this.handle = openProcess(PROCESS_VM_READ | PROCESS_VM_WRITE | PROCESS_VM_OPERATION, pid);
    loadDLLs();
  }
  
  public int byName(String name)
  {
    Tlhelp32.PROCESSENTRY32.ByReference entry = new Tlhelp32.PROCESSENTRY32.ByReference();
    Pointer snapshot = Ring0.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPALL.intValue(), 0);
    try
    {
      while (Ring0.Process32Next(snapshot, entry))
      {
        String processName = Native.toString(entry.szExeFile);
        if (name.equals(processName)) {
          return entry.th32ProcessID.intValue();
        }
      }
    }
    finally
    {
      Ring0.CloseHandle(snapshot);
    }
    Ring0.CloseHandle(snapshot);
    
    throw new IllegalStateException("Process '" + name + "' was not found. Are you sure its running?");
  }
  
  private Pointer openProcess(int permissions, int pid)
  {
    Pointer process = this.kernel32.OpenProcess(permissions, true, pid);
    return process;
  }
  
  public int readInt(long address)
  {
    return read(address, 4).getInt(0L);
  }
  
  public byte readByte(long address)
  {
    return read(address, 1).getByte(0L);
  }
  
  public long readLong(long address)
  {
    return read(address, 8).getLong(0L);
  }
  
  public double readDouble(long address)
  {
    return read(address, 8).getDouble(0L);
  }
  
  public float readFloat(long address)
  {
    return read(address, 4).getFloat(0L);
  }
  
  public long readUnsignedInt(long address)
  {
    return Integer.toUnsignedLong(read(address, 4).getInt(0L));
  }
  
  public boolean readBoolean(long address)
  {
    return read(address, 1).getByte(0L) != 0;
  }
  
  public boolean writeInt(long address, int value)
  {
    return write(address, ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array());
  }
  
  public boolean writeByte(long address, int value)
  {
    return write(address, ByteBuffer.allocate(1).order(ByteOrder.LITTLE_ENDIAN).put((byte)value).array());
  }
  
  public boolean writeBoolean(long address, boolean value)
  {
    return writeByte(address, (byte)(value ? 1 : 0));
  }
  
  public boolean writeLong(long address, long value)
  {
    return write(address, ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(value).array());
  }
  
  public boolean writeFloat(long address, float value)
  {
    return write(address, ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putFloat(value).array());
  }
  
  public boolean writeDouble(long address, double value)
  {
    return write(address, ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(value).array());
  }
  
  public Memory read(long address, int bytesToRead)
  {
    IntByReference read = new IntByReference(0);
    Memory output = new Memory(bytesToRead);
    
    this.kernel32.ReadProcessMemory(this.handle, address, output, bytesToRead, read);
    return output;
  }
  
  private void loadDLLs()
  {
      Pointer snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPMODULE32.intValue() | Tlhelp32.TH32CS_SNAPMODULE.intValue(), pid);
      Tlhelp32.MODULEENTRY32W entry = new Tlhelp32.MODULEENTRY32W.ByReference();
      try {
          while (kernel32.Module32NextW(snapshot, entry)) {
              String name = entry.szModule();
              dlls.add(new DLL(entry.hModule.getPointer(),name, entry.modBaseSize.intValue()));
          }
      } finally {
    	  kernel32.CloseHandle(snapshot);
      }
  }
  
  public boolean write(long address, byte[] data)
  {
    int size = data.length;
    Memory toWrite = new Memory(size);
    for (int i = 0; i < size; i++) {
      toWrite.setByte(i, data[i]);
    }
    boolean b = this.kernel32.WriteProcessMemory(pointer(), address, toWrite, size, null);
    
    return b;
  }
  
  public DLL dll(String name)
  {
    for (DLL d : this.dlls) {
      if (d.name().equals(name)) {
        return d;
      }
    }
    return null;
  }
  
  public Pointer pointer()
  {
    return this.handle;
  }
  
  public int pid()
  {
    return this.pid;
  }
  
  public boolean checkprocess()
  {
    return this.pid > 0;
  }
}