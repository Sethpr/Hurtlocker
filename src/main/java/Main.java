import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static final String[] pats = {"(?<=name:milk;price[:;])[0-9].[0-9][0-9]+(?=;)", "(?<=name:bread;price[:;])[0-9].[0-9][0-9]+(?=;)", "(?<=name:c[0o][0o]kies;price[:;])[0-9].[0-9][0-9]+(?=;)", "(?<=name:apples;price[:;])[0-9].[0-9][0-9]+(?=;)"};
    static final String[] name = {"Milk", "Bread","Cookies","Apples"};
    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pats.length; i++) {
            sb.append(process(getList(pats[i], output),name[i]));
        }
        sb.append("Errors        	 	 seen: " +Pattern.compile("[:@^*%;][:@^*%;]", Pattern.CASE_INSENSITIVE).matcher(output).results().count() +" times");
        System.out.println(sb);
    }

    public static String process(List<String> list, String name){
        StringBuilder sb = new StringBuilder("name:"+ pad(name)+"       seen: " +list.size()+ " times\n=============       =============\n");
        (new HashSet<>(list)).stream().forEach((p)->{sb.append(String.format("Price:   %s       seen: %d times\n-------------       -------------\n",p, Collections.frequency(list, p)));});
        return sb.append("\n").toString();
    }

    //I wanted to put these in line to reduce my line count, but I would like my code to be SOMEWHAT readable. like at least a little

    public static List<String> getList(String pattern, String toMatch){
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(toMatch).results().map((p) -> p.group()).collect(Collectors.toList());
    }

    public static String pad(String word){
        return " ".repeat(8-word.length()) + word;
    }
}
