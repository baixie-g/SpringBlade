import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springblade.core.test.BladeBootTest;
import org.springblade.core.test.BladeSpringExtension;
import org.springblade.knowledge.kmc.KnowledgeKmcApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 知识管理服务单元测试
 *
 * @author Chill
 */
@ExtendWith(BladeSpringExtension.class)
@SpringBootTest(classes = KnowledgeKmcApplication.class)
@BladeBootTest(appName = "blade-knowledge-kmc", profile = "test", enableLoader = true)
public class KnowledgeKmcTest {

	@Test
	public void contextLoads() {
		System.out.println("知识管理服务测试启动成功");
	}

}
