import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(new int[] { e[1], e[2] });
        }
        
        // dist[u][cur] stores the minimum cost to reach node u with 'cur' consecutive identical characters
        int[][] dist = new int[n][k + 1];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        
        // Priority queue stores: {distance, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Starting node 0 has an initial run length of 1 for its label
        dist[0][1] = 0;
        pq.offer(new int[] { 0, 0, 1 });
        
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int d = p[0], u = p[1], cur = p[2];
            
            // If we reached the destination node, return the distance
            if (u == n - 1) {
                return d;
            }
            
            // Skip if we found a better path to this state already
            if (d > dist[u][cur]) {
                continue;
            }
            
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                
                // Calculate next consecutive identical character count
                int nxt = (labels.charAt(v) == labels.charAt(u)) ? cur + 1 : 1;
                int nd = d + w;
                
                // Check if the run length is within the allowed limit 'k' and provides a shorter path
                if (nxt <= k && nd < dist[v][nxt]) {
                    dist[v][nxt] = nd;
                    pq.offer(new int[] { nd, v, nxt });
                }
            }
        }
        
        // If destination cannot be reached within constraints
        return -1;
    }
}