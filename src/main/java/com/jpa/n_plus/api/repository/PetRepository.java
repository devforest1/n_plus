package com.jpa.n_plus.api.repository;

import com.jpa.n_plus.api.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
