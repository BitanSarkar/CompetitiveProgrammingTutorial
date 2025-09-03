package old;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class AbridgedProblemElementMaskingOptimized {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Enter number of groups
        int n = sc.nextInt();
        sc.nextLine();
        double[] x = new double[2*n];
        double[] y = new double[2*n];
        boolean[] used = new boolean[2*n];
        //Enter all 2*n points
        for(int i = 0; i<2*n; i++){
            String[] ip = sc.nextLine().split("\\s+");
            x[i] = Double.parseDouble(ip[0]);
            y[i] = Double.parseDouble(ip[1]);
            used[i] = true;
        }
        long init = System.currentTimeMillis();
        Map<String, Double> map = new HashMap<String, Double>();
        System.out.println(minDistanceSum(x, y, map, used));
        long end = System.currentTimeMillis();
        System.out.println("Execution time : " + (end-init) + "ms");
        System.out.println();
        System.out.println();
        System.out.println();
        for (Map.Entry<String,Double> entry : map.entrySet()) 
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());
        System.out.println();
        System.out.println();
        System.out.println();
        sc.close();
    }

    private static double distance(double x0,double x1, double y0,double y1){
        return Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
    }

    private static double minDistanceSum(double[] x, double[] y, Map<String, Double> map, boolean[] used) {
        if(x==null || y==null) return Double.MAX_VALUE;
        int usedCtr = 0;
        int idx1 = 0;
        int idx2 = 0;
        for(int i = 0; i<used.length; i++) {
            if(used[i]) {
                if(usedCtr == 0) idx1 = i;
                if(usedCtr == 1) idx2 = i;
                usedCtr++;
                if(usedCtr>2) break;
            }
        }
        if(usedCtr<=1) return Double.MAX_VALUE;
        if(usedCtr==2) return distance(x[idx1], x[idx2], y[idx1], y[idx2]);
        String key = Arrays.toString(used);
        if(map.containsKey(key)) return map.get(key);
        double minDistance = Double.MAX_VALUE;
        for(int i = 0; i<used.length; i++) {    
            for(int j = i+1; j<used.length; j++) {
                if(used[i] && used[j] && i!=j) {
                    used[i] = false;
                    used[j] = false;
                    double currentDistance = distance(x[i], x[j], y[i], y[j]) + minDistanceSum(x, y, map, used);
                    minDistance = minDistance >= currentDistance ? currentDistance : minDistance;
                    used[i] = true;
                    used[j] = true;
                }
            }
        }
        map.put(key, minDistance);
        return minDistance;
    }
}