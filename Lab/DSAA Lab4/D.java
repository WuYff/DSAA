import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class D {
    ///Brute Force
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
        //矩阵 n行m列
        public boolean judge(int x,int y,int n,int m,char[][]maze){
            if(x<0||x>=m) return false;
            if(y<0||y>=n) return false;
            if(maze[y][x]=='#') return false;
            else return true;
        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            Map<Character,Character> map=new HashMap<>();
            int n,m,len,sx,sy,px,py,sum;
            char[][] maze;
            String s;
            char c;
            boolean finds;//,u,d,l,r;

            for (int i=0;i<T;i++) {
                //u=true;d=true;l=true;r=true;
                finds=false;
                sx=0;sy=0;sum=0;
                n=in.nextInt();
                m=in.nextInt();
                maze=new char[n][];
                for(int j=0;j<n;j++){
                    maze[j]=in.nextCharArray();
                }

                for(int j=0;j<n;j++){
                    for(int k=0;k<m;k++){
                        if(maze[j][k]=='S'){
                            sx=k;sy=j;
                            finds=true;
                            break;
                        }
                    }
                    if(finds) break;
                }


                s=in.next();
                len=s.length();

                for(int j=0;j<=3;j++){
                    map.put(String.valueOf(j).charAt(0) , 'u');

                    for(int k=0;k<=3;k++){
                        if(k==j) continue;
                        map.put(String.valueOf(k).charAt(0), 'd');

                        for(int q=0;q<=3;q++){
                            if(q==j||q==k) continue;
                            map.put(String.valueOf(q).charAt(0) , 'l');

                            for(int e=0;e<=3;e++){
                                if(e==j||e==k||e==q) continue;
                                map.put(String.valueOf(e).charAt(0) , 'r');
                                px=sx;py=sy;

                                for(int x=0;x<len;x++){
                                    c=map.get(s.charAt(x));

                                    if(c=='u'){
                                       if(judge(px,py-1,n,m,maze)) py-=1;
                                       else break;
                                    }else if(c=='d'){
                                        if(judge(px,py+1,n,m,maze)) py+=1;
                                        else break;
                                    }else if(c=='l'){
                                        if(judge(px-1,py,n,m,maze)) px-=1;
                                        else  break;


                                    }else if(c=='r'){
                                        if(judge(px+1,py,n,m,maze)) px+=1;
                                        else break;

                                    }

                                   if(maze[py][px]=='E'){
                                        sum+=1;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                out.println(sum);
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

        public char[] nextCharArray() {
            return next().toCharArray();
        }

    }
}