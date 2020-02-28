

DELIMITER //

CREATE PROCEDURE GetAllExchanges()
BEGIN
    SELECT *  FROM exchange_rate;
END //



CREATE PROCEDURE SearchExchange (IN p_origin varchar(120),
                                 IN p_destiny varchar(120) )
BEGIN
    SELECT * FROM exchange_rate WHERE origin = p_origin and destiny=p_destiny
    LIMIT 1;
END //


CREATE PROCEDURE InsertExchange (IN p_origin varchar(120),
                            IN p_destiny varchar(120),
                            IN p_rate double)
BEGIN
    insert into exchange_rate (origin,destiny,rate) values(p_origin,p_destiny,2);
END //


DELIMITER ;

