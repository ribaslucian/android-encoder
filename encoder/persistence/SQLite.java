package com.example.lucian.sqlite.encoder.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lucian.sqlite.EncoderApp;
import com.example.lucian.sqlite.encoder.Utils;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Criado por Lucian Rossoni Ribas <ribas.lucian@gmail.com> em 08/06/2017.
 * Classe de persistência de dados SQlite.
 *
 * Documentação em: encoder.persistence.Persistence
 */
public class SQLite extends Persistence {

    /**
     * Instancia do driver do SQLite.
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

        driver = new Driver(
            EncoderApp.context,
            (String) Persistence.param(alias, "name"),
            Integer.parseInt((String) Persistence.param(alias, "version")),
            (String) Persistence.param(alias, "SQLToCreate"),
            (String) Persistence.param(alias, "SQLToUpgrade")
        );

        Utils.log("ok");
    }

    /**
     * Criamos uma classe privada para armazenar o
     * driver do OLD_SQLite e passar as propriedades específicas.
     */
    private class Driver extends SQLiteOpenHelper {

        public String sqlToCreate;
        public String sqlToUpgrade;

        public Driver(Context context, String databaseName, int version, String sqlToCreate, String sqlToUpgrade) {
            super(context, databaseName, null, version);
            this.sqlToCreate = sqlToCreate;
            this.sqlToUpgrade = sqlToUpgrade;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sqlToCreate);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Utils.log("Upgrading Database from:" + oldVersion + " to: " + newVersion + " — which will destroy all old data.");
            db.execSQL(sqlToUpgrade);
            onCreate(db);
        }
    }

    @Override
    public boolean exists() {
        return EncoderApp.context.getDatabasePath((String) config.get("name")).exists();
    }

    @Override
    public boolean drop() {


        if (exists())
            return EncoderApp.context.deleteDatabase((String) config.get("name"));

        return true;
    }

    @Override
    public void clear(String table) {

    }

    @Override
    public long insert(String table, HashMap<String, String> columnValues) {
        return 0;
    }

    @Override
    public ArrayList<HashMap<String, String>> find(String table, HashMap<String, Object> options) {
        return null;
    }

    @Override
    public int update(String table, HashMap<String, Object> options) {
        return 0;
    }

    @Override
    public int delete(String table, HashMap<String, Object> options) {
        return 0;
    }

}
