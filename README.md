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

####下载代码
``` bash
git clone https://github.com/CaoMengLong/hyper-api.git
```

####导入数据库
``` bash
hyper_api.sql #导入Mysql数据库
```
####修改配置文件 application.yml
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

####运行程序
``` bash
HyperApiApplication.java  #在IDE中启动项目
```

####调试项目
``` bash
curl http://127.0.0.1:8080/index/demo -X POST
#返回以下信息则代表成功。
{"status":1,"code":1,"message":"HyperAPI server is running","document":null,"data":null,"created_at":"2018-12-28T07:28:36.836Z","updated_at":"2018-12-28T07:28:36.836Z"}
```


### HyperApi 源码结构及说明

TODO

---------

 


## 反馈与建议
- 可以直接开issues反馈

---------
感谢阅读这份帮助文档。非专业文档写手，难免有疏忽，尽请谅解。
 