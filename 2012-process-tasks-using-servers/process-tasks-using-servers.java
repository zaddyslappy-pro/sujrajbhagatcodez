import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Min-heap for free servers: sorted by weight, then index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Min-heap for busy servers: sorted by freeTime, then weight, then index
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[2], b[2]);
        });

        // Initialize free servers
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currTime = 0;

        for (int j = 0; j < m; j++) {
            currTime = Math.max(currTime, j);

            // Free up servers that have finished their tasks by currTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                long[] busy = busyServers.poll();
                freeServers.offer(new int[] { (int) busy[1], (int) busy[2] });
            }

            // If no servers are free, fast-forward time to the next available server
            if (freeServers.isEmpty()) {
                currTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                    long[] busy = busyServers.poll();
                    freeServers.offer(new int[] { (int) busy[1], (int) busy[2] });
                }
            }

            // Assign the task to the top free server
            int[] bestServer = freeServers.poll();
            int weight = bestServer[0];
            int index = bestServer[1];

            ans[j] = index;
            busyServers.offer(new long[] { currTime + tasks[j], weight, index });
        }

        return ans;
    }
}