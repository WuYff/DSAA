import java.io.*;
        import java.math.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class cp {
    static class Node implements Comparable<Node>{
        int ex;
        int co;
        Node next;
        public Node(int co,int ex){
            this.ex=ex;
            this.co=co;
            this.next=null;
        }
        public int compareTo(Node node){
            return ex-node.ex;
        }
    }

    static class LinkedList {
        private Node first;
        private Node last;
        public void insert(Node node) {
            if(first==null){
                first=node;
                last=node;
            }else{
                last.next=node;
                last=node;
            }
        }
    }


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
            int n=in.nextInt();
            int l1,l2,t1,t2;
            Node a1[],a2[];
            LinkedList p;
            for(int i=0;i<n;i++) {
                p=new LinkedList();
                l1=in.nextInt();
                a1=new Node[l1];
                for(int j=0;j<l1;j++){
                    a1[j]=new Node(in.nextInt(),in.nextInt());
                }
                l2=in.nextInt();
                a2=new Node[l2];
                for(int j=0;j<l2;j++){
                    a2[j]=new Node(in.nextInt(),in.nextInt());
                }
                Arrays.sort(a1);
                Arrays.sort(a2);
                t1=0;
                t2=0;

                while(t1<a1.length&&t2<a2.length){
                    if(a1[t1].compareTo(a2[t2])>0) {
                        p.insert(a2[t2]);
                        t2++;
                    }else if(a1[t1].compareTo(a2[t2])<0){
                        p.insert(a1[t1]);
                        t1++;
                    }else{
                        if(a1[t1].co+a2[t2].co!=0) p.insert(new Node(a1[t1].co+a2[t2].co,a1[t1].ex));
                        t1++;
                        t2++;
                    }
                }

                if(t1<a1.length){
                    for(int j=t1;j<a1.length;j++)   p.insert(a1[j]);
                }else if(t2<a2.length){
                    for(int j=t2;j<a2.length;j++)   p.insert(a2[j]);
                }
                //打印

                if(p.first==null) out.print(0);
                else{
                    Node node=p.first;
                    //打印第一项
                    if(node.ex!=0){
                        if(node.co==-1) out.print("-");
                        else if(node.co!=1) out.print(node.co);
                        out.print("x");
                        if(node.ex>1) out.print("^"+node.ex);
                    }else {
                        out.print(node.co);
                    }
                    node=node.next;
                    //打印其他的
                    while(node!=null){
                        if(node.co<0){
                            if(node.co!=-1) out.print(node.co);
                            else  out.print("-");
                        } else{
                            out.print("+");
                            if(node.co!=1)  out.print(node.co);
                        }

                        if(node.ex!=0){
                            out.print("x");
                            if(node.ex>1) out.print("^"+node.ex);
                        }
                        node=node.next;
                    }
                }
               if(i!=n-1) out.print("\n");
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