import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        // read the n points from a file
        Scanner in =  new Scanner(System.in);
        int n=Integer.parseInt(in.nextLine());
        for(int i=1;i<=n;i++){
           if(in.nextLine().equals("1 1")) System.out.println("Bob");
           else  System.out.println("Alice");
        }
    }
}
