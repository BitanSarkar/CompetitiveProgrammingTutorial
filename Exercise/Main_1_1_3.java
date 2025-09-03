package Exercise;

public class Main_1_1_3 {

    public static void main(String[] args) {

        //6.47213595499958
        // 2.8284271247461903
        // 8.82842712474619
        // 13.48528137423857
        // 18.14213562373095

        Solution sol = new Solution();
        int N = 3;
        int[][] points = new int[][] {
            new int[]{1, 1}, new int[]{2, 3},
            new int[]{3, 5}, new int[]{5, 6},
            new int[]{2, 5}, new int[]{3, 2}
        };
        System.out.println(sol.shortestDistanceSum(N, points));
        N = 2;
        points = new int[][] {
            new int[]{1, 1}, new int[]{1, 2},
            new int[]{2, 0}, new int[]{2, 3}
        };
        System.out.println(sol.shortestDistanceSum(N, points));
        N = 6;
        points = new int[][] {
            new int[]{1, 1}, new int[]{1, 2},
            new int[]{2, 0}, new int[]{2, 3},
            new int[]{3, 4}, new int[]{4, 0},
            new int[]{4, 3}, new int[]{0, 3},
            new int[]{5, 6}, new int[]{0, 6},
            new int[]{6, 5}, new int[]{0, 5}
        };
        System.out.println(sol.shortestDistanceSum(N, points));
        N = 7;
        points = new int[][] {
            new int[]{1, 1}, new int[]{1, 2},
            new int[]{2, 0}, new int[]{2, 3},
            new int[]{3, 4}, new int[]{4, 0},
            new int[]{4, 3}, new int[]{0, 3},
            new int[]{5, 6}, new int[]{0, 6},
            new int[]{6, 5}, new int[]{0, 5},
            new int[]{7, 8}, new int[]{1, 7}
        };
        System.out.println(sol.shortestDistanceSum(N, points));
        N = 8;
        points = new int[][] {
            new int[]{1, 1}, new int[]{1, 2},
            new int[]{2, 0}, new int[]{2, 3},
            new int[]{3, 4}, new int[]{4, 0},
            new int[]{4, 3}, new int[]{0, 3},
            new int[]{5, 6}, new int[]{0, 6},
            new int[]{6, 5}, new int[]{0, 5},
            new int[]{7, 8}, new int[]{1, 7},
            new int[]{8, 7}, new int[]{1, 8}
        };
        System.out.println(sol.shortestDistanceSum(N, points));
    }
}

class Solution {

    public double shortestDistanceSum(int N, int[][] points) {
        int n = 2*N;
        int visited = (1 << n) - 1; 
        return dfs(n, points, visited);
    }

    public double dfs(int n, int[][] points, int visited) {
        if(n < 2) return 0;
        double min = Double.MAX_VALUE;
        int i = Integer.numberOfTrailingZeros(visited);
        for(int j = i+1; j < points.length; j++) {
            int mask1 = 1 << i;
            int mask2 = 1 << j;
            int v1 = visited & mask1;
            int v2 = visited & mask2;
            if(v1 != 0 && v2 != 0) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                double dist1 = Math.hypot(x1 - x2, y1 - y2);
                if(dist1 > min) continue;
                double dist2 = dfs(n-2, points, visited & (~mask1) & (~mask2));
                min = Math.min(dist1 + dist2, min);
            }
        }
        return min;
    }
}