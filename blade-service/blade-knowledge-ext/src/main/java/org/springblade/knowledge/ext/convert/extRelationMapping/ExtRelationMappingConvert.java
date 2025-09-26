package org.springblade.knowledge.ext.convert.extRelationMapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.knowledge.ext.admin.extRelationMapping.vo.ExtRelationMappingPageReqVO;
import org.springblade.knowledge.ext.admin.extRelationMapping.vo.ExtRelationMappingRespVO;
import org.springblade.knowledge.ext.admin.extRelationMapping.vo.ExtRelationMappingSaveReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extRelationMapping.ExtRelationMappingDO;

import java.util.List;

/**
 * 关系映射 Convert
 *
 * @author qknow
 * @date 2025-02-25
 */
@Mapper
public interface ExtRelationMappingConvert {
    ExtRelationMappingConvert INSTANCE = Mappers.getMapper(ExtRelationMappingConvert.class);

    /**
     * PageReqVO 转换为 DO
     * @param extRelationMappingPageReqVO 请求参数
     * @return ExtRelationMappingDO
     */
     ExtRelationMappingDO convertToDO(ExtRelationMappingPageReqVO extRelationMappingPageReqVO);

    /**
     * SaveReqVO 转换为 DO
     * @param extRelationMappingSaveReqVO 保存请求参数
     * @return ExtRelationMappingDO
     */
     ExtRelationMappingDO convertToDO(ExtRelationMappingSaveReqVO extRelationMappingSaveReqVO);

    /**
     * DO 转换为 RespVO
     * @param extRelationMappingDO 实体对象
     * @return ExtRelationMappingRespVO
     */
     ExtRelationMappingRespVO convertToRespVO(ExtRelationMappingDO extRelationMappingDO);

    /**
     * DOList 转换为 RespVOList
     * @param extRelationMappingDOList 实体对象列表
     * @return List<ExtRelationMappingRespVO>
     */
     List<ExtRelationMappingRespVO> convertToRespVOList(List<ExtRelationMappingDO> extRelationMappingDOList);
}
