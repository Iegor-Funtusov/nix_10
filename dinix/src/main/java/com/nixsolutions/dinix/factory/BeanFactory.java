package com.nixsolutions.dinix.factory;

import com.nixsolutions.dinix.ApplicationSearcher;
import com.nixsolutions.dinix.annotations.Service;

import java.util.Set;

public class BeanFactory {

    private final ApplicationSearcher searcher;

    public BeanFactory(ApplicationSearcher searcher) {
        this.searcher = searcher;
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
}
