/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : vueblog

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 02/06/2021 13:34:54
*/
create database `vueblog` default character set utf8 collate utf8_general_ci;
use vueblog;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for m_blog
-- ----------------------------
DROP TABLE IF EXISTS `m_blog`;
CREATE TABLE `m_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL COMMENT '发布人ID',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `description` varchar(255) NOT NULL COMMENT '文章描述',
  `content` longtext COMMENT '文章内容',
  `created` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '文章发布时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '文章状态',
  `vote_count` int(10) unsigned DEFAULT '0' COMMENT '点赞数量',
  `comment_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论数量',
  `read_count` int(10) NOT NULL DEFAULT '0' COMMENT '阅读数量',
  PRIMARY KEY (`id`),
  KEY `用户外键` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for m_comment
-- ----------------------------
DROP TABLE IF EXISTS `m_comment`;
CREATE TABLE `m_comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论主键\n',
  `title` varchar(63) DEFAULT NULL COMMENT '评论主题',
  `body` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `commentable_id` bigint(20) DEFAULT NULL COMMENT '评论文章的ID',
  `parent_id` bigint(20) unsigned DEFAULT '0' COMMENT '记录被回复人的ID(话题发起最初人的ID)否则为0',
  `vote_count` bigint(20) unsigned DEFAULT '0' COMMENT '点赞该评论的',
  `comment_count` bigint(20) unsigned DEFAULT '0' COMMENT '回复该评论的数量',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '评论人的ID',
  `commented_user_id` bigint(20) unsigned DEFAULT '0' COMMENT '当回复他人的时候此处记录被回复人的ID',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评论创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for m_file
-- ----------------------------
DROP TABLE IF EXISTS `m_file`;
CREATE TABLE `m_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `status` smallint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for m_theme
-- ----------------------------
DROP TABLE IF EXISTS `m_theme`;
CREATE TABLE `m_theme` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '样式主id',
  `name` varchar(255) NOT NULL COMMENT '样式名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `email` varchar(64) DEFAULT NULL COMMENT '用户账号',
  `password` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `status` int(5) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `created` datetime DEFAULT NULL COMMENT '用户创建日期',
  `last_login` datetime DEFAULT NULL COMMENT '用户最近登录日期',
  `depiction` varchar(255) DEFAULT NULL COMMENT '用户个人简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for m_vote
-- ----------------------------
DROP TABLE IF EXISTS `m_vote`;
CREATE TABLE `m_vote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `voteable_id` int(11) NOT NULL COMMENT '点赞的文章ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_theme
-- ----------------------------
DROP TABLE IF EXISTS `user_theme`;
CREATE TABLE `user_theme` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主id',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_theme` (`user_id`),
  CONSTRAINT `user_theme` FOREIGN KEY (`user_id`) REFERENCES `m_theme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
