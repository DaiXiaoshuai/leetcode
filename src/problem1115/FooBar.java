package problem1115;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class FooBar {
    private int n =22;
    private int count = 0;
    public FooBar(){}

//    public FooBar(int n) {
//        this.n = n;
//    }

    public void foo(
//            Runnable printFoo
    ) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(this){
                if(count % 2 == 1) this.wait();
                count++;
                System.out.print("foo");
//                printFoo.run();
                this.notifyAll();
//                this.wait();

            }

        }
    }

    public void bar(
//            Runnable printBar
    ) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized(this){
                if(count % 2 == 0) this.wait();
                count++;
                // printBar.run() outputs "bar". Do not change or remove this line.
//                printBar.run();
                System.out.println("bar");
                this.notifyAll();
//                this.wait();


            }
        }
    }
    static class TestThread1 extends Thread{
        FooBar fooBar;
        TestThread1(FooBar fooBar){
            this.fooBar = fooBar;
        }

        @Override
        public void run() {
            try {
                this.fooBar.foo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class TestThread2 extends Thread{
        FooBar fooBar;
        TestThread2(FooBar fooBar){
            this.fooBar = fooBar;
        }

        @Override
        public void run() {
            try {
                this.fooBar.bar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    void test(){
        ExecutorService pool = Executors.newFixedThreadPool(2);
        FooBar fooBar = new FooBar();


        pool.execute(new TestThread2(fooBar));

        pool.execute(new TestThread1(fooBar));
    }
}
