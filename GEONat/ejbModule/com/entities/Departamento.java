package com.entities;

public enum Departamento{
	ARTIGAS("Artigas",1),
	CANELONES("Canelones",2),
	CERROLARGO("Cerro Largo",3),
	COLONIA("Colonia",4),
	DURAZNO("Durazno",5),
	FLORES("Flores",6),
	FLORIDA("Florida",7),
	LAVALLEJA("Lavalleja",8),
	MALDONADO("Maldonado",9),
	MONTEVIDEO("Montevideo",10),
	PAYSANDU("Paysandú",11),
	RIONEGRO("Río Negro",12),
	RIVERA("Rivera",13),
	ROCHA("Rocha",14),
	SALTO("Salto",15),
	SANJOSE("San José",16),
	SORIANO("Soriano",17),
	TACUAREMBO("Tacuarembó",18),
	TREINTAYTRES("Treinta y Tres",19);
	
			private String Departamente;
			private int id_Departamento;
			
	
			private Departamento(String departamente, int id_Departamento) {
				Departamente = departamente;
				this.id_Departamento = id_Departamento;
			}
			
			public String getDepartamente() {
				return Departamente;
			}
			
			public void setDepartamente(String departamente) {
				Departamente = departamente;
			}
			
			public int getId_Departamento() {
				return id_Departamento;
			}
			
			public void setId_Departamento(int id_Departamento) {
				this.id_Departamento = id_Departamento;
			}
			
	
	
}
