package org.springblade.knowledge.ext;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 知识抽取服务启动器
 *
 * @author Chill
 */
@BladeCloudApplication
public class KnowledgeExtApplication {

    public static void main(String[] args) {
        BladeApplication.run("blade-knowledge-ext", KnowledgeExtApplication.class, args);
    }

}
