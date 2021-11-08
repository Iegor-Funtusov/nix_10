package ua.com.alevel.generics;

import java.util.Collection;

public class BaseDaoImpl implements BaseDao<User> {

    @Override
    public void create(User dto) {

    }

    @Override
    public <ID extends Number> User findById(ID id) {
        return null;
    }

    @Override
    public <ID extends Number> Collection<User> findAllByChildren(Collection<ID> ids) {
        return null;
    }

    @Override
    public Collection<User> findAllByParent(Collection<? super Number> ids) {
        return null;
    }
}
