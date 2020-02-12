package com.github.mikephil.charting.test;

import com.github.mikephil.charting.utils.ObjectPool;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FSMObjectPoolTest{
    static class TestPoolable extends ObjectPool.Poolable{

        private static ObjectPool<ObjectPoolTest.TestPoolable> pool;

        static {
            pool = ObjectPool.create(4, new FSMObjectPoolTest.TestPoolable(0,0));
        }

        public int foo = 0;
        public int bar = 0;

        protected ObjectPool.Poolable instantiate(){
            return new FSMObjectPoolTest.TestPoolable(0,0);
        }

        private TestPoolable(int foo, int bar){
            this.foo = foo;
            this.bar = bar;
        }

        public static ObjectPoolTest.TestPoolable getInstance(int foo, int bar){
            ObjectPoolTest.TestPoolable result = pool.get();
            result.foo = foo;
            result.bar = bar;
            return result;
        }

        public static void recycleInstance(ObjectPoolTest.TestPoolable instance){
            pool.recycle(instance);
        }

        public static void recycleInstances(List<ObjectPoolTest.TestPoolable> instances){
            pool.recycle(instances);
        }

        public static ObjectPool getPool(){
            return pool;
        }

    }

    // <0.50f: 0
    // 0.50f - 0.74f: 1
    // 0.75f - 0.99f: 2
    // >=1.0f: 3

    @Test
    public void stateZeroToZero(){
        // start state 0
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.49f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());

        // end state 0
        pool.setReplenishPercentage(-1f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());

    }
    @Test
    public void stateZeroToOne(){
        // start state 0
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.49f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());

        // end state 1
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());
    }
    @Test
    public void stateZeroToTwo(){
        // start state 0
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.49f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());

        // end state 2
        pool.setReplenishPercentage(0.99f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());
    }
    @Test
    public void stateZeroToThree(){
        // start state 0
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.49f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());

        // end state 3
        pool.setReplenishPercentage(10f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());
    }

    @Test
    public void stateOneToZero(){
        // start state 1
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());

        // end state 0
        pool.setReplenishPercentage(-1f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());
    }
    @Test
    public void stateOneToOne(){
        // start state 1
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());

        // end state 1
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());
    }
    @Test
    public void stateOneToTwo(){
        // start state 1
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());

        // end state 2
        pool.setReplenishPercentage(0.99f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());
    }
    @Test
    public void stateOneToThree(){
        // start state 1
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());

        // end state 3
        pool.setReplenishPercentage(10f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());
    }

    @Test
    public void stateTwoToZero(){
        // start state 2
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.75f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());

        // end state 0
        pool.setReplenishPercentage(-1f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());
    }
    @Test
    public void stateTwoToOne(){
        // start state 2
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.75f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());

        // end state 1
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());
    }
    @Test
    public void stateTwoToTwo(){
        // start state 2
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.75f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());

        // end state 2
        pool.setReplenishPercentage(0.99f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());
    }
    @Test
    public void stateTwoToThree(){
        // start state 2
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(0.75f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());

        // end state 3
        pool.setReplenishPercentage(10f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());
    }

    @Test
    public void stateThreeToZero(){
        // start state 3
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(1.0f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());

        // end state 0
        pool.setReplenishPercentage(-1f);
        pool.refillPool();
        // has 1 objects, index of objects array (state) is 0
        Assert.assertEquals(0, pool.getObjectsPointer());
        Assert.assertEquals(1, pool.getPoolCount());
    }
    @Test
    public void stateThreeToOne(){
        // start state 3
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(1.0f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());

        // end state 1
        pool.setReplenishPercentage(0.50f);
        pool.refillPool();
        // has 2 objects, index of objects array (state) is 1
        Assert.assertEquals(1, pool.getObjectsPointer());
        Assert.assertEquals(2, pool.getPoolCount());
    }
    @Test
    public void stateThreeToTwo(){
        // start state 3
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(1.0f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());

        // end state 2
        pool.setReplenishPercentage(0.99f);
        pool.refillPool();
        // has 3 objects, index of objects array (state) is 2
        Assert.assertEquals(2, pool.getObjectsPointer());
        Assert.assertEquals(3, pool.getPoolCount());
    }
    @Test
    public void stateThreeToThree(){
        // start state 3
        ObjectPool pool = TestPoolable.getPool();
        pool.setReplenishPercentage(1.0f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());

        // end state 3
        pool.setReplenishPercentage(10f);
        pool.refillPool();
        // has 4 objects, index of objects array (state) is 3
        Assert.assertEquals(3, pool.getObjectsPointer());
        Assert.assertEquals(4, pool.getPoolCount());
    }
}