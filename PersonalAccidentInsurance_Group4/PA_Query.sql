create database Insurance;

use Insurance;
select * from user;
select * from policy_holder;
select * from category;

select * from premium;
select * from claim_form;
select * from beneficiary;
select * from payment;

insert into category values(1,100,'Death');
insert into category values(2,50,'Both Hands');
insert into category values(3,50,'Both feet');
insert into category values(4,70,'Entire sight in both eyes');
insert into category values(5,40,'One hand and entire sight of one eye');
insert into category values(6,40,'One leg and entire sight of one eye');
insert into category values(7,70,'Speech and hearing');
insert into category values(8,40,'One hand or one foot');
insert into category values(9,45,'One arm or one leg');
insert into category values(10,50,'Entire sight in one eye');
insert into category values(11,50,'Either speech or hearing');
insert into category values(12,40,'Four fingers of either hand');
insert into category values(13,30,'Hearing in one ear');
insert into category values(14,40,'All toes of one foot');
insert into category values(15,85,'Disability');