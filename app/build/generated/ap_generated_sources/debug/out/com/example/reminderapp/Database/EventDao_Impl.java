package com.example.reminderapp.Database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEntityClass;

  public EventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntityClass = new EntityInsertionAdapter<EntityClass>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `myTable`(`id`,`eventname`,`eventdate`,`eventtime`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EntityClass value) {
        stmt.bindLong(1, value.id);
        if (value.getEventname() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getEventname());
        }
        if (value.getEventdate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEventdate());
        }
        if (value.getEventtime() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEventtime());
        }
      }
    };
  }

  @Override
  public void insertAll(EntityClass entityClass) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEntityClass.insert(entityClass);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<EntityClass> getAllData() {
    final String _sql = "SELECT * FROM myTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfEventname = _cursor.getColumnIndexOrThrow("eventname");
      final int _cursorIndexOfEventdate = _cursor.getColumnIndexOrThrow("eventdate");
      final int _cursorIndexOfEventtime = _cursor.getColumnIndexOrThrow("eventtime");
      final List<EntityClass> _result = new ArrayList<EntityClass>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final EntityClass _item;
        _item = new EntityClass();
        _item.id = _cursor.getInt(_cursorIndexOfId);
        final String _tmpEventname;
        _tmpEventname = _cursor.getString(_cursorIndexOfEventname);
        _item.setEventname(_tmpEventname);
        final String _tmpEventdate;
        _tmpEventdate = _cursor.getString(_cursorIndexOfEventdate);
        _item.setEventdate(_tmpEventdate);
        final String _tmpEventtime;
        _tmpEventtime = _cursor.getString(_cursorIndexOfEventtime);
        _item.setEventtime(_tmpEventtime);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
