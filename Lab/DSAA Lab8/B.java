import java.io.*;

import java.util.StringTokenizer;


public class B {
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
            int T=in.nextInt(),n,m,x,y,Q,record[],num;
            LinkedList graph[];
            boolean find;
            Node node;
            for(int i=0;i<T;i++) {
                //n nodes, m edges
                n=in.nextInt();
                m=in.nextInt();
                graph =new LinkedList[n];
                for(int j=0;j<n;j++){
                    graph[j]=new LinkedList();
                }
                for(int j=0;j<m;j++){
                    //build the graph
                    graph[in.nextInt()-1].insert(new Node(in.nextInt()));

                }
                Q=in.nextInt();
                for(int j=0;j<Q;j++){
                    find=false;
                    record=new int[n+1];//we need index from 1 to n
                    x=in.nextInt();
                    y=in.nextInt();
                    queue q=new queue(n);
                    if(graph[x-1].isEmpty()){
                        find=false;
                    }else{
                        node=graph[x-1].first;
                        while(node!=null){
                            if(node.id==y){
                                find=true;
                                break;
                            }
                            q.enqueue(node.id);
                            record[node.id]=1;
                            node=node.next;
                        }
                        //队列里的数字都不是y;因为是y就找到了,我们就不找了;所以还没找到y才入队
                        //加入number入队（在队里）了,那么record[number]肯定等于1，
                        if(!find){
                            while(!q.isEmpty()){
                                num=q.dequeue();
                                //把它连接的点入队
                                node=graph[num-1].first;
                                //把它指向的所有点（未出现过的）都入队
                                while(node!=null){
                                    if(node.id==y){
                                        find=true;
                                        break;
                                    }
                                    //没有出现过的点才能入队
                                    if( record[node.id]==0){
                                        q.enqueue(node.id);
                                        record[node.id]=1;
                                        node=node.next;
                                    }else  node=node.next;

                                }
                                if(find) break;
                            }
                        }
                    }
                    if(find) out.println("YES");
                    else out.println("NO");

                }//Q
            }
        }
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