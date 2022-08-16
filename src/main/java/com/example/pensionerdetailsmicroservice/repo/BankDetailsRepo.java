package com.example.pensionerdetailsmicroservice.repo;

import com.example.pensionerdetailsmicroservice.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails,Long> {
}
