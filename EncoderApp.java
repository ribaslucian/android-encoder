package com.example.lucian.sqlite;

import android.content.Context;

import com.example.lucian.sqlite.encoder.Config;
import com.example.lucian.sqlite.encoder.Utils;
import com.example.lucian.sqlite.encoder.persistence.SQLite;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Criado por Lucian Rossoni Ribas <ribas.lucian@gmail.com> em 08/06/2017.
 *
 * Abstração da aplicação em questão para as propriedades da bibliteca Encoder.
 */
public class EncoderApp {

    /**
     * Índice personalizado para os Logs dessa aplicação.
     */
    public static final String LOG_NAME = "Encoder-Log";

    /**
     * Instancia do contexto principal da aplicação.
     *
     * @type Context
     */
    public static Context context;

    /**
     * Configurações das conexões com bancos de dados (persistências).
     * Verificar documentação em: encoder.Persistence.config
     *
     * @type HashMap<String, Config>
     */
    public static HashMap<String, Config> persistence = new HashMap(){{
        put("default", new Config() {{
            set("persistence", SQLite.class);
            set("name", "encoder-sqlite-db");
            set("version", 3);

            set("sqlToCreate", new ArrayList<String>() {{
                add("CREATE TABLE phone_numbers (id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, name VARCHAR(255), number TEXT);");
                add("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, name VARCHAR(255), date VARCHAR(32), description TEXT);");
            }});

            set("sqlToUpgrade", new ArrayList<String>() {{
                add("DROP TABLE IF EXISTS phone_numbers;");
                add("DROP TABLE IF EXISTS events;");
            }});
        }});

        put("SQLiteAux", new Config() {{
            set("persistence", SQLite.class);
            set("name", "encoder-sqlite-auxiliar-db");
            set("version", 1);

            set("sqlToCreate", new ArrayList<String>() {{
                add("CREATE TABLE phone_numbers (id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, name VARCHAR(255), number TEXT);");
                add("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, name VARCHAR(255), date VARCHAR(32), description TEXT);");
            }});

            set("sqlToUpgrade", new ArrayList<String>() {{
                add("DROP TABLE IF EXISTS phone_numbers;");
                add("DROP TABLE IF EXISTS events;");
            }});
        }});
    }};

    /**
     * Inicia uma aplicação utilizando a biblioteca
     * Encoder — armazena e prepara os recursos necessários.
     *
     * @param mainContext
     */
    public static void start(Context mainContext) {
        Utils.log("*— Start App With Encoder Lib —*");
        context = mainContext;
    }

}
