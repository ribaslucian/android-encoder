// Inserindo
//    Persistence.get("default").insert("table", new HashMap<String, String>(){{
//        put("column", "value");
//        put("column1", Utils.rand());
//    }});
//
// Buscando todos
//    for (Map<String, String> record : Persistence.get("default").all("table")) {
//        // apresentamos os dados de todas as colunas
//        for (String column : record.keySet())
//          Utils.log(column + ": " + record.get(column));
//        Utils.log("—");
//    }