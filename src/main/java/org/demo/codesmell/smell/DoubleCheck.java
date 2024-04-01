package org.demo.codesmell.smell;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DoubleCheck {

    private static Resource resource;

    private static final Object lockObj = new Object();

    private static int num = 0;

    public static class Resource {

    }

    public static Resource getResource() {
        if (resource == null) {
            synchronized (DoubleCheck.class) {
                if (resource == null) {
                    resource = new Resource();
                }
            }
        }
        return resource;
    }

    public int divide(int num) {
        int z = 0;
        if (num > 10) {
            z = 1;
        } else {
            num = 1;
        }
        return num / z;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lockObj) {
                try {
                    for (int i = 0; i < 10; i++) {
                        num++;
                        log.info(Thread.currentThread().getName());
                        Thread.sleep(300);
                        if (num == 10) {
                            log.info("notify");
                            lockObj.notify();
                        }
                    }
                } catch (Exception e) {
                    log.error("err: ", e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockObj) {
                log.info(Thread.currentThread().getName());
                try {
                    if (num != 10) {
                        lockObj.wait();
                    }
                } catch (Exception e) {
                    log.error("err: ", e);
                }
                log.info("receive notify");
            }
        });

        try {
            t2.start();
            Thread.sleep(1000);
            t1.start();
        } catch (Exception e) {
            log.error("err: ", e);
        }
    }
}
