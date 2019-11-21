import java.io.*;
import java.math.*;
import java.util.StringTokenizer;


public class E {
    public static void main(String[] args){
        try{
        InputStream inputStream = new FileInputStream("E:\\locatedata.txt");;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
        }catch(Exception e){
            System.out.println("not found");
        }
    }


    static class Task {
        class gift implements Comparable<gift>{
            int id;
            int time;
            int number;
            public gift( int id, int time, int number){
                this.id=id;
                this.time=time;
                this.number=number;
            }

            public int compareTo(gift p)
            {
                if(this.number!=p.number)
                    return this.number-p.number;
                else return p.time-this.time;
            }

        }


        class Heap{
            gift set[];
            int size;
            public Heap(int n){
                set=new gift[n+1];
                size=0;//set[0]是空位
            }

            void swap(int i,int j){
                gift temp=set[i];
                set[i]=set[j];
                set[j]=temp;
            }
            public void insert(gift n){
                set[++size]=n;
                int k=size;//新加进来的数字所在的位置
                int parent=size/2;
                if(size==1) return;
                while(n.compareTo(set[parent])>0){
                    swap(k,parent);
                    if(parent==1) break;
                    k=parent;
                    parent=parent/2;
                }
            }

            public void delete(){
                if(!this.isEmpty()) {
                    set[1] = set[size];
                    set[size] = null;
                    size--;
                    int k = 1, max;
                    while (2 * k <= size) {
                        if (2 * k + 1 <= size) {
                            if (set[2 * k].compareTo(set[2 * k + 1])>0 ) max = 2 * k;
                            else max = 2 * k + 1;
                        } else max = 2 * k;
                        if (set[k] .compareTo(set[max])<0) swap(k, max);
                        else break;
                        k = max;
                    }
                }
            }

            public boolean isEmpty(){
                return size==0;
            }

            public gift query(){ return set[1]; }

            public void checkdelet(int number[],int time[]){
                gift g;
               while(true){
                   g=this.query();
                   if(number[g.id]!=g.number||time[g.id]!=g.time){
                       this.delete();
                   }else{
                       number[g.id]=0;
                       time[g.id]=0;
                       this.delete();
                       break;
                   }
               }
            }
        }

        //大顶堆



        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int M,N,g,n,discard;
            int time[], number[];
            Heap heap;
           for(int i=0;i<T;i++) {
               discard=0;
               n=0;
               M=in.nextInt();
               N=in.nextInt();
               heap=new Heap(N);
               time=new int[1048578];number=new int[1048578];
               for(int j=1;j<=N;j++){
                   g=in.nextInt();
                   if(n<M&&number[g]==0){
                       time[g]=j;
                       number[g]=1;
                       heap.insert(new gift(g,j,1));
                       n++;
                   }else if(number[g]>0){
                       number[g]+=1;
                       heap.insert(new gift(g,time[g],number[g]));
                   }else if(n==M&&number[g]==0){
                       heap.checkdelet(number,time);
                       discard++;
                       heap.insert(new gift(g,j,1));
                       number[g]=1;
                       time[g]=j;
                   }
               }
               out.println(discard);
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
