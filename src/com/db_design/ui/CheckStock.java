package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.model.Book;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by root on 17-1-8.
 */
public class CheckStock implements ActionListener {
    public static JTable table;
    private Main main;
    private JPanel panel, outPanel;
    private Box b1;

    public CheckStock() {
    }

    public CheckStock(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel = (JPanel) main.getContentPane();
        outPanel = new ImgPanel(MenuListener.path + "/a1.jpg");
        panel.add(outPanel);
        List<Book> books = MenuListener.bookService.findByBook();
        Object[][] data = new Object[books.size()][7];

        b1 = Box.createVerticalBox();
        for (int i = 0; i < books.size(); i++) {
            data[i][0] = books.get(i).getIsbn();
            data[i][1] = books.get(i).getBookName();
            data[i][2] = books.get(i).getAuthor();
            data[i][3] = books.get(i).getSale_price();
            data[i][4] = books.get(i).getPublishing();
            data[i][5] = books.get(i).getPublishDate();
            data[i][6] = books.get(i).getQuantity();
        }

        TableModel model = new DefaultTableModel(data,
                new Object[]{"ISBN", "书名", "作者", "售价(元)", "出版社", "出版时间", "库存量(本)"});
        table = new JTable(model);
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(750, 400));
        JScrollPane scrollPane = new JScrollPane(table);

        b1.add(scrollPane);
        MenuListener.listener.remove(panel);
        outPanel.add(b1);
        panel.add(outPanel);
        MenuListener.listener.updateUI(panel);
    }
}
