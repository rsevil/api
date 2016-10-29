/* Reporte 1: ranking de clientes con mayor cantidad de reclamos */

select	c.nombre as nombreCliente, 
		count(*) as cantidad
from Reclamo r
inner join Cliente c on c.nroCliente = r.nroCliente
group by c.nombre
order by count(*) desc


/* Reporte 2: cantidad de reclamos tratados por mes */

select	datepart(mm, fecha) as mes, 
		datepart(yy, fecha) as anio, 
		count(*) as cantidad
from Reclamo
group by datepart(mm, fecha), datepart(yy, fecha) 


/* Reporte 3: ranking de tratamiento de reclamos */

declare @fechaDesde as datetime
declare @fechaHasta as datetime

set @fechaDesde = '20161009'
set @fechaHasta = '20161026'

select 
		--r.tipoReclamo,
		case 
			when r.tipoReclamo = 'ReclamoFacturacion' then 'facturacion'
			when r.tipoReclamo = 'ReclamoCantidades' then 'distribucion'
			when r.tipoReclamo = 'ReclamoFaltantes' then 'distribucion'
			when r.tipoReclamo = 'ReclamoProducto' then 'distribucion'
			when r.tipoReclamo = 'ReclamoZona' then 'zona'
			else '' 
		end as nombreUsuario,
		count(*) as cantidad
from Reclamo r
where r.fecha between @fechaDesde and @fechaHasta and r.tipoReclamo <> 'ReclamoCompuesto' 
group by r.tipoReclamo
order by count(*) desc


/* Reporte 4: tiempo promedio de respuesta de los reclamos por responsable */

declare @fechaDesde2 as datetime
declare @fechaHasta2 as datetime

set @fechaDesde2 = '20161009'
set @fechaHasta2 = '20161026'

select 
		--r.tipoReclamo,
		case 
			when r.tipoReclamo = 'ReclamoFacturacion' then 'facturacion'
			when r.tipoReclamo = 'ReclamoCantidades' then 'distribucion'
			when r.tipoReclamo = 'ReclamoFaltantes' then 'distribucion'
			when r.tipoReclamo = 'ReclamoProducto' then 'distribucion'
			when r.tipoReclamo = 'ReclamoZona' then 'zona'
			else '' 
		end as nombreUsuario,
		avg(datediff(hh, r.fecha, r.fechaCierre)) as tiempoRespuestaPromedio
from Reclamo r
where r.fecha between @fechaDesde2 and @fechaHasta2 
	and r.tipoReclamo <> 'ReclamoCompuesto' 
	and r.estado = 'Cerrado'
	and r.fechaCierre is not null
group by r.tipoReclamo



/* Auxiliares para pruebas */

select * from Reclamo

select * from Cliente

select * from Usuario

select * from Rol

select distinct tipoReclamo from Reclamo

select * from Usuario u inner join Rol r on r.id = u.idRol