package br.com.erudio.rest_with_spring_boot_and_java_erudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "title", "author", "launchDate", "price"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {
    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String title;
    private String author;
    private Date launchDate;
    private BigDecimal price;

    public BookVO() {
    }

    public Long getKey() { return key; }

    public void setKey(Long key) { this.key = key; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public Date getLaunchDate() { return launchDate; }

    public void setLaunchDate(Date launchDate) { this.launchDate = launchDate; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return Objects.equals(key, bookVO.key) && Objects.equals(title, bookVO.title) && Objects.equals(author, bookVO.author) && Objects.equals(launchDate, bookVO.launchDate) && Objects.equals(price, bookVO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, title, author, launchDate, price);
    }
}
