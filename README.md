## 这是一个使用 Spring Cloud Finchley 框架实践微服务的一个example项目，用到的功能有：
- webflux反应式调用
- druid mysql多数据源
- redis or mongodb reactive stream
- Spring Cloud Consul (替换了eureka ) 注册中心
- Spring Cloud Config 配置中心
- Spring Cloud Openfeign RPC调用,
- Spring Cloud Gateway 服务网关
- Spring-cloud-hystrix 熔断降级
- Spring-cloud-Ribbon负载均衡(Feign,hystrix依赖它)
- spring-boot-admin,actuator boot应用指标监控
- Spring Cloud Turbine ,hystrix-dashboar 调用指标
- Spring Cloud Sleuth , Zipkin 调用链
- 等

### 中间件：
中间件 | 端口 | 是否必须
---- | --- | ---
mysql   |    3306   | 是
redis   |    6379   | 否
mongodb |        27017 | 否
rabbitmq |        5672 | 否
rocketmq |        9876 | 否

### 微服务组件及端口：
微服务组件 | 端口 | 是否必须
---- | --- | ---
consul |          8500 | 是
acloud-eureka  |  8501 | 否
acloud-config  |  8502 | 是
acloud-gateway |  8503 | 否
acloud-admin   |  8505 | 否
acloud-turbine |  8506 | 否
zipkin |          9411 | 否

### 普通应用结构建议
- api ：  公共接口和数据模块
- service ：  后台服务
- ui    ： web前台服务（可以不需要，服务统一放在service，前提是动静已经分离）
###### 如：simple 是一个实例应用模块，包括3个子模块：
    acloud(项目)-simple(模块名)-API(公共接口和数据)
    acloud(项目)-simple(模块名)-service(后台服务)
    acloud(项目)-simple(模块名)-ui(前端服务)

#### 中间件安装
只有mysql必须，其它关闭了actuator health的检查

#### consul ：
consul安装并启动：
https://www.consul.io/downloads.html

#### turbine web console ：
- 进入: http://localhost:8506/hystrix/
- 输入框填入：http://localhost:8506/turbine.stream
注意：有feign的调用时（使用hystrix时），hystrix监控界面才会显示，否则一直loading

#### zipkin
因为在springboot2.0以后，官方不再建议自己搭建zipkin的服务端，而是提供现成的jar包，直接运行即可

##### 问题和注意：
springfox-swagger2  目前不支持reactive
RocketMQ  脚手架暂不支持SpringBoot2.0