import java.io.*;
import java.util.StringTokenizer;
import java.util.*;
/*public String substring(int beginIndex)
Returns a string that is a substring of this string.
The substring begins with the character at the specified index
and extends to the end of this string.*/

public class F {
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
        //标准next板子
        public int[] next(String p,int lp){
            int next[]=new int[lp];
            next[0]=-1;
            int x=-1;//x是k指针
            int j=0;
            while(j<lp-1){
                if(x==-1||p.charAt(j)==p.charAt(x)){
                    if(p.charAt(++j)==p.charAt(++x)) next[j]=next[x];
                    else next[j]=x;
                }else{
                    x=next[x];
                }
            }
            return next;
        }

        public int kmp(String s, String p, int lp,int ls){
            int[] next=next(p,lp);
            int i=0,j=0,max=-1;
            while(i<ls){
                if(j==-1||s.charAt(i)==p.charAt(j)){
                    i++;
                    j++;
                    if(j>max) max=j;//可以匹配到的最长的在哪里——>longest common substring
                    if(j==lp) break;
                }else{
                    j=next[j];
                }
            }
            return max;
        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int N,len,point,min;
            String s[];
            String s1,pattern;
            boolean b;
            ArrayList<String> Substring;
            for(int i=0;i<T;i++){
                N=in.nextInt();
                s=new String[N];
                Substring=new ArrayList<>();
                for(int j=0;j<N;j++)
                    s[j]=in.next();

                //排序，找出长度最小的字符串s1
                Comparator<String> sort = (String ss1, String ss2) -> (ss1.length()-ss2.length());
                Arrays.sort(s,sort);
                s1=s[0];
                len=s1.length();

                for(int j=0;j<len;j++){
                    pattern=s1.substring(j);
                    min=500;
                    b=true;
                    for(int k=1;k<N;k++){
                        point=kmp(s[k],pattern,pattern.length(),s[k].length());
                        if(point<=0) {
                            b=false;
                            break;//这个pattern匹配失败了
                        } else if(point<min){
                            min=point;
                            pattern=pattern.substring(0,min);
                        }
                    }
                    if(b) Substring.add(pattern);
                }

               if(Substring.size()==0){ out.println("Hong");
               } else{
                //   Comparator<String> sort2= (String ss1, String ss2) -> (-ss1.length()+ss2.length());
                   Comparator<String> sort2= new Comparator<String>() {
                       public int compare(String s1, String s2) {
                           int l1=s1.length();
                           int l2=s2.length();

                           if ( l1>l2) return -2;
                           if(l1<l2) return 2;
                           if(l1==l2) return s1.compareTo(s2);//按长度排序，如果长度一样就按字母序排序

                           return 0;
                       }
                   };

                   Collections.sort(Substring,sort2);//
                   out.println(Substring.get(0));
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

        public int nextInt() { return Integer.parseInt(next()); }

    }
}