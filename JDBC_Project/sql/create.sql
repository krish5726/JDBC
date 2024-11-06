create table student(
    fname varchar(30),
    lname varchar(30),
    roll_no int
);
create table course(
    course_name varchar(30),
    course_id int,
    teacher_id int
);
create table teacher(
    teacher_name varchar(30),
    teacher_id int
);
create table book(
    ISBN varchar(30),
    book_name varchar(30),
    author varchar(30),
    course_id int
);
create table student_course(
    roll_no int,
    course_id int
);