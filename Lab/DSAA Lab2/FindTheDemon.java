
public class FindTheDemon {
    public static void FindS(String men[],int demon,int left,int right){

        int mid=(left+right)/2;
        if(String.valueOf(demon).equals(men[mid])){
            System.out.println("YES");
            return;
        }
        if(left>=right||right==left+1){
            System.out.println("NO");
            return;
        }

        if(demon<Integer.parseInt(men[mid])){
            FindS(men,demon,left,mid);
        }else if(demon>Integer.parseInt(men[mid])){
            FindS(men,demon,mid,right);
        }


    }

    public static void main(String[] args)
    {
        String s[]={"1","2","3","4"};
        FindS(s,4, 1,3);

    }
}
