import java.util.Scanner;
//discard version
public class FightAgainstDemon {
    public static int key=-1;
    public static void PairSwords(int men[],int theOther,int left,int right){

        int mid=(left+right)/2;
        if(theOther==men[mid]){
            key=mid;
            return;
        }
        if(left>=right||right==left+1){
            key=-1;
            return;
        }

        if(theOther<men[mid]){
            PairSwords(men,theOther,left,mid-1);
        }else if(theOther>men[mid]){
            PairSwords(men,theOther,mid+1,right);
        }
        return;
    }

   /* public static void Fight(String Swords[],int target,int nSwords){
        int theOther;
        int One;
        int nPairs=0;
        for(int i=0;i< nSwords-1;i++){
            theOther=target-Integer.parseInt(Swords[i]);
            PairSwords(Swords,theOther,i+1, nSwords);
            if(key==-1){
                continue;
            }else{
                for(int j=key;j>0;j--){
                    if(String.valueOf(Swords[j]).equals(Swords[key]))
                        nPairs+=1;
                    else break;
                    }

                for(int j=key+1;j< nSwords;j++){
                    if(String.valueOf(Swords[j]).equals(Swords[key]))
                        nPairs+=1;
                    else break;
                }

                }
            }

            System.out.println(nPairs);
        }*/

    public static void Fight(int Swords[],int target,int nSwords){
        int theOther;
        int One;
        int nPairs=0;
        for(int i=0;i< nSwords-1;i++){
            theOther=target-Swords[i];
            PairSwords(Swords,theOther,i+1, nSwords-1);
            if(key==-1){
                continue;
            }else{
                for(int j=key;j>i;j--){
                    if(Swords[j]==Swords[key])
                        nPairs+=1;
                    else break;
                }

                for(int j=key+1;j< nSwords;j++){
                    if(Swords[j]==Swords[key])
                        nPairs+=1;
                    else break;
                }

            }
        }

        System.out.println(nPairs);


    }

    public static void main(String[] args)
    {
       Scanner in =new Scanner(System.in);
        int nTest=in.nextInt();
        int nSword;
        int target;
        //String Swords[];
        int Swords[];
        for(int i=1;i<=nTest;i++){
            nSword=in.nextInt();
            target=in.nextInt();
            Swords=new int[nSword];
            for(int j=0;j<nSword;j++){
                Swords[j]=in.nextInt();
            }
            Fight(Swords,target, nSword);

        }
    }
}
