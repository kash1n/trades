package ru.kashin;

public class TradeInfo {
    Long TRADENO;      // - номер сделки
    Integer TRADETIME; // - время
    String SECBOARD;   // - площадка
    String SECCODE;    // - код ценной бумаги
    Double PRICE;      // - цена
    Long VOLUME;       // - кол-во бумаг
    Double ACCRUEDINT; // - НКД (для облигаций)
    Double YIELD;      // - доходность (для облигаций)
    Double VALUE;      // - сумма сделки.

    public TradeInfo(String line) {
        String[] tokens = line.split("\t");
        int ti = 0;
        TRADENO = Long.parseLong(tokens[ti++]);
        TRADETIME = Integer.parseInt(tokens[ti++]);
        SECBOARD = tokens[ti++];
        SECCODE = tokens[ti++];
        PRICE = Double.parseDouble(tokens[ti++]);
        VOLUME = Long.parseLong(tokens[ti++]);
        ACCRUEDINT = Double.parseDouble(tokens[ti++]);
        YIELD = Double.parseDouble(tokens[ti++]);
        VALUE = Double.parseDouble(tokens[ti]);
    }

    public static TradeInfo fromString(String line) {
        return new TradeInfo(line);
    }

    public String getSECCODE () {
        return SECCODE;
    }

    public Double getVALUE () {
        return VALUE;
    }
}
