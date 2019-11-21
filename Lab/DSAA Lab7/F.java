import java.io.*;
import java.math.*;
import java.util.StringTokenizer;

public class F {
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
             int base;
             int m;

             class Avlnode{
                    int size;//以此节点为根的树的大小
                    Avlnode left;
                    Avlnode right;
                    int pay;
                    int height;//当前节点的高度
                    public Avlnode(int pay){
                        this.size=1;
                        this.height=0;
                        this.left=null;
                        this.right=null;
                        this.pay=pay;

                    }
                    public Avlnode(int size,int height,int pay,  Avlnode left,Avlnode right){
                        this.size=size;
                        this.left=left;
                        this.right=right;
                        this.pay=pay;
                        this.height = height;

                    }

                }

                class avlTree{
                    Avlnode root;
                    public avlTree(int pay){
                        this.root=new Avlnode(pay);
                    }

                }

                //a是失衡点
                public Avlnode rotater(Avlnode a){
                    //右旋一次
                    Avlnode b=a.left;
                    a.left=b.right;
                    b.right=a;
                    //维护height
                    a.height=a.left.height>a.right.height?a.left.height+1:a.right.height+1;
                    b.height=b.left.height>b.right.height?b.left.height+1:b.right.height+1;
                    //维护size
                    a.size=a.left.size+a.right.size+1;
                    b.size=b.left.size+b.right.size+1;
                   return b;
                }

                public Avlnode rotatel(Avlnode a){
                    //左旋一次
                    Avlnode b=a.right;
                    a.right=b.left;
                    b.left=a;
                    //维护height
                    a.height=a.left.height>a.right.height?a.left.height+1:a.right.height+1;
                    b.height=b.left.height>b.right.height?b.left.height+1:b.right.height+1;
                    //维护size
                    a.size=a.left.size+a.right.size+1;
                    b.size=b.left.size+b.right.size+1;

                    return b;
                }

                //lr
                public Avlnode rotatelr(Avlnode a){
                    //先左旋,将a变为ll
                    a.left=rotatel(a.left);
                    // 再右旋
                    return rotater(a);

                }
                public Avlnode rotaterl(Avlnode a){
                    //先右旋,将a变为rr
                    a.right=rotater(a.right);
                    // 再左旋
                    return rotatel(a);
                }

                public  Avlnode insert(int pay,Avlnode x) {

                    pay=pay+base;
                    if (x == null) {
                        x = new Avlnode(pay);
                        return x;
                    }

                    if (x.pay > pay) {
                        x.left = insert(pay, x.left);

                        if (x.left.height - x.right.height == 2) {
                            //ll右旋
                            rotater(x);
                        } else if (x.left.height - x.right.height == -2) {
                            //lr先左旋再右旋
                            rotatelr(x);
                        }

                    } else if (x.pay <= pay) {
                        x.right = insert(pay, x.right);

                        if (x.left.height - x.right.height == 2) {
                            //rl先右旋再左旋
                            rotaterl(x);

                        } else if (x.left.height - x.right.height == -2) {
                            //rr左旋
                            rotatel(x);


                        }//如果有pay相同的点该怎么办？？？？？

                    }
                    x.height = x.left.height > x.right.height ? x.left.height + 1 : x.right.height + 1;
                    x.size=x.left.size+x.right.size+1;
                    return x;
                }

                public int query(int k,Avlnode root) {

                  //查找第k大
                    if(root.size<k) return -1;
                    if(root.right!=null){
                        if(root.right.size==k-1){
                            //那么root就是
                            return root.pay;
                        }else if (root.right.size>k-1){
                            //从右子树找
                            query( k, root.right);
                        }else if(root.right.size<k-1){
                            //要找左子树了
                            query( k, root.left);
                        }
                    }else if(root.right==null&&root.left!=null){
                        //只有左子树，没有右子树
                        if(k==1) return root.pay;
                        else query(k, root.left);


                    }else if(root.right==null&&root.left==null){
                        if(k==1) return root.pay;
                        else return -1;
                    }

                    return -1;

                }

                public  Avlnode delete(int m,Avlnode x){
                   if(x.pay-base>=m){

                   }else if(x.pay-base<m){
                       if(x.left==null){

                           if(x.right==null){
                               //删除x



                           }else if(x.right.pay-base>=m){
                               //删除x



                           }else if(x.right.pay-base<m){
                               //要删除有字数的节点才可以！

                           }

                       }
                   }

                }


                //改变工资基准
                public void subtract(int x){
                    base+=x;



                }
                public void add(int x){
                    base-=x;
                }


        public void solve(InputReader in, PrintWriter out) {
            int T=in.nextInt(),n;

           for(int i=0;i<T;i++) {
               n=in.nextInt();
               m=in.nextInt();
               for(int j=0;j<n;j++){

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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch(IOException e) {
                return false;
            }
        }
        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}