-- DDL 
----
-- Unique index/constraint for the application id

CREATE UNIQUE INDEX IF NOT EXISTS uq_ap_uid_application_uniqueid
ON certx.ap_uid_application_uniqueid (ap_uid_application_uniqueid);

---

--Status Knot
INSERT INTO certx.sta_status(sta_id, sta_status, metadata_sta) VALUES (0, 'DELETED', 0);
INSERT INTO certx.sta_status(sta_id, sta_status, metadata_sta) VALUES (1, 'ACTIVE', 1);
-----
