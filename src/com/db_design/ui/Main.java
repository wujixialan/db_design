package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Main extends JFrame {
    public JLabel nameLabel, passwordLabel, authorityLabel;
    public JTextField nameTF;
    public JPasswordField passwordPF;
    public JComboBox<String> authority;
    public JButton loginButton, registerButton;

    private JMenu vip;
    private JMenuItem checkVipMessage, modifyVipMessage, registerVip;

    private JMenu self;
    private JMenuItem modifyPwd, checkMessage, modifyMessage;

    private JMenu bookManage;
    private JMenuItem inStock, checkStock;

    private JMenu saleManage;
    private JMenuItem saleBook, monthMoney, profit;

    private JMenu check;
    public JMenuItem checkBook;
    private JMenu logoutMenu;
    private JMenuItem logout;
    private JMenuBar menuBar;
    private JPanel panel = null, outPanel = null;
    public Main main;

    public Main() {
        outPanel = (JPanel) this.getContentPane();
        this.panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        this.outPanel.add(this.panel);

        this.nameLabel = new JLabel(" 工 号 ");
        this.nameTF = new JTextField();
        nameTF.setPreferredSize(new Dimension(20, 25));
        this.passwordLabel = new JLabel(" 密 码 ");
        this.passwordPF = new JPasswordField(20);
        passwordPF.setPreferredSize(new Dimension(20, 25));
        this.loginButton = new JButton("登陆");
        this.registerButton = new JButton("注册");
        this.authorityLabel = new JLabel(" 类 型 ");
        this.authority = new JComboBox<>();
        authority.addItem("请选择");
        authority.addItem("管理员");
        JFrame frame1 = this;

        Box b1 = Box.createHorizontalBox();
        b1.add(this.nameLabel);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(this.nameTF);

        Box b2 = Box.createHorizontalBox();
        b2.add(this.passwordLabel);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(this.passwordPF);

        Box b5 = Box.createHorizontalBox();
        b5.add(this.authorityLabel);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(this.authority);

        Box b3 = Box.createHorizontalBox();
        b3.add(this.loginButton);
        b3.add(Box.createHorizontalStrut(35));
        b3.add(this.registerButton);

        JFrame frame = this;

        self = new JMenu("自我管理");
        modifyPwd = new JMenuItem("修改密码");
        checkMessage = new JMenuItem("查看详细信息");
        modifyMessage = new JMenuItem("修改信息");
        self.add(modifyPwd);
        self.add(checkMessage);
        self.add(modifyMessage);

        modifyPwd.addActionListener(new ModifyPwd(this));
        checkMessage.addActionListener(new CheckMessage(this));
        modifyMessage.addActionListener(new ModifyMessage(this));

        bookManage = new JMenu("书库管理");
        inStock = new JMenuItem("入库");
        checkStock = new JMenuItem("查看库存");
        bookManage.add(inStock);
        bookManage.add(checkStock);
        inStock.addActionListener(new InStock(this));
        checkStock.addActionListener(new CheckStock(this));

        monthMoney = new JMenuItem("月收入");
        profit = new JMenuItem("利润");
        monthMoney.addActionListener(new MonthTotal(this));
        profit.addActionListener(new Profit(this));

        saleManage = new JMenu("买书管理");
        saleBook = new JMenuItem("售书");
        saleManage.add(saleBook);
        saleManage.add(monthMoney);
        saleManage.add(profit);
        saleBook.addActionListener(new SaleBook(this));

        checkBook = new JMenuItem("查询图书");
        check = new JMenu("查询");
        check.add(checkBook);
        checkBook.addActionListener(new CheckBook(this));

        vip = new JMenu("会员管理");
        checkVipMessage = new JMenuItem("查看会员信息");
        modifyVipMessage = new JMenuItem("修改会员信息");
        registerVip = new JMenuItem("注册会员");
        vip.add(registerVip);
        vip.add(checkVipMessage);
        vip.add(modifyVipMessage);
        registerVip.addActionListener(new RegisterVip(this));
        checkVipMessage.addActionListener(new CheckVip(this));
        modifyVipMessage.addActionListener(new ModifyVipMessage(this));

//        注册监听器
        registerButton.addActionListener(new Register(this));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("登陆")) {
                    if (nameTF.getText().trim().length() == 0) {
                        JOptionPane.showMessageDialog(panel, "工号不能为空");
                        return;
                    }
                    if (passwordPF.getPassword().length == 0) {
                        JOptionPane.showMessageDialog(panel, "密码不能为空");
                        return;
                    }
                    if (authority.getSelectedItem().equals("请选择")) {
                        JOptionPane.showMessageDialog(panel, "类型必须选");
                        return;
                    }
                    boolean flag = MenuListener.userService.findByVipNo(nameTF.getText(), String.valueOf(passwordPF.getPassword()), authority.getSelectedItem().toString());
                    if (flag) {
                        MenuListener.listener.remove(panel);
                        frame.setJMenuBar(menuBar);
                        MenuListener.listener.updateUI(panel);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(outPanel, "登录失败");
                        return;
                    }
                }
            }
        });
        List<Box> boxes = new ArrayList<>();
        boxes.add(b1);
        boxes.add(b2);
        boxes.add(b5);
        boxes.add(b3);
        Box b4 = initGUI(boxes);

        logoutMenu = new JMenu("注销");
        logout = new JMenuItem("注销");
        logoutMenu.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTF.setText("");
                passwordPF.setText("");
                authority.setSelectedItem("请选择");
                Box box = initGUI(boxes);
                MenuListener.listener.remove(outPanel);
                frame1.setJMenuBar(null);
                panel.add(box);
                outPanel.add(panel);
                MenuListener.listener.updateUI(outPanel);
            }
        });
        menuBar = new JMenuBar();
        menuBar.add(self);
        menuBar.add(vip);
        menuBar.add(bookManage);
        menuBar.add(saleManage);
        menuBar.add(check);
        menuBar.add(logoutMenu);

        this.panel.add(b4);
        this.setSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Main(Main main) {
        this.main = main;
    }
    public static void main(String[] args) {
        new Main();
    }

    public Box initGUI(java.util.List<Box> boxes) {
        Box b4 = Box.createVerticalBox();
        b4.add(Box.createVerticalStrut(130));
        b4.add(boxes.get(0));
        b4.add(Box.createVerticalStrut(20));
        b4.add(boxes.get(1));
        b4.add(Box.createVerticalStrut(20));
        b4.add(boxes.get(2));
        b4.add(Box.createVerticalStrut(20));
        b4.add(boxes.get(3));
        return b4;
    }
}
