CREATE TABLE `user`
(
    `id`          BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `username`    VARCHAR(255)        NOT NULL COMMENT '用户名',
    `password`    VARCHAR(255)        NOT NULL COMMENT '密码',
    `salt`        VARCHAR(255)        NOT NULL COMMENT '盐',
    `status`      TINYINT(4) UNSIGNED DEFAULT 1 COMMENT '状态：1启动 2禁用',
    `create_time` TIMESTAMP           NOT NULL COMMENT '创建时间',
    `update_time` TIMESTAMP           DEFAULT CURRENT_TIMESTAMP ON
        UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '用户表';

CREATE TABLE `role`
(
    `id`          INT(11) NOT NULL COMMENT '主键id',
    `role_type`   TINYINT(4) UNSIGNED DEFAULT NULL COMMENT '角色类型：0管理员 1普通用户',
    `description` VARCHAR(255)        DEFAULT NULL COMMENT '角色描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '角色表';

CREATE TABLE `permission`
(
    `id`              INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `permission_type` TINYINT(4) UNSIGNED DEFAULT NULL COMMENT '权限类型：',
    `description`     VARCHAR(255)        DEFAULT NULL COMMENT '权限描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '权限表';

DROP TABLE IF EXISTS user_role_relation;

CREATE TABLE `user_role_relation`
(
    `id`      INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` INT(20) DEFAULT NULL COMMENT '用户id',
    `role_id` INT(11) DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '用户角色关联表';

CREATE TABLE `role_permission_relation`
(
    `id`            INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `role_id`       INT(11) DEFAULT NULL COMMENT '角色id',
    `permission_id` INT(11) DEFAULT NULL COMMENT '权限id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '角色权限关联表';
