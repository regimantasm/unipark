package helper.enums;

public enum Airport {

    VILNIUS_AIRPORT(0),
    KAUNAS_AIRPORT(1),
    RIGA_AIRPORT(2);

    private int numeration;

    Airport(int numeration) {
        this.numeration = numeration;
    }

    public int getNumeration() {
        return numeration;
    }
}
