package ua.com.alevel;

import lombok.Getter;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Transaction;
import ua.com.alevel.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class DbInMemory {

    private static DbInMemory instance;

    private final List<User> users;
    private final List<Account> accounts;
    private final List<Category> categories;
    private final List<Transaction> transactions;

    private DbInMemory() {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public static DbInMemory getInstance() {
        if (instance == null) {
            instance = new DbInMemory();
        }
        return instance;
    }

    public <E extends BaseEntity> void create(Class<E> entityClass, E entity) {
        entity.setId(generateId(entityClass));
        if (Account.class.isAssignableFrom(entityClass)) {
            accounts.add((Account) entity);
        }
        if (User.class.isAssignableFrom(entityClass)) {
            users.add((User) entity);
        }
        if (Category.class.isAssignableFrom(entityClass)) {
            categories.add((Category) entity);
        }
        if (Transaction.class.isAssignableFrom(entityClass)) {
            transactions.add((Transaction) entity);
        }
    }

    public <E extends BaseEntity> E findById(Class<E> entityClass, String id) {
        if (Account.class.isAssignableFrom(entityClass)) {
            return (E) accounts.stream().filter(entity -> entity.getId().equals(id)).findFirst().get();
        }
        if (User.class.isAssignableFrom(entityClass)) {
            return (E) users.stream().filter(entity -> entity.getId().equals(id)).findFirst().get();
        }
        if (Category.class.isAssignableFrom(entityClass)) {
            return (E) categories.stream().filter(entity -> entity.getId().equals(id)).findFirst().get();
        }
        if (Transaction.class.isAssignableFrom(entityClass)) {
            return (E) transactions.stream().filter(entity -> entity.getId().equals(id)).findFirst().get();
        }
        return null;
    }

    public List<Account> byUserId(String userId) {
        return accounts.stream().filter(account -> account.getUserId().equals(userId)).collect(Collectors.toList());
    }

    public void init() {
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("name" + i);
            create(User.class, user);

            Account account = new Account();
            account.setUserId(user.getId());
            account.setBalance(new BigDecimal("100.00"));
            create(Account.class, account);

            if (i % 2 != 0) {
                account = new Account();
                account.setUserId(user.getId());
                account.setBalance(new BigDecimal("150.00"));
                create(Account.class, account);
            }
        }

        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setIncome(i % 2 == 0);
            category.setName("category" + i);
            create(Category.class, category);
        }
    }

    private <E extends BaseEntity> String generateId(Class<E> entityClass) {
        String id = UUID.randomUUID().toString();
        if (Account.class.isAssignableFrom(entityClass) && accounts.stream().anyMatch(entity -> entity.getId().equals(id))) {
            return generateId(entityClass);
        }
        if (User.class.isAssignableFrom(entityClass) && users.stream().anyMatch(entity -> entity.getId().equals(id))) {
            return generateId(entityClass);
        }
        if (Category.class.isAssignableFrom(entityClass) && categories.stream().anyMatch(entity -> entity.getId().equals(id))) {
            return generateId(entityClass);
        }
        if (Transaction.class.isAssignableFrom(entityClass) && transactions.stream().anyMatch(entity -> entity.getId().equals(id))) {
            return generateId(entityClass);
        }
        return id;
    }
}
