INSERT INTO giao_thong.role (id, name) VALUES (1, 'ROLE_GT');
INSERT INTO giao_thong.role (id, name) VALUES (2, 'ROLE_ADMIN');


INSERT INTO giao_thong.account (id, address_handle, avatar, detection, password, username) VALUES (1, '1.Hà Nội', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRacaWP2ETOX67Phc2qsXr_6RuLYo9el7vhI9Zh7RTO3Cv5vF-tvvV_zCrZOcEkoILYng&usqp=CAU', 'đội cagt Hà Đông - Hà Nội', '123456', 'gt1');
INSERT INTO giao_thong.account (id, address_handle, avatar, detection, password, username) VALUES (2, '1.Hà Nội', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRacaWP2ETOX67Phc2qsXr_6RuLYo9el7vhI9Zh7RTO3Cv5vF-tvvV_zCrZOcEkoILYng&usqp=CAU', 'đội cagt Hà Đông - Hà Nội', '123456', 'admin');

INSERT INTO giao_thong.account_roles (account_id, roles_id) VALUES (1, 1);
INSERT INTO giao_thong.account_roles (account_id, roles_id) VALUES (2, 2);


INSERT INTO giao_thong.category (id, name) VALUES (1, 'Tin tức');
INSERT INTO giao_thong.category (id, name) VALUES (2, 'Thông báo');


INSERT INTO giao_thong.blog (id, content, img, title, category_id) VALUES (1, 'giao thông văn minh', 'https://bizweb.dktcdn.net/thumb/grande/100/415/690/articles/giao-thong-duong-bo.jpg?v=1677660944393', 'giao thông văn minh', 1);
INSERT INTO giao_thong.blog (id, content, img, title, category_id) VALUES (2, 'giao thông văn minh', 'https://bizweb.dktcdn.net/thumb/grande/100/415/690/articles/giao-thong-duong-bo.jpg?v=1677660944393', 'giao thông văn minh', 1);
INSERT INTO giao_thong.blog (id, content, img, title, category_id) VALUES (3, 'giao thông văn minh', 'https://bizweb.dktcdn.net/thumb/grande/100/415/690/articles/giao-thong-duong-bo.jpg?v=1677660944393', 'giao thông văn minh', 1);
INSERT INTO giao_thong.blog (id, content, img, title, category_id) VALUES (4, 'giao thông văn minh', 'https://bizweb.dktcdn.net/thumb/grande/100/415/690/articles/giao-thong-duong-bo.jpg?v=1677660944393', 'giao thông văn minh', 1);
INSERT INTO giao_thong.blog (id, content, img, title, category_id) VALUES (5, 'giao thông văn minh', 'https://bizweb.dktcdn.net/thumb/grande/100/415/690/articles/giao-thong-duong-bo.jpg?v=1677660944393', 'giao thông văn minh', 1);
INSERT INTO giao_thong.blog (id, content, img, title, category_id) VALUES (6, 'giao thông văn minh', 'https://bizweb.dktcdn.net/thumb/grande/100/415/690/articles/giao-thong-duong-bo.jpg?v=1677660944393', 'giao thông văn minh', 1);


INSERT INTO giao_thong.car (id, address, category, date, license_plate, status, image) VALUES (1, 'Đông Triều - Quảng Ninh - Việt Nam', 'oto', '2024-07-10', '14A1-99999', 'thông hành', 'https://canthoauto.com/wp-content/uploads/2019/03/lexus-lx570-h%C3%ACnh-%E1%BA%A3nh-v%C3%A0-gi%C3%A1-b%C3%A1n-avatar.jpg');
INSERT INTO giao_thong.car (id, address, category, date, license_plate, status, image) VALUES (2, 'Đông Triều - Quảng Ninh', 'oto', '2024-07-10', '14A1-23456', 'thông hành', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSAl4aUKcMC8Qiny0Qmkg60PHmOOMaphAK3LqByp0uLnEm3SEjNX-oH1-JD9TBEZCLg_M&usqp=CAU');
INSERT INTO giao_thong.car (id, address, category, date, license_plate, status, image) VALUES (3, 'Đông Triều - Quảng Ninh', 'oto', '2024-07-10', '14A1-34567', 'thông hành', 'https://oto.net.vn/Content/Uploads/Articles/1-rao-vat/lexus-lm-2021/lexus-lx570-2021-3.jpg');
INSERT INTO giao_thong.car (id, address, category, date, license_plate, status, image) VALUES (4, 'Đông Triều - Quảng Ninh', 'oto', '2024-07-10', '14A1-45678', 'thông hành', 'https://xedoisong.vn/uploads/20221024/xedoisong_lexus_lx_570_mbs_1_ngpx.jpg');
INSERT INTO giao_thong.car (id, address, category, date, license_plate, status, image) VALUES (5, 'Đông Triều - Quảng Ninh', 'oto', '2024-07-10', '14A1-56789', 'thông hành', 'https://baodautu.vn/files/2014/05/03/lexus-lx570-moi-voi-cong-suat-len-toi-450-ma-luc-7.jpg');


INSERT INTO giao_thong.violate (id, address, behavior, status, time, account_id, car_id, image) VALUES (1, 'Hà Nội', 'Vượt đèn đỏ', 'chưa xử lý', '2024-07-10 12:38:20', 1, 1, 'https://conganbacgiang.gov.vn/uploads/files/z5276432595221_9a474bb4703ca808ad0c7a7da9497ea2.jpg');
INSERT INTO giao_thong.violate (id, address, behavior, status, time, account_id, car_id, image) VALUES (4, 'Hạ Long - Quảng Ninh', 'Vượt đèn đỏ khu vực Hạ Long', 'chưa xử lý', '2024-07-11 12:24:00', 1, 2, 'https://hnm.1cdn.vn/2020/05/28/hanoimoi.com.vn-uploads-images-phananh-2020-05-28-_dungdo.jpg');
INSERT INTO giao_thong.violate (id, address, behavior, status, time, account_id, car_id, image) VALUES (5, 'ABC', 'Vượt đèn đỏ khu vực Hạ Long', 'chưa xử lý', '2024-07-11 13:15:00', 1, 1, 'https://bcp.cdnchinhphu.vn/334894974524682240/2022/2/14/an-toan-giao-thong-16448357601842010334949.jpeg');
