package old;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class MinimumPerimeterSumProblemBitMaskOptimizedWithGroup {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Enter number of groups
        int n = sc.nextInt();
        sc.nextLine();
        int[] x = new int[3*n];
        int[] y = new int[3*n];
        int used = (1<<(3*n)) - 1;
        MinListGroup[] usedMap = new MinListGroup[used+1];
        //Enter all 3*n points
        for(int i = 0; i<3*n; i++){
            String[] ip = sc.nextLine().split("\\s+");
            x[i] = Integer.parseInt(ip[0]);
            y[i] = Integer.parseInt(ip[1]);
        }
        List<int[][]> list = new ArrayList<int[][]>();
        long init = System.currentTimeMillis();
        System.out.println(minPerimeterSum(x, y, usedMap, used, 3*n, list));
        list.forEach(arr -> System.out.println(Arrays.deepToString(arr)));
        long end = System.currentTimeMillis();
        System.out.println("Execution time : " + (end-init) + "ms");
        sc.close();
    }

    
    private static double perimeter(double x0,double x1,double x2,double y0,double y1,double y2){
        return  Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1)) + 
                Math.sqrt((x0-x2)*(x0-x2) + (y0-y2)*(y0-y2)) + 
                Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    private static double minPerimeterSum(int[] x, int[] y, MinListGroup[] usedMap, int used, int total, List<int[][]> list) {
        if(x==null || y==null) return Double.MAX_VALUE;
        int usedCtr = Integer.bitCount(used);
        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;
        int innerCtr = 0;
        for(int i = 0; i<total; i++) {
            if(((used >> i) & 1) == 1) {
                if(innerCtr == 0) idx1 = i;
                if(innerCtr == 1) idx2 = i;
                if(innerCtr == 2) {
                    idx3 = i;
                    break;
                }
                innerCtr++;
            }
        }
        if(usedCtr<=2) return Double.MAX_VALUE;
        if(usedCtr==3) {
            list.add(new int[][]{new int[]{(int)x[idx1], (int)y[idx1]}, new int[]{(int)x[idx2], (int)y[idx2]}, new int[]{(int)x[idx3], (int)y[idx3]}});
            return perimeter(x[idx1], x[idx2], x[idx3], y[idx1], y[idx2], y[idx3]);
        }
        Integer key = used;
        if(usedMap[key]!=null) {
            list.addAll(usedMap[key].group);
            return usedMap[key].perimeter;
        }
        List<int[][]> tempList = new ArrayList<int[][]>();
        List<int[][]> minList = new ArrayList<int[][]>();
        double minPerimeter = Double.MAX_VALUE;
        for(int i = 0; i<total; i++) {    
            for(int j = i+1; j<total; j++) {
                for(int k = j+1; k<total; k++) {                    
                    int ithBit = (used>>i)&1;
                    int jthBit = (used>>j)&1;
                    int kthBit = (used>>k)&1;
                    if(!isCollinear(x[i], y[i], x[j], y[j], x[k], y[k]) && ithBit == 1 && jthBit == 1 && kthBit == 1 && i!=j && k!=j && k!=i){
                        tempList.add(new int[][]{new int[]{(int)x[i], (int)y[i]}, new int[]{(int)x[j], (int)y[j]}, new int[]{(int)x[k], (int)y[k]}});
                        used = used & ~(1<<i) & ~(1<<j) & ~(1<<k);
                        double currentPerimeter = perimeter(x[i], x[j], x[k], y[i], y[j], y[k]) + minPerimeterSum(x, y, usedMap, used, total, tempList);
                        if(currentPerimeter < minPerimeter) { // '<' -> minPerimeter, '>' -> maxPerimeter
                            minList.clear();
                            minList.addAll(tempList);
                            minPerimeter = currentPerimeter;
                        }
                        tempList.clear();
                        used = used | (1<<i) | (1<<j) | (1<<k);
                    }
                }
            }
        }
        usedMap[key] = new MinListGroup(minPerimeter, minList);
        list.addAll(minList);
        return minPerimeter;
    }

    public static boolean isCollinear(int x1, int y1, int x2, int y2, int x3, int y3) {
        long val = (long)(x2 - x1) * (y3 - y1) - (long)(y2 - y1) * (x3 - x1);
        return val == 0;
    }
}

class MinListGroup {
    double perimeter;
    List<int[][]> group;
    MinListGroup(
        double perimeter,
        List<int[][]> group){
            this.group=group;
            this.perimeter=perimeter;
    }
}
