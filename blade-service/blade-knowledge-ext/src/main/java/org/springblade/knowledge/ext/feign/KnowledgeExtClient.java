package org.springblade.knowledge.ext.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

/**
 * KnowledgeExt Feign
 *
 * @author Chill
 */
@Hidden
@RestController
@AllArgsConstructor
public class KnowledgeExtClient implements IKnowledgeExtClient {

	@Override
	@GetMapping("/health")
	public R<String> health() {
		return R.data("blade-knowledge-ext服务运行正常");
	}

}
