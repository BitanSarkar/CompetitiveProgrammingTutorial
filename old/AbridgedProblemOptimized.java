package old;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class AbridgedProblemOptimized {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Enter number of groups
        int n = sc.nextInt();
        sc.nextLine();
        Point[] points = new Point[2*n];
        //Enter all 2*n points
        for(int i = 0; i<2*n; i++){
            String[] ip = sc.nextLine().split("\\s+");
            points[i] = new Point(Double.parseDouble(ip[0]), Double.parseDouble(ip[1]));
        }
        long init = System.currentTimeMillis();
        Map<String, Double> map = new HashMap<String, Double>();
        System.out.println(minDistanceSum(points, map));
        long end = System.currentTimeMillis();
        System.out.println("Execution time : " + (end-init) + "ms");
        
        // System.out.println();
        // System.out.println();
        // System.out.println();
        // for (Map.Entry<String,Double> entry : map.entrySet()) 
        //     System.out.println("Key = " + entry.getKey() +
        //                      ", Value = " + entry.getValue());
        // System.out.println();
        // System.out.println();
        // System.out.println();
        sc.close();
    }

    private static double minDistanceSum(Point[] points, Map<String, Double> map) {
        if(points==null) return Double.MAX_VALUE;
        if(points.length<=1) return Double.MAX_VALUE;
        if(points.length==2) {
            return points[0].distance(points[1]);
        }
        Arrays.sort(points);
        String key = "";
        for(int i = 0; i<points.length; i++){
            key = key + points[i].toString();
        }
        if(map.containsKey(key)){
            return map.get(key);
        }
        double minDistance = Double.MAX_VALUE;
        for(int i = 0; i<points.length; i++) {    
            for(int j = i+1; j<points.length; j++) {
                Point[] remPoints = new Point[points.length-2];
                Pair currentPair = new Pair(points[i], points[j]);
                int ctr = 0;
                for(int k = 0; k<points.length; k++) {
                    if(k!=i&&k!=j) {
                        remPoints[ctr] = points[k];
                        ctr++;
                    }
                }
                double currentDistance = currentPair.distace() + minDistanceSum(remPoints, map);
                if(currentDistance<=minDistance){
                    minDistance = currentDistance;
                }
            }
        }
        map.put(key, minDistance);
        return minDistance;
    }
}
class Point  implements Comparable<Point> {
    double x;
    double y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    double distance(Point ptr) {
        return Math.sqrt((this.x-ptr.x)*(this.x-ptr.x) + (this.y-ptr.y)*(this.y-ptr.y));
    }
    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";
    }
    @Override
    public int compareTo(Point o) {
        if(this.x==o.x && this.y==o.y) return 0;
        if(this.y*o.x == this.x*o.y) return this.x*this.x + this.y*this.y - o.x*o.x + o.y*o.y>0?1:-1;
        return this.y*o.x > this.x*o.y?1:-1;
    }
}
class Pair {
    Point A;
    Point B;
    Pair(Point A, Point B) {
        this.A = A;
        this.B = B;
    }
    double distace() {
        return Math.sqrt((this.A.x-this.B.x)*(this.A.x-this.B.x) + (this.A.y-this.B.y)*(this.A.y-this.B.y));
    }
    @Override
    public String toString() {
        return "("+this.A.x+","+this.A.y+") --- ("+this.B.x+","+this.B.y+")  =  " + distace();
    }
}