import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class I {
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
         static int ccost;//不选套餐的情况下的mst的cost
//没有问题
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
//没有问题
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
//没有问题 欧式距离的平方
        public int Euclidean(int x1,int y1,int x2,int y2 ){
            return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
        }
        public int Kruskal(Edge edge[],int N, UnionFind uf,int cost, int numOfedge){
            int u, v, len;
            Edge e;
            len=edge.length;
            for(int i=0;i<len;i++){
                if(numOfedge==N-1)break;
                e=edge[i];
                u=e.u;
                v=e.v;
                if(!uf.connected(u,v)){
                    uf.union(u,v);
                    cost+=e.w;
                    numOfedge++;
                }
            }
            return cost;

        }

        public Edge[]  prim(Vertex[]v,int N){
            PriorityQueue<Edge> pq=new  PriorityQueue<>();
            PriorityQueue<Edge> edge=new  PriorityQueue<>();
            Edge e;
            int marked[]=new int[N+1];
            int w;
            int vertex;
            int count=0;


            int x=v[1].x;int y=v[1].y;
            for(int i=2;i<=N;i++){
                w=Euclidean(x,y,v[i].x,v[i].y);
                pq.add(new Edge(1,i,w));
                marked[i]=w;
            }
            marked[1]=-1;
            //count++;

            while(!pq.isEmpty()&&count!=N-1){
                e=pq.poll();
                if(marked[e.u]==-1&&marked[e.v]==-1) continue;
                edge.add(e);
                ccost+=e.w;
                count++;
                if(marked[e.u]==-1) vertex=e.v;
                else vertex=e.u;
                marked[vertex]=-1;
                x=v[vertex].x;
                y=v[vertex].y;
                for(int i=1;i<=N;i++){
                    if(marked[i]==-1) continue;
                    w=Euclidean(x,y,v[i].x,v[i].y);
                   if(w<marked[i]){
                       marked[i]=w;
                       pq.add(new Edge(vertex,i,w));
                   }
                }
            }
            return edge.toArray(new Edge[0]);
        }

        public int kruskalPlusCombine(Edge edge[],Vertex v[], int [][]group,int N,int M){
            UnionFind uf;
            int c = M;
            int b= 1<<c;
            int cost;
            int numOfedge;
            int mincost=ccost;
            boolean incontain=false;
            for (int i = 1; i <= b-1; i++) {//所有套餐都不选的情况已经讨论过了，所以是b-1而不是b
                uf= new UnionFind(N);
                cost=0;
                numOfedge=0;
                for (int j = 0; j < c; j++) {
                    if ((i << (31 - j)) >> 31 == -1) {//看看哪一个套餐被选中了
                        incontain=false;
                        int num=group[j+1].length-1;//这个group中有这些点num
                        for(int k=1;k<num;k++){//连接（组合）当前被选中的套餐里的所有点
                            for(int m=k+1;m<=num;m++){
                                if(!uf.connected(group[j+1][k],group[j+1][m])){
                                    incontain=true;
                                    numOfedge+=1;//万一有一些点同时出现在不同的group里呢
                                    uf.union(group[j+1][k],group[j+1][m]);
                                }
                            }
                        }
                        if(incontain)  cost+=group[j+1][0];
                    }
                }
                cost= Kruskal(edge,N, uf,cost,numOfedge);
                if(cost<mincost) mincost=cost;
            }
            return mincost;
        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),N,M,num;
            int [][]group;
            Vertex []v;
            Edge[]e;
            int mincost;
            int x,y;
            for(int i=0;i<T;i++){
               ccost=0;
               N=in.nextInt();
               M=in.nextInt();
               group=new int[M+1][];
               v=new Vertex[N+1];
               e=new Edge[N-1];
               for(int j=1;j<=N;j++){

                   v[j]=new Vertex(j,in.nextInt(),in.nextInt());
               }
               for(int j=1;j<=M;j++){
                   num=in.nextInt();
                   group [j]=new int[num+1];
                   group[j][0]=in.nextInt();//多出的第一位存储cost
                   for(int k=1;k<=num;k++){
                       group[j][k]=in.nextInt();
                   }
               }
               e=prim(v,N);//mst边的集合
               mincost=kruskalPlusCombine(e,v, group, N,M);
               out.println(mincost);
            }
        }

        //我们可以肯定union是没有问题的
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