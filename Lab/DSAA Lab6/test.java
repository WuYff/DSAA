import java.util.Arrays;

public class test {
    static class gift implements Comparable<gift>{
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

    static class Heap{
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

        public gift delete(){
            gift g=null;
            if(!this.isEmpty()) {
                g=set[1];
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
            return g;
        }

        public boolean isEmpty(){
            return size==0;
        }

        public gift query(){ return set[1]; }

        public void checkdelet(int number[],int time[]){
            gift g;
            while(true){
                g=this.query();
                if(number[g.id]!=g.number&&time[g.id]!=g.time){
                    this.delete();
                }else{
                    number[g.id]=0;
                    time[g.id]=0;
                    this.delete();
                    break;
                }
            }
        }

        public void p(){
            for(int i=1;i<=size;i++)
                System.out.println(set[i].id);
        }
    }
    public static void main(String[] args){
       gift g[]=new gift[3];

      g[1]=new gift(4,1,6);
      g[0]=new gift(5,10,6);
       g[2]= new gift(7,1,7);
        Arrays.sort(g);
        System.out.println(g[0].id);
        System.out.println(g[1].id);
        System.out.println(g[2].id);

    }
}
