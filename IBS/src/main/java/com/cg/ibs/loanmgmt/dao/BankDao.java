package com.cg.ibs.loanmgmt.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.cg.ibs.loanmgmt.bean.LoanMaster;

public interface BankDao {
	public boolean saveLoan(LoanMaster loanMaster) throws SQLException;

	public LoanMaster getLoanDetailsForVerification() throws IOException, ClassNotFoundException;

	public LoanMaster getPreClosureDetailsForVerification()
			throws IOException, ClassNotFoundException; /* Fetch loan details pending for verification */

	public LoanMaster updatePreClosure(LoanMaster loanMaster); /* Updates Loan Details */

	boolean copyDocument(String srcPath, String destPath);

}
