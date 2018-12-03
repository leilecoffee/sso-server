CREATE DATABASE sso DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'sso'@'%' IDENTIFIED BY 'sso';

GRANT SELECT,INSERT,DELETE,UPDATE,CREATE,DROP,ALTER ON sso.* TO 'sso'@'%';

FLUSH PRIVILEGES;

--mysql 8.0 修改密码sql
use mysql;

--查看验证方式 
select user,host,plugin,authentication_string from user;
--修改验证方式
alter user 'sso'@'%' identified with mysql_native_password by 'sso';
FLUSH PRIVILEGES;

use sso;
--执行sql文件
set names utf8;
source ../sso.sql