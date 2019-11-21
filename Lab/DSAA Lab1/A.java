import java.util.*;

public class A{
    public static void A(String []set,String[] article){
        for(int j=0;j<set.length;j++){
            for(int m=0;m<article.length;m++){
                if(article[m].equals(set[j])){
                    System.out.println("Appeared");
                    return;
                }
            }
        }
        System.out.println("Not appeared");
    }
    public static void main(String[] args) {
        // read the n points from a file
        String [] article;
        String [] set;
        int la=0;
        int na=0;
        Scanner in = new Scanner(System.in);
        int number=Integer.parseInt(in.nextLine());
        for(int i=1;i<=number;i++){
           na=Integer.parseInt(in.nextLine());
          set=new String[na];
            while(na>0){
                set[na-1]=in.nextLine().toLowerCase();
                na--;
            }
            la=Integer.parseInt(in.nextLine());
            article=in.nextLine().toLowerCase().split(" ");
            A(set,article);
        }

    }
}
