package org.springblade.knowledge.ext.dal.dataobject.extUnstructTaskDocRel;

import com.baomidou.mybatisplus.annotation.*;
import tech.qiantong.qknow.common.core.domain.BaseEntity;

/**
 * 任务文件关联 DO 对象 ext_unstruct_task_doc_rel
 *
 * @author qknow
 * @date 2025-02-19
 */
@Data
@TableName(value = "ext_unstruct_task_doc_rel")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
// @KeySequence("ext_unstruct_task_doc_rel_seq")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExtUnstructTaskDocRelDO extends BaseEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 工作区id */
    private Long workspaceId;

    /** 任务id */
    private Long taskId;

    /** 文件id */
    private Long docId;

    /** 文件名 */
    private String docName;

    /** 是否有效 */
    private Boolean validFlag;

    /** 删除标志 */
    @TableLogic
    private Boolean delFlag;


}
