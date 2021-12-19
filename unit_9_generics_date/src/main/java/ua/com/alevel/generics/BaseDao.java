package ua.com.alevel.generics;

import java.util.Collection;

public interface BaseDao<D extends Dto> {

    void create(D dto);
    <ID extends Number> D findById(ID id);
    <ID extends Number> Collection<D> findAllByChildren(Collection<ID> ids);
    Collection<D> findAllByParent(Collection<? super Number> ids);
//    <A, B, C extends D> C test(Class<A> aClass, Collection<B> collection);
}
