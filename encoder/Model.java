package com.example.lucian.sqlite.encoder;

import com.example.lucian.sqlite.encoder.persistence.Persistence;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Criado por Lucian Rossoni Ribas <ribas.lucian@gmail.com> em 08/06/2017.
 * Abstração do modelo MVC, Model.
 */
public class Model {

    /**
     * @EntityFeature
     * Representa a persistência de dados (Banco de dados ou API) que a instância será salva.
     */
    public static String persistence = "default";

    /**
     * @EntityFeature
     * Representa a tabela a instância será salva.
     */
    public static String table = "ENCODER_MODEL_MISSING_TABLE";

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
    public static void clear(Class<? extends Model> appModel) {
        Persistence
            .get(Reflexion.field(appModel, "persistence"))
            .clear(Reflexion.field(appModel, "table"));
    }


    /**
     * @EntityFeature
     * Obtem todos do modelo referente ao parâmetro.
     * @param appModel Class<? extends Model>
     * @return
     */
    public static ArrayList<? extends Model> all(Class<? extends Model> appModel) {
        try {
            ArrayList<Model> models = new ArrayList();
            ArrayList<HashMap<String, String>> records = Persistence
                    .get(Reflexion.field(appModel, "persistence"))
                    .all(Reflexion.field(appModel, "table"));

            for (HashMap<String, String> record : records) {
                Model resourceCasted = appModel.newInstance();
                resourceCasted.data = record;
                models.add(resourceCasted);
            }

            return models;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @InstanceFeature
     * Escreve um valor em {data} da instância em questão.
     * @param column String
     * @param value String
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

    /**
     * @InstanceFeature
     * Salva o registro da instância do model referente a sua persistência.
     */
    public void save() {
        Persistence.get(persistence()).insert(table(), data);
    }

    /**
     * @EntityFeature
     * Obtem o nome da tabela definida no model filho.
     * @return String
     */
    public String table() {
        return Reflexion.field(this.getClass(), "table");
    }

    /**
     * @EntityFeature
     * Obtem o nome da persistence definida no model filho.
     * @return String
     */
    public String persistence() {
        return Reflexion.field(this.getClass(), "persistence");
    }

}
