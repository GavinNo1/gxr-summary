package alg;

public class ArrayMinMax {
    private static int min;
    private static int max;
    public static void minMax(int A[],int n) {
        int i;
        if(n%2==0) {
            if(A[0]>A[1]) {
                max=A[0];
                min=A[1];
            } else {
                max=A[1];
                min=A[0];
            }
            for(i=2;i<n-1;i=i+2) {
                if(A[i]>A[i+1]) {
                    if(max<A[i])
                        max=A[i];
                    if(min>A[i+1])
                        min=A[i+1];
                } else {
                    if(max<A[i+1])
                        max=A[i+1];
                    if(min>A[i])
                        min=A[i];
                }
            }
        } else {
            max=min=A[0];
            for(i=1;i<n-1;i=i+2) {
                if(A[i]>A[i+1]) {
                    if(max<A[i])
                        max=A[i];
                    if(min>A[i+1])
                        min=A[i+1];
                } else {
                    if(max<A[i+1])
                        max=A[i+1];
                    if(min>A[i])
                        min=A[i];
                }
            }
        }
    }


    public static void main(String[] args) {
        int A[]={8,9,50,7,30, 89};
        minMax(A, 6);
        System.err.println(max + "*******" + min);
    }
}
