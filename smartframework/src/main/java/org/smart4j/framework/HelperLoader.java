package org.smart4j.framework;

import org.smart4j.framework.Helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 * Created by nanca on 12/24/2017.
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
