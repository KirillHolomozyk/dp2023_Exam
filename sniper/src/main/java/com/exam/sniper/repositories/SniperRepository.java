package com.exam.sniper.repositories;

import com.exam.sniper.entities.Sniper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource(collectionResourceRel = "snipers", path = "snipers")
public interface SniperRepository extends JpaRepository<Sniper, Long> {
}