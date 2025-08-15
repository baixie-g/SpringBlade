import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springblade.core.test.BladeBootTest;
import org.springblade.core.test.BladeSpringExtension;
import org.springblade.knowledge.app.KnowledgeAppApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 知识应用服务单元测试
 *
 * @author Chill
 */
@ExtendWith(BladeSpringExtension.class)
@SpringBootTest(classes = KnowledgeAppApplication.class)
@BladeBootTest(appName = "blade-knowledge-app", profile = "test", enableLoader = true)
public class KnowledgeAppTest {

	@Test
	public void contextLoads() {
		System.out.println("知识应用服务测试启动成功");
	}

}
