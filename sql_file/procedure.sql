DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAdditionalTimekeepingFromTo`(IN `from_date` DATE, IN `to_date` DATE)
BEGIN
SELECT 
	t.id, t.staff_id, t.staff_code, t.day_time_leave, t.time, t.type, t.note , t.is_approved, t.day_approved, IF(t.time = "04:00:00", 0.5, 1) AS number_time,
	CASE
		WHEN sd.id IS NOT NULL THEN '3'
		WHEN DAYOFWEEK(day_time_leave) = 7 OR DAYOFWEEK(day_time_leave) = 1 THEN '2'
		ELSE '1'
	END AS multiply
FROM time_leave AS t
LEFT JOIN special_date AS sd ON t.day_time_leave >= sd.day_special_from AND t.day_time_leave <= sd.day_special_to And sd.type_day = 1
WHERE t.is_approved = 1 
AND day_approved >= from_date
AND day_approved <= to_date
ORDER BY day_approved ASC;
END$$
DELIMITER ;