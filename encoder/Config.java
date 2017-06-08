package com.example.lucian.sqlite.encoder;

import java.util.HashMap;

/**
 * Criado por Lucian Rossoni Ribas <ribas.lucian@gmail.com> em 08/06/2017.
 *
 * Abstração de um objeto que representa apenas dados abstratos de uma configuração,
 * posteriormente essa configuração será armazenada em um classe específica.
 */
public class Config {

    /**
     * Dados da configuração separados por nome.
     * @type HashMap<String, Object>
     */
    public HashMap<String, Object> data = new HashMap();

    /**
     * Escreve um valor em {data} da instância em questão.
     *
     * @param name
     * @param value
     */
    public Config set(String name, Object value) {
        data.put(name, value);
        return this;
    }

    /**
     * Obtem um valor de {data} da instância em questão.
     *
     * @param name String
     * @return Object
     */
    public Object get(String name) {
        return data.get(name);
    }

}
