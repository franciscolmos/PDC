package ej1.sessions.tarea;

public class Tarea {
	private String fecha, prio, desc, not, email;
	
	public Tarea () {
		
	}
	
	public Tarea(String f, String p, String d, String n, String e) {
		this.fecha = f;
		this.prio = p;
		this.desc = d;
		this.not = n;
		this.email = e;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPrio() {
		return prio;
	}

	public void setPrio(String prio) {
		this.prio = prio;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getNot() {
		return not;
	}

	public void setNot(String not) {
		this.not = not;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
