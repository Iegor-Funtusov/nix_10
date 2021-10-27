package com.nixsolutions.dinix;

import java.util.Collection;

public class DiNixApplication {

    public static void run(Class<?> mainClass) {
        ApplicationContext applicationContext = new ApplicationContext(mainClass);
        applicationContext.initBeanMap();
        applicationContext.configureBeanMap();
        Collection<Object> values = applicationContext.getMapInterfaceAndImplementation().values();
        ApplicationStarter starter = new ApplicationStarter(values);
        starter.start();
    }
}
