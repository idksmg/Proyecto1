package org.example;
import java.util.ArrayList;

public class AnalizadorSintactico {
    private ArrayList<String> input;
    private int currentTokenIndex;

    public AnalizadorSintactico(ArrayList<String> input) {
        this.input = input;
        this.currentTokenIndex = 0;
    }

    public Object parseExpression() {
        String token = this.getNextToken();
        if (token == null) {
            return null;
        }

        if (token.equals("(")) {
            return parseList();
        } else if (token.equals(")")) {
            throw new RuntimeException("Unexpected ')'");
        } else {
            return parseAtom(token);
        }
    }

    private ArrayList<Object> parseList() {
        ArrayList<Object> list = new ArrayList<Object>();

        while (true) {
            String token = this.getNextToken();
            if (token == null) {
                throw new RuntimeException("Missing ')'");
            } else if (token.equals(")")) {
                return list;
            } else {
                this.currentTokenIndex--;
                Object expr = parseExpression();
                list.add(expr);
            }
        }
    }

    private Object parseAtom(String token) {
        try {
            int value = Integer.parseInt(token);
            return value;
        } catch (NumberFormatException e) {
            return token;
        }
    }

    private String getNextToken() {
        while (this.currentTokenIndex < this.input.size()) {
            String token = this.input.get(this.currentTokenIndex);
            this.currentTokenIndex++;

            if (!token.equals(" ")) {
                return token;
            }
        }

        return null;
    }
}
