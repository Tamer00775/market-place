-- Insert payment status data
INSERT INTO payment_status (name, deleted, created_date)
VALUES
    ('Pending', FALSE, CURRENT_TIMESTAMP),
    ('Completed', FALSE, CURRENT_TIMESTAMP),
    ('Failed', FALSE, CURRENT_TIMESTAMP),
    ('Canceled', FALSE, CURRENT_TIMESTAMP);
