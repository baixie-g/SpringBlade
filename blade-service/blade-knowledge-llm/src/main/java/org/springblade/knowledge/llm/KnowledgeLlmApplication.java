package org.springblade.knowledge.llm;

import org.springblade.core.cloud.client.BladeCloudApplication;
import org.springblade.core.launch.BladeApplication;

/**
 * LLM智能服务启动器
 *
 * @author Chill
 */
@BladeCloudApplication
public class KnowledgeLlmApplication {

    public static void main(String[] args) {
        BladeApplication.run("blade-knowledge-llm", KnowledgeLlmApplication.class, args);
    }

}
