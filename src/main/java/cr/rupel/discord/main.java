package cr.rupel.discord;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.Guild;

/**
 * Created by Rupel on 15/07/2017.
 */
public class main {
    public static void main(String[] args){
        //
        String token = "MzQ1Njk5NTg2ODEzNDYwNDkw.DG_FbQ.OBGuQg9wlhkTKq8eq1u-sJdS-2g";
        /*if(args.length != 1){
            System.out.println("Please enter the bots token as the first argument e.g java -jar thisjar.jar tokenhere");
            return;
        }*/

        //IDiscordClient cli = BotUtils.getBuiltDiscordClient(args[0]);
        IDiscordClient cli = BotUtils.getBuiltDiscordClient(token);

        cli.changePlayingText("/whowas <channelname>");
        /*
        // Commented out as you don't really want duplicate listeners unless you're intentionally writing your code
        // like that.
        // Register a listener via the IListener interface
        cli.getDispatcher().registerListener(new IListener<MessageReceivedEvent>() {
            public void handle(MessageReceivedEvent event) {
                if(event.getMessage().getContent().startsWith(BotUtils.BOT_PREFIX + "test"))
                    BotUtils.sendMessage(event.getChannel(), "I am sending a message from an IListener listener");
            }
        });
        */

        // Register a listener via the EventSubscriber annotation which allows for organisation and delegation of events
        cli.getDispatcher().registerListener(new MyEvents());

        // Only login after all events are registered otherwise some may be missed.
        cli.login();

    }
}
