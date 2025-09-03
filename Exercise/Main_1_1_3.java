public class Main_1_1_3 {

    public static void main(String[] args) {
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
        boolean[] visited = new boolean[n];
        return dfs(n, points, visited);
    }

    public double dfs(int n, int[][] points, boolean[] visited) {
        if(n < 2) return 0;
        double min = Double.MAX_VALUE;
        for(int i = 0; i < points.length; i++) {
            for(int j = i+1; j < points.length; j++) {
                if(!visited[i] && !visited[j]) {
                    int x1 = points[i][0];
                    int y1 = points[i][1];
                    int x2 = points[j][0];
                    int y2 = points[j][1];
                    double dist1 = Math.hypot(x1 - x2, y1 - y2);
                    visited[i] = true;
                    visited[j] = true;
                    double dist2 = dfs(n-2, points, visited);
                    min = Math.min(dist1 + dist2, min);
                    visited[i] = false;
                    visited[j] = false;

                }
            }
        }
        return min;
    }
}