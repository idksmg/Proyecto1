package org.example;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {
    private ArrayList<String> input;
    private int currentTokenIndex;
    private Pattern tokenPattern;

    public AnalizadorLexico(ArrayList<String> input) {
        this.input = input;
        this.currentTokenIndex = 0;
        this.tokenPattern = Pattern.compile("\\(|\\)|[^\\s()]+");
    }

    public String getNextToken() {
        if (this.currentTokenIndex >= this.input.size()) {
            return null;
        }

        String token = this.input.get(this.currentTokenIndex);
        this.currentTokenIndex++;

        Matcher m = this.tokenPattern.matcher(token);
        if (m.matches()) {
            return token;
        } else {
            // Handle the case where a token contains multiple sub-tokens
            ArrayList<String> subTokens = new ArrayList<String>();
            m = this.tokenPattern.matcher(token);
            while (m.find()) {
                subTokens.add(m.group());
            }
            this.currentTokenIndex--;
            this.input.addAll(this.currentTokenIndex + 1, subTokens.subList(1, subTokens.size()));
            return subTokens.get(0);
        }
    }
}
