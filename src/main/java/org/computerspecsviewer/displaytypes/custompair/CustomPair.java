package org.computerspecsviewer.displaytypes.custompair;

public class CustomPair<A, B> {
    private final A objectA;
    private final B objectB;
    private boolean printParentheses;
    private String separator;

    public CustomPair(A objectA, B objectB) {
        this(objectA, objectB, false, " ");
    }

    public CustomPair(A objectA, B objectB, boolean printParentheses, String separator) {
        this.objectA = objectA;
        this.objectB = objectB;
        this.printParentheses = printParentheses;
        this.separator = separator;
    }

    @Override
    public String toString() {
        String start = "";
        String end = "";

        if(printParentheses) {
            start = "(";
            end = ")";
        }

        return start + objectA + separator + objectB + end;
    }
}
