package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "cart_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cart_product_window",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_product_window_id", referencedColumnName = "product_window_id"))
    private List<ProductWindow> productWindowList;
}
