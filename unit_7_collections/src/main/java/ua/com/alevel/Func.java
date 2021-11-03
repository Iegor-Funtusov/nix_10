package ua.com.alevel;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Func {

    public void parallel() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100_000_000; i++) {
            integers.add(random.nextInt(1000));
        }
        long start = System.currentTimeMillis();
        int sum = integers.stream().reduce(Integer::sum).get();
        long end = System.currentTimeMillis() - start;
        System.out.println("stream end = " + end);

        start = System.currentTimeMillis();
        sum = integers.stream().parallel().reduce(Integer::sum).get();
        end = System.currentTimeMillis() - start;
        System.out.println("stream parallel end = " + end);

        start = System.currentTimeMillis();
        sum = integers.parallelStream().reduce(Integer::sum).get();
        end = System.currentTimeMillis() - start;
        System.out.println("parallelStream end = " + end);
    }

    private void f() {
        AnonimImpl anonimImpl = new AnonimImpl();
        int sum = anonimImpl.sum(3, 8);

        Anonim anonim = new AnonimImpl();
        sum = anonim.sum(3, 8);

        anonim = specificSum();

        anonim = (a, b) -> a + b;
        sum = anonim.sum(3, 7);

        EmptyClass emptyClass = () -> { };

        OneArgument oneArgument = val -> val;
    }

    public void run() {
        List<Integer> integers = Arrays.asList(67, 1, 8, 67, 56, -5, 9, 45, 9);

        Stream<Integer> integerStream = integers.stream();

//        integerStream = Stream.of(1, 8, 67, 56, 9, 45);

        integerStream = integerStream
                .distinct()
//                .filter(i -> i > 0)
                .filter(this::isPositive)
//                .filter(i -> i % 2 == 0)
//                .filter(i -> EvenUtil.idEven(i))
                .filter(EvenUtil::idEven)
                .sorted();

        integers = integerStream.collect(Collectors.toList());

        System.out.println("integers = " + integers);
    }

    public void convert() {
        List<Customer> customers = new ArrayList<>();
        List<CustomerDto> customersDtos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setId((long) i);
            customer.setFirstName("FirstName" + i);
            customer.setLastName("LastName" + i);
            customer.setCreated(new Date());
            customers.add(customer);
        }

//        for (Customer customer : customers) {
//            CustomerDto customerDto = new CustomerDto();
//            customerDto.setId(customer.getId());
//            customerDto.setFullName(customer.getFirstName() + " " + customer.getLastName());
//            customerDto.setCreated(customer.getCreated().getTime());
//            customersDtos.add(customerDto);
//        }

//        for (Customer customer : customers) {
//            customersDtos.add(new CustomerDto(customer));
//        }

        customersDtos = customers
                .stream()
                .map(CustomerDto::new)
                .collect(Collectors.toList());

        List<String> list = customersDtos
                .stream()
//                .map(customerDto -> customerDto.getFullName())
                .map(CustomerDto::getFullName)
                .collect(Collectors.toList());

//        int sum = customersDtos
//                .stream()
//                .map(CustomerDto::getId)
//                .reduce(Integer::sum);

        List<Integer> integers = Arrays.asList(1, 2, 3);

        boolean even = integers.stream().anyMatch(this::isPositive);

        int summ = integers.stream().reduce(Integer::sum).get();

        System.out.println("summ = " + summ);

        for (CustomerDto customersDto : customersDtos) {
            System.out.println("customersDto = " + customersDto);
        }
    }

    private boolean isPositive(Integer integer) {
        return integer > 0;
    }

    private Anonim specificSum() {
        return new Anonim()
        {
            @Override
            public int sum(int a, int b) {
                return 0;
            }
        };
    }
}
