import java.io.*;
        import java.util.StringTokenizer;

public class E {
    public static void main(String[] args){
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task()
                ;
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int n,num,m,popmin,index;
            stack a,min,temp,temp1;//a与min是同步的
            int b[],discard[];
            for(int i=0;i<T;i++) {
                m = 0;
                n = in.nextInt();
                a = new stack(n);
                min = new stack(n);
                temp = new stack(n);
                discard = new int[n];
                b = new int[n];
                //输入倒序存到数组b里
                for (int j = n - 1; j >= 0; j--) b[j] = in.nextInt();
                //遍历b，填充a和min，直到遇到b[j]==1
                for (int j = 0; j < n; j++) {
                    num = b[j];
                    if (num == 1) {
                        m = j;
                        discard[0] = 1;
                        break;
                    }
                    a.push(num);
                    if (!min.isEmpty()) {
                        if (num < min.topValue()) min.push(num);
                        else if (num >= min.topValue()) min.push(min.topValue());
                    } else {
                        min.push(num);
                    }
                }
                index = 0;
                //处理b中剩余的，也就是b之1之后的数字num（倒序），输入顺序中在1之前输入的数字（正序）
                for (int j = m + 1; j < n; j++) {
                    num = b[j];
                    //a pop的顺序是按照如数的顺序（正序）
                    if (!a.isEmpty()) {
                        popmin = min.topValue();
                        if (num < popmin) {
                            discard[++index] = num;
                        } else if (num > popmin) {
                            while (min.topValue() < num) {
                                popmin = min.topValue();
                                while (min.topValue() <= popmin) {
                                    temp.push(a.pop());
                                    min.pop();
                                    if (a.isEmpty()) break;
                                }

                                while (!temp.isEmpty()) {

                                    if (!a.isEmpty()) {
                                        if (a.topValue() < temp.topValue()) {
                                            discard[++index] = a.pop();
                                            min.pop();
                                        } else if (a.topValue() > temp.topValue()) {
                                            discard[++index] = temp.pop();
                                        }
                                    } else if (a.isEmpty()) {
                                        while (!temp.isEmpty()) {
                                            discard[++index] = temp.pop();
                                        }
                                    }
                                }

                                if (a.isEmpty()) break;
                            }
                            discard[++index] = num;
                        }
                    } else if (a.isEmpty()) {
                        discard[++index] = num;
                    }
                }

                //1前面没有了，1后面还有剩下的;
                if (!a.isEmpty()){
                    temp1 = new stack(n);
                    while (!a.isEmpty()) {
                        popmin = min.topValue();
                        while (min.topValue() <= popmin) {
                            temp.push(a.pop());
                            min.pop();
                            if (a.isEmpty()) break;
                        }

                        while (!temp.isEmpty()) {
                            if (!a.isEmpty()) {
                                while (temp.topValue() > min.topValue()) {
                                    popmin = min.topValue();
                                    while (min.topValue() <= popmin) {
                                        temp1.push(a.pop());
                                        min.pop();
                                        if (a.isEmpty()) break;
                                    }

                                    while(!temp1.isEmpty()){
                                        discard[++index] = temp1.pop();
                                    }

                                    if (a.isEmpty()) break;
                                }
                                discard[++index] =temp.pop();

                        } else if (a.isEmpty()) {
                            while (!temp.isEmpty()) {
                                discard[++index] = temp.pop();
                            }
                        }
                    }
                }
            }
               out.print(discard[0]);
               for(int j=1;j<n;j++ )
                   out.print(" "+discard[j]);
               if(i!=T-1)  out.print("\n");

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

            public void push(int c) { stack[++top]=c; }

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
/*
5
5
2 1 5 3 4
5
5 1 4 2 3
6
1 3 6 2 5 4
3
1 3 2
4
3 4 1 2
##output
1 2 3 4 5
1 2 3 4 5
1 2 4 5 6 3
1 2 3
1 2 4 3
#################
 */