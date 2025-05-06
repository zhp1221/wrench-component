package xyz.zhp.wrench.test.dcc;

import org.junit.Test;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import xyz.zhp.wrench.dynamic.config.center.domain.model.valobj.AttributeVO;
import xyz.zhp.wrench.dynamic.config.center.types.annotations.DccValue;
import xyz.zhp.wrench.test.AppTest;
import xyz.zhp.wrench.test.dcc.model.DccVO;

import javax.annotation.Resource;

/**
 * @author zhp
 * @description
 * @since 2025-05-06 14:27
 */
public class DccTest extends AppTest {

    @DccValue("school:小学")
    private String school;

    @Resource
    private RTopic dccRedisTopic;

    @Resource
    private DccVO dccVO;

    @Resource
    private RedissonClient dccWrenchRedisson;

    @Test
    public void test_get(){
        log.info("启动成功，请前往redis查看相关属性设值, school:{}, Rtopic:{}", school, dccRedisTopic.getChannelNames());
        log.info("dccVo, 需添加到bean里，被管理，才能被做增强bean的dcc操作，所以需要添加@Component");
    }

    @Test
    public void test_publish() throws InterruptedException {
        AttributeVO attributeVO = new AttributeVO();
        attributeVO.setAttribute("name");
        attributeVO.setVal("李四");
        dccRedisTopic.publish(attributeVO);
        dccRedisTopic.publish(new AttributeVO("school", "中学"));

        /**
         * 等待{@link DynamicConfigCenterAdjustListener}处理完，否则打印的还是之前的脏值
         */
        Thread.sleep(5000);
        log.info("dccVo, name:{}", dccVO.getName());

    }

}
