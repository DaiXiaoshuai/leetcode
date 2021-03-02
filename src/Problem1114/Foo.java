package Problem1114;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 * 示例 2:
 *
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Foo {

//    Runnable thread1, thread2, thread3;
    boolean[] finishFlags = new boolean[3];


    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
//        thread1 = printFirst;
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        synchronized (this) {
            finishFlags[0] = true;
//            if (thread2 != null)
                this.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (this) {
//            thread2 = printSecond;
            if (finishFlags[0] == false)
                this.wait();
        }

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        finishFlags[1] = true;
        synchronized (this){
            this.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (this){
//            thread3 = printThird;
            if(finishFlags[0] == false){
                this.wait();
            }
            if(finishFlags[1] == false){
                this.wait();
            }
        }

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

}