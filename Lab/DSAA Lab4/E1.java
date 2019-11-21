import java.io.*;
        import java.util.StringTokenizer;
        /*
        * 想问一下E题最小字典序的那个大概要怎么做
2018/10/4 11:32:45
上海 姚寅喆 2018/10/4 11:32:45
首先我们一定能第一个打1对吧

上海 姚寅喆 2018/10/4 11:32:58
对于后面的每一个，我们有2种选择

上海 姚寅喆 2018/10/4 11:33:16
一种是打当前栈顶的那个

上海 姚寅喆 2018/10/4 11:33:33
一种是打后面的牌中最小的那个

上海 姚寅喆 2018/10/4 11:33:38
每次贪心就好了
*/

public class E1 {
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

        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt();
            int n,num,one,min;
            queue order,queue1;
            stack stack1,minstack;
            for(int i=0;i<T;i++){
                n=in.nextInt();
                one=0;
                min=300001;
                minstack=new stack(n);
                order=new queue(n);
                queue1=new queue(n);
                stack1=new stack(n);
                for(int j=0;j<n;j++){
                    num=in.nextInt();
                    if(num==1){
                        order.add(num);
                        one=j;
                        break;
                    }
                    stack1.push(num);
                }

                for(int j=one+1;j<n;j++){
                    num=in.nextInt();
                    queue1.add(num);
                }

                for(int j=queue1.rear;j>=0;j--){
                    num=queue1.queue[j];
                    if(num<min){
                        min=num;
                    }
                    minstack.push(min);
                }

                while(!stack1.isEmpty()&&!queue1.isEmpty()){
                    if(stack1.topValue()<minstack.topValue()){
                        order.add(stack1.pop());
                    }else{
                        min=minstack.topValue();
                        while(minstack.topValue()==min){
                            stack1.push(queue1.delete());
                            minstack.pop();
                            if(minstack.isEmpty()) break;
                        }
                    }
                }

                while(!stack1.isEmpty()){
                    order.add(stack1.pop());
                }


                while(!queue1.isEmpty()){

                    if(stack1.isEmpty()){
                        min=minstack.topValue();
                        while(min==minstack.topValue()){
                        stack1.push(queue1.delete());
                        minstack.pop();
                        if(minstack.isEmpty()) break;
                    }

                    }else{

                        if(stack1.topValue()<minstack.topValue()){
                            order.add(stack1.pop());
                        }else{
                            min=minstack.topValue();
                            while(minstack.topValue()==min){
                                stack1.push(queue1.delete());
                                minstack.pop();
                                if(minstack.isEmpty()) break;
                            }
                        }
                    }

                    /*
                    *
                    if(stack1.isEmpty()||stack1.topValue()>minstack.topValue()){
                        min=minstack.topValue();
                        while(min==minstack.topValue()){
                        stack1.push(queue1.delete());
                        minstack.pop();
                        if(minstack.isEmpty()) break;
                    }else
                        if(stack1.topValue()<minstack.topValue()){
                            order.add(stack1.pop());
                        }
*/
                }

                while(!stack1.isEmpty()){
                    order.add(stack1.pop());
                }

                 out.print(order.delete());
                while(!order.isEmpty()){
                    out.print(" "+order.delete());
                }
                if(i!=T-1)   out.print("\n");
            }
        }

        class stack{
            private int stack[];
            private int top;

            public stack(int size){
                stack=new int[size];
                top=-1;
            }

            public boolean isEmpty() { return top==-1; }

            public void push(int num) { stack[++top]=num; }

            public int pop() { return stack[top--]; }

            public int topValue() { return stack[top]; }

        }

        class queue{
            private int queue[];
            private int front;//front指向队列第一个的前一个
            private int rear;
            public queue(int size){
                queue=new int [size];
                front=-1;//指向队列中第一个元素的前一个位置
                rear=-1;//指向队列中最后一个元素
            }

            public boolean isEmpty(){ return front==rear; }
            public void add(int num){
                queue[++rear]=num;
            }
            public int delete(){ return queue[++front]; }
            public int Front(){ return queue[front+1]; }
            public int Rear(){ return queue[rear];}
            public int size(){ return rear-front; }
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