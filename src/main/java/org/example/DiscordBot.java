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
    private static final String BOT_TOKEN = "とーくん！";

    public static void main(String[] args) {
        jda = JDABuilder.createDefault(BOT_TOKEN)
                .setRawEventsEnabled(true)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new DiscordBot())
                .setActivity(Activity.listening("私たちの関係"))
                .build();

        jda.updateCommands().queue();

    }

    //メッセージの反応メソッド
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        //ユーザーから受け取ったメッセージ ex : @サンプルbotちゃん こんにちは
        String msg = event.getMessage().getContentRaw();

        //botのメンションを取得 @サンプルbotちゃん
        String botId = event.getJDA().getSelfUser().getAsMention();

        //ユーザーから受け取ったメッセージがbotIdだったら処理を実行 ex : @サンプルbotちゃん　こんにちは → true
        if (msg.startsWith(botId)) {

            //ユーザーから受け取ったメッセージのbotIdを抜いて本文だけにする
            msg = msg.replace(botId, "").trim();

            //ユーザーから受け取った本文の値による分岐
            switch (msg) {
                case "ねえ聞いてる？":
                    event.getChannel().sendMessage("おーん").queue();
                    break;
                default:
                    event.getChannel().sendMessage("あ〜それな").queue();
                    break;
            }

        }
    }

    //コマンドの反応メソッド
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

    }
}