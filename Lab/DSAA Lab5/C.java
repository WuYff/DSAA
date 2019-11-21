import java.io.*;
import java.util.StringTokenizer;


public class C{
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
                if(x==-1||p.charAt(j)==p.charAt(x)){
                    if(p.charAt(++j)==p.charAt(++x)){
                        next[j]=next[x];
                    }else{
                        next[j]=x;
                    }
                }else{
                    x=next[x];
                }
            }
            //j=lp-1
//匹配完这个pattern后的下一位要去哪里
             while(x!=-1&&p.charAt(j)!=p.charAt(x)){
                 x=next[x];
             }
            next[++j]=++x;

            return next;
        }

        public int kmp(String s, String p, int lp,int ls){
            int num=0;
            int[] next=next(p,lp);
            int i=0,j=0;
            //因为要找出所有匹配的次数，所以是i<ls
            while(i<ls){
                if(j==-1||s.charAt(i)==p.charAt(j)){
                    i++;
                    j++;
                    if(j==lp){
                        num+=1;//因为要记下匹配到的个数
                        j=next[j];//This is important！！！
                    }
                }else{
                    j=next[j];
                }
            }
            return num;

        }
        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int ls,lp;
            String s,p;
            for(int i=0;i<T;i++){
                ls=in.nextInt();
                s=in.next();
                lp=in.nextInt();
                p=in.next();
                out.println(kmp(s,p,lp,ls));

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