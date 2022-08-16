package com.example.pensionerdetailsmicroservice.repo;

import com.example.pensionerdetailsmicroservice.model.PensionerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PensionerDetailsRepo extends JpaRepository<PensionerDetail,Long> {
    Optional<PensionerDetail> findByAadhaarNumber(String aadhaarNumber);
}
