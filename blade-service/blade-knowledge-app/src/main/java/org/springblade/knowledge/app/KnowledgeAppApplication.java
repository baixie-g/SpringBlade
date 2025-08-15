package org.springblade.knowledge.app;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 知识应用服务启动器
 *
 * @author Chill
 */
@BladeCloudApplication
public class KnowledgeAppApplication {

    public static void main(String[] args) {
        BladeApplication.run("blade-knowledge-app", KnowledgeAppApplication.class, args);
    }

}
