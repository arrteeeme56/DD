package io.proj3ct.WhiteBananaBot.service;

import io.proj3ct.WhiteBananaBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    BotConfig config;

    public TelegramBot() {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
        }

    @Override
    public String getBotToken() { return config.getToken(); }

    @Override
    public void onUpdateReceived (Update update){

        if(update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/com":

                    try {
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    default:
                    try {
                        sendMessage(chatId, "Sorry, command was not recognized ");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
            }
        }

    }
    private void startCommandReceived(long chatId, String name) throws TelegramApiException {

        String answer = "Hi, " + name + ", nice name";

        sendMessage(chatId, answer);

    }

    private void sendMessage(long chatId, String textToSend) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        }
        catch (TelegramApiException e) {


        }
    }

}