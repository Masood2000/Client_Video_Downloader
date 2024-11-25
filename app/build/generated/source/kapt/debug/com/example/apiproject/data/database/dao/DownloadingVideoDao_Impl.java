package com.example.apiproject.data.database.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.apiproject.data.database.entity.DownloadingVideo;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DownloadingVideoDao_Impl implements DownloadingVideoDao {
  private final RoomDatabase __db;

  private final SharedSQLiteStatement __preparedStmtOfInsertDownloadingVideo;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDownloadingVideo;

  private final SharedSQLiteStatement __preparedStmtOfUpdateError;

  private final SharedSQLiteStatement __preparedStmtOfUpdateAudioDownloadedStatus;

  private final SharedSQLiteStatement __preparedStmtOfChangePauseState;

  public DownloadingVideoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__preparedStmtOfInsertDownloadingVideo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Insert into DownloadingVideos (name, url, path, cookie, isPaused ,title,image,audio,audioPath) VALUES (?, ?, ?, ?, ? ,?,?,?,?)";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteDownloadingVideo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM DownloadingVideos WHERE path = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateError = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DownloadingVideos SET status = ? WHERE name = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateAudioDownloadedStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DownloadingVideos SET isAudioDownloaded = true WHERE audioPath = ?";
        return _query;
      }
    };
    this.__preparedStmtOfChangePauseState = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE DownloadingVideos SET isPaused = ? WHERE name = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertDownloadingVideo(final String name, final String url, final String path,
      final String cookie, final boolean isPaused, final String image, final String title,
      final String audio, final String audioPath) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfInsertDownloadingVideo.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 2;
    if (url == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, url);
    }
    _argIndex = 3;
    if (path == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, path);
    }
    _argIndex = 4;
    if (cookie == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, cookie);
    }
    _argIndex = 5;
    final int _tmp = isPaused ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 6;
    if (title == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, title);
    }
    _argIndex = 7;
    if (image == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, image);
    }
    _argIndex = 8;
    if (audio == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, audio);
    }
    _argIndex = 9;
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
      __preparedStmtOfInsertDownloadingVideo.release(_stmt);
    }
  }

  @Override
  public void deleteDownloadingVideo(final String path) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDownloadingVideo.acquire();
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
      __preparedStmtOfDeleteDownloadingVideo.release(_stmt);
    }
  }

  @Override
  public void updateError(final String name, final String error) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateError.acquire();
    int _argIndex = 1;
    if (error == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, error);
    }
    _argIndex = 2;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateError.release(_stmt);
    }
  }

  @Override
  public void updateAudioDownloadedStatus(final String audioPath) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateAudioDownloadedStatus.acquire();
    int _argIndex = 1;
    if (audioPath == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, audioPath);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateAudioDownloadedStatus.release(_stmt);
    }
  }

  @Override
  public void changePauseState(final String name, final boolean isPaused) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfChangePauseState.acquire();
    int _argIndex = 1;
    final int _tmp = isPaused ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfChangePauseState.release(_stmt);
    }
  }

  @Override
  public boolean getAudioDownloadedStatus(final String audioPath) {
    final String _sql = "SELECT isAudioDownloaded FROM DownloadingVideos WHERE audioPath = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (audioPath == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, audioPath);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<DownloadingVideo>> getAllDownloadingVideos() {
    final String _sql = "SELECT * FROM DownloadingVideos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"DownloadingVideos"}, new Callable<List<DownloadingVideo>>() {
      @Override
      public List<DownloadingVideo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "path");
          final int _cursorIndexOfCookie = CursorUtil.getColumnIndexOrThrow(_cursor, "cookie");
          final int _cursorIndexOfIsPaused = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaused");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfAudio = CursorUtil.getColumnIndexOrThrow(_cursor, "audio");
          final int _cursorIndexOfAudioPath = CursorUtil.getColumnIndexOrThrow(_cursor, "audioPath");
          final int _cursorIndexOfIsAudioDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isAudioDownloaded");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<DownloadingVideo> _result = new ArrayList<DownloadingVideo>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DownloadingVideo _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpPath;
            if (_cursor.isNull(_cursorIndexOfPath)) {
              _tmpPath = null;
            } else {
              _tmpPath = _cursor.getString(_cursorIndexOfPath);
            }
            final String _tmpCookie;
            if (_cursor.isNull(_cursorIndexOfCookie)) {
              _tmpCookie = null;
            } else {
              _tmpCookie = _cursor.getString(_cursorIndexOfCookie);
            }
            final boolean _tmpIsPaused;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPaused);
            _tmpIsPaused = _tmp != 0;
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
            final String _tmpAudio;
            if (_cursor.isNull(_cursorIndexOfAudio)) {
              _tmpAudio = null;
            } else {
              _tmpAudio = _cursor.getString(_cursorIndexOfAudio);
            }
            final String _tmpAudioPath;
            if (_cursor.isNull(_cursorIndexOfAudioPath)) {
              _tmpAudioPath = null;
            } else {
              _tmpAudioPath = _cursor.getString(_cursorIndexOfAudioPath);
            }
            final Boolean _tmpIsAudioDownloaded;
            final Integer _tmp_1;
            if (_cursor.isNull(_cursorIndexOfIsAudioDownloaded)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsAudioDownloaded);
            }
            _tmpIsAudioDownloaded = _tmp_1 == null ? null : _tmp_1 != 0;
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new DownloadingVideo(_tmpId,_tmpName,_tmpUrl,_tmpPath,_tmpCookie,_tmpIsPaused,_tmpImage,_tmpTitle,_tmpAudio,_tmpAudioPath,_tmpIsAudioDownloaded,_tmpStatus);
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
