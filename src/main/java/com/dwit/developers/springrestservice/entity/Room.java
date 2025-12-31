package com.dwit.developers.springrestservice.entity;

import jakarta.persistence.*;
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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="room",uniqueConstraints = {
        @UniqueConstraint(name="uk_room_name",columnNames = "name"),
        @UniqueConstraint(name="uk_room_keyNumber",columnNames = "keyNumber"),

})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="room_name",nullable = false)
    private String name;
    @Column(name="key_number",nullable = false)
    private Integer keyNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ware_house_id",nullable=false,foreignKey = @ForeignKey(name="fk_room_warehouseid"))
    private Warehouse warehouse;

    @OneToMany(mappedBy = "room",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Rack> racks;

}
