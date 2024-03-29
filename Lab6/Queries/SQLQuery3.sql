use Lab06

SELECT * FROM PLANETS 
INNER JOIN SATELLITES 
ON PLANETS.Name = SATELLITES.Planet_Name
WHERE PLANETS.Have_Life = 1;

SELECT PLANETS.Name, PLANETS.Radius, COUNT(PLANETS.Name) as Satellites_Number
FROM PLANETS INNER JOIN SATELLITES 
ON PLANETS.Name = SATELLITES.Planet_Name
GROUP BY PLANETS.Name, PLANETS.Radius
ORDER BY PLANETS.Radius ASC, Satellites_Number DESC

SELECT TOP(1) PLANETS.Name, PLANETS.Radius, SUM(SATELLITES.Radius) as Sum_Of_Radiuses
FROM PLANETS INNER JOIN SATELLITES 
ON PLANETS.Name = SATELLITES.Planet_Name
GROUP BY PLANETS.Name, PLANETS.Radius
ORDER BY Sum_Of_Radiuses DESC

SELECT PLANETS.Name, PLANETS.Radius, COUNT(PLANETS.Name) as Satellites_Number
FROM PLANETS INNER JOIN SATELLITES 
ON PLANETS.Name = SATELLITES.Planet_Name
GROUP BY PLANETS.Name, PLANETS.Radius HAVING COUNT(*) > 1;