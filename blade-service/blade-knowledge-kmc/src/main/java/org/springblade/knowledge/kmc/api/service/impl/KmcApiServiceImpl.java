package org.springblade.knowledge.kmc.api.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springblade.knowledge.kmc.api.service.IKmcApiService;
import org.springblade.knowledge.kmc.dal.dataobject.document.KmcDocumentDO;
import org.springblade.knowledge.kmc.dal.dataobject.kmcCategory.KmcCategoryDO;
import org.springblade.knowledge.kmc.domain.TreeSelects;
import org.springblade.knowledge.kmc.service.kmcCategory.IKmcCategoryService;
import org.springblade.knowledge.kmc.service.kmcDocument.IKmcDocumentService;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KmcApiServiceImpl implements IKmcApiService {

    @Resource
    public IKmcDocumentService kmcDocumentService;
    @Resource
    public IKmcCategoryService kmcCategoryService;

    @Override
    public List<Object> getKmcDocumentList(){
        List<KmcDocumentDO> documentList = kmcDocumentService.getKmcDocumentList();
        return documentList.stream().map(doc -> (Object) doc).collect(Collectors.toList());
    }

    @Override
    public List<Object> getKmcDocumentListByIds(List<Long> ids){
        if (ids.isEmpty()) {
            return Lists.newArrayList();
        }
        List<KmcDocumentDO> documentList = kmcDocumentService.getKmcDocumentListByIds(ids);
        return documentList.stream().map(doc -> (Object) doc).collect(Collectors.toList());
    }

    @Override
    public List<Object> getCategoryTreeList(Object kmcCategoryDO) {
        if (kmcCategoryDO instanceof KmcCategoryDO) {
            List<TreeSelects> treeList = kmcCategoryService.selectCategoryTreeList((KmcCategoryDO) kmcCategoryDO);
            return treeList.stream().map(tree -> (Object) tree).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
