package com.dwit.developers.springrestservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name",nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",nullable = false,foreignKey = @ForeignKey(name="fk_item_categoryid"))
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rack_id",nullable = false,foreignKey = @ForeignKey(name="fk_item_rackid"))
    private Rack rack;
}
