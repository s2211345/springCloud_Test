package gen;



import gen.service.SysGeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 类的功能描述.
 *
 * @auther wangc
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GenApplication.class)
public class GenLocalUtils {
    @Resource
    private SysGeneratorService generatorService;

    @Test
    public void generatorCode(){
//        generatorService.generatorCode(new String[]{"bx_loan", "bx_loan_area", "bx_loan_case", "bx_loan_contacts", "bx_loan_crowd", "bx_loan_evaluate", "bx_loan_handling", "bx_loan_otherfee", "bx_loan_purpose"}, 0);
        generatorService.generatorCode(new String[]{"sys_user"}, 0);
    }
}













