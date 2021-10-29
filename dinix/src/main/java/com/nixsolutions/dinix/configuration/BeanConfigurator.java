package com.nixsolutions.dinix.configuration;

import com.nixsolutions.dinix.factory.BeanStorage;

public interface BeanConfigurator {

    void configure(Object bean, BeanStorage beanStorage);
}
