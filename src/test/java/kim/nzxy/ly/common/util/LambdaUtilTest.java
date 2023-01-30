package kim.nzxy.ly.common.util;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class LambdaUtilTest {

    @Test
    void test() {
        @Data
        class Person {
            private String id;
        }
        Function<Person, String> getId = Person::getId;
        SFunction<Person, ?> getId1 = Person::getId;
        System.out.println(getId);
        System.out.println(getId1);
    }
}
