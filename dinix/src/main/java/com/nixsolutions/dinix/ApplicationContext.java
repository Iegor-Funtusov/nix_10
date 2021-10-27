package com.nixsolutions.dinix;

import com.nixsolutions.dinix.annotations.Autowired;
import com.nixsolutions.dinix.factory.BeanFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ApplicationContext {

    private final Map<Class<?>, Object> mapInterfaceAndImplementation = new HashMap<>();
    private Set<Class<?>> serviceInterfaces;
    private ApplicationSearcher applicationSearcher;
    private BeanFactory beanFactory;

    public ApplicationContext(Class<?> mainClass) {
        try {
            Package rootPackage = mainClass.getPackage();
            this.applicationSearcher = new ApplicationSearcher(rootPackage.getName());
            List<Class<?>> allClasses = this.applicationSearcher.getAllClassesByPackage(rootPackage.getName(), mainClass.getClassLoader());
            initServiceInterfaces(allClasses);
            this.beanFactory = new BeanFactory(this.applicationSearcher);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e = " + e.getMessage());
        }
    }

    public void initBeanMap() {
        this.serviceInterfaces.forEach(serviceInterface -> {
            Object impl = this.beanFactory.createBeanByInterface(serviceInterface);
            if (impl != null) {
                mapInterfaceAndImplementation.put(serviceInterface, impl);
            }
        });
    }

    public Map<Class<?>, Object> getMapInterfaceAndImplementation() {
        return mapInterfaceAndImplementation;
    }

    public void configureBeanMap() {
        mapInterfaceAndImplementation.forEach((ifc, impl) -> {
            System.out.println("ifc = " + ifc);
            System.out.println("impl = " + impl);
            Field[] fields = impl.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(impl, mapInterfaceAndImplementation.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initServiceInterfaces(List<Class<?>> allClasses) {
        this.serviceInterfaces = allClasses
                .stream()
                .filter(Class::isInterface)
                .filter(this::isServiceType)
                .filter(this::hasChildImpl)
                .collect(Collectors.toSet());
    }

    private boolean isServiceType(Class<?> interfaceClass) {
        return interfaceClass.getSimpleName().endsWith("Controller") ||
            interfaceClass.getSimpleName().endsWith("Service") ||
            interfaceClass.getSimpleName().endsWith("Dao");
    }

    private boolean hasChildImpl(Class<?> interfaceClass) {
        Set<Class<?>> classes = this.applicationSearcher.getAllImplementation((Class<Object>) interfaceClass);
        return classes.stream().noneMatch(Class::isInterface);
    }
}
