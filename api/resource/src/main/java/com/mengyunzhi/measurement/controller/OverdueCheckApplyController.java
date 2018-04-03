package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.OverdueCheckApplyService;
import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.OverdueCheckApply;
import com.mengyunzhi.measurement.entity.Work;
import com.mengyunzhi.measurement.jsonView.OverdueCheckApplyControllerJsonView;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * @author panjie on 2017/12/12
 * 超期未检
 */
@Api(tags = "OverdueCheckApply 超期未检", description = "指强检器具超出了检定有效期而未检定的")
@RestController
@RequestMapping("/OverdueCheckApply")
public class OverdueCheckApplyController {
    @Autowired
    OverdueCheckApplyService overdueCheckApplyService;      // 超期未检

    @ApiOperation(value = "saveWorkWithCurrentUserAudit", notes = "保存 申请 -> 超期未检申请", nickname = "OverdueCheckApply_save")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    @JsonView(OverdueCheckApplyControllerJsonView.save.class)
    public OverdueCheckApply save(@ApiParam("申请") @RequestBody OverdueCheckApply overdueCheckApply) {
        return overdueCheckApplyService.save(overdueCheckApply);
    }

    @ApiOperation(value = "reply", notes = "回复 申请 -> 超期未检申请 ", nickname = "OverdueCheckApply_reply")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/")
    public void reply(@ApiParam("工作实体") @RequestBody WorkOverdueCheckApply workOverdueCheckApply) {
        workOverdueCheckApply.getWork().setApply(workOverdueCheckApply.getApply());
        overdueCheckApplyService.reply(workOverdueCheckApply.getWork());
        return;
    }

    @ApiOperation(value = "pageOfCurrentUser", notes = "当前登录用户对应的审核分页信息",
            nickname = "OverdueCheckApply_pageOfCurrentUser")
    @GetMapping("/pageOfCurrentUser")
    @JsonView(OverdueCheckApplyControllerJsonView.page.class)
    public Page<CurrentWork> pageOfCurrentUser(
            @ApiParam("申请起始日期") @RequestParam(value = "startApplyDate", required = false) Date startApplyDate,
            @ApiParam("申请终止日期") @RequestParam(value = "endApplyDate", required = false) Date endApplyDate,
            @ApiParam("器具用户") @RequestParam(value = "applyDepartmentId", required = false) Long applyDepartmentId,
            @ApiParam("分页信息") @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return overdueCheckApplyService.pageOfCurrentUser(startApplyDate, endApplyDate, applyDepartmentId, pageable);
    }

    @ApiModel(value = "WorkOverdueCheckApply (工作 + 超期检定申请)", description = "解决数据自动绑定时，无法绑定由Apply继承而来的实体")
    static public class WorkOverdueCheckApply {
        @ApiModelProperty("工作")
        private Work work;
        @ApiModelProperty("超期未检申请")
        private OverdueCheckApply apply;

        public WorkOverdueCheckApply() {
        }

        public Work getWork() {
            return work;
        }

        public void setWork(Work work) {
            this.work = work;
        }

        public OverdueCheckApply getApply() {
            return apply;
        }

        public void setApply(OverdueCheckApply apply) {
            this.apply = apply;
        }
    }

}
