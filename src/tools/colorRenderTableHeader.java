/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Alvaro Monsalve
 */
public class colorRenderTableHeader extends DefaultTableCellRenderer{
    Color background;
    Color foreground;
    
    public colorRenderTableHeader(Color backColor, Color foreColor){
        super();
        this.background = backColor;
        this.foreground =foreColor;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column){
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        comp.setBackground(background);
        comp.setForeground(foreground);
        return comp;
    }
    
}
