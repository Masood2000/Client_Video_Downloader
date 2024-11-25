/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.liulishuo.filedownloader.i;
public interface IFileDownloadIPCCallback extends android.os.IInterface
{
  /** Default implementation for IFileDownloadIPCCallback. */
  public static class Default implements com.liulishuo.filedownloader.i.IFileDownloadIPCCallback
  {
    @Override public void callback(com.liulishuo.filedownloader.message.MessageSnapshot snapshot) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.liulishuo.filedownloader.i.IFileDownloadIPCCallback
  {
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.liulishuo.filedownloader.i.IFileDownloadIPCCallback interface,
     * generating a proxy if needed.
     */
    public static com.liulishuo.filedownloader.i.IFileDownloadIPCCallback asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.liulishuo.filedownloader.i.IFileDownloadIPCCallback))) {
        return ((com.liulishuo.filedownloader.i.IFileDownloadIPCCallback)iin);
      }
      return new com.liulishuo.filedownloader.i.IFileDownloadIPCCallback.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
        data.enforceInterface(descriptor);
      }
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
      }
      switch (code)
      {
        case TRANSACTION_callback:
        {
          com.liulishuo.filedownloader.message.MessageSnapshot _arg0;
          _arg0 = _Parcel.readTypedObject(data, com.liulishuo.filedownloader.message.MessageSnapshot.CREATOR);
          this.callback(_arg0);
          break;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
      return true;
    }
    private static class Proxy implements com.liulishuo.filedownloader.i.IFileDownloadIPCCallback
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public void callback(com.liulishuo.filedownloader.message.MessageSnapshot snapshot) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _Parcel.writeTypedObject(_data, snapshot, 0);
          boolean _status = mRemote.transact(Stub.TRANSACTION_callback, _data, null, android.os.IBinder.FLAG_ONEWAY);
        }
        finally {
          _data.recycle();
        }
      }
    }
    static final int TRANSACTION_callback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
  }
  public static final java.lang.String DESCRIPTOR = "com.liulishuo.filedownloader.i.IFileDownloadIPCCallback";
  public void callback(com.liulishuo.filedownloader.message.MessageSnapshot snapshot) throws android.os.RemoteException;
  /** @hide */
  static class _Parcel {
    static private <T> T readTypedObject(
        android.os.Parcel parcel,
        android.os.Parcelable.Creator<T> c) {
      if (parcel.readInt() != 0) {
          return c.createFromParcel(parcel);
      } else {
          return null;
      }
    }
    static private <T extends android.os.Parcelable> void writeTypedObject(
        android.os.Parcel parcel, T value, int parcelableFlags) {
      if (value != null) {
        parcel.writeInt(1);
        value.writeToParcel(parcel, parcelableFlags);
      } else {
        parcel.writeInt(0);
      }
    }
  }
}
