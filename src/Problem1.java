public class Problem1 {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            int index2 = findNum(nums, target - nums[i],i);
            if(index2!=-1){
                return new int[]{i, index2};
            }
        }
        return new int[]{-1, -1};
    }

    public int findNum(int[] nums, int target, int currentIndex){
        for(int i =0;i<nums.length;i++){
            if(currentIndex == i) continue;
            if(nums[i] == target)   return i;
        }
        return -1;
    }

}
