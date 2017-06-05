package com.example.lucian.sqlite.encoder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lucian on 11/05/2017.
 */
/*
// criando números e eventos
    Encoder.log("— INSERINDO");
    for (Integer i = 1; i <= 3; i++) {
        Database.insert("phone_numbers", new HashMap<String, String>(){{
            put("name", "Nome");
            put("number", Encoder.rand());
        }});

        Database.insert("events", new HashMap<String, String>(){{
            put("name", "Nome");
            put("date", Encoder.rand());
            put("description", Encoder.rand());
        }});
    }

// apresentando números
    Encoder.log("— NÚMEROS");
    for (Map<String, String> record: Database.all("phone_numbers")) {
        // apresentamos os dados de todas as colunas
        for (String column: record.keySet())
            Encoder.log(column + ": " + record.get(column));
        Encoder.log("—");
    }

// apresentando eventos
    Encoder.log("— EVENTOS");
    for (Map<String, String> record: Database.all("events")) {
        // apresentamos os dados de todas as colunas
        for (String column: record.keySet())
            Encoder.log(column + ": " + record.get(column));
        Encoder.log("—");
    }

// editamos um ou mais registros
    Encoder.log("— EDIT");
    Database.update("phone_numbers", new HashMap<String, Object>(){{
        put("where", "id=?");
        put("params", new String[] {"1"});
        put("values", new HashMap<String, String>(){{
            put("name", "Lucian Ribas");
            put("number", "(42) 9988-5155");
        }});
    }});

// apresentando um registro especifico
    Encoder.log("— FIND");
    List<Map<String, String>> records = Database.find("phone_numbers", new HashMap<String, Object>(){{
        put("columns", new String[] {"*"});
        put("where", "id=?");
        put("params", new String[] {"1"});
    }});

    for (Map<String, String> record: records) {
        // apresentamos os dados de todas as colunas
        for (String column: record.keySet())
            Encoder.log(column + ": " + record.get(column));
        Encoder.log("—");
    }

// buscamos unicamente um registro
    Encoder.log("— GET");
    Map<String, String> record = Database.get("phone_numbers", "1");
    for (String column: record.keySet())
        Encoder.log(column + ": " + record.get(column));
    Encoder.log("—");

// editamos unicamente um registro
    Encoder.log("— EDIT ONE");
    Database.edit("phone_numbers", "1", new HashMap<String, String>(){{
        put("name", "Lucian Rossoni Ribas");
        put("number", "(42) 99988-5155");
    }});
*/
public class SQLite extends SQLiteOpenHelper {

    public SQLite(Context context) {
        super(context, Encoder.DATABASE_NAME, null, Encoder.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE phone_numbers (id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, name VARCHAR(255), number TEXT)");
        db.execSQL("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, name VARCHAR(255), date VARCHAR(32), description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Encoder.log("Upgrading Database from:" + oldVersion + " to: " + newVersion + " — which will destroy all old data.");
        db.execSQL("DROP TABLE IF EXISTS phone_numbers;");
        db.execSQL("DROP TABLE IF EXISTS events;");
        onCreate(db);
    }

    /**
     * Verifica se existe um banco de dados criado para o contexto em questão.
     *
     * @return boolean
     */
    public boolean exists() {
        return Encoder.context.getDatabasePath(Encoder.DATABASE_NAME).exists();
    }

    /**
     * Deleta o banco de dados.
     *
     * @return boolean
     */
    public boolean drop() {
        if (exists()) {
            return Encoder.context.deleteDatabase(Encoder.DATABASE_NAME);
        }

        return true;
    }

    /**
     * Apaga os registros de uma determinada tabela.
     *
     * @param table
     */
    public void clear(String table) {
        SQLiteDatabase adapter = getWritableDatabase();
        adapter.execSQL("DROP TABLE IF EXISTS " + table + ";");
        adapter.close();
    }

    /**
     * Insere um registro em uma determinada Tabela.
     *
     * @param table
     * @param columnValues Hash<String, String>
     */
    public long insert(String table, HashMap<String, String> columnValues) {
        long r = this.getWritableDatabase().insert(table, null, Encoder.mapToContentValues(columnValues));
        super.close();
        return r;
    }

    /**
     * Obtem todos os registro de uma determinada tabela.
     *
     * @param table
     * @return ArrayList<HashMap<String, String>> Vetor com todos os registros associados por coluna.
     */
    public ArrayList<HashMap<String, String>> all(String table) {
        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + table + ";", null);
        return Encoder.cursorToAssociatedList(c);
    }

    /**
     * Obtem-se um ou mais registros.
     *
     * @param table
     * @param options [String campo: String value] Valores para embasar a busca.
     * @return
     */
    public ArrayList<HashMap<String, String>> find(String table, HashMap<String, Object> options) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                table,
                (String[]) options.get("columns"),
                (String) options.get("where"),
                (String[]) options.get("params"),
                null, null, null);

        return Encoder.cursorToAssociatedList(c);
    }

    /**
     * Edita um ou mais registros de uma determinada tabela.
     *
     * @param table
     * @param options
     */
    public int update(String table, HashMap<String, Object> options) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = Encoder.mapToContentValues((HashMap<String, String>) options.get("values"));
        int r = db.update(table,
                values,
                (String) options.get("where"),
                (String[]) options.get("params"));

        db.close();
        return r;
    }

    /**
     * Busca unicamente um registro pelo ID.
     *
     * @param table
     * @param id
     * @return
     */
    public HashMap<String, String> get(String table, final String id) {
        ArrayList<HashMap<String, String>> records = find(table, new HashMap<String, Object>(){{
            put("columns", new String[] {"*"});
            put("where", "id=?");
            put("params", new String[] {id});
        }});

        return records.get(0);
    }

    /**
     * Edita unicamente um registro pelo ID.
     *
     * @param table
     * @param id
     * @param columnValues
     */
    public int edit(String table, final String id, final HashMap<String, String> columnValues) {
        return update(table, new HashMap<String, Object>(){{
            put("where", "id=?");
            put("params", new String[] {id});
            put("values", columnValues);
        }});
    }
}
