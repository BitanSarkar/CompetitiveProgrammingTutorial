import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class MinimumPerimeterSumProblemOptimized {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Enter number of groups
        int n = sc.nextInt();
        sc.nextLine();
        Point[] points = new Point[3*n];
        //Enter all 3*n points
        for(int i = 0; i<3*n; i++){
            String[] ip = sc.nextLine().split("\\s+");
            points[i] = new Point(Double.parseDouble(ip[0]), Double.parseDouble(ip[1]));
        }
        long init = System.currentTimeMillis();
        Map<String, Double> map = new HashMap<String, Double>();
        System.out.println(minPerimeterSum(points, map));
        long end = System.currentTimeMillis();
        System.out.println("Execution time : " + (end-init) + "ms");
        sc.close();
    }

    private static double minPerimeterSum(Point[] points, Map<String, Double> map) {
        if(points==null) return Double.MAX_VALUE;
        if(points.length<=2) return Double.MAX_VALUE;
        if(points.length==3) {
            return (new Triplet(points[0], points[1], points[2])).perimeter();
        }
        Arrays.sort(points);
        String key = "";
        for(int i = 0; i<points.length; i++){
            key = key + points[i].toString();
        }
        if(map.containsKey(key)) return map.get(key);
        double minPerimeter = Double.MAX_VALUE;
        for(int i = 0; i<points.length; i++) {    
            for(int j = i+1; j<points.length; j++) {
                for(int k = j+1; k<points.length; k++) {
                    Point[] remPoints = new Point[points.length-3];
                    Triplet currentTriplet = new Triplet(points[i], points[j], points[k]);
                    int ctr = 0;
                    for(int rem = 0; rem<points.length; rem++) {
                        if(rem!=i&&rem!=j&&rem!=k) {
                            remPoints[ctr] = points[rem];
                            ctr++;
                        }
                    }
                    minPerimeter = Math.min(currentTriplet.perimeter() + minPerimeterSum(remPoints, map),minPerimeter);
                }
            }
        }
        map.put(key, minPerimeter);
        return minPerimeter;
    }
}
class Point  implements Comparable<Point>{
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
class Triplet {
    Point A;
    Point B;
    Point C;
    Triplet(Point A, Point B, Point C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }
    double perimeter() {
        return  A.distance(B)+B.distance(C)+C.distance(A);
    }
    @Override
    public String toString() {
        return "("+this.A.x+","+this.A.y+") --- ("+this.B.x+","+this.B.y+") --- ("+this.C.x+","+this.C.y+")  =  " + perimeter();
    }
}

