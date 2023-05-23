DROP TABLE IF EXISTS USER;
CREATE TABLE user
(
  `id`         char(32)     NOT NULL COMMENT 'id',
  `loginid`   varchar(32)  NOT NULL COMMENT 'ログインID',
  `name`       varchar(255) NOT NULL COMMENT 'ユーザー名',
  `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
  `created_by` char(32)     NOT NULL COMMENT '作成者',
  `updated_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
  `updated_by` char(32)     NOT NULL COMMENT '更新者',
  `is_deleted` bit(1)       NOT NULL DEFAULT b'0' COMMENT '削除フラグ',
  PRIMARY KEY (`id`),
  UNIQUE uk_loginid(loginid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT 'ユーザー';
