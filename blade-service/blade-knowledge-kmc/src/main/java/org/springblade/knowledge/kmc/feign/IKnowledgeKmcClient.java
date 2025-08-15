package org.springblade.knowledge.kmc.feign;

import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * KnowledgeKmc Feign接口
 *
 * @author Chill
 */
@FeignClient(value = "blade-knowledge-kmc")
public interface IKnowledgeKmcClient {

	/**
	 * 健康检查
	 */
	@GetMapping("/health")
	R<String> health();

}
