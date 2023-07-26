package com.lft.smart_bi.manager;

import com.lft.smart_bi.common.ErrorCode;
import com.lft.smart_bi.exception.ThrowUtils;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Pakage com.lft.smart_bi.manager
 * @auther 邮专第一深情
 * @date 2023/07/16 15:13
 */
@Service
public class RedisLimiterManager {

    @Resource
    private RedissonClient redissonClient;

    public void doRateLimiter(String key) {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL, 2, 1, RateIntervalUnit.SECONDS);
        boolean canOp = rateLimiter.tryAcquire(1);
        ThrowUtils.throwIf(!canOp, ErrorCode.TOO_MANY_REQUEST);
    }


}
