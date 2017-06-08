package com.example.lucian.sqlite.encoder;

/**
 * Created by Lucian on 08/06/2017.
 */
public class Reflexion {

    /**
     * Obtem o valor estático de um determinado atributo de uma determinada classe.
     *
     * @param c
     * @param field
     * @return
     */
    public static String field(Class c, String field) {
        try {
            return (String) c.getField(field).get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
