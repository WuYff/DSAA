import java.io.*;
import java.math.*;
import java.util.StringTokenizer;
//test unit
          /*  int l[]={2, 5, 4, 3 };;//limit
            int r[]={2,3,4};//预定的房间数
            int s[]={1,2,2};//起始天
            int t[]={3,4,4};//结束天
            int c[]=new int[4];//差分数组*/
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavator
 */
public class test {
    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }


    static class Task {

        public void solve(InputReader in, PrintWriter out) {
             int l[]={4,5,6,7,8,9};;//limit
            int r[]={1,2,3,2,4,1,1};//预定的房间数
            int s[]={4,5,1,5,1,1,1};//起始天
            int t[]={6,6,5,5,3,1,1};//结束天
            int c[]=new int[6];//差分数组
            int ndays=6;
            int morders=7;
            int left = 0;
            int right= 0;
            int mid = 0;
            int sum = 0;
                right = morders - 1;
                boolean thisGroup=true;//For All orders of this Group,False means not enough
                boolean check = true;//For this "mid" orders of this Group,False means not enough
                while (left <= right) {
                    c=new int[6];
                    sum=0;
                    mid = (left + right) / 2;
                    //填充差分方程
                    for (int i = 0; i <= mid; i++) {
                        c[s[i] - 1] += r[i];
                        if (t[i] < ndays) c[t[i]] -= r[i];
                    }

                    check = true;
                    //依次遍历计算当天所需要的房间数
                    for (int i = 0; i < ndays; i++) {
                        sum += c[i];
                        //超界的情况
                        if (sum > l[i]) {
                            thisGroup=false;
                            //需要继续二分；
                            check = false;
                            break;
                        }
                    }

                    if (check) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                //The algorithm while ends
                System.out.println(check);
                if(thisGroup) System.out.println(0);
                else if(!thisGroup){
                    System.out.println(-1);
                    if(check) System.out.println(mid+1+1);
                    else System.out.println(mid+1);
                }


        }
    }




    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch(IOException e) {
                return false;
            }
        }
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}