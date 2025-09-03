package old;
import java.util.*;

public class MinimumPerimeterSumProblemBitMaskOptimizedWithGroupLeetCode {
    public static void main(String[] args) {
        // int n = 7;
        // int[][] points = {
        //     {1, 1},
        //     {6, 3},
        //     {1, 0},
        //     {14, 14},
        //     {7, 4},
        //     {6, 4},
        //     {14, 15},
        //     {0, 1},
        //     {5, 8},
        //     {5, 6},
        //     {4, 6},
        //     {4, 8},
        //     {4, 7},
        //     {100, 101},
        //     {5, 5},
        //     {11, 10},
        //     {10, 10},
        //     {100, 100},
        //     {10, 11},
        //     {15, 14},
        //     {101, 100}
        // };
        
        // int n = 8;
        // int[][] points = {
        //     {1000, 0},
        //     {966, 259},
        //     {866, 500},
        //     {707, 707},
        //     {500, 866},
        //     {259, 966},
        //     {0, 1000},
        //     {-259, 966},
        //     {-500, 866},
        //     {-707, 707},
        //     {-866, 500},
        //     {-966, 259},
        //     {-1000, 0},
        //     {-966, -259},
        //     {-866, -500},
        //     {-707, -707},
        //     {-500, -866},
        //     {-259, -966},
        //     {0, -1000},
        //     {259, -966},
        //     {500, -866},
        //     {707, -707},
        //     {866, -500},
        //     {966, -259}
        // };
        
        // int n = 7;
        // int[][] points = {
        //     {1000, 0},
        //     {900, 433},
        //     {623, 782},
        //     {223, 975},
        //     {-223, 975},
        //     {-623, 782},
        //     {-900, 433},
        //     {-1000, 0},
        //     {-900, -433},
        //     {-623, -782},
        //     {-223, -975},
        //     {223, -975},
        //     {623, -782},
        //     {900, -433},
        //     {955, -295},
        //     {782, -623},
        //     {295, -955},
        //     {-295, -955},
        //     {-782, -623},
        //     {-955, -295},
        //     {-995, -105}
        // };
        
        // int n = 7;
        // int[][] points = {
        //     {0,0},{3,1},{7,4},{11,2},{14,9},{18,5},{21,13},{25,7},
        //     {28,16},{32,11},{35,20},{39,14},{42,23},{46,17},{49,26},{53,19},
        //     {56,29},{60,22},{63,31},{67,24},{70,34},{74,27},{77,36},{81,30}
        // };
        
        int n = 8;
        int[][] points = {
            // outer ring (radius ~1000, 12 pts)
            {1000,0},{866,500},{500,866},{0,1000},{-500,866},{-866,500},
            {-1000,0},{-866,-500},{-500,-866},{0,-1000},{500,-866},{866,-500},
            // inner ring (radius ~603, 12 pts, phase-shifted)
            {582,216},{216,582},{-216,582},{-582,216},{-582,-216},{-216,-582},
            {216,-582},{582,-216},{751,260},{260,751},{-260,751},{-751,260}
        };

        Solution sol = new Solution();
        Result res = sol.minimumPerimeterPartition(points);

        System.out.println(res.perimeter);
        if (res.perimeter != Double.MAX_VALUE) {
            for (int[][] tri : res.triangles) {
                System.out.println(Arrays.deepToString(tri));
            }
        } else {
            System.out.println("No valid partition into non-collinear triangles.");
        }
    }
}

class Result {
    double perimeter;
    List<int[][]> triangles;

    Result(double perimeter, List<int[][]> triangles) {
        this.perimeter = perimeter;
        this.triangles = triangles;
    }
}

class Solution {

    public Result minimumPerimeterPartition(int[][] points) {
        int n = points.length;
        if (n == 0 || n % 3 != 0) return new Result(-1.0, List.of());

        this.points = points;
        this.N = n;
        this.bestMask = -1;
        int full = (1 << n) - 1;
        memo = new Result[full + 1];

        Result res = dfs(full);
        
        if(res.perimeter == Double.MAX_VALUE) {
            res.perimeter = -1.0;
        }
        else {
            List<int[][]> triangles = new ArrayList<>();
            int mask = bestMask;
            while (mask != 0) {
                int i = firstSetBit(mask);
                for (int j = i + 1; j < N; j++) {
                    if (((mask >> j) & 1) == 0) continue;
                    for (int k = j + 1; k < N; k++) {
                        if (((mask >> k) & 1) == 0) continue;
                        if (!isCollinear(i, j, k)) {
                            int next = mask & ~(1 << i) & ~(1 << j) & ~(1 << k);
                            if (memo[next] != null && memo[next].perimeter + triPerimeter(i, j, k) == memo[mask].perimeter) {
                                triangles.add(triToArray(i, j, k));
                                mask = next;
                                break;
                            }
                        }
                    }
                    if (mask != bestMask) break;
                }
            }
            res.triangles = triangles;
        }

        return res;
    }

    // ---------- internals ----------

    private int[][] points;
    private int N;
    private Result[] memo;
    private int bestMask;

    private Result dfs(int mask) {
        if (mask == 0) return new Result(0.0, new ArrayList<>());

        if (memo[mask] != null) return memo[mask];

        int cnt = Integer.bitCount(mask);
        if (cnt % 3 != 0) {
            return memo[mask] = new Result(Double.MAX_VALUE, List.of());
        }

        if (cnt == 3) {
            int[] idx = new int[3];
            int t = 0;
            for (int i = 0; i < N; i++) if (((mask >> i) & 1) == 1) idx[t++] = i;

            if (!isCollinear(idx[0], idx[1], idx[2])) {
                double p = triPerimeter(idx[0], idx[1], idx[2]);
                return memo[mask] = new Result(p, new ArrayList<>());
            } else {
                return memo[mask] = new Result(Double.MAX_VALUE, List.of());
            }
        }

        int i = firstSetBit(mask);

        double best = Double.MAX_VALUE;

        for (int j = i + 1; j < N; j++) {
            if (((mask >> j) & 1) == 0) continue;
            for (int k = j + 1; k < N; k++) {
                if (((mask >> k) & 1) == 0) continue;

                if (isCollinear(i, j, k)) continue;

                int next = mask & ~(1 << i) & ~(1 << j) & ~(1 << k);
                Result tail = dfs(next);
                if (tail.perimeter == Double.MAX_VALUE) continue;

                double cur = triPerimeter(i, j, k) + tail.perimeter;
                if (cur < best) {
                    best = cur;
                    this.bestMask = mask;
                }
            }
        }

        if (best == Double.MAX_VALUE) {
            return memo[mask] = new Result(Double.MAX_VALUE, List.of());
        } else {
            return memo[mask] = new Result(best, new ArrayList<>());
        }
    }

    private int firstSetBit(int mask) {
        return Integer.numberOfTrailingZeros(mask);
    }

    private boolean isCollinear(int a, int b, int c) {
        long x1 = points[a][0], y1 = points[a][1];
        long x2 = points[b][0], y2 = points[b][1];
        long x3 = points[c][0], y3 = points[c][1];
        long cross = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        return cross == 0;
    }

    private double triPerimeter(int a, int b, int c) {
        return dist(a, b) + dist(a, c) + dist(b, c);
    }

    private double dist(int u, int v) {
        double dx = (double) points[u][0] - points[v][0];
        double dy = (double) points[u][1] - points[v][1];
        return Math.hypot(dx, dy);
    }

    private int[][] triToArray(int a, int b, int c) {
        return new int[][] {
            new int[]{ points[a][0], points[a][1] },
            new int[]{ points[b][0], points[b][1] },
            new int[]{ points[c][0], points[c][1] }
        };
    }
}