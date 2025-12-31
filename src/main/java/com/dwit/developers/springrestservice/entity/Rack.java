package com.dwit.developers.springrestservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rack",uniqueConstraints = {
        @UniqueConstraint(name="uk_rack_name",columnNames = "rack_name")
})
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="rack_name", nullable=false)
    private String rackName;

    @Column(name="number_of_brackets", nullable=false)
    private int numberOfBrackets;

    @Column(name="length",nullable = false)
    private float length;
    @Column(name="breadth",nullable = false)
    private float breadth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id",nullable = false,foreignKey = @ForeignKey(name="fk_rack_roomid"))
    private Room room;

    @OneToMany(mappedBy = "rack",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Item> racks;

}
