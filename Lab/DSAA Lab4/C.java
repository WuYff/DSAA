
import java.io.*;
        import java.util.StringTokenizer;
import java.math.BigInteger;

//queue //O(n) //non-decreasing //可以有重复的数字// use Long
public class C {
    public static void main(String[] args){
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int n,m,num;
            queue q;
            BigInteger sum;
            int l;
            for(int i=0;i<T;i++) {
                sum = new BigInteger("0");
                n = in.nextInt();
                m = in.nextInt();
                q = new queue(n);
                if(n==1||n==2){
                    for (int j = 0; j < n; j++) {
                        num = in.nextInt();
                    }
                }else if(n>=3) {
                    for (int j = 0; j < n; j++) {
                        l = q.rear - q.front;
                        num = in.nextInt();
                        //如果队列元素个数小于3，就添加
                        if (l == 0) {
                            q.add(num);
                        } else if (0 < l && l < 3) {
                            //确保队列前三个一定符合q.Rear()-q.Front()<=m
                            while (num - q.Front()>m&&!q.isEmpty()) q.delete();//删除堆队头
                            q.add(num);

                        } else if (l >= 3) {
                            //如果队列元素个数大于3

                            //以下的操作可以保证q.Rear()-q.Front()<=m;
                            if (num - q.Front() > m) {

                                while(num - q.Front() >m &&!q.isEmpty()){
                                    sum = sum.add(combine(q.size()- 1));
                                    q.delete();//删除队头
                                }

                                q.add(num);//添加num

                            }else if (num - q.Front() <=m){
                                q.add(num);
                            }


                        }
                    }
                    //input完成

                    //处理queue中未处理的


                    if (q.size() >= 3) {
                        sum = sum.add(combine3(q.size()));
                        /*if (q.Rear() - q.Front() <= m) {
                            sum = sum.add(combine3(q.rear - q.front));

                        } else if (q.Rear() - q.Front() > m) {
                            while (q.Rear() - q.Front() > m) q.delete();

                            if (q.rear - q.front >= 3) sum = sum.add(combine(q.rear - q.front-1));
                        }*/
                    }
                }
                out.println(sum);
            }
        }

        public BigInteger combine(int n){
            if(n<2) return BigInteger.ZERO;
            long sum1=1;
            for(int i=n-1;i<=n;i++) sum1=sum1*i;
            sum1=sum1/2;
            BigInteger r=new BigInteger(String.valueOf(sum1));
            return r;
        }

        public BigInteger combine3(int n){
            if(n<3) return BigInteger.ZERO;
            long sum1=1;
            for(int i=n-2;i<=n;i++) sum1=sum1*i;
            sum1=sum1/6;
            BigInteger r=new BigInteger(String.valueOf(sum1));
            return r;
        }


        class queue{
            private int queue[];
            private int front;//front指向队列第一个的前一个
            private int rear;
            public queue(int size){
                queue=new int [size];
                front=-1;
                rear=-1;//尾
            }

            public boolean isEmpty(){ return front==rear; }
            public void add(int num){
               queue[++rear]=num;
            }
            public void delete(){
                front++;
            }
            public int Front(){ return queue[front+1]; }
            public int Rear(){ return queue[rear];}
            public int size(){ return rear-front; }
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