package cr.rupel.discord.references;

/**
 * Created by Rupel on 16/07/2017.
 */
public class depSingleUser {
    private String name, channel;

    public depSingleUser(String name) {
        this.name = name;
    }

    //don't use and delete later
    public depSingleUser(String name, String channel) {
        this.name = name;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public String getChannel() {
        return channel;
    }
}
