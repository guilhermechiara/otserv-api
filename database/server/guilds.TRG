TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER `oncreate_guilds` AFTER INSERT ON `guilds`\n FOR EACH ROW BEGIN\n    INSERT INTO `guild_ranks` (`name`, `level`, `guild_id`) VALUES (\'the Leader\', 3, NEW.`id`);\n    INSERT INTO `guild_ranks` (`name`, `level`, `guild_id`) VALUES (\'a Vice-Leader\', 2, NEW.`id`);\n    INSERT INTO `guild_ranks` (`name`, `level`, `guild_id`) VALUES (\'a Member\', 1, NEW.`id`);\nEND'
sql_modes=1411383296
definers='root@localhost'
client_cs_names='latin1'
connection_cl_names='latin1_swedish_ci'
db_cl_names='utf8mb4_general_ci'
created=159905213848
