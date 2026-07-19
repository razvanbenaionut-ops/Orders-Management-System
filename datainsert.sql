--
-- PostgreSQL database dump
--

\restrict m3LVCh8hwXUBUksqoHQkQtc4LNsWDwprjlgNxEvnMamd6p7AefiFGtg1l8Nv0cy

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

-- Started on 2026-07-19 20:27:45

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5025 (class 0 OID 16501)
-- Dependencies: 220
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client VALUES (1, 'Alex');
INSERT INTO public.client VALUES (4, 'Ioan');
INSERT INTO public.client VALUES (3, 'Bogdan');
INSERT INTO public.client VALUES (11, 'Alex');
INSERT INTO public.client VALUES (12, 'Maria');
INSERT INTO public.client VALUES (13, 'George');
INSERT INTO public.client VALUES (14, 'Ioan');
INSERT INTO public.client VALUES (16, 'Ionut');
INSERT INTO public.client VALUES (15, 'Marcel');
INSERT INTO public.client VALUES (18, 'Tzanca');
INSERT INTO public.client VALUES (23, 'a');


--
-- TOC entry 5031 (class 0 OID 16626)
-- Dependencies: 226
-- Data for Name: log; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.log VALUES (1, 'George', 'mouse', 1, 10, 10);
INSERT INTO public.log VALUES (2, 'Maria', 'CVD', 5, 25.5, 127.5);
INSERT INTO public.log VALUES (3, 'Alex', 'mouse', 7, 10, 70);
INSERT INTO public.log VALUES (4, 'George', 'varza a la Cloj', 2, 7, 14);
INSERT INTO public.log VALUES (5, 'Marcel', 'CVD', 1, 25.5, 25.5);
INSERT INTO public.log VALUES (6, 'Ioan', 'varza a la Cloj', 1, 7, 7);
INSERT INTO public.log VALUES (7, 'George', 'Xerox', 4, 250, 1000);
INSERT INTO public.log VALUES (8, 'Marcel', 'varza a la Cloj', 10, 7, 70);
INSERT INTO public.log VALUES (9, 'Alex', 'CVD', 3, 25.5, 76.5);
INSERT INTO public.log VALUES (10, 'Tzanca', 'iphone 15', 3, 2000, 6000);
INSERT INTO public.log VALUES (11, 'a', 'aa', 11, 11, 121);


--
-- TOC entry 5027 (class 0 OID 16510)
-- Dependencies: 222
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product VALUES (2, 'birou', 100, 7);
INSERT INTO public.product VALUES (3, 'trotineta electrica', 4000, 4);
INSERT INTO public.product VALUES (5, 'laptop', 5505.75, 10);
INSERT INTO public.product VALUES (9, 'minge de fotbal', 35, 8);
INSERT INTO public.product VALUES (1, 'Unchiul Pecos figurina', 30, 15);
INSERT INTO public.product VALUES (8, 'Xerox', 250, 7);
INSERT INTO public.product VALUES (4, 'varza a la Cloj', 7, 12);
INSERT INTO public.product VALUES (7, 'CVD', 25.5, 41);
INSERT INTO public.product VALUES (10, 'iphone 15', 2000, 5);
INSERT INTO public.product VALUES (6, 'mouse', 17, 92);


--
-- TOC entry 5029 (class 0 OID 16591)
-- Dependencies: 224
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders VALUES (1, 1, 1, 120, 4);
INSERT INTO public.orders VALUES (3, 3, 8, 500, 2);
INSERT INTO public.orders VALUES (4, 13, 6, 10, 1);
INSERT INTO public.orders VALUES (6, 11, 6, 70, 7);
INSERT INTO public.orders VALUES (7, 13, 4, 14, 2);
INSERT INTO public.orders VALUES (8, 15, 7, 25.5, 1);
INSERT INTO public.orders VALUES (9, 14, 4, 7, 1);
INSERT INTO public.orders VALUES (10, 13, 8, 1000, 4);
INSERT INTO public.orders VALUES (11, 15, 4, 70, 10);
INSERT INTO public.orders VALUES (12, 1, 7, 76.5, 3);
INSERT INTO public.orders VALUES (13, 18, 10, 6000, 3);


--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 219
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 24, true);


--
-- TOC entry 5038 (class 0 OID 0)
-- Dependencies: 225
-- Name: log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.log_id_seq', 11, true);


--
-- TOC entry 5039 (class 0 OID 0)
-- Dependencies: 223
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 14, true);


--
-- TOC entry 5040 (class 0 OID 0)
-- Dependencies: 221
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 15, true);


-- Completed on 2026-07-19 20:27:45

--
-- PostgreSQL database dump complete
--

\unrestrict m3LVCh8hwXUBUksqoHQkQtc4LNsWDwprjlgNxEvnMamd6p7AefiFGtg1l8Nv0cy

