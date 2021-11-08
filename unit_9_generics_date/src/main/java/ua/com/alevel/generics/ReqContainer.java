package ua.com.alevel.generics;

public class ReqContainer<DTO extends Dto> {

    private final DTO data;

    public ReqContainer(DTO data) {
        this.data = data;
    }

//    public <E> ReqContainer(E e) {
//        this.data = data;
//    }

    public DTO getData() {
        return data;
    }
}
