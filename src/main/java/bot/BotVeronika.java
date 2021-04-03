package bot;

import lombok.SneakyThrows;
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
import telegram.sql.PostgreSQLJDBC;

import java.util.ArrayList;
import java.util.List;

public class BotVeronika extends TelegramLongPollingBot {

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        int id = update.getMessage().getChatId().intValue();
        String firstname = update.getMessage().getFrom().getFirstName();
        String lastname = update.getMessage().getFrom().getLastName();

        String message = update.getMessage().getText();
        if(message.equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId( update.getMessage().getChatId());
            sendMsg("Здравствуйте, " + update.getMessage().getFrom().getFirstName() + "!", update.getMessage().getChatId());
        }
        if(message.equals("/help")) {
            comSQL.insertData(id, firstname, lastname);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMsg("Команды для работы с ботом: /help - список команд; /start - начало работы с ботом", update.getMessage().getChatId());
        }
        if(message.equals("/news")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId( update.getMessage().getChatId());
            sendMsg("Функции:", update.getMessage().getChatId());;
            sendMessage.setReplyMarkup(getAboutUsReplyKeyboard());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                System.out.println("Exception: " + e.toString());
            }
        }
    }

    telegram.sql.PostgreSQLJDBC comSQL = new PostgreSQLJDBC();

    public ReplyKeyboard getAboutUsReplyKeyboard() {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRowOne = new KeyboardRow();
        KeyboardRow keyboardRowTwo = new KeyboardRow();
        keyboardRowOne.add(new KeyboardButton("Футбол"));
        keyboardRowOne.add(new KeyboardButton("Хоккей"));
        keyboardRowTwo.add(new KeyboardButton("Мировые новости"));
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

    public synchronized void sendMsg(String s, long chat_id) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //log.info( "Exception: " + e.toString());
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