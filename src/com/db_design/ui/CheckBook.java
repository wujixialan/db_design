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
 * Created by root on 17-1-10.
 */
public class CheckBook implements ActionListener {
    private Main main;
    private JTable table;
    private JButton okButton;
    private JPanel panel, outPanel, panel1;
    private JLabel bookL;
    private JTextField bookTF;
    private Box b1, b2, b3;

    public static List<Book> books = null;

    public CheckBook() {

    }

    public CheckBook(Main main) {
        this.main = main;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (books == null) {
            books = MenuListener.bookService.findByBook();
            updateUI(books);
        } else if(e.getSource() == this.okButton){
            books = MenuListener.bookService.getByName(bookTF.getText());
            updateUI(books);
            if(books == null || books.size() == 0) {
                JOptionPane.showMessageDialog(null, "该书不存在");
            }
        }
    }

    public void updateUI(List<Book> bs) {
        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        outPanel.add(panel);
        Object[][] data = new Object[bs.size()][8];

        panel1 = new JPanel();
        bookL = new JLabel("查询的书名");
        bookTF = new JTextField(20);
        bookTF.setPreferredSize(new Dimension(20, 25));
        okButton = new JButton("查询");
        okButton.addActionListener(this);
        bookTF.addActionListener(this);
        panel1.add(bookL);
        panel1.add(bookTF);
        panel1.add(okButton);
        if (bs == null) {
            bs = MenuListener.bookService.findByBook();
        }
        for (int i = 0; i < bs.size(); i++) {
            data[i][0] = bs.get(i).getIsbn();
            data[i][1] = bs.get(i).getBookName();
            data[i][2] = bs.get(i).getAuthor();
            data[i][3] = bs.get(i).getSale_price();
            data[i][4] = bs.get(i).getPublishing();
            data[i][5] = bs.get(i).getPublishDate();
            data[i][6] = bs.get(i).getQuantity();
        }

        TableModel model = new DefaultTableModel(data,
                new Object[]{"ISBN", "书名", "作者", "售价(元)", "出版社", "出版时间", "库存量(本)"});
        table = new JTable(model);
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(750, 400));
        JScrollPane scrollPane = new JScrollPane(table);

        b3 = Box.createHorizontalBox();
        b3.add(Box.createHorizontalStrut(15));

        b1 = Box.createVerticalBox();
        b1.add(panel1);

        b2 = Box.createVerticalBox();
        b2.add(b1);
        b2.add(Box.createVerticalStrut(15));
        b2.add(scrollPane);
        b2.add(Box.createVerticalStrut(15));
        b2.add(b3);

        MenuListener.listener.remove(outPanel);
        panel.add(b2);
        outPanel.add(panel);
        MenuListener.listener.updateUI(outPanel);
    }
}
