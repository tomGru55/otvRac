PGDMP         "            	    {            database    15.4    15.4     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16586    database    DATABASE     ~   CREATE DATABASE database WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Croatian_Croatia.1250';
    DROP DATABASE database;
                postgres    false            �            1259    16635    oscars    TABLE     �  CREATE TABLE public.oscars (
    naziv_ceremonije character varying,
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
    DROP TABLE public.oscars;
       public         heap    postgres    false            �          0    16635    oscars 
   TABLE DATA           �   COPY public.oscars (naziv_ceremonije, datum_ceremonije, lokacija_ceremonije, kategorija_nagrade, film, dobitnik_ime, dobitnik_prezime, dobitnik_datumrod, ime_lika_u_filmu, redatelj) FROM stdin;
    public          postgres    false    214          e           2606    16641    oscars oscars_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.oscars
    ADD CONSTRAINT oscars_pkey PRIMARY KEY (film, ime_lika_u_filmu);
 <   ALTER TABLE ONLY public.oscars DROP CONSTRAINT oscars_pkey;
       public            postgres    false    214    214            �   �  x����n�0��O�X�
i/��2�N !M�9����9F�S��ўc/�cV���MUv)������|*5�`Au�p����Mӳ$�&���nU�����j�%����<hU���ܶ>荚�Gr� d�#f�.i��'Y3]�!��Mc�9�+�aom�����w�Q�ǁ����]�5{A�O�d��GR#�:98��ֲ�0���D����8��G��04�X���z��Z5#�
�)H�G�4��P�m8�f���I
��1{*ɩ�E"gt�0o֭��8�PY�5��|��<?J�?��oh�-̰��#�I�M�^|���EKl�uj����u�)��6F[V�V�ϩ�[�Еb������I:H���B�Zl�O..kvN��ʧ���������-,���h�Y�� ׭DGA�]Y
�D�'IM�_y&ڨ��`E#���(�g��Iv7(�:ͤ��[7�#u�,c�Γ�N~~�mM�o�l�;+wU�N-��k�`j���$;8$�����V�FRfq����)x��n�:��pM�Z�#k����1�ٵ��ĮE�]%�q�e��~���Vh�*���d���������v�0k$��d%�1F_��K^T�R
�TG��BEq�Z�$�j��He`E�?E���9,�{T_�?!�ȅU���������N�7b�J	     