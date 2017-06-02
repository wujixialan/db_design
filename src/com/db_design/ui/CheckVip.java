package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.model.User;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by root on 17-1-24.
 */
public class CheckVip implements ActionListener {
    private Main main;
    private JTable table;
    private JPanel panel, outPanel, panel1;
    private JLabel vipL;
    private JTextField vipTF;
    private JButton okButton;
    private List<User> users;

    public CheckVip() {
    }

    public CheckVip(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("查询")) {
            users = MenuListener.userService.findByVip(vipTF.getText().trim());
            updateUI(users);
        } else if(e.getActionCommand().equals("查看会员信息")) {
            users = MenuListener.userService.findVipUser();
            updateUI(users);
        }
    }

    public void updateUI(List<User> users) {
        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        outPanel.add(panel);
        vipL = new JLabel("工号");
        vipTF = new JTextField(20);
        vipTF.setPreferredSize(new Dimension(20, 25));
        okButton = new JButton("查询");
        okButton.addActionListener(this);
        vipTF.addActionListener(this);
        panel1 = new JPanel();
        panel1.add(vipL);
        panel1.add(vipTF);
        panel1.add(okButton);

        Object[][] data = new Object[users.size()][8];

        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getVipNo();
            data[i][1] = users.get(i).getPhoneNumber();
            data[i][2] = users.get(i).getRegisterDate();
            data[i][3] = users.get(i).getAuthority();
        }

        TableModel model = new DefaultTableModel(data,
                new Object[]{"工号", "手机号", "注册时间", "用户类型"});
        table = new JTable(model);
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(750, 400));
        JScrollPane scrollPane = new JScrollPane(table);


        Box b3 = Box.createHorizontalBox();
        b3.add(Box.createHorizontalStrut(15));

        Box b1 = Box.createVerticalBox();
        b1.add(panel1);

        Box b2 = Box.createVerticalBox();
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
