package factorialservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: pyotruk
 * Date: 2015-07-21
 */

@RestController
public class MainController {

    @RequestMapping("/factorial")
    public String input(@RequestParam(value = "n") String n) {
        try {
            PoolService.getInstance().add(Long.parseUnsignedLong(n));
            return "Запрос на вычисление принят";

        } catch (NumberFormatException e) {
            return "Error: Факториал может быть вычислен только для целого положительного числа";

        } catch (Exception e) {
           return "Error:"+e.getMessage();
        }
    }

    @RequestMapping("/")
    public String result() {
        List<String> r = PoolService.getInstance().getResult();
        StringBuilder sb = new StringBuilder("");
        int i = 0;
        for(String s: r) sb.append(++i).append(".").append(s).append("<br/>");
        return sb.toString();
    }

}
