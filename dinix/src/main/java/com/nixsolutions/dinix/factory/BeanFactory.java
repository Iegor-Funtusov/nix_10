package com.nixsolutions.dinix.factory;

import com.nixsolutions.dinix.ApplicationSearcher;
import com.nixsolutions.dinix.DiNixSearcher;
import com.nixsolutions.dinix.annotations.Autowired;
import com.nixsolutions.dinix.annotations.Service;
import com.nixsolutions.dinix.configuration.BeanConfigurator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanFactory {

    private final ApplicationSearcher searcher;
    private final DiNixSearcher diNixSearcher;
    private final BeanStorage beanStorage;
    private final List<BeanConfigurator> beanConfigurators = new ArrayList<>();

    public BeanFactory(ApplicationSearcher searcher, BeanStorage beanStorage, DiNixSearcher diNixSearcher) {
        this.searcher = searcher;
        this.beanStorage = beanStorage;
        this.diNixSearcher = diNixSearcher;
        initBeanConfigurators();
    }

    public <IFC> IFC createBeanByInterface(Class<IFC> ifc) {
        if (ifc.isInterface()) {
            Set<Class<? extends IFC>> implementations = searcher.getScanner().getSubTypesOf(ifc);
            for (Class<? extends IFC> implementation : implementations) {
                if (implementation.isAnnotationPresent(Service.class)) {
                    try {
                        return implementation.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public void configureBeanMap() {
        beanStorage.getBeanMap().forEach((ifc, impl) -> {
            beanConfigure(impl);
        });
    }

    private void beanConfigure(Object bean) {
        beanConfigurators.forEach(beanConfigurator -> beanConfigurator.configure(bean, beanStorage));
    }

    private void initBeanConfigurators() {
        Set<Class<? extends BeanConfigurator>> subTypesOf = this.diNixSearcher.getScanner().getSubTypesOf(BeanConfigurator.class);
        for (Class<? extends BeanConfigurator> aClass : subTypesOf) {
            try {
                beanConfigurators.add(aClass.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
