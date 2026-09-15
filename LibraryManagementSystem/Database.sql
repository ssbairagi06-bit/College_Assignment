CREATE DATABASE library_db;
USE library_db;

CREATE TABLE books (
    book_id VARCHAR(20) NOT NULL,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    PRIMARY KEY (book_id)
);

CREATE TABLE users (
    user_id VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    user_type VARCHAR(20) NOT NULL,
    borrow_limit INT DEFAULT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE borrowed_books (
    borrow_id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL,
    book_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (borrow_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);