insert into Benutzer (id, alias, firstName, lastName, password, role) values (1, 'fhenzel2', 'frank', 'henzel', '12434edd1c63859d1d6c682658082645',  'ROLE_USER');

insert into Kita (id, name) values (1, 'moembris1');
insert into Kita (id, name) values (2, 'moembris2');
insert into Kita (id, name) values (3, 'moembris3');

insert into BenutzerKitaRecht (id, recht, benutzer_id, kita_id) values (null, 'WRITE', 1, 1);
insert into BenutzerKitaRecht (id, recht, benutzer_id, kita_id) values (null, 'WRITE', 1, 2);
insert into BenutzerKitaRecht (id, recht, benutzer_id, kita_id) values (null, 'READ', 1, 3);


insert into Kind (id, firstName, lastName, kita_id) values (null, 'Joseph', 'Hill', 1);
insert into Kind (id, firstName, lastName, kita_id) values (null, 'Sabine', 'Rudolph', 2);
insert into Kind (id, firstName, lastName, kita_id) values (null, 'Tura', 'Berger', 3);