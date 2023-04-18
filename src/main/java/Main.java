import org.apache.commons.io.IOUtils;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static final String[] name = {"Milk", "Bread","Cookies","Apples"};

    public static void main(String[] args) throws Exception{
        String output = IOUtils.toString((new Main()).getClass().getClassLoader().getResourceAsStream("RawData.txt"));
        StringBuilder sb = new StringBuilder("\nErrors        	 	 seen: " +Pattern.compile("[:@^*%;][:@^*%;]").matcher(output).results().count() +" times");
        for (int i = name.length-1; i >= 0; i--) {
            process(Pattern.compile("(?i)(?<=name:"+name[i].charAt(0)+".{"+(name[i].length() - 1)+"};price[:;])[0-9].[0-9][0-9]+(?=;)").matcher(output).results().map((p) -> p.group()).collect(Collectors.toList()), name[i], sb);
        }
        System.out.println(sb);
    }

    public static void process(List<String> list, String name, StringBuilder sb){
        (new HashSet<>(list)).stream().forEach((p)->sb.insert(0,String.format("Price:   %s       seen: %d times\n-------------       -------------\n",p, Collections.frequency(list, p))));
        sb.insert(0,"\nname:"+ " ".repeat(8-name.length()) + name+"       seen: " +list.size()+ " times\n=============       =============\n");
    }
}
