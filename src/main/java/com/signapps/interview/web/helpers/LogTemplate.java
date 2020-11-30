package com.signapps.interview.web.helpers;

import org.slf4j.Logger;

public class LogTemplate {
    private String issueOrigin = "";
    private String transactionID = "";
    private String sourceIP = "";
    private String sourceSystem = "";
    private String serviceID = "";
    private String hostInfo = "";
    private long transactionCost = 0;
    private String operationName = "";
    private String responseCode = "";
    private String responseMsg = "";
    private long startTime = System.currentTimeMillis();

    // Set the current line number
    String setIssueOrigin(StackTraceElement st) {
        return st.getMethodName() + "(" + st.getFileName() + ":" + st.getLineNumber() + ")";
    }

    // Builder Methods
    public LogTemplate traceid(String transactionID) {
        this.transactionID = transactionID;
        return this;
    }

    public LogTemplate sourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
        return this;
    }

    public LogTemplate sourceApp(String sourceSystem) {
        this.sourceSystem = sourceSystem;
        return this;
    }

    public LogTemplate serviceID(String serviceID) {
        this.serviceID = serviceID;
        return this;
    }

    public LogTemplate hostInfo(String hostInfo) {
        this.hostInfo = hostInfo;
        return this;
    }

    public LogTemplate operation(String transactionType) {
        this.operationName = transactionType;
        return this;
    }


    public LogTemplate rescode(String responseCode) {
        // This will capture the line the log will originate from
        this.issueOrigin = setIssueOrigin(Thread.currentThread().getStackTrace()[2]);
        this.responseCode = responseCode;
        return this;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CUSTOM HELPER METHODS                                                                                          //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Fetch the exact line of code a log entry is originating from + Compute transaction Cost
    public LogTemplate time(long startTime) {
        this.startTime = startTime;
        this.transactionCost = (System.currentTimeMillis() - this.startTime);
        return this;
    }

    public void info(Logger log) {
        this.transactionCost = (System.currentTimeMillis() - this.startTime);
        log.info("{} | Line={}", this, this.issueOrigin);
    }

    // Logger Helper Methods
    public void error(Logger log) {
        log.error("{} | ErrorLine={}", this, this.issueOrigin);
    }

    public void warn(Logger log) {
        this.transactionCost = (System.currentTimeMillis() - this.startTime);
        log.warn("{} | WarnLine={}", this, this.issueOrigin);
    }

    public void debug(Logger log) {
        this.transactionCost = (System.currentTimeMillis() - this.startTime);
        log.debug("{} | DebugLine={}", this, this.issueOrigin);
    }

    @Override
    public String toString() {
        return "OperationName=" + operationName +
                " | ServiceID=" + serviceID +
                " | refID=" + transactionID +
                " | SourceSystem=" + sourceSystem +
                " | HostInfo=" + hostInfo +
                " | TransactionCost=" + transactionCost +
                " | ResponseCode=" + responseCode +
                " | ResponseMsg=" + responseMsg;
    }
}
