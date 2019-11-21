import java.io.*;
import java.math.*;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * Author: Wavatsor
 */
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
        class Node {
            int id;
            Node next;

            public Node(int id) {
                this.id = id;
                this.next = null;
            }
        }

        public boolean judge(int graph[][],int j,int k, int a, int b){
            if(graph[j][k]==0) return false;
            if(graph[j][a]==0) return false;
            if(graph[j][b]==0) return false;
            if(graph[k][a]==0) return false;
            if(graph[k][b]==0) return false;
            if(graph[a][b]==0) return false;
            return true;
        }

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),n,m,x,y,num;
            int graph[][];
            for(int i=0;i<T;i++){
                num=0;
                n=in.nextInt();
                m=in.nextInt();
                graph=new int[n][n];
                //build the graph
                for(int j=0;j<m;j++){
                    x=in.nextInt();
                    y=in.nextInt();
                    graph[x-1][y-1]=1;
                    graph[y-1][x-1]=1;
                }

                //all the combination

                for(int j=1;j<=n-3;j++){
                    for(int k=j+1;k<=n-2;k++){
                        for(int a=k+1;a<=n-1;a++){
                            for(int b=a+1;b<=n;b++){
                                if(judge(graph,j-1,k-1,a-1,b-1)) num+=1;
                                else continue;

                            }
                        }
                    }
                }
                out.println(num);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch(IOException e) {
                return false;
            }
        }
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}