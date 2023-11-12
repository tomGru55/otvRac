--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: oscars; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.oscars (
    naziv_ceremonije character varying NOT NULL,
    datum_ceremonije date,
    lokacija_ceremonije character varying,
    kategorija_nagrade character varying,
    film character varying NOT NULL,
    dobitnik_ime character varying,
    dobitnik_prezime character varying,
    dobitnik_datumrod date,
    ime_lika_u_filmu character varying NOT NULL,
    redatelj character varying
);


ALTER TABLE public.oscars OWNER TO postgres;

--
-- Data for Name: oscars; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.oscars (naziv_ceremonije, datum_ceremonije, lokacija_ceremonije, kategorija_nagrade, film, dobitnik_ime, dobitnik_prezime, dobitnik_datumrod, ime_lika_u_filmu, redatelj) FROM stdin;
76th Academy awards	2004-02-29	Kodak theatre, Los Angeles	Najbolji glumac	Mystic River	Sean	Penn	1960-08-17	Jimmy Markum	Clint Eastwood
76th Academy awards	2004-02-29	Kodak theatre, Los Angeles	Najbolji sporedni glumac	Mystic River	Tim	Robbins	1958-10-16	Dave Boyle	Clint Eastwood
76th Academy awards	2004-02-29	Kodak theatre, Los Angeles	Najbolja glumica	Monster	Charlize	Theron	1975-08-07	Alien Wuornos	Patty Jenkins
76th Academy awards	2004-02-29	Kodak theatre, Los Angeles	Najbolja sporedna glumica	Cold mountain	Renée 	Zellweger 	1969-04-25	Ruby Thewes	Anthony Minghella
77th Academy awards	2005-02-27	Kodak theatre, Los Angeles	Najbolji glumac	Ray	Jamie	Fox	1967-12-13	Ray Charles	Taylor Hackford
77th Academy awards	2005-02-27	Kodak theatre, Los Angeles	Najbolji sporedni glumac	Million Dollar Baby	Morgan	Freeman	1937-06-01	Eddie Scrap-Iron Duprist	Clint Eastwood
77th Academy awards	2005-02-27	Kodak theatre, Los Angeles	Najbolja glumica	Million Dollar Baby	Hillary	Swank	1974-06-30	Margaret Maggie Fitzgerald	Clint Eastwood
77th Academy awards	2005-02-27	Kodak theatre, Los Angeles	Najbolja sporedna glumica	The Aviator	Cate	Blanchett	1969-05-14	Katherine Hepburn	Martin Scorsese
78th Academy awards	2006-04-05	Kodak theatre, Los Angeles	Najbolji glumac	Capote	Phillip Seymour	Hoffman	1967-06-23	Truman Capote	Bennet Miller
78th Academy awards	2006-04-05	Kodak theatre, Los Angeles	Najbolji sporedni glumac	Syriana	George	Clooney	1961-05-06	Bob Barnest	Stephen Gaghan
78th Academy awards	2006-04-05	Kodak theatre, Los Angeles	Najbolja glumica	Walk the line	Reese	Whiterspoon	1976-04-22	June Carter Cash	James Mangold
78th Academy awards	2006-04-05	Kodak theatre, Los Angeles	Najbolja sporedna glumica	The Constant Gardener	Rachel	Weisz	1970-04-07	Tessa Quayle	Fernando Meirelles
\.


--
-- Name: oscars oscars_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oscars
    ADD CONSTRAINT oscars_pkey PRIMARY KEY (ime_lika_u_filmu);


--
-- PostgreSQL database dump complete
--

