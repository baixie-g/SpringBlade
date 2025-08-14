package org.springblade.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springblade.core.tool.api.R;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限接口
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Tag(name = "权限接口", description = "权限相关接口")
public class PermissionController {

	private IUserClient userClient;

	/**
	 * 获取用户权限列表
	 *
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	@GetMapping("/permissions")
	@Operation(summary = "获取用户权限", description = "根据用户ID获取权限列表")
	public R<Set<String>> getUserPermissions(@RequestParam Long userId) {
		try {
			// 调用用户服务获取用户信息
			R<UserInfo> result = userClient.userInfo(userId);
			if (result.isSuccess() && result.getData() != null) {
				UserInfo userInfo = result.getData();
				// 返回用户权限集合
				return R.data(new HashSet<>(userInfo.getPermissions()));

			}
			return R.fail("用户信息获取失败");
		} catch (Exception e) {
			return R.fail("获取用户权限失败: " + e.getMessage());
		}
	}
}
