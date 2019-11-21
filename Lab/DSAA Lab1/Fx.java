import java.util.Scanner;

public class Fx {
    public static final String alpha="bcdfghjklmnpqrstvxz";
    public static final String except="aeiouwy";
    /*public static int Combine(String combine, int [][] pairs){
        int number=0;
        for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                if(combine.contains(String.valueOf(alpha.charAt(i)))&&!combine.contains(String.valueOf(alpha.charAt(j)))){
                    number+=pairs[i][j];
                }else if(!combine.contains(String.valueOf(alpha.charAt(i)))&&combine.contains(String.valueOf(alpha.charAt(j)))){
                    number+=pairs[i][j];
                }
            }
        }
        return number;
    }*/
    public static int Beauty(String s){
       // String appear="";
        int [][] pairs=new int [19][19];
        //出现了哪几个字母(不包含a e i o u w y)
        //填充19X19的二维数组
        int len=s.length();
        for(int i=0;i<len-1;i++){
          /*  if(!except.contains(String.valueOf(s.charAt(i)))&&!appear.contains(String.valueOf(s.charAt(i)))) {
                appear +=String.valueOf(s.charAt(i));
            }*/
            if(!except.contains(String.valueOf(s.charAt(i)))&&!except.contains(String.valueOf(s.charAt(i+1)))){
                pairs[alpha.indexOf(String.valueOf(s.charAt(i)))][alpha.indexOf(String.valueOf(s.charAt(i+1)))]+=1;
                pairs[alpha.indexOf(String.valueOf(s.charAt(i+1)))][alpha.indexOf(String.valueOf(s.charAt(i)))]+=1;
            }
        }

        //可以用一个字符来储存组合——二进制,二进制的位数根据appear的长度
        //String r="";
       // String combine;
        int max=0;
        int number;
        int ed=(int)Math.pow(2,19)-1;
        /*for(int i=0;i<19;i++){
            for(int j=0;j<19;j++){
                System.out.printf("%d ",pairs[i][j]);
            }
            System.out.println();
        }*/
        for(int i=0;i<=ed;i++){
            //combine="";
            //r = Integer.toBinaryString(i);
            number=0;
            for(int j=18;j>=0;j--){
                for(int k=j-1;k>=0;k--){
                    //if(k==j)continue;
                if(((i&(1<<j))>0)&&((i&(1<<k))==0)||(((i>>j&1)==0)&&((i>>k&1)>0))){
                    number+=pairs[j][k];
                    //combine+=appear.charAt(j+appear.length()-r.length());
                }
                }
            }
            //number=Combine(combine,pairs);
            if(number>max) max=number;

        }

        return max;
    }
    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);

        int number=Integer.parseInt(in.nextLine());
        for(int i=1;i<=number;i++){
            System.out.println( Beauty(in.nextLine()));
        }

    }
}
