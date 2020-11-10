package VendingMachine.Window.CashManagement;

public class CashTableEntry {
    private String cashType;
    private String amount;


    public CashTableEntry(String cashType, String amount) {
        this.cashType = cashType;
        this.amount = amount;

    }

    public String getCashType() {
        return cashType;
    }

    public String getAmount() {
        return amount;
    }

}
