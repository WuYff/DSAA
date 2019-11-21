import java.io.*;

public class H {
    public static void main(String[] args) throws IOException{

        Reader in =new Reader();
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();

    }

    static class Task {
        class Vertex
        {
            int x;
            int y;
            int z;
            int r;

            Vertex(int x,int y,int z,int r)
            {
                this.x=x;
                this.y=y;
                this.z=z;
                this.r=r;
            }
        }
        public double distance(Vertex a, Vertex b){
           double d= Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y)+(a.z-b.z)*(a.z-b.z));
           if(a.r+b.r>=d) return 0;
           else return d-a.r-b.r;
        }


        //仍旧可以优化，只要终点被kicked掉就结束
        public double Dijkstra(Vertex v[],int n){
            int num=0;//已经踢出去的点的个数
            int kicked[]=new int[n+3];//1 means in the heap, 2 means already kicked
            //1好像没有用上~~
            int u;
            double d;
            double dis[]=new double[n+3];//1,2,……,n+1,n+2
            Heap heap=new Heap(5155);//m条边102C2=5151
            for(int i=1;i<=n+1;i++){
                dis[i]=Double.POSITIVE_INFINITY;
            }
             dis[n+2]=0;
            heap.insert(n+2,dis);
            while(num<n+2){
                //假的要从heap里去掉
                while(kicked[heap.top()]==2){
                    heap.delete(dis);
                    if(heap.isEmpty()) break;
                }
                if(heap.isEmpty()) break;
                u=heap.delete(dis);
                kicked[u]=2;
                num++;
                //任意两个点之间的距离应该只会被计算一次
                for(int i=1;i<=n+1;i++){//我们就不要每次遍历到源点了吧
                    if(kicked[i]!=2){
                        //对于还没有被踢出去的点,每个都要更新
                        d=distance(v[i],v[u]);//O(n)?
                        if(dis[i]>dis[u]+d){//single source shortest path
                            //u ———> node
                            dis[i]=dis[u]+d;
                            heap.insert(i,dis);
                        }
                    }
                }

            }

            return dis[n+1];
        }


        public void solve(Reader in, PrintWriter out)throws IOException {
            int T=in.nextInt(),N;
            Vertex [] v;
            double d;
            for(int i=0;i<T;i++){
                N=in.nextInt();
                v=new Vertex[N+3];
                for(int j=1;j<=N;j++){
                    v[j]=new Vertex(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
                }
                //把v[N+2]设为source便于遍历
                v[N+2]=new Vertex(in.nextInt(),in.nextInt(),in.nextInt(),0);//source
                v[N+1]=new Vertex(in.nextInt(),in.nextInt(),in.nextInt(),0); //destination
                d=Dijkstra(v,N);
                out.println(Math.round(d*100));
                //

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
