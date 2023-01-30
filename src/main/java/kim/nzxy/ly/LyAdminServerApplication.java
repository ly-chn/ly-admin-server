package kim.nzxy.ly;

import kim.nzxy.ly.modules.system.entity.Dict;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @author ly
 */
@SpringBootApplication
public class LyAdminServerApplication {

    public static void main(String[] args) {
        Function<Dict, Long> getId = Dict::getId;
        Method method = ReflectionUtils.findMethod(Dict.class, "getId");
        System.out.println(getId);
        SpringApplication.run(LyAdminServerApplication.class, args);
    }

}
