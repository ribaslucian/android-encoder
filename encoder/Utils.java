package com.example.lucian.sqlite.encoder;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.lucian.sqlite.EncoderApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Lucian on 08/06/2017.
 */

public class Utils {

    /**
     * Contato do debug do método debug().
     *
     * @type Integer
     */
    public static Integer DEBUG = 0;

    /**
     * Apresenta um log personalizado para a aplicação atual.
     * @param text String
     */
    public static void log(String text) {
        Log.i(EncoderApp.LOG_NAME, text);
    }

    /**
     * Efetua o debug (printa no AndroidMonitor) baseado num contador de chamada; basta
     * chamar o método para iniciar a contagem, para cada contagem o contador somará 1.
     *
     * @reurn void
     */
    public static void DEBUG() {
        DEBUG++;
        log("DEBUG " + DEBUG.toString());
    }

    /**
     * Gera uma String aleatória.
     * @return String hash
     */
    public static String rand() {
        return UUID.randomUUID().toString();
    }

    /**
     * Converte um resultado Cursor para uma lista associativa e registros.
     *
     * @param cursor Cursor
     * @return ArrayList<HashMap<String, String>>
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
     * Converte um objeto Map para um objeto ContentValues.
     *
     * @param map HashMap<String, String>
     * @return ContentValues
     */
    public static ContentValues mapToContentValues(HashMap<String, String> map) {
        ContentValues resources = new ContentValues();

        for (String column: map.keySet())
            resources.put(column, map.get(column));

        return resources;
    }
}
