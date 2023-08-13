package com.example.sales.repositories;

import com.example.sales.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface SalesRepository extends JpaRepository<Sales,Long> {
    List<Sales> findSalesById(long kw);
}
