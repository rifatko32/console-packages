CREATE TABLE public.package_type
(
    id                 SERIAL  PRIMARY KEY,
    form               varchar NOT NULL,
    description_number varchar NOT NULL,
    width              int4    NOT NULL,
    height             int4    NOT NULL
);
COMMENT ON TABLE public.package_type IS 'Типы пакетов';
