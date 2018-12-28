# 欢迎使用HyperApi

@(HyperApi)[代码使用|帮助|Markdown]

**HyperApi**是一款基于SpringBoot编写的服务端应用程序，编写该项目主要目的是为今后的工作中能有一套拿来就用的框架，本项目主要用来制作服务端应用提供HttpApi服务功能，代码clone后只需要配置数据库即可运行，开发者只需要用来编写业务代码即可。特点概述：
 
- **数据库支持** ：支持MYSQL数据库，本项目使用MyBatis作为数据库连接工具；
- **最小的代码** ：本项目尽量使用越少的代码，尽可能越少的使用第三方库，代码简单下载后直接编写您的业务代码即可；
- **配套SDK支持** ：支持多种编程语言SDK客户端程序，提供多种语言的SDK客户端程序，可以方便的与该服务端应用进行通信。

-------------------

## HyperApi简介

> HyperApi 是一款服务端应用项目，节约项目搭建时间，配套多种编程语言的客户端可SDK提供。

### HyperApi 源码使用

#####下载代码
``` bash
git clone https://github.com/CaoMengLong/hyper-api.git
```

#####导入数据库
``` bash
hyper_api.sql #导入Mysql数据库
```
#####修改配置文件 application.yml
``` bash
   druid:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://127.0.0.1:3306/hyper_api?useSSL=false&autoReconnect=true&useUnicode=true&characterEncoding=utf8
      username: root
      password: 123456 #1.修改连接数据库名称和账号密码
 
 redis:
    host: 127.0.0.1  #2.修改你的Redis地址
    
hyper-api:
  apikey-filter-enable: false  #3.是否开启API KEY请求校验  false关闭 true打开

```

#####运行程序
``` bash
HyperApiApplication.java  #在IDE中启动项目
```

#####调试项目
``` bash
curl http://127.0.0.1:8080/index/demo -X POST
#返回以下信息则代表成功。
{"status":1,"code":1,"message":"HyperAPI server is running","document":null,"data":null,"created_at":"2018-12-28T07:28:36.836Z","updated_at":"2018-12-28T07:28:36.836Z"}
```

---------
### HyperApi 源码结构及说明
``` bash
com.caosoft.hyper.api
├── server
│   ├── config               #配置文件
│   ├── controller           #业务用控制类
│   ├── filter               #过滤器
│   ├── mapper               #业务用MyBatis的mapper文件
│   ├── po                   #业务用实体类
│   ├── service              #业务用服务层类
│   └── servlet              #Servlet控制类
└── starter                  #公用类包
    ├── po                   #公用实体类
        └── HyperResult.java #接口调用数据返回规则约定
    └── utils                #公用工具类
```

**1. 接口调用数据返回规则约定**
在controller中制作自己的业务类。举例如下：
```Java
@RestController
public class IndexController {
    @RequestMapping(value = "/index/demo",method= RequestMethod.POST)
    public HyperResult demo(){
        HyperResult hyperResult = HyperResult.getInstance();
        hyperResult.setParam(1,1,"HyperAPI server is running",null);
        return hyperResult;  //返回HyperResult标准的数据结构
    }
}
```
 **HyperResult.java** 结构如下
```Java
public class HyperResult {
    Integer status;  //业务执行状态 1.成功 0失败
    Integer code;    //业务具体成功失败状态代码
    String message;  //业务执行返回消息
    String document; //业务执行返回帮助文档URL
    Object data;     //业务执行成功后返回的数据
    String created_at; //创建时间 返回字符串类型时间（ISO8601标准时间）
    String updated_at; //修改时间 返回字符串类型时间（ISO8601标准时间）**粗体文本**
}
```

**2. 开启APIKEY方式的安全保护**
本框架支持2种安全认证方案：APIKEY方式和OAuth2.0方式。
APIKEY 是一种相对简单有效的接口访问保护方案，在本项目中已经开发完成该功能。对于Http接口的APIKEY安全保护方案可以阅读此文章了解其原理：
https://www.jianshu.com/p/b1b7990e1288

如何使用：
1.在数据库表中添加需要访问的应用ID和安全密钥
```sql
hy_api_key #表名称
#执行以下命令
INSERT INTO `hy_api_key` (`id`, `app_name`, `app_key`, `security_key`, `create_time`, `update_time`, `status`) VALUES (NULL, '我的新应用', '100001', 'CY9rzUYh03PK3k6DJie09g9o', '2018-12-28 00:00:00', '2018-12-28 00:00:00', '1');
```

应用名称：我的新应用
app_key：100001
security_key：CY9rzUYh03PK3k6DJie09g9o

2.开启项目的APIKEY方式的安全保护
```
hyper-api:
  apikey-filter-enable: true  #是否开启API KEY请求校验  修改成 true
  apikey-filter-track: true  #是否显示请求校验调试日志
```
打开配置文件 application.yml ，将hyper-api.apikey-filter-enable 开启即可。

3.在客户端代码中配置好请求方式为APIKEY模式，并配置好应用app_key和security_key即可与服务端通信，具体客服端部分的配置，可以查看配套客户端的帮助文档。
 

---------

## 反馈与建议
- 可以直接开issues反馈

---------
感谢阅读这份帮助文档。非专业文档写手，难免有疏忽，尽请谅解。
 