package com.cg.ibs.loanmgmt.dao;

public interface QueryMapper {
	public static String SEL_THE_COMMON_ROWS = "select customers.uci, customers.user_id, customers.first_name,customers.last_name,"
			+ "loan.loan_number, loan.loan_amount, loan.loan_tenure, loan.balance,loan.applied_date, "
			+ "loan.total_num_of_emis, loan.num_of_emis_paid, loan.type_id,loan.emi_amount,"
			+ "loan_type.loan_type, loan_type.interest_rate, loan_type.maximum_limit,loan_type.minimum_limit "
			+ "from customers inner join loan on customers.uci=loan.uci inner join loan_type"
			+ " on loan.type_id = loan_type.type_id where customers.user_id=?";
	public static String VERIFY_CUSTOMER = "select user_id from customers where user_id=?";
	public static String GET_UCI = "select uci from customers where user_id=?";
	public static String UPLOAD_DOCUMENT = "Insert into loan_applicant(document) values(?) where uci=?";
	public static String GET_CUSTOMER_DETAILS = "Select uci, user_id, First_name, last_name where user_id=?";
	public static String INS_LOAN = "INSERT INTO loan(loan_number,uci,loan_amount,loan_tenure,balance,applied_date,total_num_of_emis,num_of_emis_paid,type_id,emi_amount) VALUES(?,?,?,?,?,?,?,?,?,?)";
	public static String INS_APP = "Insert into loan_applicant (loan_applicant.Applicant_num, loan_applicant.status, customers.uci from customers right join loan_applicant on customers.uci=loan_applicant.uci) values(?,?,?)";
}
