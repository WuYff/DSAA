import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
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

        public int[] next(String p,int lp){
            //标准update next模板
            int next[]=new int[lp];
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
            return next;
        }



        public int kmp(String s, String p,int[]next,int key,int ik){
            int i=ik,j=key,max=-1,lp=p.length(),ls=s.length();
            while(i<ls){
                if(j==-1||s.charAt(i)==p.charAt(j)){
                    i++;
                    j++;
                    if(j>max) max=j;//可以匹配到的最长的在哪里
                    if(j==lp) break;
                }else{
                    j=next[j];
                }
            }
            return max;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n;
            String s[];
            String op,p;//The length of the operation sequence <= 100000
            int result[];
            Stack stack;//每次输入操作后,得到的解结果
            int r,lop,point,min;
            int[][]next;
            Stack [] key;//每次输入操作后，对于每个字符串，kmp的指针匹配到哪里了
            while (in.hasNext()) {
                n=in.nextInt();
                s=new String[n];
                key=new Stack[n];
                next=new int[n][];
                for(int i=0;i<n;i++){
                    s[i]=in.next();
                    key[i]= new Stack(100005);
                    key[i].push(0);
                }


                op=in.next();
                lop=op.length();
                result=new int[lop+1];
                stack=new Stack(100005);//The length of the operation sequence <= 100000
                r=0;
                Comparator<String> sort = (String s1, String s2) -> (s1.length()-s2.length());
                //字符串长度从短到长排序
                Arrays.sort(s,sort);
                result[r]=s[0].length();
                min=s[0].length();

                //按照顺序计算字符串的next数组
                for(int i=0;i<n;i++){
                    next[i]=next(s[i],s[i].length());
                }
                //s[i]是pattern

                p="";


                for(int i=0;i<lop;i++){

                    if(op.charAt(i)!='-'){
                        stack.push(min);//push之前的那个结果
                        min=100005;//The total length of n words <= 100000
                        p+=op.charAt(i);
                        for(int j=0;j<n;j++){
                            if(key[j].topValue()!=s[j].length()) {
                                point = kmp(p, s[j], next[j], key[j].topValue(), p.length() - 1);
                                key[j].push(point);
                                if(s[j].length()-point<min) min=s[j].length()-point;
                            }else{
                                key[j].push(s[j].length());
                                min=0;
                            }
                        }
                        result[++r]=min;
                    } else {

                        if(!stack.isEmpty()&&p.length()>0){
                            min=stack.topValue();
                            result[++r]=stack.topValue();
                            for(int j=0;j<n;j++) key[j].pop();
                            p=p.substring(0,p.length()-1);
                        }else {
                            result[++r]= result[0];
                        }
                    }

                }

                for(int i=0;i<lop+1;i++)
                    out.println(result[i]);
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

/*Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: -1
	at Main$Task$Stack.pop(Main.java:155)
	at Main$Task.solve(Main.java:126)
	at Main.main(Main.java:13)*/

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
