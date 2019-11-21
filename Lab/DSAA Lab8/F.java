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
            long w;
            node next;
            node(int id,long w)
            {
                this.id=id;
                this.w=w;
                this.next=null;
            }
        }


        class Graph{
            int V;
            LinkedList []adj;
            queue queue ;
            public Graph(int V){
                this.V=V;
                adj=new LinkedList[V+1];
                for(int i=1;i<=V;i++){
                    adj[i]=new LinkedList();
                    adj[i].insert(new node(i,0));
                }
                queue=new queue(V+1);
            }

            public void addedge(int x,int y,int z){
                adj[x].insert(new node(y,z));
            }

            public void dfs(int visit[],int i,Stack s){
                s.push(this.adj[i].first);
                LinkedList l;
                visit[i]=1;
                node v;
                node u;
                boolean b;
                while(!s.isEmpty()){
                    b=false;
                    v=s.top();
                   // l=this.adj[v.id];
                    //u=l.first.next;
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
                        this.queue.enqueue(v);
                    }

                }

            }


            public void ReverseTopological(){
                int visit[]=new int[this.V+1];
                Stack s=new Stack(this.V+1);
                for(int i=1;i<=V;i++){
                    if(visit[i]==0){
                        this.dfs(visit,i,s);
                       // visit[i]=1;
                    }
                }
            }

            public long longest(){
                long max=-1;
                long dis[]=new long[this.V+1];
                this.ReverseTopological();
                //now we get the reverse of the toplogical order.
                node v;
                node node;
                while(!this.queue.isEmpty()){
                    v= this.queue.dequeue();
                    node=this.adj[v.id].first.next;
                    while(node!=null){
                        if(dis[v.id]<dis[node.id]+node.w){
                            dis[v.id]=dis[node.id]+node.w;
                        }
                        node=node.next;
                    }

                    if(dis[v.id]>max) max=dis[v.id];
                }
                return max;
            }
        }

        public void solve(Reader in, PrintWriter out)throws IOException {
            int n,m;
            long l;
            Graph g;
          //  try{
                int T=in.nextInt();
                for(int i=0;i<T;i++){
                    n=in.nextInt();
                    m=in.nextInt();
                    g=new Graph(n);
                    //build the graph
                    for(int j=0;j<m;j++){
                        g.addedge(in.nextInt(),in.nextInt(),in.nextInt());
                    }
                    l=g.longest();
                    out.println(l);
                }
           // }catch(Exception e){
             //   out.println(e);
            //}
        }
        class Stack{
            private node stack[];
            private int top;

            public Stack(int size){
                stack=new node[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(node num) { stack[++top]=num; }

            public node pop() { return stack[top--]; }

            public node top() { return stack[top]; }

        }

        class queue{
            private node queue[];
            private int front;//front指向队列第一个的前一个
            private int rear;
            public queue(int size){
                queue=new node [size];
                front=-1;
                rear=-1;
            }

            public boolean isEmpty(){ return front==rear; }
            public void enqueue(node num){
                queue[++rear]=num;
            }
            public node dequeue(){
                return queue[++front];
            }
            public node Front(){ return queue[front+1]; }
            public node Rear(){ return queue[rear];}
            public int size(){ return rear-front; }
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
