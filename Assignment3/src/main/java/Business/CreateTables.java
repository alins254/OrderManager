package Business;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CreateTables {
	
	public static JTable createTables(LinkedList<Object> obj) {
		if(obj.isEmpty()) return null;
		String[] coloane = new String[0];
		String[][] randuri = new String[0][5];
		int nrColoane=0;
		int nrRand=0;
		for(Field field:obj.getFirst().getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if(!field.getName().equals("nextId")) {
				coloane = Arrays.copyOf(coloane, coloane.length+1);
				coloane[coloane.length-1]=new String(field.getName());
				nrColoane++;
			}
			//System.out.println(field.getName());
		}
		for(Object obiect:obj) {
			randuri=Arrays.copyOf(randuri, randuri.length+1);
			randuri[nrRand] = new String[nrColoane];
			int index=0;
			for(Field field:obj.getFirst().getClass().getDeclaredFields()) {
				field.setAccessible(true);
				if(field.getName().equals("nextId")) continue;
				try {
					randuri[nrRand][index++] = new String(field.get(obiect).toString());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			nrRand++;
		}
		TableModel model = new DefaultTableModel(randuri,coloane);
		JTable t = new JTable(model);
		return t;
	}
}
