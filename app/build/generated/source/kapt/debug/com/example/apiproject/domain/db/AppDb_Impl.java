package com.example.apiproject.domain.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.apiproject.data.database.dao.DownloadedVideoDao;
import com.example.apiproject.data.database.dao.DownloadedVideoDao_Impl;
import com.example.apiproject.data.database.dao.DownloadingVideoDao;
import com.example.apiproject.data.database.dao.DownloadingVideoDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDb_Impl extends AppDb {
  private volatile DownloadingVideoDao _downloadingVideoDao;

  private volatile DownloadedVideoDao _downloadedVideoDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DownloadingVideos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `path` TEXT NOT NULL, `cookie` TEXT, `isPaused` INTEGER NOT NULL, `image` TEXT, `title` TEXT, `audio` TEXT, `audioPath` TEXT, `isAudioDownloaded` INTEGER, `status` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DownloadedVideos` (`path` TEXT NOT NULL, `name` TEXT NOT NULL, `downloadUrl` TEXT, `image` TEXT, `title` TEXT, `audioPath` TEXT, `downloadTime` INTEGER, PRIMARY KEY(`path`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ad71fa4cb4d95f42f2060f17116fae27')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DownloadingVideos`");
        _db.execSQL("DROP TABLE IF EXISTS `DownloadedVideos`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDownloadingVideos = new HashMap<String, TableInfo.Column>(12);
        _columnsDownloadingVideos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("path", new TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("cookie", new TableInfo.Column("cookie", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("isPaused", new TableInfo.Column("isPaused", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("image", new TableInfo.Column("image", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("audio", new TableInfo.Column("audio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("audioPath", new TableInfo.Column("audioPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("isAudioDownloaded", new TableInfo.Column("isAudioDownloaded", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadingVideos.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDownloadingVideos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDownloadingVideos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDownloadingVideos = new TableInfo("DownloadingVideos", _columnsDownloadingVideos, _foreignKeysDownloadingVideos, _indicesDownloadingVideos);
        final TableInfo _existingDownloadingVideos = TableInfo.read(_db, "DownloadingVideos");
        if (! _infoDownloadingVideos.equals(_existingDownloadingVideos)) {
          return new RoomOpenHelper.ValidationResult(false, "DownloadingVideos(com.example.apiproject.data.database.entity.DownloadingVideo).\n"
                  + " Expected:\n" + _infoDownloadingVideos + "\n"
                  + " Found:\n" + _existingDownloadingVideos);
        }
        final HashMap<String, TableInfo.Column> _columnsDownloadedVideos = new HashMap<String, TableInfo.Column>(7);
        _columnsDownloadedVideos.put("path", new TableInfo.Column("path", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedVideos.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedVideos.put("downloadUrl", new TableInfo.Column("downloadUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedVideos.put("image", new TableInfo.Column("image", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedVideos.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedVideos.put("audioPath", new TableInfo.Column("audioPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedVideos.put("downloadTime", new TableInfo.Column("downloadTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDownloadedVideos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDownloadedVideos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDownloadedVideos = new TableInfo("DownloadedVideos", _columnsDownloadedVideos, _foreignKeysDownloadedVideos, _indicesDownloadedVideos);
        final TableInfo _existingDownloadedVideos = TableInfo.read(_db, "DownloadedVideos");
        if (! _infoDownloadedVideos.equals(_existingDownloadedVideos)) {
          return new RoomOpenHelper.ValidationResult(false, "DownloadedVideos(com.example.apiproject.data.database.entity.DownloadedVideo).\n"
                  + " Expected:\n" + _infoDownloadedVideos + "\n"
                  + " Found:\n" + _existingDownloadedVideos);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "ad71fa4cb4d95f42f2060f17116fae27", "1c0524cee2f3ebd19bbc574ab538fd6c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "DownloadingVideos","DownloadedVideos");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DownloadingVideos`");
      _db.execSQL("DELETE FROM `DownloadedVideos`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DownloadingVideoDao.class, DownloadingVideoDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DownloadedVideoDao.class, DownloadedVideoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public DownloadingVideoDao downloadingVideoDao() {
    if (_downloadingVideoDao != null) {
      return _downloadingVideoDao;
    } else {
      synchronized(this) {
        if(_downloadingVideoDao == null) {
          _downloadingVideoDao = new DownloadingVideoDao_Impl(this);
        }
        return _downloadingVideoDao;
      }
    }
  }

  @Override
  public DownloadedVideoDao downloadedVideoDao() {
    if (_downloadedVideoDao != null) {
      return _downloadedVideoDao;
    } else {
      synchronized(this) {
        if(_downloadedVideoDao == null) {
          _downloadedVideoDao = new DownloadedVideoDao_Impl(this);
        }
        return _downloadedVideoDao;
      }
    }
  }
}
