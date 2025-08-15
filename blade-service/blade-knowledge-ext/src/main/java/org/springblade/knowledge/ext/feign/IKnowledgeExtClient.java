package org.springblade.knowledge.ext.feign;

import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * KnowledgeExt Feign接口
 *
 * @author Chill
 */
@FeignClient(value = "blade-knowledge-ext")
public interface IKnowledgeExtClient {

	/**
	 * 健康检查
	 */
	@GetMapping("/health")
	R<String> health();

}
