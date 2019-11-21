import java.util.Arrays;

public class test {


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String str[] = { "A", "B", "C", "D", "E" };
        int nCnt = str.length;
        //int nBit = (0xFFFFFFFF >>> (32 - nCnt));
        int nBit = 1<<nCnt;
        System.out.println("nCnt = "+nCnt);
        System.out.println("nBit = "+nBit);
        for (int i = 1; i <= nBit; i++) {
            System.out.println(i);
            for (int j = 0; j < nCnt; j++) {

                if ((i << (31 - j)) >> 31 == -1) {
                    System.out.println(j);
                    System.out.print(str[j]);
                }
            }
            System.out.println("");
        }

       /* ---------------------
         作者：GorillaNotes
        来源：CSDN
        原文：https://blog.csdn.net/XX_123_1_RJ/article/details/58585092
        版权声明：本文为博主原创文章，转载请附上博文链接！   public static void main(String[] args){
    */
    }
}
