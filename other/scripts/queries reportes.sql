/* Reporte 1: ranking de clientes con mayor cantidad de reclamos */

select c.nombre as nombreCliente, count(*) as cantidadReclamos 
from Reclamo r
inner join Cliente c on c.nroCliente = r.nroCliente
group by c.nombre
order by count(*) desc


/* Reporte 2: cantidad de reclamos tratados por mes */

select datepart(mm, fecha) as Mes, datepart(yy, fecha) as Anio, count(*) as Cantidad
from Reclamo
group by datepart(mm, fecha), datepart(yy, fecha) 


/* Reporte 3: ranking de tratamiento de reclamos */

-- A QUE SE REFIERE?


/* Reporte 4: tiempo promedio de respuesta de los reclamos por responsable */

-- NO TENEMOS EL RESPONSABLE ASIGNADO A CADA RECLAMO



/* Auxiliares para pruebas */

select * from Reclamo

select * from Cliente

select * from Usuario

select * from Rol