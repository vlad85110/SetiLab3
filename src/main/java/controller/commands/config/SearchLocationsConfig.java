package controller.commands.config;

public class SearchLocationsConfig extends CommandConfig {
    private String locationNameParam;

    private String resultsKeyName;

    public String getLocationNameParam() {
        return locationNameParam;
    }

    public void setLocationNameParam(String locationNameParam) {
        this.locationNameParam = locationNameParam;
    }

    public String getResultsKeyName() {
        return resultsKeyName;
    }

    public void setResultsKeyName(String resultsKeyName) {
        this.resultsKeyName = resultsKeyName;
    }
}
