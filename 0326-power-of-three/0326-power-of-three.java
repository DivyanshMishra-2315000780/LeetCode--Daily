public class Solution {
    public boolean isPowerOfThree(int n) {
        return helper(1, n);
    }
    private boolean helper(long val, int n) {
        if (val == n) return true;
        if (val > n) return false;
        return helper(val * 3, n);
    }
}
