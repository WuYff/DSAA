import java.io.*;
import java.math.*;
import java.util.StringTokenizer;

public class A {
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

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int N;
            int a[];
            int index;
            int max;
            for(int i=0;i<T;i++) {
                N=in.nextInt();
                index=0;
                max=-1;
                a=new int[10001];
                for(int j=0;j<(N-1)*2;j++){
                    index=in.nextInt();
                    if(index>max) max=index;
                    if( a[index]==-1) a[index]=-2;
                    else if(a[index]==0) a[index]=-1;
                }
                for(int j=2;j<max;j++){
                    if( a[j]==-1) out.print(j+" ");
                }
                if( a[max]==-1) out.print(max);
                if(i!=T-1) out.print("\n");
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