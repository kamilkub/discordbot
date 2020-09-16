package pl.discord.functionality;

public enum MessageType {

    JOKE ("#joke"),
    PLAY ("#play"),
    VIDEO("#video");
    private String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }


}
