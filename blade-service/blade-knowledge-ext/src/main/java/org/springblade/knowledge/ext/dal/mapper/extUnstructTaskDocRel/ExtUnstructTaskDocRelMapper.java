package org.springblade.knowledge.ext.dal.mapper.extUnstructTaskDocRel;

import tech.qiantong.qknow.common.core.page.PageResult;
import tech.qiantong.qknow.module.ext.controller.admin.extUnstructTaskDocRel.vo.ExtUnstructTaskDocRelPageReqVO;
import tech.qiantong.qknow.common.ext.dataobject.extUnstructTaskDocRel.ExtUnstructTaskDocRelDO;
import tech.qiantong.qknow.framework.mybatis.core.mapper.BaseMapperX;
import tech.qiantong.qknow.framework.mybatis.core.query.LambdaQueryWrapperX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 任务文件关联Mapper接口
 *
 * @author qknow
 * @date 2025-02-19
 */
public interface ExtUnstructTaskDocRelMapper extends BaseMapperX<ExtUnstructTaskDocRelDO> {

    default PageResult<ExtUnstructTaskDocRelDO> selectPage(ExtUnstructTaskDocRelPageReqVO reqVO) {
        // 定义排序的字段（防止 SQL 注入，与数据库字段名称一致）
        Set<String> allowedColumns = new HashSet<>(Arrays.asList("id", "create_time", "update_time"));

        // 构造动态查询条件
        return selectPage(reqVO, new LambdaQueryWrapperX<ExtUnstructTaskDocRelDO>()
                .eqIfPresent(ExtUnstructTaskDocRelDO::getWorkspaceId, reqVO.getWorkspaceId())
                .eqIfPresent(ExtUnstructTaskDocRelDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(ExtUnstructTaskDocRelDO::getDocId, reqVO.getDocId())
                .likeIfPresent(ExtUnstructTaskDocRelDO::getDocName, reqVO.getDocName())
                .eqIfPresent(ExtUnstructTaskDocRelDO::getCreateTime, reqVO.getCreateTime())
                // 如果 reqVO.getName() 不为空，则添加 name 的精确匹配条件（name = '<name>'）
                // .likeIfPresent(ExtUnstructTaskDocRelDO::getName, reqVO.getName())
                // 按照 createTime 字段降序排序
                .orderBy(reqVO.getOrderByColumn(), reqVO.getIsAsc(), allowedColumns));
    }
}
