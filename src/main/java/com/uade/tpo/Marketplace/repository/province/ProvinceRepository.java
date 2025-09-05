package com.uade.tpo.Marketplace.repository.province;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.Marketplace.entity.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {

}

