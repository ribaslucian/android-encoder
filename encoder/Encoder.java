package com.example.lucian.sqlite.encoder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Lucian on 11/05/2017.
 */

/*
// iniciamos os recursos da biblioteca Encoder.
    Encoder.start(getBaseContext());
*/
public abstract class Encoder {

    public static final String LOG_NAME = "Encoder-Log";
    public static final String DATABASE_NAME = "encoder-sqlite-db";
    public static final int DATABASE_VERSION = 5;
    public static Integer DEBUG = 0;

    private static HashMap<String, Object> warehouse;
    public static Context context;
    public static SQLite database;

    public static void start(Context context) {
        Encoder.log("*— START Encoder");
        Encoder.context = context;
        Encoder.database = new SQLite(context);
    }

    public static void end() {
        database.close();
    }

    public static void set(String name, Object obj) {
        warehouse.put(name, obj);
    }

    public static Object get(String name) {
        return warehouse.get(name);
    }

    public static void log(String text) {
        Log.i(Encoder.LOG_NAME, text);
    }

    public static void DEBUG() {
        DEBUG++;
        log("DEBUG " + DEBUG.toString());
    }

    public static String rand() {
        return UUID.randomUUID().toString();
    }

    /**
     * Converte um resultado Cursor para uma lista associativa e registros.
     *
     * @param cursor
     * @return
     */
    public static ArrayList<HashMap<String, String>> cursorToAssociatedList(Cursor cursor) {
        int count = cursor.getCount();

        // se nenhum registro for encontrado retornamos um Array de Objetos vazio
        if (count <= 0)
            return new ArrayList<>();

        // criamos a lista de registros de retorno
        ArrayList resources = new ArrayList<HashMap<String, String>>();

        // preparamos para percorrer os registros
        if (cursor.moveToFirst()) {
            // percorremos
            for (int i = 0; i < count; i++) {
                // criamos um HashMap para armazenar o registro atual
                HashMap resource = new HashMap<String, String>();

                // percorremos o total de colunas obtendo seu nome e valor
                for (int j = 0; j < cursor.getColumnCount(); j++)
                    resource.put(cursor.getColumnName(j), cursor.getString(j));

                resources.add(resource);
                cursor.moveToNext();
            }
        }

        return resources;
    }

    /**
     * Converte um Map para um ContentValues.
     *
     * @param map
     * @return
     */
    public static ContentValues mapToContentValues(HashMap<String, String> map) {
        ContentValues resources = new ContentValues();

        for (String column: map.keySet())
            resources.put(column, map.get(column));

        return resources;
    }

}
