package com.dbhacademy.firefight.model;

import org.hibernate.dialect.Oracle10gDialect;

public class BddOracleDialect extends Oracle10gDialect {

    public BddOracleDialect() {
        super();
    }

    @Override
    public String getCreateSequenceString(final String sequenceName) {
        String createSequenceString = super.getCreateSequenceString(sequenceName);
        return createSequenceString + " START WITH 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 NOCYCLE NOCACHE ORDER";
    }
}