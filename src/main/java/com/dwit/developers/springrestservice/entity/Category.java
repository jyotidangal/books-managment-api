package com.dwit.developers.springrestservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="category_table",uniqueConstraints ={
        @UniqueConstraint(name = "uk_category_name",columnNames = "name")

})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name",nullable = false,length = 100)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stationery_type_id",nullable = false,foreignKey = @ForeignKey(name="fk_category_stationerytypeid"))
    private StationeryType stationeryType;

    //to handle foreign key constraints
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Item> items;

}
