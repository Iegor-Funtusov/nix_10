package com.nixsolutions.dinix;

import com.nixsolutions.dinix.db.EntityManager;
import com.nixsolutions.dinix.factory.BeanFactory;
import com.nixsolutions.dinix.factory.BeanStorage;

import java.util.Collection;

public class DiNixApplication {

    public static void run(Class<?> mainClass) {
        ApplicationContext applicationContext = new ApplicationContext(mainClass);
        EntityManager.getInstance().initEntityManager(mainClass.getClassLoader());
        BeanStorage beanStorage = new BeanStorage();
        DiNixSearcher diNixSearcher = new DiNixSearcher();
        BeanFactory beanFactory = new BeanFactory(applicationContext.getApplicationSearcher(), beanStorage, diNixSearcher);
        applicationContext.setBeanFactory(beanFactory);
        applicationContext.setBeanStorage(beanStorage);
        applicationContext.initBeanMap();
        applicationContext.configureBeanMap();
        Collection<Object> values = applicationContext.getMapInterfaceAndImplementation().values();
        ApplicationStarter starter = new ApplicationStarter(values);
        starter.start();
    }
}
