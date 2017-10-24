package cr.rupel.discord.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class TrackScheduler extends AudioEventAdapter {

    public TrackScheduler(com.sedmelluq.discord.lavaplayer.player.AudioPlayer player) {

    }

    @Override
    public void onPlayerPause(AudioPlayer player) {
        // Player was paused
        System.out.println("player paused");

    }

    @Override
    public void onPlayerResume(AudioPlayer player) {
        // Player was resumed
        System.out.println("player resumed");
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        // A track started playing
        System.out.println("a track started playing");
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            // Start next track
            System.out.println("a track ended");
        }

        // endReason == FINISHED: A track finished or died by an exception (mayStartNext = true).
        // endReason == LOAD_FAILED: Loading of a track failed (mayStartNext = true).
        // endReason == STOPPED: The player was stopped.
        // endReason == REPLACED: Another track started playing while this had not finished
        // endReason == CLEANUP: Player hasn't been queried for a while, if you want you can put a
        //                       clone of this back to your queue
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        // An already playing track threw an exception (track end event will still be received separately)
        System.out.println("onTrackException");
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
        // Audio track has been unable to provide us any audio, might want to just start a new track
        System.out.printf("onTrackStuck");
    }
}