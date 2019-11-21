import java.io.*;
import java.util.StringTokenizer;

public class B {
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
          String s;
          String t;
          int ls;
          int lt;
          int js;
          int jt;
          boolean b;
          for(int i=0;i<T;i++){
              ls=in.nextInt();
              lt=in.nextInt();
              s=in.next();
              t=in.next();
              b=true;
              if(ls>lt+1){
                 b=false;
              }else if(ls==lt+1){
                  //s==t当且仅当s包含*,此时*为空
                  //为什么我感觉我写的这个代码有bug
                  js=0;
                  while(s.charAt(js)!='*'){
                      if(js==ls-1){//S没有*的情况
                          b=false;
                          break;
                      }
                      if(s.charAt(js)!=t.charAt(js)){
                          b=false;
                          break;
                      }
                      js++;
                  }

              }else if(ls==lt){
                  //s==t当且仅当所有char都一样
                  for(int j=0;j<ls;j++){
                      if(s.charAt(j)!=t.charAt(j)){
                          b=false;
                          break;
                      }
                  }

              }else if(ls<lt){
                  js=0;
                  while(s.charAt(js)!='*'&&js<ls){
                      if(s.charAt(js)!=t.charAt(js)){
                          b=false;
                          break;
                      }
                      js++;
                  }

                  if(js==ls){//如果s不包含*,s匹配t肯定会失败
                      b=false;
                  }else{//s包含*且s在*前半段与t相同,现在检查s在*之后的半段*
                      //这里没有判读b是否为false，也有漏洞！
                      js=ls-1;
                      jt=lt-1;
                      while(s.charAt(js)!='*'){
                          if(s.charAt(js)!=t.charAt(jt)){
                              b=false;
                              break;
                          }
                          js--;
                          jt--;
                      }
                  }

              }
              if(b) out.println("YES");
              else  out.println("NO");
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