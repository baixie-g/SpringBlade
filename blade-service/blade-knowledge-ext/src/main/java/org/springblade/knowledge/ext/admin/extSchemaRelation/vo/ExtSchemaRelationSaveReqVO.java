package org.springblade.knowledge.ext.admin.extSchemaRelation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.qiantong.qknow.common.core.domain.BaseEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 关系配置 创建/修改 Request VO ext_schema_relation
 *
 * @author qknow
 * @date 2025-02-18
 */
@Schema(description = "关系配置 Response VO")
@Data
public class ExtSchemaRelationSaveReqVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "工作区id", example = "")
//    @NotNull(message = "工作区id不能为空")
    private Long workspaceId;

    @Schema(description = "起点概念id", example = "")
    @NotNull(message = "起点概念id不能为空")
    private Long startSchemaId;

    @Schema(description = "关系", example = "")
    @NotBlank(message = "关系不能为空")
    @Size(max = 256, message = "关系长度不能超过256个字符")
    private String relation;

    @Schema(description = "终点概念id", example = "")
    @NotNull(message = "终点概念id不能为空")
    private Long endSchemaId;

    @Schema(description = "是否可逆", example = "")
    @NotNull(message = "是否可逆不能为空")
    private Integer inverseFlag;

    @Schema(description = "备注", example = "")
    @Size(max = 256, message = "备注长度不能超过256个字符")
    private String remark;


}
