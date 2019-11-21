
import java.io.*;
import java.util.Arrays;
 import java.util.ArrayList;
import java.util.StringTokenizer;
//CasinoFinanceWork
public class CasinoFinanceWork{
    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Node implements Comparable<Node> {
        int t;
        int day;
        Node next;
        Node pre;

        public Node(int day, int t) {
            this. t=  t;
            this.day = day;
            this.next = null;
            this.pre = null;
        }

        public int compareTo(Node node) {

           if(this.t !=node.t) return this.t - node.t;
           else return this.day-node.day;

        }

    }
    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int n, midindex,first, last;
            Node a[];
            int b[]=new int[300000+1];
            ArrayList<Integer> x=new ArrayList<>();

            for(int i=0;i<T;i++) {
                x=new ArrayList<>();
                b=new int[300000+1];
                n=in.nextInt();
                a=new Node[n];

                for(int j=0;j<n;j++) a[j]= new Node(j+1,in.nextInt());
                Arrays.sort(a);//已经对a排序了
                first=a[0].day;
                last=a[n-1].day;
                for(int j=0;j<n;j++){
                    b[a[j].day]=j;//第几天在a中的索引是什么
                    if(j>0){
                        a[j].pre=a[j-1];
                        a[j-1].next=a[j];
                    }
                }
                midindex=(1+n)/2-1;
                if(n!=1) x.add(a[midindex].t);//因为已经排序了，所以这就是中位数
                for(int j=n;j>=5;j=j-2){
                    //第j天
                    //在链表中把第j天删除
                    if(a[b[j]].day==first){//我觉得这个可以写成j==first
                        first=a[b[j]].next.day;
                        a[b[j]].next.pre=null;
                    }else if(a[b[j]].day==last){
                        last=a[b[j]].pre.day;
                        a[b[j]].pre.next=null;
                    }else {
                        a[b[j]].pre.next= a[b[j]].next;
                        a[b[j]].next.pre= a[b[j]].pre;
                    }

                    //一次包含两天，所以还有第j-1天
                    //在链表中把第j-1天删除
                    if(a[b[j-1]].day==first){
                        first=a[b[j-1]].next.day;
                        a[b[j-1]].next.pre=null;
                    }else if(a[b[j-1]].day==last){
                        last=a[b[j-1]].pre.day;
                        a[b[j-1]].pre.next=null;
                    }else {
                        a[b[j-1]].pre.next= a[b[j-1]].next;
                        a[b[j-1]].next.pre= a[b[j-1]].pre;
                    }

                    //因为a已经排序，所以只用考虑j天和j-1天的位置（与中位数比较），由此判断中位数是之前中位数的前一位，还是之后中位数的后一位
                    if(b[j]>=midindex&&b[j-1]>=midindex){//左移
                        midindex=b[a[midindex].pre.day];
                        x.add(a[midindex].t);
                    }else if((b[j]>midindex&&b[j-1]<midindex)||(b[j]<midindex&&b[j-1]>midindex)){
                        x.add(a[midindex].t);
                    }else if(b[j]<=midindex&&b[j-1]<=midindex){//右移
                        midindex=b[a[midindex].next.day];
                        x.add(a[midindex].t);
                    }


                }

               //还剩下第一天
                x.add(a[b[1]].t);

                //因为是倒序处理，所以要倒叙打印
                for(int m=x.size()-1;m>0;m--){
                    out.print(x.get(m)+" ");
                }
                out.print(x.get(0));
                if(i!=T-1)  out.print("\n");
                // do sth

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
