import java.util.Scanner;

public class G{

    public static boolean Box(int l,int h,int w,int a,int b){

        //2h+2w/2h+l*3
        //2h+2w/l+h+w*4
        //2h+2w/2w+l*3
         if(2*w+2*h<=a)
             if(l+2*w<=b||l+w+h<=b||2*h+l<=b) return true;
        if(2*w+2*h<=b)
            if(l+2*w<=a||l+w+h<=a||2*h+l<=a) return true;
        //2l+2w/2l+h*3
        //2l+2w/l+h+w*4
        //2l+2w/2w+h*3
        if(2*l+2*w<=a)
            if(2*l+h<=b||l+w+h<=b||2*w+h<=b) return true;
        if(2*l+2*w<=b)
            if(2*l+h<=a||l+w+h<=a||2*w+h<=a) return true;
        //2l+2h/2l+w*3
        //2l+2h/l+h+w*4
        //2l+2h/2h+w*3
        if(2*l+2*h<=a)
            if(2*l+w<=b||l+w+h<=b||2*h+w<=b) return true;
        if(2*l+2*h<=b)
            if(2*l+w<=a||l+w+h<=a||2*h+w<=a) return true;



       /* //2l+h/l+2w
        if(2*l+h<=a&&l+2*w<b)  return true;
        if(2*l+h<=b&&l+2*w<a)  return true;
        //2w+h/w+2l
        if(2*w+h<=a&&w+2*l<b)  return true;
        if(2*w+h<=b&&w+2*l<a)  return true;
         //2h+l/h+2w
        if(2*h+l<=a&&h+2*w<b)  return true;
        if(2*h+l<=b&&h+2*w<a)  return true;
        */

        //2l+h+w/l+w+h*3
        //2l+h+w/2h+w*2
        //2l+h+w/h+2w*2
        if(2*l+h+w<=a)
            if(2*h+w<=b||l+w+h<=b||h+2*w<=b) return true;
        if(2*l+h+w<=b)
            if(2*h+w<=a||l+w+h<=a||h+2*w<=a) return true;
        //l+2h+w/l+w+h*3
        //l+2h+w/2l+w*2
        //l+2h+w/l+2w*2
        if(2*h+l+w<=a)
            if(2*l+w<=b||l+w+h<=b||l+2*w<=b) return true;
        if(2*h+l+w<=b)
            if(2*l+w<=a||l+w+h<=a||l+2*w<=a) return true;
        //l+h+2w/l+w+h*3
        //l+h+2w/l+2h*2
        //l+h+2w/2l+h*2
        if(2*w+l+h<=a)
            if(2*l+h<=b||l+w+h<=b||l+2*h<=b) return true;
        if(2*w+l+h<=b)
            if(2*l+h<=a||l+w+h<=a||l+2*h<=a) return true;


        //3l+h+w/w+h*1
        if(3*l+h+w<=a&&w+h<=b)  return true;
        if(3*l+h+w<=b&&w+h<=a)  return true;

        //l+h+3w/l+h*1
        if(l+h+3*w<=a&&l+h<=b)  return true;
        if(l+h+3*w<=b&&l+h<=a)  return true;

        //l+3h+w/l+w*1
        if(l+3*h+w<=a&&l+w<=b)  return true;
        if(l+3*h+w<=b&&l+w<=a)  return true;

        return false;
    }

    public static void main(String[] args) {
            Scanner in =  new Scanner(System.in);
            int n=in.nextInt();
            for(int i=1;i<=n;i++){
                if(Box(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt()))
                    System.out.println("Yes");
                else
                    System.out.println("No");
        }
    }
}
