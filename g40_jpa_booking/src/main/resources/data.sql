
INSERT INTO g40_spring_vaccine_booking.app_user (id, username, password)
VALUES ('u1', 'test60', 'WhatIsYourPassword');

INSERT INTO g40_spring_vaccine_booking.app_user (id, username, password)
VALUES ('u2', 'testina64', 'Incorrect');

INSERT INTO g40_spring_vaccine_booking.app_user (id, username, password)
VALUES ('u3', 'Joe1337', 'ItHurtsWhenILookAtIt');



INSERT INTO g40_spring_vaccine_booking.app_role (id, role)
VALUES ('r1', 'ROLE_SUPER_ADMIN');

INSERT INTO g40_spring_vaccine_booking.app_role (id, role)
VALUES ('r2', 'ROLE_PATIENT_USER()');


INSERT INTO g40_spring_vaccine_booking.role_app_user (fk_app_role_id, fk_app_user_id)
VALUES ('r1', 'u3');

INSERT INTO g40_spring_vaccine_booking.role_app_user (fk_app_role_id, fk_app_user_id)
VALUES ('r2', 'u1');

INSERT INTO g40_spring_vaccine_booking.role_app_user (fk_app_role_id, fk_app_user_id)
VALUES ('r1', 'u2');


INSERT INTO contact_info (id, email, phone)
VALUES ('ci1', 'test@home.se', '+4670123456');

INSERT INTO contact_info (id, email, phone)
VALUES ('ci2', 'testina@home.se', '+46707654321');

INSERT INTO contact_info (id, email, phone)
VALUES ('ci3', 'joe@borta.no', '+479876543');

INSERT INTO contact_info (id, email, phone)
VALUES ('ci4', 'info@lasarett.se', '1234567890');

INSERT INTO contact_info (id, email, phone)
VALUES ('ci5', 'info@vårdcentral.se', '0987654321');



INSERT INTO g40_spring_vaccine_booking.address (id, street, zip_code, city)
VALUES ('a1', 'Hemvägen 1', '123 45', 'Växjö');

INSERT INTO g40_spring_vaccine_booking.address (id, street, zip_code, city)
VALUES ('a2', 'Bortavägen 404', '404 04', 'Varelse');

INSERT INTO g40_spring_vaccine_booking.address (id, street, zip_code, city)
VALUES ('a3', 'Lasarettsgatan 5', '123 45', 'Växjö');

INSERT INTO g40_spring_vaccine_booking.address (id, street, zip_code, city)
VALUES ('a4', 'Vårdgatan 1', '123 45', 'Växjö');

INSERT INTO g40_spring_vaccine_booking.address (id, street, zip_code, city)
VALUES ('a5', 'someStreet 1', '123 45', 'Växjö');



INSERT INTO g40_spring_vaccine_booking.patient (id, birthdate, first_name, last_name, pnr, fk_contact_info_id)
VALUES ('pa1', '1960-01-01', 'Test', 'Testsson', '19600101-1337', 'ci1');

INSERT INTO g40_spring_vaccine_booking.patient (id, birthdate, first_name, last_name, pnr, fk_contact_info_id)
VALUES ('pa2', '1964-02-14', 'Testina', 'Testsson', '19640214-1338', 'ci2');

INSERT INTO g40_spring_vaccine_booking.patient (id, birthdate, first_name, last_name, pnr, fk_contact_info_id)
VALUES ('pa3', '1990-12-24', 'Joe', 'Doe', '19901224-1234', 'ci3');


INSERT INTO g40_spring_vaccine_booking.premises (id, name, fk_address_id)
VALUES ('pr1', 'Lasarettet', 'a3');

INSERT INTO g40_spring_vaccine_booking.premises (id, name, fk_address_id)
VALUES ('pr2', 'Vårdcentralen Söder', 'a4');



INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b1', '2020-04-20 14:00:00', 0, 'Stickan', 'tozinameran', 1, null, 'pr1');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b2', '2020-04-20 14:03:00', 0, 'Stickan', 'tozinameran', 1, null, 'pr1');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b3', '2020-04-20 14:06:00', 0, 'Stickan', 'tozinameran', 1, null, 'pr1');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b4', '2020-04-20 14:09:00', 0, 'Stickan', 'tozinameran', 1, null, 'pr1');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b5', '2020-04-20 14:12:00', 0, 'Stickan', 'tozinameran', 1, null, 'pr1');


INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b6', '2020-04-20 14:00:00', 0, 'Injector', 'elasomeran', 1, null, 'pr2');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b7', '2020-04-20 14:03:00', 0, 'Injector', 'elasomeran', 1, null, 'pr2');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b8', '2020-04-20 14:06:00', 0, 'Injector', 'elasomeran', 1, null, 'pr2');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b9', '2020-04-20 14:09:00', 0, 'Injector', 'elasomeran', 1, null, 'pr2');
INSERT INTO g40_spring_vaccine_booking.booking (id, date_time, price, administrator_id, vaccine_type, vacant, fk_patient_id, premises_id)
VALUES ('b10', '2020-04-20 14:12:00', 0, 'Injector', 'elasomeran', 1, null, 'pr2');











