package org.springblade.knowledge.ext.dal.dataobject.unstructTaskRelation;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import org.springblade.common.core.domain.BaseEntity;

/**
 * 任务文件关联 DO 对象 ext_unstruct_task_relation
 *
 * @author qknow
 * @date 2025-04-03
 */
@Data
@TableName(value = "ext_unstruct_task_relation")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
// @KeySequence("ext_unstruct_task_relation_seq")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExtUnstructTaskRelationDO extends BaseEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 工作区id */
    private Long workspaceId;

    /** 任务id */
    private Long taskId;

    /** 关系id */
    private Long relationId;

    /** 是否有效 */
    private Boolean validFlag;

    /** 删除标志 */
    @TableLogic
    private Boolean delFlag;


}
