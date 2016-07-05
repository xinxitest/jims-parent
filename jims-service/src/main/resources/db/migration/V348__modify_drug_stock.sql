-- Create table
--    author:zhuqi
/*==============================================================*/
/* Table:DRUG_STOCK                                             */
/*==============================================================*/

-- Add/modify columns
alter table DRUG_STOCK add price_list_id VARCHAR2(64 CHAR);

-- Create/Recreate primary, unique and foreign key constraints 
alter table DRUG_STOCK
  add constraint UNIQUE_DRUG_STOCK unique (PRICE_LIST_ID);