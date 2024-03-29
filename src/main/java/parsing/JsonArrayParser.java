package parsing;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;

public class JsonArrayParser {

    public static List<JsonElement> parseJsonArray(String jsonArrayStr) throws JsonParseException {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();

        JsonArray jsonArray = jsonParser.parse(jsonArrayStr).getAsJsonArray();
        List<JsonElement> resultList = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            resultList.add(jsonElement);
        }

        return resultList;
    }
}
