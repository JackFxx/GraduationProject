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

create table `car_rental_category`(
`id` BIGINT PRIMARY key auto_increment comment '唯一ID',
`category_id` BIGINT  comment '类目ID',
`category_name` VARCHAR(20) NOT NULL comment '名称',
`detail_des` VARCHAR(30) comment '描述',
`type` tinyint(2) NOT NULL  comment '类别',
`real_price` DECIMAL NOT NULL  comment '实时价格',
`line_price` DECIMAL NOT NULL  comment '原价格',
`source_user` VARCHAR(20) not NULL  comment '来源人',
`view_num` INTEGER DEFAULT 0 comment '浏览数',
`like_num` INTEGER DEFAULT 0 comment '点赞数',
`hate_num` INTEGER DEFAULT 0 comment '点踩数',
`main_img_url` VARCHAR(500) NOt NULL  comment '主图地址',
`img_url` VARCHAR(500) not null  comment '次图片地址',
`province_id` tinyint(1) comment '省份ID',
`province_name` VARCHAR(20) comment '省份名称',
`city_id` tinyint(1) comment '城市ID',
`city_name` VARCHAR(20) comment '城市名称',
`district_id` tinyint(1) comment '区ID',
`district_name` VARCHAR(20) comment '区名称',
`create_time` BIGINT comment '创建时间',
`modify_time` BIGINT comment '更新时间',
`obtain_time` BIGINT comment '下架时间',
 key idx_category_id(category_id),
 key idx_category_name(category_name),
 key idx_type(type),
 key idx_creat_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车租赁系统商品类目';

create table `car_rental_comment`(
`id`  BIGINT PRIMARY key auto_increment comment '唯一ID',
`category_id` BIGINT not null comment '类目ID',
`comment_user` VARCHAR(1000) comment '评论人',
`comment_content` VARCHAR(10000) comment '评论内容',
`create_time` BIGINT comment '第一次评论时间',
`modify_time` BIGINT comment '评论更新时间',
key idx_category(category_id) comment '类目ID索引'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目评论表';

create table `car_rental_order`(
`id`  BIGINT PRIMARY key auto_increment comment '唯一ID',
`order_id` BIGINT not null comment '订单ID',
`category_id` BIGINT not null comment '类目ID',
`user_id` BIGINT not null comment '购买者ID',
`user_name` VARCHAR(20) comment '用户姓名',
`mobile` BIGINT comment '用户电话号码',
`num` INTEGER not null comment '商品数量',
`total_price` DECIMAL not null comment '总价格',
`order_status` TINYINT(1) DEFAULT 0 comment '订单状态 0待支付 1已支付 2主动取消 3自动取消',
`receive_addr` VARCHAR(30) comment '详细收货地址',
`create_time` BIGINT not null comment '下单时间',
`modify_time` BIGINT DEFAULT null comment '更新时间',
`pay_time` BIGINT  DEFAULT null comment '支付时间',
`cancel_time` BIGINT  DEFAULT null comment '取消时间',
key idx_order_id(order_id),
key idx_category_id(category_id),
key idx_user_id(user_id),
key idx_order_status(order_status)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';