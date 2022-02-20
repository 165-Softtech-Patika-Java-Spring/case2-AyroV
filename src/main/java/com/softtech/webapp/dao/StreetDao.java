package com.softtech.webapp.dao;

import com.softtech.webapp.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StreetDao extends JpaRepository<Street, Long> {
    List<Street> findAllByNeighborhoodId(Long id);
    @Modifying
    @Transactional
    @Query("update Street st set st.name = ?1 where st.id = ?2")
    void setStreetNameById(String name, Long id);
}
