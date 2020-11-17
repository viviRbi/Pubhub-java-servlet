--                BOOK

drop table if exists books;

create table books (
  isbn_13 varchar (13) primary key,
  title varchar (100),
  author varchar (80),
  publish_date date,
  price decimal (6,2),
  content bytea
);

insert into books values (
  '1111111111111',          	-- id
  'The Adventures of Steve',    -- title
  'Russell Barron', 			-- author
  current_date,    				-- publishDate
  123.50,   					-- price
  null							-- blob
);
insert into books values (
  '1111111111112',          	-- id
  'Candle Star',    -- title
  'Michell Isenhoff', 			-- author
  current_date,    				-- publishDate
  123.50,   					-- price
  null							-- blob
);

--                BOOKTAG
drop table if exists book_tags;
CREATE TABLE book_tags
(
    id character varying COLLATE pg_catalog."default" NOT NULL,
    tag_name character varying COLLATE pg_catalog."default" NOT NULL,
    isbn_13 character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT book_tags_pkey PRIMARY KEY (id)
)

INSERT INTO booktags(
	id, tag_name, isbn_13)
	VALUES ('1', 'Fantasy', '1111111111111');
INSERT INTO booktags(
	id, tag_name, isbn_13)
	VALUES ('2', 'Adventure', '1111111111111');
INSERT INTO booktags(
	id, tag_name, isbn_13)
	VALUES ('3', 'Romance', '1111111111112');