package net.ebook.web.ctrl;

import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.OperationStatus;
import net.ebook.common.constants.UrlConstants;
import net.ebook.common.web.ErrorResult;
import net.ebook.common.web.ResponseMessage;
import net.ebook.common.web.SuccessResult;
import net.ebook.web.data.BookOrderVO;
import net.ebook.web.data.OrderItemVO;
import net.ebook.web.logic.OrderLogic;
import net.ebook.web.logic.UserOperationLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:51 2018/2/18
 * @Modified By:
 */
@RestController
public class OrderController extends BaseController {

    @Autowired
    OrderLogic orderLogic;

    @Autowired
    UserOperationLogic operationLogic;

    @RequestMapping(value = UrlConstants.API_ORDER,method = RequestMethod.GET)
    public Map<String, Object> getOrder(@RequestParam(value = "orderId")Long orderId,
                                        HttpServletRequest request){
        try{
            BookOrderVO vo=orderLogic.findById(orderId);
            operationLogic.recordUserOperation(request, OperationStatus.ORDER_DETAIL);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, vo);
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API + "orders",method = RequestMethod.GET)
    public Map<String, Object> getOrders(@RequestParam(value = "userId",required = false)Long userId,
                                         @RequestParam(value = "page")Integer page,
                                         @RequestParam(value = "rows")Integer rows,
                                        HttpServletRequest request){
        try{
            PageInfo<BookOrderVO> voPageInfo=orderLogic.findByUserId(userId,page,rows);
            operationLogic.recordUserOperation(request, OperationStatus.ORDER_LIST);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, voPageInfo);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_ORDER,method = RequestMethod.POST)
    public Map<String, Object> createOrder(@RequestBody @NotNull BookOrderVO vo,
                                         HttpServletRequest request){
        try{
            vo=orderLogic.createOrder(vo);
            operationLogic.recordUserOperation(request, OperationStatus.ORDER_CREATE);
            return SuccessResult.ok(ResponseMessage.ITEM_RESULT, vo);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_ORDER,method = RequestMethod.PUT)
    public Map<String, Object> updateOrder(@RequestBody @NotNull BookOrderVO vo,
                                           HttpServletRequest request){
        try{
            orderLogic.updateOrder(vo);
            operationLogic.recordUserOperation(request, OperationStatus.ORDER_UPDATE);
            return SuccessResult.ok();
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_ORDER,method = RequestMethod.DELETE)
    public Map<String, Object> deleteOrder(@RequestParam(value = "orderId")Long orderId,
                                           HttpServletRequest request){
        try{
            orderLogic.deleteOrder(orderId);
            operationLogic.recordUserOperation(request, OperationStatus.ORDER_DELETE);
            return SuccessResult.ok();
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = UrlConstants.API_ORDER+"/item",method = RequestMethod.PUT)
    public Map<String, Object> updateOrderItem(@RequestBody @NotNull OrderItemVO vo,
                                           HttpServletRequest request){
        try{
            orderLogic.updateItem(vo);
            operationLogic.recordUserOperation(request, OperationStatus.BOOK_RETURN);
            return SuccessResult.ok();
        }catch (Exception e){
            return new ErrorResult(e.getMessage());
        }
    }
}
