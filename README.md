# 图床程序-jsp&servlet
![license](https://img.shields.io/aur/license/yaourt.svg)  ![release](https://img.shields.io/badge/release-v0.1.2-brightgreen.svg)

## Describe

这是使用jsp和servlet编写的图床程序，目前0.1以上版本基本完成功能，并使用AJAX提交，基本满足正常使用需求。

### NEEDS

- mysql-connector-java
- commons-lang3

## Install

- 修改WEB-INF下的配置文件,和修改src下Upload.java和ConfigLoader.java並重新編譯
- 将src目录下的java文件全部编译放入WEB-INF/classes文件夹内
- 将commons-lang3和JDBC的包放入tomcat的lib目录
- 启动tomcat
- 創建數據庫images,創建表
CREATE TABLE `files`( `id` int(11) NOT NULL AUTO_INCREMENT, `filename` char(21) NOT NULL, `ip` char(15) NOT NULL, `md5` char(32) NOT NULL, `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY(`id`));

## Change log

### v0.1.2(16.10.20)

- 修复部分BUG

### v0.1.1(16.10.6)

- 修改JSON传输方式

### v0.1.0(16.10.2)

- 修改为AJAX提交

### v0.0.8(16.10.2)

- 增加對上傳的重複文件處理
- 增加配置文件

### v0.0.5(16.10.2)

- 增加对上传文件的md5检测，判断是否为重复文件
- 增加数据库支持,酱文件记录进mysql
- 增加程序的配置文件

### V0.0.1(16.10.1)

- 基本上传功能
- 对当天的文件放置于（年月日）文件夹内，如2016101