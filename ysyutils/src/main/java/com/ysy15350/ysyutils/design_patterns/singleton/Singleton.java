package com.ysy15350.ysyutils.design_patterns.singleton;

/**
 * Created by yangshiyou on 2017/12/16.
 */

/**
 * 单利模式
 */
public class Singleton {

    /**
     * 当第一次加载Singleton类时并不会初始化sInstance,只有在第一次调用Singleton的getInstance方法时才会导致 sInstance被初始化。
     * 因此，第一次调用getInstance方法会导致虚拟机加载SingletonHolder类，
     * 这种方式不仅能确保线程安全，也能够保证单利对象的唯一性，同事也延迟了单例的实例化，
     * 所以这是推荐使用的单例模式实现方式
     */

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SigletonHolder.sInstance;
    }

    private static class SigletonHolder {
        private static final Singleton sInstance = new Singleton();
    }

}
