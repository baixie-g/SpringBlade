-- 创建数据库
CREATE DATABASE IF NOT EXISTS `blade_knowledge_demo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `blade_knowledge_demo`;

-- 创建知识文章表
CREATE TABLE IF NOT EXISTS `knowledge_article` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  `summary` varchar(500) DEFAULT NULL COMMENT '文章摘要',
  `category` varchar(100) DEFAULT NULL COMMENT '文章分类',
  `tags` varchar(255) DEFAULT NULL COMMENT '标签（逗号分隔）',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `read_count` int(11) DEFAULT '0' COMMENT '阅读次数',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（0-草稿，1-发布，2-下架）',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_status` (`status`),
  KEY `idx_author` (`author`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='知识文章表';

-- 插入示例数据
INSERT INTO `knowledge_article` (`id`, `title`, `content`, `summary`, `category`, `tags`, `author`, `read_count`, `like_count`, `status`, `sort`, `create_by`) VALUES
(1, 'SpringBlade 框架介绍', 'SpringBlade 是一个基于 Spring Boot 2.7+ & Spring Cloud 2021+ 的微服务架构系统。它提供了完整的微服务解决方案，包括服务注册发现、配置中心、网关、熔断器等核心组件。', 'SpringBlade 是一个功能完整的微服务架构系统，本文详细介绍其架构特点和使用方法。', '技术文档', 'SpringBoot,微服务,架构', 'AI Assistant', 0, 0, 1, 1, 'admin'),
(2, 'MyBatis Plus 使用指南', 'MyBatis Plus 是 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。', '详细介绍 MyBatis Plus 的使用方法，包括 CRUD 操作、分页查询、条件构造器等。', '技术文档', 'MyBatis,数据库,ORM', 'AI Assistant', 0, 0, 1, 2, 'admin'),
(3, 'Redis 缓存策略', 'Redis 是一个开源的内存数据结构存储系统，可以用作数据库、缓存和消息中间件。本文介绍 Redis 的缓存策略和最佳实践。', '深入探讨 Redis 缓存策略，包括缓存更新、缓存穿透、缓存雪崩等问题的解决方案。', '技术文档', 'Redis,缓存,性能优化', 'AI Assistant', 0, 0, 1, 3, 'admin'),
(4, '微服务架构设计原则', '微服务架构是一种将单体应用程序分解为多个小型、松耦合服务的架构风格。本文介绍微服务架构的设计原则和最佳实践。', '探讨微服务架构的核心设计原则，包括服务拆分、服务治理、数据一致性等关键问题。', '架构设计', '微服务,架构设计,分布式', 'AI Assistant', 0, 0, 0, 4, 'admin'),
(5, 'Spring Boot 自动配置原理', 'Spring Boot 的自动配置是其核心特性之一，它能够根据 classpath 中的依赖自动配置 Spring 应用。', '深入分析 Spring Boot 自动配置的实现原理，包括条件注解、配置类加载等机制。', '技术文档', 'SpringBoot,自动配置,原理', 'AI Assistant', 0, 0, 1, 5, 'admin');
