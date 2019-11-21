import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class J {
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
            int T=in.nextInt(),N,to,sz,count;
            UnionFind uf;
            Graph gr;
            String s;
            String wolf="werewolf";//String villager=" villager";
            ArrayList<Edge>wolfEdge;
            Edge w;
            for(int i=0;i<T;i++) {
                count=0;
                N=in.nextInt();
                uf=new UnionFind(N);
                gr=new Graph(N);
                wolfEdge=new  ArrayList<>();
                for(int j=1;j<=N;j++){
                    to=in.nextInt();
                    s=in.next();
                    if(String.valueOf(s).equals(String.valueOf(wolf))){
                        wolfEdge.add(new Edge(j,to));//狼边
                    }else{
                        uf.union(j,to);//村民边
                        gr.addedge(to,j);//反向建图(只以villager边建图)
                    }
                }

                sz=wolfEdge.size();
                for(int j=0;j<sz;j++){
                    w=wolfEdge.get(j);
                    if(uf.connected(w.from,w.to)){
                        //run dfs for to(to is the wolfnode)
                        count+=gr.dfs(w.to);
                    }
                }
                out.println(count);
            }
        }

        class Edge
        {
            int from;
            int to;

            Edge(int from,int to)
            {
                this.from=from;
                this.to=to;
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

            public Graph(int V) {
                this.V = V;
                adj = new LinkedList[V + 1];
                for (int i = 1; i <= V; i++) {
                    adj[i] = new LinkedList();
                    adj[i].insert(new Vertex(i));
                }
            }

            public void addedge(int from,int to ) { adj[from].insert(new Vertex(to)); }

            public int dfs(int i){
                int visit[]=new int[this.V+1];
                Stack s=new Stack(V+1);
                int count=0;
                s.push(this.adj[i].first);
                visit[i]=1;
                Vertex v;Vertex u;
                boolean b;
                while(!s.isEmpty()){
                    b=false;
                    v=s.top();
                    u=this.adj[v.id].first.next;
                    while(u!=null){
                        if(visit[u.id]==0){
                            s.push(u);
                            visit[u.id]=1;
                            b=true;
                            break;
                        }
                        u=u.next;
                    }

                    if(!b){
                        s.pop();
                        count++;
                    }
                }
                return count;
            }
        }


        class UnionFind{
            private int id[];
            private int size[];

            public UnionFind(int N){
                id=new int[N+100];
                size=new int[N+100];
                for(int i=1;i<=N;i++) {
                    id[i]=i;
                    size[i]=1;
                }
            }

            private int root(int i){
                while(i!=id[i]) {
                    id[i]=id[id[i]];//变成grandfather
                    i=id[i];

                }
                return i;
            }

            public boolean connected(int i,int j ){
                return root(i)==root(j);
            }

            public void union(int i,int j){
                int r1=root(i);
                int r2=root(j);
                if(r1==r2) return;
                if(size[r1]<size[r2]){
                    id[r1]=r2;
                    size[r2]+=size[r1];
                }else{
                    id[r2]=r1;
                    size[r1]+=size[r2];
                }
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