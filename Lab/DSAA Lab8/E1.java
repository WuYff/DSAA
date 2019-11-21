import java.io.*;

public class E1 {
    public static void main(String[] args){

        Reader in =new Reader();
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();

    }

    static class Task {
        class node
        {

            int j;
            int k;
            int color;
            node(int j,int k,int color)
            {
                this.j=j;
                this.k=k;
                this.color=color;
            }
        }

        class Stack{
            private node stack[];
            private int top;

            public Stack(int size){
                stack=new node[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(node num) { stack[++top]=num; }

            public node pop() { return stack[top--]; }

            public node top() { return stack[top]; }

        }

        public void dfs(node a[][],int j, int k,int m,int n,int visit[][],Stack stack){
            node v;
            boolean b;
            stack.push(a[j][k]);
            while(!stack.isEmpty()){
                v=stack.top();
                b=false;
                //入栈
                if(v.k==0){//第一例
                        //the last column
                        if(visit[v.j][m-1]==0&&a[v.j][m-1].color==v.color){
                            stack.push(a[v.j][m-1]);
                            visit[v.j][m-1]=1;
                            b=true;
                        }

                        if(visit[v.j][1]==0&&a[v.j][1].color==v.color){
                            stack.push(a[v.j][1]);
                            visit[v.j][1]=1;
                            b=true;
                        }


                }else if(v.k==m-1){//最后一列
                        //the first column
                        if(visit[v.j][0]==0&&a[v.j][0].color==v.color){
                            stack.push(a[v.j][0]);
                            visit[v.j][0]=1;
                            b=true;
                        }

                        //最后一列的前一列
                        if(visit[v.j][m-2]==0&&a[v.j][m-2].color==v.color){
                            stack.push(a[v.j][m-2]);
                            visit[v.j][m-2]=1;
                            b=true;
                        }



                }else{//不在最后一列，也不再第一列
                    if(v.k-1>=0){
                        //左一列
                        if(visit[v.j][v.k-1]==0&&a[v.j][v.k-1].color==v.color){
                            stack.push(a[v.j][v.k-1]);
                            visit[v.j][v.k-1]=1;
                            b=true;
                        }
                    }
                    if(v.k+1<=n-1){
                        //右一列
                        if(visit[v.j][v.k+1]==0&&a[v.j][v.k+1].color==v.color){
                            stack.push(a[v.j][v.k+1]);
                            visit[v.j][v.k+1]=1;
                            b=true;
                        }
                    }

                }
                //行
                if(v.j-1>=0){//前一行
                    if(visit[v.j-1][v.k]==0&&a[v.j-1][v.k].color==v.color){
                        stack.push(a[v.j-1][v.k]);
                        visit[v.j-1][v.k]=1;
                        b=true;
                    }
                }
                if(v.j+1<=n-1){//后一行
                    if(visit[v.j+1][v.k]==0&&a[v.j+1][v.k].color==v.color){
                        stack.push(a[v.j+1][v.k]);
                        visit[v.j+1][v.k]=1;
                        b=true;
                    }
                }

                if(!b){
                    stack.pop();
                 //   visit[v.j][v.k]=2;
                }




            }

        }



        public void solve(Reader in, PrintWriter out) {
            try{
            int T=in.nextInt(),n,m,num;
            node a[][];
            int visit[][];
            Stack stack;
            for(int i=0;i<T;i++) {

                num=0;
                n=in.nextInt();
                m=in.nextInt();
                a=new node[n][m];
                visit=new int[n][m];
                for(int j=0;j<n;j++){
                    for(int k=0;k<m;k++){
                        a[j][k]=new node(j,k,in.nextInt());
                    }
                }

                stack=new Stack(m*n+1);
                for(int j=0;j<n;j++){
                    for(int k=0;k<m;k++){
                        if(visit[j][k]==0){
                            dfs( a, j,  k, m, n, visit,stack);
                            num++;
                        }

                    }
                }
                out.println(num);
            }
            }catch(Exception e){
                System.out.println("Exception");

            }
        }

    }



    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}
