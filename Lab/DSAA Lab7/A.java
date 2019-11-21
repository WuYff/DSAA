import java.io.*;
import java.util.StringTokenizer;


public class A{
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
        class queue{
            private treeNode queue[];
            private int front;//front指向队列第一个的前一个
            private int rear;
            public queue(int size){
                queue=new  treeNode [size];
                front=-1;
                rear=-1;
            }

            public boolean isEmpty(){ return front==rear; }
            public void enqueue( treeNode num){
                queue[++rear]=num;
            }
            public treeNode dequeue(){ return queue[++front]; }
            public int size(){ return rear-front; }
        }

        class treeNode

        {
            int id;
            treeNode left;
            treeNode right;

            treeNode( int id,treeNode l, treeNode r)
            {
                this.left = l;
                this.right = r;
                this.id=id;
            }
            public void setRight(treeNode r){
                this.right = r;
            }
            public void setLeft(treeNode l){
                this.left=l;
            }
        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),n;
            treeNode node[],root,left,right;
            int a[],l,r;
            queue queue;
            boolean appear,judge;
           for(int i=0;i<T;i++) {
               n=in.nextInt();
               node=new treeNode[n+1];
               for(int j=1;j<=n;j++){
                   node[j]=new treeNode(j,null,null);
               }
               a=new int[n+1];
               root=null;
               queue=new queue(300000);
               appear=false;
               judge=true;
               for(int j=1;j<=n;j++){
                   l=in.nextInt();
                   r=in.nextInt();
                   node[j].setLeft(node[l]);
                   node[j].setRight(node[r]);
                   a[l]=1;
                   a[r]=1;
               }

               for(int j=1;j<=n;j++){
                   if(a[j]==0){
                       root=node[j];
                       break;
                   }
               }

                   queue.enqueue(root);
               //lever tranversal
                   while(!queue.isEmpty()) {
                        int size=queue.size();
                        for(int k=0;k<size;k++){
                           root=queue.dequeue();
                           if(root==null){
                               appear=true;
                           }else  if(root!=null) {
                               if(appear){
                                   judge=false;
                                   break;
                               }
                               queue.enqueue(root.left);
                               queue.enqueue(root.right);
                           }
                        }

                        if(!judge) break;
                }

                if(judge) out.println("YES");
                   else   out.println("NO");

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