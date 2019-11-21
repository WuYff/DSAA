import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class I1 {
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
            int id,x,y;
            Vertex(int id,int x,int y)
            {
                this.id=id;
                this.x=x;
                this.y=y;
            }
        }


        class Edge implements Comparable<Edge>
        {
            int u;
            int v;
            int w;

            Edge(int u,int v,int w)
            {
                this.u=u;
                this.v=v;
                this.w=w;

            }
            public int compareTo(Edge e) {return this.w- e.w; }
        }

        public int distance(Vertex u,Vertex v){ return (u.x-v.x)*(u.x-v.x)+(u.y-v.y)*(u.y-v.y);}

        public void solve(InputReader in, PrintWriter out) {
            Vertex v[];
            PriorityQueue<Edge> e;
            PriorityQueue<Edge> pq;
            Edge edge,mst[];
            int [][]group;
            int T=in.nextInt(),N,M,num,count,mincost,a,b;
            UnionFind uf;
            for(int i=0;i<T;i++) {
                N=in.nextInt();
                M=in.nextInt();
                v=new Vertex[N+1];
                mincost=0;count=0;
                group=new int[M+1][];
                uf=new  UnionFind(N);
                for(int j=1;j<=N;j++) v[j]=new Vertex(j,in.nextInt(),in.nextInt());
                for(int j=1;j<=M;j++){
                    num=in.nextInt();
                    group [j]=new int[num+1];
                    group[j][0]=in.nextInt();//多出的第一位存储cost
                    for(int k=1;k<=num;k++) group[j][k]=in.nextInt();
                }
                pq=new PriorityQueue<>() ;
                e=new PriorityQueue<>() ;
                for(int j=1;j<N;j++){
                    for(int k=j+1;k<=N;k++){
                        e.add(new Edge(v[j].id,v[k].id,distance(v[j],v[k])));
                    }
                }

                while( !e.isEmpty()&&count<N-1){
                    edge=e.poll();
                    a=edge.u;
                    b=edge.v;
                    if(!uf.connected(a,b)){
                        uf.union(a,b);
                        pq.add(edge);
                        mincost+=edge.w;
                    }
                }
                mst=pq.toArray(new Edge[0]);
                out.println( kruskal(M, N, group,mst, mincost));

            }
        }


        public int kruskal(int M,int N,int group[][],Edge mst[],int mincost){
            UnionFind uf;
            int ncnt = M;
            int cost,len,a,b;
            int numOfedge;
            int min=mincost;
            Edge edge;
            //int nBit = (0xFFFFFFFF >>> (32 - nCnt));
            int nbit = 1<<ncnt;
            for (int i = 1; i < nbit; i++) {
                uf=new UnionFind(N);
                cost=0;
                numOfedge=0;
                for (int j = 0; j < ncnt; j++) {//第几位上的
                    if ((i << (31 - j)) >> 31 == -1) {
                        int num=group[j+1].length-1;//这个group中有这些点num
                        cost+=group[j+1][0];
                        for(int k=1;k<num;k++){//连接（组合）当前被选中的套餐里的所有点
                            for(int m=k+1;m<=num;m++){
                                if(!uf.connected(group[j+1][k],group[j+1][m])){
                                    numOfedge+=1;//万一有一些点同时出现在不同的group里呢
                                    uf.union(group[j+1][k],group[j+1][m]);
                                }
                            }
                        }
                    }
                }
                len=mst.length;
                for(int j=0;j<len;j++){
                    if(numOfedge==-1) break;
                   edge=mst[j];
                    a=edge.u;
                    b=edge.v;
                    if(!uf.connected(a,b)){
                        uf.union(a,b);
                        cost+=edge.w;
                    }
                }
                if(cost<min) min=cost;
            }

            return min;

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