package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.model.Book;
import com.db_design.service.BookService;
import com.db_design.service.TradeService;
import com.db_design.service.impl.BookServiceImpl;
import com.db_design.service.impl.TradeServiceImpl;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * Created by root on 17-1-8.
 */
public class SaleBook implements ActionListener {
    public static JTable table;
    private JButton cancelButton, okButton, subButton, addButton;
    private JPanel panel, outPanel;
    private JLabel vipL, countL;
    private JTextField vipTF, countTF;
    private Box b1, b2, b3;
    private Main main;
    public static String vipNo;
    private List<Book> books = null;
    Map<String, Integer> map = new HashMap<String, Integer>();
    Map<String, Double> map1 = new HashMap<String, Double>();

    public SaleBook() {
    }

    public SaleBook(Main main) {
        this.main = main;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        outPanel.add(panel);
        books = MenuListener.bookService.findByBook();
        Object[][] data = new Object[books.size()][8];
        cancelButton = new JButton("取消");
        okButton = new JButton("下订单");
        vipL = new JLabel("会员号");
        vipTF = new JTextField(20);
        countL = new JLabel("折数");
        countTF = new JTextField(20);

        subButton = new JButton(new ImageIcon(MenuListener.path + "/edit_sub.png"));
        addButton = new JButton(new ImageIcon(MenuListener.path + "/edit_add.png"));


        for (int i = 0; i < books.size(); i++) {
            data[i][0] = books.get(i).getIsbn();
            data[i][1] = books.get(i).getBookName();
            data[i][2] = books.get(i).getAuthor();
            data[i][3] = books.get(i).getSale_price();
            data[i][4] = books.get(i).getPublishing();
            data[i][5] = books.get(i).getPublishDate();
            data[i][6] = books.get(i).getQuantity();
            data[i][7] = "0";
        }

        TableModel model = new DefaultTableModel(data,
                new Object[]{"ISBN", "书名", "作者", "售价(元)", "出版社", "出版时间", "库存量(本)", "购买的数量(本)"});
        table = new JTable(model);
        table.setRowHeight(30);
        // TODO: 17-1-12 设置表格的大小 setPreferredScrollableViewportSize
        table.setPreferredScrollableViewportSize(new Dimension(750, 400));
        JScrollPane scrollPane = new JScrollPane(table);
        b1 = Box.createHorizontalBox();
        b1.add(vipL);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(vipTF);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(countL);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(countTF);

        for (int i = 0; i < books.size(); i++) {
            map.put((String) table.getValueAt(i, 0), 0);
        }

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
                for (int i = 0; i < books.size(); i++) {
                    map1.put((String) table.getValueAt(i, 0), Double.valueOf(String.valueOf(table.getValueAt(i, 3))));
                }
//                得到选中行的索引
                boolean flag = false;
                if (vipTF.getText().trim().equals("")) {
                    for (Map.Entry<String, Integer> entry: map.entrySet()) {
                        for(Map.Entry<String, Double> entry1: map1.entrySet()) {
                            if (entry.getValue() != 0) {
                                if(entry.getKey().equals(entry1.getKey())) {
                                    double salePrice = entry1.getValue();
                                    flag = MenuListener.tradeService.add(entry.getKey(),
                                            entry.getValue(), (salePrice * entry.getValue()));
                                    show(flag);
                                }
                            }
                        }
                    }
                } else {
                    boolean flag2 = MenuListener.userService.getVip(vipTF.getText().trim());
                    vipNo = vipTF.getText().trim();
                    if (flag2) {
                        double count = Double.parseDouble(countTF.getText().trim());
                        if (count > 1 && count < 10) {
                            for (Map.Entry<String, Integer> entry: map.entrySet()) {
                                for(Map.Entry<String, Double> entry1: map1.entrySet()) {
                                    if (entry.getValue() != 0) {
                                        if (entry.getKey().equals(entry1.getKey())) {
                                            double salePrice = entry1.getValue();
                                            count = count * 0.1;
                                            flag = MenuListener.tradeService.add(entry.getKey(),
                                                    entry.getValue(), (salePrice * (entry.getValue()) * count));
                                            show(flag);
                                        }
                                    }
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "输入的打折数不合法！");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "该会员账号不存在！");
                        return;
                    }
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int quantity = Integer.parseInt(table.getValueAt(row, 6).toString());
                int value = Integer.parseInt(table.getValueAt(row, 7).toString());
                if (quantity > 0) {
                    map.put(table.getValueAt(row, 0).toString(), value + 1);
                    table.setValueAt(quantity - 1 + "", row, 6);
                    table.setValueAt(value + 1 + "", row, 7);
                } else if (quantity < 0) {
                    table.setValueAt(0, row, 6);
                    table.setValueAt(value, row, 7);
                }
                table.updateUI();

            }
        });
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                String quantity = table.getValueAt(row, 6).toString();
                int value = Integer.parseInt(table.getValueAt(row, 7).toString());
                if (value > 0) {
                    map.put(table.getValueAt(row, 0).toString(), value);
                    table.setValueAt((Integer.parseInt(quantity) + 1) + "", row, 6);
                    table.setValueAt((value - 1) + "", row, 7);
                } else if (value < 0) {
                    table.setValueAt(quantity, row, 6);
                    table.setValueAt(0 + "", row, 7);
                }
                table.updateUI();
            }
        });

        b3 = Box.createHorizontalBox();
        b3.add(subButton);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(addButton);
        b3.add(Box.createHorizontalStrut(15));
        b3.add(cancelButton);
        b3.add(Box.createHorizontalStrut(15));
        b3.add(okButton);

        b2 = Box.createVerticalBox();
        b2.add(scrollPane);
        b2.add(Box.createVerticalStrut(15));
        b2.add(b1);
        b2.add(Box.createVerticalStrut(15));
        b2.add(b3);
        MenuListener.listener.remove(outPanel);
        panel.add(b2);
        outPanel.add(panel);
        MenuListener.listener.updateUI(outPanel);
    }

    private void show(boolean flag) {
        if (flag) {
            JOptionPane.showMessageDialog(panel, "购物成功");
            MenuListener.listener.remove(panel);
            MenuListener.listener.updateUI(panel);
            return;
        } else {
            JOptionPane.showMessageDialog(panel, "购物失败");
            return;
        }
    }
}
