package VendingMachine.Window;

public class CashTableEntry {
    private double cashType ;
    private int amount;



    public CashTableEntry(double cashType, int amount) {
        this.cashType=cashType;
        this.amount=amount;

    }
    public double getCashType() {
        return cashType;
    }

    public int getAmount() {
        return amount;
    }

}
