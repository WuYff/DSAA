import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
//longest distance between a given tree.
public class D {
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
        int max=0;
        class treeNode
        {
            int id;
            List<treeNode> childList;
            treeNode(int id)
            {
                this.id=id;
                childList=new ArrayList<>();
            }
            public void addchild(treeNode node){
                this.childList.add(node);
            }
            public int sizechild(){
                return this.childList.size();
            }


        }
        //子树的高和次高
        //子树的max路径？
        public  int height (int preId,treeNode root){
            int h,h1=0,h2=0;
            treeNode node;
            if(root.sizechild()==0){
                return 0;
            }

            for(int i=0;i<root.sizechild();i++){
                node=root.childList.get(i);
                if(preId==node.id) continue;
                h=height ( root.id,node);
                if(h>h1){
                    h2=h1;
                    h1=h;
                }else if(h>h2){ //h1>=h>h2
                    h2=h;
                }
            }

            if(h1+h2>max) max=h1+h2;
            //只返回当前子树的最大深度
            return h1+1;
        }

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int N;
            treeNode node[];
            int treen1,treen2;

            for(int i=0;i<T;i++) {
                max=0;
                N=in.nextInt();
                node=new treeNode[N+1];
                for(int j=1;j<=N;j++) node[j] = new treeNode(j);
                for(int j=0;j<N-1;j++) {
                    treen1=in.nextInt();
                    treen2=in.nextInt();
                    node[treen1].addchild(node[treen2]);
                    node[treen2].addchild(node[treen1]);

                }
                height (0, node[1]);
                out.println(max);


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