package cr.rupel.discord.channellog;

import sx.blah.discord.handle.impl.obj.Guild;

import java.sql.Timestamp;

/**
 * Created by Rupel on 09/08/2017.
 */

//Data for a Single Event
public class SingleEventData { //package private(?)

    String channelName;
    String username; //who joined/left
    Timestamp timestamp; //when did the event occur
    boolean joined; //joined == true, left == false

    public SingleEventData(String channelName, String username, Timestamp timestamp, boolean hasJoined) {
        this.channelName = channelName;
        this.username = username;
        this.timestamp = timestamp;
        joined = hasJoined;
    }

    public String getUsername() {
        return username;
    }

    public boolean getJoined() {
        return joined;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getChannelName() {
        return channelName;
    }
}
