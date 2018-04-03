package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.FromMessageService;
import com.mengyunzhi.measurement.jsonView.FromMessageJsonView;
import com.mengyunzhi.measurement.entity.FromMessage;
import com.mengyunzhi.measurement.entity.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By chuhang on 17-10-17
 * 发送消息 控制器
 */
@Api(tags = "FromMessage (发送消息)", description = "记录发送消息")
@RestController
@RequestMapping("/FromMessage")
public class FromMessageController {
    @Autowired
    private FromMessageService fromMessageService;

    @GetMapping("/pageAllOfCurrentUser")
    @ApiOperation(value = "pageAllOfCurrentUser(获取当前用户的所有已发送信息)", notes = "获取当前用户的所有已发送信息", nickname = "FromMessage_pageAllOfCurrentUser")
    @JsonView(FromMessageJsonView.baseJsonView.class)
    public Page<FromMessage> pageAllOfCurrentUser(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<FromMessage> messages = fromMessageService.pageAllOfCurrentUser(pageable);
        return messages;
    }

    @ApiOperation(value = "get (获取一个发送消息实体)", notes = "获取一个发送消息实体", nickname = "FromMessage_get")
    @GetMapping(value = "/{id}")
    @JsonView(FromMessageJsonView.baseJsonView.class)
    public Message get(@ApiParam(value = "消息ID") @PathVariable Long id){
        return fromMessageService.get(id);
    }

    @ApiOperation(value = "delete",notes = "删除一条发送消息", nickname = "FromMessage_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id) {
        fromMessageService.delete(id);
        return;
    }

    @ApiOperation(value = "batchDelete 批量删除发送消息",notes = "批量删除发送消息", nickname = "FromMessage_batchDelete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/batchDelete/{fromMessageIds}")
    public void batchDelete(@ApiParam(value = "消息id数组") @PathVariable List<Integer> fromMessageIds) {
        fromMessageService.batchDelete(fromMessageIds);
        return;
    }
}
