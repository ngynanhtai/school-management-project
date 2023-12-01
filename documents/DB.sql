INSERT INTO public."role"
(id, code, "name", "type")
VALUES(2, 'MATH TEACHER', 'Giáo viên Toán', 'TEACHER');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(7, 'CLEANING_STAFF', 'Lao công', 'EMPLOYEE');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(8, 'NANNY', 'Bảo mẫu', 'EMPLOYEE');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(9, 'GUARD', 'Bảo vệ', 'EMPLOYEE');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(10, 'STUDENT', 'Học sinh', 'STUDENT');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(3, 'PHYSICS_TEACHER', 'Giáo viên Vật lý', 'TEACHER');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(4, 'BIOLOGY_TEACHER', 'Giáo viên Sinh học', 'TEACHER');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(5, 'CHEMISTRY_TEACHER', 'Giáo viên Hoá học', 'TEACHER');
INSERT INTO public."role"
(id, code, "name", "type")
VALUES(6, 'LITERRATURE_TEACHER', 'Giáo viên Ngữ văn', 'TEACHER');

INSERT INTO public.employee
(id, address, code, district, dob, email, full_name, gender, marital_status, national_id, "password", phone_number, profession, province, salary, ward, role_id)
VALUES(1, '24C Phan Đăng Lưu', 'TEACHER-qhgk-1701222', 'Bình Thạnh', '2001-05-21 07:00:00.000', 'nguyen.anh-tai1@hdsaison.com.vn', 'Nguyễn Anh Tài', 'male', 'SINGLE', '079201019196', NULL, '0931207366', '12', 'HCM', 20000000, '6', 3);
INSERT INTO public.employee
(id, address, code, district, dob, email, full_name, gender, marital_status, national_id, "password", phone_number, profession, province, salary, ward, role_id)
VALUES(2, '38 Cộng Hoà', 'EMPLOYEE-xmju-1701249', 'Tân Bình', '1998-06-11 07:00:00.000', 'test@gmail.com', 'Nguyễn Bảo Vệ', 'male', 'MARRIED', '079568742164', NULL, '0987999666', '12', 'HCM', 8000000.0, '6', 9);

INSERT INTO public.student
(id, address, code, district, dob, email, first_parent_name, first_parent_phone, first_parent_relation, full_name, gender, national_id, "password", phone_number, province, second_parent_name, second_parent_phone, second_parent_relation, ward, role_id)
VALUES(1, '20/10 Cống Lỡ', 'STUDENT-gtvk-1701223', 'Tân Bình', '2001-03-17 07:00:00.000', 'vnbgiangxd@gmail.com', 'A', '0999888777', 'Ba', 'Vũ Nguyễn Bình Giang', 'female', '012345678910', NULL, '0928134544', 'HCM', 'B', '0987456321', 'Mẹ', '15', 10);
INSERT INTO public.student
(id, address, code, district, dob, email, first_parent_name, first_parent_phone, first_parent_relation, full_name, gender, national_id, "password", phone_number, province, second_parent_name, second_parent_phone, second_parent_relation, ward, role_id)
VALUES(2, '11 Phạm Văn Chiêu', 'STUDENT-or1h-1701249', '4', '2014-05-06 07:00:00.000', 'hahahihi@gmail.com', 'Nguyen Van A', '0964753544', 'Ba', 'Nguyễn Thị Siu', 'female', NULL, NULL, NULL, 'HCM', 'Nguyen Thi B', '0954687424', 'Mẹ', '10', 10);


INSERT INTO public.classroom
(id, code, grade, "name", total, "type", "year", home_teacher_id)
VALUES(1, 'GRADE01-nezi-1701249', 'GRADE01', '1/1', 0, 'MAIN', '2023', 1);