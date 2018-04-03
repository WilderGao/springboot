package com.qg.springboot.limit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author:Wilder Gao
 * @time:2018/3/6
 * @Discription：注解的具体实现方法
 * 这个注解定义在方法之上的，而且方法中要有request参数，根据request获取到Ip来作为唯一的键
 */
@Aspect
@Component
public class RequestLimitContract {
    private static final Logger logger = LoggerFactory.getLogger("requestLimitLogger");
    private Map<String , Integer> redisTemplate = new HashMap<>();

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint , RequestLimit limit)throws RequestLimitException{
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0 ; i < args.length ; i++){
                if (args[i] instanceof HttpServletRequest){
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null){
                throw new RequestLimitException("方法中缺失HttpServletRequest参数");
            }
            //获得请求的IP地址和url
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            final String key = "req_limit".concat(url).concat(ip);
            if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0){
                redisTemplate.put(key , 1);
            }else {
                redisTemplate.put(key , redisTemplate.get(key)+1);
            }
            //获取单位时间内已经访问的次数
            int count = redisTemplate.get(key);

            if (count > 0){
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        redisTemplate.remove(key);
                    }
                };
                //这个定时器在limit.time()时间之后会开始执行
                timer.schedule(timerTask , limit.time());
            }

            if (count > limit.count()){
                logger.error("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        }catch (RequestLimitException e){
            e.printStackTrace();
        }
    }
}
