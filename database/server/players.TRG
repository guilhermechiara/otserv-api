TYPE=TRIGGERS
triggers='CREATE DEFINER=`root`@`localhost` TRIGGER `ondelete_players` BEFORE DELETE ON `players`\n FOR EACH ROW BEGIN\n    UPDATE `houses` SET `owner` = 0 WHERE `owner` = OLD.`id`;\nEND'
sql_modes=1411383296
definers='root@localhost'
client_cs_names='latin1'
connection_cl_names='latin1_swedish_ci'
db_cl_names='utf8mb4_general_ci'
created=159905213847
