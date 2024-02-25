package org.example.catalogservice.service;

import lombok.extern.slf4j.Slf4j;
import org.example.catalogservice.jpa.CatalogEntity;
import org.example.catalogservice.jpa.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {

    private CatalogRepository catalogRepository;

    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
