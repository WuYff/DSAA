import java.io.*;
import java.util.StringTokenizer;

public class B1 {
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
        class Vertex
        {
            int id;
            int w;
            Vertex next;
            Vertex(int id,int w)
            {
                this.id=id;
                this.w=w;
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
                    adj[i].insert(new Vertex(i,0));
                }

            }

            public void addedge(int u,int v,int w ) {
                //undirected graph
                adj[u].insert(new Vertex(v,w));
                adj[v].insert(new Vertex(u,w));
            }

        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),N,M,u,v,w,ua,va,wa,a;
            Graph g;
            for(int i=0;i<T;i++) {
                N=in.nextInt();//The number of nodes
                M=in.nextInt();//The number of edges
                g=new Graph(N);
                wa=2000;
                ua=0;va=0;
                //build the graph
                for(int j=0;j<M;j++){
                    u=in.nextInt();
                    v=in.nextInt();
                    w=in.nextInt();
                    g.addedge(u,v,w);
                    if(w<wa){
                        //寻找长度最小的边
                        ua=u;//ua and va are the vertice of the edge with smallest weight
                        va=v;
                        wa=w;
                    }
                }
                a=prim(g,M,ua,va,wa);
                out.println(a);
            }
        }


        public int prim(Graph g,int M,int ua,int va,int wa){
            int cost=wa;//the cost and the answers
            int count=2;//how many node is in the MST;Also a condition to judge when to stop(when count==g.V)
            Vertex node;
            Heap heap=new Heap(M+1);
            int kicked []=new int[g.V+1];
            kicked [ua]=-1;kicked [va]=-1;
            node=g.adj[ua].first.next;
            while(node!=null){
                if(kicked[node.id]!=-1){ heap.insert(node); }
                node=node.next;
            }

            node=g.adj[va].first.next;
            while(node!=null){
                if(kicked[node.id]!=-1){ heap.insert(node); }
                node=node.next;
            }

            while(count<g.V){
                while( kicked[heap.top()]==-1){
                    heap.delete();
                    if(heap.isEmpty()) break;
                }
                if(heap.isEmpty()) break;
                node=heap.delete();
                cost+=node.w;
                kicked[node.id]=-1;
                count++;
                if(count==g.V) break;
                node=g.adj[node.id].first.next;
                while(node!=null){
                    if(kicked[node.id]!=-1){heap.insert(node); }
                    node=node.next;
                }

            }
            return cost;
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

        //小顶堆
        class Heap{
            Vertex set[];
            int size;
            public Heap(int n){
                set=new Vertex[n];
                size=0;//set[0]是空位
            }

            void swap(int i,int j){
                Vertex temp=set[i];
                set[i]=set[j];
                set[j]=temp;
            }
            public void insert(Vertex num){
                set[++size]=num;
                int k=size;//新加进来的数字所在的位置
                int parent=size/2;
                if(size==1) return;
                while(num.w<set[parent].w){
                    swap(k,parent);
                    if(parent==1) break;
                    k=parent;//当前需要调整的元素的位置
                    parent=parent/2;
                }
            }

            public Vertex delete(){
                //删除堆顶
                if(!this.isEmpty()) {
                    Vertex a=set[1];
                    set[1] = set[size];
                    set[size] = null;
                    size--;
                    int k = 1, min;//min 存的是更小的数的index
                    while (2 * k <= size) {
                        if (2 * k + 1 <= size) {
                            if (set[2 * k].w<=set[2 * k + 1].w) min = 2 * k;
                            else min = 2 * k + 1;
                        } else min = 2 * k;
                        if (set[k].w> set[min].w) swap(k, min);
                        else break;
                        k = min;
                    }
                    return a;//return 距离最小的那个点的id

                }
                return null;
            }

            public boolean isEmpty(){
                return size==0;
            }
            public int top(){ return set[1].id; }//返回堆顶

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
