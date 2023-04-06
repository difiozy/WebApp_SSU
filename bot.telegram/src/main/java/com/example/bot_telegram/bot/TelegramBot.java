package com.example.bot_telegram.bot;

import com.example.bot_telegram.rabit.RabbitService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${spring.telegram.bot.name}")
    private String botName;

    @Value("${spring.telegram.bot.token}")
    private String botToken;
    private final RabbitService rabbitService;
    private final Pattern onlyAlf = Pattern.compile("^[A-z0-9_]{3,32}$");

    @Autowired
    public TelegramBot(RabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем из объекта сообщение пользователя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response);

                //Отправка в чат
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String parseMessage(String textMsg) {
        String response;

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if (textMsg.equals("/start"))
            response = "Введи логин (от 3 до 32 симовлов на английском языке), чтобы получить ключ от приложения";
        else if (onlyAlf.matcher(textMsg).find()) {
            UserNew userNew = generateToken(textMsg);

            response = String.format("""
                    Ваши учетные даные:
                    login: %s
                    token: %s
                    """, userNew.getName(), userNew.getToken());
        } else
            response = "Сообщение не распознано необходимо ввести логин длиной от 3 до 32 символов английского алфавита, цифр или \"_\" без пробелов и переноса строк";

        return response;
    }

    private UserNew generateToken(String userName) {
        UserNew userNew = new UserNew()
                .setName(userName)
                .setToken(UUID.randomUUID().toString());
        rabbitService.userToken(userNew);
        return userNew;
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class UserNew {
        private String name;
        private String token;
    }
}
