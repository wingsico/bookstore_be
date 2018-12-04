package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Classification;
import org.wingsico.bookstore.domain.repo.ClassificationRepo;
import org.wingsico.bookstore.service.ClassificationService;

import java.util.List;

@Service
public class ClassificationSerciceImpl implements ClassificationService {
    @Autowired
    ClassificationRepo classificationRepo;

    @Override
    public List<Classification> findAll(){ return classificationRepo.findAll(); }
}
