package com.example.joinair.repository;



import com.example.joinair.entity.Drone;
import com.example.joinair.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DroneAdRepository extends JpaRepository<Drone, Integer> {


    @Query("SELECT d FROM Drone d ORDER BY d.D_Code DESC")
    Page<Drone> findAllOrderedByDCodeWithPagination(Pageable pageable);



    @Query("SELECT d FROM Drone d WHERE d.D_Code = ?1 ORDER BY d.D_Code DESC")
    Page<Drone> findDronesByDCode(Integer searchKeyword, Pageable pageable);


    @Query("SELECT d FROM Drone d WHERE d.D_Name LIKE %?1% ORDER BY d.D_Code DESC")
    Page<Drone> findDronesByDNameContaining(String searchKeyword, Pageable pageable);




}



