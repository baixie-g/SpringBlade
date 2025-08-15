package org.springblade.knowledge.ext.api.service;


import org.springblade.knowledge.ext.dal.dataobject.extSchemaAttribute.ExtSchemaAttributeDO;

import java.util.List;

public interface IExtAttributeApiService {
    public List<ExtSchemaAttributeDO> getExtSchemaAttributeList();
}
