package ch.lebois.troyserver.model;

/**
 * Project: Hermann
 **/
public class HomepageModel {
    private String client;
    private String os;
    private String lastseen;
    private int logcount;
    private int errorcount;

    public String getLastseen() {
        return lastseen;
    }

    public void setLastseen(String lastseen) {
        this.lastseen = lastseen;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getLogcount() {
        return logcount;
    }

    public void setLogcount(int logcount) {
        this.logcount = logcount;
    }

    public int getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(int errorcount) {
        this.errorcount = errorcount;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
