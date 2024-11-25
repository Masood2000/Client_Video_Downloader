package com.example.apiproject.data.database.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.apiproject.data.database.entity.DownloadedVideo;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DownloadedVideoDao_Impl implements DownloadedVideoDao {
  private final RoomDatabase __db;

  private final SharedSQLiteStatement __preparedStmtOfInsertDownloadedVideo;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDownload;

  private final SharedSQLiteStatement __preparedStmtOfDeleteVideo;

  public DownloadedVideoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__preparedStmtOfInsertDownloadedVideo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Insert or Ignore into DownloadedVideos (name, path,downloadUrl,image,title,audioPath) VALUES (?, ?, ?, ?, ?, ?)";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDownload = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DownloadedVideos SET name = ? , path=? WHERE path = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteVideo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Delete from DownloadedVideos where path=? ";
        return _query;
      }
    };
  }

  @Override
  public void insertDownloadedVideo(final String name, final String path,
      final String downloadedurl, final String image, final String title, final String audioPath) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfInsertDownloadedVideo.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 2;
    if (path == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, path);
    }
    _argIndex = 3;
    if (downloadedurl == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, downloadedurl);
    }
    _argIndex = 4;
    if (image == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, image);
    }
    _argIndex = 5;
    if (title == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, title);
    }
    _argIndex = 6;
    if (audioPath == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, audioPath);
    }
    __db.beginTransaction();
    try {
      _stmt.executeInsert();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfInsertDownloadedVideo.release(_stmt);
    }
  }

  @Override
  public void updateDownload(final String path, final String newName, final String newPath) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDownload.acquire();
    int _argIndex = 1;
    if (newName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newName);
    }
    _argIndex = 2;
    if (newPath == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newPath);
    }
    _argIndex = 3;
    if (path == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, path);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateDownload.release(_stmt);
    }
  }

  @Override
  public void deleteVideo(final String path) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteVideo.acquire();
    int _argIndex = 1;
    if (path == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, path);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteVideo.release(_stmt);
    }
  }

  @Override
  public Flow<List<DownloadedVideo>> getAllDownloadedVideos() {
    final String _sql = "Select * from DownloadedVideos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"DownloadedVideos"}, new Callable<List<DownloadedVideo>>() {
      @Override
      public List<DownloadedVideo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDownloadUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadUrl");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAudioPath = CursorUtil.getColumnIndexOrThrow(_cursor, "audioPath");
          final int _cursorIndexOfDownloadTime = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadTime");
          final List<DownloadedVideo> _result = new ArrayList<DownloadedVideo>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DownloadedVideo _item;
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDownloadUrl;
            if (_cursor.isNull(_cursorIndexOfDownloadUrl)) {
              _tmpDownloadUrl = null;
            } else {
              _tmpDownloadUrl = _cursor.getString(_cursorIndexOfDownloadUrl);
            }
            final String _tmpImage;
            if (_cursor.isNull(_cursorIndexOfImage)) {
              _tmpImage = null;
            } else {
              _tmpImage = _cursor.getString(_cursorIndexOfImage);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpAudioPath;
            if (_cursor.isNull(_cursorIndexOfAudioPath)) {
              _tmpAudioPath = null;
            } else {
              _tmpAudioPath = _cursor.getString(_cursorIndexOfAudioPath);
            }
            final Long _tmpDownloadTime;
            if (_cursor.isNull(_cursorIndexOfDownloadTime)) {
              _tmpDownloadTime = null;
            } else {
              _tmpDownloadTime = _cursor.getLong(_cursorIndexOfDownloadTime);
            }
            _item = new DownloadedVideo(_tmpPath,_tmpName,_tmpDownloadUrl,_tmpImage,_tmpTitle,_tmpAudioPath,_tmpDownloadTime);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int getCount(final String givenName) {
    final String _sql = "Select count(*) from DownloadedVideos where name=? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (givenName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, givenName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
