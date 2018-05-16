package net.ebook.common.constants;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:31 2018/5/16
 * @Modified By:
 */
public class OrderStatus {

    public static final long OVERDUE=-1;  //逾期未还
    public static final long CHECK_BORROW=0;  //借阅审核中
    public static final long BORROWED=1;  //借阅中
    public static final long CHECK_RETURN=2;  //归还审核中
    public static final long RETURNED=3;  //已归还
}
