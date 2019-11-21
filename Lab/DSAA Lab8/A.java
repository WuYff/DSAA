import java.io.*;
import java.util.StringTokenizer;


public class A{
    public static void main(String[] args){
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),n,m,x,y,matrix[][];
            for(int i=0;i<T;i++) {
                n=in.nextInt();
                m=in.nextInt();
                matrix=new int[n][n];
                for(int j=0;j<m;j++){
                    matrix[in.nextInt()-1][in.nextInt()-1]=1;
                }
                for(int j=0;j<n;j++){
                    for(int k=0;k<n;k++){
                        out.print(matrix[j][k]+" ");
                    }
                    if(i==T-1&&j==n-1) break;
                     out.print("\n");//最后一个测试的最后一行
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