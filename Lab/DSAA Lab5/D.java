import java.io.*;

import java.util.StringTokenizer;

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
        public int[] next(String p,int lp){
            //跟E题的一样
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

        public int kmp(String s, String p, int lp,int ls){
            //跟E题的一样
            int[] next=next(p,lp);
            int i=0,j=0;
            while(i<ls){
                if(j==-1||s.charAt(i)==p.charAt(j)){
                    i++;
                    j++;
                    if(j==lp&&i<ls) j=next[j];
                }else{
                    j=next[j];
                }
            }
            return j;
        }

        public int kmp2(String s, String p, int lp,int ls){
            //只要匹配到就可以！
            int[] next=next(p,lp);
            int i=0,j=0;
            while(i<ls){
                if(j==-1||s.charAt(i)==p.charAt(j)){
                    i++;
                    j++;
                    if(j==lp) break;
                }else{

                    j=next[j];
                }
            }
            return j;
        }

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int ls,lp;
            String s,p,pm,sm;
            int j,m;
            int next[];
            for(int i=0;i<T;i++) {
                ls=in.nextInt();
                s=in.next();
                //初始化，现根据前缀后缀匹配确定pattern
                p=s.substring(0,ls/3);
                lp=p.length();
                j=kmp(s,p,lp,ls);
                if(j<=0) out.println(0);//前后缀匹配失败
                else{;//前后缀匹配成功
                    //根据E题 if(j<=0) out.println(0);
                    // else out.println(j+" "+s.substring(0,j));
                    p=s.substring(0,j);//pattern[0,j-1] 长度是j
                    next=next(p,j);
                    //search in the middle
                    while(j>0){
                       pm=p.substring(0,j);//长度j              ///公共前缀和后缀
                       sm=s.substring(j,ls-j);//后缀[ls-j~ls-1] ///sm是中间的那块字符串
                       m=kmp2(sm,pm,pm.length(),sm.length());
                       if(m==pm.length()) break;
                       //if(m<pm.length()){
                           j=next[j];    //保证pm是公共前后缀
                      // }
                    }

                    if(j<=0) out.println(0);
                    else out.println(j);
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