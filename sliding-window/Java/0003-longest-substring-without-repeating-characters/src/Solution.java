public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return len;
        }

        // 转换成字符数组，是字符串问题常见的处理办法
        // 这是因为 charAt 方法，每次访问的时候都会做边界判断，在我们求解的问题中，是不必要的
        char[] charArray = s.toCharArray();

        // 当 window 中某个字符的频数为 2 时，表示滑动窗口内有重复字符
        int[] cnt = new int[128];
        // 右边界滑动到刚刚好有重复的时候停下
        // 左边界滑动到刚刚好没有重复的时候停下
        int left = 0;
        int right = 0;
        // 滑动窗口内是否重复
        boolean repeating = false;
        int res = 1;
        // 循环不变式，保持不变的性质是：[left, right) 内没有重复元素
        while (right < len) {
            // 不能写在后面，因为数组下标容易越界
            if (cnt[charArray[right]] == 1) {
                repeating = true;
            }
            cnt[charArray[right]]++;
            right++;

            // 此时 [left, right) 内如果没有重复元素，就尝试扩张 right
            // 否则缩小左边界，while 循环体内不断缩小边界
            while (repeating) {
                if (cnt[charArray[left]] == 2) {
                    // 如果满足滑动窗口内有重复的元素，尝试不断删除左边元素
                    repeating = false;
                }
                // 只有有重复元素，就得缩短左边界
                cnt[charArray[left]]--;
                left++;
            }
            // 此时 [left, right) 内没有重复元素
            res = Math.max(res, right - left);
        }
        return res;
    }
}
