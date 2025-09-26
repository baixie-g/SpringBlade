package org.springblade.knowledge.ext.dal.dataobject.extStructTask;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import org.springblade.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 结构化抽取任务 DO 对象 ext_struct_task
 *
 * @author qknow
 * @date 2025-02-25
 */
@Data
@TableName(value = "ext_struct_task")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
// @KeySequence("ext_struct_task_seq")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExtStructTaskDO extends BaseEntity {
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

    /** 数据源id */
    private Long datasourceId;

    /** 数据源名称 */
    private String datasourceName;

    /** 是否有效 */
    private Boolean validFlag;

    /** 删除标志 */
    @TableLogic
    private Boolean delFlag;


}
