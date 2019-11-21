import java.io.*;
        import java.util.StringTokenizer;

public class  ms {
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
            int nTest=in.nextInt();
            int a1[],a2[],q[];
            int l1,l2,b1,b2,index;
            for(int i=1;i<=nTest;i++){
                l1=in.nextInt();
                l2=in.nextInt();
                a1=new int[l1];
                a2=new int[l2];
                q=new int[l1+l2];
                for(int j=0;j<l1;j++){
                    a1[j]=in.nextInt();
                }
                for(int j=0;j<l2;j++){
                    a2[j]=in.nextInt();
                }
                b1=0;
                b2=0;
                index=0;
                while(b1<l1&&b2<l2){
                    if(a1[b1]>a2[b2]){
                        q[index]=a2[b2];
                        b2++;
                    }else if(a1[b1]<a2[b2]){
                        q[index]=a1[b1];
                        b1++;
                    }else if(a1[b1]==a2[b2]){
                        q[index]=a1[b1];
                        b1++;
                    }
                    index++;
                }

                if(b1==l1&&b2<l2){
                    for(int j=index;j<l1+l2;j++){
                        q[j]=a2[b2];
                        b2++;
                    }
                }else if(b1<l1&&b2==l2){
                    for(int j=index;j<l1+l2;j++){
                        q[j]=a1[b1];
                        b1++;
                    }
                }

                out.print(q[0]);
                for(int j=1;j<l1+l2;j++){
                    out.print(" "+q[j]);
                }
                if(i!=nTest) out.print("\n");

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