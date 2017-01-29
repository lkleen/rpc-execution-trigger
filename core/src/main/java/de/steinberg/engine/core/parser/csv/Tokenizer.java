package de.steinberg.engine.core.parser.csv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lars on 29.01.2017.
 */
public class Tokenizer {

    public List<String> tokenize(String str) {
        List<String> tokens = new ArrayList<>();
        tokenize(tokens, str);
        return tokens;
    }

    private void tokenize(List<String> list, String str) {
        int begin = str.indexOf("\"") + 1;
        int end   = str.substring(begin).indexOf("\"") + begin;
        if (begin != -1 && end != -1) {
            int nextSeparatorIndex = str.substring(end).indexOf(",") + end;
            list.add(str.substring(begin, end));
            tokenize(list, str.substring(nextSeparatorIndex));
        }
    }


}
