package src.domain.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Group {

    public static void main(String[] args) {
        String line = "this.setPageNoOya(info.getPageNoOya());";
        Pattern pattern = Pattern.compile("info.get.*(?=\\()");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        
        String item = matcher.group();
        System.out.println(item);
    }
}
