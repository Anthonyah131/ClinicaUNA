-- Create the user 
create user ClinicaUNA
  identified by una
  default tablespace USERS
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges 
grant connect to ClinicaUNA;
grant resource to ClinicaUNA;
grant dba to ClinicaUNA;
-- Grant/Revoke system privileges 
grant unlimited tablespace to ClinicaUNA;
grant create session to ClinicaUNA;
