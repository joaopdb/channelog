package cr.rupel.discord.channellog;

import java.sql.Timestamp;
import java.util.*;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.apache.commons.collections4.queue.CircularFifoQueue;

/**
 * Created by Rupel on 09/08/2017.
 * Stores data related to a Single event "in" the corresponding channel
 */

//Data for a Single Channel
public class ChannelLogger {


    //Map for List of events
    Map<String, Queue<SingleEventData>> channelLogs;

    public ChannelLogger() {
        channelLogs = new HashMap<>();
        //eventDataList = new LinkedList<>();
        //eventDataQueue = new CircularFifoQueue<SingleEventData>(4);
    }

    public void addEvent(long channelID, String channelName, String username, Timestamp timestamp, boolean hasJoined) {

        channelName = channelName.toLowerCase();

        //Channel not in the list, assign new queue to key
        if(!channelLogs.containsKey(channelName)) {
            //create queue
            Queue<SingleEventData> channelQueue = new CircularFifoQueue<SingleEventData>(10);
            //fill queue
            channelQueue.add(new SingleEventData(channelName, username, timestamp, hasJoined));
            //add queue to hashMap
            channelLogs.put(channelName, channelQueue);

        }
        //Queue already existing, get it and add data
        else {
            //Get existing queue out of map and add new data
            channelLogs.get(channelName).add(new SingleEventData(channelName, username, timestamp, hasJoined));
        }
    }

    public Queue<SingleEventData> getQueueFor(String channelName) {
        //return eventDataList;
        return channelLogs.get(channelName.toLowerCase());
    }

    public String getLogFor(String channelName) throws Exception {

        Queue<SingleEventData> generalQ = channelLogs.get(channelName.toLowerCase());
        StringBuilder sb = new StringBuilder();
        boolean line = false;

        sb.append("Log for " + channelName + ":\n====================\n");
        for(SingleEventData data : generalQ) {
            if(line) sb.append("--------------------\n"); //if it's stupid but it works...
            sb.append(data.timestamp + ":\n");
            sb.append(data.getUsername());
            sb.append(data.getJoined() ? " joined.\n" : " left.\n");
            line = true;
        }
        return sb.toString();
    }
}
