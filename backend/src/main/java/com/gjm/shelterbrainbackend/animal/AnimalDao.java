package com.gjm.shelterbrainbackend.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalDao extends JpaRepository<Animal, Long> {
}
