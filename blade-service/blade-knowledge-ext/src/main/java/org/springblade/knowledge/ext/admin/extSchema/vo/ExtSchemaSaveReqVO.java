package org.springblade.knowledge.ext.admin.extSchema.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.qiantong.qknow.common.core.domain.BaseEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 概念配置 创建/修改 Request VO ext_schema
 *
 * @author qknow
 * @date 2025-02-17
 */
@Schema(description = "概念配置 Response VO")
@Data
public class ExtSchemaSaveReqVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "工作区id", example = "")
//    @NotNull(message = "工作区id不能为空")
    private Long workspaceId;

    @Schema(description = "概念名称", example = "")
    @NotBlank(message = "概念名称不能为空")
    @Size(max = 256, message = "概念名称长度不能超过256个字符")
    private String name;

    @Schema(description = "概念颜色", example = "")
    @Size(max = 16, message = "概念颜色长度不能超过16个字符")
    private String color;

    @Schema(description = "概念描述", example = "")
    @Size(max = 256, message = "概念描述长度不能超过256个字符")
    private String description;

    @Schema(description = "备注", example = "")
    @Size(max = 256, message = "备注长度不能超过256个字符")
    private String remark;


}
