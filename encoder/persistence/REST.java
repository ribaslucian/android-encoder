package com.example.lucian.sqlite.encoder.persistence;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lucian on 07/06/2017.
 */

public class REST extends Persistence {

    /**
     * Todo construtor de persistência deverá possui seu apelido informado.
     *
     * @param alias String
     */
    public REST(String alias) {
        super(alias);
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean drop() {
        return false;
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
