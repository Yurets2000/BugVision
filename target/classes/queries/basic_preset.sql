INSERT INTO public.roles (id, name) VALUES ('86029608-b8a0-4add-a8c1-c5058d0c74b9', 'ROLE_USER');
INSERT INTO public.roles (id, name) VALUES ('d51478ba-8099-4685-8ce4-807175a8e365', 'ROLE_ADMIN');

INSERT INTO public.users (id, age, email, name, password, surname, username) VALUES ('2e376b9f-bd6c-42da-9d75-34e95906c2b4', 20, 'uraura05052000@gmail.com', 'Yurii', '$2a$10$3b4r.SV3Qiu1eJYTk08d3OjNh0s8hOQifbccom3jKTnsw0e2Uk2aC', 'Bezliudnyi', 'yube0220');

INSERT INTO public.user_roles (user_id, role_id) VALUES ('2e376b9f-bd6c-42da-9d75-34e95906c2b4', '86029608-b8a0-4add-a8c1-c5058d0c74b9');
INSERT INTO public.user_roles (user_id, role_id) VALUES ('2e376b9f-bd6c-42da-9d75-34e95906c2b4', 'd51478ba-8099-4685-8ce4-807175a8e365');
