import java.util.Scanner;

public class B{
    public static void main(String[] args) {
      //(a - b) % p = (a % p - b % p) % p
      // (a * b) % p = (a % p * b % p) % p
        Scanner in = new Scanner(System.in);
        int number =in.nextInt();
        long n;
        long x;
        long sum;
        long modb;
        long a;
        long b;
        for (int i = 1; i <= number; i++) {
           n=in.nextInt();
           //n=a*19+b;
           a=n/19;
           b=n%19;
           //System.out.println("a="+a);
          //  System.out.println("b="+b);
           //3^n=3^19a*3^b
           //(3^n)%p=((3^19a)%p*(3^b)%p)%p;
           //(3^19a)%p=(3^19*3^19*3^19……)%p
             sum= 1;
             x= (int)Math.pow(3,19)% 1000000007;
            for(int j = 1; j <= a; j++)
            {
                sum = (sum*x)%1000000007;
            }
            modb=(int)Math.pow(3,b)%1000000007;
           // System.out.println("sum="+sum);
            //System.out.println("modb="+modb);
            //System.out.println("sum*modb="+sum*modb);
            System.out.println(( ( ( sum%1000000007 )*( modb%1000000007 ) )%1000000007   -1)%1000000007);

        }

    }
}
