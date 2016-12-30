package com.github.cc007.aparapidemo;

import com.aparapi.Kernel;
import com.aparapi.Range;

public class AparapiDemo {

    public static void main(String[] args) {
        final int size = 5000000;

        final float[] a = new float[size];
        final float[] b = new float[size];

        for (int i = 0; i < size; i++) {
            a[i] = (float) (Math.random() * 100);
            b[i] = (float) (Math.random() * 100);
        }

        final float[] sum = new float[size];

        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int gid = getGlobalId();
                sum[gid] = a[gid] + b[gid];
            }
        };
        long t1 = System.currentTimeMillis();
        kernel.execute(Range.create(size));
        long t2 = System.currentTimeMillis();
        kernel.dispose();
        System.out.println("Time (ms): " + (t2 - t1));
    }
}
