import java.util.Set;
import java.util.HashSet;

public class taet {

    public static void main(String[] args) {
        String test[]="bcdfghjklmnpqrstvxz".split("");
        String t="bcdfghjklmnpqrstvxz";
       long start1=System.currentTimeMillis();   //获取开始时间
        System.out.println(test[18]);
        long end1=System.currentTimeMillis(); //获取结束时间

        long start2=System.currentTimeMillis();   //获取开始时间
        System.out.println(t.charAt(18));
        long end2=System.currentTimeMillis(); //获取结束时间
       // System.out.println("程序运行时间： "+(end1-start1)+"ms");
        System.out.println("访问字符"+(end2-start2)+"ms");
    }
}