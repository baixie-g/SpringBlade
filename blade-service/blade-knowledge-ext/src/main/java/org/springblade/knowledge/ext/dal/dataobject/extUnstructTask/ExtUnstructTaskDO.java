package org.springblade.knowledge.ext.dal.dataobject.extUnstructTask;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import org.springblade.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 非结构化抽取任务 DO 对象 ext_unstruct_task
 *
 * @author qknow
 * @date 2025-02-18
 */
@Data
@TableName(value = "ext_unstruct_task")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
// @KeySequence("ext_unstruct_task_seq")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExtUnstructTaskDO extends BaseEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 工作区id */
    private Long workspaceId;

    /** 任务名称 */
    private String name;

    /** 任务状态 */
    private Integer status;

    /** 发布状态 */
    private Integer publishStatus;

    /** 发布时间 */
    private Date publishTime;

    /** 发布人id */
    private Long publisherId;

    /** 发布人 */
    private String publishBy;

    /** 是否有效 */
    private Boolean validFlag;

    /** 删除标志 */
    @TableLogic
    private Boolean delFlag;


}
