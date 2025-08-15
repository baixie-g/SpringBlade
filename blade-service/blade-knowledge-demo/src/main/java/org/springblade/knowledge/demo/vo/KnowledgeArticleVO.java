package org.springblade.knowledge.demo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

import java.util.Date;

/**
 * 知识文章视图对象
 *
 * @author AI Assistant
 * @since 2025-08-16
 */
@Data
@EqualsAndHashCode
@Schema(description = "知识文章视图对象")
public class KnowledgeArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
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
     * 状态名称
     */
    @Schema(description = "状态名称")
    private String statusName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateBy;
}
