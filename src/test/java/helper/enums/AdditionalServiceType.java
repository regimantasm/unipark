package helper.enums;

public enum AdditionalServiceType {

    FAST_CHECK_IN(0),
    LUGGAGE_PACKING(1);

    private int numeration;

    AdditionalServiceType(int numeration) {
        this.numeration = numeration;
    }

    public int getNumeration() {
        return numeration;
    }
}