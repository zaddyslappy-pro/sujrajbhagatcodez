import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int[] requestCount = new int[k];
        
        // TreeSet to store available server indices
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        // PriorityQueue to store busy servers: stores int[] {free_time, server_id}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        int maxRequests = 0;
        
        for (int i = 0; i < n; i++) {
            int currTime = arrival[i];
            
            // Free up servers whose task completion time <= current request arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                int[] freed = busyServers.poll();
                availableServers.add(freed[1]);
            }
            
            // If no servers are available, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Find the preferred server using i % k
            int target = i % k;
            Integer assignedServer = availableServers.ceiling(target);
            
            // If no server >= target is available, wrap around to the first available server
            if (assignedServer == null) {
                assignedServer = availableServers.first();
            }
            
            // Assign the request to the chosen server
            availableServers.remove(assignedServer);
            requestCount[assignedServer]++;
            maxRequests = Math.max(maxRequests, requestCount[assignedServer]);
            
            // Push the server into the busy queue with its new release time
            busyServers.offer(new int[] { currTime + load[i], assignedServer });
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                busiest.add(i);
            }
        }
        
        return busiest;
    }
}