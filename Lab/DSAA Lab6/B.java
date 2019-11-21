import java.io.*;
import java.util.StringTokenizer;
//打印中后序
public class B {
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

        class treeNode
        {
            treeNode left;
            treeNode right;
            int value;
            treeNode(int v, treeNode l, treeNode r)
            {
                value = v;
                left = l;
                right = r;
            }
        }


       public void preOrder(treeNode root)
        {
            if(root==null)
                return;
            else
            {
                System.out.print(root.value+" ");
                preOrder(root.left);
                preOrder(root.right);
            }
        }

       public void inOrder(treeNode root)
        {
            if(root==null)
                return;
            else
            {
                inOrder(root.left);
                System.out.print(root.value+" ");
                inOrder(root.right);
            }
        }

        static void postOrder(treeNode root)
        {
            if(root==null)
                return;
            else
            {
                postOrder(root.left);
                postOrder(root.right);
                System.out.print(root.value+" ");
            }
        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int N,f,s;
            int a[];
            treeNode root;
            for(int i=0;i<T;i++) {
                N=in.nextInt();
                treeNode node[]= new treeNode[N+1];
                root=new treeNode(0,null,null);
                int child[]=new int[N+1];
                for(int j=1;i<=N;i++)
                    node[i] = new treeNode(i,null,null);

                for(int j=0;j<N-1;j++){
                    f=in.nextInt();
                    s=in.nextInt();

                    if(node[f].left==null) node[f].left=node[s];
                    else node[f].right=node[s];
                    child[s]=1;
                }

                for(int j=1;j<N+1;j++){
                    if(child[j]==0){
                        root=node[j];
                        break;
                    }
                }
                preOrder(root);
                System.out.println();
                inOrder(root);
                System.out.println();
                postOrder(root);
                if(i!=T-1)  System.out.println();

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