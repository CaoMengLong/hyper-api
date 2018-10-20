-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主机： db
-- 生成日期： 2018-10-20 07:25:22
-- 服务器版本： 5.7.23
-- PHP 版本： 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `hyper_api`
--

-- --------------------------------------------------------

--
-- 表的结构 `hy_api_key`
--

CREATE TABLE `hy_api_key` (
  `id` int(11) NOT NULL,
  `app_name` varchar(200) NOT NULL COMMENT '应用的名称',
  `app_key` varchar(200) NOT NULL COMMENT '应用的KEY',
  `security_key` varchar(200) NOT NULL COMMENT '密钥',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) NOT NULL COMMENT '状态 -1禁用 0失效 1正常'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APIKEY';

--
-- 转存表中的数据 `hy_api_key`
--

INSERT INTO `hy_api_key` (`id`, `app_name`, `app_key`, `security_key`, `create_time`, `update_time`, `status`) VALUES
(1, '第一个应用', '121454155', 'UUXAJSDHIWDHIWHDIWDWIH', '2018-10-16 05:34:22', NULL, 1),
(2, '第二个应用', '321321232131', 'BBBBBBBBBBBBBBBBBBBBBB', '2018-10-16 05:34:22', NULL, 1);

--
-- 转储表的索引
--

--
-- 表的索引 `hy_api_key`
--
ALTER TABLE `hy_api_key`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `hy_api_key`
--
ALTER TABLE `hy_api_key`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
