import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        for (int i = 0; i < servers.length; i++) {
            available.offer(new int[]{servers[i], i});
        }

        PriorityQueue<long[]> suspended = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Long.compare(a[0], b[0]);
                return Long.compare(a[1], b[1]);
            }
        );

        long time = 0;
        int[] ans = new int[tasks.length];

        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, (long)i);

            if (available.isEmpty()) {
                time = Math.max(time, suspended.peek()[0]);
            }

            while (!suspended.isEmpty() && suspended.peek()[0] <= time) {
                long[] server = suspended.poll();
                int idx = (int)server[1];
                available.offer(new int[]{servers[idx], idx});
            }

            int[] server = available.poll();
            int idx = server[1];

            ans[i] = idx;
            suspended.offer(new long[]{time + tasks[i], idx});
        }

        return ans;
    }
}