package com.nixsolutions.dinix.configuration.impl;

import com.nixsolutions.dinix.annotations.Autowired;
import com.nixsolutions.dinix.configuration.BeanConfigurator;
import com.nixsolutions.dinix.db.EntityManager;
import com.nixsolutions.dinix.factory.BeanStorage;

import java.lang.reflect.Field;

public class EntityManagerBeanConfigurator implements BeanConfigurator {

    @Override
    public void configure(Object bean, BeanStorage beanStorage) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(EntityManager.class)) {
                field.setAccessible(true);
                try {
                    field.set(bean, EntityManager.getInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
