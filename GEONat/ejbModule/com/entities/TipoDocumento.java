package com.entities;

public enum TipoDocumento{
		CI("Cedula de Identidad",1),
		PASAPORTE("Pasarporte",2),
		CARTA_CIUDADANIA("Carta de Ciudadania",3),
		OTRO("Otros",4);
				
		private String TipoDocumento;
		private int id_TipoDocumento;
		
		private TipoDocumento (String TipoDocumento, int id_TipoDocumento) {
			this.id_TipoDocumento=id_TipoDocumento;
			this.TipoDocumento=TipoDocumento;
		}

		public String getTipoDocumento() {
			return TipoDocumento;
		}

		public void setTipoDocumento(String TipoDocumento) {
			this.TipoDocumento = TipoDocumento;
		}

		public int getId_TipoDocumento() {
			return id_TipoDocumento;
		}

		public void setId_TipoDocumento(int id_TipoDocumento) {
			this.id_TipoDocumento = id_TipoDocumento;
		}

}
