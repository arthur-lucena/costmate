CREATE TABLE CREDIT_CARD (
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    DAY_DUE_DATE SMALLINT,
    DAYS_TO_CLOSE SMALLINT
);

ALTER TABLE EXPENSE
ADD COLUMN CREDIT_CARD_ID BIGINT;

ALTER TABLE EXPENSE
ADD CONSTRAINT FK_EXPENSE_CREDIT_CARD
FOREIGN KEY (CREDIT_CARD_ID)
REFERENCES CREDIT_CARD (ID);