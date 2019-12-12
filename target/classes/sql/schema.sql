drop database if exists shtm;
create database shtm default character set utf8 collate utf8_general_ci;
use shtm;
drop table if exists shtm.sumclassify;
create table shtm.sumclassify(
  sumclassify_id int not null auto_increment comment 'ID',
  sumclassify_name varchar(50) default null comment '分类名',
  sumclassify_clicknum int default 0 comment '点击量',
  sumclassify_logo varchar(255) default null comment '图标',
  sumclassify_money varchar(50) default null comment '多少元起',
  primary key(sumclassify_id)
)engine=InnoDB comment '总分类表';

drop table if exists shtm.secondclassify;
create table shtm.secondclassify(
 secondclassify_id int not null auto_increment comment 'ID',
 sumclassify_id int not null comment '总分类表id',
 secondclassify_name varchar(50) default null comment '分类名',
 primary key(secondclassify_id)
)engine=InnoDB comment '二级分类表';

drop table if exists shtm.goods;
create table shtm.goods(
 goods_id int not null auto_increment comment 'ID',
 secondclassify_id int not null comment '二级分类表ID',
 goods_name varchar(50) default null comment '商品名称',
 goods_price double default 0.0 comment '价格',
 goods_uptime varchar(50) default null comment '上架时间',
 goods_description varchar(255) default null comment '描述',
 goods_paypeople int default 0 comment '多少人付款',
 goods_color varchar(50) default null comment '颜色',
 goods_size varchar(50) default null comment '尺寸',
 goods_num int default 0 comment '数量',
 goods_purchasetime int default 0 comment '购买次数',
 goods_logo varchar(255) default null comment '商品图标',
 primary key(goods_id)
)engine=InnoDB comment '商品详情表';

drop table if exists shtm.collections;
create table shtm.collections(
 collections_id int not null auto_increment comment 'ID',
 goods_id int not null comment '商品ID',
 telephone varchar(20) default null comment '用户名',
 primary key(collections_id)
)engine=InnoDB comment '收藏表';

drop table if exists shtm.comments;
create table shtm.comments(
  comments_id int not null auto_increment comment 'ID',
  goods_id int not null comment '商品ID',
  telephone varchar(20) default null comment '用户名',
  comments_content varchar(255) default null comment '评论内容',
  comments_pictures varchar(255) default null comment '评论图片',
  comments_time varchar(50) default null comment '评论时间',
  primary key(comments_id)
)engine=InnoDB comment '评论表';

drop table if exists shtm.shoppingcarmsg;
create table shtm.shoppingcarmsg(
 shoppingcarmsg_id int not null auto_increment comment 'ID',
 telephone varchar(20) default null comment '用户名',
 shoppingcarmsg_sumnum int default 0 comment '总数量(查询购物车总数量)',
 primary key(shoppingcarmsg_id)
)engine=InnoDB comment '购物车消息表';

drop table if exists shtm.shoppingcar;
create table shtm.shoppingcar(
 shoppingcar_id int not null auto_increment comment 'ID',
 goods_id int not null comment '商品ID',
 shoppingcar_num int default 0 comment '数量',
 shoppingcar_subtotal double default 0.0 comment '小计',
 shoppingcar_sumsubtotal double default 0.0 comment '合计',
 telephone varchar(20) default null comment '用户名',
 primary key(shoppingcar_id)
)engine=InnoDB comment '购物车表';

drop table if exists shtm.singlecenter;
create table shtm.singlecenter(
  singlecenter_id int not null auto_increment comment 'ID',
  singlecenter_image varchar(255) default 'http://192.168.159.1:8080/pictures/touxiang4.png' comment '用户头像',
  telephone varchar(20) default null comment '用户名',
  singlecenter_dfnum int default 0 comment '待发货数',
  singlecenter_dsnum int default 0 comment '待收货数',
  singlecenter_mark int default 0 comment '积分数',
  primary key(singlecenter_id)
)engine=InnoDB comment '个人中心表';

drop table if exists shtm.orders;
create table shtm.orders(
 orders_id int not null auto_increment comment 'ID',
 orders_msg varchar(255) default null comment '订单信息',
 goods_id int not null comment '商品ID(可多个)',
 orders_sumnum int default 0 comment '件数(总商品件数)',
 orders_summoney double default 0 comment '总金额',
 orders_status varchar(50) default null comment '订单状态',
 telephone varchar(20) default null comment '用户名'
 primary key(orders_id)
)engine=InnoDB comment '订单表';

drop table if exists shtm.user;
create table shtm.user(
 user_id int not null auto_increment comment 'ID',
 telephone varchar(50) not null comment '手机号(用户名)',
 password varchar(50) not null comment '密码',
 primary key(user_id)
)engine=InnoDB comment '用户表';

drop table if exists shtm.msg;
create table shtm.msg(
 msg_id int not null auto_increment comment 'ID',
 telephone varchar(20) default null comment '用户名',
 msg_message varchar(20) not null comment '验证码',
 primary key(msg_id)
)engine=InnoDB comment '短信验证码表';