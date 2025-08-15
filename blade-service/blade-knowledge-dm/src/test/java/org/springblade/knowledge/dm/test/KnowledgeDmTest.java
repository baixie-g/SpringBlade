import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springblade.core.test.BladeBootTest;
import org.springblade.core.test.BladeSpringExtension;
import org.springblade.knowledge.dm.KnowledgeDmApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 数据管理服务单元测试
 *
 * @author Chill
 */
@ExtendWith(BladeSpringExtension.class)
@SpringBootTest(classes = KnowledgeDmApplication.class)
@BladeBootTest(appName = "blade-knowledge-dm", profile = "test", enableLoader = true)
public class KnowledgeDmTest {

	@Test
	public void contextLoads() {
		System.out.println("数据管理服务测试启动成功");
	}

}
