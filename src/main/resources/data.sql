--User
insert into user values(10001, sysdate(), 'AB');
insert into user values(10002, sysdate(), 'Jack');
insert into user values(10003, sysdate(), 'Jill');
--Post
insert into post values(11001, 'My first post', 10001);
insert into post values(11002, 'My second post', 10001);