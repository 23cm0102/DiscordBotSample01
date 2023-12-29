package org.example;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordBot extends ListenerAdapter {
    private static JDA jda = null;
    private static final String BOT_TOKEN = "トークンを入力";

    public static void main(String[] args) {
        jda = JDABuilder.createDefault(BOT_TOKEN)
                .setRawEventsEnabled(true)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new DiscordBot())
                .setActivity(Activity.listening("死ぬな！お前の体"))
                .build();

        jda.updateCommands().queue();

    }

    //メッセージの反応メソッド
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String msg = event.getMessage().getContentRaw();
        switch (msg) {
            case "こんにちは":
                event.getChannel().sendMessage("よー！").queue();
                break;
            default:
                event.getChannel().sendMessage("まねしちゃお！w" + msg).queue();
                break;
        }
    }

    //コマンドの反応メソッド
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

    }
}