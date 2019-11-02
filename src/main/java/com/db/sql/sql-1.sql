-- AUTHOR:莫谋斌
-- REMARK:测试脚本
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名，登录账户',
  `real_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `pswd` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `user_status` int(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- AUTHOR:莫谋斌
-- REMARK:长短链接映射表
CREATE TABLE `eh_url_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `short_url` varchar(100) DEFAULT NULL COMMENT '短链接',
  `long_url` varchar(100) DEFAULT NULL COMMENT '长链接',
  `url_id` varchar(100) DEFAULT NULL COMMENT 'url唯一id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` datetime DEFAULT NULL COMMENT '链接状态：0：无效，1：生效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
