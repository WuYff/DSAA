import java.io.*;
        import java.util.StringTokenizer;

public class  A {
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
            char x[]={'l','a','n','r','a','n'};
            int T=in.nextInt();
            String s;
            int size;
            stack stack;
            char c;
            char xc;
            int m;
            for (int i=0;i<T;i++) {
                s=in.next();
                size=s.length();
                stack=new stack(size);
                for(int j=0;j<size;j++){
                    stack.push(s.charAt(j));
                }
                m=5;
                while(!stack.isEmpty()&&m>=0){
                    c=stack.pop();
                    if(c==x[m]) m--;
                }

                if(m==-1) out.println("YES");
                else out.println("NO");

            }
        }
        class stack{
            private char stack[];
            private int top;

            public stack(int size){
                stack=new char[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(char c) { stack[++top]=c; }

            public char pop() { return stack[top--]; }

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