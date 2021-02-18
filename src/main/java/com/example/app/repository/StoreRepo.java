package com.example.app.repository;

import com.example.app.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store, Integer> {
    Store findStoreByStoreId(Integer storeId);
}
