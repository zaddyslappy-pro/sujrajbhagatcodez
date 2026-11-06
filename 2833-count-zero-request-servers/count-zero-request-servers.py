
class Solution:
    def countServers(self, n, logs, x, queries):

        logs.sort(key=lambda a: a[1])

        sorted_queries = sorted((q, i) for i, q in enumerate(queries))

        ans = [0] * len(queries)
        server_count = {}

        left = 0
        right = 0

        for q, idx in sorted_queries:

            while right < len(logs) and logs[right][1] <= q:
                server_id = logs[right][0]

                server_count[server_id] = server_count.get(server_id, 0) + 1

                right += 1

            while left < right and logs[left][1] < q - x:
                server_id = logs[left][0]

                server_count[server_id] -= 1

                if server_count[server_id] == 0:
                    del server_count[server_id]

                left += 1

            ans[idx] = n - len(server_count)

        return ans
