import java.io.*;

        import java.util.StringTokenizer;

public class G {
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
            int n,num;
            String opera;
            stack max,min;
            for(int i=0;i<T;i++) {
            n=in.nextInt();
            max=new stack(n);
            min=new stack(n);
            for(int j=0;j<n;j++){
                opera=in.next();
                if(String.valueOf(opera).equals("push")){
                    num=in.nextInt();
                    if(max.isEmpty()&&min.isEmpty()){
                        max.push(num);
                        min.push(num);
                    }else{
                        if(num>max.topValue()){
                            max.push(num);
                            min.push(min.topValue());
                        }else if(num<min.topValue()){
                            max.push(max.topValue());
                            min.push(num);
                        }else{
                            max.push(max.topValue());
                            min.push(min.topValue());
                        }
                    }
                }else if(String.valueOf(opera).equals("pop")){
                    if(!max.isEmpty()&&!min.isEmpty()){
                        max.pop();
                        min.pop();
                        if(!max.isEmpty()&&!min.isEmpty()){
                            out.println(max.topValue()-min.topValue());
                        }else{
                            out.println(0);
                        }
                    }else{
                        out.println(0);
                    }
                }

            }
                // do sth

            }
        }

        class stack{
            private int stack[];
            private int top;

            public stack(int size){
                stack=new int[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(int num) { stack[++top]=num; }



            public int pop() { return stack[top--]; }

            public int topValue() { return stack[top]; }

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