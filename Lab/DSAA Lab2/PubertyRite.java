
import java.io.*;
import java.math.*;
import java.util.StringTokenizer;

//Triple search
public class PubertyRite {

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
        public double f(double p,double x[],double w[],int l){
            double f=0;
            double xi=0;
           for(int i=0;i<l;i++){
               xi=x[i];
               if(xi>=p)  f+=(xi-p)*(xi-p)*(xi-p)*w[i];
               else       f+=(p-xi)*(xi-p)*(xi-p)*w[i];

           }
           return f;
        }

        public void solve(InputReader in, PrintWriter out) {
            double[]x;double[]w;
            int nTest=in.nextInt();
            int ArrayL;
            double right,left;
            for(int i=0;i<nTest;i++){
                right=0;left=1E6;
               ArrayL=in.nextInt();
               x=new double[ArrayL];
               w=new double[ArrayL];
                for(int j=0;j<ArrayL;j++){
                    x[j]=in.nextDouble();
                    if( x[j]<left) left=x[j];//确定左边界
                    if( x[j]>right) right=x[j];//确定右边界
                    w[j]=in.nextDouble();
                }
               double eps=0.00001, mid=0, mmid=0,midF=0,mmidF=0;
               int n=200;
                while(n>0) {//强制二分次数保证精度
                   mid=left+(right-left)/3;
                   mmid=right-(right-left)/3;
                   midF=f(mid,x,w,ArrayL);
                   mmidF=f(mmid,x,w,ArrayL);
                   if(midF< mmidF){
                       //最小点一定在mmid左边
                       right=mmid;
                   }else if(midF> mmidF){
                        //最小点一定在mid右边
                        left=mid;
                    }
                    n--;
                }


                    System.out.println("Case #"+(i+1)+": "+String.format("%.0f",f((mid+mmid)/2,x,w,ArrayL)));


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
        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }

}
