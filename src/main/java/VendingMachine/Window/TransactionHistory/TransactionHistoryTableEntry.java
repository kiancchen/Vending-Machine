package VendingMachine.Window.TransactionHistory;

public class TransactionHistoryTableEntry {

    String time;
    String itemSold;
    String moneyPaid;
    String changes;
    String paymentMethod;

    public TransactionHistoryTableEntry(String time, String itemSold, String moneyPaid, String changes, String paymentMethod) {
        this.changes = changes;
        this.time = time;
        this.itemSold = itemSold;
        this.moneyPaid = moneyPaid;
        this.paymentMethod = paymentMethod;
    }

    public String getTime() {
        return time;
    }

    public String getItemSold() {
        return itemSold;
    }

    public String getMoneyPaid() {
        return moneyPaid;
    }

    public String getChanges() {
        return changes;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
