package com.example.demo.jpa.repository.sampledata;

import com.example.demo.entity.sampledata.EducationCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationCostRepository extends JpaRepository<EducationCost, Integer> {

    List<EducationCost> findBytotalcostLessThan(int totalcost);

    List<EducationCost> findByfirstgradeBetween(int firstgrade1, int firstgrade2);

    List<EducationCost> findBysubjectStartingWith(String subject);

    List<EducationCost> findBysubjectContaining(String subject);


}