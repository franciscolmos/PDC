use pdc
go

-- drop table dbo.tipos_documentos

/* --------------------------------------------------
   Tabla: tipos_documentos
   -------------------------------------------------- */
create table dbo.tipos_documentos
(
 cod_tipo_documento	varchar(6)		not null
					constraint PK__tipos_documentos__END primary key,
 tipo_documento		varchar(100)	not null,
 mascara			varchar(50)		not null,
 numerico			char(1)			not null
					constraint CK__tipos_documentos__numerico__END check(numerico in ('S', 'N'))
					constraint DF__tipos_documentos__numerico__N__END default 'N',
 tipo_persona		char(1)			not null
					constraint CK__tipos_documentos__tipo_persona__END check(tipo_persona in ('F', 'J'))
)
go

insert into dbo.tipos_documentos(cod_tipo_documento, tipo_documento, mascara, numerico, tipo_persona)
values('DNI', 'Documento Nacional de Identidad', '##.###.###', 'N', 'F'),
      ('LC', 'Libreta Cívica', '##.###.###', 'S', 'F'),
      ('LE', 'Libreta Enrolamiento', '##.###.###', 'S', 'F'),   
      ('PAS', 'Pasaporte', '##########', 'S', 'F'),
      ('CUIT', 'CUIT', '##-########-#', 'S', 'J')
go

/* --------------------------------------------------
   Consulta de tipos de documentos
   -------------------------------------------------- */
select * 
  from dbo.tipos_documentos
go   

/* --------------------------------------------------
   Procedimiento: get_datos_tipo_documento
   -------------------------------------------------- */
create or alter procedure dbo.get_datos_tipo_documento
(
 @cod_tipo_documento	varchar(6)
)
as
begin

  select cod_tipo_documento,
         tipo_documento,
		 mascara,
		 numerico,
		 tipo_persona
    from dbo.tipos_documentos (nolock)
   where cod_tipo_documento = @cod_tipo_documento

end
go

/* --------------------------------------------------
   Procedimiento: del_tipo_documento
   -------------------------------------------------- */
create or alter procedure dbo.del_tipo_documento
(
 @cod_tipo_documento	varchar(6)
)
as
begin
 
  delete 
    from dbo.tipos_documentos
   where cod_tipo_documento = @cod_tipo_documento
    
end
go

