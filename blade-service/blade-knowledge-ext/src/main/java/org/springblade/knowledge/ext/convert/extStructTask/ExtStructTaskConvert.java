package org.springblade.knowledge.ext.convert.extStructTask;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.knowledge.ext.admin.extStructTask.vo.ExtStructTaskPageReqVO;
import org.springblade.knowledge.ext.admin.extStructTask.vo.ExtStructTaskRespVO;
import org.springblade.knowledge.ext.admin.extStructTask.vo.ExtStructTaskSaveReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extStructTask.ExtStructTaskDO;

import java.util.List;

/**
 * 结构化抽取任务 Convert
 *
 * @author qknow
 * @date 2025-02-25
 */
@Mapper
public interface ExtStructTaskConvert {
    ExtStructTaskConvert INSTANCE = Mappers.getMapper(ExtStructTaskConvert.class);

    /**
     * PageReqVO 转换为 DO
     * @param extStructTaskPageReqVO 请求参数
     * @return ExtStructTaskDO
     */
     ExtStructTaskDO convertToDO(ExtStructTaskPageReqVO extStructTaskPageReqVO);

    /**
     * SaveReqVO 转换为 DO
     * @param extStructTaskSaveReqVO 保存请求参数
     * @return ExtStructTaskDO
     */
     ExtStructTaskDO convertToDO(ExtStructTaskSaveReqVO extStructTaskSaveReqVO);

    /**
     * DO 转换为 RespVO
     * @param extStructTaskDO 实体对象
     * @return ExtStructTaskRespVO
     */
     ExtStructTaskRespVO convertToRespVO(ExtStructTaskDO extStructTaskDO);

    /**
     * DOList 转换为 RespVOList
     * @param extStructTaskDOList 实体对象列表
     * @return List<ExtStructTaskRespVO>
     */
     List<ExtStructTaskRespVO> convertToRespVOList(List<ExtStructTaskDO> extStructTaskDOList);
}
