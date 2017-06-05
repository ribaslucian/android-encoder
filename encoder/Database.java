package com.example.lucian.sqlite.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucian on 01/06/2017.
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
public abstract class Database {

    /**
     * Verifica se existe um banco de dados criado para o contexto em questão.
     *
     * @return boolean
     */
    public static boolean exists() { return Encoder.database.exists(); }

    /**
     * Deleta o banco de dados.
     *
     * @return boolean
     */
    public static boolean drop() {
        return Encoder.database.drop();
    }

    /**
     * Apaga os registros de uma determinada tabela.
     *
     * @param table
     */
    public static void clear(String table) {
        Encoder.database.clear(table);
    }

    /**
     * Insere um registro em uma determinada Tabela.
     *
     * @param table
     * @param columnValues Hash<String, String>
     */
    public static void insert(String table, HashMap<String, String> columnValues) {
        Encoder.database.insert(table, columnValues);
    }

    /**
     * Obtem todos os registro de uma determinada tabela.
     *
     * @param table
     * @return ArrayList<HashMap<String, String>> Vetor com todos os registros associados por coluna.
     */
    public static ArrayList<HashMap<String, String>> all(String table) {
        return Encoder.database.all(table);
    }

    /**
     * Obtem-se um ou mais registros.
     *
     * @param table
     * @param options [String campo: String value] Valores para embasar a busca.
     * @return
     */
    public static ArrayList<HashMap<String, String>> find(String table, HashMap<String, Object> options) {
        return Encoder.database.find(table, options);
    }

    /**
     * Edita um ou mais registros de uma determinada tabela.
     *
     * @param table
     * @param options
     */
    public static String update(String table, HashMap<String, Object> options) {
        int r = Encoder.database.update(table, options);
        return new Integer(r).toString();
    }

    /**
     * Busca unicamente um registro pelo ID.
     *
     * @param table
     * @param id
     * @return
     */
    public static Map<String, String> get(String table, final String id) {
        return Encoder.database.get(table, id);
    }

    /**
     * Edita unicamente um registro pelo ID.
     *
     * @param table
     * @param id
     * @param columnValues
     */
    public static String edit(String table, final String id, final HashMap<String, String> columnValues) {
        int r = Encoder.database.edit(table, id, columnValues);
        return new Integer(r).toString();
    }

}
