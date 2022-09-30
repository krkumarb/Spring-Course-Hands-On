insert into user_details(id,birth_date,name) values(1001,current_date(),'name 1');
insert into user_details(id,birth_date,name) values(1002,current_date(),'name 2');
insert into user_details(id,birth_date,name) values(1003,current_date(),'name 3');

insert into post(id,description,user_id) values(2001,'post 1',1001);
insert into post(id,description,user_id) values(2002,'post 2',1001);
insert into post(id,description,user_id) values(2003,'post 3',1001);
insert into post(id,description,user_id) values(2004,'post 4',1002);
insert into post(id,description,user_id) values(2005,'post 5',1002);
insert into post(id,description,user_id) values(2006,'post 6',1003);
insert into post(id,description,user_id) values(2007,'post 7',1003);