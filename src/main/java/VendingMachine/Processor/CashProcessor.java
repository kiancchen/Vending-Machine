package VendingMachine.Processor;

import VendingMachine.DatabaseHandler;

import java.io.IOException;
import java.util.*;

public class CashProcessor {
    private Map<Double, Integer> cashMap;

    public CashProcessor() throws IOException {
        this.cashMap = DatabaseHandler.loadCashData();
    }

    public void setCashNumber(double value, int number) throws IOException {
        if (number >= 0) {
            cashMap.put(value, number);
            DatabaseHandler.saveCashData(this.cashMap);
        } else {
            throw new IllegalArgumentException("Number can't be less than zero.");
        }
    }

    public Map<Double, Integer> getChange(double amount) throws IOException {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount can't be less than zero.");
        }

        List<Double> cashes = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : this.cashMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                cashes.add(entry.getKey());
            }
        }
        cashes.sort(Collections.reverseOrder());

        Map<Double, Integer> changes = new HashMap<>();
        double changeAmount = 0;

        boolean found = false;
        for (double cash : cashes) {
            changeAmount += cash;
            if (changeAmount <= amount) {
                if (changes.containsKey(cash)) {
                    changes.put(cash, changes.get(cash) + 1);
                } else {
                    changes.put(cash, 1);
                }
                if (Math.abs(changeAmount - amount) <= 0.01) {
                    found = true;
                    break;
                }
            } else {
                changeAmount -= cash;
            }
        }
        if (found) {
            for (Map.Entry<Double, Integer> entry : changes.entrySet()) {
                this.cashMap.put(entry.getKey(), this.cashMap.get(entry.getKey()) - entry.getValue());
            }
            DatabaseHandler.saveCashData(this.cashMap);
            return changes;
        }
        return null;
    }

    public Map<Double, Integer> getCashMap() {
        return cashMap;
    }


}