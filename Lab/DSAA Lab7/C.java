import java.io.*;
import java.util.StringTokenizer;


public class C {
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
        int order[];
        int index;
        boolean j;
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

        public int height(treeNode root)throws Exception{
            if(root==null){
                return 0;
            }
            //左子树
            int hl=height(root.left)+1;
            int hr=height(root.right)+1;
            if(hl-hr>1||hr-hl>1){
                throw new Exception(); //抛出异常
            }else {

               return  hl > hr ? hl : hr;
            }

        }
        public boolean checkHeight(treeNode root){
            try{
                height(root);
            }catch(Exception e){

                return false;
            }
            return true;

        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(), n, a[],stop,l,r;
            treeNode root,node[];
            boolean judge;
            for(int i=0;i<T;i++) {
                n=in.nextInt();
                node=new treeNode[n+1];
                root=null;
                a=new int[n+1];
                judge=true;
                index=0;
                order=new int[n];
                j=true;
                //构造树
                for(int j=1;j<=n;j++){
                    node[j]=new treeNode(in.nextInt(),null,null);
                }

                for(int j=1;j<=n;j++){
                    l=in.nextInt();
                    r=in.nextInt();

                    a[l]=1;
                    a[r]=1;
                    node[j].left=node[l];
                    node[j].right=node[r];
                }



               // if(judge) {
                    //找根
                    for (int j = 1; j <= n; j++) {
                        if (a[j] == 0) {
                            root = node[j];
                            break;
                        }
                    //}

                }
                if(ifBST(root,n)){
                    //判断高度

                    if(checkHeight( root)) out.println("Yes");
                    else out.println("No");
                }else out.println("No");

                // do sth


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