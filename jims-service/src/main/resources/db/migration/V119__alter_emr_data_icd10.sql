/*==============================================================*/
/* Table: EMR_DATA_ICD10    icd10表                    */
/* CREATE_BY :  pq                                             */
/*==============================================================*/
-- 修改icd表字段类型
alter table EMR_DATA_ICD10 modify PID VARCHAR2(64);