
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int start = nums[0];

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (start + i) % n) {
                increasing = false;
            }

            if (nums[i] != (start - i + n) % n) {
                decreasing = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            int rotations = (n - start) % n;
            ans = Math.min(rotations, start + 2);
        }

        if (decreasing) {
            int rotations = (n - 1 - start + n) % n;
            int cost = 1 + Math.min(rotations, n - rotations);
            ans = Math.min(ans, cost);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
