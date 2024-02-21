package com.back.poopkings;

import com.back.poopkings.adapters.PoopKingsBotUseCase;
import com.back.poopkings.repositories.PodiumRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class PoopKingsApplication {

    public static void main(String[] args) throws TelegramApiException {
        ApplicationContext context = SpringApplication.run(PoopKingsApplication.class, args);

        // Initialize Api Context
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

		// Obtain PodiumRepository instance from Spring application context
        PodiumRepository podiumRepository = context.getBean(PodiumRepository.class);


        // Register our bot
        try {
            telegramBotsApi.registerBot(new PoopKingsBotUseCase(podiumRepository));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
