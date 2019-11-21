import java.util.Scanner;

public class F {

    //这个方法是绝没问题的
    public static int pair(String s){
        int number=0;
        String x="aeiouwy";
        char one='.';
        char two='.';
        for(int i=0;i<s.length()-1;i++){
            one=s.charAt(i);
            two=s.charAt(i+1);
            if(!x.contains(String.valueOf(one))&&!x.contains(String.valueOf(two))){
                if(Character.isUpperCase(one)&&Character.isLowerCase(two))
                    number+=1;
                else if(Character.isUpperCase(two)&&Character.isLowerCase(one))
                    number+=1;
            }
        }
        return number;

    }


    //思考这个方法
    public static int Beauty(String s){
        String z="";
        String origin=s;
        int max=0;
        String x="aeiouwy";
        String y="bcdfghjklmnpqrstvxz";
        String alpha="";
        String eachAlpha="";
        int number=0;
        for(int i=0;i<s.length();i++){
            s=origin;
            eachAlpha="";
            if(!x.contains(String.valueOf(s.charAt(i)))&&!alpha.contains(String.valueOf(s.charAt(i)))){
                alpha+=s.charAt(i);
                for(int j=i;j<s.length();j++){
                    if(String.valueOf(s.charAt(j)).equals(String.valueOf(s.charAt(i)))){
                        if(j-1>0&&!x.contains(String.valueOf(s.charAt(j-1)))&&!eachAlpha.contains(String.valueOf(s.charAt(j-1)))&&!String.valueOf(s.charAt(j-1)).equals(s.charAt(i)))
                            eachAlpha+=s.charAt(j-1);
                        if(j+1<s.length()&&!x.contains(String.valueOf(s.charAt(j+1)))&&!eachAlpha.contains(String.valueOf(s.charAt(j+1)))&&!String.valueOf(s.charAt(j+1)).equals(s.charAt(i)))
                            eachAlpha+=s.charAt(j+1);
                      }
                    }

                for(int j=0;j<y.length();j++){
                    if(!eachAlpha.contains(String.valueOf(y.charAt(j)))&&s.contains(String.valueOf(y.charAt(j))))
                       s= s.replace(String.valueOf(y.charAt(j)),String.valueOf(y.charAt(j)).toUpperCase());
                }



               number= pair(s);
                if(number>max) max=number;

                 s= s.replace(String.valueOf(origin.charAt(i)),String.valueOf(origin.charAt(i)).toUpperCase());

                number= pair(s);
                if(number>max) max=number;

                }
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


