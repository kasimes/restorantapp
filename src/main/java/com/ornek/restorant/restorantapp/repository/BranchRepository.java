package com.ornek.restorant.restorantapp.repository;

import com.ornek.restorant.restorantapp.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch , Long> {

    @Query(value = """
        SELECT b.* FROM branch b
        JOIN address a ON b.address_id = a.id
        WHERE (6371 * acos(
            cos(radians(:lat)) * cos(radians(a.latitude)) *
            cos(radians(a.longitude) - radians(:lon)) +
            sin(radians(:lat)) * sin(radians(a.latitude))
        )) < :radius
    """, nativeQuery = true)
    List<Branch> findBranchesNearby(
            @Param("lat") double latitude,
            @Param("lon") double longitude,
            @Param("radius") double radiusKm
    );


}
