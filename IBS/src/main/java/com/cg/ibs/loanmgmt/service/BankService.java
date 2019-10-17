package com.cg.ibs.loanmgmt.service;

import java.io.IOException;
import java.util.List;

import com.cg.ibs.loanmgmt.bean.LoanMaster;

public interface BankService {

	public LoanMaster getLoanDetailsForVerification() throws IOException, ClassNotFoundException;

	public LoanMaster getPreClosureDetailsForVerification()
			throws IOException, ClassNotFoundException; /* Getting Loan Details */

	public LoanMaster updatePreClosure(LoanMaster loanMaster);

	public boolean verifyLoan(LoanMaster loanMaster) throws Exception;

	public List<String> getFilesAvailable();

	public boolean downloadDocument(String destPath, String fileName);

}
