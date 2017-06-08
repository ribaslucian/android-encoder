package com.example.lucian.sqlite.encoder.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lucian.sqlite.EncoderApp;
import com.example.lucian.sqlite.encoder.Utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Criado por Lucian Rossoni Ribas <ribas.lucian@gmail.com> em 08/06/2017.
 * Classe de persistência de dados SQlite.
 * <p>
 * Documentação em: encoder.persistence.Persistence
 */
public class SQLite extends Persistence {

    /**
     * Instancia do driver do SQLite.
     *
     * @type private driver SQLiteOpenHelper
     */
    public SQLiteOpenHelper driver;

    /**
     * Todo construtor de persistência deverá possui seu apelido informado.
     *
     * @param alias String
     */
    public SQLite(String alias) {
        super(alias);

        if (driver == null) {
            driver = new Driver(alias);
        }
    }

    /**
     * Criamos uma classe privada para armazenar o
     * driver do OLD_SQLite e passar as propriedades específicas.
     */
    protected class Driver extends SQLiteOpenHelper {

        public String alias;

        public Driver(String alias) {
            super(EncoderApp.context, (String) Persistence.param(alias, "name"), null, (int) Persistence.param(alias, "version"));
            this.alias = alias;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Utils.log("Persistence '" + alias + "' Create in version " + Persistence.param(alias, "version") + ":\n");
            for (String sql: (ArrayList<String>) Persistence.param(alias, "sqlToCreate")) {
                Utils.log("\t" + sql + "\n");
                db.execSQL(sql);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Utils.log("Persistence '" + alias + "' Upgrade in " + oldVersion + "->" + newVersion  + ":\n");
            for (String sql: (ArrayList<String>) Persistence.param(alias, "sqlToUpgrade")) {
                Utils.log("\t" + sql + "\n");
                db.execSQL(sql);
            }
            onCreate(db);
        }
    }

    @Override
    public boolean exists() {
        return EncoderApp.context.getDatabasePath((String) Persistence.param(alias, "name")).exists();
    }

    @Override
    public boolean drop() {
        if (exists())
            return EncoderApp.context.deleteDatabase((String) Persistence.param(alias, "name"));

        return true;
    }

    @Override
    public void clear(String table) {
        SQLiteDatabase adapter = driver.getWritableDatabase();
        adapter.execSQL("DROP TABLE IF EXISTS " + table + ";");
        adapter.close();
    }

    @Override
    public long insert(String table, HashMap<String, String> columnValues) {
        long r =  driver.getWritableDatabase().insert(table, null, Utils.mapToContentValues(columnValues));
        driver.close();
        return r;
    }

    @Override
    public ArrayList<HashMap<String, String>> find(String table, HashMap<String, Object> options) {
        SQLiteDatabase db = driver.getReadableDatabase();
        Cursor c = db.query(
                table,
                (String[]) options.get("columns"),
                (String) options.get("where"),
                (String[]) options.get("params"),
                null, null, null);

        return Utils.cursorToAssociatedList(c);
    }

    @Override
    public int update(String table, HashMap<String, Object> options) {
        SQLiteDatabase db = driver.getWritableDatabase();
        ContentValues values = Utils.mapToContentValues((HashMap<String, String>) options.get("values"));
        int r = db.update(table,
                values,
                (String) options.get("where"),
                (String[]) options.get("params"));

        db.close();
        return r;
    }

    @Override
    public int delete(String table, HashMap<String, Object> options) {
        return 0;
    }

}
