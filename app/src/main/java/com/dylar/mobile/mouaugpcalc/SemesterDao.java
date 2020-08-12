package com.dylar.mobile.mouaugpcalc;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "semesterTable".
*/
public class SemesterDao extends AbstractDao<Semester, Long> {

    public static final String TABLENAME = "semesterTable";

    /**
     * Properties of entity Semester.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Gp = new Property(1, Float.class, "gp", false, "GP");
        public final static Property Level = new Property(2, String.class, "level", false, "LEVEL");
        public final static Property GpName = new Property(3, String.class, "gpName", false, "GP_NAME");
        public final static Property Semester = new Property(4, String.class, "semester", false, "SEMESTER");
    }


    public SemesterDao(DaoConfig config) {
        super(config);
    }
    
    public SemesterDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"semesterTable\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"GP\" REAL," + // 1: gp
                "\"LEVEL\" TEXT," + // 2: level
                "\"GP_NAME\" TEXT," + // 3: gpName
                "\"SEMESTER\" TEXT);"); // 4: semester
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_semesterTable_LEVEL ON \"semesterTable\"" +
                " (\"LEVEL\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"semesterTable\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Semester entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Float gp = entity.getGp();
        if (gp != null) {
            stmt.bindDouble(2, gp);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(3, level);
        }
 
        String gpName = entity.getGpName();
        if (gpName != null) {
            stmt.bindString(4, gpName);
        }
 
        String semester = entity.getSemester();
        if (semester != null) {
            stmt.bindString(5, semester);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Semester entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Float gp = entity.getGp();
        if (gp != null) {
            stmt.bindDouble(2, gp);
        }
 
        String level = entity.getLevel();
        if (level != null) {
            stmt.bindString(3, level);
        }
 
        String gpName = entity.getGpName();
        if (gpName != null) {
            stmt.bindString(4, gpName);
        }
 
        String semester = entity.getSemester();
        if (semester != null) {
            stmt.bindString(5, semester);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Semester readEntity(Cursor cursor, int offset) {
        Semester entity = new Semester( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getFloat(offset + 1), // gp
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // level
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // gpName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // semester
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Semester entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setGp(cursor.isNull(offset + 1) ? null : cursor.getFloat(offset + 1));
        entity.setLevel(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGpName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSemester(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Semester entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Semester entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Semester entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
