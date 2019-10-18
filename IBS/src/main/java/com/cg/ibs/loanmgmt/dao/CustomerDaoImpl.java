package com.cg.ibs.loanmgmt.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cg.ibs.loanmgmt.bean.CustomerBean;
import com.cg.ibs.loanmgmt.bean.Document;
import com.cg.ibs.loanmgmt.bean.LoanMaster;
import com.cg.ibs.loanmgmt.bean.LoanStatus;
import com.cg.ibs.loanmgmt.bean.LoanType;
import com.cg.ibs.loanmgmt.exception.ExceptionMessages;
import com.cg.ibs.loanmgmt.exception.IBSException;
import com.cg.ibs.loanmgmt.util.dbUtil;

public class CustomerDaoImpl implements CustomerDao {
	// private static DataBase base = new DataBase();
	private static Map<String, LoanMaster> loanData = base.getLoanMasterData();
	private static Map<String, CustomerBean> customerData = base.getCustomerBeanData();
	private static LoanMaster loanMaster = new LoanMaster();
	private static CustomerBean customer = new CustomerBean();

//Done For SQL

	public LoanMaster updateEMI(LoanMaster loanMaster) {
//		loanMaster.setNumberOfEmis(loanMaster.getNumberOfEmis() + 1);
//		loanMaster.setNextEmiDate(loanMaster.getNextEmiDate().plusMonths(1));
//		loanData.replace(loanMaster.getLoanNumber(), loanMaster);
//		return loanMaster;
		Connection connection = dbUtil.getConnection();
		String sqlst = "update loan set num_of_emis_paid = ? where loan_number = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlst);) {
			preparedStatement.setInt(1, (loanMaster.getNumberOfEmis() + 1));

			// in case we go for next emi date
			/*
			 * 
			 * LocalDate updatedNextEmiDate = loanMaster.getNextEmiDate().plusMonths(1);
			 * java.sql.Date date = java.sql.Date.valueOf(updatedNextEmiDate);
			 * preparedStatement.setDate(2, date);
			 * 
			 * 
			 */
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// throw new IBSException(ExceptionMessages.MESSAGEFOREXCEPTION);
		}
	}

	public LoanMaster getEMIDetails(String loanNumber) {
//		loanMaster = null;
//		if (loanData.containsKey(loanNumber)) {
//			loanMaster = loanData.get(loanNumber);
//		}
//		return loanMaster;
		Connection connection = dbUtil.getConnection();
		String sqlst1 = "select loan_number from loan where loan_number =?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlst1);) {
			preparedStatement.setInt(1, Integer.valueOf(loanNumber));
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if(resultSet.next()) {
					if(resultSet.getInt("loan_number") == Integer.valueOf(loanNumber)) {
				
					String sqlst2 = "select emi_amount from loan where loan_number =?";
					try (PreparedStatement preparedStatement2 = connection.prepareStatement(sqlst2);) {
						preparedStatement2.setString(1, loanNumber);
						try (ResultSet resultSet2 = preparedStatement.executeQuery()) {
//							private String loanNumber;
//							private LoanStatus loanStatus;
//							private long applicationNumber;
//							private double loanAmount;
//							private int loanTenure;
//							private LoanType loanType;
//							private float interestRate;
//							private double balance;
//							private double emiAmount;
//							private CustomerBean customerBean;
//							private int numberOfEmis;
//							private int totalNumberOfEmis;
//							private LocalDate appliedDate;
//							private LocalDate nextEmiDate;
							//loanMaster.setLoanNumber(resultSet2.getInt("loan_number"));
							//loanMaster.setLoanStatus(resultSet2.getString(""));
							loanMaster.setEmiAmount(resultSet2.getDouble("emi_amount"));
						}	
						}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// throw new IBSException(ExceptionMessages.MESSAGEFOREXCEPTION);
		}
	}

	@Override
	public CustomerBean getCustomerDetails(String userId) throws SQLException, IBSException {
		customer = null;
		Connection connection = dbUtil.getConnection();

		try (PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.GET_CUSTOMER_DETAILS);) {
			preparedStatement.setString(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				customer.setUCI(resultSet.getBigDecimal("uci").toBigInteger());
				customer.setFirstName(resultSet.getString("first_name"));
				customer.setLastName(resultSet.getString("last_name"));
				customer.setUserId(resultSet.getString("user_id"));
			}

		}
		return customer;
	}

	// Done For SQL

	// LoanDetails
	public List<LoanMaster> getHistory(String userId) throws IBSException { /* getting list of loans */
		List<LoanMaster> loanMasters = new ArrayList<>();
		Connection connection = dbUtil.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.SEL_THE_COMMON_ROWS);) {
			preparedStatement.setString(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					LoanMaster loanMaster = new LoanMaster();
					CustomerBean customer = new CustomerBean();
					customer.setFirstName(resultSet.getString("first_name"));
					customer.setLastName(resultSet.getString("last_name"));
					customer.setUCI(resultSet.getBigDecimal("uci").toBigInteger());
					customer.setUserId(resultSet.getString("user_id"));
					loanMaster.setCustomerBean(customer);
					loanMaster.setLoanNumber(resultSet.getInt("loan_number"));
					loanMaster.setLoanAmount(resultSet.getDouble("loan_amount"));
					loanMaster.setLoanTenure(resultSet.getInt("loan_tenure"));
					loanMaster.setNumberOfEmis(resultSet.getInt("num_of_emis_paid"));
					loanMaster.setTotalNumberOfEmis(resultSet.getInt("total_num_of_emis"));
					loanMaster.setEmiAmount(resultSet.getDouble("emi_amount"));
					if (resultSet.getInt("type_id") == 1) {
						loanMaster.setLoanType(LoanType.HOME_LOAN);
					} else if (resultSet.getInt("type_id") == 2) {
						loanMaster.setLoanType(LoanType.EDUCATION_LOAN);
					} else if (resultSet.getInt("type_id") == 3) {
						loanMaster.setLoanType(LoanType.PERSONAL_LOAN);
					} else if (resultSet.getInt("type_id") == 4) {
						loanMaster.setLoanType(LoanType.VEHICLE_LOAN);
					}
					loanMaster.setAppliedDate(resultSet.getDate("applied_date").toLocalDate());
					loanMasters.add(loanMaster);
				}
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			throw new IBSException(ExceptionMessages.MESSAGEFORSQLEXCEPTION);
		}
		return loanMasters;
	}

	// PreClosure
	public LoanMaster getPreClosureLoanDetails(String loanNumber) {
		/* Fetch loan Details against the loan number */
		loanMaster = null;
		if (loanData.containsKey(loanNumber)) {
			loanMaster = loanData.get(loanNumber); // LoanData HashMap
		}

		return loanMaster;
	}

	@Override
	public boolean verifyLoanNumber(String loanNumber) { /* Verification of loan number (Pre Closure) */
		boolean check = false;
		if (loanData.containsKey(loanNumber)) {
			check = true;
		}
		return check;
	}

	public boolean sendPreClosureForVerification(LoanMaster loanMaster)
			throws FileNotFoundException, IOException { /* Send Loan for Pre Closure */
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./PreClosureDetails.dat"));
		out.writeObject(loanMaster);
		out.close();
		return true;

	}

	public boolean uploadDocument(Document document, LoanMaster laonMaster) throws IBSException {
		boolean isDone = false;
		Connection connection = dbUtil.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.UPLOAD_DOCUMENT);) {
			preparedStatement.setString(1, );
			return isDone;
	}

	// Done For SQL

	@Override
	public boolean verifyCustomer(String userId) throws IBSException {
		boolean check = false;
		Connection connection = dbUtil.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.VERIFY_CUSTOMER);) {
			preparedStatement.setString(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					if (resultSet.getString("user_id").equals(userId)) {
						check = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IBSException(ExceptionMessages.MESSAGEFORSQLEXCEPTION);
			}

		} catch (SQLException exp) {
			exp.printStackTrace();
		}
		return check;
	}

	// Done For SQL

	@Override
	public boolean sendLoanForVerification(LoanMaster loanMaster) throws IBSException {
		boolean check = false;
		BigDecimal uciTemp = null;
		Connection connection = dbUtil.getConnection();
		try (PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.GET_UCI);) {
			preparedStatement.setString(1, loanMaster.getCustomerBean().getUserId());
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (null == uciTemp) {
					if (resultSet.next()) {
						uciTemp = resultSet.getBigDecimal("uci");
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IBSException(ExceptionMessages.MESSAGEFORSQLEXCEPTION);
		}

		try (PreparedStatement preparedStatement = connection.prepareStatement(QueryMapper.INS_APP);) {
			preparedStatement.setDouble(1, loanMaster.getApplicationNumber());
			preparedStatement.setBigDecimal(2, uciTemp);
			preparedStatement.setString(3, "pending");
		} catch (SQLException exp) {

		}

		return false;
	}
}