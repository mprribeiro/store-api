package com.marcosribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcosribeiro.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository <Address, Integer> {

}
