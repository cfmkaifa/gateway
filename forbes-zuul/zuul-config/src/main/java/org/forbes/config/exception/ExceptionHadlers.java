package org.forbes.config.exception;

import org.forbes.comm.vo.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * ExceptionHadlers概要说明：
 * @author Huanghy
 */
@RestControllerAdvice
public class ExceptionHadlers {

	
	/***
	 * errorHandler方法慨述:
	 * @param ex
	 * @return Result<Object>
	 * @创建人 huanghy
	 * @创建时间 2019年11月24日 下午3:27:27
	 * @修改人 (修改了该文件，请填上修改人的名字)
	 * @修改日期 (请填上修改该文件时的日期)
	 */
	@ExceptionHandler(value = BindException.class)
    public Result<Object> errorHandler(BindException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        Result<Object> reponseResult = new Result<Object>();
        reponseResult.error500(errorMsg.toString());
        return reponseResult;
    }
}
