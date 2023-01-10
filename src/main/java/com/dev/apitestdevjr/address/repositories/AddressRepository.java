package com.dev.apitestdevjr.address.repositories;

import com.dev.apitestdevjr.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {
    @Query("SELECT obj FROM Address obj WHERE obj.person.id = :id_person ORDER BY 'title'")
    List<Address> findAllByIdPerson(@Param(value = "id_person")Long id);
}
