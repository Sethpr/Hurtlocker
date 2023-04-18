import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static final String[] pats = {"(?<=name:milk;price[:;])[0-9].[0-9][0-9]+(?=;)", "(?<=name:bread;price[:;])[0-9].[0-9][0-9]+(?=;)", "(?<=name:c[0o][0o]kies;price[:;])[0-9].[0-9][0-9]+(?=;)", "(?<=name:apples;price[:;])[0-9].[0-9][0-9]+(?=;)"};
    static final String[] name = {"Milk", "Bread","Cookies","Apples"};
    static StringBuilder sb = new StringBuilder();
    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        for (int i = 0; i < pats.length; i++) {
            process(Pattern.compile(pats[i], Pattern.CASE_INSENSITIVE).matcher(output).results().map((p) -> p.group()).collect(Collectors.toList()),name[i]);
        }
        sb.append("\nErrors        	 	 seen: " +Pattern.compile("[:@^*%;][:@^*%;]", Pattern.CASE_INSENSITIVE).matcher(output).results().count() +" times");
        System.out.println(sb);
    }

    public static void process(List<String> list, String name){
        sb.append("\nname:"+ " ".repeat(8-name.length()) + name+"       seen: " +list.size()+ " times\n=============       =============\n");
        (new HashSet<>(list)).stream().forEach((p)->sb.append(String.format("Price:   %s       seen: %d times\n-------------       -------------\n",p, Collections.frequency(list, p))));
    }
}
