CREATE TABLE company (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    edinet_code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    security_code VARCHAR(255) NOT NULL UNIQUE,
    corporate_number VARCHAR(255) NOT NULL UNIQUE
);
