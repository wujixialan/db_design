package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.model.Book;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Created by root on 17-1-7.
 */
public class InStock implements ActionListener{
    private JLabel isbnL, bookNameL, authorL, priceL, salePriceL, publishingL, quantityL;
    private JTextField isbn, bookName, author, price, salePrice, publishing,
            quantity;
    private JPanel panel, outPanel;
    private JButton cancelButton, okButton;
    public Book book = new Book();
    private Box b1, b2, b3, b4, b5, b6, b7, b8, b9;

    private Main main;

    public InStock() {
    }

    public InStock(Main main) {
        this.main = main;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        outPanel.add(panel);

        isbnL = new JLabel("   ISBN   ");
        bookNameL = new JLabel("  书  名   ");
        authorL = new JLabel("  作   者  ");
        priceL = new JLabel("进价(元) ");
        salePriceL = new JLabel("售价(元) ");
        publishingL = new JLabel("出 版 社 ");
        quantityL = new JLabel("数量(本)");

        isbn = new JTextField(20);
        isbn.setPreferredSize(new Dimension(20, 25));
        bookName = new JTextField(20);
        bookName.setPreferredSize(new Dimension(20, 25));
        author = new JTextField(20);
        author.setPreferredSize(new Dimension(20, 25));
        price = new JTextField(20);
        price.setPreferredSize(new Dimension(20, 25));
        salePrice = new JTextField(20);
        salePrice.setPreferredSize(new Dimension(20, 25));
        publishing = new JTextField(20);
        publishing.setPreferredSize(new Dimension(20, 25));
        quantity = new JTextField(20);
        quantity.setPreferredSize(new Dimension(20, 25));
        cancelButton = new JButton("取消");
        okButton = new JButton("入库");


        b1 = Box.createHorizontalBox();
        b1.add(isbnL);
        b1.add(Box.createHorizontalStrut(15));
        b1.add(isbn);

        b2 = Box.createHorizontalBox();
        b2.add(bookNameL);
        b2.add(Box.createHorizontalStrut(15));
        b2.add(bookName);

        b3 = Box.createHorizontalBox();
        b3.add(authorL);
        b3.add(Box.createHorizontalStrut(15));
        b3.add(author);

        b4 = Box.createHorizontalBox();
        b4.add(priceL);
        b4.add(Box.createHorizontalStrut(15));
        b4.add(price);

        b5 = Box.createHorizontalBox();
        b5.add(salePriceL);
        b5.add(Box.createHorizontalStrut(15));
        b5.add(salePrice);

        b6 = Box.createHorizontalBox();
        b6.add(publishingL);
        b6.add(Box.createHorizontalStrut(15));
        b6.add(publishing);

        b7 = Box.createHorizontalBox();
        b7.add(quantityL);
        b7.add(Box.createHorizontalStrut(15));
        b7.add(quantity);

        b8 = Box.createHorizontalBox();
        b8.add(cancelButton);
        b8.add(Box.createHorizontalStrut(15));
        b8.add(okButton);

        b9 = Box.createVerticalBox();
        b9.add(Box.createVerticalStrut(80));
        b9.add(b1);
        b9.add(Box.createVerticalStrut(20));
        b9.add(b2);
        b9.add(Box.createVerticalStrut(20));
        b9.add(b3);
        b9.add(Box.createVerticalStrut(20));
        b9.add(b4);
        b9.add(Box.createVerticalStrut(20));
        b9.add(b5);
        b9.add(Box.createVerticalStrut(20));
        b9.add(b6);
        b9.add(Box.createVerticalStrut(20));
        b9.add(b7);
        b9.add(Box.createVerticalStrut(20));
        b9.add(b8);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuListener.listener.remove(panel);
                MenuListener.listener.updateUI(panel);
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;

                if(isbn.getText().trim() == null || isbn.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "isbn 不能为空！");
                    return;
                }
                if(bookName.getText().trim() == null || bookName.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "书名不能为空！");
                    return;
                }
                if(author.getText().trim() == null || author.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "作者不能为空！");
                    return;
                }
                if(price.getText().trim() == null || price.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "进价不能为空！");
                    return;
                }
                if(salePrice.getText().trim() == null || salePrice.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "售价不能为空！");
                    return;
                }
                if(publishing.getText().trim() == null  || publishing.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "出版社不能为空！");
                    return;
                }
                if(quantity.getText().trim() == null  || quantity.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "入库数量不能为空！");
                    return;
                }
                if(Double.parseDouble(price.getText().trim()) <= 0
                        || Double.parseDouble(salePrice.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "价格不能为负或0！");
                    return;
                }

                if(Integer.parseInt(quantity.getText().trim()) <= 0) {
                    JOptionPane.showMessageDialog(null, "入库的库存必须大于 0！");
                    return;
                }

                if(Double.parseDouble(price.getText().trim()) > Double.parseDouble(salePrice.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "售价必须高于进价！");
                    return;
                }
                book.setIsbn(isbn.getText());
                book.setBookName(bookName.getText());
                book.setAuthor(author.getText());
                book.setPrice(Double.parseDouble(price.getText()));
                book.setSale_price(Double.parseDouble(salePrice.getText()));
                book.setPublishing(publishing.getText());
                book.setPublishDate(new Date(new java.util.Date().getTime()));
                book.setQuantity(Integer.parseInt(quantity.getText()));
                int yesOrNo = JOptionPane.showConfirmDialog(null, "确定录入吗", "提示信息", JOptionPane.YES_NO_OPTION);
                if (yesOrNo == 0) {
                    flag = MenuListener.bookService.findByIsbn(book.getIsbn());
                    if (flag) {
                        int qu = book.getQuantity() + MenuListener.bookService.findByIsbnQuantity(book.getIsbn());
                        flag = MenuListener.bookService.modifyQuantity(book.getIsbn(), qu);
                    } else {
                        flag = MenuListener.bookService.add(book);
                    }
                }
                if (flag) {
                    JOptionPane.showMessageDialog(null, "录入成功");
                    MenuListener.listener.remove(panel);
                    MenuListener.listener.updateUI(panel);
                } else {
                    JOptionPane.showMessageDialog(null, "录入失败");
                }
            }
        });

        MenuListener.listener.remove(outPanel);
        panel.add(b9);
        outPanel.add(panel);
        MenuListener.listener.updateUI(outPanel);
    }
}
