package io.github.thirumalx.dao.columns;

/**
 * Column name constants for View tables.
 */
public interface ViewColumns {
    
    interface ApplicationLatest {
        String TABLE = "certx.lAP_Application";
        String ID = "AP_ID";
        String NAME = "AP_NAM_Application_Name";
        String STATUS = "AP_STA_STA_Status";
        String UNIQUE_ID = "AP_UID_Application_UniqueId";
    }
    
    interface ApplicationNow {
        String TABLE = "certx.nAP_Application";
        String ID = "AP_ID";
        String NAME = "AP_NAM_Application_Name";
        String STATUS = "AP_STA_STA_Status";
        String STATUS_ID_COL = "ap_sta_sta_id";
        String UNIQUE_ID = "AP_UID_Application_UniqueId";
    }
    
    interface ClientNow {
        String TABLE = "certx.nCL_Client";
        String ID = "CL_ID";
        String NAME = "CL_NAM_Client_Name";
        String EMAIL = "CL_EID_Client_Email";
        String MOBILE_NUMBER = "CL_MNO_Client_MobileNumber";
        String STATUS_ID_COL = "cl_sta_sta_id";
        String STATUS = "CL_STA_STA_Status";
    }
}
