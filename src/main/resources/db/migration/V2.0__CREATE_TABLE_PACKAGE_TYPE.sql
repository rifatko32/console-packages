CREATE TABLE public.package_type
(
    "name"             varchar NOT NULL,
    form               varchar NOT NULL,
    description_number varchar NOT NULL,
    width              int4    NOT NULL,
    height             int4    NOT NULL,
    CONSTRAINT package_type_pk PRIMARY KEY ("name")
);
COMMENT ON TABLE public.package_type IS 'Типы пакетов';
