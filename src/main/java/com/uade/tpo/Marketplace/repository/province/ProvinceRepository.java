package com.uade.tpo.Marketplace.repository.province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {

}

