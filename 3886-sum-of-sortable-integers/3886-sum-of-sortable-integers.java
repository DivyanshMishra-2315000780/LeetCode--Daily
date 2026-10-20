
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int prevMax = Integer.MIN_VALUE;

            for (int start = 0; start < n && valid; start += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int drops = 0;

                for (int j = start; j < start + k; j++) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);

                    int next = (j + 1 == start + k) ? start : j + 1;

                    if (k > 1 && nums[j] > nums[next]) {
                        drops++;
                    }
                }

                if (drops > 1 || min < prevMax) {
                    valid = false;
                }

                prevMax = max;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}
