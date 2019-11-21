import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
//Gigi
public class Gigi {

    public static void main(String[] args){
        InputStream inputStream =System.in; //new FileInputStream("E:\\大二上\\数据结构与算法分析\\dsaa lab5\\in.txt");
        OutputStream outputStream =System.out;//new FileOutputStream("E:\\大二上\\数据结构与算法分析\\dsaa lab5\\out.txt");
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();


    }

    static class Task {
        public int[] next(String p){
            int lp=p.length();
            int next[]=new int[lp+1];
            next[0]=-1;
            int x=-1;
            int j=0;
            while(j<lp-1){
                if(x==-1||p.charAt(j)==p.charAt(x)){

                    if(p.charAt(++j)==p.charAt(++x)) next[j]=next[x];
                    else next[j]=x;
                }else{
                    x=next[x];
                }
            }

            while(x!=-1&&p.charAt(j)!=p.charAt(x)){
                x=next[x];
            }
            next[++j]=++x;
            return next;
        }

        public int kmp(char c, String p,int[]next,int key){
            //往下匹配一个字符罢了，key是上一次匹配到哪里了（待匹配）
            int j=key,lp=p.length();
            if(j==lp) j=next[j];
                if(j==-1||c==p.charAt(j)){
                    j++;
                    return j;
                }else{
                    j=next[j];
                    if(j==-1) return 0;
                    if(c==p.charAt(j)) return ++j;
                }
          return j;//我怎么觉得return j这里写的有问题！！！！
        }

        public void solve(InputReader in, PrintWriter out) {
            int n;//给定的字符串的个数
            String p[];//储存给定的字符串
            String op;//operation字符串
            String s;//存储input
            int result[];//储存要输出的结果
            Stack stack;//储存每次输入一个char后,要输出的结果
            int r,lop,point,min,kej;
            int[][]next;//储存每个字符串对应的数组
            Stack [] key;//每次输入操作后，对于每个字符串，kmp的指针指到哪里了
            while (in.hasNext()) {
                //初始化
                n=in.nextInt();
                p=new String[n];
                key=new Stack[n];
                next=new int[n][];
                for(int i=0;i<n;i++){
                    p[i]=in.next();
                    key[i]=new Stack(100005);//The length of the operation sequence <= 100000
                    key[i].push(0);//初始指针为0；
                }
                op=in.next();
                lop=op.length();
                result=new int[lop+1];
                stack=new Stack(100005);//The length of the operation sequence <= 100000
                r=0;
                //按字符串长度从短到长排序
                Comparator<String> sort = (String s1, String s2) -> (s1.length()-s2.length());
                Arrays.sort(p,sort);
                //最短的字符串的长度为输出的第一个结果
                result[r]=p[0].length();
                stack.push(p[0].length());

                //按照顺序计算字符串的next数组
                for(int i=0;i<n;i++)  next[i]=next(p[i]);

                for(int i=0;i<lop;i++){

                    if(op.charAt(i)!='-'){
                        min=100005;//The total length of n words <= 100000
                        for(int j=0;j<n;j++){
                            point = kmp(op.charAt(i), p[j], next[j], key[j].topValue());//计算指针j的位置
                            key[j].push(point);//储存指针j
                            if(p[j].length()-point<min) min=p[j].length()-point;

                        }

                        result[++r]=min;
                        stack.push(min);

                    } else if(op.charAt(i)=='-'){

                        if(stack.top>0){
                            stack.pop();//清理'-'之前的那个结果
                            for(int j=0;j<n;j++)  key[j].pop();//清理'-'之前的所有的指针
                            result[++r]=stack.topValue();

                        }else {
                            result[++r]= result[0];//什么东西都没有的情况下，输入'-'
                        }

                    }

                }

                for(int i=0;i<lop+1;i++)  out.println(result[i]);
            }
        }


        class Stack{
            private int stack[];
            private int top;

            public Stack(int size){
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
