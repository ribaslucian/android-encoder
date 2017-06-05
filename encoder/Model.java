package com.example.lucian.sqlite.encoder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lucian on 03/06/2017.
 */
// mostrando models
//    Encoder.log("— ALL");
//    for (Model event: Model.all("events")) {
//        Encoder.log(event.get("id"));
//        Encoder.log(event.get("name"));
//        Encoder.log("—");
//    }
public class Model {

    /**
     * @InstanceFeature
     */
    public String table;

    /**
     * @InstanceFeature / Recurso da Instância: Objeto
     * Corresponde aos valores do registro armazanado no model.
     * Representa os valores de uma instância específica, portanto
     * não deve ser estático. Para preencher esse valor se utiliza
     * o construtor os métodos {set/get}.
     */
    public HashMap<String, String> data = new HashMap();

    /**
     * @InstanceFeature
     * Construtor de uma instância sem parâmetros; {data}
     * deve ser gerenciado utilizando os métodos {set/get};
     */
    public Model() { }

    /**
     * @InstanceFeature
     * Construtor de uma instância de model que recebe {data}.
     * @param data
     */
    public Model(HashMap<String, String> data) {
        this.data = data;
    }

    /**
     * @EntityFeature
     * Limpa a tabela correspondente ao model em questão.
     */
    public static void clear(String table) {
        Database.clear(table);
    }

    /**
     * @EntityFeature
     * Obtem todos os registros da entidade correspondente.
     * @param table String
     * @return ArrayList<Model>
     */
    public static ArrayList<Model> all(String table) {
        ArrayList<Model> models = new ArrayList();

        for (HashMap<String, String> record: Database.all(table)) {
            Model model = new Model(record);
            models.add(model);
        }

        return models;
    }

    /**
     * @InstanceFeature
     * Escreve um valor em {data} da instância em questão.
     *@param column
     * @param value
     */
    public Model set(String column, String value) {
        data.put(column, value);
        return this;
    }

    /**
     * @InstanceFeature
     * Obtem um valor de {data} da instância em questão.
     *
     * @param column String
     * @return String String
     */
    public String get(String column) {
        return data.get(column);
    }

    public void save(String table) {
        Database.insert(table, data);
    }

}
