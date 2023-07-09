#ROLES
INSERT INTO `role` (`id`, `name`)VALUES (1, 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `name`)VALUES (2, 'ROLE_USER');

#ADMIN
INSERT INTO `users` (id, email, email_verified, name, password, provider)VALUES (1, 'admin@dashboard.com', TRUE, 'admin', '$2a$10$xax4sqtOiyaSKF0SlWlDbOyHZKwQG0ZQ1qEwMpzIMI7PUG0or0Uby','local');
INSERT INTO `role_users` (roles_id, users_id)VALUES (1, 1);
INSERT INTO `users_roles` (user_id, roles_id)VALUES (1, 1);

#SERVICES
INSERT INTO `services` (name, base_url, active)VALUES ('CheapShark', 'https://www.cheapshark.com/api/1.0/', TRUE);
INSERT INTO `services` (name, base_url, active)VALUES ('OpenWeatherMap', 'https://api.openweathermap.org/data/3.0/', TRUE);
INSERT INTO `services` (name, base_url, active)VALUES ('BitcoinPrice', 'https://api.coindesk.com/v1/bpi/currentprice.json/', TRUE);

#WIDGETS
INSERT INTO `widgets` (name, url, description,active, service_id)VALUES ('BestDeal', 'https://www.cheapshark.com/api/1.0/','Display five best deals from CheapShark platform', TRUE, 1);
INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('BestGameInSale', 'https://www.cheapshark.com/api/1.0/deals?storeID=1&pageSize=5&sortBy=Metacritic/','Display games with the best quality/price ratio from CheapShark platform', TRUE, 1);

INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('Temperature', '','Display current temperature from a given location', TRUE, 2);
INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('Humidity', '', 'Display current humidity from a given location',TRUE, 2);
INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('Pressure', '','Display current pressure from a given location', TRUE, 2);
INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('Wind', '', 'Display current wind from a given location',TRUE, 2);
INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('FeelLike', '','Display current feel like temperature from a given location', TRUE, 2);
INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('TempMin', '','Display current min temperature from a given location', TRUE, 2);
INSERT INTO `widgets` (name, url,description, active, service_id)VALUES ('TempMax', '','Display current max temperature from a given location', TRUE, 2);

INSERT INTO `widgets` (name, url, description,active, service_id)VALUES ('BitcoinPrice', '','Display current Bitcoin price', TRUE, 3);

INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (1, 1);
INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (1, 2);


INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (2, 3);
INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (2, 4);
INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (2, 5);
INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (2, 6);
INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (2, 7);
INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (2, 8);
INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (2, 9);

INSERT INTO `services_widgets` (service_id, widgets_id)VALUES (3, 10);


INSERT INTO param (name, type, widget_id)VALUES ('city', 'string', 3),('city', 'string', 4),('city', 'string', 5),('city', 'string', 6),('city', 'string', 7),('city', 'string', 8),('city', 'string', 9);

INSERT INTO widgets_params (params_id, widget_id)VALUES (1, 3),(2, 4),(3, 5),(4, 6),(5, 7),(6, 8),(7, 9);


