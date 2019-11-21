import java.io.*;

public class G {
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
            node next;
            node(int id)
            {
                this.id=id;
                this.next=null;
            }
        }


        class Graph{
            int V;
            LinkedList []adj;
            int time[][];
            public Graph(int V){
                this.V=V;
                adj=new LinkedList[V+1];
                for(int i=1;i<=V;i++){
                    adj[i]=new LinkedList();
                    adj[i].insert(new node(i));
                }
                time=new int[V+1][2];
            }

            public void addedge(int x,int y){
                //y is the father of x
                adj[y].insert(new node(x));
            }

            public void dfs(int visit[],int i,Stack s,int count){
                s.push(this.adj[i].first);
                this.time[i][0]=++count;
                visit[i]=1;
                node v;node u;
                boolean b;
                while(!s.isEmpty()){
                    b=false;
                    v=s.top();
                    u=this.adj[v.id].first.next;
                    while(u!=null){
                        if(visit[u.id]==0){
                            s.push(u);
                            this.time[u.id][0]=++count;
                            visit[u.id]=1;
                            b=true;
                            break;
                        }
                        u=u.next;
                    }

                    if(!b){
                        s.pop();
                        this.time[v.id][1]=++count;
                    }

                }

            }


            /*public void  getTime(){
                int count=0;
                int visit[]=new int[this.V+1];
                Stack s=new Stack(this.V+1);
                for(int i=1;i<=V;i++){
                    if(visit[i]==0){
                        this.dfs(visit,i,s,count);
                    }
                }
                //now we get the time each vertex push and pop;
            }*/

            public void  getTime(int root){
                int count=0;
                int visit[]=new int[this.V+1];
                Stack s=new Stack(this.V+1);
                this.dfs(visit,root,s,count);
               // for(int i=1;i<=V;i++){
                  // if(visit[i]==0){
                    //    this.dfs(visit,i,s,count);
                  //  }
                //}
                //now we get the time each vertex push and pop;
            }

            public boolean ancestor(int x,int y){
                // If y is an ancestor of x, output Yes, else output No.
                if(x==y) return true;
                if(time[y][0]<time[x][0]&&time[x][1]<time[y][1]) return true;
                return false;
            }


        }

        public void solve(Reader in, PrintWriter out)throws IOException {
            int n,m,r,x,y;
            Graph g;
            int root[];
            int T=in.nextInt();
            for(int i=0;i<T;i++){
                r=0;
                n=in.nextInt();
                m=in.nextInt();
                g=new Graph(n);
                root=new int[n+1];
                //build the graph
                for(int j=0;j<n-1;j++){
                    x=in.nextInt();
                    y=in.nextInt();
                    g.addedge(x,y);
                    root[x]=1;
                }
                for(int j=1;j<=n;j++){
                   if(root[j]==0){
                       r=j;
                       break;
                   }
                }
                g.getTime(r);
                for(int j=0;j<m;j++){
                   if(g.ancestor(in.nextInt(),in.nextInt()))
                       out.println("Yes");
                   else out.println("No");
                }
            }

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
