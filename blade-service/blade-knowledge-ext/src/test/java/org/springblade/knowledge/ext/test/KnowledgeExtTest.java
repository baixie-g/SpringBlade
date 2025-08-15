import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springblade.core.test.BladeBootTest;
import org.springblade.core.test.BladeSpringExtension;
import org.springblade.knowledge.ext.KnowledgeExtApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 知识抽取服务单元测试
 *
 * @author Chill
 */
@ExtendWith(BladeSpringExtension.class)
@SpringBootTest(classes = KnowledgeExtApplication.class)
@BladeBootTest(appName = "blade-knowledge-ext", profile = "test", enableLoader = true)
public class KnowledgeExtTest {

	@Test
	public void contextLoads() {
		System.out.println("知识抽取服务测试启动成功");
	}

}
