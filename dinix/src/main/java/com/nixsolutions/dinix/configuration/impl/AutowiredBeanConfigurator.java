package com.nixsolutions.dinix.configuration.impl;

import com.nixsolutions.dinix.annotations.Autowired;
import com.nixsolutions.dinix.configuration.BeanConfigurator;
import com.nixsolutions.dinix.factory.BeanStorage;

import java.lang.reflect.Field;

public class AutowiredBeanConfigurator implements BeanConfigurator {

    @Override
    public void configure(Object bean, BeanStorage beanStorage) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                try {
                    field.set(bean, beanStorage.getBeanMap().get(field.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
