package org.springblade.knowledge.ext.admin.extUnstructTask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.qiantong.qknow.common.annotation.Excel;
import tech.qiantong.qknow.module.ext.controller.admin.extUnstructTaskDocRel.vo.ExtUnstructTaskDocRelPageReqVO;
import tech.qiantong.qknow.common.ext.dataobject.unstructTaskRelation.ExtUnstructTaskRelationDO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 非结构化抽取任务 Response VO 对象 ext_unstruct_task
 *
 * @author qknow
 * @date 2025-02-18
 */
@Schema(description = "非结构化抽取任务 Response VO")
@Data
public class ExtUnstructTaskRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "ID")
    @Schema(description = "ID")
    private Long id;

    @Excel(name = "工作区id")
    @Schema(description = "工作区id", example = "")
    private Long workspaceId;

    @Excel(name = "任务名称")
    @Schema(description = "任务名称", example = "")
    private String name;

    @Excel(name = "任务状态")
    @Schema(description = "任务状态", example = "")
    private Integer status;

    @Excel(name = "发布状态")
    @Schema(description = "发布状态", example = "")
    private Integer publishStatus;

    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "发布时间", example = "")
    private Date publishTime;

    @Excel(name = "发布人id")
    @Schema(description = "发布人id", example = "")
    private Long publisherId;

    @Excel(name = "发布人")
    @Schema(description = "发布人", example = "")
    private String publishBy;

    @Excel(name = "是否有效")
    @Schema(description = "是否有效", example = "")
    private Boolean validFlag;

    @Excel(name = "删除标志")
    @Schema(description = "删除标志", example = "")
    private Boolean delFlag;

    @Excel(name = "创建人")
    @Schema(description = "创建人", example = "")
    private String createBy;

    @Excel(name = "创建人id")
    @Schema(description = "创建人id", example = "")
    private Long creatorId;

    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间", example = "")
    private Date createTime;

    @Excel(name = "更新人")
    @Schema(description = "更新人", example = "")
    private String updateBy;

    @Excel(name = "更新人id")
    @Schema(description = "更新人id", example = "")
    private Long updaterId;

    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间", example = "")
    private Date updateTime;

    @Excel(name = "备注")
    @Schema(description = "备注", example = "")
    private String remark;

    private List<ExtUnstructTaskDocRelPageReqVO> relPageReqVOS;

    private List<ExtUnstructTaskRelationDO> relRelationDOList;
}
