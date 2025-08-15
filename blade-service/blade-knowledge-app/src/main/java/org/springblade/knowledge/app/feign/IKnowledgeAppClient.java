package org.springblade.knowledge.app.feign;

import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * KnowledgeApp Feign接口
 *
 * @author Chill
 */
@FeignClient(value = "blade-knowledge-app")
public interface IKnowledgeAppClient {

	/**
	 * 健康检查
	 */
	@GetMapping("/health")
	R<String> health();

}
