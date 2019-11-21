
import java.io.*;
        import java.util.StringTokenizer;

public class ThreeBody2{


    static class Node{
        boolean isEarth;
        Node next;
        public Node(boolean isEarth){
            this.isEarth=isEarth;
            this.next=null;
        }
    }
    //环形链表
    static class LinkedList{

        public Node first;
        public Node last;

        public boolean isEmpty(){
            return first==null;
        }
        public void insert(boolean e){
            Node node=new Node(e);
            if(this.isEmpty()){
                //空链表插入第一个node;
                first=node;
                last=node;
                last.next=first;
            }else{
                //插入一个node;
                last.next=node;
                last=node;
                last.next=first;
            }
        }
        //destroy 3,要删除第四个， 0 1 2 3 4 5 6
        //删除直到地球被删除（地球是最后或者不是）
        //要考虑删除的是head还是tail，因为要改变指针
        public void delete(int destroy){
            Node beforedelet=this.first;
            boolean deleteEarth=false;
            //找到要删除的那个节点之前的位置
            while(!deleteEarth) {
                beforedelet=this.first;
                for (int i = 1; i < destroy; i++) {
                    beforedelet = beforedelet.next;
                }
                //这时候beforedelet是2
                if (beforedelet.next.isEarth == true)
                    return;
                    //deleteEarth = true;
                //如果删除的是first
                if (beforedelet.next == first) {
                    first = first.next;
                    last.next=first;
                } else if (beforedelet.next == last) {
                    beforedelet.next = last.next;
                    last = beforedelet;
                } else {
                    beforedelet.next = beforedelet.next.next;
                    last=beforedelet;
                    first=beforedelet.next;
                }
            }
        }

    }
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
            int nplanet;
            int destroy;
            int earth;

            LinkedList circle;
              for(int i=1;i<=nTest;i++){
             nplanet=in.nextInt();
            destroy=in.nextInt();
            earth=in.nextInt();
            circle=new LinkedList();
            for(int j=0;j<earth;j++)
                circle.insert(false);
            circle.insert(true);
            for(int j=earth+1;j<nplanet;j++)
                circle.insert(false);
            circle.delete(destroy);
            if(circle.first==circle.last)//只剩下地球了
                out.println("Yes");
            else
                out.println("No");
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