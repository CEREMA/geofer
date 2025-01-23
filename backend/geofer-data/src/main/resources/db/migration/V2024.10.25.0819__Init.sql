--
-- TOC entry 1910 (class 1247 OID 586473)
-- Name: annee_arret_train; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE annee_arret_train AS ENUM (
    'Entre 2010 et 2014',
    'Avant 2000',
    'Je ne sais pas',
    'Entre 2005 et 2010',
    'Entre 2000 et 2005'
);



--
-- TOC entry 1913 (class 1247 OID 586484)
-- Name: etat_ite; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE etat_ite AS ENUM (
    'Inutilisable',
    'Utilisée',
    'Bon',
    'Mauvais',
    'Neuf'
);



--
-- TOC entry 1916 (class 1247 OID 586496)
-- Name: evolution_ite; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE evolution_ite AS ENUM (
    'A la hausse',
    'De façon stable',
    'A la baisse'
);



--
-- TOC entry 1919 (class 1247 OID 586504)
-- Name: flux; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE flux AS ENUM (
    'recue',
    'expediee'
);



--
-- TOC entry 1922 (class 1247 OID 586510)
-- Name: frequence_circulation_train; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE frequence_circulation_train AS ENUM (
    'Un train par semaine',
    'Un train par mois',
    'Plus d''un train par jour',
    'Moins d''un train par semaine mais plus d''un train par mois',
    'Moins d''un train par mois mais plus d''un train par an',
    'Un train par jour',
    'Moins d''un train par jour mais plus d''un train par semaine',
    'Moins d''un train par an ou juste un train par an'
);



--
-- TOC entry 1925 (class 1247 OID 586528)
-- Name: loco_tracteur; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE loco_tracteur AS ENUM (
    'Aucun',
    '1',
    '2',
    'Plus de 2'
);



--
-- TOC entry 2033 (class 1247 OID 638528)
-- Name: obj_type; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE obj_type AS ENUM (
    'TABLE',
    'VIEW',
    'COLUMN',
    'SEQUENCE',
    'FUNCTION',
    'SCHEMA',
    'DATABASE'
);



--
-- TOC entry 2036 (class 1247 OID 638544)
-- Name: perm_type; Type: TYPE; Schema: geofer; Owner: postgres
--

CREATE TYPE perm_type AS ENUM (
    'SELECT',
    'INSERT',
    'UPDATE',
    'DELETE',
    'TRUNCATE',
    'REFERENCES',
    'TRIGGER',
    'USAGE',
    'CREATE',
    'EXECUTE',
    'CONNECT',
    'TEMPORARY'
);



SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 343 (class 1259 OID 589718)
-- Name: communes; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE communes (
    id integer NOT NULL,
    regrgp_nom character varying(255),
    reg_nom character varying(255),
    reg_nom_old character varying(255),
    aca_nom character varying(255),
    dep_nom character varying(255),
    com_code character varying(10),
    com_code1 character varying(10),
    com_code2 character varying(10),
    com_id character varying(10),
    com_nom_maj_court character varying(255),
    com_nom_maj character varying(255),
    com_nom character varying(255),
    uu_code character varying(10),
    uu_id character varying(10),
    uucr_id character varying(10),
    uucr_nom character varying(255),
    uu_wikidata character varying(255),
    uu_paysage character varying(255),
    ze_id character varying(10),
    dep_code character varying(10),
    dep_id character varying(10),
    dep_nom_num character varying(255),
    dep_num_nom character varying(255),
    dep_wikidata character varying(255),
    dep_paysage character varying(255),
    nuts_code_3 character varying(10),
    aca_code character varying(10),
    aca_id character varying(10),
    aca_wikidata character varying(255),
    aca_paysage character varying(255),
    reg_code character varying(10),
    reg_id character varying(10),
    reg_wikidata character varying(255),
    nuts_code_1 character varying(10),
    reg_paysage character varying(255),
    reg_code_old character varying(10),
    reg_id_old character varying(10),
    fd_id character varying(10),
    fr_id character varying(10),
    fe_id character varying(10),
    uu_id_10 character varying(10),
    uu_id_99 character varying(10),
    au_code character varying(10),
    au_id character varying(10),
    auc_id character varying(10),
    auc_nom character varying(255),
    epci_id character varying(10),
    epci_nom character varying(255),
    atlas_id character varying(10),
    atlas_nom character varying(255),
    atlas_wikidata character varying(255),
    atlas_paysage character varying(255),
    com_nom_debut date,
    com_nom_fin date,
    com_nom_anciens text,
    com_nom_anciens_dates text,
    com_code_actuel character varying(10),
    com_code_actuel_depuis date,
    geometry public.geometry(Point,4326),
    geom public.geometry
);



--
-- TOC entry 342 (class 1259 OID 589717)
-- Name: communes_id_seq; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE communes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 4851 (class 0 OID 0)
-- Dependencies: 342
-- Name: communes_id_seq; Type: SEQUENCE OWNED BY; Schema: geofer; Owner: postgres
--

ALTER SEQUENCE communes_id_seq OWNED BY communes.id;


--
-- TOC entry 354 (class 1259 OID 589790)
-- Name: departements; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE departements (
    id integer NOT NULL,
    code character varying(10) NOT NULL,
    nom character varying(255) NOT NULL,
    geom public.geometry(MultiPolygon,4326) NOT NULL
);



--
-- TOC entry 323 (class 1259 OID 586771)
-- Name: seq_donnee_arret_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_donnee_arret_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 301 (class 1259 OID 586537)
-- Name: donnee_arret; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE donnee_arret (
    id integer DEFAULT nextval('seq_donnee_arret_id'::regclass) NOT NULL,
    annee integer NOT NULL,
    ref_type_arret_id integer NOT NULL,
    nombre_arret integer,
    gare_id integer NOT NULL
);



--
-- TOC entry 324 (class 1259 OID 586772)
-- Name: seq_donnee_isochrone_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_donnee_isochrone_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 302 (class 1259 OID 586540)
-- Name: donnee_isochrone; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE donnee_isochrone (
    id integer DEFAULT nextval('seq_donnee_isochrone_id'::regclass) NOT NULL,
    ref_type_donnee_id integer NOT NULL,
    annee integer NOT NULL,
    nombre integer,
    isochrone_id integer NOT NULL
);



--
-- TOC entry 341 (class 1259 OID 586789)
-- Name: seq_donnee_voyageur_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_donnee_voyageur_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 322 (class 1259 OID 586632)
-- Name: donnee_voyageur; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE donnee_voyageur (
    id integer DEFAULT nextval('seq_donnee_voyageur_id'::regclass) NOT NULL,
    annee integer NOT NULL,
    nombre_voyageur integer,
    gare_id integer NOT NULL
);



--
-- TOC entry 355 (class 1259 OID 589896)
-- Name: dpt_regions; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE dpt_regions (
    department_code text NOT NULL,
    department_name text,
    region_code text,
    region_name text
);



--
-- TOC entry 325 (class 1259 OID 586773)
-- Name: seq_etablissement_scolaire_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_etablissement_scolaire_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 303 (class 1259 OID 586543)
-- Name: etablissement_scolaire; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE etablissement_scolaire (
    id integer DEFAULT nextval('seq_etablissement_scolaire_id'::regclass) NOT NULL,
    nom_etablissement character varying,
    annee integer,
    nb_eleve integer,
    ref_type_etablissement_id integer NOT NULL,
    the_geom public.geometry(Point,4326)
);



--
-- TOC entry 326 (class 1259 OID 586774)
-- Name: seq_gare_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_gare_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 304 (class 1259 OID 586548)
-- Name: gare; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE gare (
    id integer DEFAULT nextval('seq_gare_id'::regclass) NOT NULL,
    nom_commune character varying,
    insee_commune character varying(5),
    insee_departement character varying(2),
    code_uic integer,
    nom_gare character varying,
    si_ouverte boolean NOT NULL,
    nom_aom character varying,
    si_automates_tgv_intercites boolean,
    si_automates_ter boolean,
    si_poste_vente_guichet boolean,
    si_libre_service_assiste boolean,
    the_geom public.geometry(Point,4326),
    CONSTRAINT chk_code_uic_longueur CHECK (((code_uic)::text ~ similar_escape('[0-9]{8}'::text, NULL::text)))
);



--
-- TOC entry 327 (class 1259 OID 586775)
-- Name: seq_installation_term_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_installation_term_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 305 (class 1259 OID 586554)
-- Name: installation_term; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE installation_term (
    id integer DEFAULT nextval('seq_installation_term_id'::regclass) NOT NULL,
    code_ite integer NOT NULL,
    date_enquete date,
    raison_sociale character varying,
    ref_activite_principale_id integer,
    entreprise_ferroviaire character varying,
    etat_ite etat_ite,
    si_utilisee boolean,
    annee_arret_train annee_arret_train,
    si_reutilisation_prevue boolean,
    nombre_voies integer,
    longueur_voie integer,
    si_regime_train_entier boolean,
    si_regime_lotissement boolean,
    si_train_entier_possible boolean,
    loco_tracteur_rail loco_tracteur,
    loco_tracteur_rail_route loco_tracteur,
    frequence_circulation_train frequence_circulation_train,
    evolution_depuis_2010 evolution_ite,
    evolution_future evolution_ite,
    the_geom public.geometry(Point,4326)
);



--
-- TOC entry 4852 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.ref_activite_principale_id; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.ref_activite_principale_id IS 'référence à la table ref_activite_principale';


--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.etat_ite; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.etat_ite IS 'Enum';


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.annee_arret_train; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.annee_arret_train IS 'Enum';


--
-- TOC entry 4855 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.si_regime_train_entier; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.si_regime_train_entier IS 'true = train entier utilisé';


--
-- TOC entry 4856 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.si_regime_lotissement; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.si_regime_lotissement IS 'true = lotissement utilisé';


--
-- TOC entry 4857 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.si_train_entier_possible; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.si_train_entier_possible IS 'true= train entier possible, false= train entier impossible, null= ne sais pas';


--
-- TOC entry 4858 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.loco_tracteur_rail; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.loco_tracteur_rail IS 'Enum';


--
-- TOC entry 4859 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.loco_tracteur_rail_route; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.loco_tracteur_rail_route IS 'Enum';


--
-- TOC entry 4860 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.frequence_circulation_train; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.frequence_circulation_train IS 'Enum';


--
-- TOC entry 4861 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.evolution_depuis_2010; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.evolution_depuis_2010 IS 'Enum';


--
-- TOC entry 4862 (class 0 OID 0)
-- Dependencies: 305
-- Name: COLUMN installation_term.evolution_future; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term.evolution_future IS 'Enum';


--
-- TOC entry 328 (class 1259 OID 586776)
-- Name: seq_installation_term_ref_marchandise_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_installation_term_ref_marchandise_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 306 (class 1259 OID 586559)
-- Name: installation_term_ref_marchandise; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE installation_term_ref_marchandise (
    id integer DEFAULT nextval('seq_installation_term_ref_marchandise_id'::regclass) NOT NULL,
    installation_term_id integer,
    ref_marchandise_id integer,
    si_par_train boolean NOT NULL,
    flux flux NOT NULL
);



--
-- TOC entry 4863 (class 0 OID 0)
-- Dependencies: 306
-- Name: TABLE installation_term_ref_marchandise; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON TABLE installation_term_ref_marchandise IS 'Table de liaison entre installation_terminale_embranchee et ref_marchandise.
En ignorant ''si_par train'', on a les marchandises expédiées ou reçues tous modes confondus
En filtrant ''si_par_train'', on a les marchandises expédiées ou reçues par train uniquement';


--
-- TOC entry 4864 (class 0 OID 0)
-- Dependencies: 306
-- Name: COLUMN installation_term_ref_marchandise.si_par_train; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term_ref_marchandise.si_par_train IS 'si true le train fait partie des modes de transport';


--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 306
-- Name: COLUMN installation_term_ref_marchandise.flux; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON COLUMN installation_term_ref_marchandise.flux IS 'enum recue/expediee';


--
-- TOC entry 307 (class 1259 OID 586562)
-- Name: installation_term_ref_type_site; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE installation_term_ref_type_site (
    installation_term_id integer NOT NULL,
    ref_type_site_id integer NOT NULL
);



--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 307
-- Name: TABLE installation_term_ref_type_site; Type: COMMENT; Schema: geofer; Owner: postgres
--

COMMENT ON TABLE installation_term_ref_type_site IS 'Table de liaison entre les installation_terminale_embranchee et ref_type_site';


--
-- TOC entry 329 (class 1259 OID 586777)
-- Name: seq_isochrone_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_isochrone_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 308 (class 1259 OID 586565)
-- Name: isochrone; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE isochrone (
    id integer DEFAULT nextval('seq_isochrone_id'::regclass) NOT NULL,
    ref_type_isochrone_id integer NOT NULL,
    gare_id integer NOT NULL,
    the_geom public.geometry(Polygon,4326)
);



--
-- TOC entry 330 (class 1259 OID 586778)
-- Name: seq_ligne_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ligne_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 309 (class 1259 OID 586570)
-- Name: ligne; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ligne (
    id integer DEFAULT nextval('seq_ligne_id'::regclass) NOT NULL,
    code_ligne character varying,
    nom_ligne character varying
);



--
-- TOC entry 310 (class 1259 OID 586575)
-- Name: ligne_gare; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ligne_gare (
    ligne_id integer NOT NULL,
    gare_id integer NOT NULL
);



--
-- TOC entry 311 (class 1259 OID 586578)
-- Name: ligne_reseau; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ligne_reseau (
    ligne_id integer NOT NULL,
    reseau_id integer NOT NULL
);



--
-- TOC entry 331 (class 1259 OID 586779)
-- Name: seq_passage_niveau_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_passage_niveau_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 312 (class 1259 OID 586581)
-- Name: passage_niveau; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE passage_niveau (
    id integer DEFAULT nextval('seq_passage_niveau_id'::regclass) NOT NULL,
    id_pn bigint,
    libelle character varying,
    obstacle character varying,
    code_ligne character varying,
    ref_classe_passage_niveau_id integer,
    the_geom public.geometry(Point,4326),
    CONSTRAINT chk_id_pn_longueur CHECK (((id_pn)::text ~ similar_escape('[0-9]{11}'::text, NULL::text)))
);



--
-- TOC entry 300 (class 1259 OID 586456)
-- Name: profil; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE profil (
    id bigint NOT NULL,
    utilisateurid bigint NOT NULL,
    role_id integer
);



--
-- TOC entry 299 (class 1259 OID 586455)
-- Name: profil_id_seq; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE profil_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 4867 (class 0 OID 0)
-- Dependencies: 299
-- Name: profil_id_seq; Type: SEQUENCE OWNED BY; Schema: geofer; Owner: postgres
--

ALTER SEQUENCE profil_id_seq OWNED BY profil.id;


--
-- TOC entry 332 (class 1259 OID 586780)
-- Name: seq_ref_activite_principale_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_activite_principale_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 313 (class 1259 OID 586587)
-- Name: ref_activite_principale; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_activite_principale (
    id integer DEFAULT nextval('seq_ref_activite_principale_id'::regclass) NOT NULL,
    code_naf character varying NOT NULL,
    libelle character varying NOT NULL
);



--
-- TOC entry 333 (class 1259 OID 586781)
-- Name: seq_ref_classe_passage_niveau_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_classe_passage_niveau_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 314 (class 1259 OID 586592)
-- Name: ref_classe_passage_niveau; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_classe_passage_niveau (
    id integer DEFAULT nextval('seq_ref_classe_passage_niveau_id'::regclass) NOT NULL,
    classe character varying NOT NULL,
    definition character varying NOT NULL
);



--
-- TOC entry 334 (class 1259 OID 586782)
-- Name: seq_ref_marchandise_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_marchandise_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 315 (class 1259 OID 586597)
-- Name: ref_marchandise; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_marchandise (
    id integer DEFAULT nextval('seq_ref_marchandise_id'::regclass) NOT NULL,
    code_nst character varying NOT NULL,
    libelle character varying NOT NULL
);



--
-- TOC entry 335 (class 1259 OID 586783)
-- Name: seq_ref_type_arret_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_type_arret_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 316 (class 1259 OID 586602)
-- Name: ref_type_arret; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_type_arret (
    id integer DEFAULT nextval('seq_ref_type_arret_id'::regclass) NOT NULL,
    libelle character varying NOT NULL
);



--
-- TOC entry 336 (class 1259 OID 586784)
-- Name: seq_ref_type_donnee_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_type_donnee_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 317 (class 1259 OID 586607)
-- Name: ref_type_donnee; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_type_donnee (
    id integer DEFAULT nextval('seq_ref_type_donnee_id'::regclass) NOT NULL,
    groupe_donnee character varying NOT NULL,
    type_donnee character varying NOT NULL
);



--
-- TOC entry 337 (class 1259 OID 586785)
-- Name: seq_ref_type_etablissement_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_type_etablissement_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 318 (class 1259 OID 586612)
-- Name: ref_type_etablissement; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_type_etablissement (
    id integer DEFAULT nextval('seq_ref_type_etablissement_id'::regclass) NOT NULL,
    libelle character varying NOT NULL
);



--
-- TOC entry 338 (class 1259 OID 586786)
-- Name: seq_ref_type_isochrone_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_type_isochrone_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 319 (class 1259 OID 586617)
-- Name: ref_type_isochrone; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_type_isochrone (
    id integer DEFAULT nextval('seq_ref_type_isochrone_id'::regclass) NOT NULL,
    libelle character varying NOT NULL
);



--
-- TOC entry 339 (class 1259 OID 586787)
-- Name: seq_ref_type_site_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_ref_type_site_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 320 (class 1259 OID 586622)
-- Name: ref_type_site; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE ref_type_site (
    id integer DEFAULT nextval('seq_ref_type_site_id'::regclass) NOT NULL,
    libelle character varying NOT NULL
);



--
-- TOC entry 353 (class 1259 OID 589768)
-- Name: regions; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE regions (
    id integer NOT NULL,
    code character varying(10) NOT NULL,
    nom character varying(255) NOT NULL,
    geom public.geometry(MultiPolygon,4326) NOT NULL
);



--
-- TOC entry 340 (class 1259 OID 586788)
-- Name: seq_reseau_ferroviaire_id; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE seq_reseau_ferroviaire_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 321 (class 1259 OID 586627)
-- Name: reseau_ferroviaire; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE reseau_ferroviaire (
    id integer DEFAULT nextval('seq_reseau_ferroviaire_id'::regclass) NOT NULL,
    code_ligne character varying,
    rg_troncon character varying,
    pk_debut_r character varying,
    pk_fin_r character varying,
    mnemo character varying,
    infrastructure character varying,
    id_typologie_petite_ligne integer,
    retenue_petite_ligne character varying,
    the_geom public.geometry(MultiLineString,4326)
);



--
-- TOC entry 296 (class 1259 OID 586440)
-- Name: roles; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE roles (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);



--
-- TOC entry 295 (class 1259 OID 586439)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 4868 (class 0 OID 0)
-- Dependencies: 295
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: geofer; Owner: postgres
--

ALTER SEQUENCE roles_id_seq OWNED BY roles.id;


--
-- TOC entry 298 (class 1259 OID 586447)
-- Name: users; Type: TABLE; Schema: geofer; Owner: postgres
--

CREATE TABLE users (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    orionid character varying(255)
);



--
-- TOC entry 297 (class 1259 OID 586446)
-- Name: users_id_seq; Type: SEQUENCE; Schema: geofer; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 4869 (class 0 OID 0)
-- Dependencies: 297
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: geofer; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 351 (class 1259 OID 589760)
-- Name: v_voyageur_par_annee; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_voyageur_par_annee AS
 SELECT donnee_voyageur.annee,
    donnee_voyageur.gare_id,
    sum(donnee_voyageur.nombre_voyageur) AS total_nombre_voyageur
   FROM donnee_voyageur
  GROUP BY donnee_voyageur.annee, donnee_voyageur.gare_id
  ORDER BY donnee_voyageur.annee;



--
-- TOC entry 352 (class 1259 OID 589764)
-- Name: v_aggregate_voyageur; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_aggregate_voyageur AS
 SELECT v_voyageur_par_annee.annee,
    v_voyageur_par_annee.gare_id,
    sum(v_voyageur_par_annee.total_nombre_voyageur) AS total_par_annee
   FROM v_voyageur_par_annee
  GROUP BY v_voyageur_par_annee.annee, v_voyageur_par_annee.gare_id
  ORDER BY v_voyageur_par_annee.annee;



--
-- TOC entry 346 (class 1259 OID 589735)
-- Name: v_csvlist; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_csvlist AS
 SELECT gare.code_uic,
    gare.id AS gare_id,
    gare.insee_commune,
    gare.insee_departement,
    gare.nom_aom,
    gare.nom_commune,
    gare.nom_gare,
    gare.si_automates_ter,
    gare.si_automates_tgv_intercites,
    gare.si_libre_service_assiste,
    gare.si_ouverte,
    gare.si_poste_vente_guichet,
    donnee_arret.annee,
    donnee_arret.nombre_arret,
    donnee_arret.ref_type_arret_id
   FROM (gare
     LEFT JOIN donnee_arret ON ((donnee_arret.gare_id = gare.id)));



--
-- TOC entry 347 (class 1259 OID 589740)
-- Name: v_data_arret; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_data_arret AS
 SELECT donnee_arret.annee,
    ref_type_arret.id AS ref_type_arret,
    ref_type_arret.libelle,
    donnee_arret.nombre_arret,
    donnee_arret.gare_id,
    donnee_arret.id,
    gare.nom_gare
   FROM ((donnee_arret
     JOIN ref_type_arret ON ((ref_type_arret.id = donnee_arret.ref_type_arret_id)))
     JOIN gare ON ((gare.id = donnee_arret.gare_id)))
  ORDER BY donnee_arret.annee, ref_type_arret.libelle, donnee_arret.nombre_arret DESC;



--
-- TOC entry 348 (class 1259 OID 589745)
-- Name: v_donnee_voyageur; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_donnee_voyageur AS
 SELECT gare.id AS gare_id,
    gare.nom_gare,
    donnee_voyageur.annee,
    donnee_voyageur.nombre_voyageur,
    gare.si_ouverte
   FROM (donnee_voyageur
     JOIN gare ON ((donnee_voyageur.gare_id = gare.id)))
  WHERE (donnee_voyageur.annee >= ( SELECT max(donnee_voyageur_1.annee) AS max
           FROM donnee_voyageur donnee_voyageur_1));



--
-- TOC entry 350 (class 1259 OID 589755)
-- Name: v_gare_voyageur; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_gare_voyageur AS
SELECT
    NULL::character varying AS nom_gare,
    NULL::text AS statut_gare,
    NULL::bigint AS nombre_arrets_total,
    NULL::character varying AS nom_aom,
    NULL::integer AS annee,
    NULL::integer AS voyageurs,
    NULL::integer AS gare_id;



--
-- TOC entry 344 (class 1259 OID 589726)
-- Name: v_grid; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_grid AS
 SELECT g.nom_gare,
    sum(da.nombre_arret) AS total_nombre_arret,
    g.nom_aom,
    dv.id AS voyageur_id,
    dv.annee,
    dv.nombre_voyageur
   FROM ((gare g
     JOIN donnee_arret da ON ((da.gare_id = g.id)))
     JOIN ( SELECT DISTINCT ON (donnee_voyageur.gare_id) donnee_voyageur.id,
            donnee_voyageur.annee,
            donnee_voyageur.nombre_voyageur,
            donnee_voyageur.gare_id
           FROM donnee_voyageur
          WHERE (donnee_voyageur.gare_id = ANY (ARRAY[(4)::bigint, (5)::bigint]))
          ORDER BY donnee_voyageur.gare_id, donnee_voyageur.annee DESC) dv ON ((dv.gare_id = g.id)))
  GROUP BY g.nom_gare, g.nom_aom, dv.id, dv.annee, dv.nombre_voyageur;



--
-- TOC entry 349 (class 1259 OID 589750)
-- Name: v_isochrone; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_isochrone AS
 SELECT isochrone.gare_id,
    donnee_isochrone.nombre,
        CASE
            WHEN (donnee_isochrone.ref_type_donnee_id = 1) THEN 'etablissementsDeSante'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 2) THEN 'etablissementsDeSport'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 3) THEN 'commerces'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 4) THEN 'chambresHotelEtEmplacementsCamping'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 5) THEN 'etablissementsDeLoisir'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 6) THEN 'salaries'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 7) THEN 'restaurants'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 8) THEN 'habitants'::text
            WHEN (donnee_isochrone.ref_type_donnee_id = 9) THEN 'eleves'::text
            ELSE 'inconnu'::text
        END AS type_donnee_camelcase,
        CASE
            WHEN (isochrone.ref_type_isochrone_id = 1) THEN concat(
            CASE
                WHEN (donnee_isochrone.ref_type_donnee_id = 1) THEN 'sante15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 2) THEN 'sport15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 3) THEN 'commerces15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 4) THEN 'hotelsEtCampings15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 5) THEN 'loisirs15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 6) THEN 'salaries15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 7) THEN 'restaurants15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 8) THEN 'habitants15MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 9) THEN 'eleves15MinAPied2022'::text
                ELSE 'inconnu15MinAPied2022'::text
            END)
            WHEN (isochrone.ref_type_isochrone_id = 2) THEN concat(
            CASE
                WHEN (donnee_isochrone.ref_type_donnee_id = 1) THEN 'sante30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 2) THEN 'sport30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 3) THEN 'commerces30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 4) THEN 'hotelsEtCampings30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 5) THEN 'loisirs30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 6) THEN 'salaries30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 7) THEN 'restaurants30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 8) THEN 'habitants30MinAPied2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 9) THEN 'eleves30MinAPied2022'::text
                ELSE 'inconnu30MinAPied2022'::text
            END)
            WHEN (isochrone.ref_type_isochrone_id = 3) THEN concat(
            CASE
                WHEN (donnee_isochrone.ref_type_donnee_id = 1) THEN 'sante10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 2) THEN 'sport10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 3) THEN 'commerces10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 4) THEN 'hotelsEtCampings10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 5) THEN 'loisirs10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 6) THEN 'salaries10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 7) THEN 'restaurants10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 8) THEN 'habitants10MinEnVoiture2022'::text
                WHEN (donnee_isochrone.ref_type_donnee_id = 9) THEN 'eleves10MinEnVoiture2022'::text
                ELSE 'inconnu10MinEnVoiture2022'::text
            END)
            ELSE 'inconnu'::text
        END AS nommage_camelcase
   FROM (donnee_isochrone
     JOIN isochrone ON ((isochrone.id = donnee_isochrone.isochrone_id)));



--
-- TOC entry 345 (class 1259 OID 589731)
-- Name: v_stat; Type: VIEW; Schema: geofer; Owner: postgres
--

CREATE VIEW v_stat AS
 SELECT ref_type_donnee.type_donnee,
    donnee_isochrone.annee,
    donnee_isochrone.nombre,
    isochrone.gare_id,
    ref_type_donnee.groupe_donnee,
    ref_type_isochrone.libelle
   FROM (((donnee_isochrone
     JOIN isochrone ON ((isochrone.id = donnee_isochrone.isochrone_id)))
     JOIN ref_type_isochrone ON ((isochrone.ref_type_isochrone_id = ref_type_isochrone.id)))
     JOIN ref_type_donnee ON ((donnee_isochrone.ref_type_donnee_id = ref_type_donnee.id)));



--
-- TOC entry 4586 (class 2604 OID 589721)
-- Name: communes id; Type: DEFAULT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY communes ALTER COLUMN id SET DEFAULT nextval('communes_id_seq'::regclass);


--
-- TOC entry 4566 (class 2604 OID 586459)
-- Name: profil id; Type: DEFAULT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY profil ALTER COLUMN id SET DEFAULT nextval('profil_id_seq'::regclass);


--
-- TOC entry 4564 (class 2604 OID 586443)
-- Name: roles id; Type: DEFAULT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY roles ALTER COLUMN id SET DEFAULT nextval('roles_id_seq'::regclass);


--
-- TOC entry 4565 (class 2604 OID 586450)
-- Name: users id; Type: DEFAULT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 4645 (class 2606 OID 589725)
-- Name: communes communes_pkey; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY communes
    ADD CONSTRAINT communes_pkey PRIMARY KEY (id);


--
-- TOC entry 4649 (class 2606 OID 589796)
-- Name: departements departements_pkey; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY departements
    ADD CONSTRAINT departements_pkey PRIMARY KEY (id);


--
-- TOC entry 4651 (class 2606 OID 589902)
-- Name: dpt_regions dpt_regions_pkey; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY dpt_regions
    ADD CONSTRAINT dpt_regions_pkey PRIMARY KEY (department_code);

--
-- TOC entry 4599 (class 2606 OID 586636)
-- Name: donnee_arret pk_donnee_arret; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_arret
    ADD CONSTRAINT pk_donnee_arret PRIMARY KEY (id);


--
-- TOC entry 4601 (class 2606 OID 586638)
-- Name: donnee_isochrone pk_donnee_isochrone; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_isochrone
    ADD CONSTRAINT pk_donnee_isochrone PRIMARY KEY (id);


--
-- TOC entry 4643 (class 2606 OID 586678)
-- Name: donnee_voyageur pk_donnee_voyageur; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_voyageur
    ADD CONSTRAINT pk_donnee_voyageur PRIMARY KEY (id);


--
-- TOC entry 4603 (class 2606 OID 586640)
-- Name: etablissement_scolaire pk_etablissement_scolaire; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY etablissement_scolaire
    ADD CONSTRAINT pk_etablissement_scolaire PRIMARY KEY (id);


--
-- TOC entry 4605 (class 2606 OID 586642)
-- Name: gare pk_gare; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY gare
    ADD CONSTRAINT pk_gare PRIMARY KEY (id);


--
-- TOC entry 4607 (class 2606 OID 586644)
-- Name: installation_term pk_installation_term; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term
    ADD CONSTRAINT pk_installation_term PRIMARY KEY (id);


--
-- TOC entry 4611 (class 2606 OID 586646)
-- Name: installation_term_ref_marchandise pk_installation_term_ref_marchandise; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term_ref_marchandise
    ADD CONSTRAINT pk_installation_term_ref_marchandise PRIMARY KEY (id);


--
-- TOC entry 4613 (class 2606 OID 586648)
-- Name: installation_term_ref_type_site pk_installation_term_ref_type_site; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term_ref_type_site
    ADD CONSTRAINT pk_installation_term_ref_type_site PRIMARY KEY (installation_term_id, ref_type_site_id);


--
-- TOC entry 4615 (class 2606 OID 586650)
-- Name: isochrone pk_isochrone; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY isochrone
    ADD CONSTRAINT pk_isochrone PRIMARY KEY (id);


--
-- TOC entry 4617 (class 2606 OID 586652)
-- Name: ligne pk_ligne; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ligne
    ADD CONSTRAINT pk_ligne PRIMARY KEY (id);


--
-- TOC entry 4619 (class 2606 OID 586654)
-- Name: ligne_gare pk_ligne_gare; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ligne_gare
    ADD CONSTRAINT pk_ligne_gare PRIMARY KEY (ligne_id, gare_id);


--
-- TOC entry 4621 (class 2606 OID 586656)
-- Name: ligne_reseau pk_ligne_reseau; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ligne_reseau
    ADD CONSTRAINT pk_ligne_reseau PRIMARY KEY (ligne_id, reseau_id);


--
-- TOC entry 4623 (class 2606 OID 586658)
-- Name: passage_niveau pk_passage_niveau; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY passage_niveau
    ADD CONSTRAINT pk_passage_niveau PRIMARY KEY (id);


--
-- TOC entry 4625 (class 2606 OID 586660)
-- Name: ref_activite_principale pk_ref_activite_principale; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_activite_principale
    ADD CONSTRAINT pk_ref_activite_principale PRIMARY KEY (id);


--
-- TOC entry 4627 (class 2606 OID 586662)
-- Name: ref_classe_passage_niveau pk_ref_classe_passage_niveau; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_classe_passage_niveau
    ADD CONSTRAINT pk_ref_classe_passage_niveau PRIMARY KEY (id);


--
-- TOC entry 4629 (class 2606 OID 586664)
-- Name: ref_marchandise pk_ref_marchandise; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_marchandise
    ADD CONSTRAINT pk_ref_marchandise PRIMARY KEY (id);


--
-- TOC entry 4631 (class 2606 OID 586666)
-- Name: ref_type_arret pk_ref_type_arret; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_type_arret
    ADD CONSTRAINT pk_ref_type_arret PRIMARY KEY (id);


--
-- TOC entry 4633 (class 2606 OID 586668)
-- Name: ref_type_donnee pk_ref_type_donnee; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_type_donnee
    ADD CONSTRAINT pk_ref_type_donnee PRIMARY KEY (id);


--
-- TOC entry 4635 (class 2606 OID 586670)
-- Name: ref_type_etablissement pk_ref_type_etablissement; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_type_etablissement
    ADD CONSTRAINT pk_ref_type_etablissement PRIMARY KEY (id);


--
-- TOC entry 4637 (class 2606 OID 586672)
-- Name: ref_type_isochrone pk_ref_type_isochrone; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_type_isochrone
    ADD CONSTRAINT pk_ref_type_isochrone PRIMARY KEY (id);


--
-- TOC entry 4639 (class 2606 OID 586674)
-- Name: ref_type_site pk_ref_type_site; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ref_type_site
    ADD CONSTRAINT pk_ref_type_site PRIMARY KEY (id);


--
-- TOC entry 4641 (class 2606 OID 586676)
-- Name: reseau_ferroviaire pk_reseau_ferroviaire; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY reseau_ferroviaire
    ADD CONSTRAINT pk_reseau_ferroviaire PRIMARY KEY (id);


--
-- TOC entry 4597 (class 2606 OID 586461)
-- Name: profil profil_pkey; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY profil
    ADD CONSTRAINT profil_pkey PRIMARY KEY (id);


--
-- TOC entry 4647 (class 2606 OID 589774)
-- Name: regions regions_pkey; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY regions
    ADD CONSTRAINT regions_pkey PRIMARY KEY (id);


--
-- TOC entry 4593 (class 2606 OID 586445)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 4609 (class 2606 OID 586680)
-- Name: installation_term uk_installation_term_code_ite; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term
    ADD CONSTRAINT uk_installation_term_code_ite UNIQUE (code_ite);


--
-- TOC entry 4595 (class 2606 OID 586454)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

--
-- TOC entry 4834 (class 2618 OID 589758)
-- Name: v_gare_voyageur _RETURN; Type: RULE; Schema: geofer; Owner: postgres
--

CREATE OR REPLACE VIEW v_gare_voyageur AS
 SELECT g.nom_gare,
        CASE
            WHEN (g.si_ouverte = true) THEN 'ouverte'::text
            WHEN (g.si_ouverte = false) THEN 'fermée'::text
            ELSE 'statut inconnu'::text
        END AS statut_gare,
    sum(da.nombre_arret) AS nombre_arrets_total,
    g.nom_aom,
    dv.annee,
    dv.nombre_voyageur AS voyageurs,
    g.id AS gare_id
   FROM ((gare g
     JOIN donnee_arret da ON ((da.gare_id = g.id)))
     JOIN ( SELECT DISTINCT ON (donnee_voyageur.gare_id) donnee_voyageur.id,
            donnee_voyageur.annee,
            donnee_voyageur.nombre_voyageur,
            donnee_voyageur.gare_id
           FROM donnee_voyageur
          ORDER BY donnee_voyageur.gare_id, donnee_voyageur.annee DESC) dv ON ((dv.gare_id = g.id)))
  GROUP BY g.nom_gare, g.nom_aom, dv.id, dv.annee, dv.nombre_voyageur, g.id;


--
-- TOC entry 4654 (class 2606 OID 586681)
-- Name: donnee_arret fk_donnee_arret_gare; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_arret
    ADD CONSTRAINT fk_donnee_arret_gare FOREIGN KEY (gare_id) REFERENCES gare(id);


--
-- TOC entry 4655 (class 2606 OID 586686)
-- Name: donnee_arret fk_donnee_arret_ref_type_arret; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_arret
    ADD CONSTRAINT fk_donnee_arret_ref_type_arret FOREIGN KEY (ref_type_arret_id) REFERENCES ref_type_arret(id);


--
-- TOC entry 4656 (class 2606 OID 586691)
-- Name: donnee_isochrone fk_donnee_isochrone_isochrone; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_isochrone
    ADD CONSTRAINT fk_donnee_isochrone_isochrone FOREIGN KEY (isochrone_id) REFERENCES isochrone(id);


--
-- TOC entry 4657 (class 2606 OID 586696)
-- Name: donnee_isochrone fk_donnee_isochrone_ref_type_donnee; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_isochrone
    ADD CONSTRAINT fk_donnee_isochrone_ref_type_donnee FOREIGN KEY (ref_type_donnee_id) REFERENCES ref_type_donnee(id);


--
-- TOC entry 4671 (class 2606 OID 586766)
-- Name: donnee_voyageur fk_donnee_voyageur_gare; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY donnee_voyageur
    ADD CONSTRAINT fk_donnee_voyageur_gare FOREIGN KEY (gare_id) REFERENCES gare(id);


--
-- TOC entry 4658 (class 2606 OID 586701)
-- Name: etablissement_scolaire fk_etablissement_scolaire_ref_type_etablissement; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY etablissement_scolaire
    ADD CONSTRAINT fk_etablissement_scolaire_ref_type_etablissement FOREIGN KEY (ref_type_etablissement_id) REFERENCES ref_type_etablissement(id);


--
-- TOC entry 4659 (class 2606 OID 586706)
-- Name: installation_term fk_installation_term_ref_activite_principale; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term
    ADD CONSTRAINT fk_installation_term_ref_activite_principale FOREIGN KEY (ref_activite_principale_id) REFERENCES ref_activite_principale(id);


--
-- TOC entry 4660 (class 2606 OID 586711)
-- Name: installation_term_ref_marchandise fk_installation_term_ref_marchandise_installation_term; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term_ref_marchandise
    ADD CONSTRAINT fk_installation_term_ref_marchandise_installation_term FOREIGN KEY (installation_term_id) REFERENCES installation_term(id);


--
-- TOC entry 4661 (class 2606 OID 586716)
-- Name: installation_term_ref_marchandise fk_installation_term_ref_marchandise_ref_marchandise; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term_ref_marchandise
    ADD CONSTRAINT fk_installation_term_ref_marchandise_ref_marchandise FOREIGN KEY (ref_marchandise_id) REFERENCES ref_marchandise(id);


--
-- TOC entry 4662 (class 2606 OID 586721)
-- Name: installation_term_ref_type_site fk_installation_term_ref_type_site_installation_term; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term_ref_type_site
    ADD CONSTRAINT fk_installation_term_ref_type_site_installation_term FOREIGN KEY (installation_term_id) REFERENCES installation_term(id);


--
-- TOC entry 4663 (class 2606 OID 586726)
-- Name: installation_term_ref_type_site fk_installation_term_ref_type_site_ref_type_site; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY installation_term_ref_type_site
    ADD CONSTRAINT fk_installation_term_ref_type_site_ref_type_site FOREIGN KEY (ref_type_site_id) REFERENCES ref_type_site(id);


--
-- TOC entry 4664 (class 2606 OID 586731)
-- Name: isochrone fk_isochrone_gare; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY isochrone
    ADD CONSTRAINT fk_isochrone_gare FOREIGN KEY (gare_id) REFERENCES gare(id);


--
-- TOC entry 4665 (class 2606 OID 586736)
-- Name: isochrone fk_isochrone_ref_type_isochrone; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY isochrone
    ADD CONSTRAINT fk_isochrone_ref_type_isochrone FOREIGN KEY (ref_type_isochrone_id) REFERENCES ref_type_isochrone(id);


--
-- TOC entry 4666 (class 2606 OID 586741)
-- Name: ligne_gare fk_ligne_gare_gare; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ligne_gare
    ADD CONSTRAINT fk_ligne_gare_gare FOREIGN KEY (gare_id) REFERENCES gare(id);


--
-- TOC entry 4667 (class 2606 OID 586746)
-- Name: ligne_gare fk_ligne_gare_ligne; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ligne_gare
    ADD CONSTRAINT fk_ligne_gare_ligne FOREIGN KEY (ligne_id) REFERENCES ligne(id);


--
-- TOC entry 4668 (class 2606 OID 586751)
-- Name: ligne_reseau fk_ligne_reseau_ligne; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ligne_reseau
    ADD CONSTRAINT fk_ligne_reseau_ligne FOREIGN KEY (ligne_id) REFERENCES ligne(id);


--
-- TOC entry 4669 (class 2606 OID 586756)
-- Name: ligne_reseau fk_ligne_reseau_reseau; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY ligne_reseau
    ADD CONSTRAINT fk_ligne_reseau_reseau FOREIGN KEY (reseau_id) REFERENCES reseau_ferroviaire(id);


--
-- TOC entry 4670 (class 2606 OID 586761)
-- Name: passage_niveau fk_passage_niveau_ref_classe_passage_niveau; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY passage_niveau
    ADD CONSTRAINT fk_passage_niveau_ref_classe_passage_niveau FOREIGN KEY (ref_classe_passage_niveau_id) REFERENCES ref_classe_passage_niveau(id);


--
-- TOC entry 4652 (class 2606 OID 586462)
-- Name: profil profil_role_id_fkey; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY profil
    ADD CONSTRAINT profil_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles(id);


--
-- TOC entry 4653 (class 2606 OID 586467)
-- Name: profil profil_utilisateurid_fkey; Type: FK CONSTRAINT; Schema: geofer; Owner: postgres
--

ALTER TABLE ONLY profil
    ADD CONSTRAINT profil_utilisateurid_fkey FOREIGN KEY (utilisateurid) REFERENCES users(id);
