import java.io.*;
import java.util.StringTokenizer;

public class A {
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
            int T=in.nextInt();
            int num;
            String s;
            char rhyme;
            char c;
            int now;
            int max;
            for(int i=0;i<T;i++){
                num=in.nextInt();
                now=0;
                max=0;
                s="";
                rhyme=' ';
                c=' ';

                for(int j=0;j<num;j++){
                    s=in.next();
                    rhyme=s.charAt(s.length()-1);
                    if(rhyme==c){
                        now+=1;
                    }else{
                        c=rhyme;
                        if(now>max) max=now;
                        now=1;
                    }
                }

                if(now>max) max=now;
                out.println(max);
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