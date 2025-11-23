-- V1.2__Create_postgresql_extensions.sql
-- Install PostgreSQL extensions required by the application

-- UUID extension for generating unique identifiers
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Cryptographic functions extension
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Comments for documentation
COMMENT ON EXTENSION "uuid-ossp" IS 'UUID generation functions';
COMMENT ON EXTENSION "pgcrypto" IS 'Cryptographic functions for hashing and encryption';

