package org.springblade.knowledge.dm.feign;

import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Hidden;

/**
 * KnowledgeDm Feign
 *
 * @author Chill
 */
@Hidden
@RestController
@AllArgsConstructor
public class KnowledgeDmClient implements IKnowledgeDmClient {

	@Override
	@GetMapping("/health")
	public R<String> health() {
		return R.data("blade-knowledge-dm服务运行正常");
	}

}
