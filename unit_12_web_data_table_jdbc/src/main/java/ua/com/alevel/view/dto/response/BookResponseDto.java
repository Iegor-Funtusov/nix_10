package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Book;

public class BookResponseDto extends ResponseDto {

    private String bookName;
    private String imageUrl;
    private Integer pageSize;
    private Integer publicationDate;
    private String description;
    private Integer publisherCount;
    private Integer authorCount;

    public BookResponseDto() {
        this.publisherCount = 0;
        this.authorCount = 0;
    }

    public BookResponseDto(Book book) {
        this();
        setId(book.getId());
        setCreated(book.getCreated());
        setUpdated(book.getUpdated());
        setVisible(book.getVisible());
        this.bookName = book.getBookName();
        this.imageUrl = book.getImageUrl();
        this.pageSize = book.getPageSize();
        this.publicationDate = book.getPublicationDate();
        this.description = book.getDescription();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublisherCount() {
        return publisherCount;
    }

    public void setPublisherCount(Integer publisherCount) {
        this.publisherCount = publisherCount;
    }

    public Integer getAuthorCount() {
        return authorCount;
    }

    public void setAuthorCount(Integer authorCount) {
        this.authorCount = authorCount;
    }
}
