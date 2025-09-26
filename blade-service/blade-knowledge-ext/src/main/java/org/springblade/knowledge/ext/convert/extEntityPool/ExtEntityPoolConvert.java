package org.springblade.knowledge.ext.convert.extEntityPool;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springblade.knowledge.ext.admin.extEntityPool.vo.ExtEntityPoolRespVO;
import org.springblade.knowledge.ext.admin.extEntityPool.vo.ExtEntityPoolSaveReqVO;
import org.springblade.knowledge.ext.dal.dataobject.extEntityPool.ExtEntityPoolDO;

import java.util.List;

/**
 * 实体池 Convert
 *
 * @author qknow
 */
@Mapper
public interface ExtEntityPoolConvert {

    ExtEntityPoolConvert INSTANCE = Mappers.getMapper(ExtEntityPoolConvert.class);

    ExtEntityPoolDO convert(ExtEntityPoolSaveReqVO bean);

    ExtEntityPoolRespVO convert(ExtEntityPoolDO bean);

    List<ExtEntityPoolRespVO> convertList(List<ExtEntityPoolDO> list);

    ExtEntityPoolDO convert(ExtEntityPoolRespVO bean);

}
