import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class D {
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


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),n,m,K,cost;
            for(int i=0;i<T;i++) {
                PriorityQueue<Edge> q=new  PriorityQueue<>();
                n=in.nextInt();
                m=in.nextInt();
                K=in.nextInt();
                for(int j=0;j<m + K;j++){
                    q.add(new Edge(in.nextInt(),in.nextInt(),in.nextInt()));
                }
                if(m+K<n-1) out.println(-1);
                else{
                cost=Kruskal( q,n) ;
                out.println(cost);}
            }
        }

        public int Kruskal( PriorityQueue<Edge> q,int N){
            int u;int v;
            UnionFind uf=new UnionFind(N);//union是针对顶点的！
            int cost=0;
            int numOfedge=0;
            Edge e;
            while(!q.isEmpty()&&numOfedge!=N-1){
                e=q.poll();
                u=e.u;
                v=e.v;
                if(!uf.connected(u,v)){
                    uf.union(u,v);
                    cost+=e.w;
                    numOfedge++;
                }
            }
            if(numOfedge<N-1) return -1;
            else return cost;

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