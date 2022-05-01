use pdc
go

drop table dbo.videos
drop table dbo.categorias_videos

/* ----------------------------------------------
   Tabla: categorias_videos
   ---------------------------------------------- */
create table dbo.categorias_videos
(
 nro_categoria		tinyint			not null,
 nom_categoria		varchar(50)		not null
 constraint PK__categorias_videos__END
			primary key (nro_categoria)
);
go

insert into dbo.categorias_videos(nro_categoria, nom_categoria)
values(1, 'BLUE'),  
      (2, 'CLÁSICA'),  
      (3, 'COUNTRY'),  
      (4, 'FUNK'),  
      (5, 'HIP HOP'),  
      (6, 'JAZZ'),  
      (7, 'POP'),
      (8, 'ROCK'),
      (9, 'ROCK ALTERNATIVO');
go

/* ----------------------------------------------
   Tabla: videos
   ---------------------------------------------- */
create table dbo.videos
(
 nro_video			integer			not null identity(1,1),
 titulo				varchar(255)	not null,
 cantante			varchar(255)	not null,
 nro_categoria		tinyint			not null,
 link_video			varchar(255)	not null,
 constraint PK__videos__END
			primary key (nro_video),
 constraint FK__videos__categorias_videos__END
			foreign key (nro_categoria)
			references dbo.categorias_videos
);
go
    
insert into dbo.videos(titulo, cantante, nro_categoria, link_video)
values('Patience', 'Guns N'' Roses', 8, 'https://www.youtube.com/embed/ErvgV4P6Fzc'),
      ('Paradise City', 'Guns N'' Roses', 8, 'https://www.youtube.com/embed/Rbm6GXllBiw'),
      ('Smells Like Teen Spirit', 'Nirvana', 8, 'https://www.youtube.com/embed/hTWKbfoikeg'),
      ('Come As You Are', 'Nirvana', 8, 'https://www.youtube.com/embed/vabnZ9-ex7o'),
      ('Creep', 'Radiohead', 9, 'https://www.youtube.com/embed/XFkzRNyygfk'),
      ('The Reason', 'Hoobastank', 9, 'https://www.youtube.com/embed/fV4DiAyExN0'),
      ('Layla (acoustic)', 'Eric Clapton', 1, 'https://www.youtube.com/embed/Q_L-0Ryhmic'),
      ('Tears In Heaven', 'Eric Clapton', 1, 'https://www.youtube.com/embed/JxPj3GAYYZ0'),
      ('Uptown Funk ft. Bruno Mars', 'Mark Ronson', 4, 'https://www.youtube.com/embed/OPf0YbXqDm0'),
      ('Happy', 'Pharrell Williams', 4, 'https://www.youtube.com/embed/y6Sxv-sUYtM'),
      ('When I Was Your Man', 'Bruno Mars', 7, 'https://www.youtube.com/embed/ekzHIouo8Q4'),
      ('Let Her Go', 'Passenger', 7, 'https://www.youtube.com/embed/RBumgq5yVrA'),
      ('Nothing Compares 2U', 'Sinéad O''Connor', 7, 'https://www.youtube.com/embed/iUiTQvT0W_0');
go

/* ----------------------------------------------
   Procedimiento: get_categorias_videos
   ---------------------------------------------- */
create procedure dbo.get_categorias_videos
as
begin

  select nom_categoria,
         nro_categoria
	from dbo.categorias_videos (nolock)
   order by nom_categoria

end;
go

-- execute dbo.get_categorias_videos

/* ----------------------------------------------
   Consulta: videos
   ---------------------------------------------- */
declare @nro_categoria		tinyint = 7,
        @string_busqueda	varchar(255)

select c.nom_categoria,
       v.nro_categoria,
       v.titulo,
	   v.cantante,
	   v.link_video,
	   v.nro_video
  from dbo.videos v (nolock)
       join dbo.categorias_videos c (nolock)
	     on v.nro_categoria = c.nro_categoria
 where (@nro_categoria  is null
    or  v.nro_categoria = @nro_categoria)
   and  v.titulo + ' ' + v.cantante + ' ' + v.titulo like '%' + isnull(ltrim(rtrim(@string_busqueda)), '') + '%' 
 order by c.nom_categoria,
          v.titulo;
go

