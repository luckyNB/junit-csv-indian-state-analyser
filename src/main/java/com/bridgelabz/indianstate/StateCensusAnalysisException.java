package com.bridgelabz.indianstate;

public class StateCensusAnalysisException extends Exception {
    ExceptionType type;
    private String message;

    public StateCensusAnalysisException(String message) {
        super(message);
        this.message = message;
    }

    public StateCensusAnalysisException(ExceptionType type) {
        super(String.valueOf(type));

    }

    enum ExceptionType {
        NO_SUCH_FILE, WRONG_DATA_FORMAT, WRONG_FILE_TYPE
    }


}