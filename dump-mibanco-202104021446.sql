PGDMP         .                y            mibanco    13.2    13.2     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16394    mibanco    DATABASE     f   CREATE DATABASE mibanco WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE mibanco;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            ?           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            ?            1259    16411    accounts    TABLE     ?   CREATE TABLE public.accounts (
    id character varying NOT NULL,
    amount double precision NOT NULL,
    customerid character varying NOT NULL,
    openedat date NOT NULL
);
    DROP TABLE public.accounts;
       public         heap    postgres    false    3            ?            1259    16395 	   customers    TABLE     ?   CREATE TABLE public.customers (
    id character varying NOT NULL,
    name character varying,
    lastname character varying,
    email character varying
);
    DROP TABLE public.customers;
       public         heap    postgres    false    3            ?            1259    16487    transactions    TABLE     ?   CREATE TABLE public.transactions (
    number character varying NOT NULL,
    acountid character varying NOT NULL,
    type character varying NOT NULL,
    amount double precision NOT NULL,
    date date NOT NULL
);
     DROP TABLE public.transactions;
       public         heap    postgres    false    3            ?            1259    16497    transactions2    TABLE     ?   CREATE TABLE public.transactions2 (
    number integer NOT NULL,
    acountid character varying NOT NULL,
    type character varying NOT NULL,
    amount double precision NOT NULL,
    date date NOT NULL
);
 !   DROP TABLE public.transactions2;
       public         heap    postgres    false    3            ?            1259    16495    transactions2_number_seq    SEQUENCE     ?   CREATE SEQUENCE public.transactions2_number_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.transactions2_number_seq;
       public          postgres    false    3    204            ?           0    0    transactions2_number_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.transactions2_number_seq OWNED BY public.transactions2.number;
          public          postgres    false    203            2           2604    16500    transactions2 number    DEFAULT     |   ALTER TABLE ONLY public.transactions2 ALTER COLUMN number SET DEFAULT nextval('public.transactions2_number_seq'::regclass);
 C   ALTER TABLE public.transactions2 ALTER COLUMN number DROP DEFAULT;
       public          postgres    false    204    203    204            ?          0    16411    accounts 
   TABLE DATA           D   COPY public.accounts (id, amount, customerid, openedat) FROM stdin;
    public          postgres    false    201            ?          0    16395 	   customers 
   TABLE DATA           >   COPY public.customers (id, name, lastname, email) FROM stdin;
    public          postgres    false    200            ?          0    16487    transactions 
   TABLE DATA           L   COPY public.transactions (number, acountid, type, amount, date) FROM stdin;
    public          postgres    false    202            ?          0    16497    transactions2 
   TABLE DATA           M   COPY public.transactions2 (number, acountid, type, amount, date) FROM stdin;
    public          postgres    false    204            ?           0    0    transactions2_number_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.transactions2_number_seq', 1, false);
          public          postgres    false    203            6           2606    16418    accounts accounts_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.accounts DROP CONSTRAINT accounts_pkey;
       public            postgres    false    201            4           2606    16402    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    200            :           2606    16505     transactions2 transactions2_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.transactions2
    ADD CONSTRAINT transactions2_pkey PRIMARY KEY (number);
 J   ALTER TABLE ONLY public.transactions2 DROP CONSTRAINT transactions2_pkey;
       public            postgres    false    204            8           2606    16494    transactions transactions_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (number);
 H   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_pkey;
       public            postgres    false    202            ?   F   x?m?A
?@?s??J???????{??%0???u[ɑT?F]+W????]?XP?}ac??{?????      ?   ]   x?3???????O)?L/M???rr3s???s??8}?JSs8?,???"d???E%?y?ɉ?n9?E?Ŝ?P?"???|?
b???? ??.o      ?     x??UInQ\ۧ???<??b??1??????\?????<????z???g?3??&M?(7[0?????q??[o??v? ?????lE??)ϵ?????)/?ti)3&mѹ????s&sSQ??>m??a?s	??F???顯??d???čiڊ?D??*>?׻?o??v???????????]??Gw?nL?*)n*?D??F?iE#?	a?"???$?wy??]b-??R<e3?sE6??R?????D?̐?v????*?(???'9|jfIe?P?Qy?Iv????4???Y'??S???4%?5K~U4??s?Ok?]???"?Q???q?!y?h|?Ne??7A??tsiO????ሢ??[?ƭ??;`n??G?
,?) *W2?J??#̂?ke??tcPa???$?????8??L??a????Zjqld<?k??)yt??,??2{=8x4??*$O?AP;?? \2??~?6?Z<uB???_?{?1?c??????g???ˡ??c?Q?(??-???g?      ?      x?????? ? ?          ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    16394    mibanco    DATABASE     f   CREATE DATABASE mibanco WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE mibanco;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false            ?           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    3            ?            1259    16411    accounts    TABLE     ?   CREATE TABLE public.accounts (
    id character varying NOT NULL,
    amount double precision NOT NULL,
    customerid character varying NOT NULL,
    openedat date NOT NULL
);
    DROP TABLE public.accounts;
       public         heap    postgres    false    3            ?            1259    16395 	   customers    TABLE     ?   CREATE TABLE public.customers (
    id character varying NOT NULL,
    name character varying,
    lastname character varying,
    email character varying
);
    DROP TABLE public.customers;
       public         heap    postgres    false    3            ?            1259    16487    transactions    TABLE     ?   CREATE TABLE public.transactions (
    number character varying NOT NULL,
    acountid character varying NOT NULL,
    type character varying NOT NULL,
    amount double precision NOT NULL,
    date date NOT NULL
);
     DROP TABLE public.transactions;
       public         heap    postgres    false    3            ?            1259    16497    transactions2    TABLE     ?   CREATE TABLE public.transactions2 (
    number integer NOT NULL,
    acountid character varying NOT NULL,
    type character varying NOT NULL,
    amount double precision NOT NULL,
    date date NOT NULL
);
 !   DROP TABLE public.transactions2;
       public         heap    postgres    false    3            ?            1259    16495    transactions2_number_seq    SEQUENCE     ?   CREATE SEQUENCE public.transactions2_number_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.transactions2_number_seq;
       public          postgres    false    3    204            ?           0    0    transactions2_number_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.transactions2_number_seq OWNED BY public.transactions2.number;
          public          postgres    false    203            2           2604    16500    transactions2 number    DEFAULT     |   ALTER TABLE ONLY public.transactions2 ALTER COLUMN number SET DEFAULT nextval('public.transactions2_number_seq'::regclass);
 C   ALTER TABLE public.transactions2 ALTER COLUMN number DROP DEFAULT;
       public          postgres    false    204    203    204            ?          0    16411    accounts 
   TABLE DATA           D   COPY public.accounts (id, amount, customerid, openedat) FROM stdin;
    public          postgres    false    201   (       ?          0    16395 	   customers 
   TABLE DATA           >   COPY public.customers (id, name, lastname, email) FROM stdin;
    public          postgres    false    200   P        ?          0    16487    transactions 
   TABLE DATA           L   COPY public.transactions (number, acountid, type, amount, date) FROM stdin;
    public          postgres    false    202   g        ?          0    16497    transactions2 
   TABLE DATA           M   COPY public.transactions2 (number, acountid, type, amount, date) FROM stdin;
    public          postgres    false    204          ?           0    0    transactions2_number_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.transactions2_number_seq', 1, false);
          public          postgres    false    203            6           2606    16418    accounts accounts_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.accounts DROP CONSTRAINT accounts_pkey;
       public            postgres    false    201            4           2606    16402    customers customers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    200            :           2606    16505     transactions2 transactions2_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.transactions2
    ADD CONSTRAINT transactions2_pkey PRIMARY KEY (number);
 J   ALTER TABLE ONLY public.transactions2 DROP CONSTRAINT transactions2_pkey;
       public            postgres    false    204            8           2606    16494    transactions transactions_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (number);
 H   ALTER TABLE ONLY public.transactions DROP CONSTRAINT transactions_pkey;
       public            postgres    false    202           