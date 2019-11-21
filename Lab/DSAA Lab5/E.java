import java.io.*;
import java.util.StringTokenizer;


public class E{
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
        public int[] next(String p,int lp){
            int next[]=new int[lp+1];
            next[0]=-1;
            int x=-1;
            int j=0;
            while(j<lp-1){
                if(x>=0&&p.charAt(j)!=p.charAt(x)){
                    x=next[x];
                }else{
                    if(p.charAt(++j)==p.charAt(++x)) next[j]=next[x];
                    else next[j]=x;
                }
            }

            while(x!=-1&&p.charAt(j)!=p.charAt(x)){
                x=next[x];
            }
            next[++j]=++x;

            return next;
        }

        //在kmp匹配到之后继续匹配，不要让j指针重指
        public int kmp(String s, String p, int lp,int ls){
            int[] next=next(p,lp);
            int i=0,j=0;
            while(i<ls){
                if(j==-1||s.charAt(i)==p.charAt(j)){
                    i++;
                    j++;
                    if(j==lp&&i<ls) j=next[j];//This is important!!
                    //j==lp是“匹配到”的信号
                }else{
                    j=next[j];
                }
            }
            return j;
        }
        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int ls,lt;
            int j;
            String s,t;
            for(int i=0;i<T;i++){
                ls=in.nextInt();
                lt=in.nextInt();
                s=in.next();
                t=in.next();
                j=kmp(t,s,ls,lt);//这里s是pattern
                if(j<=0) out.println(0);//s和t没有公共前后缀
                else out.println(j+" "+s.substring(0,j));

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