insert into person(id,namesa,blood_type,year_of_birthday,month_of_birthday,day_of_birthday) values(1,'martin','A',1991,8,15)
insert into person(id,namesa,blood_type,year_of_birthday,month_of_birthday,day_of_birthday) values(2,'david','B',1992,7,21)
insert into person(id,namesa,blood_type,year_of_birthday,month_of_birthday,day_of_birthday) values(3,'dennis','O',1993,10,15)
insert into person(id,namesa,blood_type,year_of_birthday,month_of_birthday,day_of_birthday) values(4,'sophia','AB',1994,8,31)
insert into person(id,namesa,blood_type,year_of_birthday,month_of_birthday,day_of_birthday) values(5,'benny','A',1995,12,23)

insert into block(id,namess) values(1,'dennis')
insert into block(id,namess) values(2,'sophia')

update person set block_id =1 where id = 3
update person set block_id =2 where id = 4
