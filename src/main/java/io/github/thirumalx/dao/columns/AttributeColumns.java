package io.github.thirumalx.dao.columns;

/**
 * Column name constants for Attribute tables.
 */
public interface AttributeColumns {
    
    interface ApplicationName {
        String TABLE = "certx.ap_nam_application_name";
        String FK = "ap_nam_ap_id";
        String VALUE = "ap_nam_application_name";
        String CHANGED_AT = "ap_nam_changedat";
        String METADATA = "metadata_ap_nam";
    }
    
    interface ApplicationStatus {
        String TABLE = "certx.ap_sta_application_status";
        String FK = "ap_sta_ap_id";
        String VALUE = "ap_sta_sta_id";
        String CHANGED_AT = "ap_sta_changedat";
        String METADATA = "metadata_ap_sta";
    }
    
    interface ApplicationUniqueId {
        String TABLE = "certx.ap_uid_application_uniqueid";
        String FK = "ap_uid_ap_id";
        String VALUE = "ap_uid_application_uniqueid";
        String CHANGED_AT = "ap_uid_changedat";
        String METADATA = "metadata_ap_uid";
    }
    
    interface ClientName {
        String TABLE = "certx.cl_nam_client_name";
        String FK = "cl_nam_cl_id";
        String VALUE = "cl_nam_client_name";
        String METADATA = "metadata_cl_nam";
    }
    
    interface ClientEmail {
        String TABLE = "certx.cl_eid_client_email";
        String FK = "cl_eid_cl_id";
        String VALUE = "cl_eid_client_email";
        String CHANGED_AT = "cl_eid_changedat";
        String METADATA = "metadata_cl_eid";
    }
    
    interface ClientMobileNumber {
        String TABLE = "certx.cl_mno_client_mobilenumber";
        String FK = "cl_mno_cl_id";
        String VALUE = "cl_mno_client_mobilenumber";
        String CHANGED_AT = "cl_mno_changedat";
        String METADATA = "metadata_cl_mno";
    }
    
    interface ClientStatus {
        String TABLE = "certx.cl_sta_client_status";
        String FK = "cl_sta_cl_id";
        String VALUE = "cl_sta_sta_id";
        String CHANGED_AT = "cl_sta_changedat";
        String METADATA = "metadata_cl_sta";
    }
}
