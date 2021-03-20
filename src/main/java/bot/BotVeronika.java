package bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotVeronika extends TelegramLongPollingBot {
    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        if(message.equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId( update.getMessage().getChatId());
            sendMsg("Здравствуйте, " + update.getMessage().getFrom().getFirstName() + "!", update.getMessage().getChatId());
            sendMessage.setReplyMarkup(getAboutUsReplyKeyboard());
        }
        if(message.equals("/help")) {

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId( update.getMessage().getChatId());
            sendMessage.setText("Команды для работы с ботом: /help - список команд; /start - начало работы с ботом");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                System.out.println("Exception: " + e.toString());
            }
        }
        //else{
        //sendMsg(update.getMessage().getText(), update.getMessage().getChatId());
        //}
    }

    public ReplyKeyboard getAboutUsReplyKeyboard() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowOne = new KeyboardRow();
        KeyboardRow keyboardRowTwo = new KeyboardRow();
        KeyboardRow keyboardRowThree = new KeyboardRow();
        keyboardRowOne.add(new KeyboardButton("Преподаватели"));
        keyboardRowOne.add(new KeyboardButton("Отзывы"));
        keyboardRowTwo.add(new KeyboardButton("Результаты"));
        keyboardRowTwo.add(new KeyboardButton("Программа"));
        keyboardRowThree.add(new KeyboardButton("Главное меню"));
        keyboardRows.add(keyboardRowThree);
        keyboardRows.add(keyboardRowOne);
        keyboardRows.add(keyboardRowTwo);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    public InlineKeyboardMarkup getSignUpToTrialInlineMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonSignUp = new InlineKeyboardButton().setText("Записаться на пробное занятие");
        buttonSignUp.setCallbackData("signUpToTrial");
        List<InlineKeyboardButton> firstKeyboardButtonRow = new ArrayList<>();
        firstKeyboardButtonRow.add(buttonSignUp);
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        keyboardRows.add(firstKeyboardButtonRow);
        inlineKeyboardMarkup.setKeyboard(keyboardRows);
        return inlineKeyboardMarkup;
    }

    public synchronized void sendMsg(String s, Long chat_id) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */

    @Override
    public String getBotUsername() {
        return "sportReporterBot";
    }

    @Override
    public String getBotToken() {
        return "1658416657:AAEZXfY-33KlPmgc6VaHlU8tzJ0C_qwHmpY";
    }
}