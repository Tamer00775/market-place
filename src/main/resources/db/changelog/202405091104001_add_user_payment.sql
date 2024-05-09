-- Insert a new user payment record for the user with login "admin"
INSERT INTO user_payment (
    user_id, payment_type, provider, account_no, expiry, created_date
)
VALUES (
           (SELECT id FROM _user WHERE login = 'admin'),
           'CREDIT_CARD',
           'Visa',
           '1234567890123456',
           '2025-12-31',
           CURRENT_TIMESTAMP
       );