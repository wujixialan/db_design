package com.db_design.listener;

import com.db_design.model.User;
import com.db_design.service.BookService;
import com.db_design.service.ProfitService;
import com.db_design.service.TradeService;
import com.db_design.service.UserService;
import com.db_design.service.impl.BookServiceImpl;
import com.db_design.service.impl.ProfitServiceImpl;
import com.db_design.service.impl.TradeServiceImpl;
import com.db_design.service.impl.UserServiceImpl;
import com.db_design.ui.Profit;
import com.db_design.utils.DBUtils;

/**
 * Created by root on 17-1-20.
 */
public interface MenuListener {
    static Listener listener = new Listener();
    static UserService userService = new UserServiceImpl();
    static BookService bookService = new BookServiceImpl();
    static TradeService tradeService = new TradeServiceImpl();
    static ProfitService profitService = new ProfitServiceImpl();
    static User user = new User();
//    static String path = "/media/zhaoxiaogang/software/Document/Java/db_design/src/icon";
    static String path = "D:\\Document\\Java\\db_design/src/icon";
}
