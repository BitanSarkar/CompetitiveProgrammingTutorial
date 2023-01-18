import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class AbridgedProblemElementMasking {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //Enter number of groups
        int n = sc.nextInt();
        sc.nextLine();
        Point[] points = new Point[2*n];
        boolean[] used = new boolean[2*n];
        //Enter all 2*n points
        for(int i = 0; i<2*n; i++){
            String[] ip = sc.nextLine().split("\\s+");
            points[i] = new Point(Double.parseDouble(ip[0]), Double.parseDouble(ip[1]));
            used[i] = true;
        }
        long init = System.currentTimeMillis();
        List<Pair> list = new ArrayList<Pair>();
        Map<String, MinListGroup> map = new HashMap<String, MinListGroup>();
        System.out.println(minDistanceSum(points, list, map, used));
        list.forEach(pair -> System.out.println(pair.toString()));
        long end = System.currentTimeMillis();
        System.out.println("Execution time : " + (end-init) + "ms");
        sc.close();
    }

    private static double minDistanceSum(Point[] points, List<Pair> list, Map<String, MinListGroup> map, boolean[] used) {
        if(points==null) return Double.MAX_VALUE;
        int usedCtr= 0;
        for(int i = 0; i<used.length; i++) usedCtr += used[i]?1:0;
        if(usedCtr<=1) return Double.MAX_VALUE;
        if(usedCtr==2) {
            Point A = null;
            Point B = null;
            int ctrInner = 0;
            for(int i = 0; i<used.length; i++) {
                if(used[i]) {
                    if(ctrInner == 0) A = points[i];
                    if(ctrInner == 1){
                        B = points[i];
                        break;
                    }
                    ctrInner++;
                }
            }
            list.add(new Pair(A, B));
            return A.distance(B);
        }
        String key = Arrays.toString(used);
        if(map.containsKey(key)){
            list.addAll(map.get(key).group);
            return map.get(key).perimeter;
        }
        List<Pair> tempList = new ArrayList<Pair>();
        List<Pair> minList = new ArrayList<Pair>();
        double minDistance = Double.MAX_VALUE;
        for(int i = 0; i<points.length; i++) {    
            for(int j = i+1; j<points.length; j++) {
                if(used[i] && used[j] && i!=j) {
                    used[i] = false;
                    used[j] = false;
                    Pair currentPair = new Pair(points[i], points[j]);
                    tempList.add(currentPair);
                    double currentDistance = currentPair.distace() + minDistanceSum(points, tempList, map, used);
                    if(currentDistance<=minDistance){
                        minList.clear();
                        minList.addAll(tempList);
                        minDistance = currentDistance;
                    }
                    tempList.clear();
                    used[i] = true;
                    used[j] = true;
                }
            }
        }
        map.put(key, new MinListGroup(minDistance, minList));
        list.addAll(minList);
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
class MinListGroup {
    double perimeter;
    List<Pair> group;
    MinListGroup(
        double perimeter,
        List<Pair> group){
            this.group=group;
            this.perimeter=perimeter;
    }
}
