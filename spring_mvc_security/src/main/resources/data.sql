insert into Benutzer (id, alias, firstName, lastName, password, role, traeger) values (1, 'fhenzel1', 'frank', 'henzel', '12434edd1c63859d1d6c682658082645',  'ROLE_USER', 'traeger-moembris');

insert into Kita (id, name) values (1, 'wiesbaden1');
insert into Kita (id, name) values (2, 'moembris1');

insert into BenutzerKitaRecht (id, recht, benutzer_id, kita_id) values (null, 'WRITE', 1, 1);
insert into BenutzerKitaRecht (id, recht, benutzer_id, kita_id) values (null, 'READ', 1, 2);


insert into Kind (id, firstName, lastName, kita_id) values (null, 'Jim', 'Smith', 1);
insert into Kind (id, firstName, lastName, kita_id) values (null, 'Tina', 'Marsh', 1);
insert into Kind (id, firstName, lastName, kita_id) values (null, 'Steve', 'Blair', 2);