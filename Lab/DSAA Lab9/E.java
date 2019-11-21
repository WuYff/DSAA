import java.io.*;

public class E {
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
            int indegree;
            node next;
            node(int id)
            {
                this.id=id;
                this.next=null;
                this.indegree=0;
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
                    adj[i].insert(new node(i));
                }

            }

            public void addedge(int x, int y) {
                //x is the father of y
                adj[x].insert(new node(y));
            }

            public void degreeplus(int i) {
                adj[i].first.indegree += 1;
            }

            public int degreeMinus(int i) {
                if (adj[i].first.indegree > 0)
                    adj[i].first.indegree -= 1;
                return adj[i].first.indegree;
            }
            public boolean judgeIndegree(int i){
                if (adj[i].first.indegree == 0) return true;
                else return false;
            }
        }



        public void solve(Reader in, PrintWriter out)throws IOException {
            int n,m,x,y,index;
            int T=in.nextInt();
            int root[];
            Heap heap;
            int order[];
            int u;
            node node;
            Graph g;//The one that is 0 is the node with indegree = 0;
            for(int i=0;i<T;i++){
                n=in.nextInt();
                m=in.nextInt();
                root=new int[n+1];
                g=new Graph(n);
                heap=new Heap(n);
                order=new int[n];
                index=0;
                //build the graph
                for(int j=0;j<m;j++){
                    //x is the father
                    x=in.nextInt();
                    y=in.nextInt();
                    root[y]=1;
                    g.addedge(x,y);
                    g.degreeplus(y);
                }

                for(int j=1;j<=n;j++){
                    if(root[j]==0) heap.insert(j);
                }

                while(!heap.isEmpty()){
                    u=heap.delete();
                    order[index++]=u;
                    node=g.adj[u].first.next;
                    while(node!=null){
                        //更新去掉结点u之后，u连的点的indegree;
                        g.degreeMinus(node.id);
                        //插入更新后所有indegree=0的点
                        if(g.judgeIndegree(node.id)) heap.insert(node.id);
                        node=node.next;
                    }
                }

                //impossible当且仅当有环
                if(index<n-1){
                    out.print("impossible");
                }else{
                    for(int a=0;a<n-1;a++)
                        out.print(order[a]+" ");
                    out.print(order[n-1]);
                }
                out.print("\n");

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
            public void insert(int n){
                set[++size]=n;
                int k=size;//新加进来的数字所在的位置
                int parent=size/2;
                while(n<set[parent]){
                    swap(k,parent);
                    if(parent==1) break;
                    k=parent;
                    parent=parent/2;
                }
            }

            public int delete(){

                if(!this.isEmpty()) {
                    int a=set[1];
                    set[1] = set[size];
                    set[size] = 0;
                    size--;
                    int k = 1, min;
                    while (2 * k <= size) {
                        if (2 * k + 1 <= size) {
                            if (set[2 * k] <= set[2 * k + 1]) min = 2 * k;
                            else min = 2 * k + 1;
                        } else min = 2 * k;
                        if (set[k] > set[min]) swap(k, min);
                        else break;
                        k = min;
                    }
                    return a;

                }
                return -1;
            }
            public boolean isEmpty(){
                return size==0;
            }

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
