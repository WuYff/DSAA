import java.io.*;
import java.util.StringTokenizer;


public class G {
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
        static double min;
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
                //x is the father of y
                adj[u].insert(new Vertex(v,w));
                adj[v].insert(new Vertex(u,w));
            }

        }



        public void solve(InputReader in, PrintWriter out) {
            double dMap[][];
            int T=in.nextInt(),K,N,M;
            Graph g;
            int list[];
            for(int i=0;i<T;i++) {
                min=Double.POSITIVE_INFINITY;
                N=in.nextInt();
                M=in.nextInt();
                K=in.nextInt();
                dMap=new double [N+1][N+1];
                list=new int[K+2];
                g=new Graph(N);
                    //build the graph
                for(int j=0;j<M;j++){
                    g.addedge(in.nextInt(),in.nextInt(),in.nextInt());
                }
                for(int j=1;j<=K;j++){
                    //through K given Vertexes
                    list[j]=in.nextInt();
                }
                list[0]=1;
                list[K+1]=N;//因为到最后一个点就结束了
                for(int j=0;j<K+1;j++){
                   dMap[list[j]]= Dijkstra( g, list[j],N,M);
                }
                FindMin(list,1, K+1,dMap);//不要包含起点和终点
                out.println((int)min);
                // do sth

            }
        }

        public double[] Dijkstra(Graph g, int s,int n,int m){
            //s is the id of the source
            int kicked[]=new int[n+1];//1 means in the heap, 2 means already kicked
            //1好像没有用上~~
            double dis[]=new double[n+1];
            Heap heap=new Heap(m);
            Vertex node;
            int u;
            for(int i=1;i<=n;i++){
                dis[i]=Double.POSITIVE_INFINITY;
            }
            dis[s]=0;
            heap.insert(s,dis);
            while(!heap.isEmpty()){//判断条件可以优化的，不是通过heap是否为空，而是通过已经踢掉的vertex的数目
                //假的
                while(kicked[heap.top()]==2){
                    heap.delete(dis);
                    if(heap.isEmpty()) break;
                }
                if(heap.isEmpty()) break;
                u=heap.delete(dis);
                node=g.adj[u].first.next;
                //更新u连接的点
                while(node!=null){
                    if(kicked[node.id]!=2){
                        if(dis[node.id]>dis[u]+node.w){//single source shortest path
                            //u ———> node
                            dis[node.id]=dis[u]+node.w;
                            heap.insert(node.id,dis);
                        }
                    }
                    node=node.next;
                }
            }

            return dis;
        }

        //全排列后找到最小路径
        public static void FindMin(int[] list, int start, int end,double dMap[][]) {
            int i;
            //double min=Double.POSITIVE_INFINITY;
            double d;
            int from,to;
            if (start == end) {//排列组合好了
                d=0;
                for (i = 1; i < end+1; i++){//这里是length+1，因为你必须到达终点！
                    //now the list have all you want baby.
                  //  System.out.print(list[i] + " ");
                //System.out.println();
                    from=list[i-1];
                    to=list[i];
                    if(dMap[from][to]==Double.POSITIVE_INFINITY){
                        //从from到to的最短距离
                        d=-1;
                        break;//这种排列是不可以的

                    }else{
                        d+=dMap[from][to];
                    }
                }
                //我们获得总路程d了
                if(d!=-1&&d<min){
                    min=d;
                }

            } else {
                for (i = start; i < end; i++) {
                    swap(list, start, i);
                    FindMin(list, start + 1, end,dMap);
                    swap(list, start, i);//back to the origin
                }
            }
        }

        public static void swap(int[] list, int i, int j) {
            int temp;
            temp = list[i];
            list[i] = list[j];
            list[j] = temp;
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
            int set[];
            int size;
            public Heap(int n){
                set=new int[n];
                size=0;//set[0]是空位
            }

            void swap(int i,int j){
                int temp=set[i];
                set[i]=set[j];
                set[j]=temp;
            }
            public void insert(int num,double dis[]){
                set[++size]=num;
                int k=size;//新加进来的数字所在的位置
                int parent=size/2;
                while(dis[num]<dis[set[parent]]){
                    swap(k,parent);
                    if(parent==1) break;
                    k=parent;//当前需要调整的元素的位置
                    parent=parent/2;
                }
            }

            public int delete(double dis[]){
                //删除堆顶
                if(!this.isEmpty()) {
                    int a=set[1];
                    set[1] = set[size];
                    set[size] = 0;
                    size--;
                    int k = 1, min;//min 存的是更小的数的index
                    while (2 * k <= size) {
                        if (2 * k + 1 <= size) {
                            if (dis[set[2 * k]] <=dis[set[2 * k + 1]]) min = 2 * k;
                            else min = 2 * k + 1;
                        } else min = 2 * k;
                        if (dis[set[k] ]> dis[set[min]]) swap(k, min);
                        else break;
                        k = min;
                    }
                    return a;//return 距离最小的那个点的id

                }
                return -1;
            }

            public boolean isEmpty(){
                return size==0;
            }
            public int top(){ return set[1]; }//返回堆顶

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