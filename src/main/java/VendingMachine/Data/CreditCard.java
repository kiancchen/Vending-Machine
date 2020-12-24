package VendingMachine.Data;

public class CreditCard {
    private String name;
    private String number;

    public CreditCard(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
