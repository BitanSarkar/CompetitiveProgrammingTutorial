package old;
import java.util.Arrays;
import java.util.Scanner;
public class AbridgedProblemBitMaskingOptimized {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Enter number of groups
        int n = sc.nextInt();
        sc.nextLine();
        double[] x = new double[2*n];
        double[] y = new double[2*n];
        int used = (1<<(2*n)) - 1;
        double[] usedMap = new double[used+1];
        Arrays.fill(usedMap, Double.MAX_VALUE);
        //Enter all 2*n points
        for(int i = 0; i<2*n; i++){
            String[] ip = sc.nextLine().split("\\s+");
            x[i] = Double.parseDouble(ip[0]);
            y[i] = Double.parseDouble(ip[1]);
        }
        long init = System.currentTimeMillis();
        System.out.println(minDistanceSum(x, y, usedMap, used, 2*n));
        long end = System.currentTimeMillis();
        System.out.println("Execution time : " + (end-init) + "ms");
        sc.close();
    }

    private static double distance(double x0,double x1, double y0,double y1){
        return Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
    }

    private static double minDistanceSum(double[] x, double[] y, double[] usedMap, int used, int total) {
        if(x==null || y==null) return Double.MAX_VALUE;
        int usedCtr = Integer.bitCount(used);
        int idx1 = 0;
        int idx2 = 0;
        int innerCtr = 0;
        for(int i = 0; i<total; i++) {
            if(((used >> i) & 1) == 1) {
                if(innerCtr == 0) idx1 = i;
                if(innerCtr == 1) {
                    idx2 = i;
                    break;
                }
                innerCtr++;
            }
        }
        if(usedCtr<=1) return Double.MAX_VALUE;
        if(usedCtr==2) return distance(x[idx1], x[idx2], y[idx1], y[idx2]);
        Integer key = used;
        if(usedMap[key]!=Double.MAX_VALUE) return usedMap[key];
        double minDistance = Double.MAX_VALUE;
        for(int i = 0; i< total; i++) {    
            for(int j = i+1; j< total; j++) {
                int ithBit = (used>>i)&1;
                int jthBit = (used>>j)&1;
                if(ithBit == 1 && jthBit == 1 && i!=j) {
                    used = used & ~(1<<i);
                    used = used & ~(1<<j);
                    double currentDistance = distance(x[i], x[j], y[i], y[j]) + minDistanceSum(x, y, usedMap, used, total);
                    minDistance = minDistance >= currentDistance ? currentDistance : minDistance;
                    used = used | (1<<i);
                    used = used | (1<<j);
                }
            }
        }
        usedMap[key] = minDistance;
        return minDistance;
    }
}