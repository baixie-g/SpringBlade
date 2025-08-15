package org.springblade.knowledge.kmc.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

/**
 * KnowledgeKmc Feign
 *
 * @author Chill
 */
@Hidden
@RestController
@AllArgsConstructor
public class KnowledgeKmcClient implements IKnowledgeKmcClient {

	@Override
	@GetMapping("/health")
	public R<String> health() {
		return R.data("blade-knowledge-kmc服务运行正常");
	}

}
