package org.springblade.knowledge.dm;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * 数据管理服务启动器
 *
 * @author Chill
 */
@BladeCloudApplication
public class KnowledgeDmApplication {

    public static void main(String[] args) {
        BladeApplication.run("blade-knowledge-dm", KnowledgeDmApplication.class, args);
    }

}
