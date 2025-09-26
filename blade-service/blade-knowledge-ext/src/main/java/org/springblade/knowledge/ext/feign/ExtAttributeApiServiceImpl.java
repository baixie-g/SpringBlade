package org.springblade.knowledge.ext.feign;

import org.springframework.stereotype.Service;
import org.springblade.knowledge.ext.api.service.IExtAttributeApiService;
import org.springblade.knowledge.ext.dal.dataobject.extSchemaAttribute.ExtSchemaAttributeDO;
import org.springblade.knowledge.ext.service.extSchemaAttribute.IExtSchemaAttributeService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExtAttributeApiServiceImpl implements IExtAttributeApiService {
    @Resource
    private IExtSchemaAttributeService extSchemaAttributeService;

    public List<ExtSchemaAttributeDO> getExtSchemaAttributeList() {
        return extSchemaAttributeService.getExtSchemaAttributeList();
    }
}

