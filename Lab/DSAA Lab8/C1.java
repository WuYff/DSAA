import java.io.*;

public class C1 {
    public static void main(String[] args){

            Reader in =new Reader();
            OutputStream outputStream = System.out;
            PrintWriter out = new PrintWriter(outputStream);
            Task solver = new Task();
            solver.solve(in, out);
            out.close();

    }

    static class Task {
        class Node {
            int id;
            Node next;


            public Node(int id) {
                this.id = id;
                this.next = null;
            }
        }

        class LinkedList {
            private Node first;
            private Node last;

            public void insert(Node node) {
                if (first == null) {
                    first = node;
                    last = node;
                } else {
                    last.next = node;
                    last = node;
                }
            }

            public boolean isEmpty() {
                return first == null;
            }
        }

        class queue {
            private int queue[];
            private int front;//front指向队列第一个的前一个
            private int rear;

            public queue(int size) {
                queue = new int[size];
                front = -1;//指向队列中第一个元素的前一个位置
                rear = -1;//指向队列中最后一个元素
            }

            public boolean isEmpty() {
                return front == rear;
            }

            public void enqueue(int num) {
                queue[++rear] = num;
            }

            public int dequeue() {
                return queue[++front];
            }

            public int size() {
                return rear - front;
            }

        }

        public void solve(Reader in, PrintWriter out) {

            try{
                int T = in.nextInt(), n, m, s, x, y, record[], d, size, id;
                LinkedList graph[];
                queue q;
                Node node;
            for (int i = 0; i < T; i++) {
                n = in.nextInt();//n个点
                m = in.nextInt();//m条边
                s = in.nextInt();
                record = new int[n + 1];//记录距离
                q = new queue(n + 2);//最多n个点
                graph = new LinkedList[n + 1];
                for (int j = 1; j < n + 1; j++) {
                    graph[j] = new LinkedList();
                }
                //bulid the undirected graph
                for (int j = 0; j < m; j++) {
                    x = in.nextInt();
                    y = in.nextInt();
                    graph[x].insert(new Node(y));
                    graph[y].insert(new Node(x));

                }
                d = 1;
                node = graph[s].first;
                record[s]=-2;
                while (node != null) {
                    q.enqueue(node.id);
                    record[node.id] = d;
                    node = node.next;
                }

                while (!q.isEmpty()) {
                    size = q.size();
                    ++d;
                    for (int k = 0; k < size; k++) {
                        id = q.dequeue();
                        node = graph[id].first;
                        while (node != null) {
                            if (record[node.id] == 0) {
                                q.enqueue(node.id);
                                record[node.id] = d;
                            }
                            node = node.next;
                        }

                    }

                }

                for (int j = 1; j < n; j++) {
                    if (j == s) out.print(0 + " ");
                    else if (record[j] == 0) out.print(-1 + " ");
                    else out.print(record[j] + " ");
                }
                if (n == s) out.print(0 );
                else if(record[n] == 0) out.print(-1 );
                else out.print(record[n]);
                if (T != n - 1) out.print("\n");
            }

        }catch(Exception e){
               // out.println("Exception");

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