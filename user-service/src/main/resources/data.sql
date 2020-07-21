INSERT INTO user(id, username, password, role, created_at) VALUES
(1, 'admin', '$2a$10$08G9MoHmIElHEp33nrZxXehx1eJoDT5I9LMkGkTonZm3ca340s0Fe', 'ADMIN', NOW()),
(2, 'user', '$2a$10$R4dULbfDzfISbmsj0HxlwOP5r7sFDTyrQ5sOpPKQgI2HKec0Blroq', 'USER', NOW());
(3, 'user2', '$2a$10$2MACMXr19mq4tAFWHptzEu56kJ816obN5jOZv0RYwRZ9mFc69X3Hu', 'USER', NOW())

insert into user_subscriptions(user_id, subscriptions) values (3, 2)