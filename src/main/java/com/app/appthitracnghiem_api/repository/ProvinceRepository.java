package com.app.appthitracnghiem_api.repository;

import com.app.appthitracnghiem_api.entity.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Provinces, Integer> {
    public Provinces findProvinceById(int id);
}
