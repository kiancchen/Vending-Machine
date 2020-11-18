package VendingMachine.Window.TransactionHistory;

public class TransactionHistoryTableEntry {

    String time;
    String moneyPaid;
    String changes;
    String paymentMethod;

    public TransactionHistoryTableEntry(String time, String moneyPaid, String changes, String paymentMethod) {
        this.changes = changes;
        this.time = time;
        this.moneyPaid = moneyPaid;
        this.paymentMethod = paymentMethod;
    }

    public String getTime() {
        return time;
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
