import java.io.*;
import java.util.StringTokenizer;

public class C {
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
        class Node {
            int id;
            Node next;

            public Node(int id) {
                this.id = id;
                this.next = null;
            }
        }

        class LinkedList {
            private Node first;
            private Node last;

            public void insert(Node node) {
                if(first==null){
                    first=node;
                    last=node;
                }else{
                    last.next=node;
                    last=node;
                }
            }

            public boolean isEmpty(){
                return first==null;
            }
        }

        class queue{
            private int queue[];
            private int front;//front指向队列第一个的前一个
            private int rear;
            public queue(int size){
                queue=new int [size];
                front=-1;//指向队列中第一个元素的前一个位置
                rear=-1;//指向队列中最后一个元素
            }

            public boolean isEmpty(){ return front==rear; }
            public void enqueue(int num){
                queue[++rear]=num;
            }
            public int dequeue(){ return queue[++front]; }
            public int size(){ return rear-front; }

        }

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),n,m,s,x,y,record[],d,size,id;
            LinkedList graph[];
            queue q;
            Node node;
            for(int i=0;i<T;i++) {
                n=in.nextInt();//n个点
                m=in.nextInt();//m条边
                s=in.nextInt();
                record=new int[n+1];//记录距离
                q=new queue(n+2);//最多n个点
                graph =new LinkedList[n+1];
                for(int j=1;j<n+1;j++){
                    graph[j]=new LinkedList();
                }
                //bulid the undirected graph
                for(int j=0;j<m;j++){
                    x=in.nextInt();
                    y=in.nextInt();
                    graph[x].insert(new Node(y));
                    graph[y].insert(new Node(x));

                }
               /*d=1;
                node=graph[s].first;
                while(node!=null){
                    q.enqueue(node.id);
                    record[node.id]=d;
                    node=node.next;
                }
                while(!q.isEmpty()){
                    size=q.size();
                    for(int k=0;k<size;k++){
                        ++d;
                        id=q.dequeue();
                        node=graph[id].first;
                        while(node!=null){
                            if(record[node.id]==0) {
                                    q.enqueue(node.id);
                                    record[node.id]=d;
                                }
                                node=node.next;
                            }

                    }
                }

                for(int j=1;j<n;j++){
                    if(j==s) out.print(0+" ");
                    else if(record[j]==0) out.print(-1+" ");
                    else out.print(record[j]+" ");
                }
                out.print(record[n]);
                if(T!=n-1) out.println("\n");*/
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