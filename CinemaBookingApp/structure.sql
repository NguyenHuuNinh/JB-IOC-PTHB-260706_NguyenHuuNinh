--Tao bang ticket_bookings
CREATE TABLE ticket_bookings (
   booking_id SERIAL PRIMARY KEY,
   movie_title VARCHAR(150) NOT NULL,
   customer_name VARCHAR(100) NOT NULL,
   show_time TIMESTAMP NOT NULL,
   booking_date TIMESTAMP NOT NULL,
   seat_quantity INT NOT NULL,
   status VARCHAR(30) NOT NULL
);

--1. Funtion lay danh sach tat ca phieu dat ve
CREATE OR REPLACE FUNCTION fn_get_all_bookings()
RETURNS SETOF ticket_bookings
LANGUAGE plpgsql
AS $$
BEGIN
  RETURN QUERY SELECT * FROM ticket_bookings ORDER BY booking_id;
END;
$$;

--2. Procedure them moi phieu dat ve
CREATE OR REPLACE PROCEDURE sp_add_booking(
  p_movie_title VARCHAR,
  p_customer_name VARCHAR,
  p_show_time TIMESTAMP,
  p_booking_date TIMESTAMP,
  p_seat_quantity INT,
  p_status VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
  INSERT INTO ticket_bookings(movie_title, customer_name, show_time, booking_date, seat_quantity, status)
  VALUES ( p_movie_title,  p_customer_name,  p_show_time, p_booking_date, p_seat_quantity, p_status );
END;
$$;

--3. Function lay danh sach phieu dat ve theo ten khach hang customer_name 
CREATE OR REPLACE FUNCTION fn_get_bookings_by_customer (p_customer_name VARCHAR)
RETURNS SETOF ticket_bookings
LANGUAGE plpgsql
AS $$
BEGIN 
  RETURN QUERY SELECT * FROM ticket_bookings WHERE customer_name = p_customer_name;
END;
$$;

--4. Procdure cap nhat thong tin phieu dat ve theo booking_id
CREATE OR REPLACE PROCEDURE sp_update_booking(
  p_booking_id INT,
  p_movie_title VARCHAR,
  p_customer_name VARCHAR,
  p_show_time TIMESTAMP,
  p_booking_date TIMESTAMP,
  p_seat_quantity INT,
  p_status VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN 
  UPDATE ticket_bookings
  SET movie_title = p_movie_title,
      customer_name = p_customer_name,
	  show_time = p_show_time,
	  booking_date = p_booking_date,
	  seat_quantity = p_seat_quantity,
	  status = p_status
  WHERE booking_id = p_booking_id;  
END;
$$;

--5. Procedure xoa phieu dat ve theo booking_id
CREATE OR REPLACE PROCEDURE sp_delete_booking(p_booking INT)
LANGUAGE plpgsql
AS $$
BEGIN 
  DELETE FROM ticket_bookings WHERE booking_id = p_booking_id; 
END;
$$;

--6. Function tim kiem phieu dat ve theo ten phim (tim gan dung, khong phan biet hoa thuong)
CREATE OR REPLACE FUNCTION fn_search_bookings_by_movie(p_movie_title VARCHAR)
RETURNS SETOF ticket_bookings
LANGUAGE plpgsql
AS $$
BEGIN 
  RETURN QUERY SELECT * FROM ticket_bookings WHERE movie_title ILIKE '%' || p_movie_title || '%';
END;
$$;



