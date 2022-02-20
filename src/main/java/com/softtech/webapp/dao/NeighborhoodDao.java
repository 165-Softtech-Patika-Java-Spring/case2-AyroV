package com.softtech.webapp.dao;

import com.softtech.webapp.entity.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NeighborhoodDao extends JpaRepository<Neighborhood, Long> {
    List<Neighborhood> findAllByDistrictId(Long id);
    @Modifying
    @Transactional
    @Query("update Neighborhood nh set nh.name = ?1 where nh.id = ?2")
    void setNeighborhoodNameById(String name, Long id);
}
