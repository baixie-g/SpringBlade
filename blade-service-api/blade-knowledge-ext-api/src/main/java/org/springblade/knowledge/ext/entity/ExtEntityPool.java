package org.springblade.knowledge.ext.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;

import java.time.LocalDateTime;

/**
 * 实体池实体类
 *
 * @author Chill
 */
@Data
@TableName("ext_entity_pool")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "实体池")
public class ExtEntityPool extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 实体名称
     */
    @TableField("entity_name")
    @Schema(description = "实体名称")
    private String entityName;

    /**
     * 实体类型
     */
    @TableField("entity_type")
    @Schema(description = "实体类型")
    private String entityType;

    /**
     * 实体描述
     */
    @TableField("entity_description")
    @Schema(description = "实体描述")
    private String entityDescription;

    /**
     * 实体属性JSON
     */
    @TableField("entity_properties")
    @Schema(description = "实体属性JSON")
    private String entityProperties;

    /**
     * 来源数据源
     */
    @TableField("source_datasource")
    @Schema(description = "来源数据源")
    private String sourceDatasource;

    /**
     * 来源文档
     */
    @TableField("source_document")
    @Schema(description = "来源文档")
    private String sourceDocument;

    /**
     * 抽取时间
     */
    @TableField("extract_time")
    @Schema(description = "抽取时间")
    private LocalDateTime extractTime;

    /**
     * 抽取方法
     */
    @TableField("extract_method")
    @Schema(description = "抽取方法")
    private String extractMethod;

    /**
     * 置信度
     */
    @TableField("confidence")
    @Schema(description = "置信度")
    private Double confidence;

    /**
     * 状态（0-待审核，1-已确认，2-已拒绝）
     */
    @TableField("status")
    @Schema(description = "状态")
    private Integer status;

    /**
     * 备注
     */
    @TableField("remark")
    @Schema(description = "备注")
    private String remark;
}
