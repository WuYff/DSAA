import java.util.Scanner;

public class Fu {

    public static final String alpha="bcdfghjklmnpqrstvxz";
    public static final String except="aeiouwy";
    public static int Combine(String combine, int [][] pairs){
        int number=0;
        for(int i=0;i<pairs.length;i++){
            for(int j=0;j<pairs.length;j++){
                if(combine.contains(String.valueOf(alpha.charAt(i)))&&!combine.contains(String.valueOf(alpha.charAt(j)))){
                    number+=pairs[i][j];
                }else if(!combine.contains(String.valueOf(alpha.charAt(i)))&&combine.contains(String.valueOf(alpha.charAt(j)))){
                    number+=pairs[i][j];
                }
            }
        }
      return number;
    }

    //public static int pair(){}
    public static int Beauty(){
        String s;
        Scanner in =  new Scanner(System.in);
        s=in.nextLine();
        String appear="";
        int [][] pairs=new int [19][19];
        //出现了哪几个字母(不包含a e i o u w y)
        //填充19X19的二维数组
        for(int i=0;i<s.length()-1;i++){
            if(!except.contains(String.valueOf(s.charAt(i)))&&!appear.contains(String.valueOf(s.charAt(i)))) {
                appear += s.charAt(i);

            }
            if(!except.contains(String.valueOf(s.charAt(i)))&&!except.contains(String.valueOf(s.charAt(i+1)))){
                pairs[alpha.indexOf(s.charAt(i))][alpha.indexOf(s.charAt(i+1))]+=1;
            }
        }

        //可以用一个字符来储存组合——二进制,二进制的位数根据appear的长度
        String r="";
        String combine;
        int max=0;
        int number=0;
        for(int i=0;i<=Math.pow(2,appear.length())-1;i++){
           combine="";
           r = Integer.toBinaryString(i);
            for(int j=r.length()-1;j>=0;j--){
                if(String.valueOf(r.charAt(j)).equals("1")){
                   combine+=appear.charAt(j+appear.length()-r.length());
                }
            }

            number=Combine(combine,pairs);
           if(number>max) max=number;

        }

        return max;
    }


    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);
        int number=in.nextInt();
        for(int i=1;i<=number;i++){
            System.out.println( );
        }
    }
}
