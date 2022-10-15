package controller.commands.config;

import java.util.Map;

public class CommandConfig {
    protected String baseURL;

    private Map<String, String> params;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
