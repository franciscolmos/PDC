use pdc
go

create table dbo.grupos_contactos
(
 cod_area		varchar(10)		not null,
 nro_grupo		smallint		not null,
 nom_grupo		varchar(255)	not null,
 exclusivo		char(1)			not null
			constraint CK__grupos_contactos__exclusivo__END check (exclusivo in ('S', 'N')),
 vigente		char(1)			not null
			constraint CK__grupos_contactos__vigente__END check (vigente in ('S', 'N')),
 constraint PK__grupos_contactos__END
	primary key (cod_area, nro_grupo)
);
go

/*-----------------------------------------------------
  Consulta de grupos registrados
  ----------------------------------------------------- */
declare @cod_area varchar(10) = 'ADM'

select cod_area,
       nro_grupo,
       nom_grupo,
       exclusivo,
       vigente
  from dbo.grupos_contactos
 where cod_area = @cod_area
go

/*------------------------------------------------------
  Procedimiento para el registro/actualización del grupo
  ------------------------------------------------------ */
create procedure dbo.act_grupo_contacto
(
 @cod_area		varchar(10),
 @nro_grupo		smallint	= null,
 @nom_grupo		varchar(255),
 @exclusivo		char(1),
 @vigente		char(1)
)
as
begin

  if @nro_grupo = 0 or @nro_grupo is null
     begin
       select @nro_grupo = isnull(max(nro_grupo), 0) + 1
	 from dbo.grupos_contactos (tablockx)
	where cod_area = @cod_area

       insert into dbo.grupos_contactos(cod_area, nro_grupo, nom_grupo, exclusivo, vigente)
       values(@cod_area, @nro_grupo, @nom_grupo, @exclusivo, @vigente)
     end
  else 
     begin
       update dbo.grupos_contactos   
	  set nom_grupo = @nom_grupo,
	      exclusivo = @exclusivo,
	      vigente   = @vigente
	where cod_area  = @cod_area  
	  and nro_grupo = @nro_grupo   
     end

end
go

execute dbo.act_grupo_contacto @cod_area='ADM', @nom_grupo='Alumnos 2015', @exclusivo='S', @vigente='S'
execute dbo.act_grupo_contacto @cod_area='ADM', @nro_grupo=1, @nom_grupo='Alumnos Presenciales 2015', @exclusivo='S', @vigente='S'

declare @cod_area varchar(10) = 'ADM'

select cod_area,
       nro_grupo,
       nom_grupo,
       exclusivo,
       vigente
  from dbo.grupos_contactos
 where cod_area = @cod_area
go
