package com.cg.ibs.loanmgmt.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.cg.ibs.loanmgmt.bean.CustomerBean;
import com.cg.ibs.loanmgmt.bean.LoanMaster;
import com.cg.ibs.loanmgmt.bean.LoanType;

public class DataBase {

	private static Map<String, LoanMaster> loanMasterData = new HashMap<String, LoanMaster>();
	private static Map<String, CustomerBean> customerBeanData = new HashMap<String, CustomerBean>();
	static {
		CustomerBean customerBean = new CustomerBean("Divyam", "Batta", "12345", "dbatta");
		CustomerBean customerBean1 = new CustomerBean("Chetan", "Kohli", "12346", "ckohli");
		CustomerBean customerBean2 = new CustomerBean("Divyasnh", "Batta", "12347", "dgoyal");
		CustomerBean customerBean3 = new CustomerBean("Yuvraj", "Kalra", "12348", "ykalra");
		CustomerBean customerBean4 = new CustomerBean("yuasa", "rese", "12322", "tatata");

		customerBeanData.put(customerBean.getUserId(), customerBean);
		customerBeanData.put(customerBean1.getUserId(), customerBean1);
		customerBeanData.put(customerBean2.getUserId(), customerBean2);
		customerBeanData.put(customerBean3.getUserId(), customerBean3);
		customerBeanData.put(customerBean4.getUserId(), customerBean4);

		LoanMaster loanMaster = new LoanMaster("997", LoanType.HOME_LOAN, 2500000.00, 120, 30996.0, customerBean,
				60, 120, LocalDate.of(2014, 1, 25), LocalDate.now());
		LoanMaster loanMaster1 = new LoanMaster("998", LoanType.VEHICLE_LOAN, 1000000.00, 24, 45800.0, customerBean1,
				16, 24, LocalDate.of(2018, 6, 15), LocalDate.now());
		LoanMaster loanMaster2 = new LoanMaster("999", LoanType.PERSONAL_LOAN, 500000.00, 24, 23245.0, customerBean2, 23,
				24, LocalDate.of(2017, 9, 12), LocalDate.now());
		LoanMaster loanMaster3 = new LoanMaster("1000", LoanType.EDUCATION_LOAN, 1000000.00, 60, 21917.0, customerBean3,
				50, 60, LocalDate.of(2015, 12, 11), LocalDate.now());
		LoanMaster loanMaster4 = new LoanMaster("996", LoanType.VEHICLE_LOAN, 1000000.00, 24, 45800.0, customerBean3, 16,
				24, LocalDate.of(2018, 6, 22), LocalDate.now());
		LoanMaster loanMaster5 = new LoanMaster("995", LoanType.HOME_LOAN, 2500000.00, 120, 30996.0, customerBean4,
				60, 120, LocalDate.of(2014, 1, 5), LocalDate.now());
		;

		loanMasterData.put(loanMaster.getLoanNumber(), loanMaster);
		loanMasterData.put(loanMaster1.getLoanNumber(), loanMaster1);
		loanMasterData.put(loanMaster2.getLoanNumber(), loanMaster2);
		loanMasterData.put(loanMaster3.getLoanNumber(), loanMaster3);
		loanMasterData.put(loanMaster4.getLoanNumber(), loanMaster4);
		loanMasterData.put(loanMaster5.getLoanNumber(), loanMaster5);

	}

	public static Map<String, LoanMaster> getLoanMasterData() {
		return loanMasterData;
	}

	public static void setLoanMasterData(Map<String, LoanMaster> loanMasterData) {
		DataBase.loanMasterData = loanMasterData;
	}

	public static Map<String, CustomerBean> getCustomerBeanData() {
		return customerBeanData;
	}

	public static void setCustomerBeanData(Map<String, CustomerBean> customerBeanData) {
		DataBase.customerBeanData = customerBeanData;
	}

}
