package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Apply;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/**
 * Created by liming on 17-5-21.
 */
public class ApplyRepositoryTest extends RepositoryTest{
    @Autowired
    private ApplyRepository applyRepository;
    static private Logger logger = Logger.getLogger(ApplyRepositoryTest.class.getName());

    @Test
    public void findAll() {
        applyRepository.findAll();
    }

    @Test
    public void save() {
        Apply apply = new Apply();
        applyRepository.save(apply);
    }
}