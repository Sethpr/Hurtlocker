import org.apache.commons.io.IOUtils;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception{
        String output = IOUtils.toString((new Main()).getClass().getClassLoader().getResourceAsStream("RawData.txt"));
        Arrays.stream(new String[]{"Milk","Bread","Cookies","Apples"}).forEach((n->process(Pattern.compile("(?i)(?<=name:"+n.charAt(0)+".{"+(n.length()-1)+"};price[:;])\\d.\\d\\d(?=;)").matcher(output).results().map((p)->p.group()).collect(Collectors.toList()),n)));
        System.out.println("\nErrors        	 	seen: "+Pattern.compile("[:@^*%;][:@^*%;]").matcher(output).results().count()+" times");
    }

    public static void process(List<String> list, String name){
        System.out.print("\nname:"+ " ".repeat(8-name.length())+name+"       seen: "+list.size()+ " times\n=============       =============\n");
        (new HashSet<>(list)).stream().forEach((p)->System.out.print(String.format("Price:   %s       seen: %d times\n-------------       -------------\n",p,Collections.frequency(list,p))));
    }
}