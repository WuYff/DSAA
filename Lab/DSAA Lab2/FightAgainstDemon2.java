import java.io.*;
import java.util.StringTokenizer;

public class FightAgainstDemon2{
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
                int nTest=in.nextInt();
                int nMen,demon,need, left,right,totalNum,mid;
                int Sword[];

                for(int i=1;i<=nTest;i++){
                    totalNum=0;
                    nMen=in.nextInt();
                    demon=in.nextInt();
                    Sword=new int[nMen];
                    for(int j=0;j<nMen;j++){
                        Sword[j]=in.nextInt();
                    }

                    for(int j=0;j<nMen-1;j++){
                        need=demon-Sword[j];
                        left=j+1;
                        right=nMen-1;
                        while(left<=right){
                            mid=(left+right)/2;
                            if(Sword[mid]==need){
                               for(int k=mid;k<nMen;k++){
                                   if(Sword[k]==need) totalNum+=1;
                                   else break;
                               }
                                for(int k=mid-1;k>j;k--){
                                    if(Sword[k]==need) totalNum+=1;
                                    else break;
                                }
                                break;
                            }
                            if(Sword[mid]<need){
                                left=mid+1;
                            }else{
                                right=mid-1;
                            }
                        }
                    }
                    System.out.println(totalNum);
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