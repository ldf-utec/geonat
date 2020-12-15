package com.presentacion.componentes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;


// Se utiliza para darle formato de fecha a las celdas de una JTable
//https://www.invalidexpression.net/2016/11/java-formato-fecha-hora-jtable.html


public class DateCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public DateCellRenderer() {
        super();
    }

    public void setValue(Object value) {
        if (formatter == null) {
            formatter = DateFormat.getDateInstance();
        }
        setText((value == null) ? "" : formatter.format(value));
    }
}
