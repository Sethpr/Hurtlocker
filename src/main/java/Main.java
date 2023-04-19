import org.apache.commons.io.IOUtils;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception{
        String out = IOUtils.toString((new Main()).getClass().getClassLoader().getResourceAsStream("RawData.txt"));
        Arrays.stream(new String[]{"Milk","Bread","Cookies","Apples"}).forEach((n->process(Pattern.compile("(?i)(?<=:"+n.charAt(0)+".{3,6};price[:;])\\d.\\d+(?=;)").matcher(out).results().map(p->p.group()).collect(Collectors.toList()),n)));
        System.out.printf("\nErrors        	 	seen: %d times",Pattern.compile("[:@^*%;][:@^*%;]").matcher(out).results().count());
    }

    public static void process(List<String> list, String name){
        System.out.printf("\nname:%s       seen: %d times\n=============       =============\n"," ".repeat(8-name.length())+name,list.size());
        (new HashSet<>(list)).stream().forEach(p->System.out.printf("Price:   %s       seen: %d times\n-------------       -------------\n",p,Collections.frequency(list,p)));
    }
}