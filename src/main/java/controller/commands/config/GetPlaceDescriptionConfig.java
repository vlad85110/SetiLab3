package controller.commands.config;

public class GetPlaceDescriptionConfig extends CommandConfig {
    private String infoKeyName;
    private String descriptionKeyName;

    public String getInfoKeyName() {
        return infoKeyName;
    }

    public void setInfoKeyName(String infoKeyName) {
        this.infoKeyName = infoKeyName;
    }

    public String getDescriptionKeyName() {
        return descriptionKeyName;
    }

    public void setDescriptionKeyName(String descriptionKeyName) {
        this.descriptionKeyName = descriptionKeyName;
    }
}
