create database if not exists carecentral;

use carecentral;
create table if not exists users(
user_id int primary key auto_increment,
first_name varchar(15) not null,
last_name varchar(15) not null,
age int not null,
gender enum('M' , 'F' , 'O'),
mobile_number long not null,
email_id varchar(50) not null unique,
password varchar(20) not null,
is_active boolean default true,
created_at timestamp default current_timestamp,
modified_at timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

alter table users add constraint check_age check (age>=16);

-- create users --
insert into users(first_name , last_name , age , gender , mobile_number , email_id , password)
values("Deepika" , "Sriram" , 18 , 'F' , 7397314532 , "deepika.sriram@fssa.freshworks.com" , "Deepu@1234"),
("Uthra" , "Boopathy" , 18 , 'F' , 9876123403 , "uthra@gmail.com" , "uthraB*#*#1234") ,
("Vignesh" , "Ramachandran" , 22 , 'M' , 6379871209 , "sivavicky@gmail.com" , "vickySK**2000"),
("Praveen" , "Kumar" , 18 , 'M' , 7834091298 , "praveen@gmail.com" , "asdf***1234") , 
("Meera" , "Raghavan" , 45 , 'F' , 9812093488 , "meera012@gmail.com" , "MeErA*&45") , 
("Shalini" , "Shetty" , 50 , 'F' , 9934660537 , "shalinishty015@gmail.com" , "Shlnshty***123");

select * from users;

-- update users -- 
update users set last_name = "Boopathy Kannan" where user_id = 2;

-- delete users --
update users set is_active = 0 where user_id = 1;

-- listing active users --
select * from users where is_active = 1;



-- creating doctor table --
create table if not exists doctors(
doctor_id int auto_increment primary key,
user_id int,
FOREIGN KEY (user_id) REFERENCES users (user_id),
qualifications varchar(30) not null,
experience int not null,
department varchar(30) not null,
is_active boolean default true,
created_at timestamp default current_timestamp,
modified_at timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

alter table doctors drop column is_active;

-- creating doctor -- 
insert into doctors(user_id , qualifications , experience , department)
values(5 , 'MBBS , DGO-General Medicine' , 120 , "Gynecologist");
insert into doctors(user_id , qualifications , experience , department)
values(6 , "MBBS , MS-Opthalmology" , 30 , "Opthalmologist , eye surgeon" );

-- listing doctor --
select * from doctors;

-- updating doctor --
update doctors set experience = 3600 where doctor_id = 1;

-- deleting doctor --
update users set is_active = 0 where user_id = 5;
