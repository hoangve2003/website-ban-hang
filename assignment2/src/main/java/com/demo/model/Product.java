package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    @NotBlank
    String name;
    @Column(name = "image")
    String image;
    @Column(name = "price")
    @NotNull @Min(0) @Max(10000000)
    Integer price;
    @Temporal(TemporalType.DATE)
    @Column(name = "createdate")
    @NotNull
    Date createDate = new Date();
    @Column(name = "available")
    Boolean available;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonIgnore
    @Valid @NotNull
    Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<OrderDetail> orderDetails;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", category=" + category +
                '}';
    }
}
