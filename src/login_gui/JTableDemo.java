/*
 * 此类用于支持MainPageStudent.java中的表格格式
 * 单双行交叉色显示
 * 边框颜色可设置
 * 点击行的颜色可设置
 * Author:lyz
 * Date: 2010-7-28
 */

package login_gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import java.awt.*;

public class JTableDemo extends JTable {
	private static final long serialVersionUID = -655361681066423663L;
	TableColumn firstColumn;
	TableColumn secondColumn;
	TableColumn thirdColumn;
	

	private Color selectionColor = new Color(192, 252, 221);// 行选择颜色
	private Color evenRowColor = new Color(244, 244, 244);// 奇数行颜色
	private Color oddRowColor = new Color(255, 255, 255);// 偶数行颜色
	private Color gridColor = new Color(225,242,250);// 网格颜色

	private int rowHeight = 50;// 行高度

	public JTableDemo(TableModel tableModel) {
		super(tableModel);
		this.setGridColor(gridColor);
		this.setRowHeight(rowHeight);	
		this.setFont(new Font("微软雅黑", Font.PLAIN, 16));
	} 

	public void TableSetColumn(int first,int second,int third){
		firstColumn = this.getColumnModel().getColumn(0);
		firstColumn.setMinWidth(first);
		secondColumn = this.getColumnModel().getColumn(1);
		firstColumn.setMinWidth(second);
		thirdColumn = this.getColumnModel().getColumn(2);
		firstColumn.setMinWidth(third);
	}
	
	
	public TableCellRenderer getCellRenderer(int row, int column) {
		return new MyCellRenderer();
	}

	class MyCellRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row,int column) {
			this.setHorizontalAlignment(JLabel.CENTER);
			Component cell = super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
			this.setColor(cell, table, isSelected, hasFocus, row, column);
			return cell;
		}

		/*
		 * 
		 * 设置颜色
		 */

		private void setColor(Component component, JTable table,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				component.setBackground(selectionColor);
				setBorder(null);// 去掉边
			} else {
				if (row % 2 == 0) {
					component.setBackground(evenRowColor);
				} else {
					component.setBackground(oddRowColor);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
