package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.SystemConfigService;
import com.mengyunzhi.measurement.entity.SystemConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "SystemConfig 系统设置", description = "系统设置")
@RestController
@RequestMapping("/SystemConfig")
public class SystemConfigController {
    @Autowired
    SystemConfigService systemConfigService;

    @ApiOperation(value = "getAll 获取所有数据", notes = "获取所有的数据，不分页", nickname = "SystemConfig_getAll")
    @GetMapping("/getAll")
    public List<SystemConfig> findAll() {
        return systemConfigService.findAll();
    }
}
