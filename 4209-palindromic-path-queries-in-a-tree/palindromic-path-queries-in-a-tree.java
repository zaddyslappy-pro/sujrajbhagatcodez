import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[] parent, depth, heavy, head, pos;
    int curPos;
    SegmentTree tree;

    class SegmentTree {
        int n;
        int[] treeNode;

        SegmentTree(int n) {
            this.n = n;
            treeNode = new int[4 * n];
        }

        void update(int node, int l, int r, int idx, int val) {
            if (l == r) {
                treeNode[node] = val;
                return;
            }
            int mid = (l + r) / 2;
            if (idx <= mid) update(2 * node, l, mid, idx, val);
            else update(2 * node + 1, mid + 1, r, idx, val);
            treeNode[node] = treeNode[2 * node] ^ treeNode[2 * node + 1];
        }

        int query(int node, int l, int r, int ql, int qr) {
            if (qr < l || ql > r) return 0;
            if (ql <= l && r <= qr) return treeNode[node];
            int mid = (l + r) / 2;
            return query(2 * node, l, mid, ql, qr) ^ query(2 * node + 1, mid + 1, r, ql, qr);
        }
    }

    private int dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubtree = 0;
        heavy[u] = -1;
        for (int v : adj[u]) {
            if (v != p) {
                int subSize = dfs1(v, u, d + 1);
                size += subSize;
                if (subSize > maxSubtree) {
                    maxSubtree = subSize;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u; u = v; v = temp;
            }
            res ^= tree.query(1, 0, adj.length - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) {
            int temp = u; u = v; v = temp;
        }
        res ^= tree.query(1, 0, adj.length - 1, pos[u], pos[v]);
        return res;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        curPos = 0;

        dfs1(0, -1, 0);
        dfs2(0, 0);

        tree = new SegmentTree(n);
        char[] charArr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            tree.update(1, 0, n - 1, pos[i], 1 << (charArr[i] - 'a'));
        }

        List<Boolean> answer = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                charArr[u] = c;
                tree.update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);
                answer.add(mask == 0 || (mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }
}