import java.io.*;
import java.util.StringTokenizer;

public class B{
    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }
//中序遍历严格递增
    static class Task {

        int order[];
        int index;
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
        class stack{
            private treeNode stack[];
            private int top;

            public stack(int size){
                stack=new treeNode[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(treeNode c) { stack[++top]=c; }


            public treeNode pop() { return stack[top--]; }

        }

        public void inOrder(treeNode root)
        {
            stack s = new stack(100005);
            while(root!=null||!s.isEmpty()){

                while(root!=null){
                    s.push(root);
                    root=root.left;
                }

                if(!s.isEmpty()){
                    root=s.pop();
                    order[index++]=root.value;
                    root=root.right;
                }
            }

        }

        public boolean ifBST(treeNode root,int n){
            boolean b=true;
            inOrder(root);
            for(int i=0;i<n-1;i++){
                if(order[i]>=order[i+1]){
                    b=false;
                    break;
                }
        }
            return b;

        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(), n, a[],stop;
            treeNode root,node[];
            boolean judge;
           for(int i=0;i<T;i++) {
               n=in.nextInt();
               node=new treeNode[n+1];
               root=null;
               a=new int[n+1];
               order=new int[n];
               index=0;
               judge=true;
               for(int j=1;j<=n;j++){
                   node[j]=new treeNode(in.nextInt(),null,null);
               }

               for(int j=0;j<n-1;j++){
                   int f=in.nextInt();
                   int s=in.nextInt();
                   if(node[f].value>node[s].value){
                       if(node[f].left==null){
                           node[f].left=node[s];
                           a[s]=1;
                       }else {
                           judge=false;
                           for(int m=j+1;m<n-1;m++){
                               f=in.nextInt();
                               s=in.nextInt();
                           }
                           break;
                       }
                   }else if(node[f].value<node[s].value){
                       if(node[f].right==null){
                           node[f].right=node[s];
                           a[s]=1;
                       }else {

                           judge=false;
                           for(int m=j+1;m<n-1;m++){
                               f=in.nextInt();
                               s=in.nextInt();
                           }
                           break;
                       }
                   }else if(node[f].value==node[s].value){
                       judge=false;
                       for(int m=j+1;m<n-1;m++){
                           f=in.nextInt();
                           s=in.nextInt();
                       }
                       break;
                   }
               }

               if(judge){
                   for(int j=1;j<=n;j++){
                       if(a[j]==0){
                           root=node[j];
                           break;
                       }
                   }

                   if(ifBST(root,n)) out.println("Case #"+(i+1)+": YES");
                   else out.println("Case #"+(i+1)+": NO");


               }else out.println("Case #"+(i+1)+": NO");


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