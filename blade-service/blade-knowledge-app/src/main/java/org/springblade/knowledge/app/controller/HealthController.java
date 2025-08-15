package org.springblade.knowledge.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查控制器
 *
 * @author Chill
 */
@RestController
@RequestMapping("/health")
@Tag(name = "健康检查", description = "健康检查接口")
public class HealthController {

	@GetMapping
	@Operation(summary = "健康检查", description = "健康检查")
	public R<String> health() {
		return R.data("blade-knowledge-app服务运行正常");
	}

}
