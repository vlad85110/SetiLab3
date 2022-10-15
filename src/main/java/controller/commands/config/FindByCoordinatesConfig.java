package controller.commands.config;

public abstract class FindByCoordinatesConfig extends CommandConfig {
    private String latKeyName;
    private String lngKeyName;

    public String getLatKeyName() {
        return latKeyName;
    }

    public void setLatKeyName(String latKeyName) {
        this.latKeyName = latKeyName;
    }

    public String getLngKeyName() {
        return lngKeyName;
    }

    public void setLngKeyName(String lngKeyName) {
        this.lngKeyName = lngKeyName;
    }
}
