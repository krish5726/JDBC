alter table student
add constraint pk_stud PRIMARY KEY (roll_no);

alter table course
add constraint pk_course PRIMARY KEY (course_id);

alter table teacher
add constraint pk_teach PRIMARY KEY (teacher_id);

alter table course
add constraint fk_cour_teach FOREIGN KEY (teacher_id)
REFERENCES teacher (teacher_id)
ON DELETE CASCADE;

alter table book
add constraint pk_book PRIMARY KEY (ISBN);

alter table book
add constraint fk_book_course FOREIGN KEY (course_id)
REFERENCES course (course_id)
ON DELETE CASCADE;

alter table student_course
add constraint pk_stud_cour PRIMARY KEY (roll_no, course_id);

alter table student_course
add constraint fk_stud FOREIGN KEY (roll_no)
REFERENCES student (roll_no)
ON DELETE CASCADE;

alter table student_course
add constraint fk_course FOREIGN KEY (course_id)
REFERENCES course (course_id)
ON DELETE CASCADE;