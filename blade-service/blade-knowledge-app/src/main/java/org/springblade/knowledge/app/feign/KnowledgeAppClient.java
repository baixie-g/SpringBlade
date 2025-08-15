package org.springblade.knowledge.app.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

/**
 * KnowledgeApp Feign
 *
 * @author Chill
 */
@Hidden
@RestController
@AllArgsConstructor
public class KnowledgeAppClient implements IKnowledgeAppClient {

	@Override
	@GetMapping("/health")
	public R<String> health() {
		return R.data("blade-knowledge-app服务运行正常");
	}

}
