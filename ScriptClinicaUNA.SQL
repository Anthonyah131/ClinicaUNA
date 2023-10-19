/*
Created: 08/10/2023
Modified: 18/10/2023
Model: ClinicaUNA
Database: Oracle 18c
*/


-- Create sequences section -------------------------------------------------

CREATE SEQUENCE CLI_ANTECEDENTE_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_EXPEDIENTE_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_ATENCION_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_MEDICO_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_PACIENTE_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_EXAMEN_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_USUARIO_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_CITA_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_PARAMETROS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_REPORTEAGENDA_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_REPORTEEXPEDIENTE_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_REPORTEUSUARIOS_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE CLI_AGENDA_SEQ01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

-- Create tables section -------------------------------------------------

-- Table CLI_USUARIO

CREATE TABLE CLI_USUARIO(
  usu_id Number NOT NULL,
  usu_nombre Varchar2(25 ) NOT NULL,
  usu_papellido Varchar2(25 ) NOT NULL,
  usu_sapellido Varchar2(25 ) NOT NULL,
  usu_cedula Varchar2(9 ) NOT NULL,
  usu_correo Varchar2(80 ) NOT NULL,
  usu_tipousuario Varchar2(1 ),
  usu_usuario Varchar2(20 ) NOT NULL,
  usu_clave Varchar2(15 ) NOT NULL,
  usu_tempclave Varchar2(15 ),
  usu_idioma Varchar2(1 ),
  usu_activo Varchar2(1 ) NOT NULL,
  usu_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT CLI_USUARIO_CK01 CHECK (usu_activo in ('A','I')),
  CONSTRAINT CLI_USUARIO_CK02 CHECK (usu_idioma in ('E','I'))
)
;

-- Create indexes for table CLI_USUARIO

CREATE UNIQUE INDEX CLI_USUARIO_UNQ01 ON CLI_USUARIO (usu_correo)
;

CREATE UNIQUE INDEX CLI_USUARIO_UNQ02 ON CLI_USUARIO (usu_cedula)
;

CREATE UNIQUE INDEX CLI_USUARIO_UNQ03 ON CLI_USUARIO (usu_usuario)
;

-- Add keys for table CLI_USUARIO

ALTER TABLE CLI_USUARIO ADD CONSTRAINT CLI_USUARIO_PK PRIMARY KEY (usu_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_USUARIO.usu_id IS 'id del registro del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_nombre IS 'nombre del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_papellido IS 'primer apellido del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_sapellido IS 'segundo apellido del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_cedula IS 'cedula del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_correo IS 'gmail del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_tipousuario IS 'tipo de usuario(A:Admin, R:Recepcionista, M:Medico)'
;
COMMENT ON COLUMN CLI_USUARIO.usu_usuario IS 'usuario del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_clave IS 'clave del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_tempclave IS 'clave temporal del usuario'
;
COMMENT ON COLUMN CLI_USUARIO.usu_idioma IS 'idioma de preferencia (E:Espanol, I:Ingles)'
;
COMMENT ON COLUMN CLI_USUARIO.usu_activo IS 'si el usuario esta activo (A:Activo, I:Inactivo)'
;
COMMENT ON COLUMN CLI_USUARIO.usu_version IS 'version del registro del usuario'
;

-- Table CLI_MEDICO

CREATE TABLE CLI_MEDICO(
  med_id Number NOT NULL,
  med_codigo Varchar2(15 ) NOT NULL,
  med_folio Varchar2(10 ) NOT NULL,
  med_carne Varchar2(15 ) NOT NULL,
  med_estado Varchar2(1 ) NOT NULL,
  med_fini Date,
  med_ffin Date,
  med_espaciosxhora Number,
  med_version Number DEFAULT 1 NOT NULL,
  usu_id Number,
  CONSTRAINT CLI_MEDICO_CK01 CHECK (med_estado in ('A','I'))
)
;

-- Create indexes for table CLI_MEDICO

CREATE INDEX CLI_USUARIOMEDICO_FK01 ON CLI_MEDICO (usu_id)
;

CREATE UNIQUE INDEX CLI_MEDICO_UNQ01 ON CLI_MEDICO (med_folio)
;

-- Add keys for table CLI_MEDICO

ALTER TABLE CLI_MEDICO ADD CONSTRAINT CLI_MEDICO_PK PRIMARY KEY (med_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_MEDICO.med_id IS 'id del registro del medico'
;
COMMENT ON COLUMN CLI_MEDICO.med_codigo IS 'codigo del medico'
;
COMMENT ON COLUMN CLI_MEDICO.med_folio IS 'folio del medico'
;
COMMENT ON COLUMN CLI_MEDICO.med_carne IS 'carne del medico'
;
COMMENT ON COLUMN CLI_MEDICO.med_estado IS 'estado del medico (A:Activo, I:Inactivo)'
;
COMMENT ON COLUMN CLI_MEDICO.med_fini IS 'fecha del inicio de la jornada'
;
COMMENT ON COLUMN CLI_MEDICO.med_ffin IS 'fecha del fin de la jornada'
;
COMMENT ON COLUMN CLI_MEDICO.med_espaciosxhora IS 'cantidad de espacios x hora de atencion del medico'
;
COMMENT ON COLUMN CLI_MEDICO.med_version IS 'version del registro del usuario'
;

-- Table CLI_CITA

CREATE TABLE CLI_CITA(
  cit_id Number NOT NULL,
  cit_usuarioregistra Varchar2(20 ) NOT NULL,
  cit_motivo Varchar2(80 ),
  cit_fechahora Date,
  cit_version Number DEFAULT 1 NOT NULL,
  age_id Number,
  pac_id Number
)
;

-- Create indexes for table CLI_CITA

CREATE INDEX CLI_AGENDACITA_FK01 ON CLI_CITA (age_id)
;

CREATE INDEX CLI_PACIENTECITA_FK01 ON CLI_CITA (pac_id)
;

-- Add keys for table CLI_CITA

ALTER TABLE CLI_CITA ADD CONSTRAINT CLI_CITA_PK PRIMARY KEY (cit_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_CITA.cit_id IS 'id de la cita'
;
COMMENT ON COLUMN CLI_CITA.cit_usuarioregistra IS 'usuario que registra la cita'
;
COMMENT ON COLUMN CLI_CITA.cit_motivo IS 'motivo de la cita'
;
COMMENT ON COLUMN CLI_CITA.cit_fechahora IS 'fecha y hora de la cita'
;
COMMENT ON COLUMN CLI_CITA.cit_version IS 'version del registro de la cita'
;

-- Table CLI_PACIENTE

CREATE TABLE CLI_PACIENTE(
  pac_id Number NOT NULL,
  pac_nombre Varchar2(25 ) NOT NULL,
  pac_papellido Varchar2(25 ) NOT NULL,
  pac_sapellido Varchar2(25 ),
  pac_cedula Varchar2(9 ) NOT NULL,
  pac_telefono Number,
  pac_correo Varchar2(80 ) NOT NULL,
  pac_genero Varchar2(1 ) NOT NULL,
  pac_fnacimiento Date NOT NULL,
  pac_version Number DEFAULT 1 NOT NULL,
  CONSTRAINT CLI_PACIENTE_CK01 CHECK (pac_genero in ('M','F'))
)
;

-- Create indexes for table CLI_PACIENTE

CREATE UNIQUE INDEX CLI_PACIENTE_UNQ01 ON CLI_PACIENTE (pac_cedula)
;

CREATE UNIQUE INDEX CLI_PACIENTE_UNQ02 ON CLI_PACIENTE (pac_correo)
;

-- Add keys for table CLI_PACIENTE

ALTER TABLE CLI_PACIENTE ADD CONSTRAINT CLI_PACIENTE_PK PRIMARY KEY (pac_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_PACIENTE.pac_id IS 'id del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_nombre IS 'nombre del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_papellido IS 'primer apellido del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_sapellido IS 'segundo apellido del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_cedula IS 'cedula del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_telefono IS 'celular del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_correo IS 'correo del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_genero IS 'genero del paciente (M:Masculino, F:Femenino)'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_fnacimiento IS 'fecha nacimiento del paciente'
;
COMMENT ON COLUMN CLI_PACIENTE.pac_version IS 'version del registro del paciente'
;

-- Table CLI_EXPEDIENTE

CREATE TABLE CLI_EXPEDIENTE(
  exp_id Number NOT NULL,
  exp_hospitalizaciones Number,
  exp_operaciones Number,
  exp_alergias Number,
  exp_patologicos Varchar2(500 ),
  exp_tiposalergias Varchar2(500 ),
  exp_tratamientos Varchar2(500 ),
  exp_version Number DEFAULT 1 NOT NULL,
  pac_id Number
)
;

-- Create indexes for table CLI_EXPEDIENTE

CREATE INDEX CLI_PACIENTEEXPEDIENTE_FK01 ON CLI_EXPEDIENTE (pac_id)
;

-- Add keys for table CLI_EXPEDIENTE

ALTER TABLE CLI_EXPEDIENTE ADD CONSTRAINT CLI_EXPEDIENTE_PK PRIMARY KEY (exp_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_EXPEDIENTE.exp_id IS 'id del expediente'
;
COMMENT ON COLUMN CLI_EXPEDIENTE.exp_hospitalizaciones IS 'Catidad de veces hospitalizado'
;
COMMENT ON COLUMN CLI_EXPEDIENTE.exp_operaciones IS 'Cantidad de operaciones'
;
COMMENT ON COLUMN CLI_EXPEDIENTE.exp_alergias IS 'Numero de alergias'
;
COMMENT ON COLUMN CLI_EXPEDIENTE.exp_patologicos IS 'Padecimientos patologicos del paciente'
;
COMMENT ON COLUMN CLI_EXPEDIENTE.exp_tiposalergias IS 'Tipo de alergias'
;
COMMENT ON COLUMN CLI_EXPEDIENTE.exp_tratamientos IS 'Tipo de tratamientos que el paciente utiliza o consume
'
;
COMMENT ON COLUMN CLI_EXPEDIENTE.exp_version IS 'version del registro del expediente'
;

-- Table CLI_ANTECEDENTE

CREATE TABLE CLI_ANTECEDENTE(
  ant_id Number NOT NULL,
  ant_descripcion Varchar2(100 ),
  ant_tipo Varchar2(10 ),
  ant_parentesco Varchar2(20 ),
  ant_version Number DEFAULT 1 NOT NULL,
  exp_id Number
)
;

-- Create indexes for table CLI_ANTECEDENTE

CREATE INDEX CLI_EXPEDIENTEANTECEDENTE_FK01 ON CLI_ANTECEDENTE (exp_id)
;

-- Add keys for table CLI_ANTECEDENTE

ALTER TABLE CLI_ANTECEDENTE ADD CONSTRAINT CLI_ANTECEDENTE_PK PRIMARY KEY (ant_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_ANTECEDENTE.ant_id IS 'id del antecedente personal'
;
COMMENT ON COLUMN CLI_ANTECEDENTE.ant_descripcion IS 'descripcion del antecedente'
;
COMMENT ON COLUMN CLI_ANTECEDENTE.ant_tipo IS 'El tipo de antecedente que tiene el familiar'
;
COMMENT ON COLUMN CLI_ANTECEDENTE.ant_parentesco IS 'Parentesco con la persona del antecedente'
;
COMMENT ON COLUMN CLI_ANTECEDENTE.ant_version IS 'version del registro del antecedente'
;

-- Table CLI_ATENCION

CREATE TABLE CLI_ATENCION(
  ate_id Number NOT NULL,
  ate_fechahora Date,
  ate_presion Varchar2(5 ),
  ate_frecuenciacard Varchar2(5 ),
  ate_peso Varchar2(5 ),
  ate_talla Varchar2(5 ),
  ate_temperatura Varchar2(5 ),
  ate_imc Varchar2(5 ),
  ate_anotacionenfe Varchar2(80 ),
  ate_razonconsulta Varchar2(50 ),
  ate_planatencion Varchar2(1 ),
  ate_observaciones Varchar2(80 ),
  ate_tratamiento Varchar2(50 ),
  ate_version Number DEFAULT 1 NOT NULL,
  exp_id Number
)
;

-- Create indexes for table CLI_ATENCION

CREATE INDEX CLI_PACIENTEATENCION_FK01 ON CLI_ATENCION (exp_id)
;

-- Add keys for table CLI_ATENCION

ALTER TABLE CLI_ATENCION ADD CONSTRAINT CLI_ATENCION_PK PRIMARY KEY (ate_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_ATENCION.ate_id IS 'id de la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_fechahora IS 'fecha y hora de la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_presion IS 'presion del paciente en la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_frecuenciacard IS 'frecuencia cardiaca del paciente en la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_peso IS 'peso del paciente en la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_talla IS 'talla del paciente en la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_temperatura IS 'temperatura del paciente en la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_imc IS 'imc del paciente en la atencion'
;
COMMENT ON COLUMN CLI_ATENCION.ate_anotacionenfe IS 'anotacion de enfermeria'
;
COMMENT ON COLUMN CLI_ATENCION.ate_razonconsulta IS 'razon de la consulta'
;
COMMENT ON COLUMN CLI_ATENCION.ate_planatencion IS 'no se que es xd'
;
COMMENT ON COLUMN CLI_ATENCION.ate_observaciones IS 'observaciones del medico'
;
COMMENT ON COLUMN CLI_ATENCION.ate_tratamiento IS 'tratamientos dados por el medico'
;
COMMENT ON COLUMN CLI_ATENCION.ate_version IS 'version del registro de la atencion'
;

-- Table CLI_EXAMEN

CREATE TABLE CLI_EXAMEN(
  exa_id Number NOT NULL,
  exa_nombre Varchar2(30 ) NOT NULL,
  exa_fecha Date,
  exa_anotacionesmed Varchar2(80 ),
  exa_version Number DEFAULT 1 NOT NULL,
  ate_id Number,
  exp_id Number
)
;

-- Create indexes for table CLI_EXAMEN

CREATE INDEX CLI_ATENCIONEXAMEN_FK01 ON CLI_EXAMEN (ate_id)
;

CREATE INDEX CLI_EXPEDIENTEEXAMEN_FK01 ON CLI_EXAMEN (exp_id)
;

-- Add keys for table CLI_EXAMEN

ALTER TABLE CLI_EXAMEN ADD CONSTRAINT CLI_EXAMEN_PK PRIMARY KEY (exa_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_EXAMEN.exa_id IS 'id del examen'
;
COMMENT ON COLUMN CLI_EXAMEN.exa_nombre IS 'nombre del examen'
;
COMMENT ON COLUMN CLI_EXAMEN.exa_fecha IS 'fecha del examen'
;
COMMENT ON COLUMN CLI_EXAMEN.exa_anotacionesmed IS 'anotaciones del medico'
;
COMMENT ON COLUMN CLI_EXAMEN.exa_version IS 'version del registro del examen'
;

-- Table CLI_PARAMETROS

CREATE TABLE CLI_PARAMETROS(
  par_id Number NOT NULL,
  par_nombre Varchar2(30 ),
  par_descripcion Varchar2(30 ),
  par_email Varchar2(30 ),
  par_clave Varchar2(30 ),
  par_foto Blob,
  par_html Blob,
  par_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table CLI_PARAMETROS

ALTER TABLE CLI_PARAMETROS ADD CONSTRAINT CLI_PARAMETROS_PK PRIMARY KEY (par_id)
;

-- Table CLI_AGENDA

CREATE TABLE CLI_AGENDA(
  age_id Number NOT NULL,
  age_fecha Date,
  age_tiempo Char(20 ),
  age_espacios Number,
  age_version Number DEFAULT 1 NOT NULL,
  med_id Number
)
;

-- Create indexes for table CLI_AGENDA

CREATE INDEX CLI_MEDICOAGENDA_FK01 ON CLI_AGENDA (med_id)
;

-- Add keys for table CLI_AGENDA

ALTER TABLE CLI_AGENDA ADD CONSTRAINT PK_CLI_AGENDA PRIMARY KEY (age_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_AGENDA.age_id IS 'Id de la agenda
'
;
COMMENT ON COLUMN CLI_AGENDA.age_fecha IS 'Fecha de la agenda
'
;
COMMENT ON COLUMN CLI_AGENDA.age_tiempo IS 'Tiempo de los espacion de atencion dentro de la agenda
'
;
COMMENT ON COLUMN CLI_AGENDA.age_espacios IS 'Cantidad de espacion de la agenda'
;
COMMENT ON COLUMN CLI_AGENDA.age_version IS 'Version del registro de la agenda
'
;

-- Table CLI_REPORTEAGENDA

CREATE TABLE CLI_REPORTEAGENDA(
  repage_id Number NOT NULL,
  repage_fechainicio Date,
  repage_fechafinal Date,
  repage_fechaemision Date,
  repage_version Number DEFAULT 1 NOT NULL,
  med_id Number
)
;

-- Create indexes for table CLI_REPORTEAGENDA

CREATE INDEX CLI_MEDICOREPORTEAGENDA_FK01 ON CLI_REPORTEAGENDA (med_id)
;

-- Add keys for table CLI_REPORTEAGENDA

ALTER TABLE CLI_REPORTEAGENDA ADD CONSTRAINT CLI_REPORTEAGENDA_PK PRIMARY KEY (repage_id)
;

-- Table CLI_REPORTEEXPEDIENTE

CREATE TABLE CLI_REPORTEEXPEDIENTE(
  repexp_id Number NOT NULL,
  repexp_fechaemision Date,
  repexp_version Number DEFAULT 1 NOT NULL,
  pac_id Number
)
;

-- Create indexes for table CLI_REPORTEEXPEDIENTE

CREATE INDEX CLI_PACIENTEREPORTEEXPEDIENTE_FK01 ON CLI_REPORTEEXPEDIENTE (pac_id)
;

-- Add keys for table CLI_REPORTEEXPEDIENTE

ALTER TABLE CLI_REPORTEEXPEDIENTE ADD CONSTRAINT CLI_REPORTEEXPEDIENTE_PK PRIMARY KEY (repexp_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_REPORTEEXPEDIENTE.repexp_id IS 'Id del los parametros del reporte del paciente'
;
COMMENT ON COLUMN CLI_REPORTEEXPEDIENTE.repexp_fechaemision IS 'Fecha de emision del reporte'
;
COMMENT ON COLUMN CLI_REPORTEEXPEDIENTE.repexp_version IS 'version del tabla'
;

-- Table CLI_REPORTEUSUARIOS

CREATE TABLE CLI_REPORTEUSUARIOS(
  repusu_id Number NOT NULL,
  repusu_fechaemision Date,
  repusu_version Number DEFAULT 1 NOT NULL
)
;

-- Add keys for table CLI_REPORTEUSUARIOS

ALTER TABLE CLI_REPORTEUSUARIOS ADD CONSTRAINT CLI_REPORTEUSUARIOS_PK PRIMARY KEY (repusu_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN CLI_REPORTEUSUARIOS.repusu_id IS 'id de la tabla'
;
COMMENT ON COLUMN CLI_REPORTEUSUARIOS.repusu_fechaemision IS 'Fecha de emision del reporte'
;
COMMENT ON COLUMN CLI_REPORTEUSUARIOS.repusu_version IS 'version de la tabla'
;

-- Table CLI_USUARIOREPORTEUSUARIOS

CREATE TABLE CLI_USUARIOREPORTEUSUARIOS(
  usu_id Number NOT NULL,
  parusu_id Number NOT NULL
)
;

-- Add keys for table CLI_USUARIOREPORTEUSUARIOS

ALTER TABLE CLI_USUARIOREPORTEUSUARIOS ADD CONSTRAINT PK_CLI_USUARIOREPORTEUSUARIOS PRIMARY KEY (usu_id,parusu_id)
;

-- Trigger for sequence CLI_USUARIO_SEQ01 for column usu_id in table CLI_USUARIO ---------
CREATE OR REPLACE TRIGGER CLI_USUARIO_TGR01 BEFORE INSERT
ON CLI_USUARIO FOR EACH ROW
BEGIN
    if :new.usu_id is null or :new.usu_id <= 0 then
        :new.usu_id := CLI_USUARIO_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_USUARIO_TGR02 AFTER UPDATE OF usu_id
ON CLI_USUARIO FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column usu_id in table CLI_USUARIO as it uses sequence.');
END;
/

-- Trigger for sequence CLI_MEDICO_SEQ01 for column med_id in table CLI_MEDICO ---------
CREATE OR REPLACE TRIGGER CLI_MEDICO_TGR01 BEFORE INSERT
ON CLI_MEDICO FOR EACH ROW
BEGIN
    if :new.med_id is null or :new.med_id <= 0 then
        :new.med_id := CLI_MEDICO_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_MEDICO_TGR02 AFTER UPDATE OF med_id
ON CLI_MEDICO FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column med_id in table CLI_MEDICO as it uses sequence.');
END;
/

-- Trigger for sequence CLI_CITA_SEQ01 for column cit_id in table CLI_CITA ---------
CREATE OR REPLACE TRIGGER CLI_CITA_TGR01 BEFORE INSERT
ON CLI_CITA FOR EACH ROW
BEGIN
    if :new.cit_id is null or :new.cit_id <= 0 then
        :new.cit_id := CLI_CITA_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_CITA_TGR02 AFTER UPDATE OF cit_id
ON CLI_CITA FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column cit_id in table CLI_CITA as it uses sequence.');
END;
/

-- Trigger for sequence CLI_PACIENTE_SEQ01 for column pac_id in table CLI_PACIENTE ---------
CREATE OR REPLACE TRIGGER CLI_PACIENTE_TGR01 BEFORE INSERT
ON CLI_PACIENTE FOR EACH ROW
BEGIN
    if :new.pac_id is null or :new.pac_id <= 0 then
        :new.pac_id := CLI_PACIENTE_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_PACIENTE_TGR02 AFTER UPDATE OF pac_id
ON CLI_PACIENTE FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column pac_id in table CLI_PACIENTE as it uses sequence.');
END;
/


-- Trigger for sequence CLI_EXPEDIENTE_SEQ01 for column exp_id in table CLI_EXPEDIENTE ---------
CREATE OR REPLACE TRIGGER CLI_EXPEDIENTE_TGR01 BEFORE INSERT
ON CLI_EXPEDIENTE FOR EACH ROW
BEGIN
    if :new.exp_id is null or :new.exp_id <= 0 then
        :new.exp_id := CLI_EXPEDIENTE_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_EXPEDIENTE_TGR02 AFTER UPDATE OF exp_id
ON CLI_EXPEDIENTE FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column exp_id in table CLI_EXPEDIENTE as it uses sequence.');
END;
/

-- Trigger for sequence CLI_ANTECEDENTE_SEQ01 for column ant_id in table CLI_ANTECEDENTE ---------
CREATE OR REPLACE TRIGGER CLI_ANTECEDENTE_TGR01 BEFORE INSERT
ON CLI_ANTECEDENTE FOR EACH ROW
BEGIN
    if :new.ant_id is null or :new.ant_id <= 0 then
        :new.ant_id := CLI_ANTECEDENTE_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_ANTECEDENTE_TGR02 AFTER UPDATE OF ant_id
ON CLI_ANTECEDENTE FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column ant_id in table CLI_ANTECEDENTE as it uses sequence.');
END;
/

-- Trigger for sequence CLI_ATENCION_SEQ01 for column ate_id in table CLI_ATENCION ---------
CREATE OR REPLACE TRIGGER CLI_ATENCION_TGR01 BEFORE INSERT
ON CLI_ATENCION FOR EACH ROW
BEGIN
    if :new.ate_id is null or :new.ate_id <= 0 then
        :new.ate_id := CLI_ATENCION_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_ATENCION_TGR02 AFTER UPDATE OF ate_id
ON CLI_ATENCION FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column ate_id in table CLI_ATENCION as it uses sequence.');
END;
/


-- Trigger for sequence CLI_EXAMEN_SEQ01 for column exa_id in table CLI_EXAMEN ---------
CREATE OR REPLACE TRIGGER CLI_EXAMEN_TGR01 BEFORE INSERT
ON CLI_EXAMEN FOR EACH ROW
BEGIN
    if :new.exa_id is null or :new.exa_id <= 0 then
        :new.exa_id := CLI_EXAMEN_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_EXAMEN_TGR02 AFTER UPDATE OF exa_id
ON CLI_EXAMEN FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column exa_id in table CLI_EXAMEN as it uses sequence.');
END;
/

-- Trigger for sequence CLI_PARAMETROS_SEQ01 for column par_id in table CLI_PARAMETROS ---------
CREATE OR REPLACE TRIGGER CLI_PARAMETROS_TGR01 BEFORE INSERT
ON CLI_PARAMETROS FOR EACH ROW
BEGIN
    if :new.par_id is null or :new.par_id <= 0 then
        :new.par_id := CLI_PARAMETROS_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_PARAMETROS_TGR02 AFTER UPDATE OF par_id
ON CLI_PARAMETROS FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column par_id in table CLI_PARAMETROS as it uses sequence.');
END;
/

-- Trigger for sequence CLI_AGENDA_SEQ01 for column age_id in table CLI_AGENDA ---------
CREATE OR REPLACE TRIGGER CLI_AGENDA_TGR01 BEFORE INSERT
ON CLI_AGENDA FOR EACH ROW
BEGIN
    if :new.age_id is null or :new.age_id <= 0 then
        :new.age_id := CLI_AGENDA_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_AGENDA_TGR02 AFTER UPDATE OF age_id
ON CLI_AGENDA FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column age_id in table CLI_AGENDA as it uses sequence.');
END;
/

-- Trigger for sequence CLI_REPORTEAGENDA_SEQ01 for column repage_id in table CLI_REPORTEAGENDA ---------
CREATE OR REPLACE TRIGGER CLI_REPORTEAGENDA_TGR01 BEFORE INSERT
ON CLI_REPORTEAGENDA FOR EACH ROW
BEGIN
    if :new.repage_id is null or :new.repage_id <= 0 then
        :new.repage_id := CLI_REPORTEAGENDA_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_REPORTEAGENDA_TGR02 AFTER UPDATE OF repage_id
ON CLI_REPORTEAGENDA FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column repage_id in table CLI_REPORTEAGENDA as it uses sequence.');
END;
/

-- Trigger for sequence CLI_REPORTEEXPEDIENTE_SEQ01 for column repexp_id in table CLI_REPORTEEXPEDIENTE ---------
CREATE OR REPLACE TRIGGER CLI_REPORTEEXPEDIENTE_TGR01 BEFORE INSERT
ON CLI_REPORTEEXPEDIENTE FOR EACH ROW
BEGIN
    if :new.repexp_id is null or :new.repexp_id <= 0 then
        :new.repexp_id := CLI_REPORTEEXPEDIENTE_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_REPORTEEXPEDIENTE_TGR02 AFTER UPDATE OF repexp_id
ON CLI_REPORTEEXPEDIENTE FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column repexp_id in table CLI_REPORTEEXPEDIENTE as it uses sequence.');
END;
/

-- Trigger for sequence CLI_REPORTEUSUARIOS_SEQ01 for column repusu_id in table CLI_REPORTEUSUARIOS ---------
CREATE OR REPLACE TRIGGER CLI_REPORTEUSUARIOS_TGR01 BEFORE INSERT
ON CLI_REPORTEUSUARIOS FOR EACH ROW
BEGIN
    if :new.repusu_id is null or :new.repusu_id <= 0 then
        :new.repusu_id := CLI_REPORTEUSUARIOS_SEQ01.nextval;
    end if;
END;
/
CREATE OR REPLACE TRIGGER CLI_REPORTEUSUARIOS_TGR02 AFTER UPDATE OF repusu_id
ON CLI_REPORTEUSUARIOS FOR EACH ROW
BEGIN
    RAISE_APPLICATION_ERROR(-20010,'Cannot update column repusu_id in table CLI_REPORTEUSUARIOS as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE CLI_MEDICO ADD CONSTRAINT CLI_USUARIOMEDICO_FK01 FOREIGN KEY (usu_id) REFERENCES CLI_USUARIO (usu_id)
;


ALTER TABLE CLI_EXPEDIENTE ADD CONSTRAINT CLI_PACIENTEEXPEDIENTE_FK01 FOREIGN KEY (pac_id) REFERENCES CLI_PACIENTE (pac_id)
;


ALTER TABLE CLI_ANTECEDENTE ADD CONSTRAINT CLI_EXPEDIENTEANTECEDENTE_FK01 FOREIGN KEY (exp_id) REFERENCES CLI_EXPEDIENTE (exp_id)
;


ALTER TABLE CLI_ATENCION ADD CONSTRAINT CLI_PACIENTEATENCION_FK01 FOREIGN KEY (exp_id) REFERENCES CLI_EXPEDIENTE (exp_id)
;


ALTER TABLE CLI_EXAMEN ADD CONSTRAINT CLI_ATENCIONEXAMEN_FK01 FOREIGN KEY (ate_id) REFERENCES CLI_ATENCION (ate_id)
;


ALTER TABLE CLI_CITA ADD CONSTRAINT CLI_AGENDACITA_FK01 FOREIGN KEY (age_id) REFERENCES CLI_AGENDA (age_id)
;


ALTER TABLE CLI_CITA ADD CONSTRAINT CLI_PACIENTECITA_FK01 FOREIGN KEY (pac_id) REFERENCES CLI_PACIENTE (pac_id)
;


ALTER TABLE CLI_AGENDA ADD CONSTRAINT CLI_MEDICOAGENDA_FK01 FOREIGN KEY (med_id) REFERENCES CLI_MEDICO (med_id)
;


ALTER TABLE CLI_EXAMEN ADD CONSTRAINT CLI_EXPEDIENTEEXAMEN_FK01 FOREIGN KEY (exp_id) REFERENCES CLI_EXPEDIENTE (exp_id)
;


ALTER TABLE CLI_REPORTEAGENDA ADD CONSTRAINT CLI_MEDICOREPORTEAGENDA_FK01 FOREIGN KEY (med_id) REFERENCES CLI_MEDICO (med_id)
;


ALTER TABLE CLI_REPORTEEXPEDIENTE ADD CONSTRAINT CLI_PACIENTEREPORTEEXPEDIENTE_FK01 FOREIGN KEY (pac_id) REFERENCES CLI_PACIENTE (pac_id)
;


ALTER TABLE CLI_USUARIOREPORTEUSUARIOS ADD CONSTRAINT CLI_USUARIOREPORTEUSUARIOS_FK01 FOREIGN KEY (usu_id) REFERENCES CLI_USUARIO (usu_id)
;


ALTER TABLE CLI_USUARIOREPORTEUSUARIOS ADD CONSTRAINT CLI_USUARIOREPORTEUSUARIOS_FK02 FOREIGN KEY (parusu_id) REFERENCES CLI_REPORTEUSUARIOS (repusu_id)
;





