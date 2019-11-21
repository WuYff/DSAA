import java.io.*;
import java.math.*;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;// Differentiate
public class  Differentiate {
    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Node implements Comparable<Node> {
        int ex;
        int co;
        Node next;

        public Node(int co, int ex) {
            this.ex = ex;
            this.co = co;
            this.next = null;
        }

        public int compareTo(Node node) {
            return ex - node.ex;
        }

    }


    static class Task {


        public void solve(InputReader in, PrintWriter out) {
            int n=in.nextInt();
            int term,co, ex;
            Node[]p;Node[]o;
            Set<Integer> e;
            String s;
            for (int i=0;i<n;i++) {
                s="";
                e=new HashSet<>();
                term=in.nextInt();
                p=new Node[term];
                for(int j=0;j<term;j++){
                    co=in.nextInt();
                    ex=in.nextInt();
                    if(e.contains(ex)){
                        //从数组中查找Node？
                        for(int k=0;k<j;k++){
                            if(p[k].ex==ex){
                                p[k]=new Node(co+p[k].co,ex);
                                break;
                            }
                        }
                        p[j]=new Node(0,-10000);//排序后舍去最前面的

                    }else{
                        e.add(ex);
                        p[j]=new Node(co,ex);
                    }
                }

            // do sth
                Arrays.sort(p);
                o=Arrays.copyOfRange(p,term-e.size(),term);
                int not0=0;
                int ol=o.length;
                for(not0=0;not0<ol;not0++){
                    if(o[not0].co!=0&&o[not0].ex!=0) break;
                }

                if(not0!=ol) {

                    int oc = o[not0].co;//不为0
                    int oe = o[not0].ex;//不为0

                    //输出第一项
                            if (oe == 1) {//-x,x-->-1,+1
                                s += oc;

                            } else if (oe == 2) {
                                s += oc * 2 + "x";

                            } else {
                                if(oc * oe == -1) s+="-";
                                else if(oc*oe!=1) s+= oc * oe;
                                s +="x^";
                                s += oe - 1;
                            }

                    //输出第一项之后的所有项
                    for (int j =not0+1; j < o.length; j++) {
                        oc = o[j].co;
                        oe = o[j].ex;
                        if (oc != 0) {

                            if (oe != 0) {

                                if (oe == 1) {//-x,x-->-1,+1
                                    if (oc > 0) s += "+";
                                    s += oc;

                                } else if (oe == 2) {
                                    if (oc > 0) s += "+";
                                    s += oc * 2 + "x";

                                } else {
                                    if(oc * oe == -1) s+="-";
                                    else if(oc*oe>0){
                                        s+= "+";
                                        if(oc*oe!=1)  s+=oc*oe;
                                    }else if(oc*oe<0){
                                        s+=oc*oe;
                                    }
                                    s +="x^";
                                    s += oe - 1;
                                }

                            }

                        }

                    }

                    if(s.length()==0) out.print(0);
                    else out.print(s);

                }else if(not0==ol){
                    out.print(0);
                }

                if(i!=n-1)out.print("\n");

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























































































































































































