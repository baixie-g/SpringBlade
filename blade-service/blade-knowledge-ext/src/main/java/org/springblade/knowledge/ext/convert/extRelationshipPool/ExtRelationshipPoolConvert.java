package org.springblade.knowledge.ext.convert.extRelationshipPool;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.qiantong.qknow.module.ext.controller.admin.extRelationshipPool.vo.ExtRelationshipPoolRespVO;
import tech.qiantong.qknow.module.ext.controller.admin.extRelationshipPool.vo.ExtRelationshipPoolSaveReqVO;
import tech.qiantong.qknow.module.ext.dal.dataobject.extRelationshipPool.ExtRelationshipPoolDO;

import java.util.List;

/**
 * 关系池 Convert
 *
 * @author qknow
 */
@Mapper
public interface ExtRelationshipPoolConvert {

    ExtRelationshipPoolConvert INSTANCE = Mappers.getMapper(ExtRelationshipPoolConvert.class);

    ExtRelationshipPoolDO convert(ExtRelationshipPoolSaveReqVO bean);

    ExtRelationshipPoolRespVO convert(ExtRelationshipPoolDO bean);

    List<ExtRelationshipPoolRespVO> convertList(List<ExtRelationshipPoolDO> list);

    ExtRelationshipPoolDO convert(ExtRelationshipPoolRespVO bean);

}
