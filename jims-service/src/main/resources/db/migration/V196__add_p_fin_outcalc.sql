/*确认收费 存储过程*/
create or replace procedure p_fin_outcalc
  (
  I_IDS  IN VARCHAR2,  /*待划价项目id字符串 ,分割*/
  O_SCCE_FLAG     OUT number  /*返回值 0为正确 -1为失败*/
  ) as
  v_ids VARCHAR2(2000) :='';
  v_id VARCHAR2(64) :='';
  v_new_pos integer :=1;
  v_outp_orders outp_orders%rowtype;
  v_clinic_master clinic_master%rowtype;
  v_outp_presc outp_presc%rowtype;
  v_bill_date date;
  v_costs number(12,8);
  v_charges number(12,8);
  v_rcpt_no varchar2(20);
  v_appoints_Id varchar2(64);
  v_order_class varchar2(10);/*标记：用来表示 收费类型，：检验，检查，处方*/

  begin
  select sysdate into v_bill_date from dual;/*获取当前系统时间*/
  select to_char(sysdate,'yyyyMMddHHmmss')||trunc(dbms_random.value(0,1000))into v_rcpt_no from dual;/*根据当前日期+随机数 生成 收据号*/
  v_ids := I_IDS;
  v_new_pos := instr(v_ids,',');

  if v_new_pos>1 then

    while(v_new_pos>1) loop

     v_id := substr(v_ids,1,v_new_pos-1);

     v_ids:=substr(v_ids,v_new_pos+1);

     v_new_pos:=instr(v_ids,',');

     dbms_output.put_line(v_id);

    /*根据截取outp_orders Id  查询outpOrders对象*/
    select * into v_outp_orders from outp_orders where id = v_id;
    select sum(costs) into v_costs from outp_orders_costs where serial_no=v_outp_orders.serial_no;
    select sum(charges) into v_charges from outp_orders_costs where serial_no=v_outp_orders.serial_no;
     /*更新 outp_orders_costs*/
    update outp_orders_costs set charge_indicator =1 ,BILL_DATE=v_bill_date,rcpt_no=v_rcpt_no where serial_no=v_outp_orders.serial_no;

    /*根据 serial_no 查询 所有的收费明细*/
    DECLARE CURSOR costs IS
    select * from outp_orders_costs where serial_no=v_outp_orders.serial_no;
    BEGIN
    /*循环 costsList*/
    for t_costs in  costs loop
      /*更新 outp_treat_rec */
    update outp_treat_rec  set charge_indicator =1 ,BILL_VISIT_DATE  =v_bill_date,rcpt_no=v_rcpt_no where serial_no=t_costs.serial_no;
     /*查询 申请id */
    select APPOINT_NO into v_appoints_Id from outp_treat_rec where serial_no=t_costs.serial_no;

    /*开始判断 是否是药品(AorB)，检查(D)，检验(C)*/
    if t_costs.order_class ='D' then

   /*更新 exam_items */
    update exam_items set BILLING_INDICATOR  =1 , rcpt_no=v_rcpt_no where APPOINTS_ID=v_appoints_Id ;
     /*更新 exam_appoints 预约记录*/
    update exam_appoints set costs=v_costs,charges=v_charges where id=v_appoints_Id ;

     else if t_costs.order_class  ='C' then

    /*更新 lab_test_items*/
    update lab_test_items set BILLING_INDICATOR =1 ,rcpt_no=v_rcpt_no where APPOINTS_ID=v_appoints_Id;
    /*更新 lab_test_master*/
    update lab_test_master set costs=v_costs,charges=v_charges ,BILLING_INDICATOR =1 where id=v_appoints_Id;

    else if t_costs.order_class ='A' then /*处方*/

       select * into v_outp_presc from outp_presc where serial_no=t_costs.serial_no;
       update outp_presc set drug_presc_date=v_bill_date ,drug_presc_no='1403' where serial_no=t_costs.serial_no;
       INSERT INTO drug_presc_detail_temp (
       presc_date ,
       presc_no ,
       item_no ,
       drug_code ,
       drug_spec ,
       drug_name ,
       firm_id ,
       package_spec ,
       package_units ,
       quantity ,
       costs ,
       payments ,
       administration ,
       ORDER_NO ,
       ORDER_SUB_NO ,
       DOSAGE_EACH ) VALUES
       (
       v_bill_date,
       v_outp_presc.presc_no,
       v_outp_presc.item_no,
       v_outp_presc.drug_code,
       v_outp_presc.drug_spec,
       v_outp_presc.drug_name,
       v_outp_presc.firm_id,
       '',/*包装规格*/
       '',/*单位规格*/
       v_outp_presc.amount,
       v_outp_presc.costs,
       v_outp_presc.charges,
       v_outp_presc.administration,
       t_costs.order_no,
       t_costs.order_sub_no,
       v_outp_presc.dosage
        );
      end if;
    end if;
    end if;
     /*判断结束*/
    /*insert outp_bill_items 门诊病人诊疗收费项目明细*/
    INSERT INTO outp_bill_items(
      id,
      visit_date,
      visit_no,
      rcpt_no,
      item_no,
      item_class,
      class_on_rcpt,
      item_code,
      item_name,
      item_spec,
      amount,
      units,
      performed_by,
      costs,
      charges,
      confirmed_operator,
      invoice_no,
      flag,
      class_on_reckoning,
      subj_code,
      price_quotiety,
      item_price,
      order_no,
      sub_order_no,
      administration,
      ward_code,
      performed_by_sub)values(
         sys_guid() ,
         t_costs.visit_date,
         t_costs.visit_no,
         v_rcpt_no,
         t_costs.item_no,
         t_costs.item_class,
         'R',
         t_costs.item_code,
         t_costs.item_name,
         t_costs.item_spec,
         t_costs.amount,
         t_costs.units,
         t_costs.performed_by,
         t_costs.costs,
         t_costs.charges,
         '',
         '',
         1,
         t_costs.class_on_reckoning,
         t_costs.subj_code,
         t_costs.price_quotiety,
         t_costs.item_price,
         t_costs.order_no,
         t_costs.order_sub_no,
         null,
         t_costs.ward_code,
         '*');

   end loop;/*对象 循环结束*/

    end;


    /*根据orders表中的clinic_id 查询出就诊记录基本信息*/
    select * into v_clinic_master from clinic_master where id=v_outp_orders.clinic_id;

  /*insert outp_rcpt_master 门诊医疗收据记录*/
    INSERT INTO outp_rcpt_master(
      id,
      rcpt_no,
      patientid,
      name,
      name_phonetic,
      identity,
      charge_type,
      unit_in_contract,
      visit_date,
      total_costs,
      total_charges,
      operator_no,
      charge_indicator,
      refunded_rcpt_no,
      acct_no,
      printed_rcpt_no
    ) VALUES (
      sys_guid() ,
      v_rcpt_no,
      v_clinic_master.id,
      v_clinic_master.name,
      v_clinic_master.name_phonetic,
      v_clinic_master.identity,
      v_clinic_master.charge_type,
      v_clinic_master.unit_in_contract,
      v_clinic_master.visit_date,
      v_costs,
      v_charges,
      '当前登录人',
      0,
      null,
      null,
      '');
      /*更新 clinic_master*/
      update clinic_master set REGISTRATION_STATUS =1 where id=v_outp_orders.clinic_id;
      /*insert outp_order_desc 开单记录*/
      INSERT INTO outp_order_desc(
      id,
      visit_date,
      visit_no,
      presc_indicator,
      ordered_by_dept,
      ordered_by_doctor,
      rcpt_no,
      presc_attr,
      clinic_no,
      order_group
    ) values (
       sys_guid() ,
       v_bill_date,
       v_rcpt_no,
       0,
       '104610',/*开单科室*/
       '医生',/*开单医生*/
       v_rcpt_no,
       '',
       v_clinic_master.clinic_no,
       ''
    );
    if v_order_class='A' then /*只有是药品时执行该语句*/
   /*处方代发药主表  drug_presc_master_temp  插入数据*/
   INSERT INTO drug_presc_master_temp (
     presc_date ,
     presc_no ,
     patient_id ,
     name ,
     name_phonetic ,
     identity ,
     charge_type ,
     unit_in_contract ,
     presc_type ,
     presc_source ,
     repetition ,
     ordered_by ,
     prescribed_by ,
     rcpt_no ,
     entered_by ,
     presc_attr ,
     clinic_no ,
     DOCTOR_USER ) values(
     v_bill_date,
     '1403',/*发药 处方号，生成规则？*/
     '',
     v_clinic_master.name,
     v_clinic_master.name_phonetic,
     v_clinic_master.identity,
     v_clinic_master.charge_type,
     v_clinic_master.unit_in_contract,
     0,/*0:西药，1：中药*/
     0,/*0:门诊，1：住院，2：其他*/
     1,/*剂数：从处方数据中取，还未定义变量*/
     '101000',/*开单科室 --数据未知*/
     '00XX',/*开单医生，*/
     v_rcpt_no,
     '00XX',/*当前登录人*/
     '',/*处方属性，从处方明细中取出*/
     v_clinic_master.clinic_no,
     '00XX'/*当前登录人*/
      );
      end if;
   /*insert outp_payments_money 支付方式*/
   INSERT INTO outp_payments_money(
      id,
      rcpt_no,
      payment_no,
      money_type,
      payment_amount,
      refunded_amount
    ) VALUES (
    sys_guid() ,
    v_rcpt_no,
    '',
    '现金',
    v_costs,
    0.0
    );
    end loop;/*id 循环结束*/

  end if;

  O_SCCE_FLAG :=0;

  EXCEPTION
  WHEN OTHERS THEN
            O_SCCE_FLAG :=-1;

end;