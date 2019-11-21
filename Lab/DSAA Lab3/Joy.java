import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;

public class Joy {
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
            int T = in.nextInt();
            long sum, intervalNum;
            int n, k, first, last;
            int a[],b[],next[],pre[];
            int index, preindex,nextindex,preNum, nextNum;
            ArrayList<Integer> x;
            for (int i = 0; i < T; i++) {
                sum = 0;
                b = new int[500000 + 1];
                n = in.nextInt();
                a = new int[n];
                next = new int[n];//[n - 1];
                pre = new int[n];//[n - 1];
                k = in.nextInt();
                if (k > 80) k = 80;
                first = 0;
                last = n - 1;
                for (int j = 0; j < n; j++) {

                    if (j > 0) {
                        next[j - 1] = j; //a中index为j-1的项的nextNode在a中的index为j;
                        pre[j] = j - 1; //a中index为j的项的preNode在a中的index
                    }
                    a[j] = in.nextInt();//一个strength;
                    b[a[j]] = j;  //这个strength(1~n)在数组a中的index;
                }

                if(k==0){
                    out.println(0);
                }else {

                for (int j = 1; j <= n; j++) {
                    index = b[j];//在a中的index
                    preNum = 0;
                    nextNum = 0;
                    intervalNum = 0;
                    x = new ArrayList<>();
                    preindex = index;
                    //在数组a中，往j所在位置的左边数
                        if (index > first) {

                            while (preNum <k-1) {
                                preindex = pre[preindex];
                                if (a[preindex] > j) {//比j大的才有贡献
                                    x.add(preindex);
                                    preNum += 1;
                                }
                                if(preindex == first) break;//到head了，没有pre了
                            }

                            if(preNum<k-1){
                                x.add(-1);  //X不足
                            }else if(preNum==k-1){
                                if(preindex>first){
                                    x.add(pre[preindex]);
                                }else if(preindex==first)  x.add(-1);
                            }

                        }else if(index == first)  x.add(-1);


                        Collections.reverse(x);
                        x.add(index);


                        //往j的右边数
                        nextindex = index;
                        if (index < last) {
                            while (nextNum < k-1) {
                                nextindex =next[nextindex];

                                if (a[nextindex] > j) {
                                    x.add(nextindex);
                                    nextNum += 1;
                                }

                                if (nextindex == last) break;//到tail了，没有next了
                            }

                            if(nextNum < k-1){
                                x.add(n);
                            }else if(nextNum==k-1){
                                if(nextindex==last){
                                    x.add(n);
                                }else if(nextindex<last){
                                    x.add(next[nextindex]);
                                }
                            }

                        }else if(index ==last){
                            x.add(n);
                        }


                        //利用乘法原理，计算贡献度
                        if(preNum+nextNum>=k-1) {
                            for (int m = 1; m <= x.size() - k-1; m++)//m+k<=x.size-1
                                intervalNum += (x.get(m) - x.get(m - 1)) * (x.get(m + k) - x.get(m + k - 1));
                        }
                        sum += intervalNum * j;

                        if (first == last) {
                            //这应该是处理完最后一个数，循环该结束了吧
                        }

                        //删除处理完的这个点
                          //更改pre和next的数组，因为pre和next存储的是点在a中的坐标，虽然next和pre删除了，但中间隔着多少个点的信息仍完整保留着
                        if (b[j] == first) {
                            first = next[b[j]];
                        } else if (b[j] == last) {
                            last = pre[b[j]];
                        } else {
                            next[pre[b[j]]] = next[b[j]];
                            pre[next[b[j]]] = pre[b[j]];
                        }
                }

                out.println(sum);
                }

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

    }
}