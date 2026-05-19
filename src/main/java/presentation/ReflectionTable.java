package presentation;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class ReflectionTable {
    public static DefaultTableModel createTable(List<?> objects){
        Class<?> type=objects.get(0).getClass();
        Field[] fields=type.getDeclaredFields();
        String[] columnNames=new String[fields.length];
        for(int i=0;i<fields.length;i++)
            columnNames[i]=fields[i].getName();
        DefaultTableModel model=new DefaultTableModel(columnNames,0);
        for(Object obj:objects)
        {
            Object[] row=new Object[fields.length];
            for(int i=0;i<fields.length;i++)
            {
                fields[i].setAccessible(true);
                try {
                    row[i]=fields[i].get(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(row);
        }
        return model;

    }
}
