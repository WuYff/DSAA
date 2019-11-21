import java.io.*;
import java.util.StringTokenizer;
public class BookStore {
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
            String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int a[];
            int nbook=in.nextInt();
            int n;int len;int leave;
            String s;
            char p;
            while (nbook!=0) {
              a=new int[26];
              leave=0;
              n=nbook;
              s=in.next();
              len=s.length();
              for(int i=0;i<len;i++){
                p=s.charAt(i);
                if(a[alpha.indexOf(p)]==1){
                    a[alpha.indexOf(p)]=0;
                    nbook+=1;
                }else if(a[alpha.indexOf(p)]==0){
                    if(nbook==0){
                        leave+=1;
                        a[alpha.indexOf(p)]=-1;
                    }else if(nbook>0){
                        nbook-=1;
                        a[alpha.indexOf(p)]=1;
                    }
                }else if(a[alpha.indexOf(p)]==-1){
                    a[alpha.indexOf(p)]=0;
                }
              }
              out.println(leave);
              //At The End
              nbook=in.nextInt();
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