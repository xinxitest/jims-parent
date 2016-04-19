﻿create table PATS_IN_HOSPITAL
(
  ID                     VARCHAR2(64 CHAR) not null,
  HOSID                  VARCHAR2(64 CHAR),
  VISIT_ID               NUMBER(2),
  WARD_CODE              VARCHAR2(10 CHAR),
  DEPT_CODE              VARCHAR2(10 CHAR),
  BED_NO                 NUMBER(8),
  ADMISSION_DATE_TIME    DATE,
  ADM_WARD_DATE_TIME     DATE,
  DIAGNOSIS              VARCHAR2(100 CHAR),
  PATIENT_CONDITION      VARCHAR2(64 CHAR),
  NURSING_CLASS          VARCHAR2(64 CHAR),
  DOCTOR_IN_CHARGE       VARCHAR2(20 CHAR),
  OPERATING_DATE         DATE,
  BILLING_DATE_TIME      DATE,
  PREPAYMENTS            NUMBER(10,2),
  TOTAL_COSTS            NUMBER(12,4),
  TOTAL_CHARGES          NUMBER(12,4),
  GUARANTOR              VARCHAR2(8 CHAR),
  GUARANTOR_ORG          VARCHAR2(40 CHAR),
  GUARANTOR_PHONE_NUM    VARCHAR2(16 CHAR),
  BILL_CHECKED_DATE_TIME DATE,
  SETTLED_INDICATOR      NUMBER(1),
  LEND_BED_NO            NUMBER(3),
  BED_DEPT_CODE          VARCHAR2(10 CHAR),
  BED_WARD_CODE          VARCHAR2(10 CHAR),
  DEPT_CODE_LEND         VARCHAR2(10 CHAR),
  LEND_INDICATOR         NUMBER(1),
  IS_NEWBORN             NUMBER(1)
)
-- Add comments to the table 
comment on table PATS_IN_HOSPITAL
  is '在院病人记录';
-- Add comments to the columns 
comment on column PATS_IN_HOSPITAL.ID
  is '病人标识号';
comment on column PATS_IN_HOSPITAL.HOSID
  is '医院ID';
comment on column PATS_IN_HOSPITAL.VISIT_ID
  is '病人本次住院标识';
comment on column PATS_IN_HOSPITAL.WARD_CODE
  is '所在病房代码';
comment on column PATS_IN_HOSPITAL.DEPT_CODE
  is '所在科室代码';
comment on column PATS_IN_HOSPITAL.BED_NO
  is '床号';
comment on column PATS_IN_HOSPITAL.ADMISSION_DATE_TIME
  is '入院日期及时间';
comment on column PATS_IN_HOSPITAL.ADM_WARD_DATE_TIME
  is '入科日期及时间';
comment on column PATS_IN_HOSPITAL.DIAGNOSIS
  is '主要诊断';
comment on column PATS_IN_HOSPITAL.PATIENT_CONDITION
  is '病情状态';
comment on column PATS_IN_HOSPITAL.NURSING_CLASS
  is '护理等级';
comment on column PATS_IN_HOSPITAL.DOCTOR_IN_CHARGE
  is '经治医生';
comment on column PATS_IN_HOSPITAL.OPERATING_DATE
  is '手术日期';
comment on column PATS_IN_HOSPITAL.BILLING_DATE_TIME
  is '计价截止日期及时间';
comment on column PATS_IN_HOSPITAL.PREPAYMENTS
  is '预交金余额';
comment on column PATS_IN_HOSPITAL.TOTAL_COSTS
  is '累计未结费用';
comment on column PATS_IN_HOSPITAL.TOTAL_CHARGES
  is '优惠后未结费用';
comment on column PATS_IN_HOSPITAL.GUARANTOR
  is '经济担保人';
comment on column PATS_IN_HOSPITAL.GUARANTOR_ORG
  is '经济担保人所在单位';
comment on column PATS_IN_HOSPITAL.GUARANTOR_PHONE_NUM
  is '经济担保人联系电话';
comment on column PATS_IN_HOSPITAL.BILL_CHECKED_DATE_TIME
  is '上次划价检查日期';
comment on column PATS_IN_HOSPITAL.SETTLED_INDICATOR
  is '出院结算标记';