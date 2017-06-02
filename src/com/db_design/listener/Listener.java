package com.db_design.listener;

import com.db_design.utils.DateUtil;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 17-1-9.
 */
public class Listener {
    public void remove(JPanel panel) {
        panel.removeAll();
        panel.repaint();
    }

    public void updateUI(JPanel panel) {
        panel.updateUI();
    }
}
