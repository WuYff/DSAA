import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
/*Game 博弈论？
 1
 7
0 1 1 0 1 1 0
1 2
3 1
4 2
2 5
7 3
6 3*/
//
public class F {
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
        class TreeNode
        {
            int id;
            int color;
            List<TreeNode> childList;

            TreeNode(int id,int color)
            {
                this.id=id;
                this.color=color;
                childList=new ArrayList<>();
            }

            public void addchild(TreeNode node){
                this.childList.add(node);
            }

            public int sizechild(){ return this.childList.size(); }


        }


        public void solve(InputReader in, PrintWriter out) {
            queue queue;
            TreeNode[] node;
            TreeNode root,child;
            int T=in.nextInt();
            int N,id1,id2,num,size,size2;
            int a[];
            boolean win;
            for(int i=0;i<T;i++){
                win=false;
                queue=new queue(10000);
                N=in.nextInt();
                node=new TreeNode[N+1];
                a=new int[N+1];
                for(int j=1;j<=N;j++) node[j]=new TreeNode(j,in.nextInt());
                for(int j=0;j<N-1;j++){
                    id1=in.nextInt();
                    id2=in.nextInt();
                    node[id1].addchild(node[id2]);
                    node[id2].addchild(node[id1]);
                }

                    queue.enqueue(node[1]);
                   while(!queue.isEmpty()) {
                        num=0;
                        size=queue.size();
                        for(int k=0;k<size;k++){
                           root=queue.dequeue();
                           a[root.id]=-1;
                           if(root.color==1) num+=1;//可以改进
                            size2=root.sizechild();
                           for(int m=0;m<size2;m++){
                               child=root.childList.get(m);
                              if(a[child.id]!=-1) queue.enqueue(child);
                           }
                        }
                        if(num%2==1) {
                            win=true;
                            break;
                        }
                }


                if(win) out.println("YES");
                   else out.println("NO");

            }
        }
        class queue{
            private TreeNode queue[];
            private int front;//front指向队列第一个的前一个
            private int rear;
            public queue(int size){
                queue=new  TreeNode [size];
                front=-1;
                rear=-1;
            }

            public boolean isEmpty(){ return front==rear; }
            public void enqueue( TreeNode num){
                queue[++rear]=num;
            }
            public TreeNode dequeue(){ return queue[++front]; }
            public int size(){ return rear-front; }
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
