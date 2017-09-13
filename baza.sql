--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.0
-- Dumped by pg_dump version 9.6.0

-- Started on 2017-09-13 16:54:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = "PersonalizovanaMedicina", pg_catalog;

--
-- TOC entry 2196 (class 0 OID 16409)
-- Dependencies: 188
-- Data for Name: dijagnoze; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY dijagnoze (id, naziv, opis, koristi, postotak) FROM stdin;
15	artritis	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
3	gripa	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
19	dijabesta	Dijabetes je poremecaj povecavanja razine secera u krvi zlijezde gusterace (pankreas-a), koji se desava kada gusteraca prestane potpuno ili djelomicno proizvoditi hormon inzulin ili proizvedeni inzulin nije djelotvoran u organizmu.  Bolest je nasljedna.	0	0
16	astma	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
14	lupus	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
18	celijakija	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
13	Alzheimerova bolest	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
17	aneurizma na mozgu	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
4	prehlada	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
12	malarija	Celijakija je nepodnosljivost organizma na gluten - bjelancevinu koje ima u zitaricama. Najvece ostecenje crijevne sluznice je u podrucju dvanaesnika jer je tamo izlozenost glutenu najveca. Lijeci se uvodjenjem bezglutenske dijete.	0	0
\.


--
-- TOC entry 2213 (class 0 OID 0)
-- Dependencies: 197
-- Name: dijagnoze_id_seq; Type: SEQUENCE SET; Schema: PersonalizovanaMedicina; Owner: postgres
--

SELECT pg_catalog.setval('dijagnoze_id_seq', 19, true);


--
-- TOC entry 2201 (class 0 OID 16444)
-- Dependencies: 193
-- Data for Name: dijagnoze_lijekovi; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY dijagnoze_lijekovi (id_dijagnoze, id_lijeka) FROM stdin;
3	1
4	1
12	4
13	5
14	6
15	7
16	6
17	9
18	6
19	11
\.


--
-- TOC entry 2202 (class 0 OID 16459)
-- Dependencies: 194
-- Data for Name: dijagnoze_pacijenti; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY dijagnoze_pacijenti (id_dijagnoze, id_pacijenta, nivo_secera_u_krvi, broj_eritrocita, broj_leukocita, broj_trombocita, doza_lijeka, datum_dijagnoze) FROM stdin;
3	5	6	5	4	3	85	2016-12-11
4	1	6	0	0	0	82	2016-12-11
4	1	6	0	0	0	82	2017-01-09
12	1	0	100	1000	500	100	2017-01-19
19	12	16	0	0	0	731	2017-01-19
\.


--
-- TOC entry 2199 (class 0 OID 16424)
-- Dependencies: 191
-- Data for Name: korisnici; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY korisnici (id, username, password) FROM stdin;
1	merisa	merisa
2	armin	armin
\.


--
-- TOC entry 2214 (class 0 OID 0)
-- Dependencies: 196
-- Name: korisnici_id_seq; Type: SEQUENCE SET; Schema: PersonalizovanaMedicina; Owner: postgres
--

SELECT pg_catalog.setval('korisnici_id_seq', 2, true);


--
-- TOC entry 2198 (class 0 OID 16419)
-- Dependencies: 190
-- Data for Name: lijekovi; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY lijekovi (id, naziv, standardna_doza) FROM stdin;
1	paracetamol	100
5	aricept	100
6	kortikosteroidi	100
4	hidroksihlorokvin	100
7	ibuprofen	50
8	kortikosteroidi	50
9	antikoagulans	5
10	kortikosteroidi	50
11	inzulin	750
\.


--
-- TOC entry 2215 (class 0 OID 0)
-- Dependencies: 198
-- Name: lijekovi_id_seq; Type: SEQUENCE SET; Schema: PersonalizovanaMedicina; Owner: postgres
--

SELECT pg_catalog.setval('lijekovi_id_seq', 11, true);


--
-- TOC entry 2197 (class 0 OID 16414)
-- Dependencies: 189
-- Data for Name: pacijenti; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY pacijenti (id, ime_prezime, spol, datum_rodjenja) FROM stdin;
1	Merisa Golic	Z	1995-04-26
5	Armin Nogo	M	1995-02-05
12	Meho Mehic	M	2017-01-21
\.


--
-- TOC entry 2216 (class 0 OID 0)
-- Dependencies: 199
-- Name: pacijenti_id_seq; Type: SEQUENCE SET; Schema: PersonalizovanaMedicina; Owner: postgres
--

SELECT pg_catalog.setval('pacijenti_id_seq', 12, true);


--
-- TOC entry 2203 (class 0 OID 16474)
-- Dependencies: 195
-- Data for Name: pacijenti_korisnici; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY pacijenti_korisnici (id_pacijenta, id_korisnika) FROM stdin;
1	1
5	2
12	1
\.


--
-- TOC entry 2195 (class 0 OID 16404)
-- Dependencies: 187
-- Data for Name: simptomi; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY simptomi (id, naziv) FROM stdin;
1	temperatura
3	bolno grlo
5	glavobolja
6	umor
2	kasalj
4	upala misica
7	zacepljen nos
8	bol u ledjima
9	bol u prsima
10	depresija
11	vrtoglavica
12	mucnina
13	stucanje
14	visok pritisak
15	nizak pritisak
16	nesanica
17	svrbez
18	osip
19	bol u koljenu
20	gubitak apetita
21	bol u vratu
22	krvarenje iz nosa
23	povracanje
24	bol u uhu
25	zubobolja
26	slabost
27	utrnutost
28	sljepoca
30	gubitak tezine
31	debljanje
32	suhoca usta
33	halucinacije
34	paraliza
35	fobija
36	opadanje kose
37	aritmija
38	zaboravljanje
39	promjena raspolozenja
40	povucenost
41	otezana komunikacija
42	zbunjenost
43	alergija
44	bol u zglobovima
45	anemija
46	osjetljivost na svjetlo
47	pojacano znojenje
48	gubitak svijesti
49	ukocenost
50	otezano kretanje
51	otezano disanje
52	zelucane tegobe
53	prekomjerna zedj
54	pojacana glad
55	ceste infekcije
29	smetnje vida
\.


--
-- TOC entry 2200 (class 0 OID 16429)
-- Dependencies: 192
-- Data for Name: simptomi_dijagnoze; Type: TABLE DATA; Schema: PersonalizovanaMedicina; Owner: postgres
--

COPY simptomi_dijagnoze (id_simptoma, id_dijagnoze) FROM stdin;
1	3
2	3
3	3
4	3
5	3
6	3
7	3
2	4
3	4
7	4
1	12
6	12
38	13
41	13
39	13
42	13
40	13
46	14
6	14
5	14
44	14
45	14
9	14
18	14
36	14
2	12
23	12
47	12
5	12
6	15
49	15
50	15
30	15
4	15
44	15
51	16
9	16
2	16
12	17
23	17
29	17
5	17
50	17
52	18
39	18
4	18
6	18
11	18
27	18
5	18
53	19
6	19
54	19
30	19
29	19
55	19
\.


--
-- TOC entry 2217 (class 0 OID 0)
-- Dependencies: 200
-- Name: simptomi_id_seq; Type: SEQUENCE SET; Schema: PersonalizovanaMedicina; Owner: postgres
--

SELECT pg_catalog.setval('simptomi_id_seq', 55, true);


-- Completed on 2017-09-13 16:54:41

--
-- PostgreSQL database dump complete
--

