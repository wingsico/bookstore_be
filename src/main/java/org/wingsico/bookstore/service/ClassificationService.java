package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.Classification;

import java.util.List;

public interface ClassificationService {
    /**
     * 获取所有的Classification
     *
     */
    public List<Classification> findAll();
}
