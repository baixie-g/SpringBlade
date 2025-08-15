package org.springblade.knowledge.dm.feign;

import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * KnowledgeDm Feign接口
 *
 * @author Chill
 */
@FeignClient(value = "blade-knowledge-dm")
public interface IKnowledgeDmClient {

	/**
	 * 健康检查
	 */
	@GetMapping("/health")
	R<String> health();

}
