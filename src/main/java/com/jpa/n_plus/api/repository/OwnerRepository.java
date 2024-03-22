package com.jpa.n_plus.api.repository;

import com.jpa.n_plus.api.entity.Owner;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select distinct o from Owner o join fetch o.pets")
    List<Owner> findAllJoinFetch();

    @EntityGraph(attributePaths = {"pets"})
    @Query("select distinct o from Owner o")
    List<Owner> findAllEntityGraph();
}
