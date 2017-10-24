package cr.rupel.discord;


import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import cr.rupel.discord.channellog.ChannelLogger;
import cr.rupel.discord.channellog.DBconnector;
import cr.rupel.discord.channellog.SingleEventData;
import cr.rupel.discord.lavaplayer.AudioProvider;
import cr.rupel.discord.lavaplayer.TrackScheduler;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelJoinEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelLeaveEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelMoveEvent;
import sx.blah.discord.util.audio.events.AudioPlayerEvent;

import java.sql.Timestamp;
import java.util.List;
import java.util.Queue;

public class MyEvents {

    ChannelLogger channelLogger;

    public MyEvents() {
        /*List<SingleUser> users = new ArrayList<>(100);
        List<SingleChannel> channels = new ArrayList<>(100);
        //channels.add(new Channel)

        users.add(new SingleUser("Rupely", "Testchannelber"));
        users.add(new SingleUser("Pupel", "TestchanneGeneral"));
        for(SingleUser user : users) {
            System.out.println("HERE I COME");
            System.out.println(user.getChannel());
            System.out.println(user.getName());
        }*/
        channelLogger = new ChannelLogger();
        DBconnector db = new DBconnector("postgres", "password");

    }

    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event){
        if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "test"))
            BotUtils.sendMessage(event.getChannel(), event.getChannel() + " I am sending a massage from an EventSubscriber listener");


        //TODO: handle parameters
        /*if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "whowas General")) {
            channelLogger.getLogFor("General");
            BotUtils.sendMessage(event.getChannel(), "```" + channelLogger.getLogFor("General") + "```");
        }

        if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "whowas Testing")) {
            channelLogger.getLogFor("Testing");
            BotUtils.sendMessage(event.getChannel(), "```" + channelLogger.getLogFor("Testing") + "```");
        }*/

        if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "whowas ")) {
            String command = event.getMessage().getContent();
            //TODO: add functionality for spaces in channelname
            String[] token = command.split(" ");
            System.out.println(token[1]);
            try {
                BotUtils.sendMessage(event.getChannel(), "```" + channelLogger.getLogFor(token[1]) + "```");
            } catch (Exception ex) {
                System.out.println("Invalid Channel specified!");
                BotUtils.sendMessage(event.getChannel(),  "No data or invalid channel specified! Usage: /whowas <channelname>");
                BotUtils.sendMessage(event.getChannel(), "Specified channel: " + token[1]);
            }
        }




    }

    @EventSubscriber
    public void onUserVoiceChannelJoin(UserVoiceChannelJoinEvent event) {
        //System.out.println("RUPDEB: " + event.getUser().getName() + " has left "+ event.getVoiceChannel().getName());
        logNewEvent(event, true);
    }
    @EventSubscriber
    public void onUserVoiceChannelLeave(UserVoiceChannelLeaveEvent event) {
        //System.out.println("RUPDEB: " + event.getUser().getName() + " has left "+ event.getVoiceChannel().getName());
        logNewEvent(event, false);
    }

    @EventSubscriber
    public void onUserVoiceChannelMove(UserVoiceChannelMoveEvent event) {
        //DEBUG
        /*String currentChannel = "null";
        System.out.println("RUPDEB: " + event.getUser().getName() +
                           " moved from " + event.getOldChannel() +
                           " to " + event.getNewChannel() + ".\nRUPDEB: Current Channel: " + event.getVoiceChannel());
                           */
        //checkIfBotShallJoin(event);
        //checkIfBotShallLeave(event);
    }


    private void logNewEvent(UserVoiceChannelEvent event, boolean userJoined) {
        //System.out.println("___________________________________________________");
        //System.out.print("Adding a new event to the Logger [...] ");
        channelLogger.addEvent( event.getVoiceChannel().getLongID(),
                                event.getVoiceChannel().getName(),
                                event.getUser().getName(),
                                new Timestamp(new java.util.Date().getTime()),
                                userJoined);
    }



    //Audio
/*
    @EventSubscriber
    public void onAudioPlayerInitEvent(AudioPlayerEvent event) {
        System.out.println("TONTONTONTONTOTNOTNTONTOTNTONT");
    }



    //TODO: AFK channel isn't necessarily named "AFK"!
    public void checkIfBotShallJoin(UserVoiceChannelMoveEvent event) {

        //Should be done with class Guild, dunno how to use yet ->>>> event.getGuild???
        if(event.getVoiceChannel().getName().equals("AFK")) {
            System.out.println("User joined the AFK Channel, previous Channel: " + event.getOldChannel());
            int numUsersOldChannel = event.getOldChannel().getConnectedUsers().size();
            //If the User left someone alone in the Channel bear him company (Keep in mind this is only applicable if they move to the AFK channel!)
            if(numUsersOldChannel == 1) {
                //Join
                System.out.println("Now joining the old voicechannel");
                event.getOldChannel().join();
                playAudio(event);
            }
        }
    }

    public void playAudio(UserVoiceChannelMoveEvent event) {

        com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        com.sedmelluq.discord.lavaplayer.player.AudioPlayer player = playerManager.createPlayer();

        TrackScheduler trackScheduler = new TrackScheduler(player); //dummy ctor
        player.addListener(trackScheduler);

        event.getGuild().getAudioManager().setAudioProvider(new AudioProvider(player));


        playerManager.loadItem("LYN6DRDQcjI", new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                System.out.println("track loaded");
                player.playTrack(audioTrack);
                player.startTrack(audioTrack, true);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                System.out.println("playlist loaded");
            }

            @Override
            public void noMatches() {
                System.out.println("no matches, no fire (=song can't be fire if there's no song)!");
            }

            @Override
            public void loadFailed(FriendlyException e) {
                System.out.println("load failed!");
            }
        });


    }

    private void checkIfBotShallLeave(UserVoiceChannelMoveEvent event) {
        System.out.println("Current number of Users in the previous channel: " + event.getOldChannel().getConnectedUsers().size());

        //Only one user left
        if(event.getOldChannel().getConnectedUsers().size() == 1) {
            //check if it's "me" (the Bot)
            if(event.getOldChannel().getConnectedUsers().get(0).isBot()) {
                try {
                    event.getOldChannel().leave();
                } catch (Exception ex) {
                    System.out.println("Bot left in the channel, but not me. (WaitInLine)");
                }
            }
        }
        //TODO: If there's more than 2 people the bot isn't required
    }
*/


    //**More than 50% of the Users left to AFK - Join the Channel:**//
    //For obtaining the required information we need the Guild class
    //Get AFK Userlist
    //For each user in Userlist: get previous channel
    //count users in AFK
    //count users in prev.
    //compare and play




}