import java.util.Scanner;

public class D{

    public static boolean cover(int w, int h) {
        if (w + 1 + h + w + h - 2 * 2 * w > 0) {
            return false;
        }
        return true;
    }
     /*1到2w行和NumOfLine-2w+1到NumOfLine行是要用"."来填补的；
          1到2w行的填补数就等于行数；
          从NumOfLine-2w+1到NumOfLine行的填补数等于NumOfLine-所在行数+1*/
    /*想要知道开始交叉的行数和结束较差的行数
     */
    //交叉的话一定是在顶部斜面区交叉，也就是说每行的点数>0
    //最先打印的行数于宽度b和长度a有关，行数=2b

    //判断是否重叠非常简单
    //知道从第几行重叠、重叠的行数、每一重叠行一共要打多少点、
    //对于每一个重叠立方体，还是分为前斜面区（不重、重）、后斜面区（不重）

    public static void PrintCover(int l, int w, int h) {
        int NumOfLine = w + 1 + h + w + h;
        int Length = 2 * w + 2 * l + 1;
        int NumOfCover = 2 * 2 * w - NumOfLine;
        int start = NumOfLine - 2 * w + 1;
        int dot = 1 + NumOfCover;
        String side = "";
        String topface = "";
        String frontface = "";
        for (int i = 0; i < l; i++) {
            side += "+-";
            topface += "/.";
            frontface += "|.";
        }
        side += "+";


        //前斜面区（不重叠区）
        for (int i = 2 * w; i > NumOfCover; i--) {
            for (int j = i; j >= 1; j--) System.out.print(".");
            if (i % 2 == 0) {
                System.out.print(side);
                for (int j = (Length - (i + side.length())) / 2; j > 0; j--) System.out.print(".+");
            } else {
                System.out.print(topface);
                for (int j = (Length - (i + topface.length())) / 2; j > 0; j--) System.out.print("/|");
            }
            System.out.println();
        }
      // System.out.println("前斜面区（不重叠区）");

        //前斜面区（重叠区）
        topface += "/";
        for (int i = NumOfCover; i >= 1; i--) {
            for (int j = i; j >= 1; j--) System.out.print(".");

            if (i % 2 == 1) {
                System.out.print(topface);
                for (int j = (Length - topface.length() - dot) / 2; j >= 1; j--)
                    System.out.print("|/");
            } else {
                System.out.print(side);
                for (int j = (Length - side.length() - dot) / 2; j >= 1; j--)
                    System.out.print(".+");
            }

            for (int j = dot - i; j >= 1; j--) System.out.print(".");
            System.out.println();
        }
        //System.out.println("前斜面区（重叠区）");

        //后斜面区（不重）
        for (int i = NumOfCover + 1; i < NumOfCover + 1 + 2 * w - NumOfCover; i++) {
            if (i % 2 == 0) {
                System.out.print(side);
                for (int j = (Length - side.length() - i) / 2; j >= 1; j--)
                    System.out.print(".+");

            } else {
                System.out.print(frontface);
                for (int j = (Length - frontface.length() - i) / 2; j >= 1; j--)
                    System.out.print("|/");
            }
            for (int j = i; j >= 1; j--) System.out.print(".");
            System.out.println();
        }


    }


    //重叠区域
    //对于每一个不重叠的立方体、还是分为前斜面区、正面区、后斜面区
    public static void PrintNotCover(int l, int w, int h) {
        int NumOfLine = w + 1 + h + w + h;
        int Length = 2 * w + 2 * l + 1;

        String side = "";
        String topface = "";
        String frontface = "";
        for (int i = 0; i < l; i++) {
            side += "+-";
            topface += "/.";
            frontface += "|.";
        }
        side += "+";
        frontface += "|";

        //打印前斜面
        for (int i = 2 * w; i >= 1; i--) {
            //打印"."
            for (int j = i; j >= 1; j--) System.out.print(".");

            //打印固定的边，跟奇偶有关
            if (i % 2 == 0) {
                System.out.print(side);
                for (int j = (Length - (i + side.length())) / 2; j > 0; j--) System.out.print(".+");
            } else {
                System.out.print(topface);
                for (int j = (Length - (i + topface.length())) / 2; j > 0; j--) System.out.print("/|");

            }
            System.out.println();
        }

        String l1 = side;
        String l2 = frontface;
        for (int i = (Length - side.length()) / 2; i > 0; i--) {
            l1 += ".+";
            l2 += "/|";
        }

        //打印正面
        for (int i = 1; i <= NumOfLine - 2 * 2 * w; i++) {
            if (i % 2 == 1) System.out.println(l1);
            else System.out.println(l2);
        }

        //打印后斜面
        for (int i = 1; i <= 2 * w; i++) {
            if (i % 2 == 1) {
                System.out.print(frontface.substring(0, frontface.length() - 1));
                for (int j = (Length - (frontface.length() - 1) - i) / 2; j >= 1; j--) System.out.print("|/");
                for (int j = 1; j <= i; j++) System.out.print(".");

            } else {
                System.out.print(side);
                for (int j = (Length - side.length() - i) / 2; j >= 1; j--) System.out.print(".+");
                for (int j = 1; j <= i; j++) System.out.print(".");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // read the n points from a file
        Scanner in = new Scanner(System.in);
        int l=0;
        int w=0;
        int h=0;
        int n=Integer.parseInt(in.nextLine());
        for (int i = 1; i <=n ; i++) {
            l = in.nextInt();
            w = in.nextInt();
            h = in.nextInt();
            if (cover(w, h)) {
                PrintCover(l, w, h);
            } else {
                PrintNotCover(l, w, h);
            }
        }
    }

}


