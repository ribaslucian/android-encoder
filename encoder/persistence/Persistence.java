package com.example.lucian.sqlite.encoder.persistence;

import com.example.lucian.sqlite.EncoderApp;
import com.example.lucian.sqlite.encoder.Config;
import com.example.lucian.sqlite.encoder.Utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Criado por Lucian Rossoni Ribas <ribas.lucian@gmail.com> em 08/06/2017.
 * Uma Persistência representa abstração da camada de referência
 * de dados, sejam bancos de dados locais e remotos ou APIs online.
 */
public abstract class Persistence {

    /**
     * Aqui será salvo todas as configurações de persistências incluindo
     * os dados de conexões e propriedades específicas da conexão em questão.
     *
     * Este valor é estático pois as configurações
     * serão sempre as mesmas durante a execução toda.
     *
     * O Alias da persistência deve ir nos {models} da aplicação, informando
     * qual persitência o {Model} utilizará para gerenciar os registros.
     *
     * Exemplo de chamada:
     *      put("ALIAS_SQLite_01", new Config() {{
     *          set("persistence", "OLD_SQLite";"PostgreSQL";"REST";"other");
     *          set("user", "name");
     *          set("password", "123");
     *          set("charset", "UTF-8");
     *          set("persistence", "OLD_SQLite";"PostgreSQL";"REST";"other");
     *          set("name", "encoder-sqlite-db"); // apenas para OLD_SQLite
     *          set("URL", "www.api.com"); // apenas para REST
     *
     *          // Aqui a instância da classe de persistência respectiva
     *          // (OLD_SQLite, PostgreSQL, REST, etc) deve armazenada,
     *          // contendo as propriedades definas nessa configuração.
     *          set("instance", new OLD_SQLite());
     *      }});
     */
    public static HashMap<String, Config> configs;

    /**
     * Esse apelido referência a persistência dentro dos valores de configurações
     * (Persistece.config). Basicamente, toda instância de persistência será em
     * função das configurações definadas pra ela em Inicializer.persistence;
     * esse apelido apenas informa qual é a configuração da instância em questão.
     *
     * @type String
     */
    public String alias;

    /**
     * Configurações salvas no alias da conexões em questão.
     *
     * @type Config
     */
    public Config config;

    /**
     * Todo construtor de persistência deverá possui seu apelido informado.
     * @param alias String
     */
    public Persistence(String alias) {
        this.alias = alias;
        config = EncoderApp.persistence.get(alias);
    }

    /**
     * Crie (se ainda não existir) e obtém a instância da
     * persistencia definida na conexão respectiva ao parâmetro {alias}.
     *
     * @param alias
     */
    public static void get(String alias) {
        try {
            // obtemos a classe do persistência basenado-se nas configurações de {alias}
            Class persistenceClass = (Class) Persistence.param(alias, "persistence");
            // criamos a instância da persistência
            Persistence persistence = (Persistence) persistenceClass.getDeclaredConstructor(String.class).newInstance(alias);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        Config config = EncoderApp.persistence.get(alias);
//        return (Persistence) config.get("instance");
    }

    /**
     * Obtém um parâmetro específico de uma determinada configuração.
     *
     * @param alias String
     * @param param String
     * @return Object
     */
    public static Object param(String alias, String param) {
        return EncoderApp.persistence.get(alias).get(param);
    }

    /**
     * Verifica se a base de dados referente a instância da Persistência atual Existe.
     * - No de bancos de dados locais verifica se o arquivo existe;
     * - No de bancos de dados remotos refirica se há conexão;
     * - No de REST verifica se a URL principal da API está online.
     *
     * @return boolean
     */
    public abstract boolean exists();

    /**
     * Deve-se excluir o arquivo ou banco de dados referente a persistência atual.
     *
     * @return boolean
     */
    public abstract boolean drop();

    /**
     * Limpa os registros de uma determinada tabela da persistência atual.
     *
     * @return boolean
     */
    public abstract void clear(String table);

    /**
     * Insere um registro em uma determinada tabela da persistência.
     * Exemplo de chamada:
     *      insert("user", new HashMap<String, String>(){{
     *          put("email", "example@email.com");
     *          put("email", "123456");
     *      }})
     *
     * @param table String
     * @param columnValues HashMap<String, String>
     * @return long: Código resultado da interação específico da persistência.
     *              Sugere 0 para erro e 1 para sucesso.
     */
    public abstract long insert(String table, HashMap<String, String> columnValues);

    /**
     * Busca um ou mais registros especifícos de uma
     * determinada tabela da persistência em questão.
     * Exemplo de chamada:
     *      Persistence.find("users", new HashMap<String, Object>(){{
     *          put("columns", new String[] {"*"});
     *          put("where", "id=?");
     *          put("params", new String[] {"1"});
     *      }});
     *
     * @param table String
     * @param options HashMap<String, Object>
     * @return ArrayList<HashMap<String, String>>
     */
    public abstract ArrayList<HashMap<String, String>> find(String table, HashMap<String, Object> options);

    /**
     * Atualizar um ou mais registros de uma
     * determinada tabela da persistência em questão.
     * Exemplo de chamada:
     *      Persistece.update("users", new HashMap<String, Object>(){{
     *          put("where", "id=?");
     *          put("params", new String[] {"1"});
     *          put("values", new HashMap<String, String>(){{
     *              put("email", "example1@email.com");
     *              put("password", "654321");
     *          }});
     *      }});
     *
     * @param table String
     * @param options HashMap<String, Object>
     * @return int: Código resultado da interação específico da persistência.
     *              Sugere 0 para erro e 1 para sucesso.
     */
    public abstract int update(String table, HashMap<String, Object> options);

    /**
     * Deleta um ou mais registros de uma
     * determinada tabela da persistência em questão.
     * Exemplo de chamada:
     *      Persistece.update("users", new HashMap<String, Object>(){{
     *          put("where", "id=?");
     *          put("params", new String[] {"1"});
     *      }});
     *
     * @param table String
     * @param options HashMap<String, Object>
     * @return int: Código resultado da interação específico da persistência.
     *              Sugere 0 para erro e 1 para sucesso.
     */
    public abstract int delete(String table, HashMap<String, Object> options);

    /**
     * @Utilitário do método {find}, portanto é igual para todas
     * as persistências, o corpo do médoto pode ser escrito aqui mesmo.
     *
     * Busca todos od registros de uma
     * determinada tabela da persistência em questão.
     * Exemplo de chamada:
     *      for (Map<String, String> record: Persistence.all("users")) {
     *          // apresentamos os dados de todas as colunas
     *          for (String column: record.keySet())
     *              Encoder.log(column + ": " + record.get(column));
     *
     *          Encoder.log("—");
     *      }
     *
     * @param table String
     * @return ArrayList<HashMap<String, String>>
     */
    public ArrayList<HashMap<String, String>> all(String table) {
        return find(table, new HashMap<String, Object>(){{
            put("columns", new String[] {"*"});
            put("where", "");
            put("params", new String[]{});
        }});
    }

    /**
     * @Utilitário do método {find}, portanto é igual para todas
     * as persistências, o corpo do médoto pode ser escrito aqui mesmo.
     *
     *
     * @param table String
     * @param id String
     * @return HashMap<String, String>
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
     * @Utilitário do método {update}, portanto é igual para todas
     * as persistências, o corpo do médoto pode ser escrito aqui mesmo.
     *
     * Edita um único registro de uma determinada tabela da persistência em questão.
     *
     * @param table String
     * @param id String
     * @param columnValues
     * @return HashMap<String, String>
     * @return int: Código resultado da interação específico da persistência.
     *              Sugere 0 para erro e 1 para sucesso.
     */
    public int edit(String table, final String id, final HashMap<String, String> columnValues) {
        return update(table, new HashMap<String, Object>(){{
            put("where", "id=?");
            put("params", new String[] {id});
            put("values", columnValues);
        }});
    }

    /**
     * @Utilitário do método {delete}, portanto é igual para todas
     * as persistências, o corpo do médoto pode ser escrito aqui mesmo.
     *
     * Deleta um único registro de uma determinada tabela da persistência em questão.
     *
     * @param table String
     * @param id String
     * @param columnValues
     * @return HashMap<String, String>
     * @return int: Código resultado da interação específico da persistência.
     *              Sugere 0 para erro e 1 para sucesso.
     */
    public int remove(String table, final String id, final HashMap<String, String> columnValues) {
        return update(table, new HashMap<String, Object>(){{
            put("where", "id=?");
            put("params", new String[] {id});
            put("values", columnValues);
        }});
    }


}
