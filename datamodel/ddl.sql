-- DDL 
-- Unique index/constraint for the application id

CREATE UNIQUE INDEX IF NOT EXISTS uq_ap_uid_application_uniqueid
ON certx.ap_uid_application_uniqueid (ap_uid_application_uniqueid);

---