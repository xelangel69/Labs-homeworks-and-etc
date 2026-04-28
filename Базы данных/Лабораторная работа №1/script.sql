CREATE TABLE space_ships (
    id serial PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE crew_members (
    id serial PRIMARY KEY,
    name text NOT NULL,
    ship_id int NOT NULL, 
    noticed_anomaly bool DEFAULT FALSE,
    FOREIGN KEY (ship_id) REFERENCES space_ships (id) ON DELETE CASCADE
);

CREATE TABLE ship_systems (
    id serial PRIMARY KEY,
    name text NOT NULL,
    ship_id int NOT NULL REFERENCES space_ships (id) ON DELETE CASCADE,
    working_properly bool DEFAULT TRUE
);

CREATE TABLE anomalies (
    id serial PRIMARY KEY,
    system_id int NOT NULL REFERENCES ship_systems (id) ON DELETE CASCADE,
    description text
);

CREATE TABLE fixations (
    id serial PRIMARY KEY,
    crew_member_id int REFERENCES crew_members (id) ON DELETE SET NULL,
    anomaly_id int NOT NULL REFERENCES anomalies (id) ON DELETE CASCADE,
    fixation_time timestamp DEFAULT NOW()
);

INSERT INTO space_ships (name) VALUES ('Дискавери');

INSERT INTO crew_members (name, ship_id, noticed_anomaly) VALUES ('Боумен', 1, true);

INSERT INTO ship_systems (name, ship_id, working_properly) VALUES 
('Система наблюдения', 1, false),
('Система жизнеобеспечения', 1, true),
('Система охраны', 1, false);

INSERT INTO anomalies (system_id, description) VALUES
(1, 'Камеры наблюдения перестали работать'),
(3, 'Охранные турели начали сбоить');

INSERT INTO fixations (crew_member_id, anomaly_id, fixation_time) VALUES 
(1, 2, '2001-05-10 21:30:00'),
(1, 1, '2001-05-11 15:26:00');

COMMIT;