import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
//二分法最小化最大值
public class SportMeeting{
    public static void main(String[] args)
    {  InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

      /*  //二分法
        public int recursion(int left,int right,int position[],int nPeople,int p){
            int mid=(left+right)/2;
            if(left>=right)
                return mid;
            if( check(p,position,mid,nPeople))
                 return recursion(left,mid,position,nPeople,p);//如果试验数mid符合要求，递归前一半
            else
                 return  recursion(mid+1,right,position,nPeople,p);  //如果试验数mid不符合要求，递归后一半
        }*/
        //检查当前的mid是否满足跑步的人数
            public boolean check(int p,int position[],int mid,int nPeople){
            int run=0;//当前跑步者跑的历程
            int nrunner=1;//总的跑步的人数
            for(int i=0;i<p+1;i++) {
                //当前跑步者跑的里程是run，加上他从当前接力点点跑到下一个接力点的距离position[i] ，得到总历程run+ position[i]
                 //如果总里程run+ position[i] <=mid，说明可以跑到下一点,run就加上position[i]来更新当前人的里程,
                if (run + position[i] <= mid) {
                    run += position[i];

                } else {
                    //否则，当前的人跑不到下一个点。判断position[i]是否小于等于mid
                    //如果position[i]小于等于mid，就要让新的人跑
                    if(position[i]<=mid){
                        nrunner++;//人数增加一个
                        run = position[i];
                       if(nrunner>nPeople) return false;

                    } else{ //如果position[i]>mid，显然不满足
                        return false;
                    }
                }
            }
            return nrunner<=nPeople;
        }
            public void solve(InputReader in, PrintWriter out) {
                    int L, p,nPeople;//赛道长,多少个接力点,多少个跑步的人
                    int position[];//接力点的位置
                    int left=0;//algorithm
                    int right;//algorithm
                    int mid=0;
                    while (in.hasNext()) {
                        L=in.nextInt();
                        p=in.nextInt();//position
                        nPeople=in.nextInt();
                        position=new int[p+1];//???
                        left=0;
                        right=L;
                        for(int i=0;i<p;i++)
                            position[i]=in.nextInt();
                        position[p]=L;
                        Arrays.sort(position);

                        //将每个接力点之间的距离存入数组
                         for(int i=p;i>0;i--) {
                            position[i] = position[i] - position[i - 1];
                        }

                        while(right-1>left){
                            mid=(left+right)/2;
                            if( check( p,position,mid,nPeople)) right=mid;
                            else left=mid;
                        }

                        if( check( p,position,left,nPeople))
                            System.out.println(left);
                        else
                            System.out.println(right);

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

            public boolean hasNext() {
                while(tokenizer == null || !tokenizer.hasMoreElements())
                {
                    try
                    {
                        tokenizer = new StringTokenizer(reader.readLine());
                    } catch(Exception e)
                    {
                        return false;
                    }
                }
                return true;
            }
        }
}

