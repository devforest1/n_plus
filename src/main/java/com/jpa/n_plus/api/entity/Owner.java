package com.jpa.n_plus.api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Owner {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<>();

    @Builder
    public Owner(String name, List<Pet> pets) {
        this.name = name;
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pets=" + pets +
                '}';
    }
}
