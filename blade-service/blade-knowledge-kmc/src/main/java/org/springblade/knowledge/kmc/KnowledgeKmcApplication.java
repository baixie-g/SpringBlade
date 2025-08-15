package org.springblade.knowledge.kmc;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 知识管理服务启动器
 *
 * @author Chill
 */
@BladeCloudApplication
public class KnowledgeKmcApplication {

    public static void main(String[] args) {
        BladeApplication.run("blade-knowledge-kmc", KnowledgeKmcApplication.class, args);
    }

}
