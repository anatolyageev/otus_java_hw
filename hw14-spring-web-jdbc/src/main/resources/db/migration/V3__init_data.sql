

INSERT INTO public.client (id, login, name, password) VALUES (1, 'dbServiceFirst', 'nameFirst', 'pass1');
INSERT INTO public.client (id, login, name, password) VALUES (2, 'dbServiceSecond', 'nameSecond', 'pass2');

INSERT INTO public.addressdataset (id, street, client_id) VALUES (1, 'addr1', 1);
INSERT INTO public.addressdataset (id, street, client_id) VALUES (2, 'addr2', 2);

INSERT INTO public.phone_data (id, number, client_id) VALUES (1, '12341551', 1);
INSERT INTO public.phone_data (id, number, client_id) VALUES (2, '12341552', 1);
INSERT INTO public.phone_data (id, number, client_id) VALUES (3, '12341553', 2);
INSERT INTO public.phone_data (id, number, client_id) VALUES (4, '12341554', 2);
