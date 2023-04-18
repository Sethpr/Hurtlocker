import org.apache.commons.io.IOUtils;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception{
        String output = IOUtils.toString((new Main()).getClass().getClassLoader().getResourceAsStream("RawData.txt"));
        StringBuilder sb = new StringBuilder("\nErrors        	 	seen: "+Pattern.compile("[:@^*%;][:@^*%;]").matcher(output).results().count()+" times");
        Arrays.stream(new String[]{"Apples","Cookies","Bread","Milk"}).forEach((n->process(Pattern.compile("(?i)(?<=name:"+n.charAt(0)+".{"+(n.length()-1)+"};price[:;])\\d.\\d\\d(?=;)").matcher(output).results().map((p)->p.group()).collect(Collectors.toList()),n,sb)));
        System.out.println(sb);
    }

    public static void process(List<String> list, String name, StringBuilder sb){
        (new HashSet<>(list)).stream().forEach((p)->sb.insert(0,String.format("Price:   %s       seen: %d times\n-------------       -------------\n",p,Collections.frequency(list,p))));
        sb.insert(0,"\nname:"+ " ".repeat(8-name.length())+name+"       seen: "+list.size()+ " times\n=============       =============\n");
    }
}
