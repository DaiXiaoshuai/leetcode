package problem799

/**
 * 799. 香槟塔
我们把玻璃杯摆成金字塔的形状，其中第一层有1个玻璃杯，第二层有2个，依次类推到第100层，每个玻璃杯(250ml)将盛有香槟。

从顶层的第一个玻璃杯开始倾倒一些香槟，当顶层的杯子满了，任何溢出的香槟都会立刻等流量的流向左右两侧的玻璃杯。当左右两边的杯子也满了，就会等流量的流向它们左右两边的杯子，依次类推。（当最底层的玻璃杯满了，香槟会流到地板上）

例如，在倾倒一杯香槟后，最顶层的玻璃杯满了。倾倒了两杯香槟后，第二层的两个玻璃杯各自盛放一半的香槟。在倒三杯香槟后，第二层的香槟满了 - 此时总共有三个满的玻璃杯。在倒第四杯后，第三层中间的玻璃杯盛放了一半的香槟，他两边的玻璃杯各自盛放了四分之一的香槟，如下图所示。



现在当倾倒了非负整数杯香槟后，返回第 i 行 j 个玻璃杯所盛放的香槟占玻璃杯容积的比例（i 和 j都从0开始）。



示例 1:
输入: poured(倾倒香槟总杯数) = 1, query_glass(杯子的位置数) = 1, query_row(行数) = 1
输出: 0.0
解释: 我们在顶层（下标是（0，0））倒了一杯香槟后，没有溢出，因此所有在顶层以下的玻璃杯都是空的。

示例 2:
输入: poured(倾倒香槟总杯数) = 2, query_glass(杯子的位置数) = 1, query_row(行数) = 1
输出: 0.5
解释: 我们在顶层（下标是（0，0）倒了两杯香槟后，有一杯量的香槟将从顶层溢出，位于（1，0）的玻璃杯和（1，1）的玻璃杯平分了这一杯香槟，所以每个玻璃杯有一半的香槟。
注意:

poured 的范围[0, 10 ^ 9]。
query_glass 和query_row 的范围 [0, 99]。
 */
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SolutionTest {

    @Test
    fun champagneTower() {
        val solution = Solution()
        assertEquals(0.0, solution.champagneTower(1,1,1))
        assertEquals(0.5, solution.champagneTower(2,1,1))
        assertEquals(1.0, solution.champagneTower(100000009,33,17))
    }
}
class Solution {
    var resultIndex :Int = 0

    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
        val glasses = DoubleArray((query_row*query_row + 3*query_row + 2)/2)
        resultIndex = (query_row*query_row + query_row)/2 + query_glass
        glasses[0] = poured.toDouble()
        for(row in 1 .. query_row){
            glasses[(row + 1) * row /2] = maxOf(0.0, (glasses[(row+1) * row /2 - row] - 1) /2)
            for(column in 1 until row){
                glasses[(row+1) * row /2 + column] += maxOf(0.0, (glasses[(row+1) * row /2 + column - row -1] -1) /2)
                glasses[(row+1) * row /2 + column] += maxOf(0.0, (glasses[(row+1) * row /2 + column - row] - 1) /2)
            }
            glasses[(row + 1) * row /2 + row] = maxOf(0.0, (glasses[(row+1) * row /2 -1] -1) /2)
        }

        return minOf(1.0, glasses[resultIndex])
    }

    fun champagneTower2(poured: Int, query_row: Int, query_glass: Int): Double {
        val glasses = DoubleArray((query_row*query_row + 3*query_row + 2)/2)
        resultIndex = (query_row*query_row + query_row)/2 + query_glass
        addWine(glasses,0,0,poured.toDouble())
        return glasses[resultIndex]
    }

    /**
     * 递归超时
     */
    fun addWine(glasses:DoubleArray, row:Int,column:Int, value:Double ){
        val index = (row*row +row)/2 + column
        if(index > glasses.lastIndex) return
        if(glasses[index] + value > 1){
            val tmp = glasses[index]
            glasses[index] = 1.toDouble()
            if(index == resultIndex) return
            addWine(glasses, row+1, column, (value + tmp -1)/2)
            addWine(glasses, row+1, column + 1, (value + tmp -1)/2)
        }else{
            glasses[index] = glasses[index] + value
        }
    }

}