package MianShi;

import org.junit.jupiter.api.Test;

public class Problem1 {


    public void function(){
        // 1000以内的所有完全平方数
        int[] nums = new int[]{ 0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225, 256, 289, 324, 361, 400, 441, 484, 529, 576, 625, 676, 729, 784, 841, 900, 961};

        for(int n=0;n<1000;n++){
            int num = (n+n*n)/2;
            int index = search(num, nums);
            if( index>=0){
                int doorNum = (int)Math.sqrt(nums[index]);
                int maxDoorNum = n;
                if(doorNum < n )
                    System.out.println("最大门牌号：" + maxDoorNum +",所住门牌号:" + doorNum);
                // 经运算1000内符合条件的仅有一组数据  最大门牌号：8,所在门牌号:6
            }
        }
    }

    /**
     * 从数组中找出目标值的下标
     * 可用二分查找，此处从简
     * @return -1 不在数组中
     */
    public int search(int target, int[] nums){
        for(int i=0;i<nums.length;i++){
            if(target == nums[i]) return i;
        }
        return -1;
    }


    /**
     * @param n 长度
     * @return sum 所求和
     */
    int function2(int n){
        int[] result= new int[n];
        for(int i=1;i<=n;i++){
            result[i-1] = 3* i;
        }
        int sum = 0;
        for(int item :result){
            if(item % 9 == 0) sum += item;
        }
        return sum;
    }

    @Test
    public void test() {
        Problem1 problem1 = new Problem1();
        problem1.function();
    }



}
