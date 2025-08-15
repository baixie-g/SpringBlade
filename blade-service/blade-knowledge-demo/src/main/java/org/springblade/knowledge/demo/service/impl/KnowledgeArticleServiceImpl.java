package org.springblade.knowledge.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.knowledge.demo.entity.KnowledgeArticle;
import org.springblade.knowledge.demo.mapper.KnowledgeArticleMapper;
import org.springblade.knowledge.demo.service.IKnowledgeArticleService;
import org.springblade.knowledge.demo.vo.KnowledgeArticleVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 知识文章服务实现类
 *
 * @author AI Assistant
 * @since 2025-08-16
 */
@Slf4j
@Service
@AllArgsConstructor
public class KnowledgeArticleServiceImpl extends ServiceImpl<KnowledgeArticleMapper, KnowledgeArticle> implements IKnowledgeArticleService {

    private final KnowledgeArticleMapper knowledgeArticleMapper;
    private final BladeRedis bladeRedis;

    @Override
    public IPage<KnowledgeArticleVO> selectKnowledgeArticlePage(IPage<KnowledgeArticle> page, KnowledgeArticle article) {
        return knowledgeArticleMapper.selectKnowledgeArticlePage(page, article);
    }

    @Override
    public KnowledgeArticleVO selectKnowledgeArticleById(Long id) {
        // 先从Redis缓存中获取
        String cacheKey = "knowledge:article:" + id;
        KnowledgeArticleVO cachedArticle = bladeRedis.get(cacheKey);
        if (cachedArticle != null) {
            log.info("从Redis缓存获取文章: {}", id);
            return cachedArticle;
        }

        // 缓存中没有，从数据库查询
        KnowledgeArticleVO article = knowledgeArticleMapper.selectKnowledgeArticleById(id);
        if (article != null) {
            // 将结果放入Redis缓存，设置过期时间为1小时
            bladeRedis.setEx(cacheKey, article, 1, TimeUnit.HOURS);
            log.info("文章已缓存到Redis: {}", id);
        }

        return article;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean incrementReadCount(Long id) {
        // 更新数据库中的阅读次数
        boolean success = update().setSql("read_count = read_count + 1").eq("id", id).update();
        
        if (success) {
            // 清除Redis缓存，下次查询时会重新缓存
            String cacheKey = "knowledge:article:" + id;
            bladeRedis.del(cacheKey);
            log.info("文章阅读次数已更新，缓存已清除: {}", id);
        }
        
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean likeArticle(Long id) {
        // 更新数据库中的点赞次数
        boolean success = update().setSql("like_count = like_count + 1").eq("id", id).update();
        
        if (success) {
            // 清除Redis缓存，下次查询时会重新缓存
            String cacheKey = "knowledge:article:" + id;
            bladeRedis.del(cacheKey);
            log.info("文章点赞次数已更新，缓存已清除: {}", id);
        }
        
        return success;
    }
}
