package snake.gui.map;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import snake.game.field.GameField;


public class JtableMap implements DrawableMap{
    private JTable jTableMap = new JTable();
    
    private String[] columnNames;
    
    private Boolean[][] tableContent;

    public JtableMap(GameField gameField) {
        int height = 26*gameField.getRowCount();
        int width = 26*gameField.getColumnCount();
        
        jTableMap.setEnabled(false);
        jTableMap.setSize(new java.awt.Dimension(height, width));
        jTableMap.setRowHeight(26);
        jTableMap.setRowSelectionAllowed(false);
        jTableMap.setShowHorizontalLines(false);
        jTableMap.setShowVerticalLines(false);
        jTableMap.setTableHeader(null);
        jTableMap.setUpdateSelectionOnSort(false);
        jTableMap.setVerifyInputWhenFocusTarget(false);
        
        tableContent = gameField.getGameFieldArray();
        
        gameField.setDrawMap(this);
        
        this.drawMap();
    }
    
    @Override
    public Component getMapComponent() {
        return jTableMap;
    }

    @Override
    public boolean drawMap() {
        
        try {
            // присваиваем пустоту всем заголовкам столбцов, чтобы у таблицы не было заголовоков, а то некрасиво смотрится
            columnNames = new String[tableContent[0].length];

            for (int i = 0; i < columnNames.length; i++) {
                columnNames[i] = "";
            }
            

            // игровое поле будет отображаться в super без заголовков столбцов
            jTableMap.setModel(new DefaultTableModel(tableContent, columnNames));


            // вместо текста в ячейках таблицы устанавливаем отображение картинки
            for (int i = 0; i < jTableMap.getColumnCount(); i++) {
                jTableMap.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
                TableColumn a = jTableMap.getColumnModel().getColumn(i);
                a.setPreferredWidth(26);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        
        return true;
    }

}


class ImageRenderer extends DefaultTableCellRenderer{
    private JLabel lbl = new JLabel();
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        
        ImageIcon icon = null;
        
        if (value.equals(true)) {
            icon = new ImageIcon( getClass().getResource("/snake/images/wait.png") );
        }
        
        lbl.setText(null);
        lbl.setIcon(icon);
        
        return lbl;
    }

}