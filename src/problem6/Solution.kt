package problem6

import org.junit.jupiter.api.Test
import java.lang.Exception
import java.lang.StringBuilder
import kotlin.test.assertEquals

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zigzag-conversion
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution{
    fun convert(s: String, numRows: Int): String {
        if(numRows==1) return s
        val columns = getNumColumns(s, numRows)

        val chars = CharArray(s.length)
        val groupSize = numRows*2 - 2
        var row = 0 // 记录所在行
        var column = 0 // 记录所在列
        for(index in s.indices){
            column = index / (groupSize) * (numRows-1) + if(index%groupSize < numRows) 0 else (index%groupSize - numRows +1)
            row = if(index % groupSize < numRows) index%groupSize else numRows - 2 - (index%groupSize - numRows)
            var columnIndex = 0
            if(row ==0 || row == numRows -1)
                columnIndex = column/(numRows-1)
            else
                columnIndex = column/(numRows-1) * 2
            if(column%(numRows-1) == 0) columnIndex += 0
            else columnIndex += 1
            if(getPrevRowCharSum(s.length, numRows, row) + columnIndex >=s.length)
                print(12)
            chars[getPrevRowCharSum(s.length, numRows, row) + columnIndex] = s[index]
        }
        return String(chars)
    }

    fun getPrevRowCharSum(length: Int, numRows: Int, row:Int):Int{
        var sum = 0
        for(i in 0 until row){
            sum += getColumnSumInRow(length, numRows,i)
        }
        return sum
    }

    /**
     * 获得某一行的字符数量
     */
    fun getColumnSumInRow(length:Int, numRows:Int,row:Int):Int{
        val groupSize = numRows*2 -2
        var fixedColumns = length/groupSize*2
        if(row == 0 || row == numRows-1) fixedColumns =  length/groupSize
        if(length % groupSize == 0) return fixedColumns
        if(length % groupSize<= numRows && length%groupSize>0)
            return fixedColumns + if (length%groupSize>row)  1 else 0
        if(length%groupSize>numRows)
//            return fixedColumns + 1 + if(row<length%groupSize - numRows + 1) 0 else 1
            return fixedColumns  + if(row < numRows - (length%groupSize - numRows) -1) 1 else 2
        throw Exception("未正确处理！！！")
    }


    fun getNumColumns(s: String, numRows: Int):Int{
        var numColumns = s.length/(numRows*2-2) * (numRows-1)
        if(s.length%(numRows*2-2) > numRows) numColumns += 1 + s.length % (numRows * 2 - 2) % numRows
        else numColumns += 1
        return numColumns
    }

    @Test
    fun testConvert(){
        val solution = Solution()
        assertEquals(7, solution.getNumColumns("LEETCODEISHIRING", 4))

        assertEquals("ABCDFE", solution.convert("ABCDEF" , 5))
        assertEquals("ABCED", solution.convert("ABCDE" , 4))


        assertEquals("LDREOEIIECIHNTSG", solution.convert("LEETCODEISHIRING", 4))
        assertEquals("LDEOEIECIHTS", solution.convert("LEETCODEISHI", 4))
        assertEquals("LDREOEIECIHTS", solution.convert("LEETCODEISHIR", 4))
        assertEquals("LDREOEIIECIHTS", solution.convert("LEETCODEISHIRI", 4))
        assertEquals("LDREOEIIECIHNTS", solution.convert("LEETCODEISHIRIN", 4))

        assertEquals("LEETCODEISHIRING", solution.convert("LEETCODEISHIRING", 1))
        assertEquals("LCIRETOESIIGEDHN", solution.convert("LEETCODEISHIRING", 3))



        assertEquals("", solution.convert("", 1))
//        assertEquals("", solution.convert("ctcjqmrkiwhwerepqyehsyirqvxryrwbmbmepfpzeyvkrzajzesteakwvhnwalznmnrbuicygxjxylixrbtvbdrzngxnrwcglujfcmellpkmctltueqvkjuxprmippoajyinmmyxdjjfevayzqtlzqiojxybmndxllmxzlwcwgjadjaebvqalaqxxpyjedippvooimtgucixoierfwsrwkqubqfftqwinmxvzsdtwltmvxeatytrillkbtpvlofyaetzwyttlofiljkghexspletgvqrjvpkakjyietvszdfknlutlojyseenuxxpohrysqixldpkivxvitpvhatbezoawnpkwjkpbummzdzhayflrugawcbabrayhrkdcxsdrgsrmramp", 170))

    }
}