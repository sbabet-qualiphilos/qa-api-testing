package utils;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<String, Object> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public void set(String key, Object value) {
        context.put(key, value);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(context.get(key));
    }

    public void setResponse(Response response) {
        set("response", response);
    }

    public Response getResponse() {
        return get("response", Response.class);
    }
}
