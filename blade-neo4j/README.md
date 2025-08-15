# SpringBlade Neo4j 模块

## 概述

`blade-neo4j` 是 SpringBlade 生态系统的图数据库支持模块，基于 Neo4j 提供强大的图数据操作能力。该模块完全集成到 SpringBlade 生态中，支持微服务架构和云原生部署。

## 特性

- 🚀 **完全集成 SpringBlade 生态**
- 🔧 **灵活的查询构建器** - 支持链式查询API
- 📊 **动态实体支持** - 支持动态标签和属性
- 🔗 **关系管理** - 完整的关系CRUD操作
- 📄 **分页查询** - 内置分页支持
- 🏷️ **多标签支持** - 支持节点多标签
- 🎯 **Lambda表达式** - 类型安全的属性访问

## 快速开始

### 1. 添加依赖

```xml
<dependency>
    <groupId>org.springblade</groupId>
    <artifactId>blade-neo4j</artifactId>
    <version>${revision}</version>
</dependency>
```

### 2. 配置 Neo4j

在 `application.yml` 中添加配置：

```yaml
spring:
  neo4j:
    enabled: true
    uri: bolt://localhost:7687
    authentication:
      username: neo4j
      password: password
    connection-timeout: 30s
    max-connection-pool-size: 100
```

### 3. 使用示例

#### 创建实体

```java
@Service
public class KnowledgeService {
    
    public DynamicEntity createEntity(String name, String type) {
        DynamicEntity entity = new DynamicEntity();
        entity.setName(name);
        entity.setType(type);
        entity.setId(UUID.randomUUID().toString());
        entity.addLabels("KnowledgeEntity", type);
        return entity;
    }
}
```

#### 查询实体

```java
public List<DynamicEntity> findEntities(String type) {
    Neo4jQueryWrapper<DynamicEntity> wrapper = new Neo4jQueryWrapper<>(DynamicEntity.class);
    wrapper.addLabels(type);
    wrapper.like("name", "知识");
    return wrapper.find(wrapper);
}
```

#### 分页查询

```java
public PageResult<DynamicEntity> findEntitiesPage(String type, int page, int size) {
    Neo4jQueryWrapper<DynamicEntity> wrapper = new Neo4jQueryWrapper<>(DynamicEntity.class);
    wrapper.addLabels(type);
    wrapper.page(page, size);
    return wrapper.findPage(wrapper);
}
```

#### 创建关系

```java
public void createRelationship(String sourceId, String targetId, String relationType) {
    Neo4jBuildWrapper<DynamicEntity> wrapper = new Neo4jBuildWrapper<>(DynamicEntity.class);
    
    Map<String, Object> sourceMap = Map.of("id", sourceId);
    Map<String, Object> targetMap = Map.of("id", targetId);
    
    wrapper.createRelationship("KnowledgeEntity", sourceMap, targetMap, relationType, Map.of());
}
```

## 核心组件

### 1. BaseRepository

基础仓库接口，提供所有 Neo4j 操作的基础方法：

- `find()` - 条件查询
- `findPage()` - 分页查询
- `relationChain()` - 关系链查询
- `mergeCreateNode()` - 合并创建节点
- `mergeRelationship()` - 合并创建关系

### 2. Neo4jQueryWrapper

查询构建器，支持链式API：

```java
Neo4jQueryWrapper<DynamicEntity> wrapper = new Neo4jQueryWrapper<>(DynamicEntity.class);
wrapper.addLabels("Person")
       .eq("age", 25)
       .like("name", "张")
       .page(1, 10);
```

### 3. Neo4jBuildWrapper

构建操作包装器，用于创建和更新操作：

```java
Neo4jBuildWrapper<DynamicEntity> wrapper = new Neo4jBuildWrapper<>(DynamicEntity.class);
wrapper.mergeCreateNode("Person", mergeMap, attributeMap);
```

### 4. DynamicEntity

动态实体类，支持：

- 动态标签
- 动态属性
- 关系管理
- 灵活的类型系统

## 配置说明

### 环境变量支持

```yaml
spring:
  neo4j:
    uri: ${NEO4J_URI:bolt://localhost:7687}
    authentication:
      username: ${NEO4J_USERNAME:neo4j}
      password: ${NEO4J_PASSWORD:password}
```

### 条件配置

```yaml
spring:
  neo4j:
    enabled: true  # 启用/禁用Neo4j支持
```

## 最佳实践

### 1. 标签设计

- 使用有意义的标签名称
- 避免过多标签
- 考虑标签的层次结构

### 2. 属性设计

- 使用有意义的属性名
- 避免过深的嵌套结构
- 考虑索引优化

### 3. 关系设计

- 使用动词作为关系类型
- 关系属性要简洁
- 考虑关系的方向性

## 故障排除

### 常见问题

1. **连接失败**
   - 检查 Neo4j 服务状态
   - 验证连接参数
   - 检查网络连通性

2. **查询性能问题**
   - 添加适当的索引
   - 优化 Cypher 查询
   - 使用分页查询

3. **内存问题**
   - 调整连接池大小
   - 优化查询结果集大小
   - 使用流式查询

## 版本兼容性

- Spring Boot: 2.7.x, 3.x
- Spring Cloud: 2021.x, 2022.x
- Neo4j: 4.x, 5.x
- Java: 8, 11, 17, 21

## 贡献

欢迎提交 Issue 和 Pull Request！

## 许可证

遵循 SpringBlade 项目的许可证。
