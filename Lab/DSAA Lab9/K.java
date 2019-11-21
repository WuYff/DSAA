import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class K {
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
        //肯定是许许多多独立的环
        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),n,size,v;
            PriorityQueue <Integer> cycle;
            Graph g;
            int max;
            for(int i=0;i<T;i++) {
                cycle=new  PriorityQueue<>();
                n=in.nextInt();
                g=new Graph(n);
                max=0;
                //create the graph
                for(int j=1;j<=n;j++){
                    g.addedge(j,in.nextInt());
                }

                for(int j=1;j<=n;j++){
                    if(g.visit[j]==0){
                        //这个点还没有成环
                        cycle.add(g.dfs(j));
                    }
                }
                //cycle.size-2//2
                size=cycle.size();
                if(size==1){
                    v=cycle.poll();
                    max=v*v;
                }else if(size==2){
                    v=cycle.poll()+cycle.poll();
                    max=v*v;
                }else if(size>=3) {
                    for (int j = 1; j <= size - 2; j++) {
                        v = cycle.poll();
                        max += v * v;
                    }
                    v=cycle.poll()+cycle.poll();
                    max+=v*v;
                }
                out.println(max);

            }
        }

        class Vertex
        {
            int id;
            Vertex next;
            Vertex(int id)
            {
                this.id=id;
                this.next=null;
            }
        }


        class Graph {
            int V;
            LinkedList[] adj;
            int visit[];

            public Graph(int V) {
                this.V = V;
                adj = new LinkedList[V + 1];
                visit=new int[V+1];
                for (int i = 1; i <= V; i++) {
                    adj[i] = new LinkedList();
                    adj[i].insert(new Vertex(i));
                }
            }

            public void addedge(int from,int to ) { adj[from].insert(new Vertex(to)); }

            public int dfs(int i){
                int start=0, end, node=0;
                Vertex v;Vertex u;
                Stack s=new Stack(V+1);
                s.push(this.adj[i].first);
                this.visit[i]=1;
                while(!s.isEmpty()){
                    v=s.top();
                    u=this.adj[v.id].first.next;//这个点指向的点
                    if(this.visit[u.id]==1){//找的环了（这个点指向的点被标记过）
                        start=s.topIndex();
                        node=u.id;
                        break;
                    }else if(visit[u.id]==0){//这个点是环上的点，但还没有加上
                        s.push(u);
                        this.visit[u.id]=1;
                    }
                }
                while(s.top().id!=node){
                    s.pop();
                }
                end=s.topIndex();
                return  start-end+1;
            }
        }

        class LinkedList {
            private Vertex first;
            private Vertex last;

            public void insert(Vertex node) {
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


        class Stack{
            private Vertex stack[];
            private int top;

            public Stack(int size){
                stack=new Vertex[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(Vertex num) { stack[++top]=num; }

            public Vertex pop() { return stack[top--]; }

            public Vertex top() { return stack[top]; }

            public int topIndex(){return top;}

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