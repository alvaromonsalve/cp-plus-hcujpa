/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

/**
 *
 * @author Alvaro Monsalve
 */
public class numericKeypad extends JPanel{

    
    public numericKeypad(final JTable Tabla, final int row, final JPopupMenu pop){
        Tabla.setValueAt("",row,2);
        setLayout(new GridLayout(4,3,0,0));
        ActionListener accion=new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               JButton b=(JButton)e.getSource();
               if(b.getText().equals("X")){
                   if(((String)Tabla.getValueAt(row, 2)).equals("")||((String)Tabla.getValueAt(row, 2)).equals("0")||
                           ((String)Tabla.getValueAt(row, 2)).equals("00")||((String)Tabla.getValueAt(row, 2)).equals("000")){
                       Tabla.setValueAt("1", row, 2);
                   }
                   pop.setVisible(false);
               }else{
                   Tabla.setValueAt(((String)Tabla.getValueAt(row, 2))+b.getText(), row, 2);
                   if(((String)Tabla.getValueAt(row, 2)).length()==3){
                       pop.setVisible(false);
                   }
               }
            }
        };
        
        for(int i=0;i<12;i++){
            if(i<9){
                JButton b=new JButton(""+(i+1));
                b.addActionListener(accion);
                add(b);
            }else{
                if(i==9){
                    JButton b=new JButton("");
                    b.setEnabled(false);
                    add(b);
                }
                if(i==10){
                    JButton b=new JButton("0");
                    b.addActionListener(accion);
                    add(b);
                }
                if(i==11){
                    JButton b=new JButton("X");
                    b.addActionListener(accion);
                    add(b);
                    
                }
            }
        }
    }
    
}
