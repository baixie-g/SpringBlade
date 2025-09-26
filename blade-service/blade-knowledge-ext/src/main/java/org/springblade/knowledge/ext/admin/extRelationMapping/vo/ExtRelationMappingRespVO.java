package org.springblade.knowledge.ext.admin.extRelationMapping.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springblade.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 关系映射 Response VO 对象 ext_relation_mapping
 *
 * @author qknow
 * @date 2025-02-25
 */
@Schema(description = "关系映射 Response VO")
@Data
public class ExtRelationMappingRespVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "ID")
    @Schema(description = "ID")
    private Long id;

    @Excel(name = "工作区id")
    @Schema(description = "工作区id", example = "")
    private Long workspaceId;

    @Excel(name = "任务id")
    @Schema(description = "任务id", example = "")
    private Long taskId;

    @Excel(name = "表名")
    @Schema(description = "表名", example = "")
    private String tableName;

    @Excel(name = "表显示名称")
    @Schema(description = "表显示名称", example = "")
    private String tableComment;

    @Excel(name = "字段名")
    @Schema(description = "字段名", example = "")
    private String fieldName;

    @Excel(name = "字段显示名称")
    @Schema(description = "字段显示名称", example = "")
    private String fieldComment;

    @Excel(name = "关系")
    @Schema(description = "关系", example = "")
    private String relation;

    @Excel(name = "关联表")
    @Schema(description = "关联表", example = "")
    private String relationTable;

    @Schema(description = "关联表名称", example = "")
    private String relationTableName;

    @Excel(name = "关联表字段")
    @Schema(description = "关联表字段", example = "")
    private String relationField;

    @Excel(name = "关联表实体字段")
    @Schema(description = "关联表实体字段", example = "")
    private String relationNameField;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间", example = "")
    private Date createTime;

    @Excel(name = "更新人")
    @Schema(description = "更新人", example = "")
    private String updateBy;

    @Excel(name = "更新人id")
    @Schema(description = "更新人id", example = "")
    private Long updaterId;

    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间", example = "")
    private Date updateTime;

    @Excel(name = "备注")
    @Schema(description = "备注", example = "")
    private String remark;

}
