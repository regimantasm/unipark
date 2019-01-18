package helper.enums;

public enum CheckboxType {

    TERMS_AND_CONDITIONS(0),
    NEWS_LETTER(2),
    GET_INVOICE(3);

    private int numeration;

    CheckboxType(int numeration) {
        this.numeration = numeration;
    }

    public int getNumeration() {
        return numeration;
    }
}