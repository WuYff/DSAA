//debug 1: 2 3 1 5 2
import java.util.Scanner;
public class E{
    public static int FindMax(int[]a){
        int difference=a[0]-a[1];
        int minuend=a[1];
        int max;
        if(a[0]<a[1]) max=a[1];
        else max=a[0];
        for(int i=2;i<a.length;i++){
            if(a[i]<=minuend){
                minuend=a[i];
                difference=max-minuend;
            }else if(a[i]>=max){
                max=a[i];
            }else{
                if(max-a[i]>difference){
                    difference=max-a[i];
                    minuend=a[i];
                }
            }
        }
        return difference;
    }
    public static void main(String[] args) {
        // read the n points from a file
        Scanner in = new Scanner(System.in);
        int[]a;
        int size=0;
        int n=Integer.parseInt(in.nextLine());
        for (int i = 1; i <=n ; i++) {
            a=new int[in.nextInt()];
            for(int j=0;j<a.length;j++) a[j]=in.nextInt();
            System.out.println(FindMax(a));
        }
    }
}
