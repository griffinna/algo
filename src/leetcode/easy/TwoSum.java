package leetcode.easy;

import java.util.Arrays;

public class TwoSum {

    public static int[] solution(int[] nums, int target) {
        int[] answer = new int[2];

        loop:
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    answer[0] = i;
                    answer[1] = j;
                    break loop;
                }
            }
        }
        return answer;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{2, 7, 11, 15}, 9)));
    }

}
