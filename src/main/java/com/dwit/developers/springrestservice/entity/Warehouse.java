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
@Table(name="ware_house",uniqueConstraints = {
        @UniqueConstraint(name="uk_warehouse_name",columnNames = "name")
})
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name",nullable = false,length = 100)
    private String name;
    @Column(name="location",nullable = false)
    private String location;
    //To handle the foreign key constraints
    @OneToMany(mappedBy = "warehouse",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Room> rooms;
}
