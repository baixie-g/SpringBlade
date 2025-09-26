package org.springblade.knowledge.ext.convert.extSchemaMapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.knowledge.ext.admin.extSchemaMapping.vo.ExtSchemaMappingPageReqVO;
import org.springblade.knowledge.ext.admin.extSchemaMapping.vo.ExtSchemaMappingRespVO;
import org.springblade.knowledge.ext.admin.extSchemaMapping.vo.ExtSchemaMappingSaveReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extSchemaMapping.ExtSchemaMappingDO;

import java.util.List;

/**
 * 概念映射 Convert
 *
 * @author qknow
 * @date 2025-02-25
 */
@Mapper
public interface ExtSchemaMappingConvert {
    ExtSchemaMappingConvert INSTANCE = Mappers.getMapper(ExtSchemaMappingConvert.class);

    /**
     * PageReqVO 转换为 DO
     * @param extSchemaMappingPageReqVO 请求参数
     * @return ExtSchemaMappingDO
     */
     ExtSchemaMappingDO convertToDO(ExtSchemaMappingPageReqVO extSchemaMappingPageReqVO);

    /**
     * SaveReqVO 转换为 DO
     * @param extSchemaMappingSaveReqVO 保存请求参数
     * @return ExtSchemaMappingDO
     */
     ExtSchemaMappingDO convertToDO(ExtSchemaMappingSaveReqVO extSchemaMappingSaveReqVO);

    /**
     * DO 转换为 RespVO
     * @param extSchemaMappingDO 实体对象
     * @return ExtSchemaMappingRespVO
     */
     ExtSchemaMappingRespVO convertToRespVO(ExtSchemaMappingDO extSchemaMappingDO);

    /**
     * DOList 转换为 RespVOList
     * @param extSchemaMappingDOList 实体对象列表
     * @return List<ExtSchemaMappingRespVO>
     */
     List<ExtSchemaMappingRespVO> convertToRespVOList(List<ExtSchemaMappingDO> extSchemaMappingDOList);
}
