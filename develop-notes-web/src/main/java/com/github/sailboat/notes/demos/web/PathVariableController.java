/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.sailboat.notes.demos.web;

import com.github.sailboat.notes.nacos.config.NacosConfigService;
import com.github.sailboat.notes.redis.RedisManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Slf4j
@Controller
public class PathVariableController {

//    @Resource
//    NacosConfigService configService;
//
//    @Resource
//    RedisManager redisManager;
//
//    // http://127.0.0.1:8080/user/123/roles/222
//    @RequestMapping(value = "/user/{userId}/roles/{roleId}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getLogin(@PathVariable("userId") String userId, @PathVariable("roleId") String roleId) {
//        log.info("获取nacos配置Key:gushi, V:{}", configService.getParam());
//        return "User Id : " + userId + " Role Id : " + roleId;
//    }
//
//    // http://127.0.0.1:8080/javabeat/somewords
//    @RequestMapping(value = "/javabeat/{regexp1:[a-z-]+}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getRegExp(@PathVariable("regexp1") String regexp1) {
//        return "URI Part : " + regexp1;
//    }
//
//    @RequestMapping(value = "/redis/{key}", method = RequestMethod.GET)
//    @ResponseBody
//    public String getRedisValue(@PathVariable("key") String key){
//       return redisManager.get(key);
//    }
//
//    @RequestMapping(value = "/redis/{key}/{val}", method = RequestMethod.GET)
//    @ResponseBody
//    public void setRedisValue(@PathVariable("key") String key, @PathVariable("val") String val){
//        redisManager.set(key, val);
//    }
}
