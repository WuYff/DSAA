import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C
{
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
            int T=in.nextInt(),N,M,left,right,mid,cost,precost,ll,rr,w;
            Edge a[];
            for(int i=0;i<T;i++) {
                N=in.nextInt();
                M=in.nextInt();
                a=new Edge[M];
                for(int j=0;j<M;j++){ a[j]=new Edge(in.nextInt(),in.nextInt(),in.nextInt()); }
                Arrays.sort(a);//从小到大排序
                left=0;
                right=a.length-N+1;//a.length-1-(a.length-N+1)+1=a.length-1-a.length+N-1+1=N-1（条）
                cost=0; ll=0;rr=0;precost=0;
                while(left<=right){
                    mid=(left+right)/2;
                    w=a[mid].w;
                    for(int j=mid;j>=0;j--){
                        if( w!=a[j].w) {
                            ll=j+1;
                            break;
                        }
                    }
                    //ll=search( left,mid,w, a);
                    cost= Kruskal(a, ll , M,N);
                    if(cost==-1) {//生不成树要扩大范围
                        right=ll-1;
                    } else {
                        precost=cost;//记录下最后能生成的树的cost//生的成树的话要继续缩小范围
                        left=mid+1;
                    }
                }
                out.println(precost);
            }
        }


        /*public int search(int left,int right,int weight,Edge[] a){
            int l=left;int r=right;int w=weight;int mid;int index=0;
            while(l<=r){
                mid=(l+r)/2;
                if(a[mid].w==w){
                    r=mid-1;
                    index=mid;
                }else if(a[mid].w<w){
                    l=mid+1;
                }else if(a[mid].w>w){
                    r=mid-1;
                }

            }
            return index;
        }
*/
        /*public int search2(int left,int right,int weight,Edge[] a){
            int l=left;int r=right;int w=weight;int mid;int index=0;
            while(l<=r){
                mid=(l+r)/2;
                if(a[mid].w==w){
                    l=mid+1;
                    index=mid;
                }else if(a[mid].w>w){
                    r=mid-1;
                }else if(a[mid].w<w){
                    l=mid+1;
                }

            }
            return index;
        }*/
        public int Kruskal(Edge a[], int mid,int M,int N){
            int u;
            int v;
            UnionFind uf=new UnionFind(M);
            int cost=0;
            int numOfedge=0;

            for(int i=mid;i<M;i++){
                if(numOfedge==N-1) break;
                u=a[i].u;
                v=a[i].v;
                if(!uf.connected(u,v)){
                    uf.union(u,v);
                    numOfedge++;
                    cost+=a[i].w;
                }
            }
            if(numOfedge==N-1) return cost;
            else return -1;
        }

        class UnionFind{
            private int id[];
            private int size[];

            public UnionFind(int N){
                id=new int[N+1];
                size=new int[N+1];
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