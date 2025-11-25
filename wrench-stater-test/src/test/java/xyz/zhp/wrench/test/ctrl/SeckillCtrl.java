package xyz.zhp.wrench.test.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhp.wrench.test.design.tree.hub.factory.SeckillFactory;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillReq;
import xyz.zhp.wrench.test.design.tree.hub.model.SeckillResp;

/**
 *
 *
 * @author zhp
 * @since 2025-11-25 18:05
 */
@RestController
@RequestMapping("/seckill")
public class SeckillCtrl {

    @Autowired
    private SeckillFactory seckillFactory;

    @PostMapping("/rule-hub-engine")
    public SeckillResp pre(@RequestBody SeckillReq secKillReq) {
        return seckillFactory.start(secKillReq);
    }


}
