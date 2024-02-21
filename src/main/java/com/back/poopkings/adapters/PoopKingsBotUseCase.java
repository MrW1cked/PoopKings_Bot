package com.back.poopkings.adapters;

import com.back.poopkings.models.database.PodiumMO;
import com.back.poopkings.models.database.PodiumPK;
import com.back.poopkings.models.RandomSentences;
import com.back.poopkings.repositories.PodiumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PoopKingsBotUseCase extends TelegramLongPollingBot {

    private final PodiumRepository repository;

    @Autowired
    public PoopKingsBotUseCase(PodiumRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onUpdateReceived(Update incommingMessage) {
        String messageText = incommingMessage.getMessage().getText(); //will store the User message
        String messageBody = ""; //will store the output message
        RandomSentences randomSentences = new RandomSentences();

        // We check if the incommingMessage has a message and the message has text
        if (incommingMessage.hasMessage() && incommingMessage.getMessage().hasText()) {
            messageBody = switch (messageText) {
                case "/poop", "/poop@PoopKings_bot" -> {
                    updateUserScorePlusOne(incommingMessage);
                    yield randomSentences.getRandomMessage();
                }
                case "/top", "/top@PoopKings_bot" -> getTopUsers(incommingMessage);
                case "/info", "/info@PoopKings_bot" -> getWelcomeMessage();
                default -> "";
            };
            sendMessage(messageBody, incommingMessage);
        }
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

    private String getWelcomeMessage() {
        return "Olá\\! Eu sou o Poop Bot\\. O bot que conta toda a merda que voçês fazem neste grupo \\(podes estar em varios grupos aos mesmo tempo\\) \n" +
                "Basta criares um grupo com os teus amigos e adicionar como participante o Bot @PoopKings \\(procurem nos contactos que ele existe\\)\n \n" +
                "A lista de comandos é simples\\. So tens de escrever uma mensagem normal com o texto:\n" +
                "/poop ou /poop@PoopKings\\_bot \\- Adiciona \\+1 \uD83D\uDCA9 ao teu total de cagadelas \n" +
                "/top ou /top@PoopKings\\_bot\\- Devolve a classificação dos cagões deste grupo \n" +
                "\n" +
                "Que começe a festa de \uD83D\uDCA9 \\!";
    }

    private String getTopUsers(Update incommingMessage) {
        Map<String, Integer> podium = getPodium(incommingMessage).stream().collect(
                Collectors.toMap(PodiumMO::getName, PodiumMO::getPoops));
        //the map aboce has a name and its score (poops). now we need to sort it by score and add to the message the User name, and its score (poops)
        return "*Nossa senhora tanta merda que vai neste grupo\\! \uD83D\uDCA9 \uD83D\uDCA9 \uD83D\uDCA9\n" +
                "Vejamos como estão as \uD83C\uDFC6classificações\uD83C\uDFC6 dos cagões deste grupo: \uD83D\uDC47* \n" +
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
