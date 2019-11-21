import java.io.*;
import java.math.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SaveJoy {
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
            int n=0, k=0;
            int s[], c[];
            double sig[];
            double left, right, mid=0, sum;
            final double eps=1e-7;
            while (in.hasNext()) {
                n=in.nextInt();
                k=in.nextInt();
                s=new int[n];//学分
                c=new int[n];//成绩
                sig=new double[n];
                for(int i=0;i<n;i++) s[i]=in.nextInt();//读入数据
                for(int i=0;i<n;i++) c[i]=in.nextInt();//读入数据
                right=1000;left=0;//初始化左右边界
                //mid是GPA
                while(right-left>eps){
                    sum=0;
                    mid=(left+right)/2;
                    for(int i=0;i<n;i++)
                        sig[i]=s[i]*(c[i]-mid);
                    Arrays.sort(sig);
                    for(int i=k;i<sig.length;i++)
                        sum+=sig[i];//因为最多可以删k个，所以这些一定要有
                    for(int i=0;i<k;i++)
                        if(sig[i]>0) sum+=sig[i];
                    if(sum>0) left=mid;//也许GPA还可以再增大
                    if(sum<0) right=mid;//达不到这么大的GPA所以要减小

                }

                System.out.println(String.format("%.3f",mid));
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
    }
}
