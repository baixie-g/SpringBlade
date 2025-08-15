package org.springblade.knowledge.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

import java.util.Date;

/**
 * 知识文章实体类
 *
 * @author AI Assistant
 * @since 2025-08-16
 */
@Data
@TableName("knowledge_article")
@EqualsAndHashCode
@Schema(description = "知识文章")
public class KnowledgeArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private Long id;

    /**
     * 文章标题
     */
    @Schema(description = "文章标题")
    private String title;

    /**
     * 文章内容
     */
    @Schema(description = "文章内容")
    private String content;

    /**
     * 文章摘要
     */
    @Schema(description = "文章摘要")
    private String summary;

    /**
     * 文章分类
     */
    @Schema(description = "文章分类")
    private String category;

    /**
     * 标签（逗号分隔）
     */
    @Schema(description = "标签")
    private String tags;

    /**
     * 作者
     */
    @Schema(description = "作者")
    private String author;

    /**
     * 阅读次数
     */
    @Schema(description = "阅读次数")
    private Integer readCount;

    /**
     * 点赞次数
     */
    @Schema(description = "点赞次数")
    private Integer likeCount;

    /**
     * 状态（0-草稿，1-发布，2-下架）
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新人")
    private String updateBy;

    /**
     * 是否删除
     */
    @TableLogic
    @Schema(description = "是否删除")
    private Integer deleted;
}
