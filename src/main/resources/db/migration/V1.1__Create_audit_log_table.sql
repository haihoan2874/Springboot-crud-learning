-- V1.1__Create_audit_log_table.sql
-- Create audit logging table for tracking changes

CREATE TABLE IF NOT EXISTS audit_log (
    id BIGSERIAL PRIMARY KEY,
    entity_type VARCHAR(255) NOT NULL,
    entity_id BIGINT NOT NULL,
    action VARCHAR(50) NOT NULL,
    changes JSONB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255)
);

-- Create indexes for audit log queries
CREATE INDEX IF NOT EXISTS idx_audit_log_entity ON audit_log(entity_type, entity_id);
CREATE INDEX IF NOT EXISTS idx_audit_log_created_at ON audit_log(created_at);
CREATE INDEX IF NOT EXISTS idx_audit_log_action ON audit_log(action);

-- Add comments for documentation
COMMENT ON TABLE audit_log IS 'Audit trail for tracking all entity changes';
COMMENT ON COLUMN audit_log.entity_type IS 'The type of entity being audited';
COMMENT ON COLUMN audit_log.entity_id IS 'The ID of the entity being audited';
COMMENT ON COLUMN audit_log.action IS 'The action performed: CREATE, UPDATE, DELETE';
COMMENT ON COLUMN audit_log.changes IS 'JSON representation of changes made';
COMMENT ON COLUMN audit_log.created_by IS 'User who made the change';

