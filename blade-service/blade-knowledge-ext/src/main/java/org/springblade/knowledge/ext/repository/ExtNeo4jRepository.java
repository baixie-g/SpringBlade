package org.springblade.knowledge.ext.repository;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import tech.qiantong.qknow.common.ext.dataobject.extExtraction.ExtExtraction;
import tech.qiantong.qknow.neo4j.repository.BaseRepository;

public interface ExtNeo4jRepository extends BaseRepository<ExtExtraction, Long> {

    /**
     * 根据任务id删除节点及其关系 结构化
     *
     * @param taskId
     * @return
     */
    @Query("MATCH (a:ExtUnStruck {dynamicProperties_task_id: $taskId}) " +
            "DETACH DELETE a")
    void deleteExtUnStruck(@Param("taskId") Long taskId);


    /**
     * 根据任务id删除节点及其关系 非结构化
     *
     * @param taskId
     * @return
     */
    @Query("MATCH (a:ExtStruck {dynamicProperties_task_id: $taskId}) " +
            "DETACH DELETE a")
    void deleteExtStruck(@Param("taskId") Long taskId);
}
