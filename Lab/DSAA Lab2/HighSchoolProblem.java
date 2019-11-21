
//F(x) = 5x^7+6x^6+3x^3+4x^2-2xy
//F'(x) = 35x^6+36x^5+9x^2+8x-2y  单调递增
//F''(x) = 210x^5+180x^4+18x+8    >0
//F'(100)>0 For every y
//F'(0)<0   For every
//0 <= x <=100
//Find x that let F'(x)=0;
import java.util.Scanner;
    public class HighSchoolProblem {
        //计算F导数
        public static double F(double y,double x){ return 35*x*x*x*x*x*x+36*x*x*x*x*x+9*x*x+8*x-2*y; }
        //计算函数
        public static double Min(double y,double x){ return 5*x*x*x*x*x*x*x+6*x*x*x*x*x*x+3*x*x*x+4*x*x-2*x*y; }
        //二分法
        public static double FindTheMin(double y,double left,double right){
            double mid=(left+right)/2;
            double f=F(y,mid);
            if(f>=-0.000001&&f<=0.000001) return Min(y,mid);
            if(f>0.000001) return FindTheMin(y,left,mid);
            else return FindTheMin(y,mid,right);//易错点：不能传入left+-1这种，

    }
    public static void main(String[] args)
    { Scanner in =new Scanner(System.in);
        int nTest=in.nextInt();
        double y;
        for(int i=1;i<=nTest;i++){
            y=in.nextDouble();
            System.out.println("Case "+i+": "+String.format("%.4f",FindTheMin(y,0.0,100.0)));
        }
    }
}
