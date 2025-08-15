# Blade Knowledge Demo

这是一个基于 SpringBlade 框架的知识管理演示服务，展示了标准的 SpringBlade 项目二次开发流程。

## 功能特性

- ✅ 完整的 CRUD 操作
- ✅ MyBatis Plus 集成
- ✅ Redis 缓存支持
- ✅ 分页查询
- ✅ 数据验证
- ✅ Swagger API 文档
- ✅ 逻辑删除
- ✅ 阅读次数统计
- ✅ 点赞功能
- ✅ 文章状态管理

## 技术栈

- **框架**: SpringBlade 4.6.0
- **数据库**: MySQL 8.0+
- **缓存**: Redis 6.0+
- **ORM**: MyBatis Plus 3.5+
- **文档**: Swagger 3.0 (Knife4j)
- **构建工具**: Maven 3.6+

## 项目结构

```
blade-knowledge-demo/
├── src/main/java/org/springblade/knowledge/demo/
│   ├── KnowledgeDemoApplication.java          # 主启动类
│   ├── controller/                            # 控制器层
│   │   └── KnowledgeArticleController.java    # 知识文章控制器
│   ├── service/                               # 服务层
│   │   ├── IKnowledgeArticleService.java      # 服务接口
│   │   └── impl/                              # 服务实现
│   │       └── KnowledgeArticleServiceImpl.java
│   ├── mapper/                                # 数据访问层
│   │   └── KnowledgeArticleMapper.java        # Mapper接口
│   ├── entity/                                # 实体类
│   │   └── KnowledgeArticle.java              # 知识文章实体
│   └── vo/                                    # 视图对象
│       └── KnowledgeArticleVO.java            # 知识文章VO
├── src/main/resources/
│   ├── application.yml                        # 主配置文件
│   ├── application-dev.yml                    # 开发环境配置
│   ├── mapper/                                # MyBatis映射文件
│   │   └── KnowledgeArticleMapper.xml
│   └── sql/                                   # 数据库脚本
│       └── init.sql                           # 初始化脚本
└── pom.xml                                    # Maven配置
```

## 快速开始

### 1. 环境准备

- JDK 17+
- MySQL 8.0+
- Redis 6.0+
- Maven 3.6+

### 2. 数据库配置

执行 `src/main/resources/sql/init.sql` 脚本创建数据库和表结构。

### 3. 配置文件

修改 `application.yml` 中的数据库和Redis连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/blade_knowledge_demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: your_username
    password: your_password
  
  redis:
    host: localhost
    port: 6379
    password: your_redis_password
```

### 4. 启动服务

```bash
# 编译项目
mvn clean compile

# 运行服务
mvn spring-boot:run
```

### 5. 访问接口

- 服务地址: http://localhost:8100
- API文档: http://localhost:8100/doc.html

## API 接口

### 知识文章管理

| 接口 | 方法 | 描述 |
|------|------|------|
| `/knowledge/article/page` | GET | 分页查询文章 |
| `/knowledge/article/detail` | GET | 查询文章详情 |
| `/knowledge/article/save` | POST | 新增文章 |
| `/knowledge/article/update` | POST | 修改文章 |
| `/knowledge/article/remove` | POST | 删除文章 |
| `/knowledge/article/read` | POST | 增加阅读次数 |
| `/knowledge/article/like` | POST | 点赞文章 |
| `/knowledge/article/publish` | POST | 发布文章 |
| `/knowledge/article/unpublish` | POST | 下架文章 |

## 核心特性说明

### 1. MyBatis Plus 集成

- 使用 `BaseMapper` 提供基础 CRUD 操作
- 支持逻辑删除
- 自动填充创建时间和更新时间
- 自定义分页查询

### 2. Redis 缓存

- 文章详情缓存，提高查询性能
- 缓存更新策略：更新操作后清除缓存
- 缓存过期时间：1小时

### 3. 数据验证

- 使用 `@Valid` 注解进行参数验证
- 实体类字段验证注解
- 统一的异常处理

### 4. 分页查询

- 支持多条件查询
- 自动排序（按排序字段和创建时间）
- 返回标准分页结果

## 开发规范

### 1. 代码结构

- 遵循 SpringBlade 框架的包结构规范
- 使用标准的 MVC 分层架构
- 实体类继承 `TenantEntity` 支持多租户

### 2. 命名规范

- 类名：大驼峰命名法
- 方法名：小驼峰命名法
- 数据库字段：下划线命名法
- 常量：全大写，下划线分隔

### 3. 注释规范

- 类和方法必须有 JavaDoc 注释
- 重要业务逻辑添加行内注释
- 使用 Swagger 注解描述 API

## 扩展建议

### 1. 功能扩展

- 添加文章分类管理
- 实现文章搜索功能
- 添加用户权限控制
- 支持文件上传（图片、附件）

### 2. 性能优化

- 添加数据库连接池配置
- 实现分布式缓存
- 添加接口限流
- 实现异步处理

### 3. 监控运维

- 集成 Spring Boot Actuator
- 添加日志收集
- 实现健康检查
- 添加性能监控

## 常见问题

### 1. 编译错误

确保 Maven 依赖正确，特别是 SpringBlade 相关依赖。

### 2. 数据库连接失败

检查数据库服务是否启动，连接信息是否正确。

### 3. Redis 连接失败

检查 Redis 服务是否启动，连接配置是否正确。

### 4. 端口冲突

修改 `application.yml` 中的 `server.port` 配置。

## 贡献指南

欢迎提交 Issue 和 Pull Request 来改进这个项目。

## 许可证

本项目采用 MIT 许可证。
