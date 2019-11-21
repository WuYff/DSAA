import java.io.*;
import java.util.StringTokenizer;

//heap
public class C {
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
        class Heap{
            int set[];
            int size;
            public Heap(int n){
                set=new int[n];
                size=0;//set[0]是空位
            }

            void swap(int i,int j){
                int temp=set[i];
                set[i]=set[j];
                set[j]=temp;
            }
            public void insert(int n){
                set[++size]=n;
                int k=size;//新加进来的数字所在的位置
                int parent=size/2;
                while(n<set[parent]){
                    swap(k,parent);
                    if(parent==1) break;
                    k=parent;
                    parent=parent/2;
                }
            }

            public void delete(){
                if(!this.isEmpty()) {
                    set[1] = set[size];
                    set[size] = 0;
                    size--;
                    int k = 1, min;
                    while (2 * k <= size) {
                        if (2 * k + 1 <= size) {
                            if (set[2 * k] <= set[2 * k + 1]) min = 2 * k;
                            else min = 2 * k + 1;
                        } else min = 2 * k;
                        if (set[k] > set[min]) swap(k, min);
                        else break;
                        k = min;
                    }

                }

            }
            public boolean isEmpty(){
                return size==0;
            }

            public int query(){
                return set[1];
            }
        }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int n1,q,num;
            int a[];
            Heap heap;
            for(int i=0;i<T;i++) {
                n1=in.nextInt();
                heap=new Heap(200005);
                for(int j=0;j<n1;j++) heap.insert(in.nextInt());
                q=in.nextInt();
                for(int j=0;j<q;j++){
                    num=in.nextInt();
                    switch (num){
                        case 1:
                           heap.insert(in.nextInt());
                           break;
                        case 2:
                            heap.delete();
                            break;
                        case 3:
                            out.println(heap.query());
                            break;
                    }


                }

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