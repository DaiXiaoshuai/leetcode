package problem478;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Solution {
    double radius;
    double x_center;
    double y_center;

    Solution(){}

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    public double[] randPoint() {
        double rad = Math.sqrt(Math.random() * radius * radius);
        double angle = Math.random() * Math.PI * 2;
        return new double[]{this.x_center + rad * Math.cos(angle), this.y_center + rad * Math.sin(angle)};
    }

    @Test
    void test(){
        Solution solution = new Solution(1.0,0.0,0.0);
        System.out.println(Arrays.toString(solution.randPoint()));
    }
}
