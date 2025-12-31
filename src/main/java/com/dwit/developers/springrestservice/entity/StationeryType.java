package com.dwit.developers.springrestservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="stationery_type",uniqueConstraints = {
        @UniqueConstraint(name="uk_stationeryType_name", columnNames = "name")

})
public class StationeryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name",nullable = false,length = 100)
    private String name;

    @OneToMany(mappedBy = "stationeryType",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Category> categories;
}
