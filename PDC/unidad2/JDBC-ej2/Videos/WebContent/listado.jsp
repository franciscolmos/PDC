<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.LinkedList,ar.edu.ubp.pdc.beans.VideoBean" %>
<%!
boolean containsVideo(Cookie cookies[], int nroVideo) {
	for(Cookie c: cookies) {
		if(c.getName().contains("video") && Integer.parseInt(c.getValue()) == nroVideo)
			return true;
	}
	return false;
}
%>
<%
LinkedList<VideoBean> videos = (LinkedList<VideoBean>) request.getAttribute("videos");
int i = 0;
short nroCategoria;
if(videos.size() > 0) {
	while(i < videos.size()) {
		out.println("<b>" + videos.get(i).getNomCategoria() + "</b>");
		nroCategoria = videos.get(i).getNroCategoria();
		
		while(i < videos.size() && nroCategoria == videos.get(i).getNroCategoria()) {
			out.println("<p>");
			out.println("<a href=\"#\" onclick=\"jVideos.ver(this)\">" + videos.get(i).getTitulo() + "</a>");
			out.println("<i>" + videos.get(i).getCantante() + "</i>");
			out.println("<span>" + (containsVideo(request.getCookies(), videos.get(i).getNroVideo()) ? "[ Visto ]" : "") + "</span>");
			out.println("<input type=\"hidden\" name=\"titulo\" value=\"" + videos.get(i).getTitulo() + "\">");
			out.println("<input type=\"hidden\" name=\"cantante\" value=\"" + videos.get(i).getCantante() + "\">");
			out.println("<input type=\"hidden\" name=\"link_video\" value=\"" + videos.get(i).getLinkVideo() + "\">");
			out.println("<input type=\"hidden\" name=\"nro_video\" value=\"" + videos.get(i).getNroVideo() + "\">");
			out.println("</p>");
			i++;
		}
		out.println("<br>");
	}
}
else {
	out.println("<p>No existen videos que satisfagan la búsqueda</p>");
}
%>