package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.utils.DateUtil;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 17-1-21.
 */
public class MonthTotal implements ActionListener {
    private Main main;
    private JLabel yearL, monthL;
    private JTextField yearTF, monthTF;
    private JPanel panel, outPanel;
    private JButton cancelB, okB;
    private String year = "", month = "";
    private JTable table;

    public MonthTotal() {
    }

    public MonthTotal(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        yearL = new JLabel("年 份");
        yearTF = new JTextField(20);
        monthL = new JLabel("月 份");
        monthTF = new JTextField(20);
        cancelB = new JButton("取消");
        okB = new JButton("查询");

        Box b1 = Box.createHorizontalBox();
        b1.add(yearL);
        b1.add(Box.createHorizontalStrut(15));
        b1.add(yearTF);
        yearTF.setPreferredSize(new Dimension(20, 25));

        Box b2 = Box.createHorizontalBox();
        b2.add(monthL);
        b2.add(Box.createHorizontalStrut(15));
        b2.add(monthTF);
        monthTF.setPreferredSize(new Dimension(20, 25));

        Box b4 = Box.createHorizontalBox();
        b4.add(cancelB);
        b4.add(Box.createHorizontalStrut(15));
        b4.add(okB);

        Box b3 = Box.createVerticalBox();
        b3.add(Box.createVerticalStrut(130));
        b3.add(new JLabel("查看月收入"));
        b3.add(Box.createVerticalStrut(20));
        b3.add(b1);
        b3.add(Box.createVerticalStrut(20));
        b3.add(b2);
        b3.add(Box.createVerticalStrut(20));
        b3.add(b4);

        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuListener.listener.remove(panel);
                MenuListener.listener.updateUI(panel);
            }
        });

        okB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year = yearTF.getText().trim();
                month = monthTF.getText().trim();
                Double totalMonth = 0.0;
                Date start = null;
                Date end = null;

                if (year.length() == 0) {
                    JOptionPane.showMessageDialog(null, "查询的年份必须输！");
                    return;
                }
                if (month.length() == 0) {
                    JOptionPane.showMessageDialog(null, "查询的月份必须输！");
                    return;
                }

                if(Integer.parseInt(year) < 1970 || Integer.parseInt(year) > 2050) {
                    JOptionPane.showMessageDialog(panel, "输入的年份超出范围！");
                    return;
                }

                if(Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
                    JOptionPane.showMessageDialog(panel, "输入的月份超出范围！");
                    return;
                }

                if (Integer.parseInt(year) > Calendar.getInstance().get(Calendar.YEAR)) {
                    JOptionPane.showMessageDialog(panel, "输入的年份有误！");
                    return;
                }

                if (Integer.parseInt(month) == 1 || Integer.parseInt(month) == 3 ||
                        Integer.parseInt(month) == 5 || Integer.parseInt(month) == 7 ||
                        Integer.parseInt(month) == 8 || Integer.parseInt(month) == 10 ||
                        Integer.parseInt(month) == 12) {
                    start = new Date(DateUtil.StringCovertToDate(year + "-" + month + "-01").getTime());
                    end = new Date(DateUtil.StringCovertToDate(year + "-" + month + "-31").getTime());
                } else if (Integer.parseInt(month) == 4 || Integer.parseInt(month) == 6 ||
                        Integer.parseInt(month) == 9 || Integer.parseInt(month) == 1) {
                    start = new Date(DateUtil.StringCovertToDate(year + "-" + month + "-01").getTime());
                    end = new Date(DateUtil.StringCovertToDate(year + "-" + month + "-30").getTime());
                } else if (Integer.parseInt(month) == 2) {
                    start = new Date(DateUtil.StringCovertToDate(year + "-" + month + "-01").getTime());
                    end = new Date(DateUtil.StringCovertToDate(year + "-" + month + "-28").getTime());
                }

                Map<String, Double> profit = new HashMap<String, Double>();
                List<String> bookIsbn = MenuListener.tradeService.getISBN();
                profit = MenuListener.tradeService.getTotalMonth(start, end, bookIsbn);
                String[][] data = new String[profit.size()][2];
                int i = 0;
                for (Map.Entry<String, Double> entry : profit.entrySet()) {
                    data[i][0] = entry.getKey();
                    data[i][1] = String.valueOf(entry.getValue());
                    i++;
                    totalMonth = totalMonth + entry.getValue();
                }
                TableModel model = new DefaultTableModel(data,
                        new Object[]{"ISBN", "月收入"});
                table = new JTable(model);
                table.setRowHeight(30);
                table.setEnabled(false);
                table.setPreferredScrollableViewportSize(new Dimension(750, 400));
                JScrollPane scrollPane = new JScrollPane(table);
                Box box = Box.createVerticalBox();
                box.add(scrollPane);
                box.add(new JLabel(year + "年" + month + "月的月收入为 " + totalMonth + " 元"));
                MenuListener.listener.remove(panel);
                panel.add(box);
                outPanel.add(panel);
                MenuListener.listener.updateUI(panel);
            }
        });

        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        MenuListener.listener.remove(outPanel);
        panel.add(b3);
        outPanel.add(panel);
        MenuListener.listener.updateUI(outPanel);
    }
}
