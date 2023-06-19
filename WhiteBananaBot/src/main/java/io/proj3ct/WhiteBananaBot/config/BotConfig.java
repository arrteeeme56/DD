package io.proj3ct.WhiteBananaBot.config;

import io.proj3ct.WhiteBananaBot.service.TelegramBot;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class BotConfig {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String token;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(botName, token);
    }
}
