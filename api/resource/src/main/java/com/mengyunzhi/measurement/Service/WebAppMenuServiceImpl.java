package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.WebAppMenu;
import com.mengyunzhi.measurement.repository.WebAppMenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 安强 on 2017/6/2.
 * 前台菜单  M层测试
 */
@Service
public class WebAppMenuServiceImpl implements WebAppMenuService {
    static final Logger logger = LoggerFactory.getLogger(WebAppMenuServiceImpl.class.getName());
    private final WebAppMenuRepository webAppMenuRepository;

    @Autowired
    public WebAppMenuServiceImpl(WebAppMenuRepository webAppMenuRepository) {
        this.webAppMenuRepository = webAppMenuRepository;
    }

    @Override
    public WebAppMenu save(WebAppMenu webAppMenu) {
        webAppMenuRepository.save(webAppMenu);
        return webAppMenu;
    }

    @Override
    public List<WebAppMenu> getAll() {
        // 按权重进行排序，权重越小越靠前
        return webAppMenuRepository.findAll(new Sort("weight"));
    }

    @Override
    public void updateWeightByIdAndWeight(Long id, int weight) {
        WebAppMenu webAppMenu = webAppMenuRepository.findOne(id);
        webAppMenu.setWeight(weight);
        webAppMenuRepository.save(webAppMenu);
    }
}
