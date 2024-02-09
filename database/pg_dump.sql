--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9 (Homebrew)
-- Dumped by pg_dump version 14.9 (Homebrew)

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
-- Name: announces; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.announces (
    id_announces integer NOT NULL,
    id_person integer,
    selling_price double precision,
    status integer,
    validation_date timestamp without time zone,
    date_announces timestamp(6) with time zone,
    description text,
    id_car integer
);


ALTER TABLE public.announces OWNER TO postgres;

--
-- Name: announces_id_announces_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.announces_id_announces_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.announces_id_announces_seq OWNER TO postgres;

--
-- Name: announces_id_announces_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.announces_id_announces_seq OWNED BY public.announces.id_announces;


--
-- Name: announces_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.announces_log (
    date date,
    id_announce integer,
    id_announces_log integer NOT NULL,
    id_person integer,
    status integer NOT NULL
);


ALTER TABLE public.announces_log OWNER TO postgres;

--
-- Name: announces_log_id_announces_log_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.announces_log_id_announces_log_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.announces_log_id_announces_log_seq OWNER TO postgres;

--
-- Name: announces_log_id_announces_log_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.announces_log_id_announces_log_seq OWNED BY public.announces_log.id_announces_log;


--
-- Name: announces_picture; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.announces_picture (
    id_picture integer NOT NULL,
    id_announce integer,
    imagebyte bytea
);


ALTER TABLE public.announces_picture OWNER TO postgres;

--
-- Name: announces_picture_id_picture_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.announces_picture_id_picture_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.announces_picture_id_picture_seq OWNER TO postgres;

--
-- Name: announces_picture_id_picture_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.announces_picture_id_picture_seq OWNED BY public.announces_picture.id_picture;


--
-- Name: brand; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.brand (
    id_brand integer NOT NULL,
    brand character varying(255)
);


ALTER TABLE public.brand OWNER TO postgres;

--
-- Name: brand_id_brand_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.brand_id_brand_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.brand_id_brand_seq OWNER TO postgres;

--
-- Name: brand_id_brand_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.brand_id_brand_seq OWNED BY public.brand.id_brand;


--
-- Name: car; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.car (
    id_car integer NOT NULL,
    id_brand integer,
    id_model integer,
    id_category integer,
    id_motorisation integer,
    id_transmission integer,
    id_version integer,
    id_fuel_type integer,
    registration character varying(100) NOT NULL,
    mile_age numeric NOT NULL
);


ALTER TABLE public.car OWNER TO postgres;

--
-- Name: car_id_car_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.car_id_car_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.car_id_car_seq OWNER TO postgres;

--
-- Name: car_id_car_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.car_id_car_seq OWNED BY public.car.id_car;


--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id_category integer NOT NULL,
    category character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_id_category_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_category_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_category_seq OWNER TO postgres;

--
-- Name: category_id_category_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_category_seq OWNED BY public.category.id_category;


--
-- Name: favorite_announces; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favorite_announces (
    id_announces integer,
    id_favorite integer NOT NULL,
    id_person integer
);


ALTER TABLE public.favorite_announces OWNER TO postgres;

--
-- Name: favorite_announces_id_favorite_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.favorite_announces_id_favorite_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.favorite_announces_id_favorite_seq OWNER TO postgres;

--
-- Name: favorite_announces_id_favorite_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.favorite_announces_id_favorite_seq OWNED BY public.favorite_announces.id_favorite;


--
-- Name: fuel_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fuel_type (
    id_fuel_type integer NOT NULL,
    label character varying(255)
);


ALTER TABLE public.fuel_type OWNER TO postgres;

--
-- Name: fuel_type_id_fuel_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fuel_type_id_fuel_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fuel_type_id_fuel_type_seq OWNER TO postgres;

--
-- Name: fuel_type_id_fuel_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fuel_type_id_fuel_type_seq OWNED BY public.fuel_type.id_fuel_type;


--
-- Name: model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.model (
    id_brand integer,
    id_model integer NOT NULL,
    model character varying(255)
);


ALTER TABLE public.model OWNER TO postgres;

--
-- Name: model_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.model_category (
    id_model_category integer NOT NULL,
    id_model integer,
    id_category integer
);


ALTER TABLE public.model_category OWNER TO postgres;

--
-- Name: model_category_id_model_category_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.model_category_id_model_category_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_category_id_model_category_seq OWNER TO postgres;

--
-- Name: model_category_id_model_category_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.model_category_id_model_category_seq OWNED BY public.model_category.id_model_category;


--
-- Name: model_category_motorisation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.model_category_motorisation (
    id_model_category_motorisation integer NOT NULL,
    id_model_category integer,
    engine_power double precision,
    description character varying(50)
);


ALTER TABLE public.model_category_motorisation OWNER TO postgres;

--
-- Name: model_category_motorisation_id_model_category_motorisation_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.model_category_motorisation_id_model_category_motorisation_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_category_motorisation_id_model_category_motorisation_seq OWNER TO postgres;

--
-- Name: model_category_motorisation_id_model_category_motorisation_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.model_category_motorisation_id_model_category_motorisation_seq OWNED BY public.model_category_motorisation.id_model_category_motorisation;


--
-- Name: model_id_model_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.model_id_model_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_id_model_seq OWNER TO postgres;

--
-- Name: model_id_model_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.model_id_model_seq OWNED BY public.model.id_model;


--
-- Name: motorisation_fuel_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motorisation_fuel_type (
    id_motorisation_fuel_type integer NOT NULL,
    id_motorisation integer,
    id_fuel_type integer
);


ALTER TABLE public.motorisation_fuel_type OWNER TO postgres;

--
-- Name: motorisation_fuel_type_id_motorisation_fuel_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.motorisation_fuel_type_id_motorisation_fuel_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.motorisation_fuel_type_id_motorisation_fuel_type_seq OWNER TO postgres;

--
-- Name: motorisation_fuel_type_id_motorisation_fuel_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.motorisation_fuel_type_id_motorisation_fuel_type_seq OWNED BY public.motorisation_fuel_type.id_motorisation_fuel_type;


--
-- Name: motorisation_transmission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motorisation_transmission (
    id_motorisation_transmission integer NOT NULL,
    id_motorisation integer,
    id_transmission integer
);


ALTER TABLE public.motorisation_transmission OWNER TO postgres;

--
-- Name: motorisation_transmission_id_motorisation_transmission_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.motorisation_transmission_id_motorisation_transmission_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.motorisation_transmission_id_motorisation_transmission_seq OWNER TO postgres;

--
-- Name: motorisation_transmission_id_motorisation_transmission_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.motorisation_transmission_id_motorisation_transmission_seq OWNED BY public.motorisation_transmission.id_motorisation_transmission;


--
-- Name: motorisation_version; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motorisation_version (
    id_motorisation_version integer NOT NULL,
    id_version integer,
    id_motorisation integer,
    details character varying(500)
);


ALTER TABLE public.motorisation_version OWNER TO postgres;

--
-- Name: motorisation_version_id_motorisation_version_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.motorisation_version_id_motorisation_version_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.motorisation_version_id_motorisation_version_seq OWNER TO postgres;

--
-- Name: motorisation_version_id_motorisation_version_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.motorisation_version_id_motorisation_version_seq OWNED BY public.motorisation_version.id_motorisation_version;


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    birth_date date,
    gender smallint,
    id_person integer NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    CONSTRAINT person_gender_check CHECK (((gender >= 0) AND (gender <= 1)))
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_id_person_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_person_seq OWNER TO postgres;

--
-- Name: person_id_person_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_id_person_seq OWNED BY public.person.id_person;


--
-- Name: person_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person_user (
    id_person integer NOT NULL,
    id_person_user integer NOT NULL,
    role smallint,
    email character varying(255),
    password character varying(255),
    CONSTRAINT person_user_role_check CHECK (((role >= 0) AND (role <= 1)))
);


ALTER TABLE public.person_user OWNER TO postgres;

--
-- Name: person_user_id_person_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_user_id_person_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_user_id_person_user_seq OWNER TO postgres;

--
-- Name: person_user_id_person_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_user_id_person_user_seq OWNED BY public.person_user.id_person_user;


--
-- Name: transmission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transmission (
    id_transmission integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.transmission OWNER TO postgres;

--
-- Name: transmission_id_transmission_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transmission_id_transmission_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transmission_id_transmission_seq OWNER TO postgres;

--
-- Name: transmission_id_transmission_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transmission_id_transmission_seq OWNED BY public.transmission.id_transmission;


--
-- Name: v_motorisation_fuel_type; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_motorisation_fuel_type AS
 SELECT mft.id_motorisation_fuel_type,
    mft.id_motorisation,
    ft.id_fuel_type,
    ft.label
   FROM (public.motorisation_fuel_type mft
     JOIN public.fuel_type ft ON ((ft.id_fuel_type = mft.id_fuel_type)));


ALTER TABLE public.v_motorisation_fuel_type OWNER TO postgres;

--
-- Name: v_motorisation_transmission; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_motorisation_transmission AS
 SELECT mt.id_motorisation_transmission,
    mt.id_motorisation,
    t.id_transmission,
    t.name
   FROM (public.motorisation_transmission mt
     JOIN public.transmission t ON ((mt.id_transmission = t.id_transmission)));


ALTER TABLE public.v_motorisation_transmission OWNER TO postgres;

--
-- Name: version; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.version (
    id_version integer NOT NULL,
    intitule character varying(100)
);


ALTER TABLE public.version OWNER TO postgres;

--
-- Name: v_motorisation_version; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_motorisation_version AS
 SELECT mv.id_motorisation_version,
    mv.id_motorisation,
    v.id_version,
    v.intitule,
    mv.details
   FROM (public.motorisation_version mv
     JOIN public.version v ON ((mv.id_version = v.id_version)));


ALTER TABLE public.v_motorisation_version OWNER TO postgres;

--
-- Name: version_id_version_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.version_id_version_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.version_id_version_seq OWNER TO postgres;

--
-- Name: version_id_version_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.version_id_version_seq OWNED BY public.version.id_version;


--
-- Name: announces id_announces; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces ALTER COLUMN id_announces SET DEFAULT nextval('public.announces_id_announces_seq'::regclass);


--
-- Name: announces_log id_announces_log; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces_log ALTER COLUMN id_announces_log SET DEFAULT nextval('public.announces_log_id_announces_log_seq'::regclass);


--
-- Name: announces_picture id_picture; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces_picture ALTER COLUMN id_picture SET DEFAULT nextval('public.announces_picture_id_picture_seq'::regclass);


--
-- Name: brand id_brand; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand ALTER COLUMN id_brand SET DEFAULT nextval('public.brand_id_brand_seq'::regclass);


--
-- Name: car id_car; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car ALTER COLUMN id_car SET DEFAULT nextval('public.car_id_car_seq'::regclass);


--
-- Name: category id_category; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id_category SET DEFAULT nextval('public.category_id_category_seq'::regclass);


--
-- Name: favorite_announces id_favorite; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite_announces ALTER COLUMN id_favorite SET DEFAULT nextval('public.favorite_announces_id_favorite_seq'::regclass);


--
-- Name: fuel_type id_fuel_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fuel_type ALTER COLUMN id_fuel_type SET DEFAULT nextval('public.fuel_type_id_fuel_type_seq'::regclass);


--
-- Name: model id_model; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model ALTER COLUMN id_model SET DEFAULT nextval('public.model_id_model_seq'::regclass);


--
-- Name: model_category id_model_category; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_category ALTER COLUMN id_model_category SET DEFAULT nextval('public.model_category_id_model_category_seq'::regclass);


--
-- Name: model_category_motorisation id_model_category_motorisation; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_category_motorisation ALTER COLUMN id_model_category_motorisation SET DEFAULT nextval('public.model_category_motorisation_id_model_category_motorisation_seq'::regclass);


--
-- Name: motorisation_fuel_type id_motorisation_fuel_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_fuel_type ALTER COLUMN id_motorisation_fuel_type SET DEFAULT nextval('public.motorisation_fuel_type_id_motorisation_fuel_type_seq'::regclass);


--
-- Name: motorisation_transmission id_motorisation_transmission; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_transmission ALTER COLUMN id_motorisation_transmission SET DEFAULT nextval('public.motorisation_transmission_id_motorisation_transmission_seq'::regclass);


--
-- Name: motorisation_version id_motorisation_version; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_version ALTER COLUMN id_motorisation_version SET DEFAULT nextval('public.motorisation_version_id_motorisation_version_seq'::regclass);


--
-- Name: person id_person; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN id_person SET DEFAULT nextval('public.person_id_person_seq'::regclass);


--
-- Name: person_user id_person_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user ALTER COLUMN id_person_user SET DEFAULT nextval('public.person_user_id_person_user_seq'::regclass);


--
-- Name: transmission id_transmission; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transmission ALTER COLUMN id_transmission SET DEFAULT nextval('public.transmission_id_transmission_seq'::regclass);


--
-- Name: version id_version; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.version ALTER COLUMN id_version SET DEFAULT nextval('public.version_id_version_seq'::regclass);



--
-- Name: announces_id_announces_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.announces_id_announces_seq', 1, false);


--
-- Name: announces_log_id_announces_log_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.announces_log_id_announces_log_seq', 1, false);


--
-- Name: announces_picture_id_picture_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.announces_picture_id_picture_seq', 1, false);


--
-- Name: brand_id_brand_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.brand_id_brand_seq', 1, true);


--
-- Name: car_id_car_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.car_id_car_seq', 1, false);


--
-- Name: category_id_category_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_category_seq', 20, true);


--
-- Name: favorite_announces_id_favorite_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.favorite_announces_id_favorite_seq', 1, false);


--
-- Name: fuel_type_id_fuel_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fuel_type_id_fuel_type_seq', 9, true);


--
-- Name: model_category_id_model_category_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_category_id_model_category_seq', 1, false);


--
-- Name: model_category_motorisation_id_model_category_motorisation_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_category_motorisation_id_model_category_motorisation_seq', 1, false);


--
-- Name: model_id_model_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.model_id_model_seq', 2, true);


--
-- Name: motorisation_fuel_type_id_motorisation_fuel_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.motorisation_fuel_type_id_motorisation_fuel_type_seq', 1, false);


--
-- Name: motorisation_transmission_id_motorisation_transmission_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.motorisation_transmission_id_motorisation_transmission_seq', 1, false);


--
-- Name: motorisation_version_id_motorisation_version_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.motorisation_version_id_motorisation_version_seq', 1, false);


--
-- Name: person_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_person_seq', 1, false);


--
-- Name: person_user_id_person_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_user_id_person_user_seq', 1, false);


--
-- Name: transmission_id_transmission_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transmission_id_transmission_seq', 1, false);


--
-- Name: version_id_version_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.version_id_version_seq', 1, false);


--
-- Name: announces_log announces_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces_log
    ADD CONSTRAINT announces_log_pkey PRIMARY KEY (id_announces_log);


--
-- Name: announces_picture announces_picture_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces_picture
    ADD CONSTRAINT announces_picture_pkey PRIMARY KEY (id_picture);


--
-- Name: announces announces_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces
    ADD CONSTRAINT announces_pkey PRIMARY KEY (id_announces);


--
-- Name: brand brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id_brand);


--
-- Name: car car_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (id_car);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id_category);


--
-- Name: favorite_announces favorite_announces_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite_announces
    ADD CONSTRAINT favorite_announces_pkey PRIMARY KEY (id_favorite);


--
-- Name: fuel_type fuel_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fuel_type
    ADD CONSTRAINT fuel_type_pkey PRIMARY KEY (id_fuel_type);


--
-- Name: model_category_motorisation model_category_motorisation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_category_motorisation
    ADD CONSTRAINT model_category_motorisation_pkey PRIMARY KEY (id_model_category_motorisation);


--
-- Name: model_category model_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_category
    ADD CONSTRAINT model_category_pkey PRIMARY KEY (id_model_category);


--
-- Name: model model_id_brand_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_id_brand_key UNIQUE (id_brand);


--
-- Name: model model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pkey PRIMARY KEY (id_model);


--
-- Name: motorisation_fuel_type motorisation_fuel_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_fuel_type
    ADD CONSTRAINT motorisation_fuel_type_pkey PRIMARY KEY (id_motorisation_fuel_type);


--
-- Name: motorisation_transmission motorisation_transmission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_transmission
    ADD CONSTRAINT motorisation_transmission_pkey PRIMARY KEY (id_motorisation_transmission);


--
-- Name: motorisation_version motorisation_version_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_version
    ADD CONSTRAINT motorisation_version_pkey PRIMARY KEY (id_motorisation_version);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);


--
-- Name: person_user person_user_id_person_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT person_user_id_person_key UNIQUE (id_person);


--
-- Name: person_user person_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT person_user_pkey PRIMARY KEY (id_person_user);


--
-- Name: transmission transmission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transmission
    ADD CONSTRAINT transmission_pkey PRIMARY KEY (id_transmission);


--
-- Name: version version_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.version
    ADD CONSTRAINT version_pkey PRIMARY KEY (id_version);


--
-- Name: announces announces_car__fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces
    ADD CONSTRAINT announces_car__fk FOREIGN KEY (id_car) REFERENCES public.car(id_car);


--
-- Name: announces_picture announces_picture_id_announce_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces_picture
    ADD CONSTRAINT announces_picture_id_announce_fkey FOREIGN KEY (id_announce) REFERENCES public.announces(id_announces);


--
-- Name: car car_id_brand_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_brand_fkey FOREIGN KEY (id_brand) REFERENCES public.brand(id_brand);


--
-- Name: car car_id_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.category(id_category);


--
-- Name: car car_id_fuel_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_fuel_type_fkey FOREIGN KEY (id_fuel_type) REFERENCES public.fuel_type(id_fuel_type);


--
-- Name: car car_id_model_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_model_fkey FOREIGN KEY (id_model) REFERENCES public.model(id_model);


--
-- Name: car car_id_motorisation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_motorisation_fkey FOREIGN KEY (id_motorisation) REFERENCES public.model_category_motorisation(id_model_category_motorisation);


--
-- Name: car car_id_transmission_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_transmission_fkey FOREIGN KEY (id_transmission) REFERENCES public.transmission(id_transmission);


--
-- Name: car car_id_version_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_id_version_fkey FOREIGN KEY (id_version) REFERENCES public.version(id_version);


--
-- Name: person_user fk5sc93nyo8ptkbxdvqv8wl506o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person_user
    ADD CONSTRAINT fk5sc93nyo8ptkbxdvqv8wl506o FOREIGN KEY (id_person) REFERENCES public.person(id_person);


--
-- Name: model fkdihu4r5qyrc2dcui706kc9ail; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model
    ADD CONSTRAINT fkdihu4r5qyrc2dcui706kc9ail FOREIGN KEY (id_brand) REFERENCES public.brand(id_brand);


--
-- Name: announces fkgcpnnl40dciibywhwjdcq9pcg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.announces
    ADD CONSTRAINT fkgcpnnl40dciibywhwjdcq9pcg FOREIGN KEY (id_person) REFERENCES public.person(id_person);


--
-- Name: favorite_announces fkhj6lw41poxsjhh20hy1t2yjqf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite_announces
    ADD CONSTRAINT fkhj6lw41poxsjhh20hy1t2yjqf FOREIGN KEY (id_person) REFERENCES public.person(id_person);


--
-- Name: favorite_announces fkkqfjc5nkgnbcpa7899w52mp4g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favorite_announces
    ADD CONSTRAINT fkkqfjc5nkgnbcpa7899w52mp4g FOREIGN KEY (id_announces) REFERENCES public.announces(id_announces);


--
-- Name: model_category model_category_id_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_category
    ADD CONSTRAINT model_category_id_category_fkey FOREIGN KEY (id_category) REFERENCES public.category(id_category);


--
-- Name: model_category model_category_id_model_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_category
    ADD CONSTRAINT model_category_id_model_fkey FOREIGN KEY (id_model) REFERENCES public.model(id_model);


--
-- Name: model_category_motorisation model_category_motorisation_id_model_category_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.model_category_motorisation
    ADD CONSTRAINT model_category_motorisation_id_model_category_fkey FOREIGN KEY (id_model_category) REFERENCES public.model_category(id_model_category);


--
-- Name: motorisation_fuel_type motorisation_fuel_type_id_fuel_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_fuel_type
    ADD CONSTRAINT motorisation_fuel_type_id_fuel_type_fkey FOREIGN KEY (id_fuel_type) REFERENCES public.fuel_type(id_fuel_type);


--
-- Name: motorisation_fuel_type motorisation_fuel_type_id_motorisation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_fuel_type
    ADD CONSTRAINT motorisation_fuel_type_id_motorisation_fkey FOREIGN KEY (id_motorisation) REFERENCES public.model_category_motorisation(id_model_category_motorisation);


--
-- Name: motorisation_transmission motorisation_transmission_id_motorisation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_transmission
    ADD CONSTRAINT motorisation_transmission_id_motorisation_fkey FOREIGN KEY (id_motorisation) REFERENCES public.model_category_motorisation(id_model_category_motorisation);


--
-- Name: motorisation_transmission motorisation_transmission_id_transmission_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_transmission
    ADD CONSTRAINT motorisation_transmission_id_transmission_fkey FOREIGN KEY (id_transmission) REFERENCES public.motorisation_version(id_motorisation_version);


--
-- Name: motorisation_version motorisation_version_id_motorisation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_version
    ADD CONSTRAINT motorisation_version_id_motorisation_fkey FOREIGN KEY (id_motorisation) REFERENCES public.model_category_motorisation(id_model_category_motorisation);


--
-- Name: motorisation_version motorisation_version_id_version_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motorisation_version
    ADD CONSTRAINT motorisation_version_id_version_fkey FOREIGN KEY (id_version) REFERENCES public.version(id_version);


--
-- PostgreSQL database dump complete
--

