package cr.rupel.discord.references;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rupel on 16/07/2017.
 */
public class depSingleChannel {

    String name;
    List<String> users = new ArrayList<>();

    public depSingleChannel(String channelName) {
        name = channelName;
    }

    public void addUser(depSingleUser user) {
        users.add(user.getName());
    }

    public void removeUser(depSingleUser user) {
        users.remove(user);
    }

}
