drop database if exists car_rental;
create database car_rental;
use car_rental;
create table `car_rental_user`(
`id` INTEGER PRIMARY key auto_increment comment '唯一ID',
`username` VARCHAR(20) NOT NULL comment '用户名',
`password` VARCHAR(20) NOT NULL comment '密码',
`mobile` BIGINT comment '用户电话号码',
`gender` tinyint(1) NOT NULL comment '性别 0为男 1为女 默认是男',
`age` tinyint(3) comment '年龄，可为空',
`province_id` tinyint(1) comment '省份ID',
`province_name` VARCHAR(20) comment '省份名称',
`city_id` tinyint(1) comment '城市ID',
`city_name` VARCHAR(20) comment '城市名称',
`district_id` tinyint(1) comment '区ID',
`district_name` VARCHAR(20) comment '区名称',
`create_time` BIGINT comment '创建时间',
`modify_time` BIGINT comment '更新时间',
key idx_user_pwd(username,password) comment '常用字段创建索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车租赁系统用户表';