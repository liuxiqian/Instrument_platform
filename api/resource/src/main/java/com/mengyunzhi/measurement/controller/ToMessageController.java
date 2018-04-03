package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.ToMessageService;
import com.mengyunzhi.measurement.jsonView.ToMessageJsonView;
import com.mengyunzhi.measurement.entity.ToMessage;
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
 * 接受消息 控制器
 */
    @Api(tags = "ToMessage (接受消息)", description = "记录接受消息")
@RestController
@RequestMapping("/ToMessage")
public class ToMessageController {
    @Autowired
    private ToMessageService toMessageService;

    @GetMapping("/pageAllOfCurrentUser")
    @ApiOperation(value = "pageAllOfCurrentUser(获取当前用户的所有已接受信息)", notes = "获取当前用户的所有已接受信息", nickname = "ToMessage_pageAllOfCurrentUser")
    @JsonView(ToMessageJsonView.baseJsonView.class)
    public Page<ToMessage> pageAllOfCurrentUser(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<ToMessage> messages = toMessageService.pageAllOfCurrentUser(pageable);
        return messages;
    }

    @ApiOperation(value = "get (获取一个接受消息实体)", notes = "获取一个接受消息实体", nickname = "ToMessage_get")
    @GetMapping(value = "/{id}")
    @JsonView(ToMessageJsonView.baseJsonView.class)
    public ToMessage get(@ApiParam(value = "消息ID") @PathVariable Long id){
        return toMessageService.get(id);
    }

    @ApiOperation(value = "reply (回复)", notes = "回复消息", nickname = "ToMessage_reply")
    @PostMapping("/reply")
    public void reply(@ApiParam(value = "消息实体") @RequestBody ToMessage toMessage)
    {
        toMessageService.reply(toMessage);
        return;
    }

    @ApiOperation(value = "setMessageToRead (将消息设置为已读)", notes = "将消息设置为已读", nickname = "ToMessage_setMessageToRead")
    @PostMapping("/setMessageToRead/{id}")
    public void setMessageToRead(@ApiParam(value = "消息ID") @PathVariable Long id) {
        toMessageService.setMessageToRead(id);
        return;
    }

    @GetMapping("/pageReceiveUnReadToMessageOfCurrentUser")
    @ApiOperation(value = "pageReceiveUnReadToMessageOfCurrentUser(获取当前用户的未读收件消息)", notes = "获取当前用户的未读收件消息", nickname = "ToMessage_pageReceiveUnReadMessageOfCurrentUser")
    @JsonView(ToMessageJsonView.baseJsonView.class)
    public Page<ToMessage> pageReceiveUnReadToMessageOfCurrentUser(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<ToMessage> toMessages = toMessageService.pageReceiveUnReadToMessageOfCurrentUser(pageable);
        return toMessages;
    }

    @ApiOperation(value = "sendMessageToManagementDepartmentOfCurrentUser (向当前用户所属市级管理部门发送消息)", notes = "向当前用户所属市级管理部门发送消息", nickname = "ToMessage_sendMessageToManagementDepartmentOfCurrentUser")
    @PostMapping("/sendMessageToManagementDepartmentOfCurrentUser")
    @JsonView(ToMessageJsonView.baseJsonView.class)
    public ToMessage sendMessageToManagementDepartmentOfCurrentUser(@ApiParam(value = "消息实体") @RequestBody ToMessage toMessage)
    {
        return toMessageService.sendMessageToManagementDepartmentOfCurrentUser(toMessage);
    }

    @ApiOperation(value = "batchDelete 批量删除发送消息",notes = "批量删除发送消息", nickname = "ToMessage_batchDelete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/batchDelete/{toMessageIds}")
    public void batchDelete(@ApiParam(value = "消息id数组") @PathVariable List<Integer> toMessageIds) {
        toMessageService.batchDelete(toMessageIds);
        return;
    }

    @ApiOperation(value = "delete",notes = "删除一条接受消息", nickname = "ToMessage_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id) {
        toMessageService.delete(id);
        return;
    }

}
