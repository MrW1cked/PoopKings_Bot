package com.back.poopkings.adapters;

import com.back.poopkings.helpers.Messages;
import com.back.poopkings.models.database.LanguageMO;
import com.back.poopkings.models.database.PodiumMO;
import com.back.poopkings.models.database.PodiumPK;
import com.back.poopkings.repositories.LanguageRepository;
import com.back.poopkings.repositories.PodiumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PoopKingsBotUseCase extends TelegramLongPollingBot {

    private String locale;
    private final PodiumRepository repository;
    private final LanguageRepository languageRepository;
    private Messages messages = new Messages();

    @Autowired
    public PoopKingsBotUseCase(PodiumRepository repository, LanguageRepository languageRepository) {
        this.repository = repository;
        this.languageRepository = languageRepository;
    }

    @Override
    public void onUpdateReceived(Update incommingMessage) {
        String messageText = incommingMessage.getMessage().getText(); //will store the User message
        String messageBody = ""; //will store the output message
        Messages messages = new Messages();

        // We check if the incommingMessage has a message and the message has text
        if (incommingMessage.hasMessage() && incommingMessage.getMessage().hasText()) {
            locale = checkGroupLanguage(incommingMessage);
            messageBody = switch (messageText) {
                case "/poop", "/poop@PoopKings_bot" -> {
                    updateUserScorePlusOne(incommingMessage);
                    yield messages.getRandomMessage(locale);
                }
                case "/top", "/top@PoopKings_bot" -> getTopUsers(incommingMessage, locale);
                case "/info", "/info@PoopKings_bot" -> getWelcomeMessage(locale);
                case "/language_es", "/language_es@PoopKings_bot" -> setUpGoupLanguage(incommingMessage, "es");
                case "/language_en", "/language_en@PoopKings_bot" -> setUpGoupLanguage(incommingMessage, "en");
                case "/language_pt", "/language_pt@PoopKings_bot" -> setUpGoupLanguage(incommingMessage, "pt");
                default -> "";
            };
            sendMessage(messageBody, incommingMessage);
        }
    }


    private String checkGroupLanguage(Update incommingMessage) {
        languageRepository.findById(incommingMessage.getMessage().getChatId()).ifPresentOrElse(
                language -> locale = language.getLanguage(),
                () -> locale = setUpGoupLanguage(incommingMessage, "en")
        );
        return locale;
    }

    private String setUpGoupLanguage(Update incommingMessage, String locale) {
        LanguageMO language = LanguageMO.builder()
                .chatId(incommingMessage.getMessage().getChatId())
                .groupName(incommingMessage.getMessage().getChat().getTitle())
                .language(locale)
                .build();
        languageRepository.save(language);
        return locale + "✅";

    }

    private void sendMessage(String messageBody, Update incommingMessage) {
        SendMessage message = SendMessage.builder()
                .text(messageBody)
                .parseMode("MarkdownV2")// Use Markdown so that we can use bolds and Italics if needed
                .chatId(incommingMessage.getMessage().getChatId())
                .replyToMessageId(incommingMessage.getMessage().getMessageId())
                .build();
        if (!messageBody.isEmpty()) {
            try {
                execute(message); // Sending our message object to User
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getWelcomeMessage(String locale) {
        return messages.getInfoMessage(locale);
    }

    private String getTopUsers(Update incommingMessage, String locale) {
        Map<String, Integer> podium = getPodium(incommingMessage).stream().collect(
                Collectors.toMap(PodiumMO::getName, PodiumMO::getPoops));
        //the map aboce has a name and its score (poops). now we need to sort it by score and add to the message the User name, and its score (poops)
        return messages.getTopMessage(locale) +
                podium.entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .map(entry -> entry.getKey() +
                                ": " +
                                entry.getValue() + " \uD83D\uDCA9")
                        .collect(Collectors.joining("\n"));
    }

    @Override
    public String getBotUsername() {
        //ENV VARIABLES
        // If bot Username is @MyAmazingBot, it must return 'MyAmazingBot'
        return System.getenv("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return System.getenv("BOT_TOKEN");
    }

    public void updateUserScore(PodiumMO podiumMO) {
        repository.save(podiumMO);
    }

    public void updateUserScorePlusOne(Update incommingMessage) {
        PodiumMO podiumMO = createUserIfDontExists(incommingMessage);
        podiumMO.setPoops(podiumMO.getPoops() + 1);
        log.info(podiumMO.getName() + ": " + podiumMO.getPoops());
        updateUserScore(podiumMO);
    }

    public PodiumMO createUserIfDontExists(Update update) {
        try {
            return getUser(update);
        } catch (Exception e) {
            PodiumMO podiumMO = PodiumMO.builder()
                    .id(update.getMessage().getFrom().getId())
                    .chatId(update.getMessage().getChatId())
                    .name(update.getMessage().getFrom().getFirstName())
                    .poops(0)
                    .build();
            updateUserScore(podiumMO);
            return podiumMO;
        }
    }

    public PodiumMO getUser(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        Long chatId = update.getMessage().getChatId();

        PodiumPK pk = PodiumPK.builder()
                .id(userId)
                .chatId(chatId)
                .build();
        return repository.findById(pk).orElseThrow();
    }

    public List<PodiumMO> getPodium(Update update) {
        return repository.findAllByChatId(update.getMessage().getChatId());
    }
}
