import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;
        import java.util.Map;
        import java.util.HashMap;

public class F{
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
        static final int mod=1000000007;
        static final  BigInteger mmod = new BigInteger("1000000007");
        public Matrix times(Matrix a,Matrix b,int m){
            BigInteger biginteger = new BigInteger("0");
           Matrix c=new Matrix(m);
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
                   for(int k=0;k<m;k++){
                       c.matrix[i][j]+=a.matrix[i][k]*b.matrix[k][j]%mod;
                   }
                     if(c.matrix[i][j]>0) c.matrix[i][j]= c.matrix[i][j]%mod;
                     else  c.matrix[i][j]= (c.matrix[i][j]%mod+mod)%mod;
                }
            }
            return c;
        }

        public Matrix plus(Matrix a,Matrix b,int m){
            Matrix c=new Matrix(m);
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
                    c.matrix[i][j]=(a.matrix[i][j]+b.matrix[i][j])%mod;
                }
            }
            return c;
        }

        public Matrix minus(Matrix a,Matrix b,int m){
            Matrix c=new Matrix(m);
            for(int i=0;i<m;i++){
                for(int j=0;j<m;j++){
                    c.matrix[i][j]=(a.matrix[i][j]-b.matrix[i][j]+mod)%mod;
                }
            }
            return c;
        }

        public void solve(InputReader in, PrintWriter out) {
            Map<Character,Integer> map=new HashMap<>();
            //map存储运算符的栈内优先权
            map.put('(',0);
            map.put('+',1);
            map.put('-',1);
            map.put('*',2);
            String operator="(+-*)";
            String s,operand;
            int T=in.nextInt();
            int n,m,index;
            int sum[][];
            Matrix []matrix;
            Matrix num1,num2;
            char c[];
            char x;
            stack post;
            StackMatrix stackmatrix;
            for(int i=0;i<T;i++){
                s="";
                n=in.nextInt();
                m=in.nextInt();
                matrix=new Matrix[n];
                post=new stack(50);
                stackmatrix=new StackMatrix(50);
                sum=new int[m][m];
                operand="";
                //初始化所有matrix——开始
                for(int j=0;j<n;j++){
                    matrix[j]=new Matrix(m);
                    for(int y=0;y<m;y++){
                        for(int mx=0;mx<m;mx++){
                            matrix[j].fill(y,mx,in.nextInt());
                        }
                    }
                }
                //初始化所有matrix——结束
                //把中序表达式变为后续表达式
                c=in.nextCharArray();
                index=0;
                while(index<c.length){
                     x=c[index];
                    if(!operator.contains(String.valueOf(x))){
                        //遇到的是数字，就直接"输出"
                        while(!operator.contains(String.valueOf(c[index]))){
                            s+=c[index++];
                            if(index==c.length) break;
                        }
                        s+=",";//用于划分多位组成的数字

                    }else {
                        if(x=='('){
                            post.push(x);
                        }else if(x==')'){
                            while(post.topChar()!='('){
                                s+=post.pop();
                            }
                            post.pop();
                        }else{
                            if(post.isEmpty()){
                                post.push(x);
                            }else if (map.get(x)>map.get(post.topChar())){
                                    post.push(x);
                            }else{
                                while(map.get(post.topChar())>=map.get(x)){
                                    s+=post.pop();
                                    if(post.isEmpty()) break;
                                }
                                post.push(x);
                            }
                        }
                        index++;
                    }
                }

                while(!post.isEmpty()){
                    s+=post.pop();
                }

                //s=后序表达式，完成
                //out.println(s);
                index=0;
                while(index<s.length()){
                    x=s.charAt(index);
                    if(operator.contains(String.valueOf(x))){
                        //如果是运算符
                        num2=stackmatrix.pop();
                        num1=stackmatrix.pop();
                        if(x=='*')  stackmatrix.push(times(num1,num2,m));
                        else if(x=='+') stackmatrix.push(plus(num1,num2,m));
                        else if(x=='-') stackmatrix.push(minus(num1,num2,m));
                        index++;

                    }else{

                        operand="";
                        //如果是数字
                       while(x!=','){
                           operand+=x;
                           x=s.charAt(++index);
                       }
                       index++;//跳过','
                       stackmatrix.push(matrix[Integer.parseInt(operand)-1]);

                    }

                }

                //把最终结果存到num1里；
                num1=stackmatrix.pop();
                for(int j=0;j<m;j++){
                    for(int k=0;k<m;k++){
                        out.print(num1.matrix[j][k]);
                        if(k==m-1&&j!=m-1) out.print("\n");
                        else if(k!=m-1) out.print(" ");
                    }
                }


                if(i!=T-1)  out.print("\n");

            }
        }

        class Matrix{
            long [][] matrix;
            public Matrix(int m) {
                matrix=new long[m][m];
            }
            public void fill(int y,int x,long num){ matrix[y][x]=num; }
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

            public char topChar() { return stack[top]; }

        }

        class StackMatrix{
            private Matrix stack[];
            private int top;

            public  StackMatrix(int size){
                stack=new Matrix[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(Matrix c) { stack[++top]=c; }

            public Matrix pop() { return stack[top--]; }

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