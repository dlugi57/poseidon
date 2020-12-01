
insert into user(id, fullname, username, password, role) values(1,'Administrator', 'admin', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'ADMIN');
insert into user(id,fullname, username, password, role) values(2,'User1', 'user1', '$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa', 'USER');
insert into user(id,fullname, username, password, role) values(3,'test', 'test', '$2a$10$6uspipZGSAs16PbB9KdzxeYg7Z4wzOplsZIJzXEDBRw0e1hyrkl0O', 'ADMIN');
insert into user(id,fullname, username, password, role) values(5,'user', 'user', '$2a$10$CnodS5wdELXZicpFC2StkeM9mLml2jQd7fPCWELdp7EcrAOF6TOei', 'USER');



insert into bid_list(id, account, bid_quantity, type) values(1, 'Tests1', 10, 'DescTests1');
insert into bid_list(id, account, bid_quantity, type) values(2, 'Tests2', 10, 'DescTests2');
insert into curve_point(id, curve_id, term, value) values (1, 1, 2, 3);
insert into curve_point(id, curve_id, term, value) values (2, 4, 5, 6);
insert into rating(id, fitch_rating ,moodys_rating, sandprating, order_number) values (10, '1', '2','2', 3);
insert into rating(id, fitch_rating , moodys_rating, sandprating, order_number) values (20, '4', '5','2', 6);
insert into rule_name(id, name, description, json, template, sql_str, sql_part) values (1, '1', '2', '3', '4', '5', '6');
insert into rule_name(id, name, description, json, template, sql_str, sql_part) values (2, '7', '8', '9', '10', '11', '12');
insert into trade(id, account, type, buy_quantity) values (1, '1', '2', 3);
insert into trade(id, account, type, buy_quantity) values (2, '4', '5', 6);