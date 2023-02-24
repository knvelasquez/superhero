-- -----------------------------------------------------
-- ROLE_ADMIN= Allows Access to all system resources.'
-- ROLE_CONSULT= Allows only consult resources.
-- ROLE_CREATE= Allows only create resources.
-- ROLE_UPDATE= Allows only update resources.
-- ROLE_DELETE= Allows only delete resources.
INSERT INTO `user_privilege` VALUES(1, 1020,'ROLE_ADMIN');
INSERT INTO `user_privilege` VALUES(2, 1025,'ROLE_CONSULT');
INSERT INTO `user_privilege` VALUES(3, 1035,'ROLE_CREATE');
INSERT INTO `user_privilege` VALUES(4, 1045,'ROLE_UPDATE');
INSERT INTO `user_privilege` VALUES(5, 1055,'ROLE_DELETE');

INSERT INTO `user_privilege` VALUES(6, 1065,'ROLE_CONSULT');
INSERT INTO `user_privilege` VALUES(7, 1065,'ROLE_CREATE');

INSERT INTO `user_privilege` VALUES(8, 1075,'ROLE_CONSULT');
INSERT INTO `user_privilege` VALUES(9, 1075,'ROLE_CREATE');
INSERT INTO `user_privilege` VALUES(10, 1075,'ROLE_UPDATE');

INSERT INTO `user_privilege` VALUES(11, 1085,'ROLE_CONSULT');
INSERT INTO `user_privilege` VALUES(12, 1085,'ROLE_CREATE');
INSERT INTO `user_privilege` VALUES(13, 1085,'ROLE_UPDATE');
INSERT INTO `user_privilege` VALUES(14, 1085,'ROLE_DELETE');

-- -----------------------------------------------------