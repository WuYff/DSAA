import java.io.*;

public class F {
    public static void main(String[] args) throws IOException{

        Reader in =new Reader();
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();

    }

    static class Task {
        class node
        {
            int id;
            int w;
            node next;
            node(int id,int w)
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
                    adj[i].insert(new node(i,0));
                }

            }

            public void addedge(int u,int v,int w ) {
                //x is the father of y
                adj[u].insert(new node(v,w));
                adj[v].insert(new node(u,w));
            }

        }

        public double[] Dijkstra(Graph g, int s,int n,int m){
            int kicked[]=new int[n+1];//1 means in the heap, 2 means already kicked
            //1好像没有用上~~
            double dis[]=new double[n+1];
            Heap heap=new Heap(m);
            node node;
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

        public void solve(Reader in, PrintWriter out)throws IOException {
            int T=in.nextInt(),n,m,s,t;
            double a[],b[],bigger,min;
            Graph g;
            for(int i=0;i<T;i++){
                min=Double.POSITIVE_INFINITY;
                n=in.nextInt();
                m=in.nextInt();
                g=new Graph(n);
                //build the graph
                for(int j=0;j<m;j++){
                    g.addedge(in.nextInt(),in.nextInt(),in.nextInt());
                }
                s=in.nextInt();
                t=in.nextInt();
                b=Dijkstra(g,s,n,m);
                a=Dijkstra(g,t,n,m);
                for(int j=1;j<=n;j++){
                    if(a[j]>b[j]) bigger=a[j];
                    else bigger=b[j];
                    if(bigger<min) min=bigger;
                }

                out.println((int)min);

            }

        }

        class LinkedList {
            private node first;
            private node last;

            public void insert(node node) {
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



    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}
