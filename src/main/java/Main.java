import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static final String[] name = {"Milk", "Bread","Cookies","Apples"};
    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(name).forEach(n->sb.append(process(getList(n, output),n)));
        sb.append("Errors        	 	 seen: " +Pattern.compile("[:@^*%;][:@^*%;]", Pattern.CASE_INSENSITIVE).matcher(output).results().count() +" times");
        System.out.println(sb);
    }

    public static String process(List<String> list, String name){
        StringBuilder sb = new StringBuilder("name:"+ pad(name)+"       seen: " +list.size()+ " times\n=============       =============\n");
        (new HashSet<>(list)).stream().forEach((p)->{sb.append(String.format("Price:   %s       seen: %d times\n-------------       -------------\n",p, Collections.frequency(list, p)));});
        return sb.append("\n").toString();
    }

    //I wanted to put these in line to reduce my line count, but I would like my code to be SOMEWHAT readable. like at least a little

    public static List<String> getList(String name, String toMatch){
        return Pattern.compile("(?i)(?<=name:"+name.charAt(0)+".{"+(name.length() - 1)+"};price[:;])[0-9].[0-9][0-9]+(?=;)").matcher(toMatch).results().map((p) -> p.group()).collect(Collectors.toList());
    }

    public static String pad(String word){
        return " ".repeat(8-word.length()) + word;
    }
}