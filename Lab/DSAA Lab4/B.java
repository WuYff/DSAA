import java.io.*;
        import java.util.StringTokenizer;
        import java.util.Map;
import java.util.HashMap;


public class B{
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
            Map<Character ,Character> map =new HashMap<>();
            map.put('(',')');
            map.put('[',']');
            map.put('{','}');
            String r="}])";
            String s;
            int T=in.nextInt();
            int n;
            boolean b;
            char c;
            char pop;
            for(int i=0;i<T;i++) {
                b=true;
                n=in.nextInt();
                s=in.next();
                if(n%2==1){
                    out.println("NO");
                }else{
                    stack stack=new stack(n/2);
                    for(int j=0;j<n;j++){
                        c=s.charAt(j);
                        if(r.contains(String.valueOf(c))){
                            if(stack.isEmpty()){
                                b=false;
                                break;
                            } else if(c!=map.get(stack.pop())){
                                b=false;
                                break;
                            }
                        }else{
                            if(stack.isFulll()){
                                b=false;
                                break;
                            }else{
                                stack.push(c);
                            }
                        }
                    }

                    if(b) out.println("YES");
                    else  out.println("NO");

                }


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
            public boolean isFulll() { return top==stack.length-1;}

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

        public int nextInt() { return Integer.parseInt(next()); }

    }
}