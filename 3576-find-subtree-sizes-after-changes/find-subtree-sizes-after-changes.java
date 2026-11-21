import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;

        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        dfsAssignParents(0, parent, origTree, s, lastSeen, newParent);

        List<Integer>[] finalTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            finalTree[newParent[i]].add(i);
        }

        int[] answer = new int[n];
        dfsSubtreeSize(0, finalTree, answer);
        return answer;
    }

    private void dfsAssignParents(
            int u,
            int[] parent,
            List<Integer>[] origTree,
            String s,
            int[] lastSeen,
            int[] newParent
    ) {
        int idx = s.charAt(u) - 'a';
        if (u > 0) {
            newParent[u] = (lastSeen[idx] != -1) ? lastSeen[idx] : parent[u];
        }

        int prev = lastSeen[idx];
        lastSeen[idx] = u;

        for (int v : origTree[u]) {
            dfsAssignParents(v, parent, origTree, s, lastSeen, newParent);
        }

        lastSeen[idx] = prev;
    }

    private int dfsSubtreeSize(int u, List<Integer>[] finalTree, int[] answer) {
        int size = 1;
        for (int v : finalTree[u]) {
            size += dfsSubtreeSize(v, finalTree, answer);
        }
        answer[u] = size;
        return size;
    }
}