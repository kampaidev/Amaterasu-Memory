package com.kamdev.dll;
import com.sun.jna.Pointer;

public class DLL
{
  protected long address;
  protected String name;
  protected Pointer handle;
  
  public DLL(Pointer handle, String name, long address)
  {
    this.address = Pointer.nativeValue(handle);
    this.name = name;
    this.handle = handle;
  }
  
  public Pointer handle()
  {
    return this.handle;
  }
  
  public long address()
  {
    return this.address;
  }
  
  public String name()
  {
    return this.name;
  }
}
