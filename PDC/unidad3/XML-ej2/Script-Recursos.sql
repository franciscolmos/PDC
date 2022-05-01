use pdc
go

/* ----------------------------------------------
   Tabla: personal
   ---------------------------------------------- */
create table dbo.personal
(
 nro_leg_personal		integer			not null,
 apellido				varchar(40)		not null,
 nombre					varchar(255)	not null,
 cod_tipo_documento		varchar(3)		not null,
 nro_documento			varchar(20)		not null,
 constraint PK__personal__END 
			primary key (nro_leg_personal),
 constraint UK__personal__END
			unique(cod_tipo_documento, nro_documento),
 constraint CK__personal__cod_tipo_documento__END 
			check (cod_tipo_documento in ('DNI', 'LE', 'LC', 'CI', 'PAS'))
);
go

declare @nro_leg_personal integer = 1

while @nro_leg_personal <= 100 
  begin
    insert into dbo.personal(nro_leg_personal, apellido, nombre, cod_tipo_documento, nro_documento)
    values(@nro_leg_personal, 'APELLIDO ' + right(replicate('0', 3) + convert(varchar(8), @nro_leg_personal), 3), 'NOMBRE ' + right(replicate('0', 3) + convert(varchar(8), @nro_leg_personal), 3), 'DNI', right(replicate('0', 8) + convert(varchar(8), @nro_leg_personal), 8))
	
	set @nro_leg_personal = @nro_leg_personal + 1
  end
go

-- select * from dbo.personal

/* ----------------------------------------------
   Tabla: areas
   ---------------------------------------------- */
create table dbo.areas
(
 nro_area				tinyint			not null,
 nom_area				varchar(255)	not null,
 constraint PK__areas__END
			primary key (nro_area)
);
go

declare @nro_area integer = 1

while @nro_area <= 10 
  begin
    insert into dbo.areas(nro_area, nom_area)
    values(@nro_area, 'AREA ' + right(replicate('0', 2) + convert(varchar(5), @nro_area), 2))
	
	set @nro_area = @nro_area + 1
  end
go

-- select * from dbo.areas

/* ----------------------------------------------
   Tabla: recursos
   ---------------------------------------------- */
create table dbo.recursos
(
 nro_recurso			integer			not null	identity(1,1),
 desc_recurso			varchar(255)	not null,
 cod_tipo_recurso		char(1)			not null,
 nro_leg_personal		integer			null,
 nro_area				tinyint			null,
 fecha_hora_registro	datetime		not null	constraint DF__recursos__fecha_hora_registro__current_timestamp__END default current_timestamp,
 vigente				char(1)			not null	constraint DF__recursos__vigente__S__END default 'S',
 constraint PK__recursos__END
			primary key (nro_recurso),
 constraint FK__recursos__personal__END
			foreign key (nro_leg_personal) references dbo.personal,
 constraint FK__recursos__areas__END
			foreign key (nro_area) references dbo.areas,
 constraint CK__recursos__cod_tipo_recurso__END
			check (cod_tipo_recurso in ('C', 'I')),
 constraint CK__recursos__pertenece_a__END
			check (nro_leg_personal is not null and nro_area is null or
			       nro_leg_personal is null     and nro_area is not null),
 constraint CK__recursos__vigentet__END
			check (vigente in ('S', 'N'))
);
go

insert into dbo.recursos(desc_recurso, cod_tipo_recurso, nro_leg_personal, nro_area, vigente)
values('RECURSO 01', 'C', 2, null, 'S'),('RECURSO 02', 'I', 3, null, 'S'),('RECURSO 03', 'C', null, 4, 'N'),('RECURSO 04', 'C', null, 7, 'S'),('RECURSO 05', 'I', null, 10, 'N')
go

/* ----------------------------------------------
   Procedimiento: get_lista_propietarios
   ---------------------------------------------- */
create procedure dbo.get_lista_propietarios
(
 @tipo_propietario	char(1),
 @nro_leg_personal	integer		= null,
 @nro_area			tinyint		= null
)
as
begin

  if @nro_area = 0
     set @nro_area = null
  if @nro_leg_personal = 0
     set @nro_leg_personal = null

  select nombre           = convert(varchar(300), ''),
         nro_leg_personal = convert(integer, null),
		 nro_area         = convert(tinyint , null),
		 seleccionado     = case when @nro_leg_personal is null 
		                          and @nro_area         is null 
		                         then 'S'
								 else 'N'
						    end,
		 nro_orden        = 1
   union
  select nombre           = apellido + ', ' + nombre,
         nro_leg_personal = nro_leg_personal,
		 nro_area         = convert(tinyint , null),
		 seleccionado     = case when nro_leg_personal = @nro_leg_personal 
		                         then 'S'
								 else 'N'
						    end,
		 nro_orden        = 2
	from dbo.personal (nolock)
   where @tipo_propietario = 'P'
   union 
  select nombre           = nom_area,
         nro_leg_personal = convert(integer, null),
		 nro_area         = nro_area,
		 seleccionado     = case when nro_area = @nro_area 
		                         then 'S'
								 else 'N'
						    end,
		 nro_orden        = 3
	from dbo.areas (nolock)
   where @tipo_propietario = 'A'
   order by nro_orden,
            nombre

end
go    

-- execute dbo.get_lista_propietarios @tipo_propietario='A'
-- execute dbo.get_lista_propietarios @tipo_propietario='A', @nro_area=2
-- execute dbo.get_lista_propietarios @tipo_propietario='P', @nro_leg_personal=4

/* ----------------------------------------------
   Consulta de recursos
   ---------------------------------------------- */
select desc_recurso     = desc_recurso,
       cod_tipo_recurso = cod_tipo_recurso,
	   tipo_propietario = case when nro_leg_personal is not null 
	                           then 'P'
							   else 'A'
						  end,        	   
	   nro_leg_personal = nro_leg_personal,
	   nro_area         = nro_area,
	   vigente          = vigente,
	   nro_recurso      = nro_recurso
  from dbo.recursos (nolock)
 order by desc_recurso

/* ----------------------------------------------
   Inserción de un recurso
   ---------------------------------------------- */
declare @desc_recurso		varchar(255)	= 'RECURSO',
        @cod_tipo_recurso	char(1)			= 'C',
        @nro_leg_personal	integer			= 10,
        @nro_area			tinyint			= null,
        @vigente			char(1)			= 'S'

insert into dbo.recursos(desc_recurso, cod_tipo_recurso, nro_area, nro_leg_personal, vigente)
values(@desc_recurso, @cod_tipo_recurso, @nro_area, @nro_leg_personal, @vigente)
go

/* ----------------------------------------------
   Actualización de un recurso
   ---------------------------------------------- */
declare @nro_recurso		integer			= 2,
        @desc_recurso		varchar(255)	= 'RECURSO 22',
        @cod_tipo_recurso	char(1)			= 'C',
        @nro_leg_personal	integer			= 10,
        @nro_area			tinyint			= null,
        @vigente			char(1)			= 'S'

update dbo.recursos
   set desc_recurso     = @desc_recurso,
       cod_tipo_recurso = @cod_tipo_recurso,
	   nro_leg_personal = @nro_leg_personal,
	   nro_area         = @nro_area,
	   vigente          = @vigente
 where nro_recurso = @nro_recurso
go 
