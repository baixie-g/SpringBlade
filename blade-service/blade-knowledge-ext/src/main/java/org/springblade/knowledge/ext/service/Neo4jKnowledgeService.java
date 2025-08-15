package org.springblade.knowledge.ext.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springblade.neo4j.domain.DynamicEntity;
import org.springblade.neo4j.repository.BaseRepository;
import org.springblade.neo4j.wrapper.Neo4jBuildWrapper;
import org.springblade.neo4j.wrapper.Neo4jQueryWrapper;
import org.springblade.neo4j.core.page.PageResult;

import java.util.*;

/**
 * Neo4j知识图谱服务
 * 展示如何使用blade-neo4j模块进行知识图谱操作
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class Neo4jKnowledgeService {

    /**
     * 创建知识实体
     */
    public DynamicEntity createKnowledgeEntity(String name, String type, Map<String, Object> properties) {
        DynamicEntity entity = new DynamicEntity();
        entity.setName(name);
        entity.setType(type);
        entity.setId(UUID.randomUUID().toString());
        
        // 添加动态属性
        if (properties != null) {
            properties.forEach(entity::putDynamicProperties);
        }
        
        // 添加标签
        entity.addLabels("KnowledgeEntity");
        entity.addLabels(type);
        
        log.info("Created knowledge entity: {} of type: {}", name, type);
        return entity;
    }

    /**
     * 创建实体关系
     */
    public void createEntityRelationship(String sourceEntityId, String targetEntityId, 
                                       String relationshipType, Map<String, Object> relationshipProps) {
        Neo4jBuildWrapper<DynamicEntity> wrapper = new Neo4jBuildWrapper<>(DynamicEntity.class);
        
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("id", sourceEntityId);
        
        Map<String, Object> targetMap = new HashMap<>();
        targetMap.put("id", targetEntityId);
        
        wrapper.createRelationship("KnowledgeEntity", sourceMap, targetMap, relationshipType, relationshipProps);
        log.info("Created relationship: {} -> {} -> {}", sourceEntityId, relationshipType, targetEntityId);
    }

    /**
     * 查询知识实体
     */
    public List<DynamicEntity> findKnowledgeEntities(String type, String namePattern) {
        Neo4jQueryWrapper<DynamicEntity> wrapper = new Neo4jQueryWrapper<>(DynamicEntity.class);
        
        if (type != null) {
            wrapper.addLabels(type);
        }
        
        if (namePattern != null && !namePattern.trim().isEmpty()) {
            wrapper.like("name", namePattern);
        }
        
        return wrapper.find(wrapper);
    }

    /**
     * 分页查询知识实体
     */
    public PageResult<DynamicEntity> findKnowledgeEntitiesPage(String type, String namePattern, 
                                                              int page, int size) {
        Neo4jQueryWrapper<DynamicEntity> wrapper = new Neo4jQueryWrapper<>(DynamicEntity.class);
        
        if (type != null) {
            wrapper.addLabels(type);
        }
        
        if (namePattern != null && !namePattern.trim().isEmpty()) {
            wrapper.like("name", namePattern);
        }
        
        wrapper.page(page, size);
        return wrapper.findPage(wrapper);
    }

    /**
     * 查询实体关系链
     */
    public List<DynamicEntity> findEntityRelationshipChain(String entityId, int depth) {
        Neo4jQueryWrapper<DynamicEntity> wrapper = new Neo4jQueryWrapper<>(DynamicEntity.class);
        wrapper.eq("id", entityId);
        
        // 使用关系链查询
        return wrapper.relationChain(wrapper);
    }

    /**
     * 批量创建知识实体
     */
    public List<DynamicEntity> batchCreateKnowledgeEntities(List<Map<String, Object>> entityDataList) {
        List<DynamicEntity> entities = new ArrayList<>();
        
        for (Map<String, Object> entityData : entityDataList) {
            String name = (String) entityData.get("name");
            String type = (String) entityData.get("type");
            
            @SuppressWarnings("unchecked")
            Map<String, Object> properties = (Map<String, Object>) entityData.get("properties");
            
            DynamicEntity entity = createKnowledgeEntity(name, type, properties);
            entities.add(entity);
        }
        
        log.info("Batch created {} knowledge entities", entities.size());
        return entities;
    }

    /**
     * 删除知识实体
     */
    public void deleteKnowledgeEntity(String entityId) {
        // 这里需要实现具体的删除逻辑
        log.info("Deleting knowledge entity: {}", entityId);
    }

    /**
     * 更新知识实体属性
     */
    public void updateKnowledgeEntityProperties(String entityId, Map<String, Object> newProperties) {
        Neo4jBuildWrapper<DynamicEntity> wrapper = new Neo4jBuildWrapper<>(DynamicEntity.class);
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", entityId);
        
        wrapper.updateQuery("KnowledgeEntity", paramMap, newProperties);
        log.info("Updated properties for entity: {}", entityId);
    }
}
