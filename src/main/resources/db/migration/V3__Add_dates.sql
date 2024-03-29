-- Adicionar campos DATE_CREATE e DATE_UPDATE na tabela CREDIT_CARD
ALTER TABLE CREDIT_CARD
ADD COLUMN DATE_CREATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN DATE_UPDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Adicionar campos DATE_CREATE e DATE_UPDATE na tabela EXPENSE
ALTER TABLE EXPENSE
ADD COLUMN DATE_CREATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN DATE_UPDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Adicionar campos DATE_CREATE e DATE_UPDATE na tabela INCOME
ALTER TABLE INCOME
ADD COLUMN DATE_CREATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN DATE_UPDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Adicionar campos DATE_CREATE e DATE_UPDATE na tabela PLAN_MONTH
ALTER TABLE PLAN_MONTH
ADD COLUMN DATE_CREATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN DATE_UPDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Criar função para preencher as colunas DATE_UPDATE e DATE_CREATE
CREATE OR REPLACE FUNCTION update_date_columns()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        NEW.DATE_CREATE = CURRENT_TIMESTAMP;
        NEW.DATE_UPDATE = CURRENT_TIMESTAMP;
    ELSIF TG_OP = 'UPDATE' THEN
        NEW.DATE_UPDATE = CURRENT_TIMESTAMP;
        NEW.DATE_CREATE = OLD.DATE_CREATE;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Criar trigger para tabela CREDIT_CARD
CREATE TRIGGER credit_card_date_trigger
BEFORE INSERT OR UPDATE ON CREDIT_CARD
FOR EACH ROW
EXECUTE FUNCTION update_date_columns();

-- Criar trigger para tabela EXPENSE
CREATE TRIGGER expense_date_trigger
BEFORE INSERT OR UPDATE ON EXPENSE
FOR EACH ROW
EXECUTE FUNCTION update_date_columns();

-- Criar trigger para tabela INCOME
CREATE TRIGGER income_date_trigger
BEFORE INSERT OR UPDATE ON INCOME
FOR EACH ROW
EXECUTE FUNCTION update_date_columns();

-- Criar trigger para tabela PLAN_MONTH
CREATE TRIGGER plan_month_date_trigger
BEFORE INSERT OR UPDATE ON PLAN_MONTH
FOR EACH ROW
EXECUTE FUNCTION update_date_columns();