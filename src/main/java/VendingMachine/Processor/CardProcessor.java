package VendingMachine.Processor;

import VendingMachine.Data.CreditCard;
import VendingMachine.DatabaseHandler;

import java.io.IOException;
import java.util.List;

public class CardProcessor {
    private final List<CreditCard> cards;
    private static CardProcessor cardProcessor;

    private CardProcessor() throws IOException {
        cards = DatabaseHandler.loadCreditCards();
    }

    public static CardProcessor getInstance() throws IOException {
        if (cardProcessor == null) {
            cardProcessor = new CardProcessor();
        }
        return cardProcessor;
    }

    public CreditCard validateCard(String name, String number) {
        for (CreditCard card : cards) {
            if (card.getName().equals(name) && card.getNumber().equals(number)) {
                return card;
            }
        }
        return null;
    }
}
